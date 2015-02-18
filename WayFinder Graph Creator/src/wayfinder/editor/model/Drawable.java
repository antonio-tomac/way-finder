
package wayfinder.editor.model;

import java.awt.Graphics;
import wayfinder.editor.model.utils.Vector;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public interface Drawable {
	
	void draw(Graphics g, Vector offset, double scale);
	
}
