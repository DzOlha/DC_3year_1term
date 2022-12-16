package exam.rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class ClientRmiTask3 {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        int choice = 1000;
        Scanner in = new Scanner(System.in);
        try {
            RMIServer st = (RMIServer) Naming.lookup("//localhost:123/exam");
            System.out.println("Choose option:\n"
                    + "1 - output Patients with diagnosis X\n"
                    + "2 - output Patients with medical card number in interval of X and Y\n"
                    + "3 - exit");
            choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter X:");
                    String x = in.nextLine();
                    System.out.println(st.displayPatientsWithDiagnosis(x));
                }
                case 2 -> {
                    System.out.print("Enter X value: ");
                    Integer x = in.nextInt();
                    System.out.print("Enter Y value: ");
                    Integer y = in.nextInt();
                    System.out.println(st.displayWithMedicalCardNumberInInterval(x, y));
                }
            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}