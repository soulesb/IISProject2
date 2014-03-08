import java.util.HashSet;
import java.util.Set;


public class group {

	private int groupID;
	private Character color;
	private Set<coordinate> liberties;
	private Set< coordinate > members;
	private Set<stone> memStones;
	
	
	public group( stone s, int i ) {
		members = new HashSet<coordinate>();
		liberties = new HashSet<coordinate>();
		memStones = new HashSet<stone>();
		
		members.add(s.getLocation());
		memStones.add(s);
		color = s.getColor();
		groupID = i;
		
		
	}
	
	public group( group g) {
		members = g.getMem();
		liberties = g.getLib();
		color = g.getColor();
	}
	
	public group( group one, group two) {
		one.addLibSet(two.getLib());
		one.addMemSet(two.getMem());
		if( one.getGroupID() < two.getGroupID() ) {
			groupID = one.getGroupID();
		}
		else {
			groupID = two.getGroupID();
		}
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
	
	public int getGroupID() {
		return groupID;
	}
	
	public Set<stone> getStones(){
		return memStones;
	}
	
	public boolean addLib( coordinate c ) {
		boolean temp = liberties.add(c);
		return temp;
	}
	
	public boolean addMem( coordinate c ) {
		boolean temp = members.add(c);
		return temp;
	}
	
	public boolean addStone( stone s ) {
		boolean temp = memStones.add(s);
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
	
	public boolean remStone( stone s ) {
		boolean temp = memStones.remove(s);
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
	
	public boolean addStoneSet( Set<stone> s ) {
		boolean temp = memStones.addAll(s);
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
	
	public boolean remStoneSet( Set<stone> s ) {
		boolean temp = memStones.removeAll(s);
		return temp;
	}
}
