package edu.nwpu.edap.edapplugin.library;

import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.external.sceneConfig.Scenario;
import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.Map;

public class SceneConfigLibrary {

    /**
     * 场景个数
     */
    private static int num;

    /**
     * 场景List
     */
    private static ArrayList<Scenario> scenarios;
    
    private static Map<Integer, Scenario> scenarioMapById;
    
    public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		SceneConfigLibrary.num = num;
	}

	public static ArrayList<Scenario> getScenarios() {
		return scenarios;
	}

	public static void setScenarios(ArrayList<Scenario> scenarios) {
		SceneConfigLibrary.scenarios = scenarios;
		if(scenarioMapById==null) {
			scenarioMapById = new HashedMap<Integer, Scenario>();
		}
		for(Scenario scenario:scenarios) {
			scenarioMapById.put(scenario.getId(), scenario);
		}
	}
	
	/**
	 * 判断符合的场景的id，若不符合则返回-1
	 * @param rpMessage
	 * @return
	 */
	public static int matchScenario(RPMessage rpMessage) {
		for(Scenario scenario:scenarios) {
			if(scenario.match(rpMessage)) {
				return scenario.getId();
			}
		}
		return -1;
	}
	
	/**
	 * 根据id返回对应的Scenario
	 * @param id
	 * @return
	 */
	public static Scenario getScenarioById(int id) {
		return scenarioMapById.get(id);
	}

    public static String show() {
        return "SceneConfig{" +
                "num=" + num +
                ", scenes=" + scenarios +
                '}';
    }
}
