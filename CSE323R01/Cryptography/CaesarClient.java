import java.io.*;
import java.net.*;

public class CaesarClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5001;
    private static final int SHIFT = 3;

    public static String caesarEncrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                encrypted.append((char) ((ch - 'A' + shift) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                encrypted.append((char) ((ch - 'a' + shift) % 26 + 'a'));
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("send_file.txt"));
            StringBuilder fileData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileData.append(line).append("\n");
            }
            reader.close();

            String encryptedData = caesarEncrypt(fileData.toString(), SHIFT);
            System.out.println("\nEncrypted Data:");
            System.out.println(encryptedData);

            Socket socket = new Socket(HOST, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print(encryptedData);
            out.flush();
            socket.shutdownOutput();
            System.out.println("\nFile sent successfully!");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}