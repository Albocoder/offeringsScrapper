import java.util.ArrayList;
import java.util.Arrays;

/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 1.8
 * ==========================================================
 * This will search for the data in the offering URL.
 * And then will return data accordingly in an ArrayList
 * of InfoHolders. 
 * ==========================================================
 * Date: 23/3/2015
 * Version: 2.0
 * 
 * Date: 2/4/2015
 * Version: 2.4
 * 
 * Date: 9/9/2015
 * Version: 2.6
 * */
public class DataSearcher extends OfferingsReader {
	private String websiteContents, id;
	private int section, grade;
	private ArrayList<InfoHolder> allInfo = new ArrayList<InfoHolder>();


	//by default can select all courses
	public DataSearcher(String id) {
		this( id, 0,0);
	}

	public DataSearcher(String id,int g, int sec) {
		super(id);
		id = id.toUpperCase();
		section = sec;
		this.id = id;
		grade = g;
		websiteContents = super.getExpected();
	}

	public ArrayList<InfoHolder> findAll() throws NullPointerException{
		if( section != 0 && grade != 0){
			allInfo.add( singleSearch(grade,section));
		}

		else if ( grade != 0 && section == 0 ){
			int c = 1;
			InfoHolder d = null;
			do{
				d = singleSearch(grade, c);
				if ( d != null ){
					allInfo.add(d);
				}
				c++;
			}
			while( c < 150);

		}
		else{
			int c = 1;
			int d = 101;
			InfoHolder e = null;
			do{
				do{
					e = singleSearch(d, c);
					if ( e != null ){
						allInfo.add(e);
					}
					c++;
				}
				while( c < 150);
				c=1;
				d++;
			}
			while( d < 700 );

		}

		return allInfo;

	}


	private InfoHolder singleSearch(int g, int se) throws NullPointerException {
		int occ;
		//used for the next td
		int next;
		//is used in extracting times
		int br;
		//span for teacher name
		int span;
		//the limit <tr>
		int tr;
		String searchString = "<td>"+id;
		searchString += " "+ g;
		searchString += "-"+ se+"</td>";

		String lessonHour;
		String lessons[] = new String[40];
		String teacherName;
		String name;
		//int to take the amount of lesson hours
		int howMany = 0;
		
		occ = websiteContents.indexOf( searchString );
		
		
		//if the searchString is not found return null
		if ( occ < 0 ){
			return null;
		}

		next = websiteContents.indexOf( "</td>", occ + searchString.length() + 5);

		//the next table row is maximum of our search
		tr = websiteContents.indexOf( "<tr>", next);

		//name of subject taken :D
		name = websiteContents.substring(start(next),next);


		//name of teacher taken
		span = websiteContents.indexOf("</span>",next);
		if ( span > tr || span < 0 )
			teacherName = "Staff";
		else
			teacherName = websiteContents.substring(start(span),span);

		String tempDay;
		while (websiteContents.indexOf( "<br />", next) < tr){
			br = websiteContents.indexOf( "<br />", next);
			if (br < 0){
				lessonHour = null;
				break;
			}
			else{
				tempDay = websiteContents.substring(websiteContents.lastIndexOf("<b>",start(br))+3,websiteContents.lastIndexOf("</b>",start(br)));
				lessonHour = websiteContents.substring(websiteContents.lastIndexOf("/b>", br)+3,br);
				lessonHour = lessonHour.replace(" ", "");
				lessonHour = lessonHour.replace("<sup>", "//");
				lessonHour = lessonHour.replace("</sup>", "//");
				lessonHour = lessonHour.replace("&nbsp;", "@");
				lessonHour = tempDay+" "+lessonHour;
				//lessonHour = lessonHour.substring(0,11)+" "+lessonHour.substring(17,lessonHour.length());
				next = br+3;
				//this will trim up the lessoHour String to make it only 1 line
				if ( lessonHour.startsWith("\n") )
					lessonHour = lessonHour.substring(1);
				lessons[howMany] = lessonHour;
				howMany++;
			}
		}
		int quo = websiteContents.lastIndexOf("<td align='center'>",tr);
		int quota = 0;
		try{
			quota = Integer.parseInt(websiteContents.substring(quo+19,websiteContents.indexOf("</td>",quo)));
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
		//this cuts the array to the parts we want for initialization
		lessons = Arrays.copyOfRange( lessons , 0, howMany);
		searchString = searchString.substring(4, searchString.indexOf("</td>"));
		InfoHolder toReturn = new InfoHolder( searchString , name , teacherName, lessons,quota);

		return toReturn;
	}

	//facility developed to help in finding the beginning of a useful String to extract
	//this is used at the singleSearch method
	private int start(int endIndex){
		return websiteContents.lastIndexOf(">", endIndex)+1;
	}
}