package wayfinder.editor.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import wayfinder.editor.model.anchors.Anchor;
import wayfinder.editor.model.utils.Vector;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Floor implements Drawable {

	public static enum Rotation {

		NORMAL, CW, ACW, FLIPPED
	}

	private BufferedImage image;
	private double imageScale;
	private Rotation imageRotation;

	private List<Node> nodes;
	private Anchor anchor;
	private String floorName;

	public Floor(BufferedImage image, double imageScale, Rotation imageRotation,
			Anchor anchor, String floorName) {
		this.image = image;
		this.imageScale = imageScale;
		this.imageRotation = imageRotation;
		this.anchor = anchor;
		this.floorName = floorName;
		nodes = new ArrayList<>();
	}

	public Image getImage() {
		return image;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public Anchor getAnchor() {
		return anchor;
	}

	public void rotateImageClockwise() {
		
	}
	
	public void rotateImageAntilockwise() {
		
	}
	
	public void flipImage() {
		
	}
	
	@Override
	public void draw(Graphics g, Vector offset, double scale) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage result;
		if (imageRotation.equals(Rotation.NORMAL)) {
			result = image;
		} else {
			int angle = 0;
			int rotatePointX = 0;
			int rotatePointY = 0;
			if (imageRotation.equals(Rotation.CW)) {
				angle = 90;
				rotatePointX = rotatePointY = height / 2;
			} else if (imageRotation.equals(Rotation.FLIPPED)) {
				angle = 180;
				rotatePointX = width / 2;
				rotatePointY = height / 2;
			} else {
				angle = -90;
				rotatePointX = rotatePointY = width / 2;
			}
			if (imageRotation.equals(Rotation.CW) || imageRotation.equals(Rotation.ACW)) {
				int tmp = height;
				height = width;
				width = tmp;
			}
			AffineTransform at = AffineTransform.getRotateInstance(
					Math.toRadians(angle),
					rotatePointX, rotatePointY
			);
			AffineTransformOp aop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			result = aop.filter(image, null);
		
		}
		
		g.drawImage(result, 0, 0, width / 2, height / 2, null);
	}
}
