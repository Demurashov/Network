package ru.lessosn2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8888;
    private static char[] arrCharOut;
    private static char[] arrCharIn;

    public static void main(String[] args) {
        //char[]ch=HOST.toCharArray();
        //System.out.println(ch[ch.length-1]);
        String output = "???";
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {

                    System.out.println("Подключен клиент:" + clientSocket.getPort());
                    printWriter.println(output);
                    char lastCharOut = output.charAt(output.length() - 1);

                    String in = bufferedReader.readLine();
                    System.out.println("Получено сообщение от клиента: " + in);
                    char firstCharIn = in.charAt(0);

                    if (in != null && in.equals("Q")) {
                        System.out.println("Получена команда на отключение!");
                        break;
                    }
                    if (lastCharOut!='?') {
                        if (lastCharOut == firstCharIn) {
                            printWriter.println("ОK");
                            output = in;
                        } else {
                            printWriter.println("NOT ОK");
                        }
                    }else{
                        printWriter.println("ОK");
                        output = in;
                    }
                }
            }
        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }

}
