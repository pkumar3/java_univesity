package F;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public abstract class Item {
	
	private Image image;
	private boolean visible;
	private boolean canPickUp;
	
	//========================================================== Constructors
	public Item(String imageFile, boolean visible, boolean canPickUp) {
		setImage(imageFile);
		setVisible(visible);
		setCanPickUp(canPickUp);	
	}
	
	//========================================================== Methods
	public void draw(Graphics g, int x, int y, int size) {
		if(visible)
			g.drawImage(image, x, y, size, size, null);
	}
	
	//========================================================== Getters / Setters
	public void setImage(String imageFile) {
		System.out.println("setImage: " + "GameImages/" + imageFile);
		image = Toolkit.getDefaultToolkit().getImage("GameImages/" + imageFile);
	}	
	public Image getImage() {
		return image;
	}	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean getCanPickUp() {
		return canPickUp;
	}
	public void setCanPickUp(boolean canPickUp) {
		this.canPickUp = canPickUp;
	}
	
	

}
