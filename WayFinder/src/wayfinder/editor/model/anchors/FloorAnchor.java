
package wayfinder.editor.model.anchors;

import wayfinder.editor.model.Floor;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class FloorAnchor implements Anchor {
	
	private Floor toFlor;
	private double xOffset;
	private double yOffset;

	@Override
	public double getGlobalX() {
		return toFlor.getAnchor().getGlobalX() + xOffset;
	}

	@Override
	public double getGlobalY() {
		return toFlor.getAnchor().getGlobalY() + yOffset;
	}
	
}
