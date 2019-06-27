import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Sprite
 * The truck can create command centres. 
 * it should take 15 seconds, cost no metal,
 * and when the command centre is complete the truck should be destroyed.
*/


public class Truck extends Unit{
	private static final String TRUCK_PATH = "assets/units/truck.png";
	private static final double SPEED = 0.25;
	private static final int COST_BUILDER = 100;
	private static final int COST_FACTORY = 150;
	private static final int FIFTEEN_SECONDS = 15000;
	private double time=0;
	private boolean isTrainable=false;
	
	public Truck(double x, double y) throws SlickException {
		super(x,y, TRUCK_PATH, SPEED);
	}
	
	public void update(World world, Camera camera,ArrayList<Sprite> sprites) throws SlickException {
		super.update(world, camera, sprites);
		Input input = world.getInput();
		
		// if it is getting highlighted, see if it meets the requirements of training
		if (super.getHighlight()) {
			if(input.isKeyDown(Input.KEY_1)&&!isTrainable) {
				isTrainable = true;
			}
		}
		
		// if meets the requirements, wait until 15 seconds pass
		if (isTrainable && time<FIFTEEN_SECONDS) {
			time+=world.getDelta();
		}
		
		// once 15 seconds has passed, add the new instance and set istrainable to false and remove the truck
		if (time>=FIFTEEN_SECONDS) {
			time = 0;
			sprites.add(new CommandCenter(super.getX(),super.getY()));
			isTrainable = false;
			sprites.remove(this);
		}
	}
	
	public void render(double x, double y, Graphics g) {
		super.render(x, y, g);
		setText("1- Create CommandCenter\n");
	}

	public static int getCostBuilder() {
		return COST_BUILDER;
	}

	public static int getCostFactory() {
		return COST_FACTORY;
	}
}
