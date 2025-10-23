/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pizzaservings;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PizzaServings extends JFrame{
    
    public String pizzaSize;
    public JTextField input = new JTextField(4);
    public JLabel result = new JLabel("", SwingConstants.CENTER);
    
    public PizzaServings(){
        //Naming the Window
        super("Pizza Servings Calculator");
        
//Declaring the Layout Type
        setLayout(new GridLayout(4,1));
        
        //Title
        JLabel title = new JLabel("Pizza Servings Calculator", SwingConstants.CENTER);
        title.setForeground(Color.red);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        add(title);
        
        //Pizza Size
        JPanel jpanel = new JPanel();
     
        JLabel enterPizzaSize = new JLabel("Enter the size of the pizza in inches:", SwingConstants.CENTER);
        add(jpanel);
        jpanel.add(enterPizzaSize);
        jpanel.add(input);
        
        //Adding Calculate Servings Button
        JButton calculateServingsButton = new JButton("Calculate Servings");
        add(calculateServingsButton);
        
        //Button Handling and Calculation
        ButtonHandler handler = new ButtonHandler();
        calculateServingsButton.addActionListener(handler);
        
        //Result
        add(result);
    }
    
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
        pizzaSize = input.getText();
        double servings = Math.pow((Double.parseDouble(pizzaSize)/8),2);
        result.setText("A " + pizzaSize + " inch pizza will serve " + String.format("%.2f",servings) + " people.");
        }
    }
    
    public static void main(String[] args) {
        PizzaServings pizzaFrame = new PizzaServings();
        pizzaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pizzaFrame.setSize(350,300);
        pizzaFrame.setVisible(true);
    }
    
}
