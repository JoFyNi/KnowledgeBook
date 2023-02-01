package main.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class startToDoList implements ActionListener {
    DefaultListModel<String> toDoListModel = new DefaultListModel<>();
    JFrame toDoFrame;
    JTextField newToDo = new JTextField();
    JButton editButton = new JButton("Edit");
    JButton deleteButton = new JButton("Delete");
    JButton saveButton = new JButton("Save");
    // create list to hold ToDo's
    JList<String> toDoList = new JList<>(toDoListModel);
    @Override
    public void actionPerformed(ActionEvent event) {
        loadToDoList();
        toDoList.updateUI();
        toDoFrame = new JFrame("ToDo's");
        toDoFrame.setSize(400, 500);
        toDoFrame.add(newToDo, BorderLayout.NORTH);
        toDoFrame.add(toDoList, BorderLayout.CENTER);
        toDoFrame.add(saveButton, BorderLayout.EAST);
        toDoFrame.add(editButton, BorderLayout.WEST);
        toDoFrame.add(deleteButton, BorderLayout.SOUTH);
        // create button for saving, editing, and deleting ToDo's
        startToDoList toDoList = new startToDoList();
        toDoList.saveToDoList();

        toDoFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                toDoList.saveToDoList();
            }
        });
        newToDo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    String newToDoText = newToDo.getText().trim();
                    toDoListModel.addElement(newToDoText);
                    toDoList.updateUI();
                    toDoList.saveToDoList();
                    newToDo.setText("");
                }
                super.keyPressed(e);
            }
        });
        // add action listener to save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get text from new ToDo text field and add to list
                String newToDoText = newToDo.getText().trim();
                toDoListModel.addElement(newToDoText);
                toDoList.updateUI();
                newToDo.setText("");
            }
        });
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

    private int getSelectedIndex() {
        return 0;
    }

    private String getSelectedValue() {
        return null;
    }

    public void saveToDoList() {
        try {
            // create ObjectOutputStream to write to file
            FileOutputStream fos = new FileOutputStream("ToDoList.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write ToDo list to file
            oos.writeObject(toDoListModel);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadToDoList() {
        try {
            // create ObjectInputStream to read from file
            FileInputStream fis = new FileInputStream("ToDoList.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            // read ToDo list from file
            toDoListModel = (DefaultListModel<String>) ois.readObject();
            // update JList with loaded ToDo's
            toDoList.setModel(toDoListModel);
            ois.close();
        } catch (IOException e) {
            // save new ToDo list if file is not found
            if (e instanceof FileNotFoundException) {
                saveToDoList();
            } else {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateUI() {
        toDoList.repaint();
        toDoList.revalidate();
    }

}