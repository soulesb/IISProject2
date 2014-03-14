
public class scoreGo {

	private int blackScore;
	private int whiteScore;
	
	public scoreGo() {
		blackScore = 0;
		whiteScore = 0;
	}
	
	public void incBScore() {
		blackScore++;
	}
	
	public void incWScore() {
		whiteScore++;
	}
	
	public void modBScore( int modVal ) {
		blackScore = blackScore + modVal;
	}
	
	public void modWScore( int modVal ) {
		whiteScore = whiteScore + modVal;
	}
	
	public int getBScore() {
		return blackScore;
	}
	
	public int getWScore() {
		return whiteScore;
	}
}
