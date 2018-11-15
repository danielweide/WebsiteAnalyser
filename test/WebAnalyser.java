import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author Crunchify.com
 *
 */
public class WebAnalyser {

  public static String[] reviews = {
    "Our mark: Your site rating: A. It shows a good security level and a good implementation of HTTPS. However, it is not completely safe so keep it up !.",
    "Our mark: Your site rating: D. It shows a patial security level due to a bad implementation of HTTPS. Most likely, the HTTPS is present but can be downgraded to an HTTP version. It would be wise to notify your IT service.",
    "Our mark: Your site rating: F. It shows a very weak security level and no implementation of HTTPS. Using an encrypted connection is very important to ensure a secured website and to avoid any attack ! It needs to be fixed as soon as possible"
  };


  public static int score = 0;
	public static String evaluate(String website) throws Exception {
    score = 0;
    // Scanner sc = new Scanner(System.in);
    // System.out.print("Please enter a website: ");
    // String website = sc.nextLine();
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
    // getSecAwareness(website);
		System.out.println("Scan completed...");
    System.out.println("Your score is: "+score);
    if(score == 2){
      return "Our mark: Your site rating: A. It shows a good security level and a good implementation of HTTPS. However, it is not completely safe so keep it up !.";
    } else if(score == 1) {
      return "Our mark: Your site rating: D. It shows a patial security level due to a bad implementation of HTTPS. Most likely, the HTTPS is present but can be downgraded to an HTTP version. It would be wise to notify your IT service.";
    } else if(score == 0) {
      return "Our mark: Your site rating: F. It shows a very weak security level and no implementation of HTTPS. Using an encrypted connection is very important to ensure a secured website and to avoid any attack ! It needs to be fixed as soon as possible";
    }
    return "";
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

  public static String getHTTPSecAwareness(String url) throws IOException {
    String result = "";
    System.out.print("HELLO !" );
    try {
      URL siteURL = new URL(  "http://desenmascara.me/api/" + url );
      HttpURLConnection apiConnection = (HttpURLConnection) siteURL.openConnection();
      apiConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      // apiConnection.setRequestProperty("origin","localhost");
      // apiConnection.setRequestProperty("Content-Type","application/json");
      apiConnection.setDoInput(true);
      apiConnection.setDoOutput(true);
      // InputStream response = apiConnection.getInputStream();
      apiConnection.setRequestMethod("GET");
      apiConnection.setConnectTimeout(4000);
      apiConnection.connect();

      if (apiConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
  			String inputLine;
  			StringBuffer response = new StringBuffer();

  			while ((inputLine = in.readLine()) != null) {
  				response.append(inputLine);
  			}
  			in.close();

  			// print result
  			return "Result for the Https version from http://desenmascara.me :" + response.toString();
  		} else {
        return "GET request not worked";
      }
      // System.out.println("hey " +apiConnection.getResponseMessage());
    } catch (Exception e) {
      result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
      return "GET request not worked";
    }
  }



  public static String getHTTPSSecAwareness(String url) throws IOException {
    String result = "";
    System.out.print("HELLO !" );
    try {
      URL siteURL = new URL(  "http://desenmascara.me/api/s/" + url );
      HttpURLConnection apiConnection = (HttpURLConnection) siteURL.openConnection();
      apiConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      // apiConnection.setRequestProperty("origin","localhost");
      // apiConnection.setRequestProperty("Content-Type","application/json");
      apiConnection.setDoInput(true);
      apiConnection.setDoOutput(true);
      // InputStream response = apiConnection.getInputStream();
      apiConnection.setRequestMethod("GET");
      apiConnection.setConnectTimeout(4000);
      apiConnection.connect();

      if (apiConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        // print result
        return "Result for the Https version from http://desenmascara.me :" + response.toString();
      } else {
        return "No result";
      }
      // System.out.println("hey " +apiConnection.getResponseMessage());
    } catch (Exception e) {
      result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
      return "GET request not worked";
    }
  }


}
