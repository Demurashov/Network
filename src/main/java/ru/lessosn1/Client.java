package ru.lessosn1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8888;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())))) {
            printWriter.println("Привет сервак!!!");
            System.out.println("Получено сообщение от сервера: "+bufferedReader.readLine());
            printWriter.println("Q");
        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }
}
