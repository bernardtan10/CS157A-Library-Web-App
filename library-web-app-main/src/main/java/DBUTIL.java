import java.io.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUTIL {
	private static Connection c = null;

	public static void registerDriver() {
		try {
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			if (c != null && !c.isClosed()) {
				if (c.isValid(500)) {
					return c;
				}
			} else {
				try {
					return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
					// System.out.println("Database connected");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void Database(Connection c) {
		String s;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			InputStream is = DBUTIL.class.getClassLoader().getResourceAsStream("createTables.sql");
			Reader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);
			while ((s = br.readLine()) != null) {
				stringBuilder.append(s);
			}
			br.close();
			String[] inst = stringBuilder.toString().split(";");
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

	public static void Dropbase(Connection c) {
		String s;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			InputStream is = DBUTIL.class.getClassLoader().getResourceAsStream("dropTables.sql");
			Reader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);
			while ((s = br.readLine()) != null) {
				stringBuilder.append(s);
			}
			br.close();

			String[] inst = stringBuilder.toString().split(";");
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
}
