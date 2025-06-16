import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;;

public class LicensePlatePanel extends JPanel implements ActionListener{

    // Instance variables for components of the LicensePlatePanel
    private JLabel titleLabel;
    public JButton[] letterButtons, numberButtons;
    private JLabel[] letterLabels, numberLabels;
    private JLabel[][] allcomponents;
    
    // Declare a combobox that will hold all 50 states
    private JComboBox stateComboBox;
    private DefaultListCellRenderer listRenderer;
    // Store 50 states into an array
    private static final String[] STATES = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", 
                                            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
                                            "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", 
                                            "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", 
                                            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
    
    // Store all letters and numbers into arrays, which will be used to display the license plate
    private static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final int[] NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

    // Store the current index of each label
    private int currIndeces[] = {0, 0, 0, 0, 0, 0};

    // Store the concatenated license plate 
    private static String licenseID;

    // Initialize subpanels
    private JPanel statePanel, letterPanel, numberPanel, parentPanel;

    // Store the price of a license plate
    private static final double LICENSE_PLATE_PRICE = 50;
    // Set premium membership to false by default
    public static boolean isPremiumMember = false;

    // Constructor
    public LicensePlatePanel(){

        // Set layout and background color
        setLayout(new BorderLayout());
        setBackground(Color.MAGENTA);
        
        // Create titled border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("License Plate Builder");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        // Initialize statePanel
        statePanel = new JPanel();
        statePanel.setLayout(new BorderLayout(0,0));

        // Initialize titleLabel
        titleLabel = new JLabel("Select Your State");
        titleLabel.setForeground(Color.BLACK);

        // Initialize stateComboBox
        stateComboBox = new JComboBox<>(STATES);
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        stateComboBox.setRenderer(listRenderer);

        // Add components to statePanel
        statePanel.add(titleLabel, BorderLayout.NORTH);
        statePanel.add(stateComboBox, BorderLayout.SOUTH);
        
        // Initialize letterPanel and numberPanel
        letterPanel = new JPanel();
        letterPanel.setLayout(new GridLayout(1,3, 0, 0));

        numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(1,4, 0, 0));
        
        // Assemble components
        assembleComponents();

        // Add letter and number labels to their respective panels
        letterPanel.add(letterLabels[0]);
        letterPanel.add(letterLabels[1]);
        letterPanel.add(letterLabels[2]);

        numberPanel.add(numberLabels[0]);
        numberPanel.add(numberLabels[1]);
        numberPanel.add(numberLabels[2]);

        // Initialize parentPanel
        parentPanel = new JPanel();
        parentPanel.setLayout(new GridLayout(1,2));
        parentPanel.add(letterPanel);
        parentPanel.add(numberPanel);

        // Set backgrounds
        statePanel.setBackground(Color.MAGENTA);
        parentPanel.setBackground(Color.MAGENTA);

