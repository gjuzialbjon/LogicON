/**
 * @(#)GateNOT.java
 *
 *
 * @author 
 * @version 1.00 2016/7/22
 */
import java.awt.*;
import javax.swing.*;


public class GateNOT extends LogicComponent {
	private WireModel input;
    public GateNOT(Point p) {
   		super();
    	type = "GateNOT";
    	setPoint(p);
    	setInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 25)));
    	setSecondInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 25)));
    	setOutputPoint(new Point((int)(p.getX() + 92),(int)(p.getY() + 25)));
    }
	public void setInput1(WireModel w){
  	  input = w;
   	 operate();   
    
 	 }
 	 public void setInput2(WireModel w){
    input = w;
    operate();
  }
  public void operate(){
    if(input == null)
      setState(false);
    else  
      setState(!input.getState());
  }
  public Image getImage(){
		return new ImageIcon("img\\not.png").getImage();
	}
    
    
}