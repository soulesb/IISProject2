import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class influenceMap {

	
	private Map< coordinate, Integer > iR;
	private int size;
	
	public influenceMap(int s) {
		iR = new HashMap<coordinate, Integer>( (s*s));
		size = s;
		
	}
	
	public boolean addStone( coordinate c, Character color ) {
		if(iR.containsKey(c)) {
			int temp = iR.get(c);
			if( temp <= 89 && temp >= 11) {
				if( color.equals('B')) {
					iR.put(c, 90);
					//update map
					//pulse(c, 0, 90, visited);
					return true;
				}
				else if( color.equals('W')) {
					iR.put(c, 10);
					//update map
					//pulse(c, 0, 10, visited);
					return true;
				}
			}
		}
		else {
			if( color.equals('B')) {
				iR.put(c, 90);
				//update map
				//pulse(c, 0, 90, visited);
				return true;
			}
			else if( color.equals('W')) {
				iR.put(c, 10);
				//update map
				//pulse(c, 0, 10, visited);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeStone( coordinate c ) {
		if( iR.containsKey(c)) {
			int temp = iR.get(c);
			if( temp == 90 || temp == 10 ) {
				iR.put(c, 0); // 0 is a value that indicates the tile needs to be updated
				return true;
			}
		}
		return false;
	}
	
	public void pulse( coordinate c, int depth, int value, Set<coordinate> visited ) {
		int maxDepth = 3;
		visited.add(c);
		
		Set<coordinate> adj = new HashSet<coordinate>();
		//adj.addAll(getAdj( c ));
		
		Iterator<coordinate> it = adj.iterator();
		
		while( it.hasNext() ) {
			coordinate a = it.next();
			
			if( visited.contains(a) ) {
				adj.remove(a);
			}
			else if( !isInMap(a) ) {
				adj.remove(a);
			}
			else {
				int locVal = iR.get(a);
				if( iR.containsKey(a)) { 
					/// a is a location with a value
					/// check if it is a stone, if so remove it from the adj list
					
					if( locVal == 10 || locVal == 90 ) {
					adj.remove(a);
					}
					/// if not a stone change the value and pulse from that location if depth has not been reached
					else {
						if( value == 90 ) {
							locVal += 3;
								iR.put(c, locVal);
								if( depth < maxDepth ) {
									pulse( a, depth+1, value, visited);
								}
						}
						else {
							locVal -= 3;
							iR.put(c, locVal);
							if( depth < maxDepth ) {
								pulse( a, depth+1, value, visited);
							}
						}
					}
				}
				else {
					/// not a location already in the map, initialized with a modified value
					if( value == 90 ) {
						iR.put(a, 57);
					}
					else {
						iR.put(a, 52);
					}
				}
			}
			adj.remove(a);
		}
	}
	
	/*
	public Set<coordinate> getAdj(coordinate c) {
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
		if( c.getCol() != 1) {
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
	*/

	public Map<coordinate,Integer> getIMap() {
		return iR;
	}
	
	public int getIMVal( coordinate c ) {
		if( iR.containsKey(c)) {
			return iR.get(c);
		}
		return -1;
	}
	
	private boolean isInMap( coordinate c ) {
		int size = (int) Math.sqrt( (double)iR.size());
		int row = c.getRow();
		int column = c.getCol();
		column -= 64;
		
		if( row >= 1 && row <= (size+1) ) {
			if (column >= 1 && column <= (size+1)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isStone( coordinate c ) {
		if( iR.containsKey(c) ) {
			if( iR.get(c) == 10 || iR.get(c) == 90 ) {
				return true;
			}
		}
		return false;
	}
	
	private Character convIntToChar( int i ) {
		/// converts an int to the corresponding letter
		char temp = (char) (i + 'A');
		return temp;
	}

	public void modInfVal(coordinate c, Character color, int mod) {
		if( mod == 1) {
			if( color.equals('B')) {
				if( iR.containsKey(c)) {
					if( !isStone( c ) ) {
						int i = iR.get(c);
						i += 3;
						iR.put(c, i);
					}
				}
				else {
					iR.put(c, 57);
				}
			}
			else {
				if( iR.containsKey(c)) {
					if( !isStone( c )) {
						int i = iR.get(c);
						i -= 3;
						iR.put(c, i);
					}
				}	
				else {
					iR.put(c, 52);
				}
			}
		}
		else {
			/// reverse the modification operation
			if( color.equals('B')) {
				if( iR.containsKey(c)) {
					if( !isStone( c ) ) {
						int i = iR.get(c);
						i -= 3;
						iR.put(c, i);
					}
				}
			}
			else {
				if( iR.containsKey(c)) {
					if( !isStone( c )) {
						int i = iR.get(c);
						i += 3;
						iR.put(c, i);
					}
				}
			}
		}
	}
}
