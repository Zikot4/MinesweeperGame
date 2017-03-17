package Frame;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class MinesweeperFrame extends JFrame{	
	private static final long serialVersionUID = 1L;
	
	private int blocks_x,blocks_y;
	private int bombs;
	
	public MinesweeperFrame(){
		blocks_x = 9;
		blocks_y = 9;
		bombs = 10;
	}
	
	public MinesweeperFrame(int blocks_x,int blocks_y, int bombs){
		this.blocks_x = blocks_x;
		this.blocks_y = blocks_y;
		this.bombs = bombs;
	}
	
	public void run(){
		setTitle("Sapper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		ImageIcon icon = new ImageIcon("img/reset.png");
		setIconImage(icon.getImage());
		
		JMenuBarSetting.getJMenuBarSetting().createJMenuBar(this);
		add(JPanelHeader.getJPanelHeader(),BorderLayout.NORTH);	
		add(JPanelArea.getJPanelArea(),BorderLayout.CENTER);
		JPanelArea.setParametersGame(blocks_x,blocks_y,bombs);
		JPanelArea.getJPanelArea().run(this);
	}	
}

