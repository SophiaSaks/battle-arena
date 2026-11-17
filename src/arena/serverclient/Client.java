package arena.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void saveResults(String result) {
        try (Socket socket = new Socket("localhost", 5000)) {
            Scanner scanner = new Scanner(System.in);

            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Ansluten till servern");

            // while (true) {
            outputWriter.println(result);

            String response = inputReader.readLine();

            // if (response == null) {
                // System.out.println("Servern kopplades ifrån");
                // break;
            // }
            System.out.println("Server: " + response);
            // }
        } catch (IOException e) {
            System.out.println("connection refused");
            e.printStackTrace();
        }
    }
}