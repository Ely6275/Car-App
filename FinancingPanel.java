import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
public class FinancingPanel extends JPanel{
    //Initialize all components that will be displayed on the panel
    private JLabel promptLabel;
    
    private ButtonGroup radios;
    private JRadioButton yesButton, noButton;

    private JPanel promptPanel, buttonPanel;

    //Rate that the car is financed at
    private static final double FINANCING_RATE = 1.07;

    //Constructor that runs at start time, sets up the panel and formatting
    public FinancingPanel(){

        setLayout(new BorderLayout(0,0));
        setBackground(Color.MAGENTA);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Financing Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        buildFinancingPanel();
        
    }

    //Constructor that runs when the user is running a save file, used to copy the save object for financing
    public FinancingPanel(FinancingPanel financing){

        setLayout(new BorderLayout(0,0));
        setBackground(Color.MAGENTA);
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Financing Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        buildFinancingPanel(financing);
        
    }

    //Adds and formates all financingPanel components
    private void buildFinancingPanel(){

        //Create prompt and buttons for "yes" and "no"
        promptLabel = new JLabel("Would you like to finance this car at %7 interest?");
        promptLabel.setForeground(Color.BLACK);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        buttonPanel.setBackground(Color.MAGENTA);

        //Intialize buttons and add to the radio group
        radios = new ButtonGroup();

        yesButton = new JRadioButton("Yes", true);
        yesButton.setBackground(Color.magenta);
        yesButton.setForeground(Color.BLACK);

        noButton = new JRadioButton("No");
        noButton.setBackground(Color.magenta);
        noButton.setForeground(Color.BLACK);

        radios.add(yesButton);
        radios.add(noButton);
        
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        //Add sub panels to the FinancingPanel
        add(promptLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    //method that runs when the user is running a save file, makes a copy of financing save object passed into parameter
    private void buildFinancingPanel(FinancingPanel financing){

        promptLabel = new JLabel("Would you like to finance this car at %7 interest?");
        promptLabel.setForeground(Color.BLACK);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        buttonPanel.setBackground(Color.MAGENTA);

        //Copy save object buttons to the current class to save any data
        this.radios = financing.radios;

        this.yesButton = financing.yesButton;
        yesButton.setBackground(Color.magenta);
        yesButton.setForeground(Color.BLACK);

        this.noButton = financing.noButton;
        noButton.setBackground(Color.magenta);
        noButton.setForeground(Color.BLACK);

        radios.add(yesButton);
        radios.add(noButton);
        
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(promptLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    //Returns the rate for financing
    public double getRate(){
        //If the user opted in for financing, return the financing rate
        if(yesButton.isSelected()){
            return FINANCING_RATE;
        }
        //If the user did not opt in for financing, return 1
        else return 1;
    }
}
