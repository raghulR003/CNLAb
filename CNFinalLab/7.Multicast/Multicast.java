import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Multicast{
    private static final String MULTICAST_GROUP_IP="224.255.0.1";
    private static final int PORT=8121;

    public static void main(String args[]){
        try{
            Scanner sc=new Scanner(System.in);
            System.out.print("Enter username: ");
            String name=sc.nextLine();
            InetAddress group=InetAddress.getByName(MULTICAST_GROUP_IP);
            MulticastSocket socket=new MulticastSocket(PORT);
            socket.joinGroup(group);

            Thread senderThread=new Thread(()->{
                try{
                while(true){
                    //System.out.print("Enter message: ");
                    String message=sc.nextLine();
                    message=name+" said: "+message;
                    byte[] buffer=message.getBytes();
                    DatagramPacket packet=new DatagramPacket(buffer, buffer.length,group, PORT);
                    socket.send(packet);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            });

            Thread receiverThread=new Thread(()->{
                try {
                    byte[] buffer=new byte[1024];
                    DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
                    while(true){
                        socket.receive(packet);
                        String receivedMessage=new String(packet.getData(),0,packet.getLength());
                        if (!receivedMessage.startsWith(name + ": ")) {
                            System.out.println(receivedMessage);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            senderThread.start();
            receiverThread.start();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}