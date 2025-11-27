Client:
import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        int clientPort = 998;

        try {
            aSocket = new DatagramSocket(clientPort);
            byte[] buf = new byte[1000];

            DatagramPacket data = new DatagramPacket(buf, buf.length);
            System.out.println("Waiting for server...\n");

            aSocket.receive(data);

            System.out.println("\nmsg: " +
                new String(data.getData(), 0, data.getLength()));
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if (aSocket != null)
                aSocket.close();
            System.out.println("Socket closed.");
        }
    }
}



Server:
import java.net.*;
import java.util.*;
import java.io.*;

public class UDPServer {
    public static void main(String args[]) {

        DatagramSocket aSocket = null;
        Scanner scn = new Scanner(System.in);
        int serverPort = 999;

        System.out.println("Server Ready\n Waiting for connection....\n");

        try {
            aSocket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[1000];

            System.out.println("\nEnter message to be sent:");
            String str = scn.nextLine();

            buffer = str.getBytes();

            DatagramPacket data = new DatagramPacket(
                buffer,
                buffer.length,
                InetAddress.getLocalHost(),
                998
            );

            aSocket.send(data);
        }
        catch (SocketException e) {
            System.out.println("Socket:" + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Io:" + e.getMessage());
        }
        finally {
            System.out.println("\nMessage sent\nConnection terminated");
            if (aSocket != null)
                aSocket.close();
            scn.close();
        }
    }
}
