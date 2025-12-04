import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private static final Logger errorsLogger = LoggerFactory.getLogger("errors");

    private final Socket socket;
    private final Server server;
    private String nickname;
    private PrintWriter out;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            this.out = writer;
            writer.println("ENTER_NICK");
            String nick = in.readLine();
            if (nick == null || nick.isBlank()) {
                writer.println("Ошибка | Некорректный никнейм");
                socket.close();
                return;
            }

            if (!server.registerClient(nick, this)) {
                writer.println("Ошибка | Никнейм уже используется");
                socket.close();
                return;
            }

            this.nickname = nick;
            writer.println("Добро пожаловать, " + nickname);
            logger.info("User connected: {}", nickname);

            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("LIST")) {
                    writer.println("USERS|" + server.listUsers());
                    continue;
                }
                if (line.equals("QUIT")) {
                    break;
                }
                if (line.startsWith("PRIVATE|")) {
                    String[] parts = line.split("\\|", 3);
                    if (parts.length < 3) {
                        writer.println("Ошибка | Неправильный формат личного сообщения");
                        continue;
                    }
                    String to = parts[1];
                    String msg = parts[2];
                    boolean ok = server.sendPrivate(nickname, to, msg);
                    if (!ok) writer.println("Ошибка | Пользователь не найден: " + to);
                    continue;
                }
                if (line.startsWith("BROADCAST|")) {
                    String msg = line.substring("BROADCAST|".length());
                    server.sendBroadcast(nickname, msg);
                    continue;
                }

                writer.println("Ошибка | Неправильная команда");
            }

        } catch (IOException e) {
            errorsLogger.error("Client handler error for {}", nickname, e);
        } finally {
            try {
                socket.close();
            }
            catch (IOException ignored) {
                logger.warn("Error closing socket for {}", nickname, ignored);
            }
            server.removeClient(nickname);
        }
    }

    public void send(String text) {
        if (out != null) out.println(text);
    }
}