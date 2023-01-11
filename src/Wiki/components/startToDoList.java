package Wiki.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startToDoList implements ActionListener {
    DefaultListModel<String> toDoListModel = new DefaultListModel<>();
    JFrame toDoFrame;
    JTextField newToDo = new JTextField();
    JButton editButton = new JButton("Edit");
    JButton deleteButton = new JButton("Delete");
    public void actionPerformed(ActionEvent event) {
        // create list to hold ToDo's
        JList<String> toDoList = new JList<>(toDoListModel);
        // create new JFrame for ToDo's
        toDoFrame = new JFrame("ToDo's");
        toDoFrame.setSize(400, 500);
        // create JTextField for new ToDo's
        toDoFrame.add(newToDo, BorderLayout.NORTH);
        // create JList to display existing ToDo's
        toDoFrame.add(toDoList, BorderLayout.CENTER);
        // create button for saving, editing, and deleting ToDo's
        JButton saveButton = new JButton("Save");
        toDoFrame.add(saveButton, BorderLayout.SOUTH);
        // add action listener to save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get text from new ToDo text field and add to list
                String newToDoText = newToDo.getText().trim();
                toDoListModel.addElement(newToDoText);
                toDoList.updateUI();
            }
        });
        // create button for editing ToDo's
        toDoFrame.add(editButton, BorderLayout.SOUTH);
        // add action listener to edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get selected ToDo from JList
                int selectedIndex = toDoList.getSelectedIndex();
                // update text in new ToDo text field to match selected ToDo
                newToDo.setText(toDoList.getSelectedValue());
                // remove selected ToDo from list
                toDoListModel.removeElementAt(selectedIndex);
                toDoList.updateUI();
            }
        });
        // create button for deleting ToDo's
        toDoFrame.add(deleteButton, BorderLayout.SOUTH);
        // add action listener to delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get selected ToDo from JList
                int selectedIndex = toDoList.getSelectedIndex();
                // remove selected ToDo from list
                toDoListModel.removeElementAt(selectedIndex);
                // update JList to remove deleted ToDo
                toDoList.updateUI();
            }
        });
        // set new JFrame visible
        toDoFrame.setVisible(true);
    }
}