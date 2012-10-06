package com.action;

import java.util.ArrayList;

import com.models.Generic;
import com.models.Payment;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        //csv file containing data
        String strLocation = "C:/Users/Ben Tiggelaar/workspace/ProfitTranslater/src/June_July_August_CSV.csv";
		ReadFile readFile = new ReadFile(strLocation);
		
		//get all generic transactions
		ArrayList<Generic> list = readFile.getGenericList();
		
		ArrayList<Payment> paymentReceivedList = readFile.getPaymentReceivedList();
		ArrayList<Payment> paymentSentList = readFile.getPaymentSentList();
		ArrayList<Generic> refundList = readFile.getRefundList();
		
		PaymentCalculator calcRec = new PaymentCalculator(paymentReceivedList);
		PaymentCalculator calcSent = new PaymentCalculator(paymentSentList);
		RefundCalculator calcRefund = new RefundCalculator(refundList);
		MasterCalculator calcMaster = new MasterCalculator(calcRec, calcSent, calcRefund);
		
		WriteFile writeFile = new WriteFile(calcMaster);
		
		/*
		System.out.println("---Payments received---");
		System.out.println("Size: " + paymentReceivedList.size());
		calcRec.printTotals();
		System.out.println("---Payments sent---");
		System.out.println("Size: " + paymentSentList.size());
		calcSent.printTotals();
		System.out.println("---Refunds---");
		System.out.println("Size: " + refundList.size());
		calcRefund.printTotals();
		System.out.println("---Done---");
		*/

	}

}
