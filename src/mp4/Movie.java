package mp4;

public class Movie {
	private final int id;
	private final String name;
	private final int releaseYear;
	private final String imdbUrl;

	/**
	 * Create a new Movie object with the given information.
	 * 
	 * @param id
	 *            the movie id
	 * @param name
	 *            the name of the movie
	 * @param releaseYear
	 *            the year of the movie's release
	 * @param imdbUrl
	 *            the movie's IMDb URL
	 */
	public Movie(int id, String name, int releaseYear, String imdbUrl) {
		this.id = id;
		this.name = name;
		this.releaseYear = releaseYear;
		this.imdbUrl = imdbUrl;
	}

	/**
	 * Return the name of the movie
	 * 
	 * @return movie name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * hashCode for equality testing
	 */
	@Override
	public int hashCode() {
		return id;
	}
	
	/**
	 * Method to check if two Movie objects are equal
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id != other.id)
			return false;
		if (imdbUrl == null) {
			if (other.imdbUrl != null)
				return false;
		} else if (!imdbUrl.equals(other.imdbUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		return true;
	}
	
}
