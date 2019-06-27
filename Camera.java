import org.newdawn.slick.Input;

/**
 * This class is an extension of answer to project 1
 * This class should be used to restrict the game's view to a subset of the entire world.
 * 
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */
public class Camera {
	private double x = 300;
	private double y = 300;
	private Sprite target;
	
	
	public void followSprite(Sprite target) {
		this.target = target;
	}
	
	// help functions to transfer between global and screen coordinates
	public double globalXToScreenX(double x) {
		return x - this.x;
	}
	public double globalYToScreenY(double y) {
		return y - this.y;
	}

	public double screenXToGlobalX(double x) {
		return x + this.x;
	}
	public double screenYToGlobalY(double y) {
		return y + this.y;
	}
	
	public void update(World world, int delta) {
		Input input = world.getInput();
		// if we press W or S or D or A, the camera should move up or down or right or left
		if (input.isKeyDown(Input.KEY_W) && y>0.4*delta){
			y -= 0.4*delta;
		} else if (input.isKeyDown(Input.KEY_S) && y<world.getMapHeight()-App.WINDOW_HEIGHT-0.4*delta){
			y += 0.4*delta;
		} else if (input.isKeyDown(Input.KEY_D) && x<world.getMapWidth()-App.WINDOW_WIDTH-0.4*delta){
			x += 0.4*delta;
		} else if (input.isKeyDown(Input.KEY_A) && x>0.4*delta){
			x -= 0.4*delta;
		// if we have a target, it should be followed and camera cannot exceed the border
		} else if(target!=null) {
			double targetX = target.getX() - App.WINDOW_WIDTH/2;
			double targetY = target.getY() - App.WINDOW_HEIGHT/2;
			
			x = Math.min(targetX, world.getMapWidth() -	 App.WINDOW_WIDTH);
			x = Math.max(x, 0);
			y = Math.min(targetY, world.getMapHeight() - App.WINDOW_HEIGHT);
			y = Math.max(y, 0);
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
