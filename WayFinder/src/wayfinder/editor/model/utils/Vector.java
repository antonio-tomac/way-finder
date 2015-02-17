
package wayfinder.editor.model.utils;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
public class Vector {

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector plus(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }
    
    public Vector minus(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }
    
    public Vector negative() {
        return new Vector(-x, -y);
    }
    
    public Vector multiply(double factor) {
        return new Vector(x*factor, y*factor);
    }
    
    public double product(Vector v) {
        return x*v.x + y*v.y;
    }
    
    public double crossProduct(Vector v) {
        return x*v.y - y*v.y;
    }
    
    public double length() {
        return Math.sqrt(x*x + y*y);
    }
    
    public double angle() {
        return Math.atan2(y, x);
    }
    
    public double angle(double ratio) {
        return Math.atan2(y, x*ratio);
    }
    
    public Vector abs() {
        return new Vector(Math.abs(x), Math.abs(y));
    }
     
    public Vector rotate(double angle) {
        double r = length();
        double a = Math.atan2(y, x);
        return new Vector(r*Math.cos(a+angle), r*Math.sin(a+angle));
    }
    
    public Vector rotateItAround(double angle, Vector around) {
        return this.minus(around).rotate(angle).plus(around);
    }
    
    public Vector mirrorOn(Vector v) {
        return v.minus(this.minus(v));
    }

    public Vector mirrorOn(Vector p1, Vector p2) {
        Vector n = p2.minus(p1).multiply(1/p2.minus(p1).length());
        Vector v = p2.minus(this);
        return p2.plus(v).minus(n.multiply(2*v.product(n)));
    }
    
    public static double mirrorAngle(double angle, Vector p1, Vector p2) {
        Vector _p2 = p2.minus(p1);
        Vector _p1 = new Vector(0, 0);
        Vector v = new Vector(Math.cos(angle), Math.sin(angle));
        v = v.mirrorOn(_p1, _p2);
        return Math.atan2(v.y, v.x);
    }
    
    @Override
    public String toString() {
        return "("+(Math.round(x*1000)/1000.)+", "+(Math.round(y*1000)/1000.)+")";
	}
	
    public void translate(Vector by) {
        Vector v = this.plus(by);
        x = v.x;
        y = v.y;
    }

    public void mirror(Vector t) {
        Vector v = this.mirrorOn(t);
        x = v.x;
        y = v.y;
    }

    public void mirror(Vector t1, Vector t2) {
        Vector v = this.mirrorOn(t1, t2);
        x = v.x;
        y = v.y;
    }

    public void rotateAround(double angle, Vector around) {
        Vector v = this.rotateItAround(angle, around);
        x = v.x;
        y = v.y;
    }

    public Vector scaleAround(double factor, Vector center) {
        return this.minus(center).multiply(factor).plus(center);
    }

    public void scale(double factor, Vector center) {
        Vector v = this.scaleAround(factor, center);
        x = v.x;
        y = v.y;
    }

    public boolean isOn(Vector v, double scale) {
        return Math.abs(this.minus(v).length())*scale < 12;
    }
	
    public static boolean doBoundingBoxesIntersect(Vector boxA1, Vector boxA2, 
            Vector boxB1, Vector boxB2) {
        Vector a0 = new Vector(Math.min(boxA1.x, boxA2.x), Math.min(boxA1.y, boxA2.y));
        Vector a1 = new Vector(Math.max(boxA1.x, boxA2.x), Math.max(boxA1.y, boxA2.y));
        Vector b0 = new Vector(Math.min(boxB1.x, boxB2.x), Math.min(boxB1.y, boxB2.y));
        Vector b1 = new Vector(Math.max(boxB1.x, boxB2.x), Math.max(boxB1.y, boxB2.y));
        
        return a0.x <= b1.x && a1.x >= b0.x && a0.y <= b1.y && a1.y >= b0.y;
    }	
}
