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
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class WIKI {
    public JPanel MainPanel;
    public JPanel SidePanel;
    public JPanel BottomPanel;
    public JPanel TopPanel;
    public JEditorPane editPane;
    public JTree sideTree;
    public JTextArea bottomTextAreaPanel;
    public JTextField searchField;
    public JToolBar PanelToolBar;
    public JButton ToolBarBtn;
    // create list to hold ToDo's
    ArrayList<String> toDoList = new ArrayList<>();

    public JPanel getMainPanel() {
        return MainPanel;
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

        // need to be in here (KeyListener == 0 if line is missing)
        searchField = new JTextField();
        searchField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println("key listener");
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    DefaultMutableTreeNode node = findNode(rootNode, searchField.getText());
                    System.out.println(node);
                    if (node != null) {
                        // model -> Path -> from node (clicked)
                        TreeNode[] nodes = ((DefaultTreeModel) sideTree.getModel()).getPathToRoot(node);
                        TreePath path = new TreePath(nodes);
                        sideTree.scrollPathToVisible(path);
                        sideTree.setSelectionPath(path);
                        bottomTextAreaPanel.setText(path.toString());
                        System.out.println("erfolg");
                    } else {
                        JOptionPane.showMessageDialog(null, "Node not found!");
                    }
                }
            }
        });

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
                            //DeadLock.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\DeadLock\\DeadLock.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("InterruptingThreads")) {
                            //InterruptingThreads.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\InterruptingThreads\\InterruptingThreads.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("Latches")) {
                            //Latches.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\Latches\\Latches.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("LowLevelSynchronization")) {
                            //LowLevelSynchronization.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\LowLevelSynchronization\\LowLevelSynchronization.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ProducerConsumer")) {
                            //ProducerConsumer.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\ProducerConsumer\\ProducerConsumer.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ReEntrantLock")) {
                            //ReEntrantLock.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\ReEntrantLock\\ReEntrantLock.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("Semaphores")) {
                            //Semaphores.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\Semaphores\\Semaphores.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("StopAndRun")) {
                            //StopAndRun.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\StopAndRun\\StopAndRun.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("SwingWithSwingWorker")) {
                            //SwingWithSwingWorker.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\SwingWithSwingWorker\\SwingWithSwingWorker.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("ThreadPool")) {
                            //ThreadPool.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\ThreadPool\\ThreadPool.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("WaitAndNotify")) {
                            //WaitAndNotify.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\WaitAndNotify\\WaitAndNotify.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        } else if (node != null && node.getUserObject().equals("Workers")) {
                            //Workers.start();
                            // opens a HTML file on the editPane
                            File file = new File("src\\Multithreading\\Workers\\Workers.html");
                            editPane.setEditable(false);
                            try {
                                // Load the HTML file
                                URL url = new URL("file:///"+file.getAbsolutePath());
                                editPane.setPage(url);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        ToolBarBtn = new JButton("ToDo's");
        ToolBarBtn.addActionListener(new startToDoList());
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

    public DefaultMutableTreeNode findNode(DefaultMutableTreeNode tree, String nodeStr) {
        DefaultMutableTreeNode node = tree; // tree is not used?!
        TreeModel model = sideTree.getModel();
        for (int i = 0; i < model.getChildCount(model.getRoot()); i++) {
            node = (DefaultMutableTreeNode) model.getChild(model.getRoot(), i);
            // return node if node.string is == nodeStr
            if (node.toString().equals(nodeStr)) {
                System.out.println(node);
                return node;
            }
            //  Emulator e ... -> return n (nextElement from DefaultMutableTreeNode
            for (Enumeration e = node.breadthFirstEnumeration(); e.hasMoreElements();) {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
                if (n.toString().equals(nodeStr)) {
                    System.out.println(n);
                    return n;
                }
            }
        }
        System.out.println("fertig?");
        return null;
    }

    // Map to store the text for each node
    public static Map<Object, String> nodeTextMap = new HashMap<>();

}