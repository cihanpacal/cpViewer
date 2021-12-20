package util;

import java.util.Stack;

public class UndoRedoStack<T> {
	
	private Stack<T> undoStack;
	private Stack<T> redoStack;
	
	public UndoRedoStack() {
		undoStack=new Stack<T>();
		redoStack=new Stack<T>();
	}
	
	
	public void push(T item) {
		undoStack.push(item);
		redoStack.clear();
	}
	
	//redo
	public T pushUndo(T current) {
		if(!redoStack.isEmpty()) {
			undoStack.push(current);
			return redoStack.pop();
		}else {
			return current;
		}
	}
	
	//undo
	public T pushRedo(T current) {
		if(!undoStack.isEmpty()) {
			redoStack.push(current);
			return undoStack.pop();
		}else {
			return current;
		}
	}
	
	public void clear() {
		undoStack.clear();
		redoStack.clear();
	}

}
