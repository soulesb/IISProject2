import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class stone implements Serializable {

	private Character color;
	private coordinate location;
	private Set<coordinate> adjacent;
	
	public stone( coordinate location, Character color, Set<coordinate> adjacent ) {
		this.color = color;
		this.location = location;
		this.adjacent = adjacent;
	}

	public stone( stone s ) {
		color = s.getColor();
		location = s.getLocation();
		adjacent = s.getAdj();
	}
	
	public Character getColor( ) {
		return color;
	}
	
	public coordinate getLocation() {
		return location;
	}
	
	public Set<coordinate> getAdj() {
		return adjacent;
	}
	
	@Override public boolean equals( Object o ) {
		if( o instanceof coordinate) {
			if( ((stone) o).getLocation() == location && ((stone) o).getColor().equals(color)) {
				return true;
			}
		}
		return false;
	}
	
	@Override public int hashCode() {
		return ( 32 * location.getRow() * (((int)location.getCol())-64));
	}
}
