import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DBInterface {
 private Connection c;

 DBInterface(Connection con){
  c = con;
 }
 
 public static void writeErrLog(String toWrite){
  Date curDate = new Date();
  SimpleDateFormat format = new SimpleDateFormat();
  String dateToStr = format.format(curDate);
  try {
   PrintWriter writer = new PrintWriter(new FileOutputStream(new File("logs.txt"), true /* append = true */));
   writer.println("[ "+dateToStr+" ]\t"+toWrite+"\n");
   writer.flush();
   writer.close();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }
 }

 public ResultSet selectStuff(String query, int timeout) throws InterruptedException {
  Statement stat;
  ResultSet rs = null;
  try {
   stat = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
   if(timeout!=0)
    stat.setQueryTimeout(timeout);
   rs = stat.executeQuery(query);
  } catch (SQLException e) {
   if(e.getMessage().equalsIgnoreCase("terminating connection due to administrator command"))
    Thread.sleep(300000);
   writeErrLog( "Selecting data ==> "+ e.getClass().getName()+": "+ e.getMessage() );
   System.exit(1);
  }
  return rs;
 }


 
 
 //Stuff below needs improvement since they are not used 
 public void printData(ResultSet r){
  if(r == null)
   return;
  try {
   ResultSetMetaData metaData = r.getMetaData();
   int count;
   count = metaData.getColumnCount();
   String[] colNames = new String[count];
   for (int i = 1; i <= count; i++)
    colNames[i-1] = metaData.getColumnName(i);
   for (int i = 1; i <= count; i++)
    System.out.print(colNames[i-1]+"\t|");
   while ( r.next() ) {
    String query = "\n";
    for (int i = 0; i <= count-1; i++){
     try{
      query += r.getString(colNames[i]).toString() + "\t|";
     }
     catch(Exception ex){
      System.out.println("Error in loop: " + ex);
      query +=  "NULL\t|";
      continue;
     }
    }
    System.out.println(query);
   }
  } catch (SQLException e) {
   writeErrLog("Printing data ==> "+ e.getClass().getName()+": "+ e.getMessage() );
  }
 }
 
 public void writeData(ResultSet r){
  try {
   PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
   ResultSetMetaData metaData = r.getMetaData();
   int count;
   count = metaData.getColumnCount();
   String[] colNames = new String[count];
   for (int i = 1; i <= count; i++)
    colNames[i-1] = metaData.getColumnName(i);
   for (int i = 1; i <= count; i++){
    writer.println(colNames[i-1]+"\t|");
    writer.flush();
   }
   while ( r.next() ) {
    String query = "\n";
    for (int i = 0; i <= count-1; i++){
     try{
      query += r.getString(colNames[i]).toString() + "\t|";
     }
     catch(Exception ex){
      System.out.println("Error in writing: " + ex);
      query +=  "NULL\t|";
      continue;
     }
    }
    //write in file instead of printing!!!
    System.out.println(query);
    writer.println(query);
   }
   writer.close();
  } catch (SQLException | FileNotFoundException | UnsupportedEncodingException e) {
   System.err.println( e.getClass().getName()+": "+ e.getMessage() );
  }
 }
 
 // -------- untested ----------
  public boolean executeStuff(String query){
   Statement stat = null;
   try {
    stat = c.createStatement();
    stat.executeUpdate(query);
    //c.commit();
    stat.close();
   } catch (Exception e) {
    writeErrLog("Executing Query ==> \""+ query.substring(0,40) + "\"" 
      + e.getClass().getName()+": "+ e.getMessage() );
    return false;
   }
   return true;
  }
}