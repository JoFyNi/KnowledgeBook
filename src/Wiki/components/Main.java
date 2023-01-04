package Wiki.components;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        createGUI();
        /**
         * seite wählen je nach thema
         * bsp. TextField.getText(hier Thema wählen(name)).firstPage()
         * bei allen gleiche bezeichnung für System bs. pageOne(), pageTwo(),....
         */
    }

    public static void createGUI(){
        WIKI ui = new WIKI();
        JPanel root = ui.MainPanel;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.setSize(400,300);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
