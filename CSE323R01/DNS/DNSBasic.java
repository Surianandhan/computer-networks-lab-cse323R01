import java.net.InetAddress;
import java.util.Scanner;

public class DNSBasic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter domain name: ");
        String domain = sc.nextLine();
        
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domain);
            System.out.println("IP Addresses:");
            for (InetAddress addr : addresses) {
                System.out.println(addr.getHostAddress());
            }
        } catch (Exception e) {
            System.out.println("Unable to resolve domain.");
        }
        sc.close();
    }
}