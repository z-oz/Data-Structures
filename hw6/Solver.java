package hw6;

import java.util.Iterator;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BreadthFirstPaths;

public class Solver {
	public static String solve(char[][] grid) {
		// TODO
		/*
		 * 1. Construct a graph using grid
		 * 
		 * 2. Use BFS to find shortest path from start to finish
		 * 3. Return the sequence of moves to get from start to finish
		 */
		
		int s = -1;
		int f = -1;
		char[][] direction = new char[grid.length * grid.length][grid.length * grid.length];
		Graph graph = new Graph(grid.length * grid.length);
		
		
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid.length; row++) {
				if (col - 1 >= 0 && grid[row][col] != '*' && grid[row][col - 1] != '*')
				{
					graph.addEdge(grid.length * (row) + col, grid.length * (row) + col - 1);
					direction[grid.length * (row) + col][grid.length * (row) + col - 1] = 'L'; 
				}
				
				
				
				
				if (col + 1 < grid.length && grid[row][col] != '*' && grid[row][col + 1] != '*') 
				{
					graph.addEdge(grid.length * (row) + col, grid.length * (row) + col + 1);
					direction[grid.length * (row) + col][grid.length * (row) + col + 1] = 'R'; 
				}
				
				
				
				if (row - 1 >= 0 && grid[row][col] != '*' && grid[row - 1][col] != '*') 
				{
					graph.addEdge(grid.length * (row) + col, grid.length * (row - 1) + col);
					direction[grid.length * (row) + col][grid.length * (row - 1) + col] = 'U'; 
				}
				
				
				
				
				if (row + 1 < grid.length && grid[row][col] != '*' && grid[row + 1][col] != '*') 
				{
					graph.addEdge(grid.length * (row) + col, grid.length * (row + 1) + col);
					direction[grid.length * (row) + col][grid.length * (row + 1) + col] = 'D'; 
				}
				
				
				
				if('s' == grid[row][col]) 
				{
					s = grid.length * (row) + col;
				}
				
				
				
				
				if('f' == grid[row][col]) 
				{
					f = grid.length * (row) + col;
				}
			}
		}
		
		
		
		BreadthFirstPaths BFS = new BreadthFirstPaths(graph, s);
		String result = "";
		
	
		if (BFS.pathTo(f) != null) {
			int increment = 0;
			Iterator iterator = BFS.pathTo(f).iterator();
			
			
			while(iterator.hasNext()==true) {
				increment = increment + 1;
				iterator.next();
			}
			
			
			
			iterator = BFS.pathTo(f).iterator();
			int[] path = new int[increment];
			int index = 0;
			
			
			while(iterator.hasNext()==true) path[index++] = (int) iterator.next();
			for(int i = 0; i < path.length - 1; i++) result = result + direction[path[i]][path[i + 1]];
		}
		
		else return null;
		return result;
	}
}
