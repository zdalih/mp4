package mp4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimpleMovieGraphTest {
	
	@Before
	public void setup(){
		Movie alphaMovie = new Movie(1,"alpha",2001,"www.alpha.com");
		Movie betaMovie = new Movie(2,"beta",2002,"www.beta.com");
		Movie charlieMovie = new Movie(3,"charlie",2003,"www.charlie.com");
		Movie deltaMovie = new Movie(4,"delta",2004,"www.delta.com");
		Movie echoMovie = new Movie(5,"echo",2005,"www.echo.com");
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
