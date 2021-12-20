package cpViewer.test;

import java.awt.EventQueue;

import cpViewer.gui.components.MainFrame;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
				try {
					MainFrame mainFrame=new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
		});

	}

}
