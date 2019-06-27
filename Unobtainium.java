import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Resources
*/

public class Unobtainium extends Resource {
	private static final String UNOBTAINIUM_PATH = "assets/resources/unobtainium_mine.png";
	private static final int INI_QUANTITY = 50;
	public Unobtainium(double x, double y) throws SlickException {
		super(x,y, UNOBTAINIUM_PATH, INI_QUANTITY);
	}
}
