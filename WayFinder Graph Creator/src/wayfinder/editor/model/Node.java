
package wayfinder.editor.model;

import wayfinder.editor.model.utils.Vector;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Node {
	
	private Vector position;
	private String room;
	private RoomType roomType;

	public Node(Vector position, String room, RoomType roomType) {
		this.position = position;
		this.room = room;
		this.roomType = roomType;
	}
	
}
