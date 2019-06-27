import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Parent class of CommandCenter, Factory and Pylon
 * set highlight path and render buildings
*/
public class Building extends Sprite {
	
	// set the highlight path for all buildings
	private static final String HIGHLIGHT_PATH = "assets/highlight_large.png";

	public Building(double x, double y, String path) throws SlickException {
		super(x,y, path, HIGHLIGHT_PATH);
	}
	public void render(double x, double y, Graphics g) {
		super.render(x, y, g);
	}
}
