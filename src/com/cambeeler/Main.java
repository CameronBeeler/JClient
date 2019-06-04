package com.cambeeler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        try(Socket socket = new Socket("localhost", 5000))
        {
            BufferedReader echoes       = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter    stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            Scanner        scan         = new Scanner(System.in);
            String echoString;
            String response;
            System.out.println("Exit to end the loop");
            do
            {
                System.out.println("Enter String to be Echoed: ");
                echoString = scan.nextLine();
                stringToEcho.println(echoString); // pushes the message to the Socket Server

                if(!echoString.equalsIgnoreCase("exit"))
                {
                    response = echoes.readLine();
                    System.out.println(response);
                }

            }while(!echoString.equalsIgnoreCase("exit"));
        }
        catch (IOException  e)
        {
            System.out.println("Client socket failed " + e.getMessage());
        }

    }
}
