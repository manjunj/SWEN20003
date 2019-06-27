import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Parent Class Sprite
* Handles initialisation, updating and rendering of all sprites
*/

public class Sprite {
	private double x;
	private double y;
	private Image image; // image of each sprite
	private Image highlight; // image of highlight 
	private boolean isHighlight = false;
	private String text=""; //text of actions
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	
	// constructor shows the image of sprite
	public Sprite(double x,double y, String path, String HIGHLIGHT_PATH) throws SlickException{
		setImage(new Image(path));
		if (HIGHLIGHT_PATH!=null) {
			highlight = new Image(HIGHLIGHT_PATH);
		}
		this.setX(x);
		this.setY(y);
	}
 
	 
	public void update(World world, Camera camera, ArrayList<Sprite> sprites) throws SlickException {
	}
	
	public void render(double x, double y, Graphics g) {
		// if the sprite is highlighted, draw the highlight and text
		if (isHighlight) {
			highlight.drawCentered((float)x, (float)y);
			g.drawString(text, 32, 100);
		} 
		// render all the sprites
		getImage().drawCentered((float) x, (float) y);
	}

	public void setHighlight(boolean isHighlight) {
		this.isHighlight = isHighlight;
	}
	
	public boolean getHighlight() {
		return isHighlight;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

}
