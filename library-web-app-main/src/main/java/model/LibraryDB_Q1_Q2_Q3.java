package testing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDB_Q1_Q2_Q3
{
   static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE"; 
   static final String USER = "system";									 
   static final String PASS = "oracle";
   
   // Q1: List all Users
   public ArrayList<L_User> findUser() 
   {
	   Connection conn = null;
	   ResultSet rs = null;
	   ArrayList<L_User> list = new ArrayList<>();
	   
	   try 
	   {
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();
           rs = stmt.executeQuery("SELECT USER_ID, USER_NAME FROM L_USER");
           while (rs.next()) {
              L_User user = new L_User();
              user.setUser_id(rs.getString("USER_ID"));
              user.setUser_name(rs.getString("USER_NAME"));
              list.add(user);
           }
	   } catch (Exception e) {
		   System.out.println(e);
	   } finally {
           if (null != rs) {
               try { rs.close(); } catch(Exception ex) { System.out.println(ex); };
             }
             if (null != conn) {
               try { conn.close(); } catch(Exception ex) { System.out.println(ex); };
             }
       }
	   return list;
   } 
   
   // Q2: Search for the User that Reserved Room Number 'XXX'
   public void findReservedRoomUser(int roomNum) 
   {
	   Connection conn = null;
	   ResultSet rs = null;
	   
	   try 
	   {
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();
           rs = stmt.executeQuery("SELECT USER_ID, ROOM_NUM FROM RESERVE WHERE ROOM_NUM = " + roomNum);
           if (rs.next()) {
     		  System.out.print("User ID: " + rs.getInt("USER_ID"));
              System.out.println(" has already reserved Room Number: " + rs.getInt("ROOM_NUM"));
     	   } else {
     		  System.out.println("No User found who reserved Room Number: " + roomNum);
     	   }
	   } catch (Exception e) {
		   System.out.println(e);
	   } finally {
           if (null != rs) {
               try { rs.close(); } catch(Exception ex) { System.out.println(ex); };
             }
             if (null != conn) {
               try { conn.close(); } catch(Exception ex) { System.out.println(ex); };
             }
       }
   }
   
   // Q3: Select all Books with more than 'X' amount of Pages
   public ArrayList<Book> findBooksWithNumPages(int amount) 
   {
	   Connection conn = null;
	   ResultSet rs = null;
	   ArrayList<Book> list = new ArrayList<>();
	   
	   try 
	   {
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();
           rs = stmt.executeQuery("SELECT ASSET_ID, PAGES FROM BOOK WHERE PAGES >= " + amount);
           while (rs.next()) {
              Book item = new Book();
              item.setAsset_id(rs.getString("ASSET_ID"));
              item.setPages(rs.getInt("PAGES"));
              list.add(item);
           }
	   } catch (Exception e) {
		   System.out.println(e);
	   } finally {
           if (null != rs) {
               try { rs.close(); } catch(Exception ex) { System.out.println(ex); };
             }
             if (null != conn) {
               try { conn.close(); } catch(Exception ex) { System.out.println(ex); };
             }
       }
	   return list;
   }

   public static void main(String[] args) 
   {
       
   }
}


