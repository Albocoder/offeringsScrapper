import java.util.ArrayList;

public class Filter {
	private ArrayList<InfoHolder> toFilter;

	public Filter( ArrayList<InfoHolder> param) {
		toFilter = param;
	}
	public void teacherFilter(String teacherFilter){
		for(int x = 0; x< toFilter.size(); x++){
			if( !toFilter.get(x).getCourseTeacher().equals(teacherFilter) ){
				toFilter.get(x).setUse( false );
			}
			else{
				toFilter.get(x).setUse( true );
			}
		}
	}
	public void idFilter(String idFilter){
		for(int x = 0; x< toFilter.size(); x++){
			if( toFilter.get(x).getCourseTeacher().indexOf( idFilter ) >= 0 ){
				toFilter.get(x).setUse( false );
			}
			else{
				toFilter.get(x).setUse( true );
			}
		}
	}
	public ArrayList<InfoHolder> getArray(){
		return toFilter;
	}
	/*
	 * this part will need a time comparison... Waiting for time class to finish.
	 * Will implement a general building comparison thing
	 * 
	public void buildingFilter(String buildingFilter){
		for(int x = 0; x< toFilter.size(); x++){
			if( !toFilter.get(x).getCourseTeacher().equals( buildingFilter ) ){
				toFilter.get(x).setUse( false );
			}
			else{
				toFilter.get(x).setUse( true );
			}
		}
	}
	*/
	public String toString(){
		String toReturn = ""; 
		for(int c = 0; c < toFilter.size(); c++){
			if(toFilter.get(c).getUse()){
				toReturn += toFilter.get(c).toString();
			}
		}
		return toReturn;
	}

}