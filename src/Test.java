import java.net.InetAddress;

public class Test {
    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getByName("google.com");
            boolean reachable = address.isReachable(10000);
            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}