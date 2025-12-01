import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);

        String serverMsg = in.readLine();
        if ("ENTER_NICK".equals(serverMsg)) {
            System.out.print("Введите никнейм: ");
            String nick = sc.nextLine();
            out.println(nick);

            String response = in.readLine();
            if (response != null && response.startsWith("ERROR | ")) {
                System.out.println(response.substring(6));
                close();
                return;
            }
            System.out.println(response);
        }

        Thread reader = new Thread(() -> {
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException ignored) {
            }
        });
        reader.setDaemon(true);
        reader.start();

        printHelp();

        while (true) {
            System.out.print("Команда (w=лич, a=все, l=список, q=выход, h=помощь): ");
            String cmd = sc.nextLine().trim();

            if (cmd.equalsIgnoreCase("q")) {
                out.println("QUIT");
                break;
            }

            if (cmd.equalsIgnoreCase("l")) {
                out.println("LIST");
                continue;
            }

            if (cmd.equalsIgnoreCase("h")) {
                printHelp();
                continue;
            }

            if (cmd.equalsIgnoreCase("a")) {
                System.out.print("Сообщение для всех: ");
                String msg = sc.nextLine();
                out.println("BROADCAST|" + msg);
                continue;
            }

            if (cmd.equalsIgnoreCase("w")) {
                out.println("LIST");
                System.out.println("Ожидаем список пользователей от сервера...");

                System.out.print("Кому отправить: ");
                String to = sc.nextLine().trim();
                if (to.isEmpty()) {
                    System.out.println("Ник не может быть пустым!");
                    continue;
                }

                System.out.print("Текст: ");
                String text = sc.nextLine();
                if (text.isEmpty()) {
                    System.out.println("Сообщение не может быть пустым!");
                    continue;
                }

                out.println("PRIVATE|" + to + "|" + text);
                continue;
            }
            System.out.println("Неизвестная команда. Нажмите h для помощи.");
        }

        close();
    }

    private void printHelp() {
        System.out.println(
                "Команды:\n" +
                        "  w - личное сообщение\n" +
                        "  a - широковещательное сообщение\n" +
                        "  l - получить список пользователей\n" +
                        "  q - выход\n" +
                        "  h - помощь"
        );
    }

    private void close() {
        try {
            if (socket != null) socket.close();
        } catch (IOException ignored) {}
        System.out.println("Соединение закрыто.");
    }

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8080;

        if (args.length >= 1) host = args[0];
        if (args.length >= 2) port = Integer.parseInt(args[1]);

        new Client(host, port).start();
    }
}