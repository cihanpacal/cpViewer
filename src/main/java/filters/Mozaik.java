package filters;

import util.Mat;

public class Mozaik implements Comparable<Mozaik> {

	private Integer[][] matrix;
	private int mean;
	
	
	
	public Integer[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}
	public int getMean() {
		return mean;
	}
	public void setMean(int mean) {
		this.mean = mean;
	}
	@Override
	public int compareTo(Mozaik o) {
		return this.mean>o.mean ? 1:-1;
	}
	
	
	
}
