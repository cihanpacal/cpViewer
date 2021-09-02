package cpViewer.gui.components.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.swing.JComponent;

@Retention(RetentionPolicy.RUNTIME)
public @interface Event {
	String[] method();
	Class<?> listenerClass();
	//Class<?> componentType();
}
