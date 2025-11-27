//Server:
import java.util.*;
import java.net.*;
import java.io.*;

public class tcpserver {
    public static void main(String args[]) {

        try {
            ServerSocket s = new ServerSocket(998);
            System.out.println("server ready \n waiting for connection \n");

            Socket s1 = s.accept();
            DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
            DataInputStream dis = new DataInputStream(s1.getInputStream());

            System.out.println(dis.readUTF());
            dos.writeUTF("connected to server \n");

            String path = dis.readUTF();
            System.out.println("\nrequest received \n processing.......");

            try {
                File myfile = new File(path);
                Scanner sc = new Scanner(myfile);

                String st = sc.nextLine();
                st = "\n the context of file is \n " + st;

                while (sc.hasNextLine()) {
                    st = st + "\n" + sc.nextLine();
                }

                dos.writeUTF(st);
                dos.close();
                s1.close();
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("\n error...\n file not found");
                dos.writeUTF("error \n file not found");
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            System.out.println("\n connection terminated");
        }
    }
}




//Client:
import java.util.*;
import java.net.*;
import java.io.*;

public class tcpclient {
    public static void main(String args[]) {
        try {
            Scanner sc = new Scanner(System.in);
            Socket s = new Socket("localhost", 998);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            dos.writeUTF("connected to 127.0.0.1 \n");
            System.out.println(dis.readUTF());

            System.out.println("\nEnter the full path of the file to be displayed:");
            String path = sc.nextLine();

            dos.writeUTF(path);

            System.out.println(dis.readUTF());

            dis.close();
            dos.close();
            s.close();
            sc.close();
        } 
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
