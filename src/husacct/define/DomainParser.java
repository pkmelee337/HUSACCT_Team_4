package husacct.define;

import java.util.ArrayList;

import husacct.common.dto.ApplicationDTO;
import husacct.common.dto.ModuleDTO;
import husacct.define.domain.Application;
import husacct.define.domain.SoftwareArchitecture;
import husacct.define.domain.module.Module;

public class DomainParser {

	public ApplicationDTO parseApplication(Application app) {
		ApplicationDTO appDTO = new ApplicationDTO();
		appDTO.name = app.getName();
		appDTO.paths = app.getPaths();
		appDTO.programmingLanguage = app.getLanguage();
		return appDTO;
	}
	
	public ModuleDTO[] parseModules(Module[] modules){
		ArrayList<ModuleDTO> moduleDTOsList = new ArrayList<ModuleDTO>();
		for (Module module : modules){
			ModuleDTO moduleDTO = parseModule(module);
			moduleDTOsList.add(moduleDTO);
		}
		ModuleDTO[] moduleDTOs = new ModuleDTO[moduleDTOsList.size()];
		moduleDTOsList.toArray(moduleDTOs);
		return moduleDTOs;
	}
	
	public ModuleDTO parseModule(Module module){
		ModuleDTO modDTO = new ModuleDTO();
		modDTO.logicalPath = getLogicalPath(module.getId());
		modDTO.physicalPaths = module.getPhysicalPaths();
		modDTO.type = module.getType();
		
		ArrayList<ModuleDTO> subModuleDTOsList = new ArrayList<ModuleDTO>();
		for (Module subModule : module.getSubModules()){
			ModuleDTO subModuleDTO = parseModule(subModule);
			subModuleDTOsList.add(subModuleDTO);
		}
		
		ModuleDTO[] subModuleDTOs = new ModuleDTO[subModuleDTOsList.size()];
		subModuleDTOsList.toArray(subModuleDTOs);
		modDTO.subModules = subModuleDTOs; 
		return modDTO;
	}
	
	public String getLogicalPath(long moduleId){
		String logicalPath = SoftwareArchitecture.getInstance().getLogicalPath(moduleId);
		return logicalPath;
	}
}
