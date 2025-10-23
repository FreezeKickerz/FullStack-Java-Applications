/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loan.account.hierarchy;

/**
 *
 * @author Freez
 */
public class UnsecuredLoan extends LoanAccount{
    public UnsecuredLoan(double principal,double annualInterestRate,int months){
        super(principal,annualInterestRate,months);
    }
    
    @Override
    public String toString(){
        return String.format("%nUnsecured Loan with:%nPrincipal: $%.2f%nAnnual Interest Rate: %.2f%%%nTerm of Loan in Months: %s%nMonthly Payment: $%.2f%n",getPrincipal(),getAnnualInterestRate(),getMonths(),calculateMonthlyPayment());
    }
    
}
