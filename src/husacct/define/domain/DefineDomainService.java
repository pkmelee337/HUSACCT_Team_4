package husacct.define.domain;

import husacct.define.domain.domainOLD.ArchitectureDefinition;
import husacct.define.domain.module.*;
import husacct.define.domain.*;

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
		SoftwareArchitecture.addModule(layer);
	}
	
	public void removeLayerByLevel(int layerLevel) {
		SoftwareArchitecture.removeLayerByLevel(int layerLevel);
	}
	
	public void moveLayerUp(int level){
		
	}
	
}