        // Add components to the main panel
        add(statePanel, BorderLayout.NORTH);
        add(parentPanel);
        
    }

    // Second constructor with a parameter for copying state from another LicensePlatePanel
    public LicensePlatePanel(LicensePlatePanel licenseSave){

        // Same initialization as the default constructor
        setLayout(new BorderLayout());
        setBackground(Color.MAGENTA);
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("License Plate Builder");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        statePanel = new JPanel();
        statePanel.setLayout(new BorderLayout(0,0));

        titleLabel = new JLabel("Select Your State");
        titleLabel.setForeground(Color.BLACK);

        stateComboBox = new JComboBox<>(STATES);
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        stateComboBox.setRenderer(listRenderer);

        statePanel.add(titleLabel, BorderLayout.NORTH);
        statePanel.add(stateComboBox, BorderLayout.SOUTH);
        
        letterPanel = new JPanel();
        letterPanel.setLayout(new GridLayout(1,3, 0, 0));

        numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(1,4, 0, 0));
        
        // Assemble components based on existing LicensePlatePanel
        assembleComponents(licenseSave);

        // Add letter and number labels to their respective panels
        letterPanel.add(letterLabels[0]);
        letterPanel.add(letterLabels[1]);
        letterPanel.add(letterLabels[2]);

        numberPanel.add(numberLabels[0]);
        numberPanel.add(numberLabels[1]);
        numberPanel.add(numberLabels[2]);

        // Initialize parentPanel
        parentPanel = new JPanel();
        parentPanel.setLayout(new GridLayout(1,2));
        parentPanel.add(letterPanel);
        parentPanel.add(numberPanel);

        // Set backgrounds
        statePanel.setBackground(Color.MAGENTA);
        parentPanel.setBackground(Color.MAGENTA);

        // Add components to the main panel
        add(statePanel, BorderLayout.NORTH);
        add(parentPanel);
    }

    // Assemble GUI components
    private void assembleComponents(){
        
        // Initialize letter buttons and add action listeners
        letterButtons = new JButton[6];
        for(int i = 0; i < letterButtons.length; i++){
            if(i < (letterButtons.length)/2) letterButtons[i] = new JButton("▲");
            else letterButtons[i] = new JButton("▼");

            letterButtons[i].addActionListener(this);
        }

        // Initialize letter labels
        letterLabels = new JLabel[3];
        for(int i = 0; i < letterLabels.length; i++){
            letterLabels[i] = new JLabel(ALPHABET[0], SwingConstants.CENTER);
            letterLabels[i].setLayout(new BorderLayout(10, 0));
            letterLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            letterLabels[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        // Initialize number buttons and add action listeners
        numberButtons = new JButton[6];
        for(int i = 0; i < numberButtons.length; i++){
            if(i < (numberButtons.length)/2) numberButtons[i] = new JButton("▲");
            else numberButtons[i] = new JButton("▼");

            numberButtons[i].addActionListener(this);
        }

        // Initialize number labels
        numberLabels = new JLabel[3];
        for(int i = 0; i < numberLabels.length; i++){
            numberLabels[i] = new JLabel("" + NUMBERS[0], SwingConstants.CENTER);
            numberLabels[i].setLayout(new BorderLayout());
            numberLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            numberLabels[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        // Assemble letter and number labels
        assembleLetterLabels();
        assembleNumberLabels();
    }

    // Assemble components based on existing LicensePlatePanel
    private void assembleComponents(LicensePlatePanel licenseSave){
        
        // Same as the default constructor, but copying components from the existing panel
        letterButtons = new JButton[6];
        for(int i = 0; i < letterButtons.length; i++){
            this.letterButtons[i] = licenseSave.letterButtons[i];
            this.letterButtons[i].addActionListener(this);
        }

        letterLabels = new JLabel[3];
        for(int i = 0; i < letterLabels.length; i++){
            this.letterLabels[i] = licenseSave.letterLabels[i];
        }

        numberButtons = new JButton[6];
        for(int i = 0; i < numberButtons.length; i++){
            this.numberButtons[i] = licenseSave.numberButtons[i];
            numberButtons[i].addActionListener(this);
        }

        numberLabels = new JLabel[3];
        for(int i = 0; i < numberLabels.length; i++){
            this.numberLabels[i] = licenseSave.numberLabels[i];
        }

        // Assemble letter and number labels
        assembleLetterLabels();
        assembleNumberLabels();
    }

    // Assemble letter labels with buttons
    private void assembleLetterLabels(){

        letterLabels[0].add(letterButtons[0], BorderLayout.NORTH);
        letterLabels[0].add(letterButtons[3], BorderLayout.SOUTH);

        letterLabels[1].add(letterButtons[1], BorderLayout.NORTH);
        letterLabels[1].add(letterButtons[4], BorderLayout.SOUTH);

        letterLabels[2].add(letterButtons[2], BorderLayout.NORTH);
        letterLabels[2].add(letterButtons[5], BorderLayout.SOUTH);
    }

    // Assemble number labels with buttons
    private void assembleNumberLabels(){

        numberLabels[0].add(numberButtons[0], BorderLayout.NORTH);
        numberLabels[0].add(numberButtons[3], BorderLayout.SOUTH);

        numberLabels[1].add(numberButtons[1], BorderLayout.NORTH);
        numberLabels[1].add(numberButtons[4], BorderLayout.SOUTH);

        numberLabels[2].add(numberButtons[2], BorderLayout.NORTH);
        numberLabels[2].add(numberButtons[5], BorderLayout.SOUTH);
    }

    // Generate the license plate ID
    private void generateLicensePlate(){
        licenseID = "";
        
        for(int i = 0; i < letterLabels.length; i++){
            licenseID += letterLabels[i].getText();
        }

        for(int i = 0; i < numberLabels.length; i++){
            licenseID += numberLabels[i].getText();
        }
    }

    // Get the generated license plate ID
    public String getLicenseID(){
        generateLicensePlate();
        return licenseID;
    }

    // Action performed for character one
    private void characterOneAction(ActionEvent e){
        if(e.getSource().equals(letterButtons[0])){
            if(currIndeces[0] == ALPHABET.length-1) currIndeces[0] = 0;
            else currIndeces[0]++;

            letterLabels[0].setText(ALPHABET[currIndeces[0]]);
        }

        if(e.getSource().equals(letterButtons[3])){
            if(currIndeces[0] == 0) currIndeces[0] = ALPHABET.length-1;
            else currIndeces[0]--;
            
            letterLabels[0].setText(ALPHABET[currIndeces[0]]);
        }
    }

    // Action performed for character two
    private void characterTwoAction(ActionEvent e){
        if(e.getSource().equals(letterButtons[1])){
            if(currIndeces[1] == ALPHABET.length-1) currIndeces[1] = 0;
            else currIndeces[1]++;

            letterLabels[1].setText(ALPHABET[currIndeces[1]]);
        }

        if(e.getSource().equals(letterButtons[4])){
            if(currIndeces[1] == 0) currIndeces[1] = ALPHABET.length-1;
            else currIndeces[1]--;
            
            letterLabels[1].setText(ALPHABET[currIndeces[1]]);
        }
    }

    // Action performed for character three
    private void characterThreeAction(ActionEvent e){
        if(e.getSource().equals(letterButtons[2])){
            if(currIndeces[2] == ALPHABET.length-1) currIndeces[2] = 0;
            else currIndeces[2]++;

            letterLabels[2].setText(ALPHABET[currIndeces[2]]);
        }

        if(e.getSource().equals(letterButtons[5])){
            if(currIndeces[2] == 0) currIndeces[2] = ALPHABET.length-1;
            else currIndeces[2]--;
            
            letterLabels[2].setText(ALPHABET[currIndeces[2]]);
        }
    }

    // Action performed for character four
    private void characterFourAction(ActionEvent e){
        if(e.getSource().equals(numberButtons[0])){
            if(currIndeces[3] == NUMBERS.length-1) currIndeces[3] = 0;
            else currIndeces[3]++;

            numberLabels[0].setText(""+NUMBERS[currIndeces[3]]);
        }

        if(e.getSource().equals(numberButtons[3])){
            if(currIndeces[3] == 0) currIndeces[3] = NUMBERS.length-1;
            else currIndeces[3]--;
            
            numberLabels[0].setText(""+NUMBERS[currIndeces[3]]);
        }
    }

    // Action performed for character five
    private void characterFiveAction(ActionEvent e){
        if(e.getSource().equals(numberButtons[1])){
            if(currIndeces[4] == NUMBERS.length-1) currIndeces[4] = 0;
            else currIndeces[4]++;

            numberLabels[1].setText(""+NUMBERS[currIndeces[4]]);
        }

        if(e.getSource().equals(numberButtons[4])){
            if(currIndeces[4] == 0) currIndeces[4] = NUMBERS.length-1;
            else currIndeces[4]--;
            
            numberLabels[1].setText(""+NUMBERS[currIndeces[4]]);
        }
    }

    // Action performed for character six
    private void characterSixAction(ActionEvent e){
        if(e.getSource().equals(numberButtons[2])){
            if(currIndeces[5] == NUMBERS.length-1) currIndeces[5] = 0;
            else currIndeces[5]++;

            numberLabels[2].setText(""+NUMBERS[currIndeces[5]]);
        }

        if(e.getSource().equals(numberButtons[5])){
            if(currIndeces[5] == 0) currIndeces[5] = NUMBERS.length-1;
            else currIndeces[5]--;
            
            numberLabels[2].setText(""+NUMBERS[currIndeces[5]]);
        }
    }

    // Get the cost of the license plate
    public static double getCost(){
        if(isPremiumMember) return 0;
        else return 50;
    }
    
    // Action listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        characterOneAction(e);
        characterTwoAction(e);
        characterThreeAction(e);
        characterFourAction(e);
        characterFiveAction(e);
        characterSixAction(e);
    }
}