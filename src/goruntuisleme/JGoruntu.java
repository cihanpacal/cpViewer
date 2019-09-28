package goruntuisleme;

import java.awt.image.BufferedImage;
import java.util.Stack;

import filtreler.IFiltre;

public class JGoruntu {
	
	private BufferedImage bf;
	private Stack<BufferedImage> filtreStackGeri;
	private Stack<BufferedImage> filtreStackIleri;
	public JGoruntu(BufferedImage bf) {
		this.filtreStackGeri=new Stack<BufferedImage>();
		this.filtreStackIleri=new Stack<BufferedImage>();
		this.bf=bf;
	}
	public void filtrele(IFiltre filtre) {
		BufferedImage tempBf=this.bf;
		filtreStackGeri.push(tempBf);
		filtreStackIleri.clear();
		this.bf=filtre.filtrele(this.bf);
		
	}
	
	public void ileri() {
		if(!filtreStackIleri.isEmpty()) {
		    BufferedImage tempBf=this.bf;
			filtreStackGeri.push(tempBf);
			this.bf=filtreStackIleri.pop();
		}
		
	}
	
	public void geri() {
		if(!filtreStackGeri.isEmpty()) {
			BufferedImage tempBf=this.bf;
			filtreStackIleri.push(tempBf);
			this.bf=filtreStackGeri.pop();
		}
	}
	public BufferedImage getBf() {
		return bf;
	}
	public Stack<BufferedImage> getFiltreStackGeri() {
		return filtreStackGeri;
	}
	
	
}
