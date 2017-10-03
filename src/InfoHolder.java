
/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 2.0
 * ==========================================================
 * This class will hold info for the course like:
 * course ID, course name, course teacher, course hours...
 * ==========================================================
 * Date: 23/3/2015
 * Date: 8/9/2015 (remake)
 * */
public class InfoHolder {
	private String id;
	private String courseName;
	private String courseTeacher;
	private String[] lessonAndBuilding;
	private int quota;
	private boolean useful;
	
	public InfoHolder(String id, String name, String teacher, String[] info, int q) {
		this.id = id;
		courseName = name;
		courseTeacher = teacher;
		lessonAndBuilding = info;
		useful = true;
		quota = q;
	}
	
	
	public String toString(){
		String toReturn = "\n";
		toReturn += id+"\n"+courseName+"\n"+courseTeacher+"\n";
		for(int c = 0; c < lessonAndBuilding.length; c++){
			toReturn += lessonAndBuilding[c] + "\n";
		}
		toReturn += "Quota: " + quota;
		toReturn += "\n\n";
		return toReturn;
	}

	
	public String getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseTeacher() {
		return courseTeacher;
	}
	public String[] getLessonAndBuilding() {
		return lessonAndBuilding;
	}
	public boolean getUse(){
		return useful;
	}
	public void setUse( boolean toSet){
		useful = toSet;
	}
	public int getQuota(){
		return quota;
	}
	
}