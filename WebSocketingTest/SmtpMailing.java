import java.io.*;
import java.time.LocalDateTime;
import javax.net.ssl.*;
import java.util.*;
class SmtpMailing{
    private static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String args[]){
        try{
            SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String email = "your-mail";
            String pass = "ypur-pass";

            String username = new String(Base64.getEncoder().encode(email.getBytes()));
            String password = new String(Base64.getEncoder().encode(pass.getBytes()));
            send("EHLO smtp.gmail.com\r\n");
            for(int i = 1; i <= 9; i++){
                System.out.println("Server: " + br.readLine());
            }
            
            send("AUTH LOGIN\r\n");     
            System.out.println("Server: " + br.readLine());

            send(username + "\r\n");
            System.out.println("Server: " + br.readLine());

            send(password + "\r\n");
            System.out.println("Server: " + br.readLine());

            send("MAIL FROM:<s2110976109@ru.ac.bd>\r\n");
            System.out.println("Server: " + br.readLine());

            send("RCPT TO:<debkdk1@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());

            send("DATA\r\n");
            //System.out.println("Server: " + br.readLine());

            send("FROM: s2110976109@ru.ac.bd\r\n");
            //System.out.println("Server: " + br.readLine());

            send("TO: debkdk1@gmail.com\r\n");
            //System.out.println("Server: " + br.readLine());

            send("Subject: Email test" + LocalDateTime.now() + "\r\n");
            //System.out.println("Server: " + br.readLine());

            send("This is a test email sent from Java program.\r\n");
            send(".\r\n");
            System.out.println("Server: " + br.readLine());

            send("QUIT\r\n");
            System.out.println("Server: " + br.readLine());
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void send(String s) throws Exception {
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: " + s);
    }
}