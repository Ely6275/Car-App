/*
 * Ely Herman
 * 4/7/24
 * REFLECTION:
 *  - This project was the longest and most elaborate thing I have ever coded, and I am extremely proud of everything I learned and implemented in this project
 *  - Cool features include
 *    - Save file that detects existing users and loads saved data (Try it out! create a save file by exiting the window and reentering your name)
 *    - Model Car that changes based on the modifications that the user selects
 *    - License Plate creator
 *    - Use of threading to run the main method
 */
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.*;


import java.awt.*;
import java.io.*;

public class Window extends JFrame implements ActionListener,  WindowListener, Serializable{

    //Declare transient panels to be serialized

    private transient GreetingPanel greeting;

    private transient JPanel westernPanel, centerPanel, easternPanel;

    //Western Panels
    private transient ModelSelectionPanel modelSelection;
    private transient TradeInPanel tradeIn;

    //Center Panels
    private transient FinancingPanel financing;
    private transient LicensePlatePanel licensePlate;
    private transient ModificationsPanel modifications;

    //Eastern Panels
    private transient CarModelPicture model;

    //Save account panel
    //private static SavePanel saves;

    private JButton calcButton;

    //window size
    private static final int WIDTH = 1600, HEIGHT = 675;

    //Stores length of account number
    private static final int ACCOUNT_NUMBER_LENGTH = 10;

    //prices for sales tax and tags+titles
    private static final double SALES_TAX_RATE = 1.06, TITLES_TAGS_PRICE = 325;

    //save file path
    private static final String FILE_PATH = "saveFiles\\accounts.dat";

    //initialize user info to blank
    private static String name = "", address = "", phoneNumber = "", account = "";

