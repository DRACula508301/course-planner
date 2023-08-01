package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import graphs.Graph;
import graphs.Vertex;

public class TopoSort {
	
	final private ArrayList<Vertex> coursesToSort;
	private final int count;
	private int[] prereqCounter;
	private ArrayList<Vertex> courseOrdering;
	private final HashMap<String, Integer> courseIndex;
	
	public TopoSort(CourseMix courseMix) {
		this.coursesToSort = courseMix.getCourseList();
		this.count = courseMix.getCount();
		this.courseIndex = new HashMap<String, Integer>();
		this.courseOrdering = new ArrayList<Vertex>();
		prereqCounter = new int[courseMix.getCount()];
		for(int i = 0; i < courseMix.getCount(); i++) {
			Vertex course = coursesToSort.get(i);
			courseIndex.put(course.getCourseID(), i);
			prereqCounter[i] = course.getPrereqs().size();
		}
	}
	
	public ArrayList<Vertex> run() {
		int itrIndex;
		for(int i = 0; i < count; i++) {
			itrIndex = 0;
			while(prereqCounter[itrIndex] != 0) {
				itrIndex++;
			}
			Vertex course = coursesToSort.get(itrIndex);
			courseOrdering.add(course);
			prereqCounter[courseIndex.get(course.getCourseID())] = -1;
			if(!course.getNext().isEmpty()) {
				for(String ensuingCourseID : course.getNext()) {
					if(courseIndex.containsKey(ensuingCourseID)) {
						prereqCounter[courseIndex.get(ensuingCourseID).intValue()]--;
					}
				}
			}

		}
		return courseOrdering;
	}
	
	@Override
	public String toString() {
		return "TopoSort [coursesToSort=" + coursesToSort + ", count=" + count + ", prereqCounter="
				+ Arrays.toString(prereqCounter) + ", courseOrdering=" + courseOrdering + ", courseIndex=" + courseIndex
				+ "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
