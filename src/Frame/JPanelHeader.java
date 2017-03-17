package Frame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelHeader extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private ImageIcon iReset = new ImageIcon("img/reset32.png");
	private JButton bStart;
	private Timer MyTimer;
	private GTimerTask myTask;
	JTextField timerTextField;
	JTextField countBombsTextField;
	
	private static final JPanelHeader pHeader = new JPanelHeader();
	
	private GridBagConstraints cGridBag = new GridBagConstraints();
	
	public void resetTimer(){
		MyTimer.cancel();
		MyTimer = new Timer();
		myTask = new GTimerTask(MyTimer,0,timerTextField);
	}
	
	public String getTime(){
		return timerTextField.getText();
	}
	
	public void startTimer(){
		MyTimer.schedule(myTask, 1000,1000);
		
	}
	
	public void setIconOnButton(){
		bStart.setIcon(iReset);
	}
	
	public void setIconOnButton(ImageIcon icon){
		bStart.setIcon(icon);
	}
	
	private void createButton(){
		cGridBag.fill = GridBagConstraints.HORIZONTAL;
		
		///////////////////
		cGridBag.insets = new Insets(0, 80, 0, 0);

		timerTextField = new JTextField("0",3);
		timerTextField.setEditable(false);
		timerTextField.setHorizontalAlignment(JTextField.CENTER);
		
		MyTimer = new Timer();
		myTask = new GTimerTask(MyTimer,0,timerTextField);
		add(timerTextField,cGridBag);
		//////////////////

		bStart = new JButton("");
		bStart.setPreferredSize(new Dimension(40,40));
		setIconOnButton(iReset);
		bStart.setBackground(Color.WHITE);
		bStart.setBorderPainted(false);
		bStart.setFocusable(false);
		setLayout(new GridBagLayout());
		add(bStart,cGridBag);	
		
		////////////////////
		countBombsTextField = new JTextField(JPanelArea.getJPanelArea().getBOMBS()+"",3);
		countBombsTextField.setEditable(false);
		countBombsTextField.setHorizontalAlignment(JTextField.CENTER);
		add(countBombsTextField,cGridBag);
		///////////////////
		
		//ActionListener{
		bStart.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent arg0) {
				resetGame();
			}
			
		});
		//ActionListener}
	}
	
	public void refreshCountBomb(){
		countBombsTextField.setText(JPanelArea.getJPanelArea().getBOMBS()+"");
	}
	

	

	private JPanelHeader(){
		setBackground(Color.WHITE);
		createButton();
	}
	
	
	protected void resetGame(){
		setIconOnButton();
		JPanelArea.getJPanelArea().setEmptyCheckBlocks();
		JPanelArea.getJPanelArea().createRandomBombs();
		refreshCountBomb();
		resetTimer();
		updateUI();
	}
	
	public static JPanelHeader getJPanelHeader(){
		return pHeader;
	}
	
	
	
}