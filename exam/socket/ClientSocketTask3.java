package exam.socket;

import exam.model.Patient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientSocketTask3 {
    private static int port = 9876;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scan = new Scanner(System.in);
        while (true)
        {
            System.out.println("Choose option:\n"
                    + "1 - output Patients with diagnosis X\n"
                    + "2 - output Patients with medical card number in interval of X and Y\n"
                    + "3 - exit");
            socket = new Socket(host.getHostName(), port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            int commandIndex = scan.nextInt();
            if (commandIndex == 3)
            {
                socket = new Socket(host.getHostName(), port);
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Sending close Request");
                oos.writeInt(commandIndex); oos.flush();
                break;
            }
            switch (commandIndex) {
                case 1 -> {
                    System.out.println("Enter X:");
                    String x = scan.nextLine();
                    String message = "" + commandIndex + "#" + x;
                    oos.writeBytes(message);
                    oos.flush();
                    break;
                }
                case 2 -> {
                    System.out.println("Enter X:");
                    int x = scan.nextInt();
                    System.out.println("Enter Y:");
                    int y = scan.nextInt();
                    String message = "" + commandIndex + "#" + x + "#" + y;
                    oos.writeBytes(message);
                    oos.flush();
                    break;
                }
            }
            System.out.println("Results: ");
            ois = new ObjectInputStream(socket.getInputStream());
            ArrayList<Patient> results = (ArrayList<Patient>) ois.readObject();
            for (Patient patient: results)
            {
                System.out.println(patient.toString());
            }
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
        oos.writeInt(1);
        System.out.println("Shutting down client!!");
    }
}
