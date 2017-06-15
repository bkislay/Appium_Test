package fwDriver_All;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class restApiTest {
	//private static final String FILENAME = "/Users/kislaybarve/Documents/resultsNLPRacial.txt";
	private static final String FILENAME = "/Users/kislaybarve/Documents/resultsNLPIntentCS.txt";

		public static void main(String[] args) {

			String outPut = "";
		    //String csvFile = "/Users/kislaybarve/Documents/DataMiner_racialtweets.csv";
		    String csvFile = "/Users/kislaybarve/Documents/intent_customerservice.csv";
		    
		    String line = "";
		    // String csvSplitBy = ",";
		    int counter=0;
		    try (BufferedReader brr = new BufferedReader(new FileReader(csvFile))) {

		    while ((line = brr.readLine()) != null) {
		    	Thread.sleep(1000);
		    	counter=counter+1;
		    System.out.println("reading line number " + counter);
		    try {
		    	writeTofile("|"+line+"|");
		    	String decoded = URLDecoder.decode(line, "UTF-8") ;

		    	line=line.replaceAll("\\s+", "%20").replaceAll("\"", "").replaceAll("#"," ").replaceAll("�","");
		    	System.out.println(decoded);
				//String text="If, you're, not, black, say, nigga, nigger, my nig, anything that sounds like nigga, dont even say Nigeria";
					//URL url = new URL("http://192.168.1.197:8080/ruleengine-models-apis/v1/ruleengine-models?text="+line);
					URL url = new URL("http://192.168.1.210:8080/cafyne-intent-apis/v1/classify/intent?model=mlr&enabled=1&q="+line);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					//writeTofile(line);
					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
						outPut=outPut+"|"+output;
								}
					writeTofile(outPut);
					conn.disconnect();
					writeTofile("newLine");
					writeTofile("|---------------|");
					outPut="";

				  } catch (MalformedURLException e) {

					e.printStackTrace();

				  } catch (IOException e) {

					e.printStackTrace();

				  }


		            }

		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		 		  

		}
		
		public static void writeTofile(String args) {

	        // The name of the file to open.
	       // String fileName = "temp.txt";

	        try {
	            // Assume default encoding.
	            FileWriter fileWriter =
	                new FileWriter(FILENAME,true);

	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter);

	            // Note that write() does not automatically
	            // append a newline character.
	            if(args.equals("newLine"))
	            bufferedWriter.newLine();
	            else
	            bufferedWriter.write(args);
	           
	            // Always close files.
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error writing to file '"
	                + FILENAME + "'");
	            // Or we could just do this:
	            // ex.printStackTrace();
	        }
	    }
}

