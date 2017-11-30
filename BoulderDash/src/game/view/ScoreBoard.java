package game.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Sirve para cargar el top en una matriz.
 *
 */
public class ScoreBoard
{
	private FileInputStream streamin;
	private FileOutputStream streamout;
	private static ScoreBoard scoreboard;

	private ScoreBoard() throws URISyntaxException, FileNotFoundException
	{
		URL filename = this.getClass().getResource("Scoreboard.dat");
		File file = new File(filename.toURI());
		streamin = new FileInputStream(file);
		streamout = new FileOutputStream(file);
	}

	public static ScoreBoard getInstance() throws FileNotFoundException, URISyntaxException
	{
		if (scoreboard == null)
		{
			scoreboard = new ScoreBoard();
		}
		return scoreboard;
	}

	/**
	 * 
	 * @param matrix
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readScorenames(Object[][] matrix) throws IOException, ClassNotFoundException
	{
		if(streamin.available() != 0)
		{
			ObjectInputStream input = null;
			Scorename participant;
			input = new ObjectInputStream(streamin);
	
			int y = 0;
			while (input.available() > 0)
			{
				participant = (Scorename) input.readObject();
				matrix[0][y] = (Object) participant.getRank();
				matrix[1][y] = (Object) participant.getName();
				matrix[2][y] = (Object) participant.getPoints();
				matrix[3][y] = (Object) participant.getTime();
				y++;
			}
		}
	}

	/**
	 * 
	 * 
	 * @param matrix
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void writeScorenames(Object[][] matrix) throws IOException, ClassNotFoundException
	{
		if(true)
		{
			ObjectOutputStream output = null;
	
			output = new ObjectOutputStream(streamout);
			int y;
			for (y = 0; y < matrix[0].length; y++)
			{
				Scorename participant = new Scorename();
				participant.setRank((Integer) matrix[0][y]);
				participant.setName((String) matrix[1][y]);
				participant.setPoints((Integer) matrix[2][y]);
				participant.setTime((Integer) matrix[3][y]);
				output.writeObject(participant);
			}
		}

	}
}
