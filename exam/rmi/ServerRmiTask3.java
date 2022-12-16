package exam.rmi;

import exam.model.Patient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

interface RMIServer extends Remote {
    ArrayList<Patient> displayPatientsWithDiagnosis(String x);
    ArrayList<Patient> displayWithMedicalCardNumberInInterval(Integer x, Integer y);
}


public class ServerRmiTask3 {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(123);
        RMIServer service = new Service();
        registry.rebind("exam", service);
        System.out.println("Server started!");
    }

    static class Service extends UnicastRemoteObject implements RMIServer {
        List<Patient> patients;

        Service() throws RemoteException {
            super();
            patients = new ArrayList<>();

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


        }

        @Override
        public ArrayList<Patient> displayPatientsWithDiagnosis(String x) {
            ArrayList<Patient> results = new ArrayList<>();
            for (Patient patient : patients) {
                if (patient.getDiagnosis().equals(x)) {
                    results.add(patient);
                }
            }
            return results;
        }

        @Override
        public ArrayList<Patient> displayWithMedicalCardNumberInInterval(Integer x, Integer y) {
            ArrayList<Patient> results = new ArrayList<>();
            for(Patient patient: patients) {
                if(patient.getMedCardNumber() >= x && patient.getMedCardNumber() <= y) {
                    results.add(patient);
                }
            }
            return results;
        }
    }
}
