package Wiki.components;

import Wiki.content.Algorithmen;
import Wiki.content.Multithreading;
import Wiki.content.Test;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WIKI {
    public JPanel MainPanel;
    public JPanel SidePanel;
    public JPanel BottemPanel;
    public JPanel TopPanel;
    public JTextField textField;
    public JEditorPane editPane;
    public JTree sideTree;

    public void main(String[] args) {
        content();
    }
    public JPanel getMainPanel() {
        return MainPanel;
    }
    public void content() {

        //String first = textField.getText();
        //editPane.setText(first + "." + pageOne());
    }

    private void createUIComponents() {
        //sideTree.addSelectionPath(Test.pageOne());
        //sideTree.addSelectionPath(Algorithmen.pageOne());
        //sideTree.addSelectionPath(Multithreading.pageOne());
        DefaultMutableTreeNode General=new DefaultMutableTreeNode("General");
        DefaultMutableTreeNode Test=new DefaultMutableTreeNode("color");
        DefaultMutableTreeNode Multithreading=new DefaultMutableTreeNode("Multithreading");
        DefaultMutableTreeNode Algorithmen=new DefaultMutableTreeNode("Algorithmen");
        General.add(Test);
        General.add(Algorithmen);
        General.add(Multithreading);
        DefaultMutableTreeNode first=new DefaultMutableTreeNode("first");
        DefaultMutableTreeNode blue=new DefaultMutableTreeNode("blue");
        DefaultMutableTreeNode black=new DefaultMutableTreeNode("black");
        DefaultMutableTreeNode green=new DefaultMutableTreeNode("green");
        Test.add(first);Test.add(blue);Test.add(black);Test.add(green);
        sideTree = new JTree(General);

        String selectedValue = null;;
        sideTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editPane.setText(String.valueOf(sideTree.getSelectionPath()));
            }
        });
        //selectedValue = null;
    }
}
