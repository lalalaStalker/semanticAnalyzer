import java.util.Date;


public class MapPair {

	Date dateCreated;
	String company;
	
	public MapPair(Date date, String company){
		this.dateCreated = date;
		this.company = company;
	}
	
	boolean equals(Date date, String company){
		return this.dateCreated.equals(date) && this.company.equals(company);
	}
	
}
