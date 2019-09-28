package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import filtreler.*;
import goruntuisleme.JGoruntu;
import gui.koordinatsistemi.KoordinatSistemi;
import gui.koordinatsistemi.Merkez;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class Gosterici extends JFrame {
	private JPanel pnlAna;
	private JPanel pnlAracCubuk;
	private JPanel pnlAltCubuk;
	private JMenu mnDosya;
	private JMenuItem mnItem1Ac;
	private JMenuBar menuCubuk;
	private JPanel pnlGosterim;
	private CizimPaneli pnlCizim;
	private BufferedImage bf;
	private JGoruntu jg;
	private JScrollPane scrollPane;
	private JMenu mnFiltreler;
	private JMenuItem mn�tmGauss;
	private JMenuItem mn�tmGriTon;
	private JMenuItem mn�tmKrmz;
	private JMenuItem mn�tmMavi;
	private JMenuItem mn�tmYeil;
	private JMenuItem mn�tmLaplacian;
	private JMenuItem mn�tmMax;
	private JMenu mnRenkler;
	private JMenuItem mn�tmMedian;
	private JMenuItem mn�tmMin;
	private JMenuItem mn�tmMutlakFark;
	private JMenuItem mn�tmOrtalama;
	private JMenuItem mn�tmPrewittDikey;
	private JMenuItem mn�tmPrewittYatay;
	private JMenuItem mn�tmPrewitt;
	private JMenuItem mn�tmYaknlatr;
	private JMenuItem mn�tmYaknlatr_1;
	private JMenuItem mn�tmUzaklatr;
	private JButton btnIleri;
	private JMenuItem mn�tmKeskinletir;
	private JButton button;
	private JButton button_1;
	private JMenu mnKenarFiltreleri;
	private JMenu mnSralamaFiltreleri;
	private JMenu mnBulankaltrmaFiltreleri;
	private JMenuItem mn�tmKaydet;
	private File file;
	private JButton btnGeri;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gosterici frame = new Gosterici();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gosterici() {
		ilkle();
	}

	public void ilkle() {
		this.setIconImage(
				Toolkit.getDefaultToolkit().getImage("resimler\\images.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		olusturPnlAna();	
	}

	public void olusturPnlAna() {
		pnlAna = new JPanel();
		pnlAna.setBackground(new Color(192, 192, 192));
		pnlAna.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setContentPane(pnlAna);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		olusturPnlGosterim();
		olusturMenuCubuk();
		olusturPnlAracCubuk();
		olusturPnlAltCubuk();
	}

	public void olusturMenuCubuk() {

		menuCubuk = new JMenuBar();
		menuCubuk.setFont(UIManager.getFont("MenuBar.font"));
		menuCubuk.setBorder(null);
		menuCubuk.setBorderPainted(false);
		menuCubuk.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuCubuk);
		mnDosya = new JMenu("Dosya");
		mnDosya.setFont(new Font("SansSerif", Font.PLAIN, 12));
		menuCubuk.add(mnDosya);

		mnItem1Ac = new JMenuItem("A\u00E7");
		mnItem1Ac.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnItem1Ac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser=new JFileChooser("C:\\Users\\Cihan\\Pictures\\Resimler");
				fileChooser.setDialogTitle("Dosya A�");
				fileChooser.setFileFilter(new DosyaFiltresi());
				int deger=fileChooser.showOpenDialog(null);
				if(deger==JFileChooser.APPROVE_OPTION) {
					file=fileChooser.getSelectedFile();
					try {
						jg=new JGoruntu(ImageIO.read(file));
						ciz();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		mnDosya.add(mnItem1Ac);
		
		mn�tmKaydet = new JMenuItem("Kaydet");
		mn�tmKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileChooser=new JFileChooser("C:\\Users\\Cihan\\Pictures\\Resimler");
				fileChooser.setDialogTitle("Kaydet");
				fileChooser.setFileFilter(new DosyaFiltresi());
				int deger=fileChooser.showSaveDialog(null);
				if(deger==fileChooser.APPROVE_OPTION) {
					String filename=fileChooser.getSelectedFile().toString();
					try {
						if(filename.endsWith(".png")) {
							ImageIO.write(jg.getBf(),"png",new File(filename));
						}else if(filename.endsWith(".jpg")) {
							ImageIO.write(jg.getBf(),"jpg",new File(filename));
						}else if(filename.endsWith(".gif")) {
							ImageIO.write(jg.getBf(),"gif",new File(filename));
						}else {
							filename+=".jpg";
							ImageIO.write(jg.getBf(),"jpg",new File(filename));
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mnDosya.add(mn�tmKaydet);
		
		mnFiltreler = new JMenu("Filtreler");
		mnFiltreler.setFont(new Font("SansSerif", Font.PLAIN, 12));
		menuCubuk.add(mnFiltreler);
		
		mnRenkler = new JMenu("Renk Filtreleri");
		mnFiltreler.add(mnRenkler);
		
		mn�tmGriTon = new JMenuItem("Gri Ton");
		mnRenkler.add(mn�tmGriTon);
		
		mn�tmKrmz = new JMenuItem("K\u0131rm\u0131z\u0131");
		mnRenkler.add(mn�tmKrmz);
		
		mn�tmMavi = new JMenuItem("Mavi");
		mnRenkler.add(mn�tmMavi);
		
		mn�tmYeil = new JMenuItem("Ye\u015Fil");
		mnRenkler.add(mn�tmYeil);
		mn�tmYeil.addActionListener(new FiltrelerListener(new YesilFiltre()));
		mn�tmMavi.addActionListener(new FiltrelerListener(new MaviFiltre()));
		mn�tmKrmz.addActionListener(new FiltrelerListener(new KirmiziFiltre()));
		mn�tmGriTon.addActionListener(new FiltrelerListener(new GriTonFiltre()));
		
		mnKenarFiltreleri = new JMenu("Kenar Filtreleri");
		mnFiltreler.add(mnKenarFiltreleri);
		
		mn�tmLaplacian = new JMenuItem("Laplacian");
		mnKenarFiltreleri.add(mn�tmLaplacian);
		
		mn�tmPrewittDikey = new JMenuItem("Prewitt Dikey");
		mnKenarFiltreleri.add(mn�tmPrewittDikey);
		
		mn�tmPrewittYatay = new JMenuItem("Prewitt Yatay");
		mnKenarFiltreleri.add(mn�tmPrewittYatay);
		
		mn�tmPrewitt = new JMenuItem("Prewitt ");
		mnKenarFiltreleri.add(mn�tmPrewitt);
		
		mn�tmMutlakFark = new JMenuItem("Mutlak Fark");
		mnKenarFiltreleri.add(mn�tmMutlakFark);
		mn�tmMutlakFark.addActionListener(new FiltrelerListener(new MinMaxMutlakFarkFiltre()));
		mn�tmPrewitt.addActionListener(new FiltrelerListener(new PrewittFiltre()));
		mn�tmPrewittYatay.addActionListener(new FiltrelerListener(new PrewittYatayFiltre()));
		mn�tmPrewittDikey.addActionListener(new FiltrelerListener(new PrewittDikeyFiltre()));
		mn�tmLaplacian.addActionListener(new FiltrelerListener(new LaplacianFiltre()));
		
		mnSralamaFiltreleri = new JMenu("S\u0131ralama Filtreleri");
		mnFiltreler.add(mnSralamaFiltreleri);
		
		mn�tmMax = new JMenuItem("Max");
		mnSralamaFiltreleri.add(mn�tmMax);
		
		mn�tmMedian = new JMenuItem("Median");
		mnSralamaFiltreleri.add(mn�tmMedian);
		
		
		mn�tmMin = new JMenuItem("Min");
		mnSralamaFiltreleri.add(mn�tmMin);
		mn�tmMin.addActionListener(new FiltrelerListener(new MinFiltre()));
		mn�tmMedian.addActionListener(new FiltrelerListener(new MedianFiltre()));
		mn�tmMax.addActionListener(new FiltrelerListener(new MaxFiltre()));
		
		mnBulankaltrmaFiltreleri = new JMenu("Bulan\u0131k\u015Falt\u0131rma Filtreleri");
		mnFiltreler.add(mnBulankaltrmaFiltreleri);
		
		
		mn�tmOrtalama = new JMenuItem("Ortalama");
		mnBulankaltrmaFiltreleri.add(mn�tmOrtalama);
		
		mn�tmGauss = new JMenuItem("Gauss");
		mnBulankaltrmaFiltreleri.add(mn�tmGauss);
		mn�tmGauss.addActionListener(new FiltrelerListener(new GaussFiltre()));
		mn�tmOrtalama.addActionListener(new FiltrelerListener(new OrtFiltre()));
		
		mn�tmYaknlatr = new JMenuItem("Yak\u0131nla\u015Ft\u0131r 1");
		mn�tmYaknlatr.addActionListener(new FiltrelerListener(new ZoomIn1Filtre()));
		mnFiltreler.add(mn�tmYaknlatr);
		
		mn�tmYaknlatr_1 = new JMenuItem("Yak\u0131nla\u015Ft\u0131r 2");
		mn�tmYaknlatr_1.addActionListener(new FiltrelerListener(new ZoomIn2Filtre()));
		mnFiltreler.add(mn�tmYaknlatr_1);
		
		mn�tmUzaklatr = new JMenuItem("Uzakla\u015Ft\u0131r");
		mn�tmUzaklatr.addActionListener(new FiltrelerListener(new ZoomOutFiltre()));
		mnFiltreler.add(mn�tmUzaklatr);
		
		mn�tmKeskinletir = new JMenuItem("Keskinle\u015Ftir");
		mn�tmKeskinletir.addActionListener(new FiltrelerListener(new KeskinlestirmeFiltre()));
		mnFiltreler.add(mn�tmKeskinletir);
	}

	public void olusturPnlAracCubuk() {
		pnlAracCubuk = new JPanel();
		pnlAracCubuk.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlAracCubuk.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlAracCubuk.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		pnlAracCubuk.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pnlAracCubuk.setPreferredSize(new Dimension(10, 40));
		pnlAracCubuk.setBackground(new Color(220, 220, 220));
		getContentPane().add(pnlAracCubuk, BorderLayout.NORTH);
		
		button = new JButton("+");
		button.setFont(new Font("Tahoma", Font.BOLD, 15));
		button.setPreferredSize(new Dimension(50, 30));
		button.addActionListener(new FiltrelerListener(new ZoomIn2Filtre()));
		
		btnIleri = new JButton("");
		btnIleri.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnIleri.setPreferredSize(new Dimension(35, 30));
		btnIleri.setIcon(new ImageIcon(Gosterici.class.getResource("/resimler/redo.png")));
		btnIleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jg.getBf()!=null) {
					jg.ileri();
					ciz();
				}
			}
		});
		
		btnGeri = new JButton("");
		btnGeri.setPreferredSize(new Dimension(35, 30));
		btnGeri.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGeri.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnGeri.setBackground(UIManager.getColor("Button.background"));
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jg.getBf()!=null) {
					jg.geri();
					ciz();
				}
			}
		});
		pnlAracCubuk.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		btnGeri.setIcon(new ImageIcon(Gosterici.class.getResource("/resimler/undo.png")));
		pnlAracCubuk.add(btnGeri);
		pnlAracCubuk.add(btnIleri);
		
		button_1 = new JButton("-");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_1.setPreferredSize(new Dimension(50, 30));
		button_1.addActionListener(new FiltrelerListener(new ZoomOutFiltre()));
		pnlAracCubuk.add(button_1);
		pnlAracCubuk.add(button);
	}
	
	public void olusturPnlGosterim() {
		    pnlGosterim=new JPanel();
		    pnlGosterim.setAlignmentY(Component.TOP_ALIGNMENT);
		    pnlGosterim.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnlGosterim.setBorder(null);
			pnlGosterim.setBackground(Color.BLACK);
			pnlGosterim.setLayout(new BorderLayout(0, 0));
			pnlAna.add(pnlGosterim, BorderLayout.CENTER);
			olusturPnlCizim();
	}

	public void olusturPnlCizim() {

        pnlCizim=new CizimPaneli();
		pnlCizim.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		pnlCizim.setBackground(Color.BLACK);
		pnlCizim.setLayout(new BorderLayout(0, 0));
		scrollPane=new JScrollPane();
		scrollPane.setViewportView(pnlCizim);
		pnlGosterim.add(scrollPane);
		
		

		
	}
	
	public void olusturPnlAltCubuk() {
		pnlAltCubuk = new JPanel();
		pnlAna.add(pnlAltCubuk, BorderLayout.SOUTH);
		pnlAltCubuk.setBackground(new Color(220, 220, 220));
		pnlAltCubuk.setPreferredSize(new Dimension(10, 22));
		
		
	}
	
	public class FiltrelerListener implements ActionListener {
		private IFiltre filtre;
		public FiltrelerListener(IFiltre filtre) {
			this.filtre=filtre;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(jg.getBf()!=null) {
				jg.filtrele(this.filtre);
				ciz();
			}
		}

	}
	public void ciz() {
		pnlCizim.setPreferredSize(new Dimension(jg.getBf().getWidth(),jg.getBf().getHeight()));
		pnlCizim.setBf(jg.getBf());
		pnlCizim.repaint();
		pnlCizim.revalidate();
		
	}
	
	
	
	
	class CizimPaneli extends JPanel{
		private BufferedImage bf;
		private Point point;
		private KoordinatSistemi koordinatSistemi;
		public CizimPaneli(BufferedImage bf) {
			this(bf,new Merkez());
		}
		public CizimPaneli(BufferedImage bf, KoordinatSistemi koordinatSistemi) {
			super();
			this.bf = bf;
			this.setKoordinatSistemi(koordinatSistemi);
			if(bf!=null) {
				point.x=bf.getMinX();
				point.y=bf.getMinY();
			}
		}
		public CizimPaneli() {
			this(null,new Merkez());
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if(bf!=null) {
				point=koordinatSistemi.ortala(bf.getWidth(),bf.getHeight());
				point=koordinatSistemi.donustur(point,this);
				g.drawImage(bf,(int)point.getX(),(int)point.getY(),null);
			}
			
			
			
			
		}
		
		public BufferedImage getBf() {
			return bf;
		}

		public void setBf(BufferedImage bf) {
			
			this.bf = bf;
			
		}

		public KoordinatSistemi getKoordinatSistemi() {
			return koordinatSistemi;
		}

		public void setKoordinatSistemi(KoordinatSistemi koordinatSistemi) {
			if(koordinatSistemi==null)
				this.koordinatSistemi=new Merkez();
			else
				this.koordinatSistemi = koordinatSistemi;
		}

		
		
	}
	
}
