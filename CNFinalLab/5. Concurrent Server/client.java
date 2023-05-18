import java.io.*;
import java.net.*;
public class client {
    public static void main(String args[]){
        try{
        Socket clientsocket=new Socket("localhost",5023);
        System.out.println("Connected to Server!");

        String msg="Hello from client!";
        PrintWriter wrt=new PrintWriter(clientsocket.getOutputStream(),true);
        wrt.println(msg);

        BufferedReader rd=new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        String serverResponse=rd.readLine();
        System.out.println("Response from server: "+serverResponse);

        wrt.close();
        rd.close();
        clientsocket.close();
        }
        catch(Exception e){}
    }
}
