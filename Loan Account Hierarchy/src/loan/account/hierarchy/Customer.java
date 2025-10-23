/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loan.account.hierarchy;

import java.util.ArrayList;

/**
 *
 * @author Freez
 */
public class Customer {
    public String firstName;
    public String lastName;
    private final String SSN;
    private ArrayList<LoanAccount> loanAccounts;
    
    public Customer(String firstName, String lastName, String SSN) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.SSN = SSN;
    loanAccounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSSN() {
        return SSN;
    }
    
    public ArrayList<LoanAccount> getLoanAccounts() {
	return loanAccounts;
    }
    
    public void addLoanAccount(LoanAccount account) {
        loanAccounts.add(account);
    }
    
    public String printMonthlyReport()  {
        String loanAccountFormat = String.format("Account Report for Customer: %s %s with SSN %s%n%n",getFirstName(),getLastName(),getSSN());
        for(LoanAccount loanAccount: loanAccounts){
            loanAccountFormat += loanAccount.toString();
        }
        return loanAccountFormat;
    }
}

    
