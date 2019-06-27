import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Unit
 * The builder can create factories to train trucks.
 * When the builder is selected and the 1 key is pressed,
 * the builder starts building at a cost of 100 metal.
 * After 10 seconds, a factory should be created at the builder’s current location.
*/

public class Builder extends Unit{
	private static final String BUILDER_PATH = "assets/units/builder.png";
	private static final double SPEED = 0.1;
	private static final int COST = 10;
	private static final int TEN_SECONDS = 10000; // in millisecond
	private double time = 0;
	private boolean isTrainable=false; // to see if anything is training right now
	
	public Builder(double x , double y) throws SlickException{
		super(x,y, BUILDER_PATH, SPEED);
	}
	
	public void update(World world, Camera camera,ArrayList<Sprite> sprites) throws SlickException {
		super.update(world, camera, sprites);
		Input input = world.getInput();
		
		// if it is getting highlighted, see if it meets the requirements of training
		if (super.getHighlight()) {
			if(input.isKeyDown(Input.KEY_1)&&world.getMetalQuantity()>=Truck.getCostBuilder()&&!isTrainable) {
				world.setMetalQuantity(world.getMetalQuantity()-Truck.getCostBuilder());
				isTrainable = true;
			}
		}
		// if meets the requirements, wait until 10 seconds pass
		if (isTrainable&&time<TEN_SECONDS) {
			time+=world.getDelta();
		}
		// once 10 seconds has passed, add the new instance and set istrainable to false
		if (time>=TEN_SECONDS) {
			time = 0;
			sprites.add(new Factory(super.getX(),super.getY()));
			isTrainable = false;
		}
	}
	
	public void render(double x, double y, Graphics g) {
		super.render(x, y, g);
		setText("1- Create Factory\n");
	}

	public static int getCost() {
		return COST;
	}
	
}
