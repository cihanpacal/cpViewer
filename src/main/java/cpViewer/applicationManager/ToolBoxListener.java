package cpViewer.applicationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ToolBoxListener implements ActionListener {

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
				System.out.println("************");
				e1.printStackTrace();
			}
		

	}
}
