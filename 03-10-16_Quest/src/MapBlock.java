package F;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MapBlock extends Rectangle {

	public static int SIZE = 48;

	private ArrayList<Item> items = new ArrayList<Item>();
	private Image image;
	private boolean visible;
	private boolean allowVisitors;
	private String symbol;
	
	public MapBlock(int col, 
					int row, 
					String imageFile, 
					boolean visible, 
					boolean allowVisitors,
					String symbol) {
		setCol(col);
		setRow(row);
		setVisible(visible);
		setAllowVisitors(allowVisitors);
		setImage(imageFile);
		setSymbol(symbol);
		setSize(SIZE);
	} 
	
	public void save(PrintWriter pw) {
		pw.print(symbol);
	}
	
	public void setImage(String imageFile) {
		image = Toolkit.getDefaultToolkit().getImage("GameImages/" + imageFile);
	}
	
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		if(items.contains(item))
			items.remove(item);
		
		// or a faster way:
		//try {items.remove(item);} catch (Exception e) {}
	}
	
	public void removeItem(int index) {
		if(index >= 0 && index < items.size())
			items.remove(index);
	}
	
	public int itemCount() {
		return items.size();
	}
	
	public Item getItem(int index) {
		if(index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}

	public Item pickupItem(int index) {
		Item ret = null;
		if(index >= 0 && index < items.size()) {
			ret = items.get(index);
			if(ret.getCanPickUp() && ret.isVisible())
				items.remove(index);
			else
				ret = null;
		}
		return ret;
	}

	public void draw(Graphics g) {
		if(visible) {
			g.drawImage(image, x, y, SIZE, SIZE, null);
			for(Item i : items)
				i.draw(g, x, y, SIZE);
		}
	} 
	
	public void setSize(int size) {
		SIZE = size;
		width = size;
		height = size;
	}

	public void setCol(int col)							{   x = col * SIZE;						}
	public void setRow(int row)							{   y = row * SIZE;						}
	public int getCol()									{   return x / SIZE;					}
	public int getRow()									{   return y / SIZE;					}
	public boolean isVisible()							{   return visible;						}
	public void setVisible(boolean visible)				{	this.visible = visible;				}
	public boolean isAllowVisitors() 					{	return allowVisitors;				}
	public void setAllowVisitors(boolean allowVisitors) {	this.allowVisitors = allowVisitors;	}



	public String getSymbol() {
		return symbol;
	}



	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean canDig() {
		boolean ret = symbol.equals(" ");
		
		for(Item i : items) {
			if(!i.getCanPickUp()) return false;
			ret = true;
		}
		return ret;
	}
	
	ArrayList<Item> getItems() {
		return items;
	}
}
