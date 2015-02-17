
package wayfinder.editor.model;

import java.util.Set;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Model {
	
	private Set<Floor> floors;
	private Set<Edge> edges;
	
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
	
}
