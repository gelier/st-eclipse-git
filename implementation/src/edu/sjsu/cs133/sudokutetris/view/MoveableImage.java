package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Customized image class that contains an explosion image to be used by the
 * game pieces as well as flags, draw, and set/get for positioning.
 */
public class MoveableImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image = null;
	private boolean visible = false;
	private int x = 0;
	private int y = 0;

	String name = "";
	private boolean mouseEnable = false;

	boolean placed = false;
	boolean dragging = false;
	boolean chosen = false;
	DragSource dragSource;

	int height;
	int width;

	private Point offset;

	/**
	 * Constructor to create moveable image.
	 * 
	 * @param x     position
	 * @param y     position
	 * @param image to be displayed
	 */
	MoveableImage(int x, int y, Image image) {

		this.image = image;
		this.x = x;
		this.y = y;

	}

	public void setImageHeight(int height) {
		this.height = height;
	}

	public void setImageWidth(int width) {
		this.width = width;
	}

	public int getImageHeight() {
		return height;
	}

	public int getImageWitdh() {
		return width;
	}
	public void setName(String name) {

		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public boolean getEnable() {
		return mouseEnable;
	}

	public void setEnable(boolean enable) {
		mouseEnable = enable;
	}

	/*
	 * public void dragGestureRecognized(DragGestureEvent evt) { Transferable t =
	 * new MoveableImage(); dragSource.startDrag(evt, DragSource.DefaultCopyDrop, t,
	 * this); } /** Set visible flag.
	 * 
	 * @param flag indicating whether image is visible or not
	 */
	public void setVisible(boolean flag) {
		this.visible = flag;
	}

	/**
	 * Get visible flag
	 * 
	 * @return flag indicating if image is visible or not
	 */
	public boolean getVisible() {
		return this.visible;
	}

	/**
	 * Set the x position for image.
	 * 
	 * @param x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set the y position for image.
	 * 
	 * @param y position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the x position.
	 * 
	 * @return x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y position.
	 * 
	 * @return y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the current image set.
	 * 
	 * @return image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Draw the image onto the observer
	 * 
	 * @param g graphics
	 * @param o observer (window/component/panel)
	 */
	public void draw(Graphics g, ImageObserver o) {
		g.drawImage(this.image, x, y, o);
	}

	/**
	 * Reset image to former. (Used after explosion change)
	 * 
	 * @param image
	 */
	public void reset(Image image) {
		this.image = image;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	public boolean getDragging() {
		return dragging;
	}

	public void setIsplaced(boolean placed) {
		this.placed = placed;
	}

	boolean isPlaced() {
		return placed;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	boolean isChosen() {
		return chosen;
	}

}