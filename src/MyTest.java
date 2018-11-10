import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean test = pingHost("www.healthhub.sg",443,5000);
		System.out.println(test);
	}
	
	public static boolean pingHost(String host, int port, int timeout) {
	    try (Socket socket = new Socket()) {
	        socket.connect(new InetSocketAddress(host, port), timeout);
	        return true;
	    } catch (IOException e) {
	        return false; // Either timeout or unreachable or failed DNS lookup.
	    }
	}

}
