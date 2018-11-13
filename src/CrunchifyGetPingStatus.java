import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.io.File;

/**
 * @author Crunchify.com
 *
 */
public class CrunchifyGetPingStatus {
  public static ArrayList<String> httpRedirectedList = new ArrayList<String>(); //good list: contains URLS which redirected back to https
  public static ArrayList<String> httpNonRedirectedList = new ArrayList<String>(); //bad list: contains URLS which DID NOT redirect back to https

	public static void main(String args[]) throws Exception {
    ArrayList<String> websitesFromTxtfile = new ArrayList<String>();
    File file = new File("/Users/BLaQkRoW/Desktop/with_https.txt");
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine()){
      websitesFromTxtfile.add("http://www."+sc.nextLine());
      //System.out.println(sc.nextLine());
    }

		String[] hostListEducation = {"https://www.ies.org.sg/Home",
    "https://uyii.com.sg/", "https://belovedbumps.sg/", "https://www.schoolofthefuture.sg",
    "https://www.swimminglessons.com.sg", "https://www.blossomedugroup.com.sg","https://bwmonastery.org.sg",
    "https://www.middleton.edu.sg/","https://www.nushigh.edu.sg/"};
    String[] hostListHealthcare = {"http://www.sata.com.sg/", "https://srfac.sg/", "https://thewellnessinsider.sg/",
    "https://www.snec.com.sg/pages/home.aspx","https://www.memc.com.sg/", "https://www.wings.sg",
    "https://ntuchealth.sg/"
    };
    String[] hostListRetail = {"https://www.happybird.com.sg/","https://lavieflo.sg/","https://www.parismiki.com.sg/",
    "https://titanzinterior.com.sg/","https://www.ootoya.com.sg", "https://www.unipack.sg/",
    "https://jiji.sg/","https://www.lornajane.sg/", "https://www.singaporestandardseshop.sg/TopMenuRight/Home.aspx",
    "https://vinova.sg/", "https://www.leadlms.com.sg/LEAD/login/lms_login.aspx", "https://www.vistaprint.sg/",
    "https://www.3dinnovations.com.sg/", "https://www.aircon-servicing.com.sg", "https://99designs.com.sg",
    "https://aestamp.com.sg", "https://albatec.com.sg", "https://www.cottony.sg/",
    "https://allthingsdelicious.sg", "https://www.citymusic.com.sg/", "https://www.amway.sg",
    "https://www.childrensociety.org.sg/", "https://audiohouse.com.sg", "https://www.charterhouse.com.sg/",
    "https://beacon.com.sg", "https://domaco.com.sg", "https://blackmrkt.com.sg",
    "https://breakfixnow.com.sg", "https://buffettown.com.sg", "https://www.freshandclean.com.sg/",
    "https://gnc.com.sg/", "https://iceman.com.sg/", "https://inresearch.com.sg/",
    "https://jrc.sg/home", "https://mountstudio.com.sg/"
    };
    /*
    String[] hostListModfied = changeHTTPStoHTTP(hostListRetail); //convert the list

		for (int i = 0; i < hostListModfied.length; i++) {
			String url = hostListModfied[i];
			getStatus(url);
		} */

    for (int i = 0; i < websitesFromTxtfile.size(); i++) {
      String url = websitesFromTxtfile.get(i);
      //System.out.println("My URL is: "+url);
      getStatus(url);
    }
    System.out.println("Printing redirected list...");
    printList(httpRedirectedList);
    System.out.println("Printing non redirected list...");
    printList(httpNonRedirectedList);

		System.out.println("Task completed...");
	}
  public static void printList(ArrayList<String> list) {
    for(int i=0; i< list.size(); i++) {
      System.out.println(i+1+". "+list.get(i));
    }
  }
  public static String[] changeHTTPStoHTTP(String[] hostList) {
    String[] hostListConverted = new String[hostList.length];
    for(int i=0; i < hostList.length; i++) {
      StringBuilder urlSB = new StringBuilder(hostList[i]);
      urlSB.replace(0,5,"http");
      hostListConverted[i] = urlSB.toString();
    }
    return hostListConverted;
  }

	public static String getStatus(String url) throws IOException {

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
        httpNonRedirectedList.add(url);
				;
			}else if (code == 301 || code == 300 || code == 302) {
				result = "-> Redirect from  <-\t" + "Code: " + code;
        httpRedirectedList.add(url);
				;
			}
      else {
				result = "-> Yellow <-\t" + "Code: " + code;
			}
		} catch (Exception e) {
			result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();

		}
		System.out.println(url + "\t\tStatus:" + result);
		return result;
	}

}
