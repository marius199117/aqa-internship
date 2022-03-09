package PeopleAllocationTool.Api.Tests.steps;

import PeopleAllocationTool.Api.Tests.utils.ScenarioContext;
import PeopleAllocationTool.Api.Tests.utils.TestContext;
import PeopleAllocationTool.Ui.Tests.Environment;
import PeopleAllocationTool.Ui.Tests.Utility;

import java.util.Properties;

public class BaseStep {
    public static Properties properties = Utility.getPropertiesFromFile("config.properties");
    public static Environment environment = Environment.newInstance("PeopleAllocationTool.Api.Tests/ContextAPI.json");
    private ScenarioContext scenarioContext;

    public BaseStep(TestContext testContext) {
        scenarioContext = testContext.getScenarioContext();
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    /*
    Create the request URL based on host
     */
    public String getUrl(String path)
    {
        return properties.getProperty("PAT") + ":" + environment.resolve(path);
    }

    public String getUrl(String path, Integer id)
    {
        if(path.contains("withParam")){
            return properties.getProperty("PAT") + ":" + environment.resolve(path) + id;
        }
        else if(path.equals("endpoints.getAllocations")){
            return properties.getProperty("PAT") + ":" + environment.resolve(path) + 1 + "/allocations";
        }
        return properties.getProperty("PAT") + ":" + environment.resolve(path);
    }
}
