import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.io.File;

/**
 * @author Crunchify.com
 *
 */
public class WebAnalyser {
  public static int score = 0;
	public static void main(String args[]) throws Exception {

    Scanner sc = new Scanner(System.in);
    System.out.print("Please enter a website: ");
    String website = sc.nextLine();
    int code = getStatus(changeToHTTP(website));
    if(code >= 300 && code < 400){
      score+=1; //redirected
    } else {
      score+=0;
    }
    code = getStatus(changeToHTTPS(website));
    if(code >= 300 && code < 400){
      score+=0; //redirected
    } else {
      score+=1; //redirected
    }
		System.out.println("Scan completed...");
    System.out.println("Your score is: "+score);
    if(score == 2){
      System.out.println("Your site rating: A");
    } else if(score == 1) {
      System.out.println("Your site rating: D");
    } else if(score == 0) {
      System.out.println("Your site rating: F");
    }
	}
  public static String changeToHTTP(String URL) {
      StringBuilder urlSB = new StringBuilder(URL);
      urlSB.insert(0,"http://");

    return urlSB.toString();
  }
  public static String changeToHTTPS(String URL) {
      StringBuilder urlSB = new StringBuilder(URL);
      urlSB.insert(0,"https://");

    return urlSB.toString();
  }

	public static int getStatus(String url) throws IOException {

		String result = "";
		int code = 0;

		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();

			code = connection.getResponseCode();
			if (code == 200) {
				result = "-> No redirection <-\t" + "Code: " + code;

			}else if (code == 301 || code == 300 || code == 302) {
				result = "-> Redirect from  <-\t" + "Code: " + code;
			}
      else {
				result = "-> Yellow <-\t" + "Code: " + code;
			}
		} catch (Exception e) {
			result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();

		}
		System.out.println(url + "\t\tStatus:" + result);
		return code;
	}

}
