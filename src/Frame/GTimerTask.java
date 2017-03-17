package Frame;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

public class GTimerTask extends TimerTask {
    private int seconds = 30;
    private Timer GTimer;
    private JTextField TLabel;
 
    GTimerTask(Timer Time,int sec,JTextField lab) {
        this.seconds = sec;
        this.GTimer = Time;
        this.TLabel = lab;
    }
    
    
    @Override
    public void run() {
        this.seconds++;
        if (this.seconds>998)
           GTimer.cancel();
        else
           TLabel.setText(""+this.seconds);
    }
 
}