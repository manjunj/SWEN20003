import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Building
 * The command centre can train (create) most units when selected.
 * When the 1 key is pressed, a scout should begin training at a cost of 5 metal.
 * When the 2 key is pressed, a builder unit should begin training at a cost of 10 metal.
 * When the 3 key is pressed, an engineer should begin training at a cost of 20 metal.
*/

public class CommandCenter extends Building{
	private static final String CENTER_PATH = "assets/buildings/command_centre.png";
	private static final int FIVE_SECONDS = 5000; // in millisecond
	private boolean isTrainable=false;
	private int command;
	private int time=0;
	
	public CommandCenter(double x, double y) throws SlickException{	
		super(x,y, CENTER_PATH);
	}
	
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) throws SlickException {
		Input input = world.getInput();
		// if it is getting highlighted, see if it meets the requirements of training,and find the right command
		if (super.getHighlight()) {
			if(input.isKeyDown(Input.KEY_1)&&world.getMetalQuantity()>=Scout.COST&&!isTrainable) {
				world.setMetalQuantity(world.getMetalQuantity()-Scout.COST);
				isTrainable = true; 
				command = 1;
			}else if(input.isKeyDown(Input.KEY_2)&&world.getMetalQuantity()>=Builder.getCost()&&!isTrainable) {
				world.setMetalQuantity(world.getMetalQuantity()-Builder.getCost());
				isTrainable = true;
				command = 2;
			}else if(input.isKeyDown(Input.KEY_3)&&world.getMetalQuantity()>=Engineer.COST&&!isTrainable) {
				world.setMetalQuantity(world.getMetalQuantity()-Engineer.COST);
				isTrainable = true;
				command = 3;
			}
		}

		// if meets the requirements, wait until 5 seconds pass
		if (isTrainable&&time<FIVE_SECONDS) {
			time+=world.getDelta();
		}
		
		// once 5 seconds has passed, add the new instance and set istrainable to false
		if (time>=FIVE_SECONDS) { 
			isTrainable = false;
			if (command == 1) {
				sprites.add(new Scout(super.getX(),super.getY()));
			} else if (command == 2) {
				sprites.add(new Builder(super.getX(),super.getY()));
			} else if (command == 3) {
				sprites.add(new Engineer(super.getX(),super.getY()));
			}
			time = 0;
		}
	}
	
	public void render(double x, double y, Graphics g) {
		super.render(x, y, g);
		super.setText("1- Create Scout\n2- Create Builder\n3- Create Engineer");
	}
}
