package cpViewer.gui.components.main_panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import cpViewer.context.AppContext;
import filters.ZoomIn2Filtre;
import filters.ZoomOutFiltre;

import java.awt.Dimension;

public class InputGroup extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2173871115092809178L;
	private JButton zoomInButton;
	private JButton zoomOutButton;
	private JButton redoButton;
	private JButton undoButton;

	public InputGroup() {
		super();
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		setBackground(new Color(220, 220, 220));
		// setBorder(new LineBorder(new Color(180,180,180)));
		setPreferredSize(new Dimension(200, 35));
		undoButton = new JButton();
		undoButton.setActionCommand("undo");
		undoButton.setIcon(new ImageIcon(this.getClass().getResource("/images/undo.png")));
		redoButton = new JButton();
		redoButton.setActionCommand("redo");
		redoButton.setIcon(new ImageIcon(this.getClass().getResource("/images/redo.png")));
		zoomInButton = new JButton("+");
		zoomInButton.setActionCommand("zoomIn");
		zoomOutButton = new JButton("-");
		zoomOutButton.setActionCommand("zoomOut");
		

		JButton[] buttonList = new JButton[] { undoButton, redoButton, zoomOutButton, zoomInButton };

		for (JButton button : buttonList) {
			button.setFont(new Font("Tahoma", Font.BOLD, 15));
			button.setPreferredSize(new Dimension(40, 25));
			button.addActionListener(null);
			button.setBackground(UIManager.getColor("Button.background"));
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			add(button);
		}

		
		undoButton.addActionListener((event) -> {
			AppContext.getAppContext().undo();
		});

		redoButton.addActionListener((event) -> {
			AppContext.getAppContext().redo();
		});

		zoomInButton.addActionListener((event) -> {
			AppContext.getAppContext().setFilter(new ZoomIn2Filtre());
		});
		zoomOutButton.addActionListener((event) -> {
			AppContext.getAppContext().setFilter(new ZoomOutFiltre());
		});
	}
}
