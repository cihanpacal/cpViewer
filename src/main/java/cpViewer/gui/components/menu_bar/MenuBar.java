package cpViewer.gui.components.menu_bar;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import cpViewer.context.AppContext;
import filters.KeskinlestirmeFiltre;
import filters.ZoomIn1Filtre;
import filters.ZoomIn2Filtre;
import filters.ZoomOutFiltre;
import filters.bluring_filters.GaussFilter;
import filters.bluring_filters.OrtFiltre;
import filters.color_filters.BlueChannelFilter;
import filters.color_filters.GrayScaleFilter;
import filters.color_filters.GreenChannelFilter;
import filters.color_filters.RedChannelFilter;
import filters.edge_dedection_filters.LaplacianFiltre;
import filters.edge_dedection_filters.MinMaxMutlakFarkFiltre;
import filters.edge_dedection_filters.PrewittDikeyFiltre;
import filters.edge_dedection_filters.PrewittFiltre;
import filters.edge_dedection_filters.PrewittYatayFiltre;
import filters.sorting_filters.MaxFiltre;
import filters.sorting_filters.MedianFiltre;
import filters.sorting_filters.MinFiltre;
import util.CustomFileChooser;
public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<JMenu> menuList;
	private CustomFileChooser fileChooser;
	private File file;
	private AppContext appContext;
	
	public MenuBar() {
		super();
		setFont(UIManager.getFont("MenuBar.font"));
		setBorder(null);
		setBorderPainted(false);
		setBackground(new Color(255, 255, 255));
		
		fileChooser=new CustomFileChooser();
		appContext=AppContext.getAppContext();
		
		JMenu menuFile = new JMenu("Dosya");
		menuFile.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		add(menuFile);
		
		JMenuItem menuItemOpenFile = new JMenuItem("A\u00E7");
		menuItemOpenFile.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		menuItemOpenFile.addActionListener((event)->{
			//JOptionPane.showMessageDialog(null,"open file");
			int value=fileChooser.showOpenDialog(null);
			if(value==JFileChooser.APPROVE_OPTION) {
				File directory=fileChooser.getCurrentDirectory();
				fileChooser.setCurrentDirectory(directory);
				file=fileChooser.getSelectedFile();
				try {
					appContext.getAppContext().setFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		menuFile.add(menuItemOpenFile);
		
		JMenuItem menuItemSaveFile = new JMenuItem("Kaydet");
		
		menuItemSaveFile.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		menuItemSaveFile.addActionListener((event)->{
			fileChooser.setDialogTitle("Kaydet");
			int value=fileChooser.showSaveDialog(null);
			if(value==fileChooser.APPROVE_OPTION) {
				String filename=fileChooser.getSelectedFile().toString();
				try {
					AppContext.getAppContext().save(filename);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		menuFile.add(menuItemSaveFile);
		
		//FilterMenu
		
		JMenu menuFilters = new JMenu("Filtreler");
		
		menuFilters.setFont(new Font("SansSerif", Font.PLAIN, 12));
		add(menuFilters);
		
		//Color Filter
		
		JMenu menuColorFilters = new JMenu("Renk Filtreleri");
		menuFilters.add(menuColorFilters);
		
		JMenuItem menuItemGrayScale = new JMenuItem("Gri Ton");
		menuItemGrayScale.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new GrayScaleFilter());
		});
		menuColorFilters.add(menuItemGrayScale);
		
		JMenuItem menuItemRed = new JMenuItem("Kýrmýzý Filtre");
		menuColorFilters.add(menuItemRed);
		
		menuItemRed.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new RedChannelFilter());
		});
		
		JMenuItem menuItemBlue = new JMenuItem("Mavi Filtre");
		menuColorFilters.add(menuItemBlue);
		
		menuItemBlue.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new BlueChannelFilter());
		});
		
		JMenuItem menuItemGreen = new JMenuItem("Yeþil Filtre");
		menuColorFilters.add(menuItemGreen);
		
		menuItemGreen.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new GreenChannelFilter());
		});
		
		//Edge Dedection Filter
		
		JMenu menuEdgeDedectionFilters = new JMenu("Kenar Filtreleri");
		menuFilters.add(menuEdgeDedectionFilters);
		
		JMenuItem menuItemLaplacian = new JMenuItem("Laplacian");
		menuEdgeDedectionFilters.add(menuItemLaplacian);
		
		menuItemLaplacian.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new LaplacianFiltre());
		});
		
		JMenuItem menuItemVerticalPrewitt = new JMenuItem("Prewitt Dikey");
		menuEdgeDedectionFilters.add(menuItemVerticalPrewitt);
		
		menuItemVerticalPrewitt.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new PrewittDikeyFiltre());
		});
		
		JMenuItem menuItemHorizontalPrewitt = new JMenuItem("Prewitt Yatay");
		menuEdgeDedectionFilters.add(menuItemHorizontalPrewitt);
		
		menuItemHorizontalPrewitt.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new PrewittYatayFiltre());
		});
		
		JMenuItem menuItemPrewitt = new JMenuItem("Prewitt");
		menuEdgeDedectionFilters.add(menuItemPrewitt);
		
		menuItemPrewitt.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new PrewittFiltre());
		});
		
		JMenuItem menuItemMinMax = new JMenuItem("Min Max");
		menuEdgeDedectionFilters.add(menuItemMinMax);
		
		menuItemMinMax.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new MinMaxMutlakFarkFiltre());
		});
		
		
		//Sorting Filter
		JMenu menuSortingFilters = new JMenu("Sýralama Filtreleri");
		menuFilters.add(menuSortingFilters);
		
		JMenuItem menuItemMax = new JMenuItem("Max");
		menuSortingFilters.add(menuItemMax);
		
		menuItemMax.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new MaxFiltre());
		});
		
		JMenuItem menuItemMin = new JMenuItem("Min");
		menuSortingFilters.add(menuItemMin);
		
		menuItemMin.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new MinFiltre());
		});
		
		JMenuItem menuItemMedian = new JMenuItem("Median");
		menuSortingFilters.add(menuItemMedian);
		
		menuItemMedian.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new MedianFiltre());
		});
		
		//Bluring Filters
		JMenu bluringFilters = new JMenu("Bulanýklaþtýrma Filtreleri");
		menuFilters.add(bluringFilters);
		
		JMenuItem menuItemAvarage = new JMenuItem("Ortalama");
		bluringFilters.add(menuItemAvarage);
		
		menuItemAvarage.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new OrtFiltre());
		});
		
		JMenuItem menuItemGauss = new JMenuItem("Gauss");
		bluringFilters.add(menuItemGauss);
		
		menuItemGauss.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new GaussFilter());
		});
		
		//Other Filters
		
		JMenuItem menuItemZoomIn1= new JMenuItem("Yakýnlaþtýrma Pixel");
		menuFilters.add(menuItemZoomIn1);
		
		menuItemZoomIn1.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new ZoomIn1Filtre());
		});
		
		JMenuItem menuItemZoomIn2 = new JMenuItem("Yakýnlaþtýrma Anti Aliasing");
		menuFilters.add(menuItemZoomIn2);
		
		menuItemZoomIn2.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new ZoomIn2Filtre());
		});
		
		JMenuItem menuItemZoomOut = new JMenuItem("Uzaklaþtýrma");
		menuFilters.add(menuItemZoomOut);
		
		menuItemZoomOut.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new ZoomOutFiltre());
		});
		
		JMenuItem menuItemSharpening  = new JMenuItem("Keskinleþtirme");
		menuFilters.add(menuItemSharpening);
		
		menuItemSharpening.addActionListener((event)->{
			AppContext.getAppContext().setFilter(new KeskinlestirmeFiltre());
		});
	
	}

	
	

}
