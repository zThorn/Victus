package com.TEAM_NAME.Fructus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AStarAlgorithm {
	
	public AStarAlgorithm()
	{}
	
	private Comparator<Node> nodeSorter = new Comparator<Node>()
	{
		public int compare(Node n0, Node n1)
		{
			if (n1.fCost < n0.fCost)
			{
				return +1;
			}
			if (n1.fCost > n0.fCost)
			{
				return -1;
			}
			return 0;
		}
	};
	
	
	public List<Node> findPath(Vector2i start, Vector2i goal, int[][] map)
	{
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while(openList.size() > 0)
		{
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.location.equals(goal))
			{
				List<Node> path = new ArrayList<Node>();
				while(current.parent != null)
				{
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++)
			{
				if (i == 4)
				{
					continue;
				}
				int x = current.location.getX();
				int y = current.location.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				int xf = x + xi;
				int yf = y + yi;
				int mapWidth = map.length - 1;
				int mapHeight = map[0].length - 1;
				
				if((xf > mapWidth) || (xf < 0))
				{
					continue;
				}
				if((yf > mapHeight) || (yf < 0))
				{
					continue;
				}
				
				int tile = map[x + xi][y + yi];
				if (tile == 0)
				{
					Vector2i a = new Vector2i(xf, yf);
					double gCost = current.gCost + getDistance(current.location, a);
					double hCost = getDistance(a, goal);
					Node node = new Node(a, current, gCost, hCost);
					if(vecInList(closedList, a) && gCost >= node.gCost)
					{
						continue;
					}
					if(!vecInList(openList, a) || gCost < node.gCost)
					{
						openList.add(node);
					}
					
				}	
			}
		}
		return null;
	}
	
	private boolean vecInList(List<Node> list, Vector2i vector)
	{
		for (Node n : list)
		{
			if (n.location.equals(vector))
			{
				return true;
			}
		}
		return false;
	}
	
	private double getDistance(Vector2i start, Vector2i goal)
	{
		double dx = start.getX() - goal.getX();
		double dy = start.getY() - goal.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
	
}
