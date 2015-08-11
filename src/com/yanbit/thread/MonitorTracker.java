package com.yanbit.thread;

import java.util.Map;

public class MonitorTracker {

	private final Map<String, Point> locations;

	public MonitorTracker(Map<String, Point> locations) {
		this.locations = locations;
	}

	public synchronized Map<String, Point> getLocations() {
		return deepCopy(locations);
	}

	public synchronized Point getLocations(String id) {
		Point loc = locations.get(id);
		return loc == null ? null : new Point(loc);
	}

	private Map<String, Point> deepCopy(Map<String, Point> locations) {
		return null;
	}

}

class Point {

	public Point() {
	}

	public Point(Point p) {
		this.id = p.id;
		this.x = p.x;
		this.y = p.y;
	}

	String id;
	int x;
	int y;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}