package hw6;

import java.io.*;
import java.util.ArrayList;

public class GridUtilities {
	public static char[][] fromStringArray(String[] in) {
		int size = in.length;
		char[][] result = new char[size][];
		for(int i = 0; i < size; i++) {
			String row = in[i];
			if (row.length() != size)
				throw new RuntimeException(String.format("Row %d and col 0 have different sizes", i));
			result[i] = row.toCharArray();
		}
		return result;
	}
	
	
	public static char[][] fromFile(String filename) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String row = in.readLine();
			ArrayList<String> temp = new ArrayList<String>(row.length());
			while (row != null) {
				temp.add(row);
				row = in.readLine();
			}
			String[] table = temp.toArray(new String[0]);
			return fromStringArray(table);
		}
	}
	
	public static char[][] rotateClockwise(char[][] in) {
		int size = in.length;
		char[][] out = new char[size][size];
		for(int row = 0; row < size; row++)
			for(int col = 0; col < size; col++)
				out[row][col] = in[size-1-col][row];
		return out;
	}
	
	public static String toString(char[][] grid) {
		StringBuilder sb = new StringBuilder(grid.length * (grid.length+5));
		for(char[] row : grid) {
			sb.append(new String(row));
			sb.append('\n');
		}
		return sb.toString();
	}
}
