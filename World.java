import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;


/**
 * This class is an extension of answer to project 1
 * This class should be used to contain all the different objects in your game world, and schedule their interactions.
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */
public class World {
	private static final String MAP_PATH = "assets/main.tmx";
	private static final String SOLID_PROPERTY = "solid";
	
	private TiledMap map;
	private Camera camera = new Camera();
	
	private Input lastInput;
	private int lastDelta;
	private int metalQuantity = 0;
	private int unobtainiumQuantity = 0;
	private boolean isSelected = false;
	private int command;
	private int amount = 2;
	
	// get the input
	public Input getInput() {
		return lastInput;
	}
	public int getDelta() {
		return lastDelta;
	}
	
	
	// create an Arraylist of all the sprites
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	// to see if the location is solid
	public boolean isPositionFree(double x, double y) {
		int tileId = map.getTileId(worldXToTileX(x), worldYToTileY(y), 0);
		return !Boolean.parseBoolean(map.getTileProperty(tileId, SOLID_PROPERTY, "false"));
	}
	
	// get the map width in pixels
	public double getMapWidth() {
		return map.getWidth() * map.getTileWidth();
	}
	
	// get the map height in pixels
	public double getMapHeight() {
		return map.getHeight() * map.getTileHeight();
	}
	
	public World() throws SlickException {
		map = new TiledMap(MAP_PATH);
		reader();
	}
	
	// reader reads the csv file and put objects into Arraylist
	public void reader() {
		try (BufferedReader br =
			new BufferedReader(new FileReader("assets/objects.csv"))) { 
				String text;			
				while ((text = br.readLine()) != null) { String cells[] = text.split(",");
					String name = cells[0];
					double x = Double.parseDouble(cells[1]); 
					double y = Double.parseDouble(cells[2]);
					// read the name and allocate it
					if (name.equals("command_centre")){
						sprites.add(new CommandCenter(x, y));
					} else if (name.equals("metal_mine")){
						sprites.add(new Metal(x, y));
					} else if (name.equals("unobtainium_mine")){
						sprites.add(new Unobtainium(x, y));
					} else if (name.equals("pylon")){
						sprites.add(new Pylon(x, y));
					} else if (name.equals("engineer")){
						sprites.add(new Engineer(x, y));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
		} 
	}
	
	// function to find the selected sprite and highlight it
	public void select() {
		double targetX,targetY; 
			if (lastInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				targetX = camera.screenXToGlobalX(lastInput.getMouseX());
				targetY = camera.screenYToGlobalY(lastInput.getMouseY());
				for (Sprite s:sprites) {
					if (s.getClass().getSuperclass().getName().equals("Unit")&&(!isSelected)) {
						if(distance(s.getX(),s.getY(),targetX,targetY)<=32) {
							s.setHighlight(true);
							isSelected = true;
							return;
						} 
					}
				}
				for (Sprite s:sprites) {
					if (s.getClass().getSuperclass().getName().equals("Building")&&(!isSelected)) {
						if(distance(s.getX(),s.getY(),targetX,targetY)<=32) {
							s.setHighlight(true);
							isSelected = true;
							return;
						}
					}
				}
				for (Sprite s:sprites) {
					s.setHighlight(false);
					isSelected = false;
				} 
			}
		}
	
	// update the sprites and camera
	public void update(Input input, int delta) throws SlickException {
		lastInput = input;
		lastDelta = delta;
		select();
		for(int i=0; i<sprites.size();i++) {
			Sprite s = sprites.get(i);
			s.update(this,camera,sprites);
			// if a unit is selected, camera should follow it
			if (s.getHighlight()&&s.getClass().getSuperclass().getName().equals("Unit")) {
				camera.followSprite(s);
				camera.update(this, delta);
				return;
			}
		}
		camera.followSprite(null);
		camera.update(this, delta);
	}
	
	// render text and sprites
	public void render(Graphics g) {
		map.render((int)camera.globalXToScreenX(0),
				   (int)camera.globalYToScreenY(0));
		String c = String.format("Metal: %d\nUnobtainium: %d", metalQuantity, unobtainiumQuantity);
		g.drawString(c,32,32);
		for(Sprite s:sprites) {
			s.render(camera.globalXToScreenX(s.getX()),
					   camera.globalYToScreenY(s.getY()), g);
		}
	}

	// This should probably be in a separate static utilities class, but it's a bit excessive for one method.
	public static double distance(double x1, double y1, double x2, double y2) {
		return (double)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	private int worldXToTileX(double x) {
		return (int)(x / map.getTileWidth());
	}
	private int worldYToTileY(double y) {
		return (int)(y / map.getTileHeight());
	}
	public int getMetalQuantity() {
		return metalQuantity;
	}
	public void setMetalQuantity(int metalQuantity) {
		this.metalQuantity = metalQuantity;
	}
	public int getUnobtainiumQuantity() {
		return unobtainiumQuantity;
	}
	public void setUnobtainiumQuantity(int unobtainiumQuantity) {
		this.unobtainiumQuantity = unobtainiumQuantity;
	}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
