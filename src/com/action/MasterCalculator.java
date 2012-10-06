package com.action;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MasterCalculator {
	
	private double otherShippingCosts;
	private double eBayFixedFees;
	private double gross;
	private double fees;
	private double net;
	private double totalProfit;
	private double lisaProfit;
	public static final double lisaRate = .7;
	private PaymentCalculator calcRec;
	private PaymentCalculator calcSent;
	private RefundCalculator calcRefund;
	
	public MasterCalculator(PaymentCalculator calcRec, PaymentCalculator calcSent, RefundCalculator calcRefund)
	{
		this.calcRec = calcRec;
		this.calcSent = calcSent;
		this.calcRefund = calcRefund;
		
		System.out.println("Enter the following costs (must be positive)");
		Scanner in = new Scanner(System.in);
		
        System.out.println("eBay fees (listing, store subscription, final value):");
        eBayFixedFees = in.nextDouble();
        if (eBayFixedFees != 0)
        {
            eBayFixedFees = eBayFixedFees * -1.0;
        }
        System.out.println("Other non-PayPal shipping costs: ");
        otherShippingCosts = in.nextDouble();
        if (otherShippingCosts != 0)
        {
            otherShippingCosts = otherShippingCosts * -1.0;
        }
        
        
        gross = calcRec.getTotalGross() + calcSent.getTotalGross() + calcRefund.getTotalGross();
        fees = calcRec.getTotalPaypalFee() + calcSent.getTotalPaypalFee() + calcRefund.getTotalPaypalFee();
        net = calcRec.getTotalNet() + calcSent.getTotalNet() + calcRefund.getTotalNet();
        totalProfit = net + eBayFixedFees + otherShippingCosts;
        lisaProfit = totalProfit * lisaRate;
        
       in.close();
	}

	public double getOtherShippingCosts() {
		return otherShippingCosts;
	}

	public void setOtherShippingCosts(double otherShippingCosts) {
		this.otherShippingCosts = otherShippingCosts;
	}

	public double geteBayFixedFees() {
		return eBayFixedFees;
	}

	public void seteBayFixedFees(double eBayFixedFees) {
		this.eBayFixedFees = eBayFixedFees;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getNet() {
		return net;
	}

	public void setNet(double net) {
		this.net = net;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public double getLisaProfit() {
		return lisaProfit;
	}

	public void setLisaProfit(double lisaProfit) {
		this.lisaProfit = lisaProfit;
	}

	public static double getLisarate() {
		return lisaRate;
	}

	public PaymentCalculator getCalcRec() {
		return calcRec;
	}

	public void setCalcRec(PaymentCalculator calcRec) {
		this.calcRec = calcRec;
	}

	public PaymentCalculator getCalcSent() {
		return calcSent;
	}

	public void setCalcSent(PaymentCalculator calcSent) {
		this.calcSent = calcSent;
	}

	public RefundCalculator getCalcRefund() {
		return calcRefund;
	}

	public void setCalcRefund(RefundCalculator calcRefund) {
		this.calcRefund = calcRefund;
	}
	
	
	
	

}
