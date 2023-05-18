import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ConcurrentServer {
    public static void main(String args[]){
        try{
            ServerSocket serversocket=new ServerSocket(5023);
            //Server started, listening on 5023
            while(true){
                Socket clientSocket=serversocket.accept();
                //New client accepted
                ClientHandler clienthandler=new ClientHandler(clientSocket);
                Thread thread=new Thread(clienthandler);
                thread.start();
            }
        }
        catch(Exception e){System.out.println(e);}
    }
}

class ClientHandler extends Thread{
    private Socket clientsocket;
    ClientHandler(Socket clientsocket){
        this.clientsocket=clientsocket;
    }
    public void run(){
        try{
        BufferedReader rd=new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        String clientMessage=rd.readLine();
        System.out.println("Received from client: "+clientMessage);

        String response="Hello from Server!";
        PrintWriter wrt=new PrintWriter(clientsocket.getOutputStream(),true);
        wrt.println(response);

        wrt.close();
        rd.close();
        clientsocket.close();
        System.out.println("Client disconnected!");
    }
    catch(Exception e){System.out.println(e);}
}
}