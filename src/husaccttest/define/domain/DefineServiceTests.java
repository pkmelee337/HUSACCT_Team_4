package husaccttest.define.domain;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import husacct.common.dto.ApplicationDTO;
import husacct.common.dto.ModuleDTO;
import husacct.define.DefineServiceImpl;
import husacct.define.domain.Application;
import husacct.define.domain.AppliedRule;
import husacct.define.domain.SoftwareArchitecture;
import husacct.define.domain.module.Layer;
import husacct.define.domain.module.Module;

import org.junit.Before;
import org.junit.Test;

public class DefineServiceTests {
	private SoftwareArchitecture sA = SoftwareArchitecture.getInstance();
	private DefineServiceImpl defineService = new DefineServiceImpl();
	
	//Before
	@Before
	public void setUp(){
		sA = new SoftwareArchitecture("Test architecture", "This architecture is used for testing purposes");
		SoftwareArchitecture.setInstance(sA);
		Module module1 = new Module("Module 1", "This is module 1");
		Module module2 = new Module("Module 2", "This is module 2");
		Module module3 = new Module("Module 3", "This is module 3");

		Module layer1 = new Layer("Layer 1", "This is layer 1", 1);
		Module layer2 = new Layer("Layer 2", "This is layer 2", 2);

		Module subModule1 = new Module("Sub Module 1", "This is a submodule");
		Module subModule11 = new Module("Sub Module 11", "This is a submodule");
		Module subModule2 = new Module("Sub Module 2", "This is a submodule");		
		Module subModule3 = new Module("Sub Module 3", "This is a submodule");		

		AppliedRule rule1 = new AppliedRule("IsNotAllowedToUse", "Test", new String[1],
				"prefix", "suffix", module1,
				null);
		AppliedRule rule2 = new AppliedRule("IsNotAllowedToUse", "Test", new String[1],
				"prefix", "suffix", module1,
				null);
		AppliedRule rule3 = new AppliedRule("IsNotAllowedToUse", "Test", new String[1],
				"prefix", "suffix", module1,
				null);

		AppliedRule exception1 = new AppliedRule("IsNotAllowedToUse", "Test", new String[1],
				"prefix", "suffix", module1,
				null);
		AppliedRule exception2 = new AppliedRule("IsNotAllowedToUse", "Test", new String[1],
				"prefix", "suffix", module1,
				null);
		AppliedRule exception3 = new AppliedRule("IsNotAllowedToUse", "Test", new String[1],
				"prefix", "suffix", module1,
				null);
		
		//TODO: Test SoftwareUnitDefinitions
		module1.addSubModule(subModule1);
		module1.addSubModule(subModule11);
		module2.addSubModule(subModule2);
		module3.addSubModule(subModule3);
		
		rule1.addException(exception1);
		rule1.addException(exception1);
		rule1.addException(exception2);
		rule1.addException(exception2);
		rule1.addException(exception3);
		
		layer1.addSubModule(module1);
		layer2.addSubModule(module2);
		
		sA.addModule(layer1);
		sA.addModule(layer2);
		sA.addModule(module3);
		
		sA.addAppliedRule(rule1);
		sA.addAppliedRule(rule2);
		sA.addAppliedRule(rule3);
	}
	
	@Test
	public void createApplication(){
		boolean testWorks = true;
		defineService.createApplication("Application1", new String[] {"c:/Application1/"}, "Java");
		Application app = Application.getInstance();
		
		testWorks = testWorks && app.getName().equals("Application1");
		testWorks = testWorks && areArraysEqual(app.getPaths(), new String[] {"c:/Application1/"});
		testWorks = testWorks && app.getLanguage().equals("Java");
		assertTrue(testWorks);
	}
	
	@Test
	public void getApplication(){
		boolean testWorks = true;
		ApplicationDTO appDTO = defineService.getApplicationDetails();
		
		testWorks = testWorks && appDTO.name.equals("Application1");
		testWorks = testWorks && areArraysEqual(appDTO.paths, new String[] {"c:/Application1/"});
		testWorks = testWorks && appDTO.programmingLanguage.equals("Java");
		assertTrue(testWorks);
	}
	
	@Test 
	public void getRootModules(){
		//TODO
		boolean testWorks = true;
		ModuleDTO[] rootModuleDTOs = defineService.getRootModules();
		testWorks = testWorks && rootModuleDTOs.length == 3;
		
		testWorks = testWorks && rootModuleDTOs[0].logicalPath.equals("Layer 1");
		testWorks = testWorks && rootModuleDTOs[0].type.equals("Layer");
		testWorks = testWorks && areArraysEqual(rootModuleDTOs[0].physicalPaths, new String[] {});
		testWorks = testWorks && rootModuleDTOs[0].subModules.length == 0;
		
		testWorks = testWorks && rootModuleDTOs[1].logicalPath.equals("Layer 2");
		testWorks = testWorks && rootModuleDTOs[1].type.equals("Layer");
		testWorks = testWorks && areArraysEqual(rootModuleDTOs[1].physicalPaths, new String[] {});
		testWorks = testWorks && rootModuleDTOs[1].subModules.length == 0;
		
		testWorks = testWorks && rootModuleDTOs[2].logicalPath.equals("Module 3");
		testWorks = testWorks && rootModuleDTOs[2].type.equals("Module");
		testWorks = testWorks && areArraysEqual(rootModuleDTOs[2].physicalPaths, new String[] {});
		testWorks = testWorks && rootModuleDTOs[2].subModules.length == 0;
		
		assertTrue(testWorks);
	}
	
	private boolean areArraysEqual(String[] list, String[] list2){
		boolean areEqual = true;
		if (list.length == list2.length){
			for (int i = 0;i<list.length;i++){
				areEqual = areEqual && list[i].equals(list2[i]);
			}
		}
		else{
			areEqual = false;
		}
		return areEqual;
	}

}
