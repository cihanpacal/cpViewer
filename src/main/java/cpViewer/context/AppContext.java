package cpViewer.context;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cpViewer.gui.components.Drawable;
import filters.IFilter;
import util.UndoRedoStack;

public class AppContext {
	
	private static AppContext appContext;
	
	private BufferedImage bufferedImage;
	
	private Drawable drawable;
	
	private UndoRedoStack<BufferedImage> undoRedoStack;
	
	private IFilter filter;
	
	private File file;
	
	private AppContext() {
		undoRedoStack=new UndoRedoStack<BufferedImage>();
	}
	
	public static AppContext getAppContext() {
		if(appContext==null) {
			appContext=new AppContext();
		}
		
		return appContext;
	}
	
	private void doFilter() {
		if(bufferedImage!=null) {
			undoRedoStack.push(bufferedImage);
			bufferedImage=filter.filtrele(bufferedImage);
			setBufferedImage(bufferedImage);
		}
	}
	
	public void undo() {
		setBufferedImage(undoRedoStack.pushRedo(bufferedImage));
	}
	
	public void redo() {
		setBufferedImage(undoRedoStack.pushUndo(bufferedImage));
	}
	
	public void save(String filename) throws IOException {
		if(bufferedImage!=null) {
			if(filename.endsWith(".png")) {
				ImageIO.write(AppContext.getAppContext().getBufferedImage(),"png",new File(filename));
			}else if(filename.endsWith(".jpg")) {
				ImageIO.write(AppContext.getAppContext().getBufferedImage(),"jpg",new File(filename));
			}else if(filename.endsWith(".gif")) {
				ImageIO.write(AppContext.getAppContext().getBufferedImage(),"gif",new File(filename));
			}else {
				filename+=".jpg";
				ImageIO.write(AppContext.getAppContext().getBufferedImage(),"jpg",new File(filename));
			}
		}
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
		drawable.draw(bufferedImage);
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
		if(filter!=null) {
			doFilter();
		}
	}

	public void setFile(File file) throws IOException {
		this.file = file;
		setBufferedImage(ImageIO.read(file));
	}
		
	
	
}
