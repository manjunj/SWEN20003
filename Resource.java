import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/** Child Class of Sprite and Parent class of Unobtainium and Metal
 * store the quantity of each resource, and update them
*/

public class Resource extends Sprite{
	
	private int quantity;
	
	public Resource(double x, double y, String path, int quantity) throws SlickException {
		super(x,y, path, null);
		this.quantity = quantity;
	}
	
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) {
		// if the resource is exhausted, remove this instance
		if (this.quantity==0) {
			sprites.remove(this);  
		} 
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
}

