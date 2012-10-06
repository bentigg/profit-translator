package com.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.models.Generic;

public class WriteFile {

	private String mOutputLocation = "C:/Users/Ben Tiggelaar/workspace/ProfitTranslater/src/June_July_August_CSV.txt";

	WriteFile() {

	}

	WriteFile(MasterCalculator masterCalculator) {
		try {
			File file = new File(mOutputLocation);

			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

	        DecimalFormat formatter = new DecimalFormat("0.00");
	        ///////////////////////////// SALES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	        bw.write("---Sales---");
	        bw.newLine();
	        bw.write("Gross: $" + formatter.format(masterCalculator.getGross()));
	        bw.newLine();
	        bw.write("Fees: $" + formatter.format(masterCalculator.getFees()));
	        bw.newLine();
	        bw.write("Net: $" + formatter.format(masterCalculator.getNet()));
	        bw.newLine();
	        bw.write("eBay fees (listing, store subscription, final value): $" + formatter.format(masterCalculator.geteBayFixedFees()));
	        bw.newLine();
	        bw.write("Other non-PayPal shipping costs: $" + formatter.format(masterCalculator.getOtherShippingCosts()));
	        bw.newLine();
	        bw.write("Total profit: $" + formatter.format(masterCalculator.getTotalProfit()));
	        bw.newLine();
	        bw.write("70% profit: $" + formatter.format(masterCalculator.getLisaProfit()));
	        bw.newLine();
	        bw.newLine();

	        printPayments(masterCalculator.getCalcRec(), bw, formatter, "Payments Received");
	        
	        printPayments(masterCalculator.getCalcSent(), bw, formatter, "Payments Sent");
	        
	        ArrayList<Generic> calcRefundPayments = masterCalculator.getCalcRefund().getRefunds();
	        printRefunds(masterCalculator, calcRefundPayments, bw, formatter);
	        
	   		
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printPayments(PaymentCalculator paymentCalculator, BufferedWriter bw, DecimalFormat formatter, String type) throws IOException {
		bw.write("---Net " + type + "---");
		bw.newLine();
		
		
		for (int i = 0; i < paymentCalculator.getPayments().size(); i++)
		{
			bw.write(i + ": $" + formatter.format(paymentCalculator.getPayments().get(i).getNet()));
			bw.newLine();
		}
		bw.newLine();
		bw.write(type + " Net: $" + formatter.format(paymentCalculator.getTotalNet()));
		bw.newLine();
		bw.write("_________________");
		bw.newLine();
	}
	
	private void printRefunds(MasterCalculator masterCalculator, ArrayList<Generic> refunds, BufferedWriter bw, DecimalFormat formatter) throws IOException {
		bw.write("---Net Refunds ---");
		bw.newLine();
		
		
		for (int i = 0; i < refunds.size(); i++)
		{
			bw.write(i + ": $" + formatter.format(refunds.get(i).getNet()));
			bw.newLine();
		}
		bw.newLine();
		bw.write("Refunds Net: $" + formatter.format(masterCalculator.getCalcRefund().getTotalNet()));
		bw.newLine();
		bw.write("_________________");
		bw.newLine();
	}

}
