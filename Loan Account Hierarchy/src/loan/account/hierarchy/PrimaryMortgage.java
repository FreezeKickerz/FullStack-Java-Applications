/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loan.account.hierarchy;

/**
 *
 * @author Freez
 */
public class PrimaryMortgage extends LoanAccount{
    private final double PMIMonthlyAmount;
    private final Address address;
    
    public PrimaryMortgage(double principal,double annualInterestRate,int months, double PMIMonthlyAmount, Address address){
        super(principal,annualInterestRate,months);
        this.PMIMonthlyAmount = PMIMonthlyAmount;
        this.address = address;
    }
    
    @Override
    public String toString(){
        return String.format("%nPrimary Mortgage Loan with:%nPrincipal: $%.2f%nAnnual Interest Rate: %.2f%%%nTerm of Loan in Months: %s%nMonthly Payment: $%.2f%nPMI Monthly Amount: $%.2f%n%s",super.getPrincipal(),super.getAnnualInterestRate(),super.getMonths(),calculateMonthlyPayment(),PMIMonthlyAmount,address.toString());
    }
}
