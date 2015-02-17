
package wayfinder.editor.model.anchors;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class AbsoluteAnchor implements Anchor {
	
	private double x;
	private double y;
	private double z;

	@Override
	public double getGlobalX() {
		return x;
	}

	@Override
	public double getGlobalY() {
		return y;	
	}	
	
	@Override
	public double getGlobalZ() {
		return z;	
	}	
	
}
