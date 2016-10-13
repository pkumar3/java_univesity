package F;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Quest extends JPanel {

	private String currentQuest = "Quest_1-0";
	
	private ArrayList<MapBlock> blocks = new ArrayList<MapBlock>();
	private JFrame window = new JFrame("Quest - 2016");
	private Player player;
	private int cols = 0, rows = 0;
	
	public Quest() {
		loadQuest();
		
		window.addKeyListener(new KeyListener() {
			
			@Override public void keyTyped(KeyEvent e) { }
			@Override public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
					switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						movePlayer(Direction.LEFT);repaint();
						break;
					case KeyEvent.VK_RIGHT:
						movePlayer(Direction.RIGHT);repaint();	
						break;
					case KeyEvent.VK_UP:
						movePlayer(Direction.UP);repaint();
						break;
					case KeyEvent.VK_DOWN:
						movePlayer(Direction.DOWN);repaint();
						break;
					case KeyEvent.VK_D:
						if(player.dig()) {
							player.getCurrentMapBlock().setSymbol("O");
							player.getCurrentMapBlock().setImage("dirt-hole.gif");
							repaint();
						}
						break;
					}
					System.out.println(player.getTreasureTotal());
					repaint();
				
			}
		});
				
				
		window.setFocusable(true);
		window.setBounds(0, 0, 800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		window.setVisible(true);
	    window.repaint();
	}

	public void save() {
		PrintWriter pw = null;
		player.getCurrentMapBlock().setSymbol("@");
		try {
			pw = new PrintWriter("quests/" + currentQuest + ".sav");
			
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < cols; c++) {
					getMapBlock(c, r).save(pw);
				}
				pw.println();
			}
	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pw != null) pw.close();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		window.repaint();
		for(MapBlock mb : blocks)
			mb.draw(g);
		player.draw(g);
	}
	
	public MapBlock getMapBlock(int col, int row) {
		for(MapBlock mb : blocks) {
			if(mb.getCol() == col && mb.getRow() == row) 
				return mb;
		}
		return null;
	}
	
	public boolean movePlayer(Direction dir) {
		MapBlock mb = getMapBlock( player.getCol() + dir.getDeltaCol(),
								   player.getRow() + dir.getDeltaRow()
								  );
		boolean ret = false;
		
		if(mb != null) {
			if(mb.getItem(0) instanceof Portal) {
				save();
				currentQuest = ((Portal)mb.getItem(0)).getFileToLoad();
				loadQuest();
			} else {
				ret = player.moveTo(mb);
			}
		}
		return ret;
	}
	
	public void loadQuest() {
		
		Scanner fin = null;
		blocks.clear();
		try {
			String line;
			int col = 0, row = 0;
			MapBlock mb;
		
			File f = new File("quests/" + currentQuest + ".sav");
			
			if (! (f.exists() && !f.isDirectory()) ) { 
				f = new File("quests/" + currentQuest + ".txt");
			}
			
			fin = new Scanner(f);
			
			while(fin.hasNextLine()) {
				line = fin.nextLine();
				rows++;
				cols = line.length();
				for(char c : line.toCharArray()) {
					mb = new MapBlock(col, row, "ground.png", true, true, "" + c);
					switch(c) {
						case 'w': 
							mb.setImage("wall.png");
							mb.setAllowVisitors(false);
							break;
						case 'f': 
							mb.setImage("wall.png");
							break;
						case 'g':
							mb.addItem(new Gun("gun.png",true,true));
							break;
						case 'k':
							mb.addItem(new Knife("knife.png",true, true));
							break;
						case ':':
							mb.addItem(new Key("key.png",true, KeyType.NORMAL));
							break;
						case 'm':
						case 'b':
							mb.addItem(new Treasure("money.png",(c=='m'),true,
									QuestFunctions.getRandomNumber(100, 500)));
							break;
						case 'M':
						case 'B':
							mb.addItem(new Treasure("money.png",(c=='M'),true,
									QuestFunctions.getRandomNumber(500, 1000)));
							break;
						case 'O':
							mb.setImage("dirt-hole.gif");
							break;
						case 's':
							mb.addItem(new Shovel("shovel.png",true, true));
							break;
						case 'd':
						case 'D':	
							mb.addItem(new Door("door_yellow.png","door.png",true,(c=='D'),KeyType.NORMAL));
							break;
						case '@':
							if(player == null) 
								player = new Player("wizard.png", mb);
							else 
								player.setMapBlock(mb);
							
							mb.setSymbol(" ");
							break;
						case '0':
						case '1':
							mb.addItem(new Portal(	"pill_blue.png",
													"pill_blue.png",
													true,
													false,
													KeyType.NONE, 
													currentQuest.substring(0, currentQuest.length()-1) + c ));
					}
					blocks.add(mb);
					col++;
				}
				
				col = 0;
				row++;
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(fin != null) fin.close();
		}
		
	}
	
	public static void main(String[] args) {
		new Quest();
	}
}


