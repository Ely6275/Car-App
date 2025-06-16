import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class TradeInPanel extends JPanel implements ActionListener{
    
    //Class variabels that will store components on the screen
    private JLabel titleLabel, promptLabel, selectCarBodyLabel, inCashLabel, yearsOwnedLabel;
    
    //Buttons and radio groups
    private ButtonGroup radios1;
    public JRadioButton yesButton1;

    public JRadioButton noButton1;

    private ButtonGroup radios2;
    public JRadioButton yesButton2, noButton2;

    //Combo box to select the car bodies
    private JComboBox carBodyComboBox;
    private String[] carBodies = {"Sedan", "SUV", "Convertible"};

    private JTextField yearsOwnedText;

    //nested panels that will be added to the additionalInfoPanel
    private JPanel initialPanel, promptPanel3, promptPanel1, promptPanel2, buttonPanel1, buttonPanel2, additionalInfoPanel;

    private JPanel carBodyPanel, inCashPanel, yearsPanel;

    //Constant to store the discount amount
    private static final int IN_CASH_DISCOUNT = 750;

    //contructor that runs on startup, which will build the panel and apply formatting
    public TradeInPanel(){

        setLayout(new GridLayout(3,1, 0, 0));
        setBackground(Color.BLUE);
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Package Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);
        
        buildTradeInPanel();
        
    }

    //Contructor that will run when the user is running a save file, copying the components of the save file to the current TradeInPanel
    public TradeInPanel(TradeInPanel tradeInSave){

        setLayout(new GridLayout(3,1, 0, 0));
        setBackground(Color.BLUE);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Package Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        buildTradeInPanel(tradeInSave);

    }

    //initializes all panels and components, formats coloring and position, and adds to the main panel
    private void buildTradeInPanel(){
        //Panel that will prompt the user for trading in their car
        promptPanel1 = new JPanel();
        promptPanel1.setLayout(new BorderLayout());
        promptPanel1.setBackground(Color.BLUE);

        promptLabel = new JLabel("Would you like to trade in your current car?");
        promptLabel.setForeground(Color.cyan);
        
        //panel that holds buttons
        buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        buttonPanel1.setBackground(Color.BLUE);

        radios1 = new ButtonGroup();

        yesButton1 = new JRadioButton("Yes");
        yesButton1.addActionListener(this);
        yesButton1.setBackground(Color.BLUE);
        yesButton1.setForeground(Color.BLACK);

        noButton1 = new JRadioButton("No", true);
        noButton1.addActionListener(this);
        noButton1.setBackground(Color.BLUE);
        noButton1.setForeground(Color.BLACK);

        radios1.add(yesButton1);
        radios1.add(noButton1);

        buttonPanel1.add(yesButton1);
        buttonPanel1.add(noButton1);

        promptPanel1.add(promptLabel, BorderLayout.NORTH);
        promptPanel1.add(buttonPanel1, BorderLayout.SOUTH);

        //Panel that holds the prompt panel
        initialPanel = new JPanel();
        initialPanel.setLayout(new BorderLayout());
        initialPanel.add(promptPanel1, BorderLayout.NORTH);

        //Panel that holds additional info if the user enters yes
        promptPanel3 = new JPanel();
        promptPanel3.setLayout(new BorderLayout());

        //Panel that will hold additonal info and be appended
        additionalInfoPanel = new JPanel();
        additionalInfoPanel.setLayout(new BorderLayout());
        additionalInfoPanel.setBackground(Color.BLUE);

        //Panel to hold car body info
        carBodyPanel = new JPanel();
        carBodyPanel.setLayout(new BorderLayout());
        carBodyPanel.setBackground(Color.BLUE);

        selectCarBodyLabel = new JLabel("Select your Car Body");
        selectCarBodyLabel.setForeground(Color.BLACK);
        selectCarBodyLabel.setBackground(Color.BLUE);

        carBodyComboBox = new JComboBox(carBodies);

        carBodyPanel.add(selectCarBodyLabel, BorderLayout.NORTH);
        carBodyPanel.add(carBodyComboBox, BorderLayout.SOUTH);

        //Panel that prompts the user for how many years they owned a car
        yearsPanel = new JPanel();
        yearsPanel.setLayout(new BorderLayout());
        yearsPanel.setBackground(Color.BLUE);

        yearsOwnedLabel = new JLabel("How many years have you owned the car?");
        yearsOwnedLabel.setForeground(Color.BLACK);
        yearsOwnedText = new JTextField(10);

        yearsPanel.add(yearsOwnedLabel, BorderLayout.NORTH);
        yearsPanel.add(yearsOwnedText, BorderLayout.SOUTH);

        additionalInfoPanel.add(carBodyPanel, BorderLayout.NORTH);
        additionalInfoPanel.add(yearsPanel, BorderLayout.SOUTH);

        promptPanel3.add(additionalInfoPanel, BorderLayout.NORTH);

        promptPanel2 = new JPanel();
        promptPanel2.setLayout(new BorderLayout());

        //Panel that asks if the user is paying in cash
        inCashPanel = new JPanel();
        inCashPanel.setLayout(new BorderLayout());
        inCashPanel.setBackground(Color.BLUE);

        inCashLabel = new JLabel("Are you paying in cash for a $750 discount?");
        inCashLabel.setForeground(Color.CYAN);
        

        buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        buttonPanel2.setBackground(Color.BLUE);

        radios2 = new ButtonGroup();

        yesButton2 = new JRadioButton("Yes");
        yesButton2.addActionListener(this);
        yesButton2.setBackground(Color.BLUE);
        yesButton2.setForeground(Color.BLACK);

        noButton2 = new JRadioButton("No", true);
        noButton2.addActionListener(this);
        noButton2.setBackground(Color.BLUE);
        noButton2.setForeground(Color.BLACK);

        radios2.add(yesButton2);
        radios2.add(noButton2);

        buttonPanel2.add(yesButton2);
        buttonPanel2.add(noButton2);

        inCashPanel.add(inCashLabel, BorderLayout.NORTH);
        inCashPanel.add(buttonPanel2, BorderLayout.SOUTH);

        promptPanel2.add(inCashPanel, BorderLayout.NORTH);

        initialPanel.setBackground(Color.BLUE);
        promptPanel2.setBackground(Color.BLUE);
        promptPanel3.setBackground(Color.BLUE);

        add(initialPanel);
        add(promptPanel2);
        add(promptPanel3);

        promptPanel3.setVisible(false);
    }

    //builds the trade in panel based on the save file the user has
    //Same logic as the previous constructor, except it just copies info from save file
    private void buildTradeInPanel(TradeInPanel tradeInSave){
        setLayout(new GridLayout(3,1, 0, 0));
        setBorder(BorderFactory.createTitledBorder("Trade-Ins"));

        promptPanel1 = new JPanel();
        promptPanel1.setLayout(new BorderLayout());
        promptPanel1.setBackground(Color.BLUE);

        promptLabel = new JLabel("Would you like to trade in your current car?");
        promptLabel.setForeground(Color.cyan);
        
        buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        buttonPanel1.setBackground(Color.BLUE);

        radios1 = new ButtonGroup();

        this.yesButton1 = tradeInSave.yesButton1;
        yesButton1.addActionListener(this);
        yesButton1.setBackground(Color.BLUE);
        yesButton1.setForeground(Color.BLACK);

        this.noButton1 = tradeInSave.noButton1;
        noButton1.addActionListener(this);
        noButton1.setBackground(Color.BLUE);
        noButton1.setForeground(Color.BLACK);

        this.radios1.add(yesButton1);
        this.radios1.add(noButton1);

        buttonPanel1.add(yesButton1);
        buttonPanel1.add(noButton1);

        promptPanel1.add(promptLabel, BorderLayout.NORTH);
        promptPanel1.add(buttonPanel1, BorderLayout.SOUTH);   

        initialPanel = new JPanel();
        initialPanel.setLayout(new BorderLayout());
        initialPanel.add(promptPanel1, BorderLayout.NORTH);

        promptPanel3 = new JPanel();
        promptPanel3.setLayout(new BorderLayout());

        additionalInfoPanel = new JPanel();
        additionalInfoPanel.setLayout(new BorderLayout());
        additionalInfoPanel.setBackground(Color.BLUE);

        carBodyPanel = new JPanel();
        carBodyPanel.setLayout(new BorderLayout());
        carBodyPanel.setBackground(Color.BLUE);

        selectCarBodyLabel = new JLabel("Select your Car Body");
        selectCarBodyLabel.setForeground(Color.BLACK);
        

        this.carBodyComboBox = tradeInSave.carBodyComboBox;

        carBodyPanel.add(selectCarBodyLabel, BorderLayout.NORTH);
        carBodyPanel.add(carBodyComboBox, BorderLayout.SOUTH);
        
        yearsPanel = new JPanel();
        yearsPanel.setLayout(new BorderLayout());
        yearsPanel.setBackground(Color.BLUE);

        yearsOwnedLabel = new JLabel("How many years have you owned the car?");
        yearsOwnedLabel.setForeground(Color.BLACK);
        this.yearsOwnedText = tradeInSave.yearsOwnedText;

        yearsPanel.add(yearsOwnedLabel, BorderLayout.NORTH);
        yearsPanel.add(yearsOwnedText, BorderLayout.SOUTH);

        additionalInfoPanel.add(carBodyPanel, BorderLayout.NORTH);
        additionalInfoPanel.add(yearsPanel, BorderLayout.SOUTH);

        promptPanel3.add(additionalInfoPanel, BorderLayout.NORTH);

        promptPanel2 = new JPanel();
        promptPanel2.setLayout(new BorderLayout());

        inCashPanel = new JPanel();
        inCashPanel.setLayout(new BorderLayout());
        inCashPanel.setBackground(Color.BLUE);


        inCashLabel = new JLabel("Are you paying in cash for a $750 discount?");
        inCashLabel.setForeground(Color.CYAN);

        buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        buttonPanel2.setBackground(Color.BLUE);


        radios2 = new ButtonGroup();

        this.yesButton2 = tradeInSave.yesButton2;
        yesButton2.addActionListener(this);
        yesButton2.setBackground(Color.BLUE);
        yesButton2.setForeground(Color.BLACK);

        this.noButton2 = tradeInSave.noButton2;
        noButton2.addActionListener(this);
        noButton2.setBackground(Color.BLUE);
        noButton2.setForeground(Color.BLACK);

        radios2.add(yesButton2);
        radios2.add(noButton2);

        buttonPanel2.add(yesButton2);
        buttonPanel2.add(noButton2);

        inCashPanel.add(inCashLabel, BorderLayout.NORTH);
        inCashPanel.add(buttonPanel2, BorderLayout.SOUTH);

        promptPanel2.add(inCashPanel, BorderLayout.NORTH);

        initialPanel.setBackground(Color.BLUE);
        promptPanel2.setBackground(Color.BLUE);
        promptPanel3.setBackground(Color.BLUE);

        add(initialPanel);
        add(promptPanel2);
        add(promptPanel3);

        if(noButton1.isSelected() && !yesButton1.isSelected()) promptPanel3.setVisible(false);
        if(!noButton1.isSelected() && yesButton1.isSelected()) promptPanel3.setVisible(true);
    }


    //Method that returns that trade in value of a car
    public double getTradeInValue(double initalPrice) throws NegativeInputException, NullInputException{
        //If the user wants to trade in their current car
        if(yesButton1.isSelected()){
            //Make sure input is not blank
            if( yearsOwnedText.getText().equals("") ) throw new NullInputException();
            else{
                //Store the users answer as an integer
                int years = Integer.parseInt(yearsOwnedText.getText());
                //make sure input is not negative
                if(years < 0) throw new NegativeInputException(years);
            }
            //Calculate trade value based in car depreciation rate
            double tradeInValue = calculateDepreciation(""+carBodyComboBox.getSelectedItem(), Integer.parseInt(yearsOwnedText.getText()), initalPrice);
            return tradeInValue;
        }
        else return 0;
            
    }
    //Return the discount if the user opted in for it
    public double getDiscount(){
        if(yesButton2.isSelected()) return IN_CASH_DISCOUNT;
        else return 0;
    }
    //return the cost from this panel
    public double getCost(double initalPrice) throws NegativeInputException, NullInputException{
        return getTradeInValue(initalPrice) - getDiscount();
    }


    //Calculates the depreciation rate of a car based on its body and years owned
    private static double calculateDepreciation(String carBody, int years, double initalPrice){
        //Declare rate and value
        double DEPRECIATION_RATE;
        double depreciationValue;
        //Set different rates for difference car bodies, and apply exponential decay function to find depreciation value
        switch(carBody){
            case "Sedan":
                DEPRECIATION_RATE = 0.48;
                depreciationValue = initalPrice * Math.pow(1-DEPRECIATION_RATE, (double)years / 2);
                return depreciationValue;
            case "SUV":
                DEPRECIATION_RATE = 0.62;
                depreciationValue = initalPrice * Math.pow(1-DEPRECIATION_RATE, (double)years / 2);
                return depreciationValue;
            case "Convertible":
                DEPRECIATION_RATE = 0.7;
                depreciationValue = initalPrice * Math.pow(1-DEPRECIATION_RATE, (double)years / 2);
                return depreciationValue;
            default: return -1;
        }

        
        
    }
    //Displays additional info panel if the user selected yes
    @Override
    public void actionPerformed(ActionEvent e) {
        if(promptPanel3.isVisible() && noButton1.isSelected()) promptPanel3.setVisible(false);
        if( !(promptPanel3.isVisible()) && yesButton1.isSelected()) promptPanel3.setVisible(true);


    }
        
        
    
}
