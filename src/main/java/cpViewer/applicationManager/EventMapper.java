package cpViewer.applicationManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;

import cpViewer.gui.components.Annotation.Event;

public class EventMapper {
	
	
	public void map(Class<?> cls) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException {

		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Event.class)) {
				Class<?> listenerClass = field.getAnnotation(Event.class).listenerClass();
				String[] methodNames = field.getAnnotation(Event.class).method();
				JButton button = (JButton) field.get(cls.getDeclaredConstructor().newInstance());
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
