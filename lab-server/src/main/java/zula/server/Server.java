package zula.server;


import zula.common.exceptions.PrintException;
import zula.common.util.InputManager;
import zula.common.util.IoManager;
import zula.server.util.Client;
import zula.server.util.ClientThread;
import zula.server.util.ListManager;
import zula.server.util.SQLCollectionManager;
import zula.server.util.ServerOutputManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;


public final class Server {
    private static final int TIMEOUT = 10;
    private static final Logger SERVERLOGGER = Logger.getLogger("Server logger");
    private static boolean serverStillWorks = true;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int AMOUNT_OF_ARGUMENTS = 5;
    private static final int PORT_ARGUMENT_INDEX = 4;
    private static final int PASSWORD_ARGUMENT_INDEX = 3;
    private static final int USER_ARGUMENT_INDEX = 2;
    private static final int NAME_ARGUMENT_INDEX = 1;
    private static final int PATH_ARGUMENT_INDEX = 0;
    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }


    public static void main(String[] args) {
        ListManager listManager = new ListManager();
        if (args.length != AMOUNT_OF_ARGUMENTS || args[0] == null) {
            return;
        }
        String path = args[PATH_ARGUMENT_INDEX];
        String name = args[NAME_ARGUMENT_INDEX];
        String user = args[USER_ARGUMENT_INDEX];
        String password = args[PASSWORD_ARGUMENT_INDEX];
        int port = Integer.parseInt(args[PORT_ARGUMENT_INDEX]);
        try (Connection connection = DriverManager.getConnection(path + name, user, password)) {
            SQLCollectionManager sqlCollectionManager = new SQLCollectionManager(connection);
            sqlCollectionManager.start(listManager);
            ServerSocket server = new ServerSocket(port);
            while (serverStillWorks) {
                checkForConsoleCommands();
                server.setSoTimeout(TIMEOUT);
                Socket clientSocket;
                try {
                    clientSocket = server.accept();
                } catch (IOException e) {
                    continue;
                }
                IoManager ioManager1 = new IoManager(new InputManager(new InputStreamReader(clientSocket.getInputStream())), new ServerOutputManager(clientSocket.getOutputStream()));
                Client client = new Client(clientSocket, ioManager1, sqlCollectionManager, listManager);
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                client.setObjectInputStream(objectInputStream);
                ClientThread clientThread = new ClientThread(client);
                clientThread.setDaemon(true); //to make exit works
                clientThread.start();
            }
        } catch (SQLException | PrintException e) {
            SERVERLOGGER.severe("Не удалось начать работу");
        } catch (IllegalArgumentException e) {
            SERVERLOGGER.severe("Неверные аргументы");
        } catch (IOException e) {
            SERVERLOGGER.severe("Не удалось запустить сервер");
        }
    }


    public static void checkForConsoleCommands() throws IOException, PrintException {
        //todo ctrl + d
            if (System.in.available() > 0) {
                String command = SCANNER.nextLine();
                if ("exit".equals(command)) {
                    SERVERLOGGER.info("До свидания!");
                    serverStillWorks = false;
                }
            }
    }
}
