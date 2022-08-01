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


public class MyJDBC_Q7_Q8_Q9 {


    public static void main(String[] args) throws SQLException {

        registerDriver();
        getConnection();
        Database();
//        newUser("8999", "Monica", "1999/08/20", "monica0820@gmail.com");
//        newLibrarian("567", "Kerry");
//        newCategory("123", "book", "Shelf123");
//        query5("1001002", "1278");
//        query6("1001001");

    }

    public static void registerDriver() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        System.out.println("Driver registered");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
        //System.out.println("Database connected");
    }

    public static void Database() {
        String s;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileReader fr = new FileReader(new File("C:\\Users\\User\\IdeaProjects\\Project157\\src\\createTables.sql"));
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
            br.close();

            String[] inst = stringBuilder.toString().split(";");

            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newUser(String id,String name,String dob,String email) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO L_USER VALUES('" + id + "', '" + name + "', to_date('" + dob + "', 'yyyy/mm/dd'), '" + email + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void newLibrarian(String id, String name) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO LIBRARIAN VALUES('" + id + "', '" + name + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void newVolunteer(String id, String name) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO VOLUNTEER VALUES('" + id + "', '" + name + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void  newStudyRoom(String number, String capacity) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO STUDYROOM VALUES('" + number + "', '" + capacity + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void newLibraryCard(String id, String validThru) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO LIBRARYCARD VALUES('" + id + "', '" + validThru + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void newPublishingHouse(String id, String name) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO PUBLISHINGHOUSE VALUES('" + id + "', '" + name + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void newDVD(String id, String name, String length) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO ASSET VALUES('" + id + "', '" + name + "')";
            ResultSet resultSet = st.executeQuery(query);
            query = "INSERT INTO DVD VALUES('" + id + "', '" + length + "')";
            resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void newBook(String id, String name, String pages) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO ASSET VALUES('" + id + "', '" + name + "')";
            ResultSet resultSet = st.executeQuery(query);
            query = "INSERT INTO BOOK VALUES('" + id + "', '" + pages + "')";
            resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void newCategory(String id, String name, String location) {
        try {
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "INSERT INTO ASSET_CATEGORY VALUES('" + id + "', '" + name + "', '" + location + "')";
            ResultSet resultSet = st.executeQuery(query);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Checkout> query5(String assetID, String userID) {
        List<Checkout> result = new ArrayList<Checkout>();
        try {
            Checkout o = null;
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "SELECT C.* FROM CHECKOUT C WHERE C.USER_ID = '" + userID + "' AND C.ASSET_ID = '" + assetID + "'";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData meta   = resultSet.getMetaData();
            int colmax = meta.getColumnCount();
            while (resultSet.next()) {
                Checkout obj = new Checkout();
                obj.setCheckout_id(resultSet.getString("CHECKOUT_ID"));
                obj.setCheckout_date(resultSet.getDate("CHECKOUT_DATE").toString());
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                obj.setUser_id(resultSet.getString("USER_ID"));
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

    public static List<Donate> query6(String assetID){
        List<Donate> result = new ArrayList<Donate>();
        try {
            Donate o = null;
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "SELECT D.* FROM DONATE D WHERE D.ASSET_ID = '" + assetID + "'";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData meta   = resultSet.getMetaData();
            int colmax = meta.getColumnCount();
            while (resultSet.next()) {
                Donate obj = new Donate();
                obj.setDonate_id(resultSet.getString("DONATE_ID"));
                obj.setDonate_date(resultSet.getDate("DONATE_DATE").toString());
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                obj.setUser_id(resultSet.getString("USER_ID"));
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
    public static List<Publish> query7(String assetID){
        List<Publish> result = new ArrayList<Publish>();
        try {
            Publish o = null;
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "SELECT P.* FROM PUBLISH P WHERE P.ASSET_ID = '" + assetID + "'";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData meta   = resultSet.getMetaData();
            int colmax = meta.getColumnCount();
            while (resultSet.next()) {
                Publish obj = new Publish();
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                obj.setP_id(resultSet.getString("P_ID"));
                result.add(obj);
            }

            for(Publish obj : result) {
                System.out.print(obj.asset_id);
                System.out.print(" " + obj.p_id);

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Q8: Find all assets that is under an asset category
    public static List<Asset_AC> query8(String AC_ID){
        List<Asset_AC> result = new ArrayList<Asset_AC>();
        try {
            Asset_AC o = null;
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "SELECT AC.* FROM ASSET_CATEGORY AC WHERE AC.AC_ID = '" + AC_ID + "'";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData meta   = resultSet.getMetaData();
            int colmax = meta.getColumnCount();
            while (resultSet.next()) {
                Asset_AC obj = new Asset_AC();
                obj.setAc_id(resultSet.getString("AC_ID"));
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                result.add(obj);
            }

            for(Asset_AC obj : result) {
                System.out.print(obj.ac_id);
                System.out.print(" " + obj.asset_id);

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Q9: Find the librarian who check in an asset follow with the check in date
    public static List<Checkin> query9(String L_ID, String Checkin_date ){
        List<Checkin> result = new ArrayList<Checkin>();
        try {
            Checkin o = null;
            Connection c = MyJDBC.getConnection();
            Statement st = c.createStatement();
            String query = "SELECT C.L_ID AS LIBARIAN, C.CHECKIN_DATE FROM CHECKIN C WHERE C.L_ID = '" + L_ID + "' AND C.CHECKIN_DATE = '" + Checkin_date + "'";
            ResultSet resultSet = st.executeQuery(query);
            ResultSetMetaData meta   = resultSet.getMetaData();
            int colmax = meta.getColumnCount();
            while (resultSet.next()) {
                Checkin obj = new Checkin();
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                obj.setCheckin_date(resultSet.getDate("CHECKIN_DATE").toString());
                obj.setCheckin_id(resultSet.getString("CHECKIN_ID"));
                obj.setL_id(resultSet.getString("L_ID"));
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
