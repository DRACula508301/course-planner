package graphs;

import java.util.ArrayList;

public class Vertex {
	
	private static int count;
	
	final private String courseName;
	
	final private String courseID;
	
	private ArrayList<String> prerequisites;
	
	private ArrayList<String> opensUp;
	
	private int credits;
	
	public Vertex(String courseName, String courseID, ArrayList<String> prerequisites, ArrayList<String> opensUp, int credits) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.prerequisites = prerequisites;
		this.opensUp = opensUp;
		this.credits = credits;
	}
	
	public Vertex() {
		this.courseName = "";
		this.courseID = "";
		this.prerequisites = new ArrayList<String>();
		this.opensUp = new ArrayList<String>();
		credits = 0;
		count++;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public String getCourseID() {
		return courseID;
	}
	
	public ArrayList<String> getPrereqs() {
		return prerequisites;
	}
	
	public ArrayList<String> getNext(){
		return opensUp;
	}
	
	public void addPrereq(String prerequisite) {
		prerequisites.add(prerequisite);
	}
	
	public void addPrereq(ArrayList<String> prerequisites) {
		this.prerequisites = prerequisites;
	}
	
	public void addNext(String next) {
		opensUp.add(next);
	}
	
	public void addNext(ArrayList<String> next) {
		this.opensUp = next;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Vertex [courseName=" + courseName + ", prerequisites=" + prerequisites + 
				", opensUp=" + opensUp + 
				", credits=" + credits + "]";
	}

	public static void main(String[] args) {
//		Vertex cse131 = new Vertex ("CSE 131", new ArrayList<Vertex>(), new ArrayList<Vertex>());
//		ArrayList<Vertex> prerequisites = new ArrayList<Vertex>();
//		prerequisites.add(cse131);
//		ArrayList<Vertex> opensUp = new ArrayList<Vertex>();
//		Vertex v = new Vertex ("CSE 247", prerequisites, opensUp);
//		System.out.println(v.toString());
	}
}
