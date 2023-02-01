package main.components;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        createGUI();
    }

    public static void createGUI(){
        WIKI ui = new WIKI();
        JPanel root = ui.MainPanel;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
