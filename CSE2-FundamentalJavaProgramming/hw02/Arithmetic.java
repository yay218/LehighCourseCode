//Name: Yang Yi
//Date: 02/03/14
//Professor: Brian Chen 
//Class: CSE 002


public class Arithmetic {
	public static void main(String[] args) {
		//Number of pairs of socks and Cost per pair of socks
		int nSocks=3;
		double sockCost$=2.58;
		
		//Number of drinking glasses and cost per glass
		int nGlasses=6;
		double glassCost$=2.29;
		
		//Number of boxes of envelopes and cost per box of envelopes
		int nEnvelopes=1;
		double envelopeCost$=3.25;
		
		//PA sale tax
		double taxPercent=0.06; 
		
		//total cost of socks,glass and envelope
		double totalSockCost$,totalGlassCost$,totalEnvelopeCost$;
		
		//sale tax of socks,glass and envelope
		double sockCostTax$,glassCostTax$,envelopeCostTax$;
		
		//total purchase cost,total sale tax and total actually paid
		double totalPurchaseCost$,totalSaleTax$,totalActuallyPaid$;
		
		//final answer of all variables
		double totalSockCostFinal$,totalGlassCostFinal$,totalEnvelopeCostFinal$;
		double sockCostTaxFinal$,glassCostTaxFinal$,envelopeCostTaxFinal$;
		double totalPurchaseCostFinal$,totalSaleTaxFinal$,totalActuallyPaidFinal$;
		
		//calculate total cost of socks,glass and envelope
		totalSockCost$=nSocks*sockCost$;
		sockCostTax$=totalSockCost$*taxPercent;
		totalGlassCost$=nGlasses*glassCost$;
		
		//calculate sale tax of socks,glass and envelope
		glassCostTax$=totalGlassCost$*taxPercent;
		totalEnvelopeCost$=nEnvelopes*envelopeCost$;
		envelopeCostTax$=totalEnvelopeCost$*taxPercent;
		
		//calculate total purchase cost,total sale tax and total actually paid
		totalPurchaseCost$=totalSockCost$+totalGlassCost$+totalEnvelopeCost$;
		totalSaleTax$=totalPurchaseCost$*taxPercent;
		totalActuallyPaid$=totalPurchaseCost$+totalSaleTax$;
		
		//calculate final answer of all variables
		totalSockCostFinal$=(int)(totalSockCost$*100);
		totalGlassCostFinal$=(int)(totalGlassCost$*100);
		totalEnvelopeCostFinal$=(int)(totalEnvelopeCost$*100);
		sockCostTaxFinal$=(int)(sockCostTax$*100);
		glassCostTaxFinal$=(int)(glassCostTax$*100);
		envelopeCostTaxFinal$=(int)(envelopeCostTax$*100);
		totalPurchaseCostFinal$=(int)(totalPurchaseCost$*100);
		totalSaleTaxFinal$=(int)(totalSaleTax$*100);
		totalActuallyPaidFinal$=(int)(totalActuallyPaid$*100);
		
		//print the cost and sales tax of each item
		//print the total cost of the purchases (before tax)
		//print the total sales tax
		//print the total cost of the purchases (including sales tax)
		System.out.println("The total cost of socks is "+(totalSockCostFinal$/100));
		System.out.println("The total cost of glass is "+(sockCostTaxFinal$/100));
		System.out.println("The total cost of envelope is "+(totalGlassCostFinal$/100));
		System.out.println("The sales tax of socks is "+(glassCostTaxFinal$/100));
		System.out.println("The sales tax of glass is "+(totalEnvelopeCostFinal$/100));
		System.out.println("The sales tax of envelope is "+(envelopeCostTaxFinal$/100));
		System.out.println("The total cost of the purchases (before tax) is "+(totalPurchaseCostFinal$/100));
		System.out.println("The total sales tax is "+(totalSaleTaxFinal$/100));
		System.out.println("The total cost of the purchases (including sales tax) is "+(totalActuallyPaidFinal$/100));
	}
}