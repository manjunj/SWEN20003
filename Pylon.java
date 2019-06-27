import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Building
 * The pylon cannot be built. Instead, they are already on the map. 
 * When a unit comes within 32 pixels of the pylon, 
 * the pylon becomes activated and should change to the active image (permanently).
 *  Each active pylon should allow all engineers to carry 1 additional resource.
*/

public class Pylon extends Building{
	private static final String PYLON_PATH = "assets/buildings/pylon.png";
	private static final String PYLON_ACTIVATE = "assets/buildings/pylon_active.png";
	private boolean isActivate = false;
	private boolean isCount = false;
	public Pylon(double x, double y) throws SlickException {
		super(x,y, PYLON_PATH);
	}
	
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) throws SlickException {
		super.update(world, camera, sprites);
		// when a unit is approaching the resource, pylon becomes activate and change its image
		for (Sprite s:sprites) {
			if(s.getClass().getSuperclass().getName().equals("Unit")){
				if (World.distance(s.getX(),s.getY(),super.getX(),super.getY())<=32) {
					setImage(new Image (PYLON_ACTIVATE));
					setActivate(true);
				}
			}
		}
	}

	public boolean getisActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	
	public boolean getisCount() {
		return isCount;
	}

	public void setisCount(boolean isCount) {
		this.isCount = isCount;
	}
	
}
