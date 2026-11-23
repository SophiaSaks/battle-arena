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

            System.out.println("Servern har startat"); //loggning INFO
            System.out.println("Servern väntar på client"); //loggning INFO

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("En klient anslöt!"); //loggning INFO

                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true); //behövs ej

                    String message; //det här kan vi förenkla till String message = inputReader.readLine(); och sen istället för while:n här under
                    //kan vi köra if (message != null) ba

                    while ((message = inputReader.readLine()) != null) {
                        if (message.equalsIgnoreCase("QUIT")) { //detta vet jag inte om vi behöver ha? att de ska kunna stänga servern med quit?
                           // outputWriter.print("Hejdå"); //den här tror jag vi kan ta bort
                            break;
                        }

                        Files.writeString(file, message + "\n", StandardOpenOption.CREATE, //viktigt för uppgiften
                                StandardOpenOption.APPEND // viktigt för uppgiften
                        );
                        System.out.println("Sparade meddelandet: " + message); //loggning INFO
                        // outputWriter.println("Sparat"); //behövs ej

                    }
                    System.out.println("Client kopplades ifrån"); //Loggning INFO
                } catch (IOException e) {
                    System.out.println("Fel: " + e.getMessage()); //loggning WARNING
                }
            }
        } catch (IOException e) {
            System.out.println("Kunde inte starta servern : " + e.getMessage()); //loggning WARNING
        }
    }
}