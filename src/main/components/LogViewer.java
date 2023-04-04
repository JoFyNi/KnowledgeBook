package main.components;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LogViewer extends JFrame {
    private JTabbedPane tabbedPane;
    private JToolBar logBar; // declare logBar variable
    public String folderPath = "src/main/components/logs";

    public LogViewer() {
        setTitle("Log Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // initialize logBar
        logBar = new JToolBar();
        add(logBar, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    }
                    reader.close();
                    JTextArea textArea = new JTextArea(sb.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    tabbedPane.addTab(file.getName(), scrollPane);

                    // add tabbedPane to logBar
                    logBar.add(tabbedPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        setVisible(true);
    }
}
