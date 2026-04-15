import java.io.*;
import java.net.*;

public class CaesarServer {
    private static final int PORT = 5001;
    private static final int SHIFT = 3;

    public static String caesarDecrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                decrypted.append((char) ((ch - 'A' - shift + 26) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                decrypted.append((char) ((ch - 'a' - shift + 26) % 26 + 'a'));
            } else {
                decrypted.append(ch);
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server waiting on port " + PORT + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder encryptedData = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                encryptedData.append(line).append("\n");
            }
            
            System.out.println("\nEncrypted Data Received:");
            System.out.println(encryptedData.toString());
            
            String decryptedData = caesarDecrypt(encryptedData.toString(), SHIFT);
            System.out.println("\nDecrypted (Original) Content:");
            System.out.println(decryptedData);
            
            FileWriter writer = new FileWriter("received_file.txt");
            writer.write(decryptedData);
            writer.close();
            System.out.println("\nFile saved as received_file.txt");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}