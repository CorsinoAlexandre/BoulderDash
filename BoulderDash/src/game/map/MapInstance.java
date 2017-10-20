package game.map;

import game.actor.*;
import game.cell.*;
import game.item.*;
import game.map.bdlevel.BDLevelReader;
import game.map.bdlevel.BDTile;
import game.Position;
import game.map.ListActor;
import game.map.ListItem;

public class MapInstance
{
	private static MapInstance single;
	private static BDTile[][] tile;
	private static MapCell cell;
	private static MapItem item;
	private static MapActor actor;

	/**
	 * Constructor para el singleton
	 */
	private MapInstance()
	{
		tile = null;
		cell = null;
		item = null;
		actor = null;
	}

	/**
	 * Metodo singleton
	 */
	public static synchronized MapInstance getInstance()
	{
		if (single == null)
		{
			single = new MapInstance();
		}
		return single;
	}

	/**
	 * Get maps
	 * 
	 * @return
	 */
	public static BDTile[][] getMapTile()
	{
		return tile;
	}
	
	public static MapCell getMapCell()
	{
		return cell;
	}

	public static MapItem getMapItem()
	{
		return item;
	}

	public static MapActor getMapActor()
	{
		return actor;
	}

	/**
	 * Retorna una celda del mapa.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static BDTile getTile(int x, int y)
	{
		return tile[x][y];
	}

	/**
	 * Usado para cargar datos al mapa o cambiar casillas.
	 * 
	 * @param tile
	 *            : de BDlevelreader
	 * @param x
	 * @param y
	 */
	public static void setTile(BDTile til, int x, int y)
	{
		tile[x][y] = til;
	}

	/**
	 * Construye el mapa. Convierte la matriz tiles a 3 matrices. 1 matriz de
	 * actores, 1 matriz de items, 1 matriz de celdas Agrega actores en una
	 * lista y agrega los items en una otra lista para el acceso.
	 * 
	 * @param level
	 *            : nivel.
	 */
	public static void buildTiles(BDLevelReader level)
	{
		Position pos = new Position();
		StatusItem stateItem = new StatusItem();
		StatusActor stateActor = new StatusActor();

		cell = new MapCell(level.getWIDTH(), level.getHEIGHT());
		item = new MapItem(level.getWIDTH(), level.getHEIGHT());
		actor = new MapActor(level.getWIDTH(), level.getHEIGHT());

		ListActor actorList = new ListActor();
		ListItem itemList = new ListItem();

		for (int y = 0; y < level.getHEIGHT(); y++)
		{
			for (int x = 0; x < level.getWIDTH(); x++)
			{
				tile[x][y] = level.getTile(x, y);
				pos.setPos(x, y);
				stateItem.reset(StatusItemEnum.IDLE, true);
				stateActor.reset(StatusActorEnum.IDLE, true);

				switch (tile[x][y])
				{
				case EMPTY:
					cell.setCell(pos, new Dirt(pos, false));
					item.setItem(pos, new Empty(stateItem, pos));
					actor.setActor(pos, null);
					break;
				case DIRT:
					cell.setCell(pos, new Dirt(pos));
					break;
				case TITANIUM:
					cell.setCell(pos, new Titanium(pos));
					break;
				case WALL:
					cell.setCell(pos, new Wall(pos));
					break;
				case ROCK:
					Rock rock = new Rock(stateItem, pos);
					item.setItem(pos, rock);
					itemList.getIt().add(rock);
					break;
				case FALLINGROCK:
					stateItem.setStateEnum(StatusItemEnum.FALLING);
					Rock fallingRock = new Rock(stateItem, pos);
					item.setItem(pos, fallingRock);
					itemList.getIt().add(fallingRock);
					break;
				case DIAMOND:
					Diamond diamond = new Diamond(stateItem, pos);
					item.setItem(pos, diamond);
					itemList.getIt().add(diamond);
					break;
				case FALLINGDIAMOND:
					stateItem.setStateEnum(StatusItemEnum.FALLING);
					Diamond fallingDiamond = new Diamond(stateItem, pos);
					item.setItem(pos, fallingDiamond);
					itemList.getIt().add(fallingDiamond);
					break;
				case AMOEBA:
					Amoeba amoeba = new Amoeba(stateItem, pos);
					item.setItem(pos, amoeba);
					itemList.getIt().add(amoeba);
					break;
				case FIREFLY:
					Firefly firefly = new Firefly(stateActor, pos);
					actor.setActor(pos, firefly);
					actorList.getAc().add(firefly);
					break;
				case BUTTERFLY:
					Butterfly butterfly = new Butterfly(stateActor, pos);
					actor.setActor(pos, butterfly);
					actorList.getAc().add(butterfly);
					break;
				case EXIT:
					cell.setCell(pos, new Exit(pos));
					break;
				case PLAYER:
					Rockford player = new Rockford(stateActor, pos);
					actor.setActor(pos, player);
					actorList.getAc().add(player);
					break;
				default:
					break;
				}

			}
		}
	}
}
