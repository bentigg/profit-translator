package com.action;

import java.util.ArrayList;

import com.models.Payment;

public class PaymentCalculator {
	private double totalGross;
	private double totalPaypalFee;
	private double totalNet;
	private ArrayList<Payment> payments;
	
	public PaymentCalculator(ArrayList<Payment> payments)
	{
		this.payments = payments;
		findTotalAmounts(payments);
	}

	private void findTotalAmounts(ArrayList<Payment> payments) {
		totalGross = 0;
		totalPaypalFee = 0;
		totalNet = 0;
		
		for (int i = 0; i < payments.size(); i++)
		{
			totalGross += payments.get(i).getGross();
			totalPaypalFee += payments.get(i).getFee();
			totalNet += payments.get(i).getNet();
		}
	}
	
	public void printTotals()
	{
		System.out.println("Total Gross: " + totalGross);
		System.out.println("Total Paypal fee: " + totalPaypalFee);
		System.out.println("Total Net: " + totalNet);
	}

	public double getTotalGross() {
		return totalGross;
	}

	public void setTotalGross(double totalGross) {
		this.totalGross = totalGross;
	}

	public double getTotalPaypalFee() {
		return totalPaypalFee;
	}

	public void setTotalPaypalFee(double totalPaypalFee) {
		this.totalPaypalFee = totalPaypalFee;
	}

	public double getTotalNet() {
		return totalNet;
	}

	public void setTotalNet(double totalNet) {
		this.totalNet = totalNet;
	}

	public ArrayList<Payment> getPayments() {
		return payments;
	}

	public void setPayments(ArrayList<Payment> payments) {
		this.payments = payments;
	}
	
	
	

}
