package game.model;

import java.util.Timer;
import java.util.TimerTask;

import game.model.actor.Rockford;
import game.model.actor.StatusActorEnum;
import game.model.cell.Exit;
import game.model.map.MapInstance;
import game.view.FrameEnd;
import game.view.FrameMap;

public class GameThread extends TimerTask
{
	private int turn = 0;
	private Timer timer;
	private boolean stop = false;
	
	int currentlevel = MapInstance.getSelectedLevel();
	boolean lost = false;
	boolean won = false;
	Rockford player = Rockford.getRockford();

	public GameThread(Timer timer) {
		this.timer = timer;
    }

	public void run()
	{
		turn++;
		FrameMap.remove();
		if (!stop)
		{
			if (!lost && !won)
			{
				MapInstance.refresh();
				FrameMap.refresh();

				if (player != null)
				{
					won = player.isInExit();
				}
				if (Rockford.getRockford().getState() == StatusActorEnum.DEAD)
				{
					lost = true;
				}

				if (MapInstance.getTimer() == 0)
				{
					Rockford.getRockford().die();
				}
				Exit.open();
				System.out.println(turn);
			}
			else if (lost)
			{
				MapInstance.refresh();
				FrameMap.refresh();
				if (Rockford.getRockford().getLives() == 0)
				{
					stop = true;
				}
				MapInstance.buildSelectedLevel(currentlevel);
				lost = false;
			}
			else if (won)
			{
				MapInstance.refresh();
				FrameMap.refresh();
				won = false;
				MapInstance.buildSelectedLevel(++currentlevel);
			}
		}
		else
		{
			Integer time = turn;
			FrameMap.getInstance().setVisible(false);
			FrameEnd.getInstance();
			FrameEnd.setTime(time);
			FrameEnd.main(null);
			FrameMap.disposeFrame();
			timer.cancel();
		}	
	}

}