import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            // Creating server socket on port 7000
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("Server running on the port: 7000 ...");

            //while (true) {
                Socket socket2 = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket2.getInputStream());
                DataOutputStream ds2 = new DataOutputStream(socket2.getOutputStream());

                while (true) {
                    String message = dis.readUTF();
                    System.out.println("Client: " + message);

                    // Process the message and send a response
                    if (message.equals("Hi")) {
                        ds2.writeUTF("Hello Client!");
                    } else if (message.equals("Time")) {
                        // Send the current time
                        String currentTime = getCurrentTime();
                        ds2.writeUTF(currentTime);
                    } else if (message.equals("OK")) {
                        for(int i = 0; i < 9; i++) {
                            ds2.writeUTF("OK! ");
                        }
                    } else if (message.equals("Exit")) {
                        ds2.writeUTF("Bye");
                        break; // Exit the inner loop after "Exit" command
                    } else {
                        ds2.writeUTF("Invalid command");
                    }
                }
                // Close the socket here after finishing communication with the client
                socket2.close();
           // }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to get the current time in HH:mm:ss format
    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
