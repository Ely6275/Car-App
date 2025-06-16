import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ModificationsPanel extends JPanel implements ItemListener{

    //intance variables for all components and panels to be displayed
    private JLabel promptLabel;

    private  JCheckBox paintCheckBox, hoodVentCheckBox, spoilerCheckBox;

    //Combo box for different selectable colors
    private  JComboBox paintBodyComboBox;
    private String[] paintColors = {"Metallic", "Red", "Black"};

    //Constants for different modification prices
    private static final double METALLIC_PRICE = 650, RED_PRICE = 400, BLACK_PRICE = 200;
    private static final double HOOD_VENT_PRICE = 200;
    private static final double SPOILER_PRICE = 300;

    //subpanels
    private JPanel promptPanel, paintPanel, checkBoxPanel;

    //initialize color and model
    public static String color = "Metallic", model = "S40";

    //Constructor to run on startup, sets all formatting and builds panels
    public ModificationsPanel() throws IOException{ 

        setLayout(new BorderLayout());
        setBackground(Color.MAGENTA);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Modification Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);
        
        //build the panel
        buildModificationsPanel();

    }
    

    //Constuctor that runs when the user is running a save file
    public ModificationsPanel(ModificationsPanel modificationsSave) throws IOException{

        setLayout(new BorderLayout());
        setBackground(Color.MAGENTA);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Modification Options");
        setBorder(titledBorder);
        titledBorder.setTitleColor(Color.BLACK);

        //build the panel
        buildModificationsPanel(modificationsSave);
        
    }

    //sets current model and updates car model image
    public void setModel(String m){
        model = m;
        updateModelImage();
    }

    public static void setImage(String model) throws IOException{
        switch(model){
            case "S40" -> CarModelPicture.setImage("images\\CARS\\S40_" + color.toUpperCase() + ".jpg");
            case "S60" -> CarModelPicture.setImage("images\\CARS\\S60_" + color.toUpperCase() + ".jpg");
            case "S70" -> CarModelPicture.setImage("images\\CARS\\S70_"+color.toUpperCase()+".jpg");
            case "S80" -> CarModelPicture.setImage("images\\CARS\\S80_"+color.toUpperCase()+".jpg");
        }
    }

    //sets all formatting, creates all components, panels, and colors. Builds the overall panel
    private void buildModificationsPanel() throws IOException{
        
        promptPanel = new JPanel();
        promptPanel.setBorder(BorderFactory.createEtchedBorder());
        promptLabel = new JLabel("Select any additional modifications");
        promptLabel.setForeground(Color.BLACK);
        promptPanel.add(promptLabel);


        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BorderLayout());

        paintCheckBox = new JCheckBox("Paint Job");
        paintCheckBox.setBackground(Color.MAGENTA);
        paintCheckBox.setForeground(Color.BLACK);
        paintCheckBox.addItemListener(this);

        hoodVentCheckBox = new JCheckBox("Hood Vents");
        hoodVentCheckBox.setBackground(Color.MAGENTA);
        hoodVentCheckBox.setForeground(Color.BLACK);
        hoodVentCheckBox.addItemListener(this);

        spoilerCheckBox = new JCheckBox("Spoiler");
        spoilerCheckBox.setBackground(Color.MAGENTA);
        spoilerCheckBox.setForeground(Color.BLACK);
        spoilerCheckBox.addItemListener(this);

        paintBodyComboBox = new JComboBox<>(paintColors);
        paintBodyComboBox.addItemListener(this);
        paintBodyComboBox.setVisible(false);

        paintPanel = new JPanel();
        paintPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        paintPanel.setBackground(Color.magenta);
        paintPanel.add(paintCheckBox);
        paintPanel.add(paintBodyComboBox);

        checkBoxPanel.add(paintPanel, BorderLayout.NORTH);
        checkBoxPanel.add(hoodVentCheckBox, BorderLayout.CENTER);
        checkBoxPanel.add(spoilerCheckBox, BorderLayout.SOUTH);

        promptPanel.setBackground(Color.MAGENTA);
        checkBoxPanel.setBackground(Color.MAGENTA);

        add(promptPanel, BorderLayout.NORTH);
        add(checkBoxPanel, BorderLayout.SOUTH);


    }

    //Same logic as previous method, except it uses info from the save file to initialize components
    private void buildModificationsPanel(ModificationsPanel modificationsSave) throws IOException{
        
        promptPanel = new JPanel();
        promptPanel.setBorder(BorderFactory.createEtchedBorder());
        promptLabel = new JLabel("Select any additional modifications");
        promptLabel.setForeground(Color.BLACK);
        promptPanel.add(promptLabel);


        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BorderLayout());

        paintCheckBox = modificationsSave.getPaintCheckBox();
        paintCheckBox.addItemListener(this);

        hoodVentCheckBox = modificationsSave.hoodVentCheckBox;
        hoodVentCheckBox.setBackground(Color.MAGENTA);
        hoodVentCheckBox.setForeground(Color.BLACK);
        hoodVentCheckBox.addItemListener(this);

        spoilerCheckBox = modificationsSave.spoilerCheckBox;
        spoilerCheckBox.setBackground(Color.MAGENTA);
        spoilerCheckBox.setForeground(Color.BLACK);
        spoilerCheckBox.addItemListener(this);

        paintBodyComboBox = modificationsSave.paintBodyComboBox;
        paintBodyComboBox.addItemListener(this);
        paintBodyComboBox.setVisible(false);

        paintPanel = new JPanel();
        paintPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        paintPanel.setBackground(Color.magenta);
        paintPanel.add(paintCheckBox);
        paintPanel.add(paintBodyComboBox);

        checkBoxPanel.add(paintPanel, BorderLayout.NORTH);
        checkBoxPanel.add(hoodVentCheckBox, BorderLayout.CENTER);
        checkBoxPanel.add(spoilerCheckBox, BorderLayout.SOUTH);

        promptPanel.setBackground(Color.MAGENTA);
        checkBoxPanel.setBackground(Color.MAGENTA);

        add(promptPanel, BorderLayout.NORTH);
        add(checkBoxPanel, BorderLayout.SOUTH);

        if(paintCheckBox.isSelected()) paintBodyComboBox.setVisible(true);
        else paintBodyComboBox.setVisible(false);

        updateModelImage();
    }


    @SuppressWarnings({ "static-access" })
    private JCheckBox getPaintCheckBox(){
        return paintCheckBox;
    }

    //returns cost from this panel
    public double getCost(){
        //initialize cost to 0
        double cost = 0;

        //add price of paint
        if(paintCheckBox.isSelected()){
            if(paintBodyComboBox.getSelectedItem().equals("Metallic")) cost += METALLIC_PRICE;
            else if(paintBodyComboBox.getSelectedItem().equals("Red")) cost += RED_PRICE;
            else cost += BLACK_PRICE;
        }
        //add vent and spoiler prices
        if(hoodVentCheckBox.isSelected()) cost += HOOD_VENT_PRICE;
        if(spoilerCheckBox.isSelected()) cost += SPOILER_PRICE;

        return cost;
    }


    //updates the current car iamge
    public void updateModelImage(){

        //retrieve color and model 
        color = ""+paintBodyComboBox.getSelectedItem();
        model = ModelSelectionPanel.getModel();
        //initialize file path to be added to
        String filePath = "images\\CARS\\";
            //add to file path based on selected model and color
            try{
                switch(model){
                    case "S40":
                        switch(color){
                            case "Metallic":
                                filePath += filePathDecider(model, "Metallic");
                                break;
                            case "Black":
                                filePath += filePathDecider(model, "Black");
                                break;
                            case "Red":
                                filePath += filePathDecider(model, "Red");
                                break;
                        }
                        break;
                    case "S60":
                        switch(color){
                            case "Metallic":
                                filePath += filePathDecider(model, "Metallic");
                                break;
                            case "Black":
                                filePath += filePathDecider(model, "Black");
                                break;
                            case "Red":
                                filePath += filePathDecider(model, "Red");
                                break;
                        }
                        break;
                    case "S70":
                        switch(color){
                            case "Metallic":
                                filePath += filePathDecider(model, "Metallic");
                                break;
                            case "Black":
                                filePath += filePathDecider(model, "Black");
                                break;
                            case "Red":
                                filePath += filePathDecider(model, "Red");
                                break;
                        }
                        break;
                    case "S80":
                        switch(color){
                            case "Metallic":
                                filePath += filePathDecider(model, "Metallic");
                                break;
                            case "Black":
                                filePath += filePathDecider(model, "Black");
                                break;
                            case "Red":
                                filePath += filePathDecider(model, "Red");
                                break;
                        }
                        break;
                }
                //set the image and write to the data file
                CarModelPicture.setImage(filePath);
                CarModelPicture.writeImage();
            }
            catch(IOException a){
                JOptionPane.showMessageDialog(null, a.getMessage(), "Error Opening Image", JOptionPane.ERROR_MESSAGE);
            }

    } 
    //Creates the file path of the desired image through string concatenation, then returns the final file path
    private String filePathDecider(String model, String color){
        String path = "";
        if(spoilerCheckBox.isSelected() && hoodVentCheckBox.isSelected()){
            path += (model + "_" + color.toUpperCase() + "_SPOILER_VENT.jpg");
        }
        else if(spoilerCheckBox.isSelected() && !hoodVentCheckBox.isSelected()){
            path += (model + "_" + color.toUpperCase() + "_SPOILER.jpg");
        }
        else if(!spoilerCheckBox.isSelected() && hoodVentCheckBox.isSelected()){
            path += (model + "_" + color.toUpperCase() + "_VENT.jpg");
        }
        else{
            path += (model + "_" + color.toUpperCase() + ".jpg"); 
        }
        return path;
    }

    //Update the current car model and paint check box
    @Override
    public void itemStateChanged(ItemEvent e){
        if(paintCheckBox.isSelected()){
            paintBodyComboBox.setVisible(true);
            updateModelImage();
        }
        else{
            updateModelImage();
            paintBodyComboBox.setVisible(false);
        }
  
    }
}
