import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
 
public class HttpsClient{
	String https_url;
    URL url;
    HttpsURLConnection con;
 
     public HttpsClient(String URL){
    	
    	try {
    		https_url = URL;
    		url = new URL(https_url);
    		con = (HttpsURLConnection)url.openConnection();
    	} 
    	catch (MalformedURLException e) {
    		e.printStackTrace();
    	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
   }
 
   @SuppressWarnings("unused")
   private void printHttpsCert(){
	   if(con!=null){ 
		   try {
			   System.out.println("Response Code : " + con.getResponseCode());
			   System.out.println("Cipher Suite : " + con.getCipherSuite());
			   System.out.println("\n");
 
			   Certificate[] certs = con.getServerCertificates();
			   for(Certificate cert : certs){
				   System.out.println("Cert Type : " + cert.getType());
				   System.out.println("Cert Hash Code : " + cert.hashCode());
				   System.out.println("Cert Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
				   System.out.println("Cert Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
				   System.out.println("\n");
			   }
 
		   } catch (SSLPeerUnverifiedException e) {
			   e.printStackTrace();
		   } catch (IOException e){
			   e.printStackTrace();
		   }   
	   }
   }
 
   public String returnContent(){
	   if(con!=null){
		   String input = "";
		   String thisString;
		   try {		
			   BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			   while ((thisString =br.readLine()) != null){
				   input += thisString;
			   }
			   br.close();
		   } 
		   
		   catch (IOException e) {
			   e.printStackTrace();
		   }
		   return input;
	   }
	   else 
		   return null;
   }
   public String getOfferings(){
		String nextLine;
		int counter = 0;
		String websiteContents = "";
		//writes as long as it's not null in a new file or even in console
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while( (nextLine = in.readLine()) != null ){
				websiteContents += nextLine+"\n";
				
				//if there is "no course" displayed then
				//set websiteContents to null
				
				if (counter == 6 && nextLine.indexOf("no course") >=0 ){
					websiteContents = null;
					break;
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return websiteContents;
	}
}