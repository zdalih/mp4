package mp4;

import static org.junit.Assert.*;

import org.junit.Test;

//Quick check to see our equals method is working as intended

public class MovieEqualityTest {

	@Test
	public void equal() {
		Movie movie1 = new Movie(123423,"A Movie",1,"www.url.com");
		Movie movie2 = new Movie(123423,"A Movie",1,"www.url.com");
		assertTrue(movie1.equals(movie2));
	}
	
	@Test
	public void notEqual() {
		Movie movie1 = new Movie(12354,"asdfadfasdf",11234,"asdfasdf");
		Movie movie2 = new Movie(123541,"asdfadfasdf",11234,"asdfasdf");
		assertFalse(movie1.equals(movie2));
	}

}
