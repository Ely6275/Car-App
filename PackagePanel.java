import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PackagePanel extends JPanel{
    
    //Instance variables for components and panels
    private JLabel youSelectedLabel, promptLabel, benefit1, benefit2, benefit3;

    public ButtonGroup radios;
    public JRadioButton yesButton, noButton;

    private JPanel promptPanel, benefitsPanel, buttonPanel;

    //stores the current model that is selected
    private String modelSelected;

    //Constant that stores the price for the package
    private static final int PACKAGE_PRICE = 3250;
    //stores expected delivery time
    public static String expectedDelivery = "2-4 Business Weeks";


    //Constructor that runs on start up, adds components, formatting, and colors
    public PackagePanel(){

        setLayout(new BorderLayout());

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Package Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        setBackground(Color.BLUE);
        buildPackagePanel();
 
    }

    //Contructor that runs when the user is running a save file
    public PackagePanel(PackagePanel packageSave){

        setLayout(new BorderLayout());

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Package Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        setBackground(Color.BLUE);
        buildPackagePanel(packageSave);
    }

    //declares all components, buttons, labels, panels, formatiing, colors, and adds to the panel
    private void buildPackagePanel(){

        promptPanel = new JPanel();
        promptPanel.setLayout(new BorderLayout(0,0));
        
        youSelectedLabel = new JLabel("Package B is only available for the S70 and S80 Models");
        youSelectedLabel.setForeground(Color.cyan);

        promptLabel = new JLabel("Here is what you are missing out on");
        promptLabel.setForeground(Color.cyan);

        benefit1 = new JLabel("- Faster Delivery");
        benefit1.setForeground(Color.BLACK);

        benefit2 = new JLabel("- Free License Plate");
        benefit2.setForeground(Color.BLACK);

        benefit3 = new JLabel("- Ely's Vehicles Premium Membership");  
        benefit3.setForeground(Color.BLACK);                         
        
        benefitsPanel = new JPanel();
        benefitsPanel.setBorder(BorderFactory.createEtchedBorder());
        benefitsPanel.setLayout(new BoxLayout(benefitsPanel, BoxLayout.PAGE_AXIS));

        benefitsPanel.add(benefit1);
        benefitsPanel.add(benefit2);
        benefitsPanel.add(benefit3);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));

        radios = new ButtonGroup();

        yesButton = new JRadioButton("Yes");
        yesButton.setBackground(Color.cyan);

        noButton = new JRadioButton("No", true);
        noButton.setBackground(Color.cyan);

        radios.add(yesButton);
        radios.add(noButton);

        buttonPanel.add(yesButton, true);
        buttonPanel.add(noButton);
        buttonPanel.setVisible(false);

        promptPanel.add(youSelectedLabel, BorderLayout.NORTH);
        promptPanel.add(promptLabel, BorderLayout.CENTER);
        
        promptPanel.setBackground(Color.blue);
        benefitsPanel.setBackground(Color.blue);
        buttonPanel.setBackground(Color.blue);

        add(promptPanel, BorderLayout.NORTH);
        add(benefitsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
    }

    //Same functionality as the previous panel, except it initializes components to the one from the save file
    private void buildPackagePanel(PackagePanel packageSave){

        promptPanel = new JPanel();
        promptPanel.setLayout(new BorderLayout(0,0));
        
        youSelectedLabel = new JLabel("Package B is only available for the S70 and S80 Models");
        youSelectedLabel.setForeground(Color.cyan);

        promptLabel = new JLabel("Here is what you are missing out on");
        promptLabel.setForeground(Color.cyan);

        benefit1 = new JLabel("- Faster Delivery");
        benefit1.setForeground(Color.BLACK);

        benefit2 = new JLabel("- Free License Plate");
        benefit2.setForeground(Color.BLACK);

        benefit3 = new JLabel("- Ely's Vehicles Premium Membership");  
        benefit3.setForeground(Color.BLACK);                          
        
        benefitsPanel = new JPanel();
        benefitsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        benefitsPanel.setLayout(new BoxLayout(benefitsPanel, BoxLayout.PAGE_AXIS));

        benefitsPanel.add(benefit1);
        benefitsPanel.add(benefit2);
        benefitsPanel.add(benefit3);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));

        this.radios = packageSave.radios;
        this.yesButton = packageSave.yesButton;

        yesButton.setBackground(Color.cyan);
        this.noButton = packageSave.noButton;
        noButton.setBackground(Color.cyan);

        radios.add(yesButton);
        radios.add(noButton);

        buttonPanel.add(yesButton, true);
        buttonPanel.add(noButton);
        buttonPanel.setVisible(false);

        promptPanel.add(youSelectedLabel, BorderLayout.NORTH);
        promptPanel.add(promptLabel, BorderLayout.CENTER);

        promptPanel.setBackground(Color.blue);
        benefitsPanel.setBackground(Color.blue);
        buttonPanel.setBackground(Color.blue);

        add(promptPanel, BorderLayout.NORTH);
        add(benefitsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
    }


    //Runs package B and determines which labels to display based on the current selected model
    public void runPackageB(String model){
        modelSelected = ModelSelectionPanel.getModel();

        if( !(model.equalsIgnoreCase("S70") || model.equalsIgnoreCase("S80")) ){
            youSelectedLabel.setText("Package B is only available for the S70 and S80 Models");
            promptLabel.setText("Here is what you are missing out on");
            buttonPanel.setVisible(false);
        }
        else{
            youSelectedLabel.setText("You selected the Model " + modelSelected);
            promptLabel.setText("Would you like to purchase package B?");
            buttonPanel.setVisible(true);
        }
    }

    //Returns the cost from this panel
    public double getCost(){
        //if the user opts in
        if(yesButton.isSelected()){
            LicensePlatePanel.isPremiumMember = true; //set premium membership to take off price of license plate
            expectedDelivery = "1-2 Business Weeks"; //reduce delivery time
            return PACKAGE_PRICE;
        }
        else{
            LicensePlatePanel.isPremiumMember = false;
            expectedDelivery = "2-4 Business Weeks";
            return 0;
        }
    }
}
