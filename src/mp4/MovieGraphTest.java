package mp4;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

// TODO: You should implement suitable JUnit tests to verify that your implementation of the MovieGraph class is correct.

/*
 * Tests to make:
 * 		-Test addVertex
 * 			-movie is successfully added, should return true
 * 			-movie exists already, should return false
 * 
 * 		-Test addEdge(Movie)
 * 			-edge is successfully added, should return true
 * 			-edge exists already, should return false
 * 
 * 		-Test addEdge(Int)
 * 			-edge is successfully added, should return true
 * 			-edge exists already, should return false
 * 
 * 		-Test getShortestPathLength
 * 			-Tests to see if returns the correct path length
 * 			-No path exception 
 * 			-No such movie
 * 		
 * 		-Test getShortestPath
 * 			-Tests to see if returns the correct path
 * 			-No path exception 
 * 			-No such movie
 * 
 * 		-Test getMovieID
 * 			-Tests to see if returns the correct movie ID
 * 			-no such movie exception		
 * 
 * 		-Test equality
 * 			-Equal
 * 			-hashcode
 */

public class MovieGraphTest {
	/**
	 * Finds the edge weight between the first and second movie.
	 * 
	 * @param first non-null Movie object
	 * @param second non-null Movie object
	 * @return Integer value of edge weight between the two movies
	 * @throws IOException
	 */
	private int edgeWeight( Movie first, Movie second ) throws IOException {
		int firstID = first.hashCode(); //Hashcode returns a movie ID
		int secondID = second.hashCode();
		Set <Integer> firstLikerIds = new HashSet<Integer>();
		Set <Integer> firstDislikerIds = new HashSet<Integer>();
		Set <Integer> secondLikerIds = new HashSet<Integer>();
		Set <Integer> secondDislikerIds = new HashSet<Integer>();
		Set <Integer> firstMovieRaters = new HashSet<Integer>();
		Set <Integer> secondMovieRaters = new HashSet<Integer>();
		int likerIntersectionSize = 0;
		int dislikerIntersectionSize = 0;
		int totalReviewers = 0;
		int edgeWeight;
		
		RatingIterator iter2 = new RatingIterator("data/u.data.txt");
		while (iter2.hasNext()) {
			
			Rating rating = iter2.getNext();
			
			if(rating.getMovieId() == firstID){
				firstMovieRaters.add(rating.getUserId());
				if(rating.getRating() < 3)
					firstDislikerIds.add(rating.getUserId());//if the user does not like the movie add user to first disliker set
				else if(rating.getRating() > 3)
					firstLikerIds.add(rating.getUserId());//if the user likes the movie add user to first liker set
			}
			
			if(rating.getMovieId() == secondID ){
				secondMovieRaters.add(rating.getUserId());
				if(rating.getRating() < 3)
					secondDislikerIds.add(rating.getUserId()); //if the user does not like the movie add user to second disliker set
				else if(rating.getRating() > 3)
					secondLikerIds.add(rating.getUserId());//if the user likes the movie add user to second liker set
			}
		}
		
		for(int firstLiker : firstLikerIds){
			for(int secondLiker : secondLikerIds){
				if(firstLiker == secondLiker)
					likerIntersectionSize++; //if the user exists in both liker sets then add to liker intersection size
			}			
		}
		
		for(int firstDisliker : firstDislikerIds){
			for(int secondDisliker : secondDislikerIds){
				if(firstDisliker == secondDisliker)
					dislikerIntersectionSize++; //if the user exists in both disliker sets then add to disliker intersection size
			}		
		}
		
		//To find the number of reviewers we can find the intersection 
		//between the set of users who rated first movie and the set 
		//of users who rated the second movie.
		//Here we are assuming the number of reviews is the number of
		//reviewers who rated both movies.
		for(int firstRater : firstMovieRaters){
			for(int secondRater : secondMovieRaters){
				if(firstRater == secondRater)
					totalReviewers++;
			}		
		}
		
		edgeWeight = 1 + totalReviewers - (likerIntersectionSize + dislikerIntersectionSize);
		
		return edgeWeight;
	}
	
	@Test
	public void addProperVertex() {
		fail("Not yet implemented");
	}
	
	@Test
	public void addImporperVertex() {
		fail("Not yet implemented");
	}
	
	@Test
	public void addProperMovieEdge() {
		fail("Not yet implemented");
	}
	
	@Test
	public void addImproperMovieEdge() {
		fail("Not yet implemented");
	}
	
	@Test
	public void addProperIntEdge() {
		fail("Not yet implemented");
	}
	
	@Test
	public void addImproperIntEdge() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getShortestPathLengthProper() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getShortestPathLengthNoPath() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getShortestPathLengthNoMovie() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getShortestPathProper() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getShortestPathNoPath() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getShortestPathNoMovie() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getMovieIDProper() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getMovieIDNoMovie() {
		fail("Not yet implemented");
	}
	
	@Test
	public void equals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void notEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void hashcode() {
		fail("Not yet implemented");
	}
	
	@Test
	public void notHashcode() {
		fail("Not yet implemented");
	}
}
