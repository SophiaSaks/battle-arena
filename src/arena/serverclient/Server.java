package arena.serverclient;

import arena.LoggColors;
import arena.gamelogic.BattleService;

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
            ServerSocket serverSocket = new ServerSocket(5000);

            logg.info(LoggColors.GREEN_INFO.getAnsi() +"Server has started" + LoggColors.RESET.getAnsi());
            logg.info(LoggColors.GREEN_INFO + "Server is waiting for client" + LoggColors.RESET);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    logg.info(LoggColors.GREEN_INFO + "Client connected!" + LoggColors.RESET);

                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String message;

                    while ((message = inputReader.readLine()) != null) {

                        Files.writeString(file,
                                message +
                                        "\n",
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND
                        );
                        logg.info(LoggColors.GREEN_INFO + "Saved messaged: " + message + LoggColors.RESET);

                    }
                    logg.info(LoggColors.GREEN_INFO + "Client disconnected" + LoggColors.RESET);
                } catch (IOException e) {
                    logg.warning(LoggColors.YELLOW_WARNING + "Error: " + e.getMessage() + LoggColors.RESET);
                }
            }
        } catch (IOException e) {
            logg.warning(LoggColors.YELLOW_WARNING.getAnsi() + "Kunde inte starta servern : " + e.getMessage() + LoggColors.RESET.getAnsi());
        }
    }
}