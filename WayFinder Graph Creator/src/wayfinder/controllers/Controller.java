
package wayfinder.controllers;

import java.awt.Graphics;
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

	public Controller() {
		this.model = new Model();
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public Model getModel() {
		return model;
	}
	
	public void addFloorPressed() {
//        JFileChooser fc = new JFileChooser("/home/antonio/");
//        int result = fc.showOpenDialog(null);
//        if (result != JFileChooser.APPROVE_OPTION) {
//            return;
//        }
		File selectedFile = new File("/home/antonio/Desktop/poster.jpg");//fc.getSelectedFile();
		BufferedImage image;
		try {
			image = ImageIO.read(selectedFile);
		} catch (IOException ex) {
			return;
		}
		Floor floor = new Floor(image, 1, Floor.Rotation.ACW, new AbsoluteAnchor(0, 0, 0), selectedFile.getName());
		model.addFloor(floor);
		editor.repaint();
	}
	
	public void drawFocusedFloor(Graphics g) {
		if (!model.getFloors().isEmpty()) {
			Floor floor = model.getFloors().iterator().next();
			floor.draw(g, new Vector(0, 0), 1);
		}
	}
}
