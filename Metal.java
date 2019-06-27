import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Resources
*/

public class Metal extends Resource{
	private static final String METAL_PATH = "assets/resources/metal_mine.png";
	private static final int INI_QUANTITY = 500;
	public Metal(double x, double y) throws SlickException {
		super(x,y, METAL_PATH, INI_QUANTITY);
	}
}