    //contructor that runs all panels
    public Window() throws IOException, NegativeInputException, PhoneNumberException, NullInputException, ClassNotFoundException{
        setTitle("Vehicle Cost Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        addWindowListener(this);

        //prompt user for their information, then build all panels
        promptUser();
        account = generateAccountNumber();
        greeting = new GreetingPanel(account, address, phoneNumber, name);
        add(greeting, BorderLayout.NORTH);

        buildWesternPanel();
        add(westernPanel, BorderLayout.WEST);

        buildCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

        buildEasternPanel();
        add(easternPanel, BorderLayout.EAST);

        calcButton = new JButton("CALCULATE");
        calcButton.addActionListener(this);
        add(calcButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    //Contructor to run when the user is running a save file; runs all savefile constructors for each panel
    public Window(GreetingPanel greetingSave, ModelSelectionPanel modelSelectionSave, PackagePanel packageSave, TradeInPanel tradeInSave, FinancingPanel financingSave, 
                  LicensePlatePanel licensePlateSave, ModificationsPanel modificationsSave, CarModelPicture modelImageSave) 
                  throws IOException, NegativeInputException, PhoneNumberException, NullInputException, ClassNotFoundException{
        

        setTitle("Vehicle Cost Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        addWindowListener(this);

        greeting = greetingSave;
        greetingSave = new GreetingPanel(greetingSave.getAccountNumber(), greetingSave.getAddress(), greetingSave.getPhoneNumber(), greetingSave.getUserName());
        add(greetingSave, BorderLayout.NORTH);

        buildWesternPanel(modelSelectionSave, packageSave, tradeInSave);
        add(westernPanel, BorderLayout.WEST);

        buildEasternPanel(modelImageSave);
        add(easternPanel, BorderLayout.EAST);

        buildCenterPanel(financingSave, licensePlateSave, modificationsSave);
        add(centerPanel, BorderLayout.CENTER);

        

        calcButton = new JButton("CALCULATE");
        calcButton.addActionListener(this);
        add(calcButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    //Window that displays panel for save accounts
    /*public Window(int test){

        saves = new SavePanel();

        setTitle("Select an account, or create a new one.");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        addWindowListener(this);
        add(saves, BorderLayout.CENTER);

        setVisible(true);

    }*/

    //Builds the western panel
    private void buildWesternPanel() throws IOException{

        westernPanel = new JPanel();
        westernPanel.setLayout(new GridLayout(2,1, 0, 0));
        westernPanel.setBorder(BorderFactory.createEtchedBorder());
        westernPanel.setBackground(Color.BLUE);
        
        modelSelection = new ModelSelectionPanel();
        westernPanel.add(modelSelection);
        
        tradeIn = new TradeInPanel();
        westernPanel.add(tradeIn);
    }
    //Builds the western panel when the user is running a save file
    private void buildWesternPanel(ModelSelectionPanel modelSelectionSave, PackagePanel packageSave, TradeInPanel tradeInSave) throws IOException{

        westernPanel = new JPanel();
        westernPanel.setLayout(new GridLayout(2,1, 0, 0));
        westernPanel.setBorder(BorderFactory.createEtchedBorder());
        westernPanel.setBackground(Color.BLUE);
        
        modelSelection = new ModelSelectionPanel(modelSelectionSave, packageSave);
        westernPanel.add(modelSelection);
        
        tradeIn = new TradeInPanel(tradeInSave);
        westernPanel.add(tradeIn);
    }

    //builds the center panel
    private void buildCenterPanel() throws IOException{

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(0,25));
        centerPanel.setBorder(BorderFactory.createEtchedBorder());
        centerPanel.setBackground(Color.MAGENTA);

        financing = new FinancingPanel();
        centerPanel.add(financing, BorderLayout.NORTH);

        licensePlate = new LicensePlatePanel();
        centerPanel.add(licensePlate, BorderLayout.CENTER);

        modifications = new ModificationsPanel();
        centerPanel.add(modifications, BorderLayout.SOUTH);
    }

    //builds the center panel when the user is running a save file
    private void buildCenterPanel(FinancingPanel financingSave, LicensePlatePanel licensePlateSave, ModificationsPanel modificationsSave) throws IOException{

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(0,25));
        centerPanel.setBorder(BorderFactory.createEtchedBorder());
        centerPanel.setBackground(Color.MAGENTA);

        financing = new FinancingPanel(financingSave);
        centerPanel.add(financing, BorderLayout.NORTH);

        licensePlate = new LicensePlatePanel(licensePlateSave);
        centerPanel.add(licensePlate, BorderLayout.CENTER);

        modifications = new ModificationsPanel(modificationsSave);
        //modifications.updateModelImage();
        centerPanel.add(modifications, BorderLayout.SOUTH);
    }

    //builds the eastern panel
    private void buildEasternPanel() throws IOException{

        easternPanel = new JPanel();
        easternPanel.setLayout(new BorderLayout());
        easternPanel.setBorder(BorderFactory.createEtchedBorder());

        model = new CarModelPicture();
        easternPanel.add(model, BorderLayout.CENTER);

    }
    
    //builds the eastern panel when the user is running the save file
    private void buildEasternPanel(CarModelPicture modelImageSave) throws IOException{

        easternPanel = new JPanel();
        easternPanel.setLayout(new BorderLayout());
        easternPanel.setBorder(BorderFactory.createEtchedBorder());

        model = new CarModelPicture(modelImageSave);
        easternPanel.add(model, BorderLayout.CENTER);

    }

    //Prompts the user for phone number and address
    private void promptUser() throws PhoneNumberException, NullInputException, IOException, ClassNotFoundException{
 

        boolean isValidAddress = false, isValidPhone = false;
        
        //while the user enters invalid input, repeat
        while(!(isValidAddress && isValidPhone)){

            try{
                //if the address input is not valid, keep prompting
                if(!isValidAddress){
                    address = JOptionPane.showInputDialog("Please enter your address for delivery");
                    if(address.equals("")) throw new NullInputException("address"); //handle empty input
                    else isValidAddress = true; //set valid to true
                }
                //if the phone input is not valid, keep prompting
                if(!isValidPhone){
                    phoneNumber = formatPhoneNumber(JOptionPane.showInputDialog("Please enter your phone number"));
                    if(phoneNumber.length() != 10) throw new PhoneNumberException(phoneNumber);//handle empty input
                    else isValidPhone = true;//set valid to true

                    phoneNumber = String.format("%s-%s-%s",phoneNumber.substring(0,3), phoneNumber.substring(3, 6), phoneNumber.substring(6, 10));
                }

            }
            catch(NullInputException | PhoneNumberException e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
        

    }

    //method that formats the phone number entered
    private String formatPhoneNumber(String num){

        StringBuilder resultBuilder = new StringBuilder(num.trim());
        for(int i = 0; i < resultBuilder.length(); i++){

            int c = (int)num.charAt(i);
            //if the current ASCII value of the character does not fall in between 0-9, the character is invalid
            if( c < 48 || c > 57 ){ 
                resultBuilder.setCharAt(i, '@'); //Temporarily set invalid character to the @ symbol, which will be handled at the end
            }   

        }
        //remove all invalid characters 
        String result = resultBuilder.toString().replaceAll("@","");

        return result;
    }

    //Generates receipt by calling getCost functions from each panel, then displays to a dialog box
    public void generateReceipt() throws NegativeInputException, NullInputException{
        String receipt = "";
        double totalCost = 0;
        try{
            receipt += String.format("Base Model Price: $%,.2f\n", ModelSelectionPanel.getCost());
            totalCost += ModelSelectionPanel.getCost();

            receipt += String.format("Package B Price: $%,.2f\n", ModelSelectionPanel.packageB.getCost());
            totalCost += ModelSelectionPanel.packageB.getCost();

            
            receipt += String.format("Trade-In Deduction: -$%,.2f\n", tradeIn.getTradeInValue(ModelSelectionPanel.getCost()));
            totalCost -= tradeIn.getTradeInValue(ModelSelectionPanel.getCost());
            
            receipt += String.format("In Cash Discount: -$%.2f\n", tradeIn.getDiscount());
            totalCost -= tradeIn.getDiscount();
            
            receipt += String.format("License Plate Cost:  $%,.2f\n", LicensePlatePanel.getCost());
            totalCost += LicensePlatePanel.getCost();

            receipt += String.format("Modifications:  $%,.2f\n", modifications.getCost());
            totalCost += modifications.getCost();

            receipt += String.format("Title and Tags:  $%,.2f\n", TITLES_TAGS_PRICE);
            totalCost += TITLES_TAGS_PRICE;

            receipt += "-------------------------------\nSubtotal: " + String.format("$%,.2f\n", totalCost) + "\n";

            receipt += String.format("Price After Financing:  $%,.2f\n", totalCost *= financing.getRate());

            receipt += String.format("After Sales Tax:  $%,.2f\n", totalCost *= SALES_TAX_RATE);

            receipt += "-------------------------------\n";

            receipt += "Final Cost: " +  String.format("$%,.2f\n", totalCost);
            receipt += "Expected Delivery Date: " + PackagePanel.expectedDelivery;
            
            JOptionPane.showMessageDialog(null, receipt, "Online Receipt", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(NullInputException | NumberFormatException | NegativeInputException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    //generates a random account number
    public static String generateAccountNumber(){
        
        String accNum = "";

        for(int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++){
            int ranNum1 = (int)(Math.random() * 10); //generates a random number from 0-9
            if(ranNum1 % 2 == 0){ //if even, add a number
                accNum+=ranNum1;
            }
            else{ //if odd, add a random capitalized letter
                int ranNum2 = (int)(Math.random() * 26) + 65; //generate a random number that is associated with a capital letter in the ascii chart
                accNum += (char)ranNum2;
            }
        }

        return accNum;

    }

    public static void main(String[] args) throws IOException, NegativeInputException, PhoneNumberException, NullInputException, ClassNotFoundException{
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            
                try {
                    //prompt the user for their name
                    JOptionPane.showMessageDialog(null, "Hello! Welcome to Ely's Vehicle Cost Calculator. Press \"Ok\" to continue.");
                    boolean isValidName = false;
                    
                   // new Window(2);

                    //keep prompting until the user enters valid input
                    while(!(isValidName)){   
                        try{              
                            name = JOptionPane.showInputDialog("Please enter your name");
                            if(name.equals("")) throw new NullInputException("name");
                            else isValidName = true;   
                        }
                        catch(NullInputException e){
                            
                            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } 
                    //attempt to open a save file
                    try{
                        File accountFile = new File("saveFiles\\accounts.dat");
                        FileInputStream inFile = new FileInputStream(accountFile);
                        ObjectInputStream saveRead = new ObjectInputStream(inFile);

                        //If the file has contents, read all objects
                        if(accountFile.length() > 0){

                            //read objects from each panel and store them 
                            GreetingPanel myGreetingPanel = (GreetingPanel)saveRead.readObject();
                            
                            ModelSelectionPanel myModel = (ModelSelectionPanel)saveRead.readObject();
                            PackagePanel myPackage = (PackagePanel)saveRead.readObject();
                            TradeInPanel myTradeIn = (TradeInPanel)saveRead.readObject();

                            FinancingPanel myFinancing = (FinancingPanel)saveRead.readObject();
                            LicensePlatePanel myLicensePlate = (LicensePlatePanel)saveRead.readObject();
                            ModificationsPanel myModifications = (ModificationsPanel)saveRead.readObject();

                            CarModelPicture myModelImage = (CarModelPicture)saveRead.readObject();
                            
                            //If the user enters a name that is similar to an existing account, ask if they would like to load saved data
                            if(myGreetingPanel.getUserName().contains(name)){
                                int prompt = JOptionPane.showConfirmDialog(null, "Existing account under the name \"" + myGreetingPanel.getUserName() + 
                                                                            "\" was found.\nWould you like to load saved data?");
                                //if the user presses yes, clear all current data and open save file
                                if(prompt == JOptionPane.YES_OPTION){
                                    new FileOutputStream(FILE_PATH).close();
                                    new Window(myGreetingPanel, myModel, myPackage, myTradeIn, myFinancing, myLicensePlate, myModifications, myModelImage);
                                }
                                //if the user enters no or cancel, start a new default window
                                else new Window(); 

                                //prevent data leak
                                saveRead.close();

                            }
                            //If there is no data in the file, start a new default window
                            else new Window();
                        }
                    }
                    //If there was an IOException (i.e file not found, end of file), then there is currently no existing account, so run the default window that will add info to the account file
                    catch(IOException e1){
                        new Window();
                    }

                }
                 catch (IOException | NegativeInputException | PhoneNumberException | NullInputException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } 
        });
    }


    //generates a receipt when the user presses calculate button
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            generateReceipt();
        } catch (NegativeInputException | NullInputException e1) {
            e1.getMessage();
        }
    }

    //Detects when a window is closing and saves data if prompted to 
    @Override
    public void windowClosing(WindowEvent e){
        //ask the user if they want to save their data
        int prompt = JOptionPane.showConfirmDialog(null, "Are you sure you would like to exit?...Unsaved progress will be lost\nWould you like to save?");
        
        //If the user presses yes, write all data to the save file and close window
        if(prompt == JOptionPane.YES_OPTION){
            try{
               // FileOutputStream saveFile = new FileOutputStream("saveFiles\\" + name + ".dat", true);
               FileOutputStream saveFile = new FileOutputStream(FILE_PATH, true);
                ObjectOutputStream save = new ObjectOutputStream(saveFile); 
                save.writeObject(greeting);

                save.writeObject(modelSelection);
                save.writeObject(modelSelection.getPackage());
                save.writeObject(tradeIn);

                save.writeObject(financing);
                save.writeObject(licensePlate);
                save.writeObject(modifications);

                save.writeObject(model);
                                
                save.close();
            }
            catch(IOException e1){
                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            setVisible(false); 
            dispose(); //Destroy the JFrame object
        }
        //If the user enters no, clear all data from save file and close window
        else if(prompt == JOptionPane.NO_OPTION){
            try {
                new FileOutputStream(FILE_PATH).close();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false); 
            dispose(); //Destroy the JFrame object
        }
        else return;
    }

    //Unused for this program, but these methods are required by the implemented interface
    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
}
