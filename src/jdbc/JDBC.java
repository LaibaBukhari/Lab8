/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import java.sql.*;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.Scanner;
/**
 *
 * @author nadeem.bukhari
 */
public class JDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
                
         try{
             
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc","root","");
         con.setAutoCommit(false);
        
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
         con.commit();
         long endTime=System.nanoTime();
            System.out.println("Inserted records into the table using statement class in "+((endTime-startTime)/1000000) +" ms");
        
        PreparedStatement a=con.prepareStatement("DELETE FROM students");
        a.executeUpdate();
         
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
         con.commit();
         long endTime2=System.nanoTime();
        System.out.println("Inserted records into the table using prepared class in "+((endTime2-startTime2)/1000000) +" ms");
        
        PreparedStatement b=con.prepareStatement("DELETE FROM students");
        b.executeUpdate();
         
        
        //BATCH UPDATE
        

         long startTime3=System.nanoTime();
         for(int i=0;i<5000;i++){
            String x="laiba"+i;
            String addr="colony"+i;
            
            String sql = "INSERT INTO students " +"VALUES ('"+x+"',"+i+",6,'"+addr+"');";
            stmt.addBatch(sql);
         }
         
         
         stmt.executeBatch();
         con.commit();
         long endTime3=System.nanoTime();
         System.out.println("Inserted records into the table using Batch Update in "+((endTime3-startTime3)/1000000) +" ms");
        
        
         PreparedStatement c=con.prepareStatement("DELETE FROM students");
         c.executeUpdate();
         
        
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
         
         
    }
    
}
