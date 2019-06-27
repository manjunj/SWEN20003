import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Building
 * The factory can train trucks.
 * When the 1 key is pressed, a truck should begin training at a cost of 150 metal.
*/

public class Factory extends Building{
	private static final String FACTORY_PATH = "assets/buildings/factory.png";
	private static final int FIVE_SECONDS = 5000;
	private int time=0;
	private boolean isTrainable=false;
	
	public Factory(double x, double y) throws SlickException {
		super(x,y, FACTORY_PATH);
	}
	
	public void update(World world, Camera camera,ArrayList<Sprite> sprites) throws SlickException {
		super.update(world, camera, sprites);
		Input input = world.getInput();
		
		// if it is getting highlighted, see if it meets the requirements of training
		if (super.getHighlight()) {
			if(input.isKeyDown(Input.KEY_1)&&world.getMetalQuantity()>=Truck.getCostFactory()&&!isTrainable) {
				world.setMetalQuantity(world.getMetalQuantity()-Truck.getCostFactory());
				isTrainable = true;
			}
		}
		
		// if meets the requirements, wait until 5 seconds pass
		if (isTrainable&&time<FIVE_SECONDS) {
			time+=world.getDelta();
		}
		
		// once 5 seconds has passed, add the new instance and set istrainable to false
		if (time>=FIVE_SECONDS) {
			time = 0;
			sprites.add(new Truck(super.getX(),super.getY()));
			isTrainable = false;
		}
	}
	
	public void render(double x, double y, Graphics g) {
		super.render(x, y, g);
		setText("1- Create Truck\n");
	}
	
}