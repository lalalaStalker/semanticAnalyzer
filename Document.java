import java.util.Date;
import java.util.HashMap;


public class Document {

	String user;
	Date dateCreated;
	String text;
	HashMap<String, WordInformation> wordList;
	String tweetID;
	int sentiment;
	int totalWordCount;
	String company;
	
	public void calculateSentiment(){
		//Stub method -- implement TODO here
	}
	
	public Document(String theUser, Date theDate, String theCompany, String theText, String theTweetID){
		this.user = theUser;
		this.dateCreated = theDate;
		this.text = theText;
		this.tweetID = theTweetID;
		this.sentiment = 0;
		wordList = this.buildWordList();
		this.totalWordCount = 0;
		this.company = theCompany;
	}
	
	public HashMap<String, WordInformation> buildWordList(){
		
		//stub method -- implement TODO here
		
		return null;
	}
	
	public void setProbability(){
		//stub method -- implement TODO here
	}
	
}
