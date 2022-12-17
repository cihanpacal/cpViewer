package filters.bluring_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import filters.AbstractFilter;
import filters.IFilter;
import filters.KernelMatris;

public class MeanFilter extends AbstractFilter implements IFilter {

	
	int filtre=IFilter.FILTRE5;
	int boyut=5;
	int[][] kernel=KernelMatris.MEAN7x7;
	
	public MeanFilter() {
	}
	
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		return filtrele(bfKaynak,(colors)->{
			return new Color(
					Math.round(colors[0]/(kernel.length*kernel.length)),
				Math.round(colors[1]/(kernel.length*kernel.length)),
				Math.round(colors[2]/(kernel.length*kernel.length)));
		}, kernel);
		
	}

	

}
