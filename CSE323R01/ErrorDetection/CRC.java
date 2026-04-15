import java.util.Scanner;

public class CRC {
    static String zeros(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append('0');
        return sb.toString();
    }

    static String xor(String a, String b) {
        String result = "";
        for (int i = 1; i < b.length(); i++) {
            result += (a.charAt(i) == b.charAt(i)) ? '0' : '1';
        }
        return result;
    }

    static String mod2Division(String dividend, String divisor) {
        int pick = divisor.length();
        String temp = dividend.substring(0, pick);
        
        while (pick < dividend.length()) {
            if (temp.charAt(0) == '1') {
                temp = xor(divisor, temp) + dividend.charAt(pick);
            } else {
                temp = xor(zeros(pick), temp) + dividend.charAt(pick);
            }
            pick++;
        }
        
        if (temp.charAt(0) == '1') {
            temp = xor(divisor, temp);
        } else {
            temp = xor(zeros(pick), temp);
        }
        return temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Data bits: ");
        String data = sc.nextLine();
        System.out.print("Enter Generator polynomial: ");
        String generator = sc.nextLine();
        
        int genLen = generator.length();
        String appendedData = data + zeros(genLen - 1);
        String crc = mod2Division(appendedData, generator);
        String transmittedData = data + crc;
        
        System.out.println("\nCRC (Remainder): " + crc);
        System.out.println("Transmitted Data: " + transmittedData);
        
        System.out.print("\nEnter received data: ");
        String receivedData = sc.nextLine();
        String remainder = mod2Division(receivedData, generator);
        
        if (remainder.contains("1")) {
            System.out.println("Error detected");
        } else {
            System.out.println("No error detected");
        }
        sc.close();
    }
}