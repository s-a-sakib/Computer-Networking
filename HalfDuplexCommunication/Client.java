import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String fucks[]){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input Server IP: ");
            String IP = scanner.nextLine();
            Socket socket = new Socket(IP,7000);
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            while(true){
                System.out.print("You: ");
                String message = scanner.nextLine();
                dos.writeUTF(message);
                String serverResponse = dis.readUTF();
                System.out.println("Server: " + serverResponse);
                if(serverResponse.equals("Bye")){
                    break;
                }
            }
            scanner.close();
            dis.close();
            dos.close();
            socket.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
