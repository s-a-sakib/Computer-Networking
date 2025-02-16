import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.101", 7000);
            System.out.println("Connected to the server on port 7000...");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Read the user's input from the console
                System.out.print("Enter message for the server (Hi, Time, OK, Exit): ");
                String message = scanner.nextLine();
                dos.writeUTF(message);
                if(message.equals("OK")){
                    for(int i = 0; i < 8; i++){
                        String serverResponse = dis.readUTF();
                        System.out.println("Server: " + serverResponse);
                    }
                }
                // Send the message to the server
                String serverResponse = dis.readUTF();
                System.out.println("Server: " + serverResponse);

                // Read the server's response
                

                // If the server sends "Bye", break the loop and exit the client
                if (serverResponse.equals("Bye")) {
                    break;
                }
            }

            // Close the socket and input/output streams after exiting the loop
            scanner.close();
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
