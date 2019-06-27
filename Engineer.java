import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Units
 * The engineer can mine metal and unobtainium. 
 * When the engineer has spent 5 seconds near a resource, 
 * the engineer starts carrying some of the resource.
*/

public class Engineer extends Unit{
	private static final String ENGINEER_PATH = "assets/units/engineer.png";
	private static final double SPEED = 0.1;
	private int time = 0;
	private static final int FIVE_SECONDS = 5000;
	private Resource resource;
	public static final int COST = 20;
	
	public Engineer(double x, double y) throws SlickException {
		super(x,y, ENGINEER_PATH, SPEED);
	}
	
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) throws SlickException {
		super.update(world, camera, sprites);
		// when it has not yet mining, check resources nearby. If a pylon is activated, amount increments
		if (time<FIVE_SECONDS) {
			for (Sprite s:sprites) {
				if(s.getClass().getSuperclass().getName().equals("Resource")){
					if (World.distance(s.getX(),s.getY(),super.getX(),super.getY())<=32) {
						time += world.getDelta();
						resource = (Resource) s; 
					}
				}
				else if(s.getClass().getName().equals("Pylon")) {
					if (((Pylon)s).getisActivate()&&!((Pylon)s).getisCount()) {
						world.setAmount(world.getAmount() + 1);
						((Pylon)s).setisCount(true);
					}
				}
			}
		}
		// when it finishes mining, go to the nearest command center automatically
		else {
			double minDistance = Math.sqrt(Math.pow(world.getMapHeight(), 2)+Math.pow(world.getMapHeight(),2));
			for (Sprite z:sprites) {
				if (z.getClass().getName().equals("CommandCenter")) {
					if (World.distance(z.getX(), z.getY(), super.getX(), super.getY())<minDistance) {
						minDistance = World.distance(z.getX(), z.getY(), super.getX(), super.getY());
						setTargetX(z.getX());
						setTargetY(z.getY()); 
					}
				}
			}
			// once it finishes, set the new quantity of mine and resources
			if (World.distance(getTargetX(), getTargetY(), super.getX(), super.getY())< SPEED) {		
				if (resource.getClass().getName().equals("Metal")) {
					world.setMetalQuantity(world.getMetalQuantity() + world.getAmount());
					resource.setQuantity(resource.getQuantity()-world.getAmount());
				}
				else if (resource.getClass().getName().equals("Unobtainium")) {
					world.setUnobtainiumQuantity(world.getUnobtainiumQuantity() + world.getAmount());
					resource.setQuantity(resource.getQuantity()-world.getAmount());
				}
				time=0;
				setTargetX(resource.getX());
				setTargetY(resource.getY());
			}
		}
	}

}
