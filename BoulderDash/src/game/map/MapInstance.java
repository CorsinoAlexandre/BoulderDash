package game.map;

import game.actor.*;
import game.cell.*;
import game.item.*;
import game.ListOfEntities;
import game.Entity;
import game.map.bdlevel.BDLevelReader;
import game.Position;

/**
 * Esta clase contiene la instancia del mapa principal del juego, y controla la
 * gran parte del movimiento del juego Es singleton, y contiene a un mapa de
 * celdas, items y actores, junto con una lista con todas las entidades vivas
 */
public class MapInstance
{
	private static MapInstance singleton;
	private static ListOfEntities entitiesAlive;

	// CONSTRUCTOR

	private MapInstance()
	{
		entitiesAlive = null;
	}

	// SINGLETON

	/**
	 * Devuelve la unica instancia del mapa
	 * 
	 * @return el singleton de la clase
	 */
	public static MapInstance getInstance()
	{
		// Si la instancia no se creo, se crea, y se devuelve la instancia
		if (singleton == null)
		{
			singleton = new MapInstance();
		}
		return singleton;
	}
	

	// INICIALIZACION

	/**
	 * Con este metodo, creo cada uno de los mapas para que se puedan usar
	 * independientemente En conjunto con una lista con todas las entidades que
	 * se mueven
	 * 
	 * @param levels:
	 *            El lector de niveles del juego
	 */
	public static void start(BDLevelReader levels)
	{
		MapCell.getInstance().start(levels);
		MapItem.getInstance().start(levels);
		MapActor.getInstance().start(levels);
		entitiesAlive = ListOfEntities.getInstance();
		ListOfEntities.start();
	}

	// GETTERS

	public static ListOfEntities getEntitiesActive()
	{
		return entitiesAlive;
	}

	// SETTERS
	
	public static void kill(Position pos)
	{
		MapCell.getCell(pos.getX(), pos.getY()).clear();
		MapItem.getItem(pos.getX(), pos.getY()).die();
		MapActor.getActor(pos.getX(), pos.getY()).die();
	}
	
	public static void kill(Integer x, Integer y)
	{
		MapCell.getCell(x, y).clear();
		MapItem.getItem(x, y).die();
		if (MapActor.getActor(x, y) != null)
		{
			MapActor.getActor(x, y).die();
		}
	}

	// SOLID
	
	public static int solid(Integer x, Integer y)
	{
		int a, b, c, d;
		a = MapCell.getCell(x, y).getSolid();
		b = MapItem.getItem(x, y).getSolid();
		if (MapActor.getActor(x, y) != null)
		{
			c = MapActor.getActor(x, y).getSolid();
		}
		else
		{
			c = 0;
		}

		d = a > b ? a : b;
		d = d > c ? d : c;
		return d;
	}
	
	// TURNOS

	/**
	 * Actualiza el mapa. Actualiza la posicion de todas las entidades en la
	 * matriz.
	 */
	public static void refresh()
	{
		int i;
		for (i = 0; i < ListOfEntities.getList().size(); ++i)
		{
			Entity ent = ListOfEntities.getList().get(i);
			ent.changePosition();
		}
	}

	// MAPA

	/**
	 * Construye el mapa. Convierte la matriz tiles a 3 matrices. 1 matriz de
	 * actores, 1 matriz de items, 1 matriz de celdas. Agrega actores y items en
	 * una lista de entities.
	 * 
	 * @param level
	 *            : nivel.
	 */
	public static void buildMap(BDLevelReader level)
	{
		// Para armar el mapa, voy por todo el nivel
		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				// Hago un nuevo status para el item/actor nuevo, y uso la
				// posicion actual
				Position pos = new Position();

				pos.setXY(x, y);
				// y dependiendo de lo que se encuentre, se guarda en cada uno
				// de los mapas
				switch (level.getTile(x, y))
				{
					case EMPTY:
						MapCell.setCell(new Dirt(pos, false));
						MapItem.setItem(new Empty(pos));
						MapActor.removeActor(pos);
						break;
					case DIRT:
						MapCell.setCell(new Dirt(pos));
						break;
					case TITANIUM:
						MapCell.setCell(new Titanium(pos));
						break;
					case WALL:
						MapCell.setCell(new Wall(pos));
						break;
					case ROCK:
						Rock rock = new Rock(pos);
						MapItem.setItem(rock);
						ListOfEntities.getList().add(rock);
						break;
					case FALLINGROCK:
						Rock fallingRock = new Rock(pos, StatusFallableEnum.FALLING);
						MapItem.setItem(fallingRock);
						ListOfEntities.getList().add(fallingRock);
						break;
					case DIAMOND:
						Diamond diamond = new Diamond(pos);
						MapItem.setItem(diamond);
						ListOfEntities.getList().add(diamond);
						break;
					case FALLINGDIAMOND:
						Diamond fallingDiamond = new Diamond(pos,StatusFallableEnum.FALLING);
						MapItem.setItem(fallingDiamond);
						ListOfEntities.getList().add(fallingDiamond);
						break;
					case AMOEBA:
						Amoeba amoeba = new Amoeba(pos);
						MapItem.setItem(amoeba);
						ListOfEntities.getList().add(amoeba);
						break;
					case FIREFLY:
						Firefly firefly = new Firefly(pos);
						MapActor.setActor(firefly);
						ListOfEntities.getList().add(firefly);
						break;
					case BUTTERFLY:
						Butterfly butterfly = new Butterfly(pos);
						MapActor.setActor(butterfly);
						ListOfEntities.getList().add(butterfly);
						break;
					case EXIT:
						MapCell.setCell(new Exit(pos));
						break;
					case PLAYER:
						Rockford player = new Rockford(pos);
						MapActor.setActor(player);
						ListOfEntities.getList().add(player);
						break;
					default:
						break;
				}

			}
		}
	}

}
