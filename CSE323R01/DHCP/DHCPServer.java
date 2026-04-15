import java.net.*;
import java.util.*;

public class DHCPServer {
    private static final int PORT = 6767;
    private static List<String> ipPool = new ArrayList<>();
    private static Map<String, String> allocatedIPs = new HashMap<>();
    
    static {
        for (int i = 100; i <= 110; i++) {
            ipPool.add("192.168.1." + i);
        }
    }
    
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(PORT);
        System.out.println("DHCP Server Started...");
        byte[] buffer = new byte[1024];
        
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            
            String[] parts = message.split(":");
            String type = parts[0];
            String clientId = parts[1];
            
            if (type.equals("DISCOVER")) {
                String offeredIP = ipPool.get(0);
                String offerMsg = "OFFER:" + clientId + ":" + offeredIP;
                sendMessage(socket, offerMsg, clientAddress, clientPort);
                System.out.println("Offered IP: " + offeredIP);
            } 
            else if (type.equals("REQUEST")) {
                String requestedIP = parts[2];
                if (!allocatedIPs.containsKey(clientId) && ipPool.contains(requestedIP)) {
                    allocatedIPs.put(clientId, requestedIP);
                    ipPool.remove(requestedIP);
                    String ackMsg = "ACK:" + clientId + ":" + requestedIP;
                    sendMessage(socket, ackMsg, clientAddress, clientPort);
                    System.out.println("IP Allocated: " + requestedIP);
                }
            }
        }
    }
    
    private static void sendMessage(DatagramSocket socket, String message, 
                                     InetAddress address, int port) throws Exception {
        byte[] response = message.getBytes();
        DatagramPacket packet = new DatagramPacket(response, response.length, address, port);
        socket.send(packet);
    }
}