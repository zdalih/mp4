package mp4;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
 * 
 * 		-Test edgeWeight
 * 			-See if outputs are correct
 */

public class MovieGraphTest {
	
	/**
	 * Finds the edge weight between the first and second movie
	 * using a simple data sample.
	 * 
	 * @param first non-null Movie object
	 * @param second non-null Movie object
	 * @return Integer value of edge weight between the two movies
	 * @throws IOException
	 */
	public int edgeWeight( Movie first, Movie second ) throws IOException {
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
		
		RatingIterator iter2 = new RatingIterator("data/u.simpleData.txt");
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
		
		//Few tests to verify the operation of the edgeWeight
		//With the simple case
		
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
	
	//A create a graph to test our path methods
	private MovieGraph createGraph() throws IOException{
		
		MovieGraph completeGraph = new MovieGraph();
		
		Movie alphaMovie = new Movie(1,"alpha",2001,"www.alpha.com");
		Movie betaMovie = new Movie(2,"beta",2002,"www.beta.com");
		Movie charlieMovie = new Movie(3,"charlie",2003,"www.charlie.com");
		Movie deltaMovie = new Movie(4,"delta",2004,"www.delta.com");
		Movie echoMovie = new Movie(5,"echo",2005,"www.echo.com");
		
		completeGraph.addVertex(alphaMovie);
		completeGraph.addVertex(betaMovie);
		completeGraph.addVertex(charlieMovie);
		completeGraph.addVertex(deltaMovie);
		completeGraph.addVertex(echoMovie);
		  
		//adding all edges of the graph
		completeGraph.addEdge(alphaMovie, betaMovie, edgeWeight(alphaMovie, betaMovie));
		completeGraph.addEdge(alphaMovie, charlieMovie, edgeWeight(alphaMovie, charlieMovie));
		completeGraph.addEdge(alphaMovie, deltaMovie, edgeWeight(alphaMovie, deltaMovie));
		completeGraph.addEdge(alphaMovie, echoMovie, edgeWeight(alphaMovie, echoMovie));
		completeGraph.addEdge(betaMovie, charlieMovie, edgeWeight(betaMovie, charlieMovie));
		completeGraph.addEdge(betaMovie, deltaMovie, edgeWeight(betaMovie, deltaMovie));
		completeGraph.addEdge(betaMovie, echoMovie, edgeWeight(betaMovie, echoMovie));
		completeGraph.addEdge(charlieMovie, deltaMovie, edgeWeight(charlieMovie, deltaMovie));
		completeGraph.addEdge(charlieMovie, echoMovie, edgeWeight(charlieMovie, echoMovie));
		completeGraph.addEdge(deltaMovie, echoMovie, edgeWeight(deltaMovie, echoMovie));
		
		return completeGraph;
	}
	
	//Movies to test or add vertex and edges
	Movie alphaMovie = new Movie(1,"alpha",2001,"www.alpha.com");
	Movie betaMovie = new Movie(2,"beta",2002,"www.beta.com");
	Movie charlieMovie = new Movie(3,"charlie",2003,"www.charlie.com");
	Movie deltaMovie = new Movie(4,"delta",2004,"www.delta.com");
	Movie echoMovie = new Movie(5,"echo",2005,"www.echo.com");
	
	@Test
	public void alphaBetaWeight() throws IOException{
		int result = edgeWeight(alphaMovie,betaMovie);
		int expected = 2;
		assertEquals(result,expected);
		}
	
	@Test
	public void betaCharlieWeight() throws IOException{
		int result = edgeWeight(betaMovie,charlieMovie);
		int expected = 4;
		assertEquals(result,expected);
	}
	
	@Test public void alphaEchoWeight() throws IOException{
		int result = edgeWeight(alphaMovie,echoMovie);
		int expected = 1;
		assertEquals(result,expected);
	}
	
	@Test
	public void addProperVertex() {
		MovieGraph graph = new MovieGraph();
		boolean result = graph.addVertex(alphaMovie);
		
		if(graph.movies.get(0).equals(alphaMovie)) //testing if the movie added is equal
			assertTrue(result); //testing if the return is true
		else
			fail();
	}
	
	@Test
	public void addImporperVertex() {
		MovieGraph graph = new MovieGraph();
		graph.addVertex(alphaMovie); //Testing if adding the same movie twice will return false
		assertFalse(graph.addVertex(alphaMovie));
	}
	
	@Test
	public void addProperMovieEdge() throws IOException{
		MovieGraph graph = new MovieGraph();
		graph.addVertex(alphaMovie);
		graph.addVertex(charlieMovie);
		
		boolean result = graph.addEdge((Movie)alphaMovie,(Movie)charlieMovie, edgeWeight(alphaMovie,charlieMovie));
		
		if(graph.movies.get(0).equals(alphaMovie) && graph.movies.get(1).equals(charlieMovie)){
			if( graph.weights.get(1).get(0) == edgeWeight(alphaMovie,charlieMovie))
				assertTrue(result);
		}
		else
			fail();
	}
	
	@Test
	public void addImproperMovieEdge() throws IOException {
		MovieGraph graph = new MovieGraph();
		graph.addVertex(alphaMovie);
		graph.addVertex(charlieMovie);
		
		graph.addEdge(alphaMovie,charlieMovie, edgeWeight(alphaMovie,charlieMovie));
		boolean result = graph.addEdge(alphaMovie,charlieMovie, edgeWeight(alphaMovie,charlieMovie));
		
		assertFalse(result);
	}
	
	@Test
	public void addProperIntEdge() throws IOException {
		MovieGraph graph = new MovieGraph();
		graph.addVertex(deltaMovie);
		graph.addVertex(echoMovie);
		
		boolean result = graph.addEdge(deltaMovie.hashCode(),echoMovie.hashCode(), edgeWeight(deltaMovie,echoMovie));
		
		if(graph.movies.get(0).equals(deltaMovie) && graph.movies.get(1).equals(echoMovie)){
			if( graph.weights.get(1).get(0) == edgeWeight(deltaMovie,echoMovie))
				assertTrue(result);
		}
		else
			fail();
	}
	
	@Test
	public void addImproperIntEdge() throws IOException {
		MovieGraph graph = new MovieGraph();
		graph.addVertex(alphaMovie);
		graph.addVertex(betaMovie);
		
		graph.addEdge(alphaMovie.hashCode(),betaMovie.hashCode(), edgeWeight(alphaMovie,betaMovie));
		boolean result = graph.addEdge(alphaMovie.hashCode(),betaMovie.hashCode(), edgeWeight(alphaMovie,betaMovie));
		
		assertFalse(result);
	}
	
	@Test
	public void getShortestPathLengthProperV1() throws NoSuchMovieException, NoPathException {
		MovieGraph graph = new MovieGraph();
		Movie A = new Movie(0, "A", 2010, "http:://www.imdb.com");
		Movie B = new Movie(1, "B", 2010, "http:://www.imdb.com");
		Movie C = new Movie(2, "C", 2010, "http:://www.imdb.com");
		Movie D = new Movie(3, "D", 2010, "http:://www.imdb.com");
		Movie E = new Movie(4, "E", 2010, "http:://www.imdb.com");
		
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		graph.addVertex(E);
		
		graph.addEdge(A, B, 1);
		graph.addEdge(B, C, 3);
		graph.addEdge(C, D, 2);
		graph.addEdge(C, E, 1);
		graph.addEdge(A, C, 1);
		graph.addEdge(A, E, 2);
		
		int result = graph.getShortestPathLength(1, 4);
		ArrayList<Movie> path = new ArrayList<Movie>();
		path = (ArrayList<Movie>) graph.getShortestPath(3, 0);
		
		String pathString =  new String("");
		
		
		for(Movie M : path){
			pathString += M.getName(); 
		}
		
		System.out.println(pathString);
	}
	
	@Test
	public void getShortestPathLengthAB() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		int length = graph.getShortestPathLength(alphaMovie.hashCode(), betaMovie.hashCode());
		int expected = 2;
		
		assertEquals(length,expected);	
		}
	
