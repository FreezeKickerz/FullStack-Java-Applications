/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;



/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{

    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    private JPanel topPanel = new JPanel();
    private JPanel lineOne = new JPanel();
    private JPanel lineTwo = new JPanel();
    private JPanel drawPanel = new DrawPanel();
    private JPanel locationPanel = new JPanel();
    
    // create the widgets for the firstLine Panel.
    private JLabel shapeLabel = new JLabel("Shape:");
    private JComboBox shapeSelection = new JComboBox<>(new String[]{"Line","Oval","Rectangle"});
    private JButton firstColor = new JButton("1st Color...");
    private JButton secondColor = new JButton("2nd Color...");
    private JButton undoButton = new JButton("Undo");
    private JButton clearButton = new JButton("Clear");
    
    //create the widgets for the secondLine Panel.
    private JLabel options = new JLabel("Options:");
    private JCheckBox filledBox = new JCheckBox("Filled");
    private JCheckBox gradientBox = new JCheckBox("Use Gradient");
    private JCheckBox dashedBox = new JCheckBox("Dashed");
    private JLabel lineWidth = new JLabel("Line Width:");
    public JSpinner widthSpinner = new JSpinner();
    private JLabel dashLengthLabel = new JLabel("Dash Length:");
    public JSpinner lengthSpinner = new JSpinner();
    
    // Variables for drawPanel.
    private Color color_1 = Color.BLACK;
    private Color color_2 = Color.BLACK;
    private MyShapes selectedShape;
    private ArrayList<MyShapes> shapes = new ArrayList<>();
    // add status label
    private JLabel mousePosition = new JLabel("(0, 0)");
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
        super("Java 2D Drawings");
        // add widgets to panels
        topPanel.setLayout(new BorderLayout());
        lineOne.setLayout(new FlowLayout());
        lineTwo.setLayout(new FlowLayout());
        drawPanel.setLayout(new BorderLayout());
        locationPanel.setLayout(new BorderLayout());
        // firstLine widgets
        lineOne.setBackground(Color.CYAN);
        lineOne.add(shapeLabel);
        lineOne.add(shapeSelection);
        lineOne.add(firstColor);
        lineOne.add(secondColor);
        lineOne.add(undoButton);
        lineOne.add(clearButton);
        // secondLine widgets
        lineTwo.setBackground(Color.CYAN);
        lineTwo.add(options);
        lineTwo.add(filledBox);
        lineTwo.add(gradientBox);
        lineTwo.add(dashedBox);
        lineTwo.add(lineWidth);
        lineTwo.add(widthSpinner);
        lineTwo.add(dashLengthLabel);
        lineTwo.add(lengthSpinner);
        
        // add top panel of two panels
        topPanel.add(lineOne, "North");
        topPanel.add(lineTwo, "South");
        // add topPanel to North, drawPanel to Center, and statusLabel to South
        locationPanel.setBackground(Color.LIGHT_GRAY);
        locationPanel.add(mousePosition, BorderLayout.WEST);
        super.add(topPanel,"North");
        super.add(drawPanel,"Center");
        super.add(locationPanel, "South");
        //add listeners and event handlers
        ButtonHandler handler = new ButtonHandler();
        firstColor.addActionListener(handler);
        secondColor.addActionListener(handler);
        undoButton.addActionListener(handler);
        clearButton.addActionListener(handler);
    }

    // Create event handlers, if needed
    
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == firstColor){
                color_1 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Select Color 1...", color_1);
            }
            if(event.getSource() == secondColor){
                color_2 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Select Color 2...", color_2);
            }
            
            if(event.getSource() == clearButton){
                shapes.clear();
                repaint();
            }
            
            if(event.getSource() == undoButton){
                if (!shapes.isEmpty()){
                    shapes.remove(shapes.size() -1);
                }
                repaint();
            }
        }
       
    }
    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {

        public DrawPanel()
        {
            setBackground(Color.WHITE);
            MouseHandler mouseHandler = new MouseHandler();
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //loop through and draw each shape in the shapes arraylist
            for(MyShapes x: shapes){
            x.draw(g2d);
            }
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {
            Paint paint;
            Stroke stroke;

            public void mousePressed(MouseEvent event){
                
                float[] dashes = {(Integer)lengthSpinner.getValue()};
                paint = color_1;
                stroke = new BasicStroke((Integer)widthSpinner.getValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                
                if(gradientBox.isSelected()){
                    paint = new GradientPaint(0, 0, color_1, 50, 50, color_2, true);
                }
                if(dashedBox.isSelected() & (Integer)lengthSpinner.getValue() !=0){
                    stroke = new BasicStroke((Integer)widthSpinner.getValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0);
                }
                
                if(shapeSelection.getSelectedItem() == "Line"){
                    selectedShape = new MyLine(event.getPoint(),event.getPoint(),paint,stroke);
                }
                if(shapeSelection.getSelectedItem() == "Rectangle"){
                    selectedShape = new MyRectangle(event.getPoint(), event.getPoint(), paint, stroke, filledBox.isSelected());
                }
                if(shapeSelection.getSelectedItem() == "Oval"){
                    selectedShape = new MyOval(event.getPoint(), event.getPoint(), paint, stroke, filledBox.isSelected());
                }
                shapes.add(selectedShape);
                mousePosition.setText(String.format("(%d, %d)",event.getX(),event.getY()));
                repaint();



            }

            public void mouseReleased(MouseEvent event){
                shapes.get(shapes.size()-1).setEndPoint(event.getPoint());
                mousePosition.setText(String.format("(%d, %d)",event.getX(),event.getY()));
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent event){
                shapes.get(shapes.size()-1).setEndPoint(event.getPoint());
                mousePosition.setText(String.format("(%d, %d)",event.getX(),event.getY()));
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent event){
                mousePosition.setText(String.format("(%d, %d)",event.getX(),event.getY()));
                repaint();
            }
        }

    }
}
