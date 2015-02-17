
package wayfinder.editor.model;

import wayfinder.editor.model.utils.Vector;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public interface Selectable {
	
	boolean doesSelect(Vector point, double minDistance);
	
}
