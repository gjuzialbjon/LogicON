/**
 * @(#)GateXOR.java
 *
 *
 * @Alperen Tercan  
 * @version 1.00 2016/7/20
 */

import java.awt.*;
import javax.swing.*;

public class GateXOR extends LogicComponent {
  private WireModel input1;
  private WireModel input2;
  public GateXOR(Point p) {
    super();
    type = "GateXOR";
    setPoint(p);
    setInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 25)));
    setSecondInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 45)));
    setOutputPoint(new Point((int)(p.getX() + 92),(int)(p.getY() + 35)));
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
      setState(((input1.getState() || input2.getState()) && (!input1.getState()|| !input2.getState())));
  }
	public Image getImage(){
		return new ImageIcon("img\\xor.png").getImage();
	}
  
}