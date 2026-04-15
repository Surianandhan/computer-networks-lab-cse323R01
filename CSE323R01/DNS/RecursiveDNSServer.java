import java.net.*;
import java.io.*;

public class RecursiveDNSServer {
    private static final int PORT = 5353;
    
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Recursive DNS Server running on port " + PORT);
        
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String domain = in.readLine();
            System.out.println("Received query for: " + domain);
            
            try {
                InetAddress[] addresses = InetAddress.getAllByName(domain);
                for (InetAddress addr : addresses) {
                    out.println(addr.getHostAddress());
                }
            } catch (Exception e) {
                out.println("Domain not found");
            }
            socket.close();
        }
    }
}