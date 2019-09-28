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
	private JMenuItem mnýtmGauss;
	private JMenuItem mnýtmGriTon;
	private JMenuItem mnýtmKrmz;
	private JMenuItem mnýtmMavi;
	private JMenuItem mnýtmYeil;
	private JMenuItem mnýtmLaplacian;
	private JMenuItem mnýtmMax;
	private JMenu mnRenkler;
	private JMenuItem mnýtmMedian;
	private JMenuItem mnýtmMin;
	private JMenuItem mnýtmMutlakFark;
	private JMenuItem mnýtmOrtalama;
	private JMenuItem mnýtmPrewittDikey;
	private JMenuItem mnýtmPrewittYatay;
	private JMenuItem mnýtmPrewitt;
	private JMenuItem mnýtmYaknlatr;
	private JMenuItem mnýtmYaknlatr_1;
	private JMenuItem mnýtmUzaklatr;
	private JButton btnIleri;
	private JMenuItem mnýtmKeskinletir;
	private JButton button;
	private JButton button_1;
	private JMenu mnKenarFiltreleri;
	private JMenu mnSralamaFiltreleri;
	private JMenu mnBulankaltrmaFiltreleri;
	private JMenuItem mnýtmKaydet;
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
				fileChooser.setDialogTitle("Dosya Aç");
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
		
		mnýtmKaydet = new JMenuItem("Kaydet");
		mnýtmKaydet.addActionListener(new ActionListener() {
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
		mnDosya.add(mnýtmKaydet);
		
		mnFiltreler = new JMenu("Filtreler");
		mnFiltreler.setFont(new Font("SansSerif", Font.PLAIN, 12));
		menuCubuk.add(mnFiltreler);
		
		mnRenkler = new JMenu("Renk Filtreleri");
		mnFiltreler.add(mnRenkler);
		
		mnýtmGriTon = new JMenuItem("Gri Ton");
		mnRenkler.add(mnýtmGriTon);
		
		mnýtmKrmz = new JMenuItem("K\u0131rm\u0131z\u0131");
		mnRenkler.add(mnýtmKrmz);
		
		mnýtmMavi = new JMenuItem("Mavi");
		mnRenkler.add(mnýtmMavi);
		
		mnýtmYeil = new JMenuItem("Ye\u015Fil");
		mnRenkler.add(mnýtmYeil);
		mnýtmYeil.addActionListener(new FiltrelerListener(new YesilFiltre()));
		mnýtmMavi.addActionListener(new FiltrelerListener(new MaviFiltre()));
		mnýtmKrmz.addActionListener(new FiltrelerListener(new KirmiziFiltre()));
		mnýtmGriTon.addActionListener(new FiltrelerListener(new GriTonFiltre()));
		
		mnKenarFiltreleri = new JMenu("Kenar Filtreleri");
		mnFiltreler.add(mnKenarFiltreleri);
		
		mnýtmLaplacian = new JMenuItem("Laplacian");
		mnKenarFiltreleri.add(mnýtmLaplacian);
		
		mnýtmPrewittDikey = new JMenuItem("Prewitt Dikey");
		mnKenarFiltreleri.add(mnýtmPrewittDikey);
		
		mnýtmPrewittYatay = new JMenuItem("Prewitt Yatay");
		mnKenarFiltreleri.add(mnýtmPrewittYatay);
		
		mnýtmPrewitt = new JMenuItem("Prewitt ");
		mnKenarFiltreleri.add(mnýtmPrewitt);
		
		mnýtmMutlakFark = new JMenuItem("Mutlak Fark");
		mnKenarFiltreleri.add(mnýtmMutlakFark);
		mnýtmMutlakFark.addActionListener(new FiltrelerListener(new MinMaxMutlakFarkFiltre()));
		mnýtmPrewitt.addActionListener(new FiltrelerListener(new PrewittFiltre()));
		mnýtmPrewittYatay.addActionListener(new FiltrelerListener(new PrewittYatayFiltre()));
		mnýtmPrewittDikey.addActionListener(new FiltrelerListener(new PrewittDikeyFiltre()));
		mnýtmLaplacian.addActionListener(new FiltrelerListener(new LaplacianFiltre()));
		
		mnSralamaFiltreleri = new JMenu("S\u0131ralama Filtreleri");
		mnFiltreler.add(mnSralamaFiltreleri);
		
		mnýtmMax = new JMenuItem("Max");
		mnSralamaFiltreleri.add(mnýtmMax);
		
		mnýtmMedian = new JMenuItem("Median");
		mnSralamaFiltreleri.add(mnýtmMedian);
		
		
		mnýtmMin = new JMenuItem("Min");
		mnSralamaFiltreleri.add(mnýtmMin);
		mnýtmMin.addActionListener(new FiltrelerListener(new MinFiltre()));
		mnýtmMedian.addActionListener(new FiltrelerListener(new MedianFiltre()));
		mnýtmMax.addActionListener(new FiltrelerListener(new MaxFiltre()));
		
		mnBulankaltrmaFiltreleri = new JMenu("Bulan\u0131k\u015Falt\u0131rma Filtreleri");
		mnFiltreler.add(mnBulankaltrmaFiltreleri);
		
		
		mnýtmOrtalama = new JMenuItem("Ortalama");
		mnBulankaltrmaFiltreleri.add(mnýtmOrtalama);
		
		mnýtmGauss = new JMenuItem("Gauss");
		mnBulankaltrmaFiltreleri.add(mnýtmGauss);
		mnýtmGauss.addActionListener(new FiltrelerListener(new GaussFiltre()));
		mnýtmOrtalama.addActionListener(new FiltrelerListener(new OrtFiltre()));
		
		mnýtmYaknlatr = new JMenuItem("Yak\u0131nla\u015Ft\u0131r 1");
		mnýtmYaknlatr.addActionListener(new FiltrelerListener(new ZoomIn1Filtre()));
		mnFiltreler.add(mnýtmYaknlatr);
		
		mnýtmYaknlatr_1 = new JMenuItem("Yak\u0131nla\u015Ft\u0131r 2");
		mnýtmYaknlatr_1.addActionListener(new FiltrelerListener(new ZoomIn2Filtre()));
		mnFiltreler.add(mnýtmYaknlatr_1);
		
		mnýtmUzaklatr = new JMenuItem("Uzakla\u015Ft\u0131r");
		mnýtmUzaklatr.addActionListener(new FiltrelerListener(new ZoomOutFiltre()));
		mnFiltreler.add(mnýtmUzaklatr);
		
		mnýtmKeskinletir = new JMenuItem("Keskinle\u015Ftir");
		mnýtmKeskinletir.addActionListener(new FiltrelerListener(new KeskinlestirmeFiltre()));
		mnFiltreler.add(mnýtmKeskinletir);
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
