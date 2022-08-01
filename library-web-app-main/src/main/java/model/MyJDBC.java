import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class MyJDBC {
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "system";
	String PASS = "oracle";
	private Connection c = null;
	private Statement st = null;
	private ResultSet rs = null;
	public MyJDBC() throws SQLException{
		registerDriver();
        c = getConnection();
        st = c.createStatement();
        Database();
	}
	public static void main(String [] args) throws SQLException {
		MyJDBC obj = new MyJDBC();
		
	}
	//register for Driver
    public void registerDriver() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        System.out.println("Driver registered");
    }
    //establish connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
        //System.out.println("Database connected");
    }
    //creates tables and build data
    public void Database() {
        String s;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileReader fr = new FileReader(new File("/Users/monicaw./Desktop/CS157AProject/createTables.sql"));
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
            br.close();

            String[] inst = stringBuilder.toString().split(";");


            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
            
//            String insert = "INSERT INTO L_USER VALUES('1234', 'Jim', to_date('1996/07/15', 'yyyy/mm/dd'), 'jim0715@gmail.com')";
//            ResultSet resultSet = st.executeQuery(insert);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //insert new User
    public Boolean newUser(String id,String name,String dob,String email) {
    	try {
	        String check  = "SELECT L.USER_ID FROM L_USER L WHERE L.USER_ID = '" + id + "'";
	        rs = st.executeQuery(check);
	        if (!rs.next()) {
	        	System.out.print("exec");
	        	String query = "INSERT INTO L_USER VALUES('" + id + "', '" + name + "', to_date('" + dob + "', 'yyyy/mm/dd'), '" + email + "')";
	        	rs = st.executeQuery(query);
	        	return true;
	        }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new Librarian
    public Boolean newLibrarian(String id, String name) {
    	try {
            String check  = "SELECT * FROM LIBRARIAN L WHERE L.L_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if (!rs.next()) {
            	System.out.print("exec");
            	String query = "INSERT INTO LIBRARIAN VALUES('" + id + "', '" + name + "')";	
            	rs = st.executeQuery(query);
            	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new volunteer
    public Boolean newVolunteer(String id, String name) {
    	try {
            String check  = "SELECT V.V_ID FROM VOLUNTEER V WHERE V.V_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if (!rs.next()) {
            	System.out.print("exec");
            	String query = "INSERT INTO VOLUNTEER VALUES('" + id + "', '" + name + "')";
            	rs = st.executeQuery(query);
            	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new studyroom
    public Boolean newStudyRoom(String number, String capacity) {
    	try {

            String check  = "SELECT S.ROOM_NUM FROM STUDYROOM S WHERE S.ROOM_NUM = '" + number + "'";
            rs = st.executeQuery(check);
            if(!rs.next()) {
            	System.out.print("exec");
            	String query = "INSERT INTO STUDYROOM VALUES('" + number + "', '" + capacity + "')";
            	rs = st.executeQuery(query);
            	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new Library Card
    public Boolean newLibraryCard(String id, String validThru) {
    	try {
            String check  = "SELECT LC.C_ID FROM LIBRARYCARD LC WHERE LC.C_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if(!rs.next()) {
        	String query = "INSERT INTO LIBRARYCARD VALUES('" + id + "', '" + validThru + "')";
        	rs = st.executeQuery(query);
            return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new Publishing House
    public Boolean newPublishingHouse(String id, String name) {
    	try {
            String check  = "SELECT P.P_ID FROM PUBLISHINGHOUSE P WHERE P.P_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if(!rs.next()) {
        	String query = "INSERT INTO PUBLISHINGHOUSE VALUES('" + id + "', '" + name + "')";
        	rs = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new DVD
    public Boolean newDVD(String id, String name, String length) {
    	try {
            String check  = "SELECT A.ASSET_ID FROM ASSET A WHERE A.ASSET_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if(!rs.next()) {
        	String query = "INSERT INTO ASSET VALUES('" + id + "', '" + name + "')";
        	rs = st.executeQuery(query);
        	query = "INSERT INTO DVD VALUES('" + id + "', '" + length + "')";
        	rs = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new Book
    public Boolean newBook(String id, String name, String pages) {
    	try {
            String check  = "SELECT A.ASSET_ID FROM ASSET A WHERE A.ASSET_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if(!rs.next()) {
        	String query = "INSERT INTO ASSET VALUES('" + id + "', '" + name + "')";
        	rs = st.executeQuery(query);
        	query = "INSERT INTO BOOK VALUES('" + id + "', '" + pages + "')";
        	rs = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //insert new Category
    public Boolean newCategory(String id, String name, String location) {
    	try {
            String check  = "SELECT AC.AC_ID FROM ASSET_CATEGORY AC WHERE AC.AC_ID = '" + id + "'";
            rs = st.executeQuery(check);
            if(!rs.next()) {
        	String query = "INSERT INTO ASSET_CATEGORY VALUES('" + id + "', '" + name + "', '" + location + "')";
        	rs = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    // Q1: List all Users
    public ArrayList<L_User> findUser() 
    {
 	   ArrayList<L_User> list = new ArrayList<>();
 	   
 	   try 
 	   {
            rs = st.executeQuery("SELECT USER_ID, USER_NAME FROM L_USER");
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
              if (null != c) {
                try { c.close(); } catch(Exception ex) { System.out.println(ex); };
              }
        }
 	   return list;
    } 
    
    // Q2: Search for the User that Reserved Room Number 'XXX'
    public void findReservedRoomUser(int roomNum) 
    {  
 	   try {
            rs = st.executeQuery("SELECT USER_ID, ROOM_NUM FROM RESERVE WHERE ROOM_NUM = " + roomNum);
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
              if (null != c) {
                try { c.close(); } catch(Exception ex) { System.out.println(ex); };
              }
        }
    }
    
    // Q3: Select all Books with more than 'X' amount of Pages
    public ArrayList<Book> findBooksWithNumPages(int amount) {
 	   ArrayList<Book> list = new ArrayList<>();
 	   
 	   try {
            rs = st.executeQuery("SELECT ASSET_ID, PAGES FROM BOOK WHERE PAGES >= " + amount);
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
              if (null != c) {
                try { c.close(); } catch(Exception ex) { System.out.println(ex); };
              }
        }
 	   return list;
    }
    
    //Q4: Insert new entity(at the beginning of the code)
    
    //Q5: Find the checkout date of an asset borrowed by a user
    public List<Checkout> query5(String assetID, String userID) {
    	List<Checkout> result = new ArrayList<Checkout>();
    	try {
        	String query = "SELECT C.* FROM CHECKOUT C WHERE C.USER_ID = '" + userID + "' AND C.ASSET_ID = '" + assetID + "'";
        	rs = st.executeQuery(query);
        	ResultSetMetaData meta   = rs.getMetaData();
//        	int colmax = meta.getColumnCount();
        	while (rs.next()) {
                Checkout obj = new Checkout();
               obj.setCheckout_id(rs.getString("CHECKOUT_ID"));
               obj.setCheckout_date(rs.getDate("CHECKOUT_DATE").toString());
               obj.setAsset_id(rs.getString("ASSET_ID"));
               obj.setUser_id(rs.getString("USER_ID"));
               result.add(obj);
              }

        	for(Checkout obj : result) {
        		System.out.print(obj.asset_id);
        		System.out.print(" " + obj.checkout_date);
        		System.out.print(" " + obj.checkout_id);
        		System.out.println(" " +obj.user_id);
        		
        	}
        	
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    //Q6: Find the user who donates an asset and the donate date
    public List<Donate> query6(String assetID){
    	List<Donate> result = new ArrayList<Donate>();
    	try {
        	String query = "SELECT D.* FROM DONATE D WHERE D.ASSET_ID = '" + assetID + "'";
        	rs = st.executeQuery(query);
        	ResultSetMetaData meta   = rs.getMetaData();
//        	int colmax = meta.getColumnCount();
        	while (rs.next()) {
               Donate obj = new Donate();
               obj.setDonate_id(rs.getString("DONATE_ID"));
               obj.setDonate_date(rs.getDate("DONATE_DATE").toString());
               obj.setAsset_id(rs.getString("ASSET_ID"));
               obj.setUser_id(rs.getString("USER_ID"));
               result.add(obj);
              }
        	for(Donate obj : result) {
        		System.out.print(obj.asset_id);
        		System.out.print(" " + obj.donate_date);
        		System.out.print(" " + obj.donate_id);
        		System.out.println(" " +obj.user_id);
        		
        	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    // Q7: Find all books that is published by a publishing house
    public List<PublishingHouse> query7(String P_ID){
        List<PublishingHouse> result = new ArrayList<PublishingHouse>();
        try { 
            String query = "SELECT PH.* FROM PUBLISHINGHOUSE PH WHERE PH.P_ID = '" + P_ID + "'";
            rs = st.executeQuery(query);
            ResultSetMetaData meta   = rs.getMetaData();
            int colmax = meta.getColumnCount();
            while (rs.next()) {
                PublishingHouse obj = new PublishingHouse();
                obj.setP_id(rs.getString("P_ID"));
                obj.setP_name(rs.getString("P_NAME"));
                result.add(obj);
            }

            for(PublishingHouse obj : result) {
                System.out.print(obj.p_id);
                System.out.print(" " + obj.p_name);

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // Q8: Find all assets that is under an asset category
    public List<Asset_Category> query8(String AC_ID){
        List<Asset_Category> result = new ArrayList<Asset_Category>();
        try {
            String query = "SELECT AC.* FROM ASSET_CATEGORY AC WHERE AC.AC_ID = '" + AC_ID + "'";
            rs = st.executeQuery(query);
            ResultSetMetaData meta   = rs.getMetaData();
            int colmax = meta.getColumnCount();
            while (rs.next()) {
                Asset_Category obj = new Asset_Category();
                obj.setAsset_id(rs.getString("ASSET_ID"));
                obj.setAsset_location(rs.getString("ASSET_LOCATION"));
                obj.setAsset_name(rs.getString("ASSET_NAME"));
                result.add(obj);
            }

            for(Asset_Category obj : result) {
                System.out.print(obj.ac_id);
                System.out.print(" " + obj.ac_location);
                System.out.print(" " + obj.ac_name);

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Q9: Find the librarian who check in an asset follow with the check in date
    public List<Checkin> query9(String L_ID, String Checkin_date ){
        List<Checkin> result = new ArrayList<Checkin>();
        try {
            String query = "SELECT C.L_ID AS LIBARIAN, C.CHECKIN_DATE FROM CHECKIN C WHERE C.L_ID = '" + L_ID + "' AND C.CHECKIN_DATE = '" + Checkin_date + "'";
            rs = st.executeQuery(query);
            ResultSetMetaData meta   = rs.getMetaData();
            int colmax = meta.getColumnCount();
            while (rs.next()) {
                Checkin obj = new Checkin();
                obj.setAsset_id(rs.getString("ASSET_ID"));
                obj.setCheckin_date(rs.getDate("CHECKIN_DATE").toString());
                obj.setCheckin_id(rs.getString("CHECKIN_ID"));
                obj.setL_id(rs.getString("L_ID"));
                result.add(obj);
            }

            for(Checkin obj : result) {
                System.out.print(obj.asset_id);
                System.out.print(" " + obj.checkin_date);
                System.out.println(" " + obj.checkin_id);
                System.out.println(" " + obj.l_id);

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
