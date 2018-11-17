package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBconn {
  //store information (constant) about DB that is used to access it.
  //realistically you should ask the user to enter this information.
  private static final String USER = "root";
  //the password should also be salted and hashed.
  private static final String PASSWORD = "yourDBpassword";
  private static final String CONN_ADD = "jdbc:mysql://localhost:3306/myDiabetesSupplies";

  /**
   * Static final function startConn, uses the constants provided to create an object of type
   * Connection that can be used to query the DB.
   * Uses jdbc Driver to handle connection.
   *
   * @return Connection - Database Connection Object
   */
  public static final Connection startConn() {
    Connection conn = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(CONN_ADD, USER, PASSWORD);

    } catch (Exception e) {
      System.out.println(e.toString());
    }

    return conn;
  }

  /**
   * Static final function endConn, gets an object of type Connection in, and uses its method
   * close() to terminate it. Returns an integer to indicate the status of the operation.
   *
   * @param conn - DB Connection Object
   * @return int - status of operation (1 = ok, -1 = error)
   */
  public static final int endConn(Connection conn) {
    int status = -1;
    try {
      conn.close();
      status = 1;
    } catch (SQLException e) {
      System.out.println(e.toString());
    }

    return status;
  }

  /**
   * Static final function queryDB, is used whenever data manipulation is not needed. If it is,
   * then need to use updateBD.
   * Runs a query on a database, and returns the ResultSet of said query.
   *
   *
   * @param query - String that contains query to run
   * @param conn - DB Connection Object
   * @return ResultSet - ResultSet of the operation
   */
  public static final ResultSet queryDb(String query, Connection conn) {
    Statement st = null;
    ResultSet rs = null;

    try {
      //using Connection object create a statement
      st = conn.createStatement();

      //with that statement, run query and save result to ResultSet
      rs = st.executeQuery(query);

    } catch (SQLException e) {
      System.out.println(e.toString());
    }

    return rs;
  }

  /**
   * Static final updateDB, used whenever the user needs to manipulate data in the database.
   * If data manipulation is not needed, use function queryDb().
   * Runs a query on a database, and returns the integer status of said query.
   *
   * @param query - String that contains query to run
   * @param conn - DB Connection Object
   * @return status - integer status of operation (1 = ok, -1 = error)
   */
  public static final int updateDb(String query, Connection conn) {
    Statement st = null;
    int status = -1;

    try {
      //create statement
      st = conn.createStatement();

      //executeUpdate returns integer that can be used for propagation of error
      status = st.executeUpdate(query);

    } catch (SQLException e) {
      System.out.println(e.toString());
    }

    return status;
  }
}
