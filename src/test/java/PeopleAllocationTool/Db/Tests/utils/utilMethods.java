package PeopleAllocationTool.Db.Tests.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class utilMethods {

    public static String pathConstructor(String fileName){
        String path = "src/test/resources/PeopleAllocationTool.Db.Tests/bodyFiles/"+fileName+ ".json";
        return path;
    }

    public static JSONObject fileParser(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(
                new FileReader(path));
        return data;
    }

    static public String insertRowProject(JSONObject row, String tableName){
        String sql = "INSERT INTO " + tableName + " VALUES (" +
                row.get("pimProjectId") +", '"+
                row.get("name")+"', '"+
                row.get("projectFic")+"', '"+
                row.get("pimProjectStatus")+"', '"+
                row.get("startDate")+"', '"+
                row.get("endDate")+"', '"+
                row.get("projectManagersUsernames")+"', '"+
                row.get("clientExecutivesUsernames")+"', '"+
                row.get("clientName")+"', '"+
                row.get("countryName")+"', '"+
                row.get("technology")+"', '"+
                row.get("direction")+ "')";
        return sql;
    }

    static public String updateRowProject(JSONObject row, String tableName){
        String sql = "UPDATE " + tableName + " SET name = " +"'" + row.get("name") + "'" + " WHERE pimProjectId = " + row.get("pimProjectId");
        return sql;
    }

    static public String deleteRowProject(JSONObject row, String tableName){
        String sql = "DELETE FROM " + tableName +" WHERE pimProjectId = " + row.get("pimProjectId");
        System.out.println(sql);
        return sql;
    }


}
