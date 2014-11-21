package mp4;

import java.util.ArrayList;
import java.util.List;

// The edges are weighted.
// This graph should be immutable except for the addition of vertices and edges. 
// It should not be possible to change a vertex after it has been added to the graph.

// You should indicate what the representation invariants and the abstraction function are for the representation you choose.

public class MovieGraph {
	
	//RI: The ith position of the movies List always represents the movie
	//in the ith node on the graph List. IF a node has an edge towards another
	//node, then the other node has a node towards the node. The list of edge is 
	//always in sync with the list of weights at any node. meaning that the ith weight
	//of the ith node is the weight of the ith edge of the ith node. 
	//
	//AF: The list movie is a list where each index of the list represents the position of 
	//the movie that is in graph and lists. At each position on those two list is a list. The lists 
	//in graph represents the movies connected to this node and the list in weights represents the
	//weight of each edge
	
	List<List<Movie>> graph = new ArrayList<List<Movie>>();
	List<List<Integer>> weights = new ArrayList<List<Integer>>();
	List<Movie> movies = new ArrayList<Movie>();
	


	/**
	 * Add a new movie to the graph. If the movie already exists in the graph
	 * then this method will return false. Otherwise this method will add the
	 * movie to the graph and return true.
	 * 
	 * @param movie
	 *            the movie to add to the graph. Requires that movie != null.
	 * @return true if the movie was successfully added and false otherwise.
	 * @modifies this by adding the movie to the graph if the movie did not
	 *           exist in the graph.
	 */
	public boolean addVertex(Movie movie) {
		
		//checks if the movie is on the graph
		
		for(int index = 0; index < movies.size(); index++){
			if(movies.contains(movie))
				return false;
		}
		
		//adds movie to the the graph by adding it to the list of
		//movies and creating a node to represent it
		
		movies.add(movie);
		graph.add(new ArrayList<Movie>());
		weights.add(new ArrayList<Integer>());
		
		return true;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method will return false. Otherwise this method will add the edge to
	 * the graph and return true.
	 * 
	 * @param movie1
	 *            one end of the edge being added. Requires that m1 != null.
	 * @param movie2
	 *            the other end of the edge being added. Requires that m2 !=
	 *            null. Also require that m1 is not equal to m2 because
	 *            self-loops are not meaningful in this graph.
	 * @param edgeWeight
	 *            the weight of the edge being added. Requires that edgeWeight >
	 *            0.
	 * @return true if the edge was successfully added and false otherwise.
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(Movie movie1, Movie movie2, int edgeWeight) {
		int index1 = movies.indexOf(movie1);
		int index2 = movies.indexOf(movie2);
		
		//checks if argument is valid
		if(graph.get(index1).contains(movie2))
			return false;
		if(graph.get(index2).contains(movie1))
			return false;
		if(movie1.equals(movie2))
			return false;
		
		//adds the edge
		graph.get(index1).add(movie2);
		graph.get(index2).add(movie1);
		weights.get(index1).add(edgeWeight);
		weights.get(index2).add(edgeWeight);
		
		return true;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method should return false. Otherwise this method should add the
	 * edge to the graph and return true.
	 * 
	 * @param movieId1
	 *            the movie id for one end of the edge being added. Requires
	 *            that m1 != null.
	 * @param movieId2
	 *            the movie id for the other end of the edge being added.
	 *            Requires that m2 != null. Also require that m1 is not equal to
	 *            m2 because self-loops are not meaningful in this graph.
	 * @param edgeWeight
	 *            the weight of the edge being added. Requires that edgeWeight >
	 *            0.
	 * @return true if the edge was successfully added and false otherwise.
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(int movieId1, int movieId2, int edgeWeight) {
		int index1 = -1;
		int index2 = -1;
		
		//get the indexes that represent the movie IDs
		for(int index = 0 ; index < movies.size(); index++){
			if(movies.get(index).hashCode() == movieId1)
				index1 = index;
			if(movies.get(index).hashCode() == movieId2)
				index2 = index;
		}
		
		//checks argument
		if(index1 == index2 || index1 == -1 || index2  == -1)
			return false;
		
		//adds the edge
		graph.get(index1).add(movies.get(index2));
		graph.get(index2).add(movies.get(index1));
		weights.get(index1).add(edgeWeight);
		weights.get(index2).add(edgeWeight);
		
		return true;
	}

	/**
	 * Return the length of the shortest path between two movies in the graph.
	 * Throws an exception if the movie ids do not represent valid vertices in
	 * the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws NoSuchMovieException
	 *             if one or both arguments are not vertices in the graph.
	 * @throws NoPathException
	 *             if there is no path between the two vertices in the graph.
	 * 
	 * @return the length of the shortest path between the two movies
	 *         represented by their movie ids.
	 */
	public int getShortestPathLength(int moviedId1, int moviedId2)
			throws NoSuchMovieException, NoPathException {
		
		int length = -1;
		int index1 = -1;
		int index2 = -1;
		int[] distances = new int[movies.size()];
		List<Integer> visitedIndexes = new ArrayList<Integer>();
		
		//get the index of the nodes for the IDs
		for(int index = 0 ; index < movies.size(); index++){
			if(movies.get(index).hashCode() == moviedId1)
				index1 = index;
			if(movies.get(index).hashCode() == moviedId2)
				index2 = index;
		}
		
		//check existence of the nodes
		if(index1 == -1 || index2 == -1)
			throw new NoSuchMovieException();
		
		//set every node to distance infinity and the node
		//we are starting from to 0
		for(int index = 0; index < movies.size(); index++){
			if(index == index1)
				distances[index] = 0;
			else
				distances[index] = Integer.MAX_VALUE;
		}
		
		//start at index1
		int currentIndex = index1;
		int count = movies.size();
		
		while (currentIndex != index2 && movies.size() > 0){
			
			//for the current index it updates all the distances
			for(Movie M : graph.get(currentIndex)){
				int vertexToUpdate = movies.indexOf(M);
				
				//if we have not yet visited the node
				if(!visitedIndexes.contains(vertexToUpdate)){
					int dist = distances[currentIndex]+weights.get(currentIndex).get(graph.get(currentIndex).indexOf(M));
					
					if(dist < distances[vertexToUpdate])
						distances[vertexToUpdate] = dist;
				}
			}
			
			//once we visited the nodes connected to the current index
			//we add it to visited indexes
			visitedIndexes.add(currentIndex);
			
			//we want to know which node to visit next, it must have the smallest
			//distance and not have been visited yet.
			
			int min = Integer.MAX_VALUE;
			
			for(int index = 0; index < distances.length; index++){
				if(distances[index] < min && !visitedIndexes.contains(index)){
					min = distances[index];
					currentIndex = index;
				}
			}
			
			//if we are now on the final index, we read the lenght of it
			if(currentIndex == index2)
				length = distances[index2];
			
			//we have count to make sure that we do know when to stop
			//in the case that there is no path
			count--;
		}
		
		//we can know return accordingly
		if(length == -1)
			throw new NoPathException();
		else
			return length;
	}

	/**
	 * Return the shortest path between two movies in the graph. Throws an
	 * exception if the movie ids do not represent valid vertices in the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws NoSuchMovieException
	 *             if one or both arguments are not vertices in the graph.
	 * @throws NoPathException
	 *             if there is no path between the two vertices in the graph.
	 * 
	 * @return the shortest path, as a list, between the two movies represented
	 *         by their movie ids. This path begins at the movie represented by
	 *         movieId1 and ends with the movie represented by movieId2.
	 */
	public List<Movie> getShortestPath(int movieId1, int movieId2)
			throws NoSuchMovieException, NoPathException {
		int index1 = -1;
		int index2 = -1;
		int[] distances = new int[movies.size()];
		List<Integer> visitedIndexes = new ArrayList<Integer>();
		List<Movie> moviePath =  new ArrayList<Movie>();
		
		//get the index of the nodes for the IDs
		for(int index = 0 ; index < movies.size(); index++){
			if(movies.get(index).hashCode() == movieId1)
				index1 = index;
			if(movies.get(index).hashCode() == movieId2)
				index2 = index;
		}
		
		//check existence of the nodes
		if(index1 == -1 || index2 == -1)
			throw new NoSuchMovieException();
		
		//set every node to distance infinity and the node
		//we are starting from to 0
		for(int index = 0; index < movies.size(); index++){
			if(index == index1)
				distances[index] = 0;
			else
				distances[index] = Integer.MAX_VALUE;
		}
		
		//start at index1
		int currentIndex = index1;
		int count = movies.size();
		
		while (currentIndex != index2 && count > 0){
			
			//for the current index it updates all the distances
			for(Movie M : graph.get(currentIndex)){
				int vertexToUpdate = movies.indexOf(M);
				
				//if we have not yet visited the node
				if(!visitedIndexes.contains(vertexToUpdate)){
					int dist = distances[currentIndex]+weights.get(currentIndex).get(graph.get(currentIndex).indexOf(M));
					
					if(dist < distances[vertexToUpdate])
						distances[vertexToUpdate] = dist;
				}
			}
			
			//once we visited the nodes connected to the current index
			//we add it to visited indexes
			visitedIndexes.add(currentIndex);
			
			//we want to know which node to visit next, it must have the smallest
			//distance and not have been visited yet.
			
			int min = Integer.MAX_VALUE;
			
			for(int index = 0; index < distances.length; index++){
				if(distances[index] < min && !visitedIndexes.contains(index)){
					min = distances[index];
					currentIndex = index;
				}
			}
			
			//we have count to make sure that we do know when to stop
			//in the case that there is no path
			count--;
		}
		
		//we sort the indexes and the distances with respect to the distance shortest distance
		//and they must also be visited
		
		int[] moviesIndexSorted = new int[visitedIndexes.size()];
		int[] remaining = distances.clone();
		int toAdd = 0;
		
		for(count = 0 ; count < visitedIndexes.size(); count++){
			int min = Integer.MAX_VALUE;
			
			//find the smallest of the remaining
			for(int index = 0; index < remaining.length; index++){
				if(remaining[index] < min){
					min = remaining[index];
					toAdd = index;
				}
			}
			
			moviesIndexSorted[count] = toAdd;
			
			if(distances[count] == this.getShortestPathLength(movieId1, movieId2)){
				moviesIndexSorted[count] = index2;
				break;
			}
			
			remaining[toAdd] = Integer.MAX_VALUE;
		}
		
		
		//we now convert this matrix into movie for
		
		for(int I : moviesIndexSorted)
			moviePath.add(movies.get(I));
		
		return moviePath;

	}

	/**
	 * Returns the movie id given the name of the movie. For movies that are not
	 * in English, the name contains the English transliteration original name
	 * and the English translation. A match is found if any one of the two
	 * variations is provided as input. Typically the name string has <English
	 * Translation> (<English Transliteration>) for movies that are not in
	 * English.
	 * 
	 * @param name
	 *            the movie name for the movie whose id is needed.
	 * @return the id for the movie corresponding to the name. If an exact match
	 *         is not found then return the id for the movie with the best match
	 *         in terms of translation/transliteration of the movie name.
	 * @throws NoSuchMovieException
	 *             if the name does not match any movie in the graph.
	 */
	public int getMovieId(String name) throws NoSuchMovieException {
		int id = -1;
		int max = 0;
		
		//checks for exact movie
		for(Movie M : movies){
			String currentName = M.getName();
			if(name.equals(currentName))
				id = currentName.hashCode();
		}
		
		if(id != -1)
			return id;
		
		//checks for title within in the movie
		for(Movie M: movies){
			String currentName = M.getName();
			if(name.contains(name))
				id = currentName.hashCode();
				
		}
		
		if(id != -1)
			return id;
		else 
			throw new NoSuchMovieException();
	}

	// Implement the next two methods for completeness of the MovieGraph ADT

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieGraph other = (MovieGraph) obj;
		if (graph == null) {
			if (other.graph != null)
				return false;
		} else if (!graph.equals(other.graph))
			return false;
		if (movies == null) {
			if (other.movies != null)
				return false;
		} else if (!movies.equals(other.movies))
			return false;
		if (weights == null) {
			if (other.weights != null)
				return false;
		} else if (!weights.equals(other.weights))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int sum = 0;
		for(List<Integer> L : weights){
			for (Integer I : L)
				sum += I;
		}
		
		sum = sum*movies.size();
		
		return sum;
	}

}