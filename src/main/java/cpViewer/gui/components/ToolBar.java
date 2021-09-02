package cpViewer.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import cpViewer.gui.components.Annotation.Event;
import cpViewer.gui.eventListener.EventListener;
import cpViewer.gui.eventListener.EventListener2;

public class ToolBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Event(method = { "zoomInButtonClicked" }, listenerClass = EventListener.class)
	private JButton zoomInButton;

	@Event(method = "zoomOutButtonClicked", listenerClass = EventListener2.class)
	private JButton zoomOutButton;

	@Event(method = "redoButtonClicked", listenerClass = EventListener.class)
	private JButton redoButton;

	@Event(method = {"undoButtonClicked"}, listenerClass = EventListener.class)
	private JButton undoButton;

	public ToolBar() {
		super();
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setPreferredSize(new Dimension(10, 40));
		setBackground(new Color(220, 220, 220));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		createToolBoxItems();
		try {
			eventMapper(this.getClass());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

	private void createToolBoxItems() {
		// ActionListener actionListener=new ToolBoxListener();
		undoButton = new JButton("geri");
		redoButton = new JButton("ileri");
		zoomInButton = new JButton("+");
		zoomOutButton = new JButton("-");
		JButton[] buttonList = new JButton[] { undoButton, redoButton, zoomOutButton, zoomInButton };

		for (JButton button : buttonList) {
			button.setFont(new Font("Tahoma", Font.BOLD, 15));
			button.setPreferredSize(new Dimension(50, 30));
			// button.addActionListener(actionListener);
			button.setBackground(UIManager.getColor("Button.background"));
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			add(button);
		}

	}

	private class ToolBoxListener implements ActionListener {

		Method method;
		Class<?> listenerClass;
		
		public ToolBoxListener(Method method,Class<?> listenerClass) {
			this.method=method;
			this.listenerClass=listenerClass;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
				try {
					method.invoke(listenerClass.newInstance());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			

		}

	}

	public void eventMapper(Class<?> cls) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException {

		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Event.class)) {
				Class<?> listenerClass = field.getAnnotation(Event.class).listenerClass();
				String[] methodNames = field.getAnnotation(Event.class).method();
				JButton button = (JButton) field.get(this);
				for(String name : methodNames) {
					Method method= listenerClass.getDeclaredMethod(name);
					if(method!=null) {
						button.addActionListener(new ToolBoxListener(method, listenerClass));
					}
				}
			}
		}
	}

}
