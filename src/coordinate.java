
public class coordinate {

	private int row;
	private Character column;
	
	public coordinate( int r, Character c) {
		row = r;
		column = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public Character getCol() {
		return column;
	}
	
	public void printCoordinate() {
		System.out.println( "Row: " + row + " Column: " + column );
	}
	
	@Override public boolean equals( Object o ) {
		if( o instanceof coordinate) {
			if( ((coordinate) o).getRow() == row && ((coordinate) o).getCol().equals(column)) {
				return true;
			}
		}
		return false;
	}
	
	@Override public int hashCode() {
		return ( 32 * row * ((int)column-64));
	}
	
}
