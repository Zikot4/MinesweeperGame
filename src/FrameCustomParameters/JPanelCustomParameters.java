package FrameCustomParameters;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import Frame.JPanelArea;

public class JPanelCustomParameters  extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static JPanelCustomParameters custom = new JPanelCustomParameters();
	
	private static int columns = 9,rows = 9,bombs = 10;
	
	private JLabel headerLabel = new JLabel();
	private JLabel statusLabel = new JLabel("Columns : "+columns);
	private JLabel statusLabel2 = new JLabel("Rows : "+rows);
	private JLabel statusLabel3 = new JLabel("Bombs : "+bombs);
	
    private JButton bConfirm = new JButton("Confirm");
	private static JSlider slider = new JSlider(JSlider.HORIZONTAL,9,50,columns);
	private static JSlider slider2 = new JSlider(JSlider.HORIZONTAL,9,50,rows);
	private static JSlider slider3 = new JSlider(JSlider.HORIZONTAL,1,100,bombs);
	
	private JPanelCustomParameters(){

	}
	

	public static JPanelCustomParameters getJPanelCustomParameters() {
		return custom;
	}
	
    public void showSliderDemo(JFrame firstFrame,JDialog secondFrame){

    	
    	
	   setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));  
	   
	    headerLabel.setText("Control in action: JSlider"); 
        //columns	    
	    slider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
	            statusLabel.setText("Columns : " + ((JSlider)e.getSource()).getValue());
	            columns = ((JSlider)e.getSource()).getValue();
	         }
	    });
	      
	    //rows
	    slider2.addChangeListener(new ChangeListener() {
	    	public void stateChanged(ChangeEvent e) {
	    		statusLabel2.setText("Rows : " + ((JSlider)e.getSource()).getValue());
	            rows = ((JSlider)e.getSource()).getValue();
	         }
	    });
	      
	    //bombs   
	    slider3.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	            statusLabel3.setText("Bombs : " + ((JSlider)e.getSource()).getValue());
	            bombs =  ((JSlider)e.getSource()).getValue();
	         }
	    });  
	    bConfirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				firstFrame.setEnabled(true);
				JPanelArea.getJPanelArea().removeButtons();
				JPanelArea.setParametersGame(columns,rows, bombs);
				JPanelArea.getJPanelArea().run(firstFrame);
				secondFrame.dispose();
			}
	    	
	    });
	    
	    
	    secondFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				firstFrame.setEnabled(true);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}


	    	
	    });
	    
	    add(headerLabel);
	      
	    add(slider);      
	    add(statusLabel);
	      
	    add(slider2);      
	    add(statusLabel2);
	    add(slider3);      
	    add(statusLabel3);
	      
	    add(bConfirm);
  }
}
