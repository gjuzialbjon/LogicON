/**
 * @(#)GateOR.java
 *
 *
 * @Alperen Tercan  
 * @version 1.00 2016/7/20
 */
import java.awt.*;
import javax.swing.*;


public class GateOR extends LogicComponent {
  private WireModel input1;
  private WireModel input2;
  public GateOR(Point p) {
    super();
    type = "GateOR";
    setPoint(p);
    setInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 23)));
    setSecondInputPoint(new Point((int)(p.getX() + 7),(int)(p.getY() + 42)));
    setOutputPoint(new Point((int)(p.getX() + 93),(int)(p.getY() + 32)));
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
      setState((input1.getState() || input2.getState()));
  }
  public Image getImage(){
		return new ImageIcon("img\\or.png").getImage();
	}
}