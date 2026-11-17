package arena.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Server {
    static void main(String[] args) {
        Path file = Path.of("results.txt");

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Servern har startat");
            System.out.println("Servern väntar på client");

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("En klient anslöt!");
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);

                    String message;

                    while ((message = inputReader.readLine()) != null) {
                        if (message.equalsIgnoreCase("QUIT")) {
                            outputWriter.print("Hejdå");
                            break;
                        }

                        Files.writeString(file, message + "\n", StandardOpenOption.CREATE, //viktigt för uppgiften
                                StandardOpenOption.APPEND // viktigt för uppgiften
                        );
                        System.out.println("Sparade meddelandet: " + message);
                        outputWriter.println("Sparat");

                    }
                    System.out.println("Client kopplades ifrån");
                } catch (IOException e) {
                    System.out.println("Fel: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Kunde inte starta servern : " + e.getMessage());
        }
    }
}