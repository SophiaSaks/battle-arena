package arena.serverclient;

import arena.LoggColors;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {
    private static final Logger logg = Logger.getLogger(Client.class.getName());
    public void saveResults(String result) {
        try (Socket socket = new Socket("localhost", 5001)) {
            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);

            logg.info(LoggColors.GREEN_INFO.color("Connected to server"));

            outputWriter.println(result);

            logg.info(LoggColors.GREEN_INFO.color("Result sent to server"));

        } catch (IOException e) {
            logg.warning(LoggColors.YELLOW_WARNING.color("Connection refused" + e.getMessage()));
        }
    }
}