
import java.rmi.Naming;
import java.util.Scanner;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;





public class TestApp {
    private static final String KEY = "rmi://pacific-plateau.herokuapp.com/url";

    public static void main(String[] a) {
        try {
            IFibo f = (IFibo) Naming.lookup(KEY);
            System.out.print("Fibonacci of: ");
           
            System.out.println(f.fibonacci(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
