import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import model.*;

import java.sql.ResultSet;

public class LibRepo {
	public Connection c;

	public LibRepo() {
		DBUTIL.registerDriver();
	}

	public Boolean newUser(String id, String name, String dob, String email) {
		try {
			c = DBUTIL.getConnection();
			Statement st = c.createStatement();
			String check = "SELECT L.USER_ID FROM L_USER L WHERE L.USER_ID = '" + id + "'";
			ResultSet resultSet = st.executeQuery(check);
			if (!resultSet.next()) {
				String query = "INSERT INTO L_USER VALUES('" + id + "', '" + name + "', to_date('" + dob
						+ "', 'yyyy/mm/dd'), '" + email + "')";
				resultSet = st.executeQuery(query);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean newLibrarian(String id, String name) {
    	try {
    		c = DBUTIL.getConnection();
    		Statement st = c.createStatement();
            String check  = "SELECT * FROM LIBRARIAN L WHERE L.L_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if (!resultSet.next()) {
            	String query = "INSERT INTO LIBRARIAN VALUES('" + id + "', '" + name + "')";	
            	resultSet = st.executeQuery(query);
            	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
	public Boolean newVolunteer(String id, String name) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT V.V_ID FROM VOLUNTEER V WHERE V.V_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if (!resultSet.next()) {
            	String query = "INSERT INTO VOLUNTEER VALUES('" + id + "', '" + name + "')";
            	resultSet = st.executeQuery(query);
            	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Boolean newStudyRoom(String number, String capacity) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT S.ROOM_NUM FROM STUDYROOM S WHERE S.ROOM_NUM = '" + number + "'";
            ResultSet resultSet = st.executeQuery(check);
            if(!resultSet.next()) {
            	String query = "INSERT INTO STUDYROOM VALUES('" + number + "', '" + capacity + "')";
            	resultSet = st.executeQuery(query);
            	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Boolean newLibraryCard(String id, String validThru) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT LC.C_ID FROM LIBRARYCARD LC WHERE LC.C_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if(!resultSet.next()) {
        	String query = "INSERT INTO LIBRARYCARD VALUES('" + id + "', '" + validThru + "')";
        	resultSet = st.executeQuery(query);
            return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Boolean newPublishingHouse(String id, String name) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT P.P_ID FROM PUBLISHINGHOUSE P WHERE P.P_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if(!resultSet.next()) {
        	String query = "INSERT INTO PUBLISHINGHOUSE VALUES('" + id + "', '" + name + "')";
        	resultSet = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Boolean newDVD(String id, String name, String length) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT A.ASSET_ID FROM ASSET A WHERE A.ASSET_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if(!resultSet.next()) {
        	String query = "INSERT INTO ASSET VALUES('" + id + "', '" + name + "')";
        	resultSet = st.executeQuery(query);
        	query = "INSERT INTO DVD VALUES('" + id + "', '" + length + "')";
        	resultSet = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Boolean newBook(String id, String name, String pages) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT A.ASSET_ID FROM ASSET A WHERE A.ASSET_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if(!resultSet.next()) {
        	String query = "INSERT INTO ASSET VALUES('" + id + "', '" + name + "')";
        	resultSet = st.executeQuery(query);
        	query = "INSERT INTO BOOK VALUES('" + id + "', '" + pages + "')";
        	resultSet = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Boolean newCategory(String id, String name, String location) {
    	try {
    		c = DBUTIL.getConnection();
            Statement st = c.createStatement();
            String check  = "SELECT AC.AC_ID FROM ASSET_CATEGORY AC WHERE AC.AC_ID = '" + id + "'";
            ResultSet resultSet = st.executeQuery(check);
            if(!resultSet.next()) {
        	String query = "INSERT INTO ASSET_CATEGORY VALUES('" + id + "', '" + name + "', '" + location + "')";
        	resultSet = st.executeQuery(query);
        	return true;
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }

	public ArrayList<L_User> findUser() {
		ResultSet rs = null;
		ArrayList<L_User> list = new ArrayList<>();

		try {
			c = DBUTIL.getConnection();
			Statement stmt = c.createStatement();
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
				try {
					rs.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}
				;
			}
			if (null != c) {
				try {
					c.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}
				;
			}
		}
		return list;
	}
	
	public ArrayList<Reserve> findReservedRoomUser(int roomNum) 
	   {
			c = DBUTIL.getConnection();
		   ResultSet rs = null;
		   ArrayList<Reserve> list = new ArrayList<>();
		   try 
		   {
	           Statement stmt = c.createStatement();
	           rs = stmt.executeQuery("SELECT USER_ID, ROOM_NUM FROM RESERVE WHERE ROOM_NUM = " + roomNum);
	           while (rs.next()) {
					Reserve res = new Reserve();
					res.setUser_id(rs.getString("USER_ID"));
					res.setRoom_num(rs.getString("ROOM_NUM"));
					list.add(res);
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

	public ArrayList<Book> findBooksWithNumPages(int amount) 
	   {
		   c = DBUTIL.getConnection();
		   ResultSet rs = null;
		   ArrayList<Book> list = new ArrayList<>();
		   
		   try 
		   {
	           Statement stmt = c.createStatement();
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
	             if (null != c) {
	               try { c.close(); } catch(Exception ex) { System.out.println(ex); };
	             }
	       }
		   return list;
	   }
	
	public ArrayList<Checkout> query5(String assetID, String userID) {
    	ArrayList<Checkout> result = new ArrayList<Checkout>();
    	c = DBUTIL.getConnection();
    	try {
            Statement st = c.createStatement();
        	String query = "SELECT C.* FROM CHECKOUT C WHERE C.USER_ID = '" + userID + "' AND C.ASSET_ID = '" + assetID + "'";
        	ResultSet resultSet = st.executeQuery(query);
        	while (resultSet.next()) {
                Checkout obj = new Checkout();
               obj.setCheckout_id(resultSet.getString("CHECKOUT_ID"));
               obj.setCheckout_date(resultSet.getDate("CHECKOUT_DATE").toString());
               obj.setAsset_id(resultSet.getString("ASSET_ID"));
               obj.setUser_id(resultSet.getString("USER_ID"));
               result.add(obj);
              }
         }
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
	
	public ArrayList<Donate> query6(String assetID){
    	ArrayList<Donate> result = new ArrayList<Donate>();
    	c = DBUTIL.getConnection();
    	try {
            Statement st = c.createStatement();
        	String query = "SELECT D.* FROM DONATE D WHERE D.ASSET_ID = '" + assetID + "'";
        	ResultSet resultSet = st.executeQuery(query);
        	while (resultSet.next()) {
               Donate obj = new Donate();
               obj.setDonate_id(resultSet.getString("DONATE_ID"));
               obj.setDonate_date(resultSet.getDate("DONATE_DATE").toString());
               obj.setAsset_id(resultSet.getString("ASSET_ID"));
               obj.setUser_id(resultSet.getString("USER_ID"));
               result.add(obj);
              }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    } 
	
	public ArrayList<Publish> query7(String pid){
        ArrayList<Publish> result = new ArrayList<Publish>();
        c = DBUTIL.getConnection();
        try {
            Statement st = c.createStatement();
            String query = "SELECT P.* FROM PUBLISH P WHERE P.P_ID = '" + pid + "'";
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Publish obj = new Publish();
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                obj.setP_id(resultSet.getString("P_ID"));
                result.add(obj);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public ArrayList<Asset_AC> query8(String AC_ID){
        ArrayList<Asset_AC> result = new ArrayList<Asset_AC>();
        c = DBUTIL.getConnection();        
        try {
            Statement st = c.createStatement();
            String query = "SELECT A.* FROM ASSET_AC A WHERE A.AC_ID = '" + AC_ID + "'";
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Asset_AC obj = new Asset_AC();
                obj.setAc_id(resultSet.getString("AC_ID"));
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                result.add(obj);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public ArrayList<Checkin> query9(String L_ID){
        ArrayList<Checkin> result = new ArrayList<Checkin>();
        c = DBUTIL.getConnection();
        try {
            Statement st = c.createStatement();
            String query = "SELECT C.* FROM CHECKIN C WHERE C.L_ID = '" + L_ID + "'";
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Checkin obj = new Checkin();
                obj.setCheckin_id(resultSet.getString("CHECKIN_ID"));
                obj.setCheckin_date(resultSet.getDate("CHECKIN_DATE").toString());
                obj.setAsset_id(resultSet.getString("ASSET_ID"));
                obj.setL_id(resultSet.getString("L_ID"));
                result.add(obj);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public void buildTables() throws SQLException{
		c = DBUTIL.getConnection();
		DBUTIL.Database(c);
		c.close();
	}

	public void dropTables() throws SQLException{
		c= DBUTIL.getConnection();
		DBUTIL.Dropbase(c);
		c.close();
		c = null;
	}
}
