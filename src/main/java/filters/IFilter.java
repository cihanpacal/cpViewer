package filters;

import java.awt.image.BufferedImage;

public interface IFilter {

	int FILTRE3=3;
	int FILTRE5=5;
	int FILTRE7=7;
	int FILTRE9=9;
	int LAPLACIAN_EKSI_4=0;
	int LAPLACIAN_ARTI_4=1;
	int LAPLACIAN_EKSI_8=2;
	int LAPLACIAN_ARTI_8=3;
	
	BufferedImage filtrele(BufferedImage sourceBufferedImage);
}
