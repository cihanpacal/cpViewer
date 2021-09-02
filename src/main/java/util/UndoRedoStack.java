package util;

import java.util.Stack;

public class UndoRedoStack<T> {
	
	private T current;
	
	private Stack<T> undoStack;
	private Stack<T> redoStack;

}
