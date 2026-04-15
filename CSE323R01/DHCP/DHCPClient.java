import java.net.*;

public class DHCPClient {
    private static final int SERVER_PORT = 6767;
    
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        String clientId = "Client1";
        
        // DISCOVER
        String discoverMsg = "DISCOVER:" + clientId;
        sendMessage(socket, discoverMsg, serverAddress);
        
        byte[] buffer = new byte[1024];
        DatagramPacket offerPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(offerPacket);
        String offer = new String(offerPacket.getData(), 0, offerPacket.getLength());
        System.out.println("Received: " + offer);
        
        String[] offerParts = offer.split(":");
        String offeredIP = offerParts[2];
        
        // REQUEST
        String requestMsg = "REQUEST:" + clientId + ":" + offeredIP;
        sendMessage(socket, requestMsg, serverAddress);
        
        // ACK
        DatagramPacket ackPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(ackPacket);
        String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
        System.out.println("Received: " + ack);
        
        socket.close();
    }
    
    private static void sendMessage(DatagramSocket socket, String message, 
                                     InetAddress address) throws Exception {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, SERVER_PORT);
        socket.send(packet);
    }
}