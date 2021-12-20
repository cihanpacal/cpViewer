package util;

import java.awt.Color;
import java.nio.file.CopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Mat {
	
	public static int yuvarla255(int i) {
		if((i/255)>0) {
			return 255;
		}else if(i<0) {
			return 0;
		}else {
			return i;
		}
		
	}
	
	public static void renkSirala(Color[] c) {
		ComperatorColorRGBOrtalama comperator=new Mat().new ComperatorColorRGBOrtalama();
		Arrays.sort(c,comperator);
	}
	
	
	
	public static int rgbOrtalama(Color c) {
		float toplam=c.getRed()+c.getGreen()+c.getBlue();
		int ort=Math.round(toplam/3);
		return ort;
	}
	
	class ComperatorColorRGBOrtalama implements Comparator<Color>{

		@Override
		public int compare(Color o1, Color o2) {
			return (rgbOrtalama(o1) > rgbOrtalama(o2) ? -1 : (rgbOrtalama(o1) == rgbOrtalama(o2) ? 0 : 1));
		}
		
	}

}
