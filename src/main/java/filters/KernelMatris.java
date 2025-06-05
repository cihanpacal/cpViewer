package filters;

import java.util.Arrays;

public class KernelMatris {
	public static final int[][] GAUSS3x3 = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };
	public static final int[][] GAUSS5x5 = { { 1, 4, 7, 4, 1 }, { 4, 16, 26, 16, 4 }, { 7, 26, 41, 26, 7 },
			{ 4, 16, 26, 16, 4 }, { 1, 4, 7, 4, 1 } };
	public static final int[][] GAUSS7x7 = { { 0, 0, 1, 2, 1, 0, 0 }, { 0, 3, 13, 22, 13, 3, 0 },
			{ 1, 13, 59, 97, 59, 13, 1 }, { 2, 22, 97, 159, 97, 22, 2 }, { 1, 13, 59, 97, 59, 13, 1 },
			{ 0, 3, 13, 22, 13, 3, 0 }, { 0, 0, 1, 2, 1, 0, 0 }, };
	public static final int[][] MEAN3x3 = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
	public static final int[][] MEAN5x5 = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
	public static final int[][] MEAN7x7 = { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, };
	
	

	
	public static final int[][] LAPLACIAN1= { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };
	public static final int[][] LAPLACIAN2 = { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } };
	public static final int[][] LAPLACIAN3 = {{ 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 }  };
	public static final int[][] LAPLACIAN4 = { {-1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 }  };
}
