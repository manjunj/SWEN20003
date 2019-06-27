import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Units
*/

public class Scout extends Unit{
	private static final String SCOUT_PATH = "assets/units/scout.png";
	private static final double SPEED = 0.3;
	public static final int COST = 5;
	
	public Scout(double x, double y) throws SlickException {
		super(x,y, SCOUT_PATH, SPEED);
	}
	
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) throws SlickException {
		super.update(world, camera, sprites);
	}
	
}
