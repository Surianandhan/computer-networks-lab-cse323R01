import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5353);
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter domain name: ");
        String domain = sc.nextLine();
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        out.println(domain);
        System.out.println("IP Addresses:");
        
        String response;
        while ((response = in.readLine()) != null) {
            System.out.println(response);
        }
        
        socket.close();
        sc.close();
    }
}