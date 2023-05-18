import java.rmi.*;

public class Server {
    public static void main(String args[]){
        try{
            GenMe stub=new GenMeRemote();
            Naming.rebind("rmi://localhost:5011/obj", stub);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
