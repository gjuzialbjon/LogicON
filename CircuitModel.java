/**
 * @(#)CircuitModel.java
 *
 *
 * @Alperen Tercan 
 * @version 1.00 2016/7/20
 */
import java.util.*;
import java.io.Serializable;
import java.io.*;

public class CircuitModel implements Serializable
{
  private ArrayList<LogicComponent> addedComp;
  private ArrayList<WireModel> addedWire;
  private String name;
  public CircuitModel() 
  {
    addedComp = new ArrayList<LogicComponent>();
    addedWire = new ArrayList<WireModel>();
  }
  public void addComponent(LogicComponent l){
    int count = 1;
    for(LogicComponent lc : addedComp){
      if((lc.type).equals(l.type))
        count += 1;
    }
    l.setName(l.type + " " + count);
    addedComp.add(l);
  }
  public void addWire(WireModel w){
    int count = 1;
    for(WireModel wm : addedWire){
      if((wm.type).equals(w.type))
        count += 1;
    }
    w.setName(w.type + " " + count);
    addedWire.add(w);
    
  }
  public void removeComponent(LogicComponent l){
    addedComp.remove(l);
  }
  public void removeAll(){
   addedComp = new ArrayList<LogicComponent>();
   addedWire = new ArrayList<WireModel> ();
  }
  public void removeWire(WireModel w){
    addedWire.remove(w);
  }
  
  public ArrayList<LogicComponent> getComponents(){
    return addedComp;
  }
  
  public ArrayList<WireModel> getWires(){
    return addedWire;
  }
  public String toString(){
    String str = "";
    for(LogicComponent l : addedComp)
      str += l.getName() + ":" + l.getType() + " " + l.getState() + "\n";
    for(WireModel w : addedWire)
      str += w.getName() + "State :" + " " + w.getState() + "\n" + w.getInput() ;
    return str;
  }
  public void setName(String name){
 this.name = name;
  }
  public String getName(){
 return name;
  }
  public void save(){
 try{
  String fileName = name + ".ser";  
  File outputFile = new File("circuit.ser");
     ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outputFile));
     os.writeObject(this);
  os.close();
  System.out.println("Done");    
 }catch(Exception ex){
  ex.printStackTrace();
    }
 }
 
 public static CircuitModel open(String str) throws FileNotFoundException,IOException,ClassNotFoundException  {
  CircuitModel cm = null;
  try{
   FileInputStream f_in = new 
   FileInputStream(str + ".ser");
   // Read object using ObjectInputStream
   ObjectInputStream obj_in = 
   new ObjectInputStream (f_in);
   // Read an object
   Object obj = obj_in.readObject();
   if (obj instanceof CircuitModel)
   {
    // Cast object to a Vector
    cm = (CircuitModel) obj;
   }
  }catch(Exception ex){
      ex.printStackTrace();    
  }
  return cm;
 }
  
  
}