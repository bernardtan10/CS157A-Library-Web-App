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

    //Q4: Insert new entity(at the beginning of the code)
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
}
