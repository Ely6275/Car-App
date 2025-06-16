import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
/*
 * NOTE: Code for image import retrieved from stack overflow
 * CAR ICON RETRIEVED FROM: <a href="https://www.flaticon.com/free-icons/car" title="car icons">Car icons created by Freepik - Flaticon</a>
 * 
 */

public class ModelSelectionPanel extends JPanel implements ActionListener {
    
    //Intialize all components to be displayed on panel
    private JLabel modelSelectLabel;
    private ButtonGroup radioGroup;
    private JRadioButton S40_Button, S60_Button, S70_Button, S80_Button;

    //Information for the car icon
    public static BufferedImage myPicture;
    private JLabel carIcon;
    private static final int iconWIDTH = 25, iconHEIGHT = 25;

    //sub panels
    private JPanel selectionPanel;
    private JPanel titlePanel;

    //declare a package panel
    public static PackagePanel packageB;

    //set default model as S40
    public static String model = "S40";
    //Declare constants for each car price
    private static final double S40_PRICE = 27700, S60_PRICE = 32500, S70_PRICE = 36000, S80_PRICE = 44000;

    //file path where icon will be saved
    private static final String FILE_PATH = "saveFiles\\accounts.dat";

    //contructor that will run on startup, intializes all components and formats and builds the panel
    public ModelSelectionPanel() throws IOException{
        
        setLayout(new BorderLayout());
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Package Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);
        
        setBackground(Color.blue);

        //build the panel
        buildModelSelectionPanel();
        
    }
    //cosntructor that will run when the user is running a save file. Will copy model and package info to current class. 
    //Same logic as previous constructor otherwise
    public ModelSelectionPanel(ModelSelectionPanel modelSave, PackagePanel packageSave) throws IOException{
        
        setLayout(new BorderLayout());

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Package Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        setBackground(Color.blue);
        buildModelSelectionPanel(modelSave, packageSave);
        
    }


    //Adds all formats, colors, and components to sub panels and main panels
    private void buildModelSelectionPanel(){

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        titlePanel.setBorder(BorderFactory.createEtchedBorder());
        
        modelSelectLabel = new JLabel("MODEL SELECTION");
        modelSelectLabel.setForeground(Color.CYAN);
        modelSelectLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        //Attempt to open and read image icon
        try{
            myPicture = new BufferedImage(iconWIDTH, iconHEIGHT, BufferedImage.TYPE_INT_ARGB);
            myPicture = ImageIO.read(new File("images\\carIcon.png"));
            carIcon = new JLabel(new ImageIcon(myPicture.getScaledInstance(iconWIDTH, iconHEIGHT, Image.SCALE_FAST)));
            //write image to data file for serialization
            writeImage();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            

        titlePanel.add(modelSelectLabel);
        titlePanel.add(carIcon);

        selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        selectionPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        S40_Button = new JRadioButton("S40", true);
        S40_Button.setBackground(Color.CYAN);
        S40_Button.addActionListener(this);

        S60_Button = new JRadioButton("S60");
        S60_Button.setBackground(Color.CYAN);
        S60_Button.addActionListener(this);

        S70_Button = new JRadioButton("S70");
        S70_Button.setBackground(Color.CYAN);
        S70_Button.addActionListener(this);

        S80_Button = new JRadioButton("S80");
        S80_Button.setBackground(Color.CYAN);
        S80_Button.addActionListener(this);

        radioGroup = new ButtonGroup();
        radioGroup.add(S40_Button);
        radioGroup.add(S60_Button);
        radioGroup.add(S70_Button);
        radioGroup.add(S80_Button);

        selectionPanel.add(S40_Button);
        selectionPanel.add(S60_Button);
        selectionPanel.add(S70_Button);
        selectionPanel.add(S80_Button);
        
        //intialize the package panel
        packageB = new PackagePanel();

        titlePanel.setBackground(Color.BLUE);
        selectionPanel.setBackground(Color.BLUE);

        add(titlePanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.CENTER);
        add(packageB, BorderLayout.SOUTH);

        //set the current model and run the package based on that model
        setModel();
        packageB.runPackageB(model);

    }

    //Same logic as previous constructor, except it will copy save data to the class
    private void buildModelSelectionPanel(ModelSelectionPanel modelSave, PackagePanel packageSave){

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        titlePanel.setBorder(BorderFactory.createEtchedBorder());
        
        modelSelectLabel = new JLabel("MODEL SELECTION");
        modelSelectLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        try{
            myPicture = new BufferedImage(iconWIDTH, iconHEIGHT, BufferedImage.TYPE_INT_ARGB);
            myPicture = ImageIO.read(new File("images\\carIcon.png"));
            carIcon = new JLabel(new ImageIcon(myPicture.getScaledInstance(iconWIDTH, iconHEIGHT, Image.SCALE_FAST)));
            writeImage();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            

        titlePanel.add(modelSelectLabel);
        titlePanel.add(carIcon);

        selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        this.S40_Button = modelSave.S40_Button;
        S40_Button.addActionListener(this);

        this.S60_Button = modelSave.S60_Button;
        S60_Button.addActionListener(this);

        this.S70_Button = modelSave.S70_Button;
        S70_Button.addActionListener(this);

        this.S80_Button = modelSave.S80_Button;
        S80_Button.addActionListener(this);

        this.radioGroup = modelSave.radioGroup;
        radioGroup.add(S40_Button);
        radioGroup.add(S60_Button);
        radioGroup.add(S70_Button);
        radioGroup.add(S80_Button);

        selectionPanel.add(S40_Button);
        selectionPanel.add(S60_Button);
        selectionPanel.add(S70_Button);
        selectionPanel.add(S80_Button);

        packageB = new PackagePanel(packageSave);

        titlePanel.setBackground(Color.BLUE);
        selectionPanel.setBackground(Color.BLUE);

        add(titlePanel, BorderLayout.NORTH);
        add(selectionPanel, BorderLayout.CENTER);
        add(packageB, BorderLayout.SOUTH);
        

        setModel();
        packageB.runPackageB(model);

    }

    //returns the current package
    public PackagePanel getPackage(){
        return packageB;
    }


    //Writes icon to save file for serialization
    public void writeImage() throws IOException{
        try{
            ImageIO.write(myPicture, "dat", new File(FILE_PATH));
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Sets the current model based on button input
    private void setModel(){
        if(S40_Button.isSelected()) model = "S40";
        else if(S60_Button.isSelected()) model = "S60";
        else if(S70_Button.isSelected()) model = "S70";
        else model = "S80";
    }
    //getter method for current model
    public static String getModel(){
        return model;
    }

    //Returns cost based on selected model
    public static double getCost(){
        switch(model){
            case "S40": return S40_PRICE;
            case "S60": return S60_PRICE;
            case "S70": return S70_PRICE;
            case "S80": return S80_PRICE;
            default: return 0;
        }
    }
    //Sets the current model, updates car image, as well as labels
    @Override
    public void actionPerformed(ActionEvent e) {
        setModel();
        
        try {
            ModificationsPanel.setImage(model);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        if(S70_Button.isSelected() || S80_Button.isSelected()){
            packageB.runPackageB(model);
            setBorder(BorderFactory.createEtchedBorder());
        }
        else{
            packageB.runPackageB(model);
        }
    }
}
