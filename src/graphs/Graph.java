package graphs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

import sorting.CourseMix;
import sorting.TopoSort;

public class Graph {
	public static HashMap<String, Vertex> courses = new HashMap<String, Vertex>();
	
	public Graph(Connection con, String courseIDColName, String tableName) {
		try {
		    Statement statement = con.createStatement();
		    String query = "SELECT * FROM " + tableName;
		    ResultSet resultSet = statement.executeQuery(query);
		    
		    while (resultSet.next()) {
		        // Process each row of the result set
		        // Example: Retrieve values from columns
		    	String courseName = resultSet.getString(1);
		    	String courseID = resultSet.getString(2);
		    	int courseCredits = resultSet.getInt(3);
		    	String prerequisites = resultSet.getString(4);
		    	String postrequisites = resultSet.getString(5);

			    ArrayList<String> prerequisiteList = new ArrayList<String>();
			    if(prerequisites != null) {
				    while(prerequisites.contains(",")) {
				    	int startIndex = 0;
					    int endIndex = prerequisites.indexOf(',');
				    	prerequisiteList.add(prerequisites.substring(startIndex, endIndex));
				    	prerequisites = prerequisites.substring(endIndex + 2);
				    }
				    prerequisiteList.add(prerequisites);
			    }
			    
			    ArrayList<String> opensUp = new ArrayList<String>();
			    if(postrequisites != null) {
				    while(postrequisites.contains(",")) {
				    	int startIndex = 0;
					    int endIndex = postrequisites.indexOf(',');
				    	opensUp.add(postrequisites.substring(startIndex, endIndex));
				    	postrequisites = postrequisites.substring(endIndex + 2);
				    }
				    opensUp.add(postrequisites);
			    }
			    
			    courses.put(courseID, new Vertex(courseName, courseID, prerequisiteList, opensUp, courseCredits));
		    }  		      
		    
		    resultSet.close();
		    statement.close();
		} catch (SQLException e) {
		    // Handle any potential errors
		    e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Graph [courses=" + courses + "]";
	}

	public static void main(String args[]) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/course-hierarchy";
		String username = "course-planner";
		String password = "It*b]4-nb*j(s*oB";

		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
		    
		    Graph courseGraph = new Graph(connection, "course_id", "info");
		    System.out.println(courseGraph.toString());
		    
			CourseMix courseMix = new CourseMix();
			courseMix.addMandatory("CSE 417T");
			System.out.println(courseMix.toString());
			
			TopoSort courseSort = new TopoSort(courseMix);
			System.out.println(courseSort.toString());
			ArrayList<Vertex> courseSchedule = courseSort.run();
			System.out.println(courseSchedule.toString());
		    
		    connection.close();
		} catch (SQLException e) {
		    // Handle any potential errors
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
