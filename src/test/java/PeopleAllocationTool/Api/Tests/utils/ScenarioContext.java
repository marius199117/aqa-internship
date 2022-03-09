package PeopleAllocationTool.Api.Tests.utils;

import java.util.HashMap;
import java.util.Map;

/*
 This class holds the test data information specifically.
 It actually uses the Test Context to travel the information between various steps.
 It stores the information in the key-value pair and again, value can be of any type.
 */
public class ScenarioContext {
    private  Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(SharedContext key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(SharedContext key){
        return scenarioContext.get(key.toString());
    }
}
