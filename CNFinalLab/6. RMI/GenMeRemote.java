import java.rmi.server.*;
import java.rmi.*;
import java.util.*;

public class GenMeRemote extends UnicastRemoteObject implements GenMe {
    GenMeRemote() throws RemoteException{
        super();
    }
    public String gen(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name=sc.nextLine();
        return name;
    }
}
