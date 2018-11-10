import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyGetPingStatus {
	public static void main(String args[]) throws Exception {
 
		String[] hostList = { "http://smrttrains.com.sg/" };
				/*, "http://yahoo.com", "https://www.ebay.com", 
				"https://google.com",
				"http://www.example.co", "https://paypal.com", 
				"http://bing.com/", "http://techcrunch.com/", "http://mashable.com/",
				"https://thenextweb.com/", "http://wordpress.com/", 
				"http://wordpress.org/", "http://example.com/", "http://sjsu.edu/",
				"https://ebay.co.uk/", "http://google.co.uk/", "http://wtstravel.com.sg" };*/
		


		for (int i = 0; i < hostList.length; i++) {
 
			String url = hostList[i];
			getStatus(url);
 
		}
 
		System.out.println("Task completed...");
	}
 
	public static String getStatus(String url) throws IOException {
 
		String result = "";
		int code = 200;
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();
 
			code = connection.getResponseCode();
			if (code == 200) {
				result = "-> Green <-\t" + "Code: " + code;
				;
			} else {
				result = "-> Yellow <-\t" + "Code: " + code;
			}
		} catch (Exception e) {
			result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
 
		}
		System.out.println(url + "\t\tStatus:" + result);
		return result;
	}
 
}