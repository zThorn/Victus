package com.TEAM_NAME.Fructus;

public class Node {
	
	public Vector2i location;
	public Node parent;
	public double fCost, gCost, hCost;
	
	public Node(Vector2i location, Node parent, double gCost, double hCost)
	{
		this.location = location;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = gCost + hCost;
	}
}
