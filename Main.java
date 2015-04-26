import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	static HashMap<MapPair, LinkedList<Document>> fullDataSet;
	
	
	/**
	 * Builds a new document that can then be placed into the fullDataSet
	 * 
	 * @param user - author of the tweet as parsed from the data file
	 * @param date - date the tweet was authored, as parsed from the data file
	 * @param company - company the tweet concerns, as parsed from the data file
	 * @param text - the actual text of the tweet, as parsed from the data file
	 * @param tweetID - the unique tweetID, as parsed from the data file
	 * @return - the Document created by the method call
	 */
	public Document createDocument(String user, Date date, String company, String text, String tweetID){
		return new Document(user, date, company, text, tweetID);
	}
	
	
	/**
	 * Checks if there is already a document list associated with the company and date MapPair
	 *		- if there is a current list, the method simply appends the document to the list
	 *		- if there isn't a current list, the method creates the appropriate map pair
	 *			and a new document list, adds the document to the new document list, and puts
	 *			the MapPair, LinkedList key-value set into the fullDataSet HashMap
	 *
	 * @param d - the document that is being added to the fullDataSet
	 * 
	 */
	public void addToFullDataSet(Document d){
		MapPair test = new MapPair(d.dateCreated, d.company);
		if(fullDataSet.containsKey(test)){
			LinkedList<Document> newList = fullDataSet.get(test);
			newList.add(d);
		} else{
			LinkedList<Document> newList = new LinkedList<Document>();
			newList.add(d);
			fullDataSet.put(test, newList);
		}
	}
	
	
	public static void main(String [] args){
		String result = null;
		String textField;
		String textParse[];
		String company;
		String companyParse[];
		String tweetId;
		String tweetIdParse[];
		String user;
		String userParse[];
		String date;
		String dateParse[];
		String unit[];
		
		try {
			FileReader dataIn = new FileReader("data/2015-03-25-22.txt");
			BufferedReader bReader = new BufferedReader(dataIn);
			String theWholeStringManLikeWhoa;
			while( (theWholeStringManLikeWhoa = bReader.readLine()) != null){
				unit = theWholeStringManLikeWhoa.split("::::");
				//now iterate over all units - each unit has 5 elements, so increment by 5
				for(int i = 0; i < 25; i = i+5){
					//grab and parse each element
					company = unit[i+0];
					companyParse = company.split(":", 2);
					tweetId = unit[i+1];
					tweetIdParse = tweetId.split(":", 2);
					date = unit[i+2];
					dateParse = date.split(":", 2);
					user = unit[i+3];
					userParse = user.split(":", 2);
					textField = unit[i+4];
					textParse = textField.split(":", 2);
					
					try{
						Process p = new ProcessBuilder("/bin/bash", "src/redirectToJava.sh", textParse[1]).start();
						System.out.println("got here");
						p.waitFor();
						BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String inputLine;
						while((inputLine = in.readLine()) != null){
							System.out.println(inputLine);
							
						}
						in.close();
					} catch (Exception e){
						System.out.println("Failed");
					}
					
				}
				
				
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
}
