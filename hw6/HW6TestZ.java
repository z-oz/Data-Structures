package hw6;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW6TestZ {
	
	private void checkSol(char[][] grid, String path, int distance) {
		int size = grid.length;
		int row = -1;
		int col = -1;
		
		// Start at s;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if (grid[i][j] == 's') {
					row = i;
					col = j;
				}
			}
		}
		
		// Move according to path
		for(char c : path.toCharArray()) {
			switch(c) {
			case 'U':
				row -= 1;
				break;
			case 'D':
				row += 1;
				break;
			case 'R':
				col += 1;
				break;
			case 'L':
				col -= 1;
				break;
			default:
				fail("Illegal character in solution: " + c);
			}
			// Make sure you haven't moved outside the grid.
			assertTrue(row >= 0 && row < size && col >= 0 && col < size);
			// Make sure you haven't moved into a '*'
			assertTrue(grid[row][col] != '*');
		}
		// Make sure you end at 'f'
		assertTrue(grid[row][col] == 'f');
		
		// Make sure it is not longer than shortest distance.
		assertTrue(path.length() <= distance);
	}

	@Test
	public void toyTest() {
		String[] data = 
			{
					"s         ",
					"          ",
					"  *       ",
					"  *  *****",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *      f"
			};
		char[][] grid;
		/* Solution to this grid is hardcoded */
		grid = GridUtilitiesz.fromStringArray(data);
		String solution = Solver.solve(grid);
		
		checkSol(grid, solution, 18);
		
		/* Hardcoded solution does not solve this grid */
		grid = GridUtilitiesz.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
	}
}
