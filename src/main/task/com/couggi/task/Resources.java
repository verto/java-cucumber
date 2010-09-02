package com.couggi.task;

import java.util.ArrayList;
import java.util.List;

import org.mortbay.resource.ResourceCollection;

/**
 * Representa um webproject path.
 * 
 * @author Everton Cardoso
 *
 */
public class Resources {

	private final List<Path> paths = new ArrayList<Path>();
	
	public void addPath(Path path) {
		this.paths.add(path);
	}
	
	public ResourceCollection getResources() {
		String[] resources = new String[paths.size()];
		for (int i=0; i < paths.size(); i++) { 
			resources[i] = paths.get(i).toString();
		}
		return new ResourceCollection(resources);
	}
}
