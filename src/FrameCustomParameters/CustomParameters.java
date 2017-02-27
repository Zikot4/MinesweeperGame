package FrameCustomParameters;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class CustomParameters extends JDialog{

	
	private static final long serialVersionUID = 1L;

	public CustomParameters(JFrame frame){
		super(frame);
	}
	
	public void run(JFrame frame){
		setTitle("Custom Setting");
		setResizable(false);
		setVisible(true);
		setSize(300,400);
		setLocationRelativeTo(null);
	
		add(JPanelCustomParameters.getJPanelCustomParameters());	
		JPanelCustomParameters.getJPanelCustomParameters().showSliderDemo(frame,this);
	}
}
