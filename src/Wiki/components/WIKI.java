package Wiki.components;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     *  createUIComponents get called bye the GUI (WIKI.form)
     *  specific information and changes for J
     */
    public void createUIComponents() {
        // Create the root node for the tree
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("General");

        // Create a list of nodes
        List<Node> nodes = Arrays.asList(
                new Node("Algorithms", Arrays.asList(
                        new Node("Bubblesort", "Text for Bubblesort node"),
                        new Node("Insertsort", "Text for Insertsort node"),
                        new Node("Mergesort", "Text for Mergesort node"),
                        new Node("OETsort", "Text for OETsort node"),
                        new Node("Quicksort", "Text for Quicksort node"),
                        new Node("Ripplesort", "Text for Ripplesort node"),
                        new Node("Selectsort", "Text for Selectsort node"),
                        new Node("Shakersort", "Text for Shakersort node"),
                        new Node("Simplesort", "Text for Simplesort node")
                )),
                new Node("Multithreading", Arrays.asList(
                        new Node("CallableAndFuture", "Text for CallableAndFuture node"),
                        new Node("DeadLock", "Text for DeadLock node"),
                        new Node("InterruptingThreads", "Text for InterruptingThreads node"),
                        new Node("Latches", "Text for Latches node"),
                        new Node("LowLevelSynchronization", "Text for LowLevelSynchronization node"),
                        new Node("ProducerConsumer", "Text for ProducerConsumer node"),
                        new Node("ReEntrantLock", "Text for ReEntrantLock node"),
                        new Node("Semaphores", "Text for Semaphores node"),
                        new Node("StopAndRun", "Text for StopAndRun node"),
                        new Node("SwingWithSwingWorker", "Text for SwingWithSwingWorker node"),
                        new Node("SynchronizedMultiThreading", "Text for SynchronizedMultiThreading node"),
                        new Node("ThreadPool", "Text for ThreadPool node"),
                        new Node("WaitAndNotify", "Text for WaitAndNotify node"),
                        new Node("Workers", "Text for Workers node")
                ))
        );
        // Add the nodes as child nodes to the root node
        for (Node node : nodes) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(node.name);
            rootNode.add(parentNode);
            for (Node child : node.children) {
                parentNode.add(new DefaultMutableTreeNode(child.name));
            }
        }
        // Create the tree with the root node
        sideTree = new JTree(rootNode);
        Map<String, String> methodMap = new HashMap<>();
        methodMap.put("Bubblesort", "bubblesortMethod");
        methodMap.put("Insertsort", "insertsortMethod");
        methodMap.put("Mergesort", "mergesortMethod");
        // and so on for all the nodes


        sideTree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = sideTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = sideTree.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    if (e.getClickCount() == 1) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                        Object userObject = node.getUserObject();
                        String text = nodeTextMap.get(userObject);
                        if (text != null) {
                            editPane.setText(text);
                            System.out.println(text);
                        }
                        // Check if the user object is an instance of Node
                        if (userObject instanceof Node) {
                            Node treeNode = (Node) userObject;
                            // Check if the Node has a corresponding method in the OtherClass
                            Method method = null;
                            try {
                                method = OtherClass.class.getMethod(treeNode.name);
                            } catch (NoSuchMethodException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (method != null) {
                                // Create an instance of OtherClass
                                OtherClass otherClass = new OtherClass();
                                // Invoke the method on the OtherClass instance
                                try {
                                    method.invoke(otherClass);
                                    System.out.println(otherClass);
                                } catch (IllegalAccessException ex) {
                                    throw new RuntimeException(ex);
                                } catch (InvocationTargetException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                }
            }
        });


    }

    private static class Node {
        String name;
        List<Node> children;
        DefaultMutableTreeNode treeNode;

        Node(String name, List<Node> children) {
            this.name = name;
            this.children = children;
            this.treeNode = new DefaultMutableTreeNode(this);
        }

        Node(String name, String text) {
            this.name = name;
            this.children = null;
            this.treeNode = new DefaultMutableTreeNode(this);
            nodeTextMap.put(this, text);
        }
    }

    // Map to store the text for each node
    public static Map<Object, String> nodeTextMap = new HashMap<>();

}
