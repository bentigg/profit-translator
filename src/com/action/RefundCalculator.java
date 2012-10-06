package com.action;

import java.util.ArrayList;

import com.models.Generic;

public class RefundCalculator {
	
	private double totalGross;
	private double totalPaypalFee;
	private double totalNet;
	ArrayList<Generic> refunds;
	
	public RefundCalculator(ArrayList<Generic> refunds)
	{
		this.refunds = refunds;
		totalGross = 0;
		totalPaypalFee = 0;
		totalNet = 0;
		
		for (int i = 0; i < refunds.size(); i++)
		{
			totalGross += refunds.get(i).getGross();
			totalPaypalFee += refunds.get(i).getFee();
			totalNet += refunds.get(i).getNet();
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

	public ArrayList<Generic> getRefunds() {
		return refunds;
	}

	public void setRefunds(ArrayList<Generic> refunds) {
		this.refunds = refunds;
	}
	
	

}
