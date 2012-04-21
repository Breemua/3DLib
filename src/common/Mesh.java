package common;

import io.OBJObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Mesh {

	private Collection<Triangle> triangles;

	/**
	 * @param triangles
	 */
	public Mesh(Collection<Triangle> triangles) {
		super();
		this.triangles = triangles;
	}
	
	public Mesh() {
		super();
		this.triangles = new ArrayList<Triangle>();
	}
	
	public void addTriangle(Triangle t) {
		triangles.add(t);
	}
	
	public OBJObject getOBJObject(String name) {
		OBJObject obj = new OBJObject(name);
		HashMap<Point, Integer> pointsToInt = new HashMap<Point, Integer>();
		
		int i = 1;
		for(Triangle triangle : this.triangles) {
			pointsToInt.put(triangle.getA(), i);
			pointsToInt.put(triangle.getB(), i);
			pointsToInt.put(triangle.getC(), i);
			i++;
		}
		
		for(Point point : pointsToInt.keySet()) {
			obj.addVertex(point.getX(), point.getY(), point.getZ());
		}
		
		int[] face = {0,0,0};
		for(Triangle triangle : this.triangles) {
			face[0] = pointsToInt.get(triangle.getA());
			face[1] = pointsToInt.get(triangle.getB());
			face[2] = pointsToInt.get(triangle.getC());
			obj.addFace(face);
		}
		
		return obj;
	}
	
}
