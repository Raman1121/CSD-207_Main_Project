import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

/**
 * Build the Main GUI of the Beatbox app
 *
 * @author Raman Dutt
 * @version 1.0
 * @since 2017-11-22
 */

public class GUI_MainClass {


    public JPanel leftPanel;
    public JPanel rightPanel;

    public JPanel backgroundPanel;
    public JFrame mainFrame;
    public BorderLayout layout;

    //Create Buttons
    public static JButton startButton = new JButton("START");
    public static JButton stopButton = new JButton("STOP");
    public static JButton tempoUP = new JButton("TEMPO UP");
    public static JButton tempoDown = new JButton("TEMPO DOWN");
    int count = 0;
    //Create an arrayList of Check Boxes
    ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

    //Name of the instruments and key codes.
    public final String[] instruments = {"Bass Drum", "Closed Hit-Hat", "Open Hit-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-Mid Tom", "High Agogo", "Open High Conga"};

    public void buildGUI() {
        mainFrame = new JFrame("THE CYBER BEATBOX");
        layout = new BorderLayout();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);      //For the close operation
        mainFrame.setSize(800, 700);
        mainFrame.setVisible(true);

        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        backgroundPanel = new JPanel(layout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //To create a margin from all the sides.

        //Add the buttons in a vertical alignment.
        buttonBox.add(Box.createRigidArea(new Dimension(0, 70)));
        buttonBox.add(startButton);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 70)));

        buttonBox.add(stopButton);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 70)));

        buttonBox.add(tempoUP);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 70)));

        buttonBox.add(tempoDown);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 70)));

        //Add buttonBox to a panel and then add the panel to the Frame mainFrame.
        leftPanel.add(buttonBox);
        mainFrame.getContentPane().add(BorderLayout.EAST, leftPanel);

        Box nameBox = new Box(BoxLayout.Y_AXIS);

        //Initialize the labels with the instruments list

        EmptyBorder border = new EmptyBorder(15, 0, 16, 0);

        for (int i = 0; i < 16; i++) {

            JLabel label = new JLabel(instruments[i]);
            label.setFont(new Font("Serif", Font.BOLD, 12));
            label.setBorder(border);
            nameBox.add(label);
        }

        //Add the names of the instruments to the rightPanel.
        rightPanel.add(nameBox);

        //Add the rightPanel to the mainFrame.
        mainFrame.getContentPane().add(BorderLayout.WEST, rightPanel);

        //Make a grid Layout (16 x 16) and it to the centerPanel
        GridLayout grid = new GridLayout(16, 16);

        //set gaps in the grid
        grid.setHgap(1);

        JPanel centerPanel = new JPanel(grid);      //DOUBT

        //Add Check Boxes to the grid Layout

        for (int i = 0; i < 256; i++) {

            JCheckBox cb = new JCheckBox();
            cb.setSelected(false);
            centerPanel.add(cb);

            checkBoxes.add(cb);
        }

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox checkBox : checkBoxes){
                    if(checkBox.isSelected()){
                        break;
                    }
                    else{
                        count++;
                    }
                }
                if(count == 256){
                    showAlertBox();
                }
            }
        });
        //Add the check boxes and the grid layout to the main frame.
        mainFrame.getContentPane().add(centerPanel);
    }

     public void showAlertBox(){
        JPanel jPanel = new JPanel();
        JOptionPane.showMessageDialog(jPanel,"Please select atleast one check box to continue","Warning",JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        GUI_MainClass gui = new GUI_MainClass();
        gui.buildGUI();
    }


}
