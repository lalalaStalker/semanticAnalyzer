import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Dictionary should hold words of a similar sentiment. The HashMap maps the word (String)
 * to it's frequency (int). Create a dictionary from a .txt file of words that express that sentiment. 
 * Use the probability that the word appears in that Dictionary, to determine the likelihood of that 
 * sentiment, given the use of that word.
 * 
 * @author Laura Stalker
 */
public class Dictionary extends HashMap< String, Integer>{
	
	
	private static final long serialVersionUID = 1L;
	private int totalWords;  //Total number of non-distinct words
	

	public Dictionary( String textFileName){
		totalWords = 0;
		this.addAllToDictionary( textFileName);
	}
	
	
	/**
	 * @return - the number of distinct words in the dictionary
	 */
	public int getNumDiffWords(){
		
		return this.size();
	}
	
	
	/**
	 * Fills a dictionary with all the words from a text file.
	 * If a repeat is found, then it's frequency (mapped value) is incremented by 1.
	 * Assumes space between words.. I'm separating with '\n'.
	 * 
	 * @param fileName - path to text file of words to add to dictionary
	 */
	public void addAllToDictionary( String fileName){
		
		int oneWord = 1;  //Frequency, for adding 1 word at a time
		
		String allWords;  //String of entire text file of dictionary words
		List<String> words = new ArrayList<String>();  //Parsed dictionary word String
		BufferedReader br = null;
		
		/*Read through the text file, parse out individual words, and save them in the list */
		try{
			br = new BufferedReader( new FileReader( fileName));
			while( (allWords = br.readLine()) != null){
				String[] token = allWords.split("\\s+");
				words.add( token[0]);
			}
		} catch ( Exception e) {
			e.printStackTrace();
		} finally{
			if( br != null){
				try {
					br.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		
		/*For each word in the list, check if it is in the Dictionary already.
		 * If so, increment its frequency (mapped value). If not, add it to the
		 * Dictionary with a frequency of 1. */
		for( int i = 0; i < words.size(); i++){
			if( this.containsKey( words.get(i))){
				int hasNum = (int)this.get( words.get(i));
				this.put( words.get(i), ++hasNum);
				this.totalWords++;
			}
			else{
				this.put( words.get(i), oneWord);
				this.totalWords+=oneWord;
			}
		}
	}// End addAllToDictionary method
	
	
	/**
	 * Calculates the probability that a certain word appears in the dictionary.
	 * =[Total times that word appears DIVIDED BY total number of non-distinct words in the dictionary]
	 * 
	 * @param word - String to check the dictionary for
	 * @return probability of a word occurring
	 */
	public double getProbability( String word){
		
		double prob = 0.0;  //Double.MIN_VALUE??
		
		word = word.toLowerCase();
		
		if( this.containsKey( word)){
			prob = ( (double) ((int)this.get( word)) / this.totalWords);
		}
		return prob;
	}
	
}
