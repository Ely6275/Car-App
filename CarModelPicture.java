import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class CarModelPicture extends JPanel{

    /*
     * Declare all class variabeles
     */
    
    //Variables to store image of the car
    public static BufferedImage carImage;
    public static JLabel finalImage;

    //Image information and file paths
    public static final int WIDTH = 900, HEIGHT = 500;
    public static final String SAVE_PATH = "saveFiles\\accounts.dat";
    public static String CURR_READ_PATH = "images\\CARS\\S40_METALLIC.jpg";

    //Default constructor that runs on first startup, creates the carModel panel
    public CarModelPicture() throws IOException{
        
        setBorder(BorderFactory.createTitledBorder("Your Car Model"));
        buildCarModelPanel();
        
    }
   
    //Constructor that runs when the user is running a save file, uses the model image
    public CarModelPicture(CarModelPicture modelSave) throws IOException{
        
        setBorder(BorderFactory.createTitledBorder("Your Car Model"));
        buildCarModelPanel(modelSave);
        
    }

    //Method that runs on startup to build the entire car model panel
    private void buildCarModelPanel() throws IOException{

        //Attempt to open the image and read it
        try{
            carImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            carImage = ImageIO.read(new File(CURR_READ_PATH));
            finalImage = new JLabel(new ImageIcon(carImage.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST)));
        }
        //Catch any IOException and show an error message
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        //Write the image to the data file for serialization
        writeImage();
        //add the image to the panel
        add(finalImage);

    }

    //method that runs when the user is running a save file. Uses the image that was saved and displays it.
    
    @SuppressWarnings("static-access")
    private void buildCarModelPanel(CarModelPicture modelSave) throws IOException{

        try{
            //Set the current carImage to the carImage from the save file
            this.carImage = modelSave.carImage;
            carImage = ImageIO.read(new File(modelSave.CURR_READ_PATH));
            //Set the current finalImage to the finalImage from the save file
            this.finalImage = new JLabel(new ImageIcon(carImage.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST)));
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        //Write the image to the data file for serialization
        writeImage();
        //add the image to the panel
        add(finalImage);
  
    }

    //Set the current image to the one with the file path "path"
    public static void setImage(String path) throws IOException{
        
        //Store new path
        CURR_READ_PATH = path;
        //Store new image
        carImage = ImageIO.read(new File(CURR_READ_PATH));
        finalImage.setIcon(new ImageIcon(carImage.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST)));

        //Write image to data file for serialization
        writeImage();
    }

    //Writes the current displayed image to the data file to be stored during serialization
    public static void writeImage() throws IOException{
        try{
            ImageIO.write(carImage, "dat", new File(SAVE_PATH));
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
