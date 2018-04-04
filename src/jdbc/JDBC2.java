/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author nadeem.bukhari
 */
public class JDBC2 {
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
                
         try{
             
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc","root","");
         con.setAutoCommit(true);
        
         //STATEMENT CLASS
         Statement stmt = con.createStatement();
         PreparedStatement pt=con.prepareStatement("DELETE FROM students");
         pt.executeUpdate();
         
         long startTime=System.nanoTime();
         for(int i=0;i<5000;i++){
            String x="laiba"+i;
            String addr="colony"+i;
            
            String sql = "INSERT INTO students " +"VALUES ('"+x+"',"+i+",6,'"+addr+"');";
            stmt.executeUpdate(sql);
         }
         //con.commit();
         long endTime=System.nanoTime();
            System.out.println("Inserted records into the table using statement class in "+((endTime-startTime)/1000000) +" ms");
        
        
         pt.executeUpdate();
         
         //PRRPARED STATEMENT CLASS
        String SQL = "Insert into students values(Name = ?, RegNo = ?,Semester=?,Address=?); ";
        PreparedStatement prSt = con.prepareStatement(SQL);
        
        long startTime2=System.nanoTime();
         for(int i=5000;i<10000;i++){
         String x="laiba"+i;
         String addr="colony"+i;
        prSt.setString(1,x);
        prSt.setInt(2,i);
        prSt.setInt(3,6);
        prSt.setString(4,addr);
        prSt.executeUpdate();
        
         }
         //con.commit();
         long endTime2=System.nanoTime();
        System.out.println("Inserted records into the table using prepared class in "+((endTime2-startTime2)/1000000) +" ms");
        
        pt.executeUpdate();
        
        //BATCH UPDATE
        

         long startTime3=System.nanoTime();
         for(int i=0;i<5000;i++){
            String x="laiba"+i;
            String addr="colony"+i;
            
            String sql = "INSERT INTO students " +"VALUES ('"+x+"',"+i+",6,'"+addr+"');";
            stmt.addBatch(sql);
         }
         
         
         stmt.executeBatch();
         //con.commit();
         long endTime3=System.nanoTime();
         System.out.println("Inserted records into the table using Batch Update in "+((endTime3-startTime3)/1000000) +" ms");
        
        
         pt.executeUpdate();
      
        
        
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
              
    }
    
}
