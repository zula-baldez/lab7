package zula.gui;

import zula.client.ConnectionManager;
import zula.common.commands.LoginCommand;
import zula.common.commands.RegisterCommand;
import zula.common.data.ResponseCode;
import zula.common.data.ServerMessage;
import zula.common.exceptions.GetServerMessageException;
import zula.common.exceptions.PrintException;
import zula.common.exceptions.SendException;
import zula.common.util.InputManager;
import zula.common.util.IoManager;
import zula.common.util.OutputManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.logging.Logger;

public class ConnectionLogics {
    private static int port;
    private static String ip;
    private static final Logger CLIENTLOGGER = Logger.getLogger("ClientLogger");
    private static final IoManager IO_MANAGER = new IoManager(new InputManager(new InputStreamReader(System.in)), new OutputManager(new OutputStreamWriter(System.out)));
    private static  ConnectionManager connectionManager;

    public String connect(String address, String serverPort) {
        try {
            ip = address;
            try {
                port = Integer.parseInt(serverPort);
                connectionManager = new ConnectionManager(ip, port, IO_MANAGER);
            } catch (IllegalArgumentException e) {
                return "PORT MUST BE A NUMBER";
            }
            try {
                connectionManager.connectToServer();
                CLIENTLOGGER.info("Подключение установлено");
                return  null;
            } catch (IOException e) {
                IO_MANAGER.getOutputManager().write("Не удалось подключиться к серверу");
                CLIENTLOGGER.severe("Ошибка при соединении");
                return "ERROR IN CONNECTION";
            }

         /*   App app = new App(IO_MANAGER, connectionManager);
            try {
                app.startApp();
            } catch (ClassNotFoundException e) {
                IO_MANAGER.getOutputManager().write("Ответ сервера не соответствует протоколу");
            }
            return null;*/
        } catch (PrintException e) {
            return null;
        }
    }



    public String login(String login, String password) {
        connectionManager.setLogin(login);
        connectionManager.setPassword(password);
        ServerMessage serverMessage = null;
        try {
            LoginCommand loginCommand = new LoginCommand();
            connectionManager.sendToServer(loginCommand, new Serializable[]{login, password});
            serverMessage = connectionManager.getMessage();
        } catch (GetServerMessageException | SendException e) {
            e.printStackTrace();
        }
        if (serverMessage.getResponseStatus() == ResponseCode.OK) {
            return null;
        } else {
            return serverMessage.getArguments()[0].toString();
        }
    }

    public String register(String login, String password) {
        connectionManager.setLogin(login);
        connectionManager.setPassword(password);
        ServerMessage serverMessage = null;
        try {
            RegisterCommand registerCommand = new RegisterCommand();
            connectionManager.sendToServer(registerCommand, new Serializable[]{login, password});
            serverMessage = connectionManager.getMessage();
        } catch (GetServerMessageException | SendException e) {
            e.printStackTrace();
        }
        if (serverMessage.getResponseStatus() == ResponseCode.OK) {
            return null;
        } else {
            return serverMessage.getArguments()[0].toString();
        }

    }

    public   ConnectionManager getConnectionManager() {
        return connectionManager;
    }
}
