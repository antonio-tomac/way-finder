
package wayfinder.editor.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Model {
	
	private final Set<Floor> floors;
	private final Set<Edge> edges;

	public Model() {
		floors = new HashSet<>();
		edges = new HashSet<>();
	}
	
	public void addFloor(Floor f) {
		floors.add(f);
	}
	
	public void removeFloor(Floor f) {
		floors.remove(f);
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public void removeEdge(Edge e) {
		edges.remove(e);
	}

	public Set<Floor> getFloors() {
		return Collections.unmodifiableSet(floors);
	}

	public Set<Edge> getEdges() {
		return Collections.unmodifiableSet(edges);
	}
	
}
