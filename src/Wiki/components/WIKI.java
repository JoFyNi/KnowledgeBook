package Wiki.components;

import SearchAlgorithms.BinarySearch.BinarySearch;
import SearchAlgorithms.InterpolationSearch.InterpolationSearch;
import SortingAlgorithms.Bubblesort.BubbleSort;
import SortingAlgorithms.Insertsort.InsertSort;
import SortingAlgorithms.Mergesort.MergeSort;
import SortingAlgorithms.OETsort.OETSort;
import SortingAlgorithms.Quicksort.QuickSort;
import SortingAlgorithms.Ripplesort.RippleSort;
import SortingAlgorithms.Selectsort.SelectSort;
import SortingAlgorithms.Shakersort.ShakerSort;
import SortingAlgorithms.Simplesort.SimpleSort;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.util.*;

public class WIKI {
    public JPanel MainPanel;
    public JPanel SidePanel;
    public JPanel BottemPanel;
    public JPanel TopPanel;
    public JEditorPane editPane;
    public JTree sideTree;
    public JTextArea bottemAreaPanel;
    private JTextField SearchField;

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
                new Node("SortingAlgorithms", Arrays.asList(
                        new Node("BubbleSort", "Text for Bubblesort node"),
                        new Node("InsertSort", "Text for Insertsort node"),
                        new Node("MergeSort", "Text for Mergesort node"),
                        new Node("OETSort", "Text for OETsort node"),
                        new Node("QuickSort", "Text for Quicksort node"),
                        new Node("RippleSort", "Text for Ripplesort node"),
                        new Node("SelectSort", "Text for Selectsort node"),
                        new Node("ShakerSort", "Text for Shakersort node"),
                        new Node("SimpleSort", "Text for Simplesort node")
                )),
                new Node("SearchAlgorithms", Arrays.asList(
                        new Node("BinarySearch", "Text for BinarySearch node"),
                        new Node("InterpolationSearch", "Text for InterpolationSearch node")
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
                // Get the row and path of the selected tree node
                int selRow = sideTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = sideTree.getPathForLocation(e.getX(), e.getY());

                // If a valid row was selected
                if (selRow != -1) {
                    if (e.getClickCount() == 1) {
                        // Get the selected tree node
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                        String callName = selPath.toString();
                        System.out.println(callName);
                        editPane.setText(callName + "\n");
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        // SortingAlgorithms
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        if (node != null && node.getUserObject().equals("BubbleSort")) {
                            BubbleSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\BubbleSort\\Bubble.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("InsertSort")) {
                            InsertSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\InsertSort\\Insert.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("MergeSort")) {
                            MergeSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\MergeSort\\Merge.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("OETSort")) {
                            OETSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\OETSort\\OET.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("QuickSort")) {
                            QuickSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\QuickSort\\Quick.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("RippleSort")) {
                            RippleSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\RippleSort\\Ripple.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("SelectSort")) {
                            SelectSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\SelectSort\\Select.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ShakerSort")) {
                            ShakerSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\ShakerSort\\Shaker.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("SimpleSort")) {
                            SimpleSort.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SortingAlgorithms\\SimpleSort\\Simple.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        // SearchAlgorithms
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        if (node != null && node.getUserObject().equals("BinarySearch")) {
                            BinarySearch.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SearchAlgorithms\\BinarySearch\\BinarySearch.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("InterpolationSearch")) {
                            InterpolationSearch.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\SearchAlgorithms\\InterpolationSearch\\InterpolationSearch.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        // Multithreading
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        if (node != null && node.getUserObject().equals("CallableAndFuture")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("DeadLock")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("InterruptingThreads")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("Latches")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("LowLevelSynchronization")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ProducerConsumer")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ReEntrantLock")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("Semaphores")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("StopAndRun")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("SwingWithSwingWorker")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ThreadPool")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("WaitAndNotify")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("Workers")) {
                            //CallableAndFuture.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\CallableAndFuture\\CallableAndFuture.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        }

/*
                        // reading the Path
                        if (node != null && !node.isRoot()) {
                            // Extract the names of the nodes in the path
                            String methodLink = "";
                            for (Object pathNode : node.getUserObjectPath()) {
                                methodLink += pathNode + ".start();";
                            }
                            // Remove the last "." from the method link
                            methodLink = methodLink.substring(0, methodLink.length() - 1);
                            System.out.println(methodLink);
                            editPane.setText(methodLink);
                        }

 */
                    }
                }
            }
        });
/*
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
                }
                // Check if the user object is an instance of Node
                if (userObject instanceof Node) {
                    Node treeNode = (Node) userObject;
                    // Check if the Node has a corresponding method in the OtherClass
                    Method method = OtherClass.class.getMethod(treeNode.name);
                    if (method != null) {
                        // Create an instance of OtherClass
                        OtherClass otherClass = new OtherClass();
                        // Invoke the method on the OtherClass instance
                        method.invoke(otherClass);
                    }
                }
            }
        }
    }
});


// Add the tree to a scroll pane
JScrollPane treeScrollPane = new JScrollPane(sideTree);

// Add the tree and edit pane to the top panel
TopPanel.add(treeScrollPane, BorderLayout.WEST);
TopPanel.add(editPane, BorderLayout.CENTER);
 */

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
