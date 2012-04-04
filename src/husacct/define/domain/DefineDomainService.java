package husacct.define.domain;

import java.util.ArrayList;

import husacct.define.domain.domainOLD.ArchitectureDefinition;
import husacct.define.domain.module.*;

public class DefineDomainService {
	private static DefineDomainService instance = null;
	public static DefineDomainService getInstance() {
		return instance == null ? (instance = new DefineDomainService()) : instance;
	}
	
	public DefineDomainService() {
		
	}
	
	public void addLayer(String name, int level) {
		Module layer = new Layer(name, level);
		((Layer) layer).setHierarchicalLevel(level);
		SoftwareArchitecture.getInstance().addModule(layer);
	}
	
	public void removeLayerByLevel(int layerLevel) {
		SoftwareArchitecture.getInstance().removeLayerByLevel(layerLevel);
	}
	
	public void moveLayerUp(int level){
		
	}
	
	public void createNewArchitectureDefinition(String name) {
		SoftwareArchitecture softwareArchitecture = SoftwareArchitecture.getInstance();
		softwareArchitecture.setName(name);
	}

	public void setLayerName(int layerId, String newName) {
		//SoftwareArchitecture.getInstance().setModuleName(layerId, newName);
	}

	public ArrayList<Integer> getLayerLevels() {
		//returns an array of the Integer of the hierarchical level of each Layer
		return SoftwareArchitecture.getInstance().getLevelFromLayers();
	}

	public String getLayerNameByLevel(int layerLevel) {
		return SoftwareArchitecture.getInstance().getLayerNameByLevel(layerLevel);
	}
	
}
