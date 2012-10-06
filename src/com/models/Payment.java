package com.models;

public class Payment extends Event {

	private String fromEmail;
	private String counterPartyStatus;
	private String addressStatus;
	private String itemTitle;
	private String itemId;
	private double shippingAndHandling;
	private double insurance;
	private double salesTax;
	private String buyerId;
	private String itemUrl;
	private String referenceTxnId;
	private String receiptId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	public Payment(Generic generic)
	{
		this.fromEmail = generic.getFromEmail();
		this.counterPartyStatus = generic.getCounterPartyStatus();
		this.addressStatus = generic.getAddressStatus();
		this.itemTitle = generic.getItemTitle();
		this.itemId = generic.getItemId();
		this.shippingAndHandling = generic.getShippingAndHandling();
		this.insurance = generic.getInsurance();
		this.salesTax = generic.getSalesTax();
		this.buyerId = generic.getBuyerId();
		this.itemUrl = generic.getItemUrl();
		this.referenceTxnId = generic.getReferenceTxnId();
		this.receiptId = generic.getReceiptId();
		this.addressLine1 = generic.getAddressLine1();
		this.addressLine2 = generic.getAddressLine2();
		this.city = generic.getCity();
		this.state = generic.getState();
		this.zipCode = generic.getZipCode();
		this.country = generic.getCountry();
		this.date = generic.getDate();
		this.time = generic.getTime();
		this.timeZone = generic.getTimeZone();
		this.name = generic.getName();
		this.type = generic.getType();
		this.status = generic.getStatus();
		this.gross = generic.getGross();
		this.fee = generic.getFee();
		this.net = generic.getNet();
		this.transactionId = generic.getTransactionId();
		this.balance = generic.getBalance();
	
	}
	
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getCounterPartyStatus() {
		return counterPartyStatus;
	}
	public void setCounterPartyStatus(String counterPartyStatus) {
		this.counterPartyStatus = counterPartyStatus;
	}
	public String getAddressStatus() {
		return addressStatus;
	}
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public double getShippingAndHandling() {
		return shippingAndHandling;
	}
	public void setShippingAndHandling(double shippingAndHandling) {
		this.shippingAndHandling = shippingAndHandling;
	}
	public double getInsurance() {
		return insurance;
	}
	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}
	public double getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getReferenceTxnId() {
		return referenceTxnId;
	}
	public void setReferenceTxnId(String referenceTxnId) {
		this.referenceTxnId = referenceTxnId;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
