/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loanaccount;

/**
 *
 * @author Freez
 */
public class LoanAccount {
    private static double annualInterestRate;
    private final double principal;
    
    private LoanAccount(double principal){
        this.principal = principal;
    }
    
    public double calculateMonthlyPayment(int numberofPayments){
        double monthlyInterest = annualInterestRate/12;    
        double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberofPayments))); 
        return monthlyPayment;
    }
    public static void setAnnualInterestRate(double newInterestRate){
        annualInterestRate = newInterestRate;
    }

    public static void main(String[] args) {
        LoanAccount loan1 = new LoanAccount(5000.00);
        LoanAccount loan2 = new LoanAccount(31000.00);
        setAnnualInterestRate(0.01);
        System.out.printf("Monthly payments for loan1 of $%.2f and loan2 $%.2f for 3, 5, and 6 year loans at 1%% interest.%n",loan1.principal,loan2.principal);
        System.out.println("Loan    3 years 5 years 6 years");
        System.out.printf("loan1   %.2f    %.2f    %.2f%n",loan1.calculateMonthlyPayment(36),loan1.calculateMonthlyPayment(60),loan1.calculateMonthlyPayment(72));
        System.out.printf("loan2   %.2f    %.2f    %.2f%n",loan2.calculateMonthlyPayment(36),loan2.calculateMonthlyPayment(60),loan2.calculateMonthlyPayment(72));
        setAnnualInterestRate(0.05);
        System.out.printf("%nMonthly payments for loan1 of $%.2f and loan2 $%.2f for 3, 5, and 6 year loans at 5%% interest.%n",loan1.principal,loan2.principal);
        System.out.println("Loan    3 years 5 years 6 years");
        System.out.printf("loan1   %.2f    %.2f    %.2f%n",loan1.calculateMonthlyPayment(36),loan1.calculateMonthlyPayment(60),loan1.calculateMonthlyPayment(72));
        System.out.printf("loan2   %.2f    %.2f    %.2f%n",loan2.calculateMonthlyPayment(36),loan2.calculateMonthlyPayment(60),loan2.calculateMonthlyPayment(72));
    }
    
}
