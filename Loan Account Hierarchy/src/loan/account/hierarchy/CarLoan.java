/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loan.account.hierarchy;

/**
 *
 * @author Freez
 */
public class CarLoan extends LoanAccount{
    
    private final String vehicleVin;
    
    public CarLoan(double principle, double annualInterestRate, int months, String vehicleVin){
        super(principle,annualInterestRate,months);
        this.vehicleVin = vehicleVin;
    }
    
    @Override
    public String toString(){
        return String.format("%s%nVehicle Vin: %s%n",super.toString(),vehicleVin);
    }
}
