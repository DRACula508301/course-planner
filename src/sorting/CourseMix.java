package sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import graphs.Graph;
import graphs.Vertex;

public class CourseMix {
	private ArrayList<Vertex> courses;
	private int count;
	
	public CourseMix() {
		this.courses = new ArrayList<Vertex>();
		count = 0;
	}
	
	public ArrayList<Vertex> getCourseList(){
		return courses;
	}
	
	public int getCount() {
		return count;
	}
	
	public void addMandatory(String mandatoryCourse) {
		Vertex courseToAdd = Graph.courses.get(mandatoryCourse);
		courses.add(courseToAdd);
		count++;
		if(!courseToAdd.getPrereqs().isEmpty()) {
			for(String prerequisite : courseToAdd.getPrereqs()) {
				addMandatory(prerequisite);
			}
		}
	}
	
	public LinkedList<Vertex> inquireInterest(){
		LinkedList<Vertex> interestedCourses = new LinkedList<Vertex>();
		Scanner in = new Scanner(System.in);
		System.out.println("Are there any specific courses that you want to take? 'y/n'");
		char moreToAdd = (char) in.nextByte();
		while(moreToAdd == 'y') {
			System.out.println("What is the course number of the course you would like to add?");
			String interestedCourseID = in.nextLine();
			if(Graph.courses.containsKey(interestedCourseID)) {
				interestedCourses.add(Graph.courses.get(interestedCourseID));
			}
			System.out.println("Are there any more courses that you want to take? 'y/n'");
			moreToAdd = (char) in.nextByte();
		}
		return interestedCourses;
	}
	
	@Override
	public String toString() {
		return "CourseMix [courses=" + courses + ", count=" + count + "]";
	}

	public void main(String args[]) {

	}

}