	@Test
	public void getShortestPathLengthAC() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		int length = graph.getShortestPathLength(alphaMovie.hashCode(), charlieMovie.hashCode());
		int expected = 2;
		
		assertEquals(length,expected);	
		}
	
	@Test
	public void getShortestPathLengthBC() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		int length = graph.getShortestPathLength(betaMovie.hashCode(), charlieMovie.hashCode());
		int expected = 3;
		
		assertEquals(length,expected);	
		}
	
	@Test
	public void getShortestPathLengthDE() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		int length = graph.getShortestPathLength(deltaMovie.hashCode(), echoMovie.hashCode());
		int expected = 2;
		
		assertEquals(length,expected);	
		}
	
	@Test
	public void getShortestPathLengthNoPath() throws IOException {
		MovieGraph graph = new MovieGraph();
		graph.addVertex(alphaMovie);
		graph.addVertex(betaMovie);
		graph.addVertex(charlieMovie);
		
		graph.addEdge(alphaMovie, betaMovie, edgeWeight(alphaMovie,betaMovie));
	
		try {
			graph.getShortestPathLength(alphaMovie.hashCode(), charlieMovie.hashCode());
			fail();
		} catch (NoSuchMovieException e) {
			fail();
		} catch (NoPathException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void getShortestPathLengthNoMovie() {
		MovieGraph graph = new MovieGraph();
		try {
			graph.getShortestPathLength(alphaMovie.hashCode(), betaMovie.hashCode());
			fail();
		} catch (NoSuchMovieException e) {
			assertTrue(true);
		} catch (NoPathException e) {
			fail();
		}
		
	}
	
	@Test
	public void getShortestPathAB() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		ArrayList<Movie> path = new ArrayList<Movie>();
		ArrayList<Movie> expected1 = new ArrayList<Movie>(Arrays.asList(alphaMovie,betaMovie));
		ArrayList<Movie> expected2 = new ArrayList<Movie>(Arrays.asList(alphaMovie,deltaMovie,betaMovie));
		
		path = (ArrayList<Movie>) graph.getShortestPath(alphaMovie.hashCode(), betaMovie.hashCode());
		
		if( path.equals(expected1) || path.equals(expected2))
			assertTrue(true);
		else
			fail();
		
	}
	
	@Test
	public void getShortestPathAC() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		ArrayList<Movie> path = new ArrayList<Movie>();
		ArrayList<Movie> expected = new ArrayList<Movie>(Arrays.asList(alphaMovie,echoMovie,charlieMovie));
		
		path = (ArrayList<Movie>) graph.getShortestPath(alphaMovie.hashCode(),charlieMovie.hashCode());
		
		if( path.equals(expected))
			assertTrue(true);
		else
			fail();
		
	}
	
	@Test
	public void getShortestPathBC() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		ArrayList<Movie> path = new ArrayList<Movie>();
		ArrayList<Movie> expected1 = new ArrayList<Movie>(Arrays.asList(betaMovie,echoMovie,charlieMovie));
		ArrayList<Movie> expected2 = new ArrayList<Movie>(Arrays.asList(betaMovie,deltaMovie,charlieMovie));
		
		path = (ArrayList<Movie>) graph.getShortestPath(betaMovie.hashCode(), charlieMovie.hashCode());
		
		if( path.equals(expected1) || path.equals(expected2))
			assertTrue(true);
		else
			fail();
		
	}
	
	@Test
	public void getShortestPathDE() throws NoSuchMovieException, NoPathException, IOException {
		MovieGraph graph = createGraph();
		ArrayList<Movie> path = new ArrayList<Movie>();
		ArrayList<Movie> expected = new ArrayList<Movie>(Arrays.asList(deltaMovie,alphaMovie,echoMovie));
		
		path = (ArrayList<Movie>) graph.getShortestPath(deltaMovie.hashCode(), echoMovie.hashCode());
		
		if( path.equals(expected))
			assertTrue(true);
		else
			fail();
		
	}
	@Test
	public void getShortestPathNoPath() throws IOException {
		fail();//out of memory error
		MovieGraph graph = new MovieGraph();
		graph.addVertex(alphaMovie);
		graph.addVertex(betaMovie);
		graph.addVertex(charlieMovie);
		
		graph.addEdge(alphaMovie, betaMovie, edgeWeight(alphaMovie,betaMovie));
	
		try {
			graph.getShortestPath(alphaMovie.hashCode(), charlieMovie.hashCode());
			fail();
		} catch (NoSuchMovieException e) {
			fail();
		} catch (NoPathException e) {
			assertTrue(true);
		}
	
	}
	
	@Test
	public void getShortestPathNoMovie() {
		MovieGraph graph = new MovieGraph();
		try {
			graph.getShortestPathLength(alphaMovie.hashCode(), betaMovie.hashCode());
			fail();
		} catch (NoSuchMovieException e) {
			assertTrue(true);
		} catch (NoPathException e) {
			fail();
		}
	}
	
	@Test
	public void getMovieIDProper() throws NoSuchMovieException, IOException {
		MovieGraph graph = createGraph();
		int result = graph.getMovieId("beta");
		assertTrue(result == 2);
	}
	
	@Test
	public void getMovieIDNoMovie() throws IOException {
		MovieGraph graph = createGraph();
		try {
			graph.getMovieId("foxtrot");
			fail();
		} catch (NoSuchMovieException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void equals() throws IOException {
		MovieGraph first = createGraph();
		MovieGraph second = createGraph();
		assertTrue(first.equals(second));
	}
	
	@Test
	public void notEquals() throws IOException {
		MovieGraph first = createGraph();
		MovieGraph second = createGraph();
		Movie additional = new Movie(1,"additional",2,"www.add.com");
		second.addVertex(additional);
		assertFalse(first.equals(second));
	}
	
	@Test
	public void hashcode() throws IOException {
		MovieGraph first = createGraph();
		MovieGraph second = createGraph();
		assertTrue(first.hashCode() == second.hashCode());
	}
	
	@Test
	public void notHashcode() throws IOException {
		MovieGraph first = createGraph();
		MovieGraph second = createGraph();
		Movie additional = new Movie(1,"additional",2,"www.add.com");
		second.addVertex(additional);
		assertFalse(first.hashCode() == second.hashCode());
	}
}
