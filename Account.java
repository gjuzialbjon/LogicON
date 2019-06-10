/**
 * @(#)Account.java
 *
 *
 * @author 
 * @version 1.00 2016/7/19
 */
import java.util.*;
import java.sql.*;

public class Account extends Circuit
{
  protected ArrayList<String> circuitsSoFar;
  protected String username;
  protected String password;
  DBConn myConnector = null;
  Connection connection = null;
  DBInterface myInterface = null;
  int score;
  
  public Account( String username, String password) {
    this.username = username;
    this.password = password;
    circuitsSoFar = new ArrayList<String>();
    myConnector = new DBConn("mysql","sql7.freemysqlhosting.net","3306","sql7128455","sql7128455","jMpKjnr5DG");
    connection = myConnector.ConnectToDB();
    myInterface = new DBInterface(connection);
    if(connection == null){
      //if not connected to DB
    }
  }
  
  public void setUsername(String username){
    
    username = username;
  }
  
  public void setPassword(String password){
    
    password = password;
  }
  
  public String getUsername(){
    
    return username;
    
  } 
  public String getPassword(){
    
    return password;
    
  } 
  
  //bu 2 method database olmasi lazim
  public boolean checkPassword(String password){
    
    if(this.password.equals(password) )
      return true;
    else
      return false;
  }
  
  public boolean checkUsername(String username){
    
    if(this.username.equals(username) )
      return true;
    else
      return false;
  }
  
 public void addCircuit(String name)
  {
   circuitsSoFar.add(name);
    }
 
 
  public void deleteCircuit(String name)
   {
   circuitsSoFar.remove(name);
  }
  
  public ArrayList<String> getCircuits()
   {
   return circuitsSoFar;
  }
  
  public void increaseScore()
  {
    if(added())
      score++;
  }
  
  public boolean added()
  {
    boolean exec = myInterface.executeStuff("INSERT INTO accounts VALUES(\'"+ username +"\',"
                                   + "\'"+ password +"\',0);");
        if(!exec)
          return false;
        else {
          return true;
        }
  }
  
  
  
  
  
}