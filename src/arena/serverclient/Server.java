package arena.serverclient;

import arena.LoggColors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class Server {
    private static final Logger logg = Logger.getLogger(Server.class.getName());

    static void main(String[] args) {
        Path file = Path.of("results.txt");

        try {
            ServerSocket serverSocket = new ServerSocket(5001);
            logg.info(LoggColors.GREEN_INFO.color("Server has started")) ;
            logg.info(LoggColors.GREEN_INFO.color("Server is waiting for client")) ;

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    logg.info(LoggColors.GREEN_INFO.color("Client connected!"));

                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String message;

                    while ((message = inputReader.readLine()) != null) {

                        Files.writeString(file,
                                message +
                                        "\n",
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND
                        );

                        logg.info(LoggColors.GREEN_INFO.color("Saved messaged: "));
                    }
                    logg.info(LoggColors.GREEN_INFO.color("Client disconnected"));
                } catch (IOException e) {
                    logg.warning(LoggColors.YELLOW_WARNING.color("Error: " + e.getMessage()));
                }
            }
        } catch (IOException e) {
            logg.warning(LoggColors.YELLOW_WARNING.color("Kunde inte starta servern : " + e.getMessage()));
        }
    }
}