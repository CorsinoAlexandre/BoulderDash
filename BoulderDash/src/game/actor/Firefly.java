package game.actor;

import game.Position;
import game.StatusItem;

public class Firefly extends Enemy {

	public Firefly(StatusItem state, Position pos) {
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void explode() {

	}
	
	@Override
	public boolean rotate() {
		return true;
	}
	
}
