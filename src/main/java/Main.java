import java.sql.*;

/**
 * Created by vic on 22.11.16.
 */
public class Main {
    public static final String INSERT_QUERY = "insert into MyTableName values (5, 'Коля')";
    public static final String PREPARED_INSERT_QUERY = "insert into MyTableName values (?, ?);";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        try(Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement preparedStatement = con.prepareStatement(PREPARED_INSERT_QUERY);
            Statement statement1 = con.createStatement()) {
            for (int i = 7; i < 100; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "name " + i);
                preparedStatement.executeUpdate();
            }
            ResultSet resultSet = statement1.executeQuery("select * from MyTableName");
            while (resultSet.next()) {
                System.out.println("id = " + resultSet.getInt(1) + " | " + resultSet.getString(2));
            }
        }

        /*Statement statement = con.createStatement()){
            //statement.execute("CREATE TABLE MyTableName(PKFieldName IDENTITY PRIMARY KEY, StringFieldName VARCHAR(255))");
            //statement.executeUpdate(INSERT_QUERY);
            ResultSet resultSet = statement.executeQuery("select * from MyTableName");
            while (resultSet.next()) {
                System.out.println("id = " + resultSet.getInt(1) + " | " + resultSet.getString(2));
            }
        }*/
    }
}
