package main.recources;

import org.w3c.dom.css.Counter;

import java.io.*;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class AllForOne {

    public AllForOne() {}


    /**
     * Method list of all kind of functions
     * CSV class
     *      CSVReader -> reading .csv files and add them to a ArrayList records
     *      CSVWriter -> write inside .csv files
     *      CSVScanner -> reading a .csv file to check for different parameters
     *
     * Server class
     *      Server -> Server(port)
     *      connect -> create connection points
     *
     * Client class
     *      Client -> Client(port, ip)
     *      connect -> connect to open connection points (server)
     *
     * CommunicationThread Interface
     *  - communication parameter
     *
     */

    static class CSV {
        private List<String[]> lines;
        public void CSVReader(File file) {
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

    static class Server implements CommunicationThread {
        MulticastSocket multicastSocket;
        public Server() {}
        @Override
        public void connect() {
            try {
                ServerSocket serverSocket = new ServerSocket(multicastPORT);
                System.out.println("Server gestartet auf Port " + multicastPORT);

                Socket socket = serverSocket.accept();
                System.out.println("Client verbunden");

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String receivedMessage = in.readUTF();
                System.out.println("Nachricht vom Client empfangen: " + receivedMessage);

                out.writeUTF("Nachricht erhalten");
                System.out.println("Nachricht an Client gesendet");

                in.close();
                out.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class Client implements CommunicationThread {
        Socket socket;
        public Client() {}
        @Override
        public void connect() {
            // Implement server-specific connection logic
            try {
                socket = new Socket(multicastIP, multicastPORT);
                System.out.println("Verbunden mit Server " + multicastIP + " auf Port " + multicastPORT);

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeUTF("Hallo Server");
                System.out.println("Nachricht an Server gesendet");

                String receivedMessage = in.readUTF();
                System.out.println("Nachricht vom Server empfangen: " + receivedMessage);

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
