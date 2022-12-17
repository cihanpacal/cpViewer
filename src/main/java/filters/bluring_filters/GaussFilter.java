package filters.bluring_filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import filters.AbstractFilter;
import filters.IFilter;
import filters.KernelMatris;

public class GaussFilter extends AbstractFilter implements IFilter {
	
	private int[][] kernel=KernelMatris.GAUSS7x7;
	
	public GaussFilter(){
		//this(KernelMatris.GAUSS3x3);
		//this.kernel=KernelMatris.GAUSS3x3;
	}
	
	
	@Override
	public BufferedImage filtrele(BufferedImage bfKaynak) {
		return filtrele(bfKaynak,(colors)->{
			return new Color(Math.round(colors[0]/1003.0f),Math.round(colors[1]/1003.0f),Math.round(colors[2]/1003.0f));
		}, kernel);
	}

	
}
