package exam.socket;

import exam.model.Patient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Callback
{
    public boolean shouldEnd = false;
}

class PatientHandler implements Runnable
{
    private Socket socket;
    private Callback callback;
    private ArrayList<Patient> patients;

    public PatientHandler(Socket socket, Callback callback, ArrayList<Patient> patients)
    {
        this.callback = callback;
        this.socket = socket;
        this.patients = patients;
    }

    @Override
    public void run() {
        try {
            InputStreamReader ois = new InputStreamReader(socket.getInputStream());
            System.out.println("Receiving input");
            BufferedReader reader = new BufferedReader(ois);
            String message = reader.readLine();
            String[] splitMessage = message.split("#");
            String commandIndex = splitMessage[0];
            System.out.println("Command " + splitMessage[0]);

            if (commandIndex.equals("3"))
            {
                System.out.println("Close command");
                callback.shouldEnd = true;
                return;
            }
            List<Patient> result = new ArrayList<>();
            switch (commandIndex) {
                //find patients with some diagnosis
                case "1" -> {
                    String x = splitMessage[1];
                    for (Patient patient : patients) {
                        if (patient.getDiagnosis().equals(x)) {
                            result.add(patient);
                        }
                    }
                    break;
                }
                //find patients with the number of medical card from the interval
                case "2" -> {
                    Integer x = Integer.parseInt(splitMessage[1]);
                    Integer y = Integer.parseInt(splitMessage[2]);
                    for (Patient patient : patients) {
                        if ((patient.getMedCardNumber() >= x && patient.getMedCardNumber() <= y)) {
                            result.add(patient);
                        }
                    }
                    break;
                }
                default -> throw new IllegalStateException("Unexpected value: " + commandIndex);
            }
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
            ois.close();
            oos.close();
            socket.close();
        }
        catch (IOException e) { }
    }
}


public class ServerSocketTask3 {
    private static ServerSocket server;

    private static int port = 9876;

    public static Callback c = new Callback();

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        ArrayList<Patient> patients = new ArrayList<>();

        patients.add(new Patient(0, "Petro", "Petrenko",
                        "Petrovych", "street one", "+380994536785",
                        2314, "cold"));
        patients.add(new Patient(1, "Ivan", "Kovalenko",
                        "Igorovich", "street two", "+380990836785",
                        2014, "flu"));
        patients.add(new Patient(2, "Taras", "Schevchenko",
                        "Grigorovichh", "street one", "+380664536785",
                        3318, "fever"));
        patients.add(new Patient(3, "Irina", "Goncharenko",
                        "Sydorivna", "street four", "+380974536700",
                        2933, "fever"));



        while (!c.shouldEnd) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            PatientHandler handler = new PatientHandler(socket, c, patients);
            handler.run();
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }
}
