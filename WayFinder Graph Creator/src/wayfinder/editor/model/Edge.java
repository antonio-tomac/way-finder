
package wayfinder.editor.model;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Edge {
	
	private final Node node1;
	private final Node node2;
	private PermissionType permissionType;
	private AccessType accessType;

	public Edge(Node node1, Node node2, PermissionType permissionType, AccessType accessType) {
		this.node1 = node1;
		this.node2 = node2;
		this.permissionType = permissionType;
		this.accessType = accessType;
	}	
	
	
}
