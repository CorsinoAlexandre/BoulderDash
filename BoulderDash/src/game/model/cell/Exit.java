package game.model.cell;

import game.model.Position;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapInstance;

/**
 * Clase de la celda salida
 */
public class Exit extends Cell
{

	private boolean isOpen;
	private static Exit exit;

	/**
	 * Constructor de la salida.
	 * 
	 * @param pos
	 */
	private Exit()
	{
		super(new Position(0, 0));
		this.setSpritechar(SpriteChar.E);
		this.isOpen = false;
	}

	/**
	 * Singleton salida.
	 * 
	 * @return
	 */
	public static Exit getInstance()
	{
		if (exit == null)
		{
			exit = new Exit();
		}
		return exit;
	}

	/**
	 * Resetea la salida con sus valores por defecto.
	 */
	public void reset()
	{
		this.setPosition(new Position(0, 0));
		this.setSpritechar(SpriteChar.E);
		this.isOpen = false;
	}

	/**
	 * Abre la salida.
	 */
	public void open()
	{
		Rockford player = Rockford.getRockford();
		if (player.getDiamonds() >= MapInstance.getLevelReader().getDiamondsNeeded())
		{
			this.setSpritechar(SpriteChar.e);
			this.isOpen = true;
		}
	}

	/**
	 * Devuelve si la salida esta abierta para el jugador.
	 * 
	 * @return isOpen
	 */
	public boolean isOpen()
	{
		return isOpen;
	}

}
