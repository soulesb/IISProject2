import java.util.HashSet;
import java.util.Set;


public class group {

	private Character color;
	private Set<coordinate> liberties;
	private Set< coordinate > members;
	
	
	public group( coordinate c, char color ) {
		members = new HashSet<coordinate>();
		liberties = new HashSet<coordinate>();
		members.add(c);
		this.color = color;
	}
	
	public group( group g) {
		members = g.getMem();
		liberties = g.getLib();
		color = g.getColor();
	}
	
	public group( group one, group two) {
		one.addLibSet(two.getLib());
		one.addMemSet(two.getMem());
	}
	
	public Character getColor() {
		return color;
	}
	
	public Set<coordinate> getLib() {
		return liberties;
	}
	
	public Set<coordinate> getMem() {
		return members;
	}
	
	public boolean addLib( coordinate c ) {
		boolean temp = liberties.add(c);
		return temp;
	}
	
	public boolean addMem( coordinate c ) {
		boolean temp = members.add(c);
		return temp;
	}
	
	public boolean remLib( coordinate c ) {
		boolean temp = liberties.remove(c);
		return temp;
	}
	
	public boolean remMem( coordinate c ) {
		boolean temp = members.remove(c);
		return temp;
	}
	
	public boolean addLibSet( Set<coordinate> s ) {
		boolean temp = liberties.addAll(s);
		return temp;
	}
	
	public boolean addMemSet( Set<coordinate> s ) {
		boolean temp = members.addAll(s);
		return temp;
	}
	
	public boolean remLib( Set<coordinate> s ) {
		boolean temp = liberties.removeAll(s);
		return temp;
	}
	
	public boolean remMem( Set<coordinate> s ) {
		boolean temp = members.removeAll(s);
		return temp;
	}
}
