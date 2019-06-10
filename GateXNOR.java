/**
 * @(#)GateXNOR.java
 *
 *
 * @Alperen Tercan  
 * @version 1.00 2016/7/20
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class GateXNOR extends LogicComponent{
  private WireModel input1;
  private WireModel input2;
  public GateXNOR(Point p) {
    type = "GateXNOR";
    setPoint(p);
    setInputPoint(new Point((int)(p.getX() + 6),(int)(p.getY() + 25)));
    setSecondInputPoint(new Point((int)(p.getX() + 6),(int)(p.getY() + 45)));
    setOutputPoint(new Point((int)(p.getX() + 93),(int)(p.getY() + 35)));  
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
      setState(!((input1.getState() || input2.getState()) && (!input1.getState()|| !input2.getState())));
  }
	public Image getImage(){
		return new ImageIcon("img\\xnor.png").getImage();
	}
  
  
}