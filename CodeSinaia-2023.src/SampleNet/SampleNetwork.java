package SampleNet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SampleNetwork {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Hello to SampleNetwork");
        InetAddress inetAddress = InetAddress.getLocalHost();
        // byte[] ipAddr = inetAddress.getAddress();
        // System.out.println(ipAddr);
        // String strAddr = Arrays.toString(ipAddr);
        // System.out.println(strAddr);

        // int[] arr = new int[4];
        // for(int i = 0; i < 4; i++) {
        //     arr[i] = Byte.toUnsignedInt(ipAddr[i]);
        // }

        // strAddr = Arrays.toString(arr);
        // System.out.println(strAddr); 

        System.out.println(inetAddress);
        System.out.println(inetAddress.getHostName());
    }
}