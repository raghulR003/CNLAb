import java.rmi.Remote;
import java.rmi.RemoteException;
//The remote interface 
public interface GenMe extends Remote {
    public String gen() throws RemoteException;
}
