package PeopleAllocationTool.Db.Tests.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import cucumber.api.java.sl.In;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import static PeopleAllocationTool.Db.Tests.utils.dbConnection.databaseConnectionMethod;
import static PeopleAllocationTool.Db.Tests.utils.utilMethods.*;

public class Steps {

    Statement statement;
    
    @Before
    public void database_Connection() throws SQLException, ClassNotFoundException {
        statement = databaseConnectionMethod();
    }

    @After
    public void close_connection() throws SQLException {
        statement.close();
    }


    @When("I {string} Data In Table {string} With Specific Data {string}")
    public void i_Data_In_Table_With_Specific_Data(String command, String tableName, String file) throws IOException, ParseException, SQLException {
        // executeUpdate: insert, update, delete
        switch (command) {
            case "insert":
                statement.executeUpdate(insertRowProject(fileParser(pathConstructor(file)),tableName));
                break;
            case "update":
                statement.executeUpdate(updateRowProject(fileParser(pathConstructor(file)),tableName));
                break;
            case "delete":
                statement.executeUpdate(deleteRowProject(fileParser(pathConstructor(file)),tableName));
                break;
        }



    }

    @Then("I Verify That {string} Data Is {string} In {string}")
    public void i_Verify_That_The_Data_Is_In(String path, String comand,  String tableName) throws SQLException, IOException, ParseException {
        JSONObject data = fileParser(pathConstructor(path));
        String id = data.get("pimProjectId").toString();
        String sql = "SELECT * FROM " + tableName + " WHERE pimProjectId = "+ id;
        ResultSet result = statement.executeQuery(sql);
        switch (comand){
            case "added":
                Assert.assertTrue(result.isBeforeFirst());
                break;
            case "updated":
                result.next();
                String value = result.getString("pimProjectId");
                Assert.assertEquals(id,value);
                break;
            case "deleted":
                Assert.assertFalse(result.isBeforeFirst());
                break;
        }
    }


}
