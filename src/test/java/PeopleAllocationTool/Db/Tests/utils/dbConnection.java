package PeopleAllocationTool.Db.Tests.utils;

import PeopleAllocationTool.Ui.Tests.Environment;

import java.sql.*;

public class dbConnection {

    public static Environment environment = Environment.newInstance("PeopleAllocationTool.Db.Tests/contextDB.json");



    public static Statement databaseConnectionMethod() throws ClassNotFoundException, SQLException {
        String url = environment.resolve("databaseConnection.url");
        String user = System.getenv("db_user");
        String password = System.getenv("db_password");
        Class.forName(environment.resolve("databaseConnection.jdbcDriver"));
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        return statement;
    }


}
