import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


public class board {

	private int size;
	private int numMoves;
	private Map< coordinate,group > boardRep;
	private Map< coordinate, Integer > influenceRep;
	private ArrayList< group > boardGroups;
	
	public board( int size) {
		/// main constructor
		this.size = size;
		boardRep = new HashMap<coordinate,group>( (size*size));
		boardGroups = new ArrayList<group>();
		numMoves = 0;
	}
	
	public board( board preFig) {
		/// constructor for a board copy
		
	}
	
	public boolean isMoveValid( coordinate loc, Character color) {
		/// checks if the coordinate is available and returns true if it is
		/// needs to check if the played stone will have any liberties using the adj function
		
		int col = loc.getCol();
		col -= 65;
		if(loc.getRow() < (size+1) && col < (size) ) {
			if( !boardRep.containsKey(loc)) {
				return true;
			}
		}
		return false;
	}
	
	public void move( coordinate loc, Character color ) {
		/// puts the stone on the board at the coordinate
		
		Set<coordinate> adj = getAdj(loc);
		stone s = new stone( loc, color, adj );
		
		group g = new group( s, numMoves );
		
		addGroup(g);
		
		numMoves++;
	}
	
	
	
	public void capGroup( group prisoner ) {
		/// replaces the representation of a group on the board with . and removes the group from the boardGroups struct
		boardGroups.remove(prisoner);
		
		/// loop to remove the stones from the board representation
		Set<coordinate> stones = prisoner.getMem();
		
		for( coordinate c : stones) {
			boardRep.remove(c);
		}
		
	}
	
	public boolean addGroup( group g ) {
		Set<coordinate> mem = g.getMem();
		Set<group> adjGroups = new HashSet<group>();
		for( coordinate c : mem ) {
			Set<group> s = getAdjGroup(c);
			adjGroups.addAll(s);
		}
		if( !adjGroups.isEmpty() ) {
			for( group tempG: adjGroups ) {
				if( tempG.getColor().equals(g.getColor())) {
					g.addMemSet(tempG.getMem());
					boardGroups.remove(tempG);
				}
			}
			mem = g.getMem();
			for( coordinate c: mem ) {
				boardRep.put(c, g);
			}
		}
		else {
			for( coordinate c: mem ) {
				boardRep.put(c, g);
			}
		}
		boolean temp = boardGroups.add(g);
		return temp;
	}
	
	public Set<group> getAdjGroup( coordinate c ) {
		Set<group> adjGroups = new HashSet<group>();
		Set<coordinate> adj = getAdj(c);
		for( coordinate coord : adj ) {
			if( boardRep.containsKey(coord)) {
				adjGroups.add(boardRep.get(coord));
			}
		}
		return adjGroups;
	}
	
	public Set<coordinate> getAdj( coordinate c ) {
		Set<coordinate> adjacent = new HashSet<coordinate>();
		if( c.getRow() != 1) {
			/// add the north intersection
			coordinate n = new coordinate((c.getRow()+1), c.getCol());
			adjacent.add(n);
		}
		if( c.getRow() != size) {
			/// add the south intersection
			coordinate s = new coordinate((c.getRow()-1), c.getCol());
			adjacent.add(s);
		}
		if( !c.getCol().equals('A')) {
			/// add the west intersection
			int i = c.getCol();
			i--;
			coordinate w = new coordinate(c.getRow(),convIntToChar(i));
			adjacent.add(w);
		}
		if( !c.getCol().equals(convIntToChar(size-1))) {
			/// add the east intersection
			int j = c.getCol();
			j++;
			coordinate e = new coordinate(c.getRow(),convIntToChar(j));
			adjacent.add(e);
		}
		
		
		return adjacent;
	}
	
	public Set<coordinate> getGLib( group g ) {
		/*
		Set<coordinate> totalLib = new HashSet<coordinate>();
		Set<coordinate> membStones = g.getMem();
		for( coordinate c : membStones ) {
			totalLib.addAll(getSLib(c, g));
		}
		
		return totalLib;
		*/
		
		Set<coordinate> totalLib = new HashSet<coordinate>();
		Set<stone> membStones = g.getStones();
		for( stone s: membStones){
			totalLib.addAll(getSLib(s));
		}
		return totalLib;
	}
	
	public Set<coordinate> getSLib( coordinate c, group g ) {
		Set< coordinate > members = g.getMem();
		Set<coordinate> adj = getAdj(c);
		Character color = boardRep.get(c).getColor();
		
		for( coordinate tempC : adj ) {
			
			/// remove all non-empty intesections
			if( boardRep.containsKey(tempC) ) {
				adj.remove(tempC);
			}
		}
		
		return adj;
	}
	
	public Set<coordinate> getSLib( stone s ) {
		Set< coordinate > adj = s.getAdj();
		for( coordinate c: adj) {
			if( boardRep.containsKey(c)) {
				adj.remove(c);
			}
		}
		return adj;
	}
	
	public int getNumMoves() {
		return numMoves;
	}
	
	public void getInfluence(){
		
	}
	
	public void getTerritory(){
		
	}
	
	public boolean isEmpty() {
		return boardGroups.isEmpty();
	}
	
	public void printBoard() {
		/// print out a representation of the board
		
		System.out.print("   ");
		for( int i = 0; i < size; i ++) {
			char t = convIntToChar(i);
			System.out.print(t + " ");
		}
		
		for( int row = 1; row < (size+1); row++ ) {
			if( row > 9) {
				System.out.print("\n" + row + " ");
			}
			else {
				System.out.print("\n " + row + " ");
			}
			for( int col = 0; col < size; col++) {
				Character colChar = convIntToChar( col );
				coordinate temp = new coordinate( row, colChar );
				
				if( boardRep.containsKey(temp) ) {
					char color = boardRep.get(temp).getColor();
					System.out.print(color + " ");
					
				}
				else {
					System.out.print(". ");
				}
			}
		}
	}
	
	private Character convIntToChar( int i ) {
		/// converts an int to the corresponding letter
		char temp = (char) (i + 'A');
		return temp;
	}
	
}
