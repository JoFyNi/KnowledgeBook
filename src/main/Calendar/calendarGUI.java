package main.Calendar;

import javax.swing.*;

public class calendarGUI extends JFrame {
    private JPanel calendarPanel;
    private JPanel conetentPanel;
    public calendarGUI() {
        setContentPane(calendarPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400,250);
        setTitle("Calendar Example");

        // calendar
        //conetentPanel.add(dateChooser);
    }

    public static void main(String[] args) {
        new calendarGUI().setVisible(true);
    }
}