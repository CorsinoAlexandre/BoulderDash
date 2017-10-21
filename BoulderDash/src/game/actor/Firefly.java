package game.actor;

import game.Position;
import game.item.StatusItem;
import game.map.ActiveActors;
import game.map.MapInstance;

public class Firefly extends Enemy
{

	public Firefly(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	// S
	
	public boolean rotate()
	{
		return true;
	}

	// S
	
	public void explode()
	{
		if (this.state.isAlive()==false) {
			ActiveActors list=MapInstance.getActorsActive();
			int i;
			for (i = 0 ; i < list.getList().size(); ++i) {
				if (this.isInRange(list.getList().get(i).getPosition())==true) {
					//cambiar objeto en el mapa y setear el estado del objeto como dead, o exploding
					//si es una luciernaga o una mariposa
				}
			}
		}
	}

}
