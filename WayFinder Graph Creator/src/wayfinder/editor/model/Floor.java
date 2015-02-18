package wayfinder.editor.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static final List<Rotation> rotations = Arrays.asList(new Rotation[] {
		Rotation.NORMAL, Rotation.CW, Rotation.FLIPPED, Rotation.ACW
	});

	private BufferedImage image;
	private double imageScale;
	private Rotation imageRotation;

	private List<Node> nodes;
	private Anchor anchor;
	private String floorName;

	public Floor(BufferedImage image, double imageScale,
			Anchor anchor, String floorName) {
		this.image = image;
		this.imageScale = imageScale;
		this.imageRotation = Rotation.NORMAL;
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
		int rotatePoint = image.getHeight() / 2;
		AffineTransform at = AffineTransform.getRotateInstance(
				Math.toRadians(90), rotatePoint, rotatePoint);
		AffineTransformOp aop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		image = aop.filter(image, null);
		imageRotation = rotations.get((rotations.indexOf(imageRotation) + 1) % 4);
	}

	public void rotateImageAntilockwise() {
		int rotatePoint = image.getWidth() / 2;
		AffineTransform at = AffineTransform.getRotateInstance(
				Math.toRadians(-90), rotatePoint, rotatePoint);
		AffineTransformOp aop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		image = aop.filter(image, null);
		imageRotation = rotations.get((rotations.indexOf(imageRotation) + 3) % 4);
	}

	public void flipImage() {
		AffineTransform at = AffineTransform.getRotateInstance(
				Math.toRadians(180), image.getWidth() / 2, image.getHeight() / 2);
		AffineTransformOp aop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		image = aop.filter(image, null);
		imageRotation = rotations.get((rotations.indexOf(imageRotation) + 2) % 4);
	}

	@Override
	public void draw(Graphics g, Vector offset, double scale) {
		g.drawImage(image, (int) (offset.x*scale),	(int) (offset.y*scale), 
				(int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
	}
}
