import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class SavePanel extends JPanel implements ActionListener{

    private JLabel title;
    private JButton test;

    private boolean accountIsSelected = false;

    public SavePanel(){

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Please select an account"));

        title = new JLabel("Available Accounts");
        
        test = new JButton("test");

        add(title, BorderLayout.NORTH);
        add(test, BorderLayout.CENTER);
    }

    public boolean checkSelected(){
        return accountIsSelected;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(test.isSelected()) accountIsSelected = true;
    }

}
