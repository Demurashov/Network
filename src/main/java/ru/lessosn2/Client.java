package ru.lessosn2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8888;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);

             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())))) {
            String in = bufferedReader.readLine();
            if (!in.equals("???")) {
                System.out.println("Предыдущий город: " + in);
                System.out.println("Введите название города, начинающееся на последнюю букву предыдущего");
            } else {
                System.out.println("Введите название любого города");
            }
            String output = sc.nextLine();
            System.out.println("Отправлено на сервер: " + output);
            printWriter.println(output);
            System.out.println("Статус: " + bufferedReader.readLine());
        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }
}
