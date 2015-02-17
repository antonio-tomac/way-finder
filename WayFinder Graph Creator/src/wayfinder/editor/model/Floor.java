
package wayfinder.editor.model;

import java.awt.Image;
import java.util.List;
import wayfinder.editor.model.anchors.Anchor;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Floor {
	
	private Image image;
	private double imageScale;
	private double imageRotation;
	
	private List<Node> nodes;
	private Anchor anchor;
	private String floorName;

	public Image getImage() {
		return image;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public Anchor getAnchor() {
		return anchor;
	}
}
