/**
 * @(#)GateNAND.java
 *
 *
 * @Alperen Tercan  
 * @version 1.00 2016/7/20
 */


import java.awt.*;
import javax.swing.*;


public class GateNAND extends LogicComponent{
  private WireModel input1;
  private WireModel input2;
  
  public GateNAND(Point p) {
    type = "GateNOR";
    setPoint(p);
    setInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 21)));
    setSecondInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 40)));
    setOutputPoint(new Point((int)(p.getX() + 92),(int)(p.getY() + 30)));
  }
  public void setInput1(WireModel w){
    input1 = w;
    operate();
  }
  public void setInput2(WireModel w){
    input2 = w;
    operate();
  }
  public void operate(){
    if(input1 == null || input2 == null)
      setState(false);
    else  
      setState(!(input1.getState() && input2.getState()));
  }
	public Image getImage(){
		return new ImageIcon("img\\nand.png").getImage();
	}
  
}