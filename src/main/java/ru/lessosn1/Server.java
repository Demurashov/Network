package ru.lessosn1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8888;


    public static void main(String[] args) {
        //char[]ch=HOST.toCharArray();
        //System.out.println(ch[ch.length-1]);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {

                    System.out.println("Подключен клиент:" + clientSocket.getPort());
                    String in = bufferedReader.readLine();
                    System.out.println("Получено сообщение от клиента: "+in + " Порт: " + clientSocket.getPort());
                    printWriter.println("Ну привет клиент!!!");
                    in = bufferedReader.readLine();
                    //System.out.println(in + " Порт: " + clientSocket.getPort());
                    if (in != null && in.equals("Q")) {
                        System.out.println("Получена команда на отключение!");
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }

}
