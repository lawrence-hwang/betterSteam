package tmge;

public abstract class Game {
	private Grid grid;
//	private Events events;
	private int score;
	
	public Game() {
		this.score = 0;
		// this.grid = new Grid(x, y);
		// this.events = new Events();
	}
	
	
	public abstract void update();
}