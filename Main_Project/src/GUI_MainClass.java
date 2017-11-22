import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI_MainClass implements ActionListener {

    /**
        * Build the Main GUI of the Beatbox app
        * @author  Raman Dutt
        * @version 1.0
        * @since   2017-11-22
        */

    public JPanel leftPanel;
    public JPanel rightPanel;

    public JPanel backgroundPanel;
    public JFrame mainFrame;
    public BorderLayout layout;

    //Create Buttons
    public JButton startButton = new JButton("START");
    public JButton stopButton = new JButton("STOP");
    public JButton tempoUP = new JButton("TEMPO UP");
    public JButton tempoDown = new JButton("TEMPO DOWN");

    //Create an arrayList of Check Boxes
    ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

    //Name of the instruments and key codes.
    public final String[] instruments = {"Bass Drum", "Closed Hit-Hat", "Open Hit-Hat", "Acoustic Snare", "Crash Cymbal",
                                    "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga",
                                    "Cowbell", "Vibraslap", "Low-Mid Tom", "High Agogo", "Open High Conga"};

    public void buildGUI(){

        mainFrame = new JFrame("THE CYBER BEATBOX");
        layout = new BorderLayout();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);      //For the close operation
        mainFrame.setSize(800, 700);
        mainFrame.setVisible(true);

        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        backgroundPanel = new JPanel(layout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10)); //To create a margin from all the sides.

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

        EmptyBorder border = new EmptyBorder(15, 0,16,0);

        for(int i=0; i<16; i++){

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
        EmptyBorder border2 = new EmptyBorder(0,0,0,0);

        for(int i=0; i<256; i++){

            JCheckBox cb = new JCheckBox();
            cb.setSelected(false);
            cb.setBorder(border2);
            centerPanel.add(cb);

            checkBoxes.add(cb);
        }
        //Add the check boxes and the grid layout to the main frame.
        mainFrame.getContentPane().add(centerPanel);
    }

    public void addActionListener(){

        //Add action Listeners to the Buttons

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tempoUP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tempoDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        GUI_MainClass gui = new GUI_MainClass();
        gui.buildGUI();
    }



}
