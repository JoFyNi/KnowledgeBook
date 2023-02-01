package main.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class OtherClass {
    // Map to store the method to execute for each node
    private static Map<Object, Runnable> nodeMethodMap = new HashMap<>();

    public static void methodToExecute() {
        // Implementation of the method to execute
    }

    public static void main(String[] args) {
        // Create the root node for the tree
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("General");

        // Create a list of nodes
        // In this example, we'll just have one "Algorithms" node with one "Bubblesort" child node
        List<Node> nodes = Arrays.asList(
                new Node("Algorithms", Arrays.asList(
                        new Node("Bubblesort", OtherClass::methodToExecute)
                ))
        );

        // Add the nodes as child nodes to the root node
        for (Node node : nodes) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(node.name);
            rootNode.add(parentNode);
            for (Node child : node.children) {
                parentNode.add(new DefaultMutableTreeNode(child.name));
                nodeMethodMap.put(child.name, child.method);
            }
        }

        // Create the tree with the root node
        JTree sideTree = new JTree(rootNode);

        sideTree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = sideTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = sideTree.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    if (e.getClickCount() == 1) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                        Object userObject = node.getUserObject();
                        Runnable method = nodeMethodMap.get(userObject);
                        if (method != null) {
                            method.run();
                        }
                    }
                }
            }
        });
    }

    // Class to represent a node in the tree
    private static class Node {
        String name;
        List<Node> children;
        Runnable method;

        Node(String name, List<Node> children) {
            this.name = name;
            this.children = children;
        }

        Node(String name, Runnable method) {
            this.name = name;
            this.children = null;
            this.method = method;
        }
    }
}
