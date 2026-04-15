import java.util.Scanner;

public class Hamming74 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter 4 Data Bits in order (D4 D3 D2 D1): ");
        System.out.print("Input: ");
        String data = sc.next();
        
        int D4 = data.charAt(0) - '0';
        int D3 = data.charAt(1) - '0';
        int D2 = data.charAt(2) - '0';
        int D1 = data.charAt(3) - '0';
        
        int[] code = new int[8];
        code[7] = D4;
        code[6] = D3;
        code[5] = D2;
        code[3] = D1;
        
        // Parity bits
        code[1] = code[3] ^ code[5] ^ code[7];
        code[2] = code[3] ^ code[6] ^ code[7];
        code[4] = code[5] ^ code[6] ^ code[7];
        
        System.out.println("\nCalculated Parity Bits:");
        System.out.println("P1 = " + code[1]);
        System.out.println("P2 = " + code[2]);
        System.out.println("P3 = " + code[4]);
        
        System.out.print("\nTransmitted Hamming Code: ");
        for (int i = 7; i >= 1; i--) {
            System.out.print(code[i]);
        }
        System.out.println();
        
        System.out.print("\nEnter Received 7-bit Code: ");
        String received = sc.next();
        int[] recv_code = new int[8];
        int idx = 0;
        for (int i = 7; i >= 1; i--) {
            recv_code[i] = received.charAt(idx++) - '0';
        }
        
        int S1 = recv_code[1] ^ recv_code[3] ^ recv_code[5] ^ recv_code[7];
        int S2 = recv_code[2] ^ recv_code[3] ^ recv_code[6] ^ recv_code[7];
        int S3 = recv_code[4] ^ recv_code[5] ^ recv_code[6] ^ recv_code[7];
        
        System.out.println("\nSyndrome Bits:");
        System.out.println("S1 = " + S1);
        System.out.println("S2 = " + S2);
        System.out.println("S3 = " + S3);
        
        int error = (S3 * 4) + (S2 * 2) + S1;
        
        if (error == 0) {
            System.out.println("\nNo Error Detected");
        } else {
            System.out.println("\nError at position " + error);
            recv_code[error] = recv_code[error] == 0 ? 1 : 0;
            System.out.print("Corrected code: ");
            for (int i = 7; i >= 1; i--) {
                System.out.print(recv_code[i]);
            }
            System.out.println();
        }
        sc.close();
    }
}