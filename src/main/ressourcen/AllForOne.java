package main.recources;

import org.w3c.dom.css.Counter;

import java.io.*;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *  This class is the main class in the package and contains methods to interact with .csv files and network communication.
 *  It contains classes for reading and writing .csv files, starting a server and connecting to a server.
 *  CommunicationThread interface is also part of this class for communication purposes.
 */
public class AllForOne {
    public AllForOne() {}

    /**
     * Method list of all kind of functions link
     * CSV class:
     * - CSVReader -> reading .csv files and add them to a ArrayList records
     * - CSVWriter -> write inside .csv files
     * - CSVScanner -> reading a .csv file to check for different parameters
     * Server class:
     * - Server -> Server(port)
     * - connect -> create connection points
     * Client class:
     * - Client -> Client(port, ip)
     * - connect -> connect to open connection points (server)
     * CommunicationThread Interface:
     * - communication parameter
     */

    static final class CSV {
        // CSV Klasse
        // die Klasse CSVScanner sucht in einer CSV-Datei nach einem bestimmten Suchbegriff
        // die Methode getRecord gibt einen bestimmten Datensatz zurück
        // die Methode getLines gibt die gesamte Liste von Datensätzen zurück

        private List<String[]> lines;
        public void CSVReader(File file) {
            /**
             * Reads the content of a .csv file and adds it to an ArrayList of records.
             * @param file the .csv file to be read.
             */
            if (file.isFile()) {
                List<List<String>> records = new ArrayList<>();
                try (BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = csvReader.readLine()) != null) {
                        String[] values = line.split(" "); // or ";" or "." or ":"
                        records.add(Arrays.asList(values));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(records);
            }
        }

        public void CSVWriter(File file, String content) {
            /**
             * Writes content to a .csv file.
             * @param file the .csv file to be written to.
             * @param content the content to be written.
             */
            List<Object[][]> requestsPending = new ArrayList<>();
            String[] splitCommand = content.split(" ");
            String first = splitCommand[0];
            String second = splitCommand[1];
            String third = splitCommand[2];
            // für mehr columns [i] erhöhen (Strings fortführen)
            if (file.isFile()) {
                List<List<String>> rows = Arrays.asList();
                try {
                    FileWriter csvWriter = new FileWriter(file);
                    Object[][] data = new Object[rows.size()][3];
                    for (int i = 0; i < rows.size(); i++) {
                        data[i][0] = rows.get(i).add(first);
                        data[i][1] = rows.get(i).add(second);
                        data[i][2] = rows.get(i).add(third);
                        requestsPending.add(data);
                    }

                    csvWriter.append(first);
                    csvWriter.append(",");
                    csvWriter.append(second);
                    csvWriter.append(",");
                    csvWriter.append(third);
                    csvWriter.append("\n");

                    for (List<String> rowData : rows) {
                        csvWriter.append(String.join(",", rowData));
                        csvWriter.append("\n");
                    }
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // Constructor zur Initialisierung des CSV-Scanners
        public void CSVScanner(File file, String searchTerm) {
            String fileString = file.getAbsolutePath();
            String searchFor;
            if (file.isFile()) {
                List<List<String>> records = new ArrayList<>();
                try {
                    Scanner scanner = new Scanner(new File(fileString));
                    scanner.useDelimiter(";");
                    while (scanner.hasNext()) {
                        searchFor = scanner.next();
                        if (searchFor.equals(searchTerm)) {
                            System.out.print(searchTerm + " gefunden!");
                        }
                    }
                    scanner.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(records);
            }
        }
        public String getRecord(int zeile, int spalte) {
            return lines.get(zeile - 1)[spalte - 1];
        }

        public List<String[]> getLines() {
            return lines;
        }
    }
    // Server Klasse implementiert das CommunicationThread Interface
    // diese Klasse ist dafür zuständig, Verbindungen zu Clients zu akzeptieren
    // und Nachrichten zu empfangen und zu senden
    static class Server implements CommunicationThread {
        // MulticastSocket member variable
        MulticastSocket multicastSocket;
        public Server() {}
        // Überschreibt connect() Methode aus dem Interface
        @Override
        public void connect() {
            try {
                // Erstellt ein ServerSocket auf dem angegebenen Port
                ServerSocket serverSocket = new ServerSocket(multicastPORT);
                System.out.println("Server gestartet auf Port " + multicastPORT);
                // Blockiert bis ein Client eine Verbindung herstellt
                Socket socket = serverSocket.accept();
                System.out.println("Client verbunden");
                // Input- und Outputstreams für die Kommunikation mit dem Client
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                // Empfange Nachricht vom Client
                String receivedMessage = in.readUTF();
                System.out.println("Nachricht vom Client empfangen: " + receivedMessage);
                // Sende Antwort an Client
                out.writeUTF("Nachricht erhalten");
                System.out.println("Nachricht an Client gesendet");
                // Schließe Streams und Verbindung
                in.close();
                out.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // Client Klasse implementiert das CommunicationThread Interface
    // diese Klasse ist dafür zuständig, eine Verbindung zu einem Server herzustellen
    // und Nachrichten zu senden und zu empfangen
    static class Client implements CommunicationThread {
        // Socket member variable
        Socket socket;
        public Client() {}
        // Überschreibt connect() Methode aus dem Interface
        @Override
        public void connect() {
            // Implement server-specific connection logic
            try {
                // Erstelle Verbindung zum Server auf der angegebenen IP und dem angegebenen Port
                socket = new Socket(multicastIP, multicastPORT);
                System.out.println("Verbunden mit Server " + multicastIP + " auf Port " + multicastPORT);
                // Input- und Outputstreams für die Kommunikation mit dem Server
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                // Sende Nachricht an Server
                out.writeUTF("Hallo Server");
                System.out.println("Nachricht an Server gesendet");
                // Empfange Nachricht vom Serevr
                String receivedMessage = in.readUTF();
                System.out.println("Nachricht vom Server empfangen: " + receivedMessage);
                // Schließe Streams und Verbindung
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static interface CommunicationThread {
        public static final String multicastIP = "192.168.173.89";
        public static final int multicastPORT = 3344;
        public static final int messageValue = 1024;
        public static final long breakTime = 3000;
        public static final String connectionOnline = "connected";
        public static final String connectionBreak = "lost connection";

        void connect();
    }


    public void counter() {
        Counter counter = new Counter() {
            @Override
            public String getIdentifier() {
                return null;
            }

            @Override
            public String getListStyle() {
                return null;
            }

            @Override
            public String getSeparator() {
                return null;
            }
        };
    }
}
