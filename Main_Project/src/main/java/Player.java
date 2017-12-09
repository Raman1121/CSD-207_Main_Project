package main.java;

import javafx.scene.control.CheckBox;

import javax.sound.midi.*;
import javax.swing.*;
import java.util.ArrayList;

public class Player {

    private Sequence sequence;
    private Sequencer sequencer;
    boolean isPlaying;
    boolean isEmptyNoteEvents;
    private Track track;
    private JFrame frame;
    private int[][] store;
    private float BPM;
    private ArrayList<JCheckBox> checkboxList ;
    private String instrumentNames[];
    private int[] instruments;
    private StringBuilder title;
    private static int[] instrumentCounter={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public Player(ArrayList<JCheckBox> checkBoxList,String[] instrumentsList,int[] instrumentsInt,JFrame mainFrame,StringBuilder builder){
        checkboxList = checkBoxList;
        instrumentNames = instrumentsList;
        instruments = instrumentsInt;
        frame = mainFrame;
        title = builder;
        store = new int[16][16];

    }

    public void setupPlayer() {
        try {
            BPM = 120;
            sequencer = MidiSystem.getSequencer();
            sequence = new Sequence(Sequence.PPQ, 4, 0);

            isPlaying = false;
            isEmptyNoteEvents = true;
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void checkState(){
        for (JCheckBox aCheckboxList : checkboxList) {
            aCheckboxList.addActionListener(e -> {
                if (sequencer.isRunning()) {
                    restartPlayer();
                }
            });
    }
}


    public float getBPM(){
        return BPM;
    }

    public void updateTitle(){
        // @FIXME might be redundant method
        frame.setTitle(title.toString() + " - BPM: " + getBPM());
    }

    public void startPlayer() {

        // start playing
        if (sequencer.isRunning()) {
            stopPlayer();
        }
        try {
//			System.out.println("\nOpened Sequencer"); // @FixME Delete
            sequencer.open();
            track = sequence.createTrack();
            createSong();
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(BPM);
            sequencer.start();
            isPlaying = true;
//			System.out.println("Total events in track = " + track.size()); // @FixME Delete
            if (isEmptyNoteEvents) {
                stopPlayer();
            }
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }



    public void playSaved() {
        if (store == null) {
            JPanel jPanel = new JPanel();
            JOptionPane.showMessageDialog(jPanel,"Nothing stored.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            RetrieveAndPlaySong(store);
        }

    }

    public void stopPlayer() {

        sequence.deleteTrack(track);

        // stop playing
//		System.out.println("\nClosed Sequencer"); // @FixME Delete
        sequencer.close();

        isPlaying = false;
        isEmptyNoteEvents = true;
    }

    public void resetPlayer() {
        stopPlayer();
        BPM = 120;
        updateTitle();

        for (JCheckBox aCheckboxList : checkboxList) {
            aCheckboxList.setSelected(false);
        }
    }

    public void tempoUpPlayer() {
        BPM += 50;
        if (sequencer.isRunning()) {
            restartPlayer();
        }
    }

    public void tempoDownPlayer() {
        BPM -= 50;
        if (sequencer.isRunning()) {
            restartPlayer();
        }
    }

    public void restartPlayer() {
        if (sequencer.isRunning()) {
            stopPlayer();
        }
        if (!sequencer.isRunning()) {
            startPlayer();
        }
    }

    public void createSong() {
        int volume = 127; // Range - 0-127
        // add instruments[chanNo] change event in channel[chanNo]
        track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 127, 0));

        for (int chanNo = 0; chanNo < 16; chanNo++) { // row - instrument [0 - 15] or channel
            for (int j = 0; j < 16; j++) { // column - beat [0 - 15]
                if (checkboxList.get(j + chanNo * 16).isSelected()) {

                    // add Note ON event to channel[chanNo]
                    track.add(makeEvent(ShortMessage.NOTE_ON, 9, instruments[chanNo], volume, j));
//					System.out.println("Added Drum ON - " + instrumentNames[chanNo] + " at Beat #" + j); // @FixME Delete

                    // add Note OFF event
                    track.add(makeEvent(ShortMessage.NOTE_OFF, 9, instruments[chanNo], volume, j + 1));
//					System.out.println("Added Drum OFF - " + instrumentNames[chanNo] + " at Beat #" + (j + 1)); // @FixME Delete

                    // store[chanNo][j]=1;
                    isEmptyNoteEvents = false;
                    instrumentCounter[chanNo]++;
                }
            }

            // @TODO add control change event to beat[15 + noteOffset]
            track.add(makeEvent(ShortMessage.CONTROL_CHANGE, 9, 127, 0, 16));
        }
    }

    public MidiEvent makeEvent(int cmd, int chan, int data1, int data2, long tick) {
        ShortMessage msg = null;
        try {
            msg = new ShortMessage(cmd, chan, data1, data2);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return new MidiEvent(msg, tick);
    }
    public static int[] getInstrumentCounts()
    {
        return instrumentCounter;
    }

    public void RetrieveAndPlaySong(int[][] stored) {

        // start playing
        if (sequencer.isRunning()) {
            stopPlayer();
        }
        try {
//			System.out.println("\nOpened Sequencer"); // @FixME Delete
            int volume = 127; // Range - 0-127
            sequencer.open();
            track = sequence.createTrack();
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 127, 0));
            for (int i = 0; i < 16; i++) { // row - instrument [0 - 15] or channel
                for (int j = 0; j < 16; j++) { // column - beat [0 - 15]
                    checkboxList.get(j + i * 16).setSelected(false);
                    if (stored[i][j]==1) {
                        checkboxList.get(j + i * 16).setSelected(true);
                        // add Note ON event to channel[chanNo]
                        track.add(makeEvent(ShortMessage.NOTE_ON, 9, instruments[i], volume, j));
//					System.out.println("Added Drum ON - " + instrumentNames[chanNo] + " at Beat #" + j); // @FixME Delete

                        // add Note OFF event
                        track.add(makeEvent(ShortMessage.NOTE_OFF, 9, instruments[i], volume, j + 1));
//					System.out.println("Added Drum OFF - " + instrumentNames[chanNo] + " at Beat #" + (j + 1)); // @FixME Delete
                        isEmptyNoteEvents = false;
                    }
                }

                // @TODO add control change event to beat[15 + noteOffset]
                track.add(makeEvent(ShortMessage.CONTROL_CHANGE, 9, 127, 0, 16));
            }

            sequencer.setTempoInBPM(BPM);
            sequencer.start();
            isPlaying = true;
            updateTitle();
//			System.out.println("Total events in track = " + track.size()); // @FixME Delete
            if (isEmptyNoteEvents) {
                stopPlayer();
            }
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }
}
