/**
 * @(#)ClockInput.java
 *
 *
 * @Alperen Tercan  
 * @version 1.00 2016/7/20
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClockInput extends LogicComponent {
  private int delay;
  private Timer timer;
  public ClockInput(Point p){
    super();
    type = "Clock Input";
    setPoint(p);
    setState(false);
   	setInputPoint(new Point((int)(p.getX()),(int)(p.getY())));
    setSecondInputPoint(new Point((int)(p.getX()),(int)(p.getY())));
    setOutputPoint(new Point((int)(p.getX() + 91),(int)(p.getY() + 30)));
    delay = 10;
    setState(false);
    timer = new Timer(delay,new ActionListener(){
      public void actionPerformed(ActionEvent e){
        operate();
      }
    });
    timer.start();

    
  }
  public void operate(){
  	setState(!getState());
  }
  public void resetTimer()
  {
    timer.restart();
  }
  public void changeDelay(int d)
  {
    delay = d;
    timer.setDelay(d);
  }

	public Image getImage(){
		if(getState())
		  return new ImageIcon("img\\clockOn.png").getImage();
		else
		  return new ImageIcon("img\\clockOff.png").getImage();
	}	}	

  
  
