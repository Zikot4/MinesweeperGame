package Frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class JPanelHeader extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton bStart;
	
	private static final JPanelHeader pHeader = new JPanelHeader();
	
	private void createButton(){
		
		bStart = new JButton("Start");
		setLayout(new BorderLayout());
		add(bStart,BorderLayout.CENTER);
		bStart.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				resetGame();
			}
			
		});
	}
	
	private JPanelHeader(){
		createButton();
	}
	
	
	protected void resetGame(){
		JPanelArea.getJPanelArea().setEmptyCheckBlocks();
		JPanelArea.getJPanelArea().createRandomBombs();
		updateUI();
	}
	
	public static JPanelHeader getJPanelHeader(){
		return pHeader;
	}
	/*
	public void createJTextField(){
		setLayout(new BorderLayout());
		JTextField lab = new JTextField("0",3);
		lab.setEditable(false);
		lab.setHorizontalAlignment(JTextField.CENTER);
		Timer MyTimer;
		MyTimer = new Timer();
		GTimerTask myTask = new GTimerTask(MyTimer,0,lab);
		MyTimer.schedule(myTask, 1000,1000);
		add(lab,BorderLayout.WEST);
		//////////////////
		JTextField countbomb = new JTextField(JPanelArea.getJPanelArea().getBOMBS()+"",3);
		countbomb.setEditable(false);
		countbomb.setHorizontalAlignment(JTextField.CENTER);
		add(countbomb,BorderLayout.EAST);
	}
	*/
}