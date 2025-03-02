import java.io.*;
import javax.net.ssl.*;
import java.time.LocalDateTime;
import java.util.*;
import java.net.NetworkInterface;
import java.net.SocketException;

class SmtpMailingWithMacAddress{
    private static DataOutputStream dos;
    public static BufferedReader br;
    public static void main(String[] args){
        try{
            //Code for mac address:
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            byte[] mac = networkInterface.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for(byte b: mac){
                sb.append(String.format("%02X:", b));
            }

            sb.deleteCharAt(sb.length()-1);
            System.out.println("MAC Address: " + sb.toString());
            Code for smtp server
            SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String email = "Your Email";
            String pass = "Your Password";

            String username = new String(Base64.getEncoder().encode(email.getBytes()));
            String password = new String(Base64.getEncoder().encode(pass.getBytes()));

            send("EHLO smtp.gmail.com\r\n");
            for(int i = 1; i <= 9; i++){
                System.out.println("Server : " + br.readLine());
            }
            send("AUTH LOGIN\r\n");
            System.out.println("Server : " + br.readLine());

            send(username + "\r\n");   
            System.out.println("Server : " + br.readLine());

            send(password + "\r\n");
            System.out.println("Server : " + br.readLine());     

            send("MAIL FROM:<s2110976109@ru.ac.bd>\r\n"); // Modify this line with your email
            System.out.println("Server : " + br.readLine());

            send("RCPT TO:<shahrearalsakib16@gmail.com>\r\n"); // Modify this line with your email
            System.out.println("Server : " + br.readLine());

            send("DATA\r\n");
            send("FROM: s2110976109@ru.ac.bd\r\n"); // Modify this line with your email
            send("TO: shahrearalsakib16@gmail.com\r\n"); // Modify this line with your email
            send("Subject: Lab Testing  \r\n");
            send("2110976109\n" + LocalDateTime.now() + "\r\n");
            send("MAC Address: " +sb.toString()+ "\r\n");
            send(".\r\n");
            System.out.println("Server: " + br.readLine());
            send("QUIT\r\n");
            System.out.println("Server: " + br.readLine());
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void send(String s) throws Exception {
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: " + s);
    }

}