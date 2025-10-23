/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loan.account.hierarchy;

/**
 *
 * @author Freez
 */
public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String zipcode;
    
    public Address(String street, String city, String state, String zipcode){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
    
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getZipcode(){
        return zipcode;
    }
    
    @Override
    public String toString(){
        return String.format("Property Address:%n%s%n%s, %s %s%n",street,city,state,zipcode);
    }
    
}
