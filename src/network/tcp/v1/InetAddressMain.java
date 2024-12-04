package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getByName("localhost");
        System.out.println(host);

        InetAddress btcpaw = InetAddress.getByName("btcpaw.com");
        System.out.println(btcpaw);

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println(google);
    }
}