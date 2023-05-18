import java.rmi.*;
public class Client {
    public static void main(String args[]){
        try{
            GenMe stub=(GenMe)Naming.lookup("rmi://localhost:5011/obj");
            System.out.println(stub.gen());
        }
        catch(Exception e){System.out.println(e);}
    }
}
