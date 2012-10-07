package com.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.models.Generic;
import com.models.Payment;

public class ReadFile {

	private ArrayList<Generic> genericList;
	private ArrayList<Payment> paymentReceivedList;
	private ArrayList<Payment> paymentSentList;
	private ArrayList<Payment> paymentEbayFees;
	private ArrayList<Generic> refundList;
	private ArrayList<Generic> notIncludedList;
	
	private int mLineNumber = 0;

	public ReadFile()
	{

	}

	public ReadFile(String location)
	{
		genericList = new ArrayList<Generic>();
		paymentReceivedList = new ArrayList<Payment>();
		paymentSentList = new ArrayList<Payment>();
		paymentEbayFees = new ArrayList<Payment>();
		refundList = new ArrayList<Generic>();
		notIncludedList = new ArrayList<Generic>();
		
		int tokenNumber = 0;
		
		try
		{

			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(location) );
			String strLine = "";
			StringTokenizer st = null;
			//skip first line
			br.readLine();

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null)
			{
				Generic generic = new Generic();

				mLineNumber++;

				//TODO: fix for , within a datacell
				//break comma separated line using " "," "
				String delim = "\t";
				st = new StringTokenizer(strLine,delim);

				while(st.hasMoreTokens())
				{

					tokenNumber++;
					String next = st.nextToken();
					
					if (next != null)
					{
						next = next.trim();
						next = next.replace("\"","");
					}
					
					switch (tokenNumber) {
					case 1:  //date
						generic.setDate(next);
						break;
					case 2:  //time
						generic.setTime(next);
						break;
					case 3:  //timeZone
						generic.setTimeZone(next);
						break;
					case 4:  //name
						generic.setName(next);
						break;
					case 5:  //type
						generic.setType(next);
						break;
					case 6:  //status
						generic.setStatus(next);
						break;
					case 7:  //gross
						if (isParsable(Double.class,next))
						{
							generic.setGross(Double.parseDouble(next));
						} else {
							generic.setGross(0);
						}
						break;
					case 8:  //fee
						if (isParsable(Double.class,next))
						{
							generic.setFee(Double.parseDouble(next));
						} else {
							generic.setFee(0);
						}
						break;
					case 9:  //net
						if (isParsable(Double.class,next))
						{
							generic.setNet(Double.parseDouble(next));
						} else {
							generic.setNet(0);
						}
						break;
					case 10: //fromEmail
						generic.setFromEmail(next);
						break;
					case 12: //transactionId
						generic.setTransactionId(next);
						break;
					case 13: //counterPartyStatus
						generic.setCounterPartyStatus(next);
						break;
					case 14:  //addressStatus
						generic.setAddressStatus(next);
						break;
					case 15:  //itemTitle
						generic.setItemTitle(next);
						break;
					case 16:  //itemId
						generic.setItemId(next);
						break;
					case 17:  //shippingAndHandling);
						if (isParsable(Double.class,next))
						{
							generic.setShippingAndHandling(Double.parseDouble(next));
						} else {
							generic.setShippingAndHandling(0);
						}
						break;
					case 18: //insurance
						if (isParsable(Double.class,next))
						{
							generic.setInsurance(Double.parseDouble(next));
						} else {
							generic.setInsurance(0);
						}
						break;
					case 19: //salesTax
						if (isParsable(Double.class,next))
						{
							generic.setSalesTax(Double.parseDouble(next));
						} else {
							generic.setSalesTax(0);
						}
						break;
					case 25: //buyerId
						generic.setBuyerId(next);
						break;
					case 26:  //itemUrl
						generic.setItemUrl(next);
						break;
					case 28:  //referenceTxnId
						generic.setReferenceTxnId(next);
						break;
					case 31:  //receiptId
						generic.setReceiptId(next);
						break;
					case 32:  //balance
						if (isParsable(Double.class,next))
						{
							generic.setBalance(Double.parseDouble(next));
						} else {
							generic.setBalance(0);
						}
						break;
					case 33: //addressLine1
						generic.setAddressLine1(next);
						break;
					case 34: //addressLine2
						generic.setAddressLine2(next);
						break;
					case 35: //city
						generic.setCity(next);
						break;
					case 36:  //state
						generic.setState(next);
						break;
					case 37:  //zipCode
						generic.setZipCode(next);
						break;
					case 38:  //country
						generic.setCountry(next);
						break;
					default: //if not found
						break;
					}
				}
				//reset token number
				tokenNumber = 0;
				genericList.add(generic);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception while reading csv file: " + e + " + e.getMessage()"); 
			System.out.println("LINE: " + mLineNumber);
			System.out.print(" TOKEN: " + tokenNumber);
		}
		try{
			separateGenerics();
		}
		catch(Exception e)
		{
			System.out.println("Exception while separating generics: ");
			e.printStackTrace();
		}
	}
	
	private void separateGenerics()
	{
		for (int i = 0; i < genericList.size(); i++)
		{
			String type = genericList.get(i).getType();
			if (type.equals("Cancelled Fee"))
			{
				createRefund(i);
			} else if (type.equals("Express Checkout Payment Received"))
			{
				createPaymentReceived(i);
				
			} else if (type.equals("Mobile Express Checkout Payment Received"))
			{
				createPaymentReceived(i);
				
			} else if (type.equals("Mobile Payment Received"))
			{
				createPaymentReceived(i);
				
			} else if (type.equals("Express Checkout Payment Sent"))
			{
				createPaymentSent(i);
				
			}else if (type.equals("Payment Sent"))
			{
				createPaymentSent(i);
			} else if (type.equals("Preapproved Payment Sent"))
			{
				//NOTE: eBay payments, not always in same month
				if (!genericList.get(i).getItemTitle().equalsIgnoreCase("Monthly eBay Seller Fees"))	//if its not eBay Fees
				{
					createPaymentSent(i);
				} else {
					createUnusedItem(i);
				}

			} else if (type.equals("Update to eCheck Received"))
			{
				createUnusedItem(i);
			} else if (type.equals("Withdraw Funds to a Bank Account"))
			{
				createUnusedItem(i);
			} else if (type.equals("Refund"))
			{
				createRefund(i);
			} else if (type.equals("Update to Reversal"))
			{
				createUnusedItem(i);
			} else if (type.equals("Shopping Cart Payment Sent"))	
			{
				createUnusedItem(i);
				//this is usually for purchases from PayPal (shouldn't be included in calculations)
			} else if (type.equals("Temporary Hold"))
			{
				createUnusedItem(i);
				//Do nothing...Has no effect 
				
			}else {
				System.out.println("Did not find type for item: " + genericList.get(i).print());
			}
		}
	}
	
	private void createUnusedItem(int i)
	{
		notIncludedList.add(genericList.get(i));
		
	}

	private void createPaymentReceived(int i) {
		Payment payment = new Payment(genericList.get(i));
		paymentReceivedList.add(payment);
	}
	
	private void createPaymentSent(int i) {
		Payment payment = new Payment(genericList.get(i));
		paymentSentList.add(payment);
	}
	
	private void createEbayFees(int i) {
		
		Payment payment = new Payment(genericList.get(i));
		paymentEbayFees.add(payment);
	}
	
	private void createRefund(int i ) {
		refundList.add(genericList.get(i));
	}
	
	/**
	 * METHOD: isParsable<p><p>
	 * 
	 * This method will look through the methods of the specified <code>from</code> parameter
	 * looking for a public method name starting with "parse" which has only one String
	 * parameter.<p>
	 * 
	 * The <code>parser</code> parameter can be a class or an instantiated object, eg:
	 * <code>Integer.class</code> or <code>new Integer(1)</code>. If you use a
	 * <code>Class</code> type then only static methods are considered.<p>
	 * 
	 * When looping through potential methods, it first looks at the <code>Class</code> associated
	 * with the <code>parser</code> parameter, then looks through the methods of the parent's class
	 * followed by subsequent ancestors, using the first method that matches the criteria specified
	 * above.<p>
	 * 
	 * This method will hide any normal parse exceptions, but throws any exceptions due to
	 * programmatic errors, eg: NullPointerExceptions, etc. If you specify a <code>parser</code>
	 * parameter which has no matching parse methods, a NoSuchMethodException will be thrown
	 * embedded within a RuntimeException.<p><p>
	 * 
	 * Example:<br>
	 * <code>isParsable(Boolean.class, "true");<br>
	 * isParsable(Integer.class, "11");<br>
	 * isParsable(Double.class, "11.11");<br>
	 * Object dateFormater = new java.text.SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");<br>
	 * isParsable(dateFormater, "2001.07.04 AD at 12:08:56 PDT");<br></code>
	 * <p>
	 * 
	 * @param parser    The Class type or instantiated Object to find a parse method in.
	 * @param str   The String you want to parse
	 * 
	 * @return true if a parse method was found and completed without exception
	 * @throws java.lang.NoSuchMethodException If no such method is accessible 
	 */
	public static boolean isParsable(Object parser, String str) {
	    Class theClass = (parser instanceof Class? (Class)parser: parser.getClass());
	    boolean staticOnly = (parser == theClass), foundAtLeastOne = false;
	    Method[] methods = theClass.getMethods();

	    // Loop over methods
	    for (int index = 0; index < methods.length; index++) {
	        Method method = methods[index];

	        // If method starts with parse, is public and has one String parameter.
	        // If the parser parameter was a Class, then also ensure the method is static. 
	        if(method.getName().startsWith("parse") &&
	            (!staticOnly || Modifier.isStatic(method.getModifiers())) &&
	            Modifier.isPublic(method.getModifiers()) &&
	            method.getGenericParameterTypes().length == 1 &&
	            method.getGenericParameterTypes()[0] == String.class)
	        {
	            try {
	                foundAtLeastOne = true;
	                method.invoke(parser, str);
	                return true; // Successfully parsed without exception
	            } catch (Exception exception) {
	                // If invoke problem, try a different method
	                /*if(!(exception instanceof IllegalArgumentException) &&
	                   !(exception instanceof IllegalAccessException) &&
	                   !(exception instanceof InvocationTargetException))
	                        continue; // Look for other parse methods*/

	                // Parse method refuses to parse, look for another different method
	                continue; // Look for other parse methods
	            }
	        }
	    }

	    // No more accessible parse method could be found.
	    if(foundAtLeastOne) return false;
	    else throw new RuntimeException(new NoSuchMethodException());
	}


	/**
	 * METHOD: willParse<p><p>
	 * 
	 * A convienence method which calls the isParseable method, but does not throw any exceptions
	 * which could be thrown through programatic errors.<p>
	 * 
	 * Use of {@link #isParseable(Object, String) isParseable} is recommended for use so programatic
	 * errors can be caught in development, unless the value of the <code>parser</code> parameter is
	 * unpredictable, or normal programtic exceptions should be ignored.<p>
	 * 
	 * See {@link #isParseable(Object, String) isParseable} for full description of method
	 * usability.<p>
	 * 
	 * @param parser    The Class type or instantiated Object to find a parse method in.
	 * @param str   The String you want to parse
	 * 
	 * @return true if a parse method was found and completed without exception
	 * @see #isParseable(Object, String) for full description of method usability 
	 */
	public static boolean willParse(Object parser, String str) {
	    try {
	        return isParsable(parser, str);
	    } catch(Throwable exception) {
	        return false;
	    }
	}

	public ArrayList<Generic> getGenericList() {
		return genericList;
	}

	public void setGenericList(ArrayList<Generic> genericList) {
		this.genericList = genericList;
	}

	public ArrayList<Payment> getPaymentReceivedList() {
		return paymentReceivedList;
	}

	public void setPaymentReceivedList(ArrayList<Payment> paymentReceivedList) {
		this.paymentReceivedList = paymentReceivedList;
	}

	public ArrayList<Payment> getPaymentSentList() {
		return paymentSentList;
	}

	public void setPaymentSentList(ArrayList<Payment> paymentSentList) {
		this.paymentSentList = paymentSentList;
	}

	public ArrayList<Generic> getRefundList() {
		return refundList;
	}

	public void setRefundList(ArrayList<Generic> refundList) {
		this.refundList = refundList;
	}

	public ArrayList<Payment> getPaymentEbayFees() {
		return paymentEbayFees;
	}

	public void setPaymentEbayFees(ArrayList<Payment> paymentEbayFees) {
		this.paymentEbayFees = paymentEbayFees;
	}

	public ArrayList<Generic> getNotIncludedList() {
		return notIncludedList;
	}

	public void setNotIncludedList(ArrayList<Generic> notIncludedList) {
		this.notIncludedList = notIncludedList;
	}

	public int getmLineNumber() {
		return mLineNumber;
	}

	public void setmLineNumber(int mLineNumber) {
		this.mLineNumber = mLineNumber;
	}
	
		
}
