import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final Logger errorsLogger = LoggerFactory.getLogger("errors");

    private final int port;
    private final ConcurrentHashMap<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private final ExecutorService pool = Executors.newCachedThreadPool();

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server started on port {}", port);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, this);
                pool.submit(handler);
            }
        }
        catch (IOException e) {
            errorsLogger.error("Server error", e);
        }
        finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    errorsLogger.error("Error closing server socket", e);
                }
            }
            pool.shutdown();
        }
    }

    public boolean registerClient(String nickname, ClientHandler handler) {
        if (nickname == null || nickname.isBlank()) return false;
        return clients.putIfAbsent(nickname, handler) == null;
    }

    public void removeClient(String nickname) {
        if (nickname != null) {
            clients.remove(nickname);
            logger.info("User disconnected: {}", nickname);
        }
    }

    public String listUsers() {
        return String.join(",", clients.keySet());
    }

    public void sendBroadcast(String from, String message) {
        String out = String.format("\n[%s -> ALL]:%s", from, message);
        logger.info("Broadcast from {}: {}", from, message);
        clients.forEach((nick, handler) -> handler.send(out));
    }

    public boolean sendPrivate(String from, String to, String message) {
        ClientHandler target = clients.get(to);
        if (target == null) return false;
        String out = String.format("\n[%s -> %s]:%s", from, to, message);
        logger.info("Private from {} to {}: {}", from, to, message);
        target.send(out);
        return true;
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ignored) {
                logger.error("Invalid port argument: {}", args[0]);
            }
        }
        new Server(port).start();
    }
}