package raytracer;

import java.util.Set;

import lighting.AmbientLight;
import lighting.Light;


public class Scene {
	
	public Set<SimpleObject> objects;
	
	public Set<Light> lights;
	
	public AmbientLight ambientLight;
	
	public Camera camera;

	public Scene(Set<SimpleObject> objects, Set<Light> lights, Camera camera) {
		super();
		this.objects = objects;
		this.lights = lights;
		this.ambientLight = new AmbientLight(new Color(1,1,1), 1);
		this.camera = camera;
	}

	public Color renderPixel(int x, int y) {
        Ray ray = new Ray(this.camera.getPosition(), 
        				  this.camera.getDirection(x, y));
        return renderRay(ray);
	}
	
	
	private Color renderRay(Ray ray) {
		return this.renderRay(ray, 1);
	}
	
	public Color renderRay(Ray ray, int recursionsLeft) {
		Color color = null;
		Intersection intersection = this.computeIntersection(ray);
		
		if (intersection == null || recursionsLeft == 0) {                          
			color = new Color(0,0,0);
		} else {
			color = intersection.getObject().getMaterial().renderRay(ray, intersection, this, recursionsLeft-1);
		}
		
		return color;
	}

	public Intersection computeIntersection(Ray ray) {
		
		Intersection intersection = null;
		Intersection tempIntersection;
		double distance = Double.MAX_VALUE;
		
		for(SimpleObject object : this.objects)	{
			tempIntersection = object.getIntersection(ray);

			if(tempIntersection != null && 
			   tempIntersection.getDistance() > 0 && 
			   tempIntersection.getDistance() < distance) {
				intersection = tempIntersection;
				distance = tempIntersection.getDistance();
			}
			
	    }
		
		return intersection;

	}
	

	public Set<SimpleObject> getObjects() {
		return objects;
	}

	public void setObjects(Set<SimpleObject> objects) {
		this.objects = objects;
	}

	public Set<Light> getLights() {
		return lights;
	}

	public void setLights(Set<Light> lights) {
		this.lights = lights;
	}


	public AmbientLight getAmbientLight() {
		return ambientLight;
	}


	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}

	
	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
}
