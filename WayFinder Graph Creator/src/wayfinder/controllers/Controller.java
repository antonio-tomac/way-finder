package wayfinder.controllers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import wayfinder.editor.model.Floor;
import wayfinder.editor.model.Model;
import wayfinder.editor.model.anchors.AbsoluteAnchor;
import wayfinder.editor.model.utils.Vector;
import wayfinder.editor.view.Editor;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Controller {

	private Editor editor;
	private Model model;
	private double zoom = 1;
	private Vector offsetPosition = new Vector(0, 0);
	private int dragStartingX = 0;
	private int dragStartingY = 0;
	private boolean inDrag = false;

	public Controller() {
		this.model = new Model();
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public Model getModel() {
		return model;
	}

	private Vector getSpacePoint(Vector pos) {
		return getSpacePoint(pos, zoom);
	}

	private Vector getSpacePoint(Vector pos, double zoom) {
		return pos.minus(offsetPosition.multiply(zoom)).multiply(1 / zoom);
	}

	public void scrolled(double ammount, Vector pos) {
		double zoomOld = zoom;
		zoom *= Math.pow(1.05, -ammount);
		Vector spacePoint = getSpacePoint(pos, zoomOld);
		Vector p1 = spacePoint.plus(offsetPosition).multiply(zoomOld);
		Vector p2 = spacePoint.plus(offsetPosition).multiply(zoom);
		Vector shift = p1.minus(p2);
		moved(shift.x, shift.y);
		//refreshAddingImage(pos);
		editor.repaint();
	}

	public void dragged(int x, int y, boolean rightClick) {
		Vector pos = new Vector(x, y);
		Vector spacePoint = getSpacePoint(pos);
		int xMove = x - dragStartingX;
		int yMove = y - dragStartingY;
		Vector from = getSpacePoint(new Vector(dragStartingX, dragStartingY));
		if (rightClick) {
			if (!inDrag) {
				dragStartingX = x;
				dragStartingY = y;
			}
			editor.repaint();
		} else {
			if (!inDrag) {
				from = getSpacePoint(pos);
			}
			dragStartingX = x;
			dragStartingY = y;
			if (inDrag) {
				boolean edited = false;
				if (!edited) {
					moved(xMove, yMove);
				} else {
					editor.repaint();
				}
			}
		}
		inDrag = true;
	}

	public void mouseReleased() {
		editor.repaint();
		inDrag = false;
	}

	public void moved(double x, double y) {
		offsetPosition = offsetPosition.plus(new Vector(x / zoom, y / zoom));
		editor.repaint();
	}

	public void addFloorPressed() {
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File selectedFile = fc.getSelectedFile();
		BufferedImage image;
		try {
			image = ImageIO.read(selectedFile);
		} catch (IOException ex) {
			return;
		}
		Floor floor = new Floor(image, 1, new AbsoluteAnchor(0, 0, 0), selectedFile.getName());
		model.addFloor(floor);
		editor.repaint();
	}

	public void rotateSelectedFloorClockwise() {
		if (!model.getFloors().isEmpty()) {
			Floor floor = model.getFloors().iterator().next();
			floor.rotateImageClockwise();
			editor.repaint();
		}
	}

	public void rotateSelectedFloorAnticlockwise() {
		if (!model.getFloors().isEmpty()) {
			Floor floor = model.getFloors().iterator().next();
			floor.rotateImageAntilockwise();
			editor.repaint();
		}
	}

	public void flipSelectedFloor() {
		if (!model.getFloors().isEmpty()) {
			Floor floor = model.getFloors().iterator().next();
			floor.flipImage();
			editor.repaint();
		}
	}

	public void drawFocusedFloor(Graphics g) {
		if (!model.getFloors().isEmpty()) {
			Floor floor = model.getFloors().iterator().next();
			floor.draw(g, offsetPosition, zoom);
		}
	}

	public void keyPressed(KeyEvent keyEvent) {
		if (!model.getFloors().isEmpty()) {
			Floor floor = model.getFloors().iterator().next();
			if (keyEvent.getKeyCode() == KeyEvent.VK_PLUS) {
				floor.setImageScale(floor.getImageScale() * 1.02);
				editor.repaint();
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_MINUS) {
				floor.setImageScale(floor.getImageScale() / 1.02);
				editor.repaint();
			}
		}
	}

	public void keyReleased(KeyEvent keyEvent) {

	}
}
