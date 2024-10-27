package TabletsOfStone;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CliTabletsOfStone {

    private static final String _SERVER_ADDRESS = "20.3.85.95"; // "20.3.85.95"
    private static final int _SERVER_PORT = 5025;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello to Tablets of Stone Client!");
        System.out.println("Server ip: " + _SERVER_ADDRESS + ":" + _SERVER_PORT);
        System.out.print("Username: ");
        Scanner console = new Scanner(System.in);
        String username = console.nextLine();
        System.out.println("logging in");

        MsgTabletsOfStone response = SendAndReceiveMessage(new MsgTabletsOfStone(1, username));
        System.out.println(response);

        boolean firstRun = true;
        String message;
        while (true) {
            if (firstRun) {
                message = "status";
                System.out.println();
            } else {
                System.out.print("\n> ");
                message = console.nextLine();
            }
            if (message.equalsIgnoreCase("help")) {
                System.out.println("commands:");
                System.out.println("> history - alias: inbox | get messages sent to you");
                System.out.println("> status - get server status");
                System.out.println("> send - send a message");
                System.out.println("> exit - quit the client");
                System.out.println();
                System.out.println("> hack_logout - send a logout request in the name of someone else");
                System.out.println("> hack_send - send a message in the name of someone else");
                continue;
            }
            if (message.equalsIgnoreCase("history") || message.equalsIgnoreCase("inbox")) {
                MsgTabletsOfStone msg = new MsgTabletsOfStone(3, username);
                response = SendAndReceiveMessage(msg);
                String constructedMessage = ""; String lastSender = "";
                while (response.getStatus() == null || response.getStatus().isEmpty()) {
                    constructedMessage += response.getData();
                    if (constructedMessage.endsWith("\r")) {
                        System.out.println("\n>> From: " + response.getFrom());
                        System.out.println(">> To: " + response.getTo());
                        System.out.println(constructedMessage.substring(0, constructedMessage.length() - 1) + "\n");
                        constructedMessage = "";
                    }
                    lastSender = response.getFrom();
                    response = SendAndReceiveMessage(msg);
                }
                System.out.println(response.getStatus());
                if(constructedMessage.length() > 0) {
                    System.out.println("Generic constructed message: " + constructedMessage);
                    System.out.println("Last sender: " + lastSender);
                }
                continue;
            }
            if (message.equalsIgnoreCase("status")) {
                MsgTabletsOfStone msg = new MsgTabletsOfStone(0, "");
                response = SendAndReceiveMessage(msg);
                System.out.println(response.getStatus());
                if(firstRun) {
                    firstRun = false;
                    System.out.println("Use 'help' to see a list o available commands");
                }
                continue;
            }
            if (message.equalsIgnoreCase("send")) {
                System.out.print("To: ");
                String to = console.nextLine();
                System.out.print("Message: ");
                String msgc = console.nextLine() + "\r";
                int k = 0;
                while (msgc.length() > 0) {
                    k++;
                    MsgTabletsOfStone msg = new MsgTabletsOfStone(username, to,
                            msgc.substring(0, Math.min(msgc.length(), 6)));
                    response = SendAndReceiveMessage(msg);
                    System.out.println(response.getStatus());

                    msgc = msgc.substring(Math.min(msgc.length(), 6));
                }
                System.out.println("The message was splited and sent into " + k + " packets");
                continue;
            }
            if (message.equalsIgnoreCase("exit")) {
                response = SendAndReceiveMessage(new MsgTabletsOfStone(2, username));
                System.out.println(response);
                break;
            }

            if (message.equalsIgnoreCase("hack_logout")) {
                System.out.print("Username: ");
                String tologout = console.nextLine();
                response = SendAndReceiveMessage(new MsgTabletsOfStone(2, tologout));
                System.out.println(response);
                continue;
            }
            if (message.equalsIgnoreCase("hack_send")) {
                System.out.print("From: ");
                String from = console.nextLine();
                System.out.print("To: ");
                String to = console.nextLine();
                System.out.print("Message (max 6): ");
                String msg = console.nextLine();
                response = SendAndReceiveMessage(new MsgTabletsOfStone(from, to, msg));
                System.out.println(response);
                continue;
            }
        }

        System.out.println("Goodbye!");
    }

    static MsgTabletsOfStone SendAndReceiveMessage(MsgTabletsOfStone message)
            throws IOException, ClassNotFoundException {
        Socket socket = new Socket(_SERVER_ADDRESS, _SERVER_PORT);

        OutputStream outStr = socket.getOutputStream();
        ObjectOutputStream objOutStream = new ObjectOutputStream(outStr);

        objOutStream.writeObject(message);

        InputStream inStream = socket.getInputStream();
        ObjectInputStream objInStream = new ObjectInputStream(inStream);
        MsgTabletsOfStone inMessage = (MsgTabletsOfStone) objInStream.readObject();
        return inMessage;
    }
}
