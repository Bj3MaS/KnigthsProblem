package cordinate;

public class Cordinate {

	private int x;
	private int y;
	private int totalMoves;

	public Cordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Cordinate(int x, int y, int totalMoves) {
		this.totalMoves = totalMoves;
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int totalmoves() {
		return totalMoves;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
}
