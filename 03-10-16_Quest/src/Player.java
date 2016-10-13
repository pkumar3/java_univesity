package F;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Player {

	private MapBlock currentMapBlock;
	private Image image;
	private ArrayList<Item> items = new ArrayList<Item>();

	public Player(String imageFile, MapBlock currentMapBlock) {
		this.currentMapBlock = currentMapBlock;
		image = Toolkit.getDefaultToolkit().getImage("GameImages/" + imageFile);
	}
	
	public int getTreasureTotal() {
		int ret = 0;

		for(Item i : items) 
			if(i instanceof Treasure)
				ret += ((Treasure)i).getAmount();

		return ret;
	}

	public boolean moveTo(MapBlock mb) {
		if(mb.isAllowVisitors()) {
			Item mapBlockItem;
			for(int i = mb.itemCount()-1; i >= 0; i--) {
				mapBlockItem = mb.getItem(i);
				if(mapBlockItem instanceof LockableItem) {
					if( ((LockableItem) mapBlockItem).isLocked() ) {
						for(Item item : items) {
							if(item instanceof Key) {
								if(((Key) item).getKeyType() == ((LockableItem) mapBlockItem).getKeyType()){
									items.remove(item);
									((LockableItem) mapBlockItem).setLocked(false);
									currentMapBlock = mb;
									mb.setSymbol(mb.getSymbol().toLowerCase());
									return true;
								}
							}
						}
						return false;
					}
				}
				if(mapBlockItem.getCanPickUp() && mapBlockItem.isVisible()){
					items.add(mapBlockItem);
					mb.removeItem(i);
					mb.setSymbol(" ");
				}	
			}
			currentMapBlock = mb;
			return true;
		}
		return false;		
	}


	public void draw(Graphics g) {
		g.drawImage(image, getX(), getY(), null);
	}

	public int getX() {
		return currentMapBlock.x;
	}

	public int getY() {
		return currentMapBlock.y;
	}

	public int getCol() {
		return currentMapBlock.getCol();
	}

	public int getRow() {
		return currentMapBlock.getRow();
	}

	public Point getLocation() {
		return new Point(currentMapBlock.x, currentMapBlock.y);
	}

	public void setMapBlock(MapBlock mb) {
		currentMapBlock = mb;
	}
	
	public Rectangle getBounds() {
		return currentMapBlock;
	}

	public MapBlock getCurrentMapBlock() {
		return currentMapBlock;
	}

	public boolean dig() {
		for(Item i : items) 
			if(i instanceof Shovel) {
				boolean ret = currentMapBlock.canDig();
				if(ret) {
					for(int j = currentMapBlock.itemCount()-1;j>=0;j--) {
						Item itm = currentMapBlock.getItem(j);
						itm.setVisible(true);
						items.add(itm);
						currentMapBlock.removeItem(itm);
					}
				}
				return ret;
			}

		
		return false;
	}
}
