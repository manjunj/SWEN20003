import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** This class is an extension of answer to project 1
 * Child Class of Sprite and Parent class of Builder, Engineer, Scout and Truck
 * set the motion of unit and update it
*/

public class Unit extends Sprite{
	private double SPEED;
	private double x = super.getX();
	private double y = super.getY();
	
	// Initially, we don't need to move at all
	private double targetX = x;
	private double targetY = y;
	private static final String HIGHLIGHT_PATH= "assets/highlight.png";
	
	public Unit(double x, double y, String path, double SPEED) throws SlickException {
		super(x,y, path, HIGHLIGHT_PATH);
		this.SPEED = SPEED;
	}
	
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) throws SlickException{
		if (this.getHighlight()) {
			Input input = world.getInput();
			// If the mouse button is being clicked, set the target to the cursor location
			if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				setTargetX(camera.screenXToGlobalX(input.getMouseX()));
				setTargetY(camera.screenYToGlobalY(input.getMouseY()));
			}
			// move to the target
			move(getTargetX(),getTargetY(),world);
		// If it is an engineer, it should move back to the mine
		} else if (this.getClass().getName().contentEquals("Engineer")) {
			move(getTargetX(),getTargetY(),world);
		}
	}
	
	
	private void resetTarget() {
		setTargetX(x);
		setTargetY(y);		
	}
	
	
	// help function for movement
	public void move(double targetX, double targetY, World world) {
		// If we're close to our target, reset to our current position
		if (World.distance(x, y, targetX, targetY) <= SPEED) {
			resetTarget();
		} else {
			// Calculate the appropriate x and y distances
			double theta = Math.atan2(targetY - y, targetX - x);
			double dx = (double)Math.cos(theta) * world.getDelta() * SPEED;
			double dy = (double)Math.sin(theta) * world.getDelta() * SPEED;
			// Check the tile is free before moving; otherwise, we stop moving
			if (world.isPositionFree(x + dx, y + dy)) {
				x += dx;
				y += dy;
			} else {
				resetTarget();
			}
		}
		setX(x);
		setY(y);
	}

	public double getTargetX() {
		return targetX;
	}

	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}

	public double getTargetY() {
		return targetY;
	}

	public void setTargetY(double targetY) {
		this.targetY = targetY;
	}	
}
