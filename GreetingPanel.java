import java.awt.*;
import java.io.*;
import javax.swing.*;

public class GreetingPanel extends JPanel implements Serializable{
    
    //Declare instance variables that will be the components for this panel
    private JLabel accountNumberLabel, addressLabel, phoneLabel;
    private JPanel accountPanel;

    //Store user information
    private String account, address, phone, name;

    //Constructor that sets all user information and builds the greeting panel
    public GreetingPanel(String account, String address, String phone, String name){
        //Set all instance variables to the ones passed into the constructor
        this.account = account;
        this.address = address;
        this.phone = phone;
        this.name = name;

        //Set the layout and border for this panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Welcome, " + name));

        //Labels that display the user's information
        accountNumberLabel = new JLabel(String.format("Account Number:     %-20s",account));
        phoneLabel = new JLabel(String.format("Phone Number:         %-20s",phone));
        addressLabel = new JLabel(String.format("Address:                     %-20s",address));
        

        //Panel that stores and displays all user informaton
        accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(3,1));
        accountPanel.add(accountNumberLabel);
        accountPanel.add(phoneLabel);
        accountPanel.add(addressLabel);

        //Add all user information to the greeting panel
        add(accountPanel, BorderLayout.WEST);
    }

    //Getter methods for user information
    public String getUserName(){
        return name;
    }
    public String getAccountNumber(){
        return account;
    }
    public String getPhoneNumber(){
        return phone;
    }
    public String getAddress(){
        return address;
    }
}