package SwingWithSwingWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainFrame extends JFrame {
    private JLabel countLabel = new JLabel("0");
    private JLabel statusLabel = new JLabel("Task not completed");
    private JButton startButton = new JButton("start");

    public MainFrame(String title) {
        super(title);
        setLayout(new GridLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        add(countLabel, gc);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        add(statusLabel, gc);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        add(startButton, gc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void start() {
        SwingWorker<Boolean,Integer> worker = new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {

                for (int i=0; i<30; i++) {
                    Thread.sleep(10);
                    System.out.println("Hello: " + i);
                    publish(i);
                }
                return true;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int value = chunks.get(chunks.size() - 1);

                countLabel.setText("Current value: " + value);
            }

            @Override
            protected void done() {
                try {
                    Boolean staus = get();
                    statusLabel.setText("Completed with status: " + staus);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
    }
}


// SwingWorker für FileFinder einsetzten -> für suche der Files (HUD kann weiter verwendet werden während der Suche
