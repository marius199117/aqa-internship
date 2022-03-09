package PeopleAllocationTool.Api.Tests.utils;

/*
 The medium to share the information between the different steps in a test
 */
public class TestContext {
    private ScenarioContext scenarioContext;

    public TestContext() {
        scenarioContext = new ScenarioContext();
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
