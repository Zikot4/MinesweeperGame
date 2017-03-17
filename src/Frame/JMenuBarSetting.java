package Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import FrameCustomParameters.CustomParameters;


public class JMenuBarSetting extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private static JMenuBarSetting menuBerSetting = new JMenuBarSetting();
	
	
	private JMenuBarSetting(){
		setBackground(Color.WHITE);
		
	}
	
	protected static JMenuBarSetting getJMenuBarSetting(){
		return menuBerSetting;
	}
	
	public void createJMenuBar(JFrame frame){
        // Add the menubar to the frame
        frame.setJMenuBar(this);
        // Define and add two drop down menu to the menubar
        JMenu gameMenu = new JMenu("Game");
        JMenu levelMenu = new JMenu("Level");
        add(gameMenu);
        add(levelMenu);

        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New Game");
        JMenuItem exitAction = new JMenuItem("Exit");
        //level
        JMenuItem begginerAction = new JMenuItem("Begginer");
        JMenuItem intermediateAction = new JMenuItem("Intermediate");
        JMenuItem expertAction = new JMenuItem("Expert");
        JMenuItem customAction = new JMenuItem("Custom");
        
        gameMenu.add(newAction);
        gameMenu.addSeparator();
        gameMenu.add(exitAction);
        
        levelMenu.add(begginerAction);
        levelMenu.add(intermediateAction);
        levelMenu.add(expertAction);
        levelMenu.add(customAction);
		
		//ActionListeners{
        exitAction.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent ev) {
              System.exit(0);
            }
        });
        
        newAction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelHeader.getJPanelHeader().resetGame();
			}

        });
        
        begginerAction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelArea.getJPanelArea().removeButtons();
				JPanelArea.setParametersGame(9,9, 10);
				JPanelHeader.getJPanelHeader().resetTimer();
				JPanelHeader.getJPanelHeader().refreshCountBomb();
				JPanelArea.getJPanelArea().run(frame);
			}

        });
        
        intermediateAction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelArea.getJPanelArea().removeButtons();
				JPanelArea.setParametersGame(16,16, 40);
				JPanelHeader.getJPanelHeader().resetTimer();
				JPanelHeader.getJPanelHeader().refreshCountBomb();
				JPanelArea.getJPanelArea().run(frame);
			}

        });
        expertAction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelArea.getJPanelArea().removeButtons();
				JPanelArea.setParametersGame(16,30, 99);
				JPanelHeader.getJPanelHeader().resetTimer();
				JPanelHeader.getJPanelHeader().refreshCountBomb();
				JPanelArea.getJPanelArea().run(frame);
			}

        });
        
        customAction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomParameters customParameters = new CustomParameters(frame);
				customParameters.run(frame);
				JPanelHeader.getJPanelHeader().resetTimer();
				frame.setEnabled(false);
			}

        });
        
        
		//ActionListeners}
	}
}
