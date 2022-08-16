package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import drawing.Canvas;

public class ProjectGUIload {
	public final int WINDOW_X_SIZE = 1600;
	public final int WINDOW_Y_SIZE = 600;
	public final int ORIGIN_XY = 0;
	private JPanel toolBar;
	private List<JButton> ButtonArray = new ArrayList<>();
	private List<JSlider> SliderArray = new ArrayList<>();
	private JFrame frame;

	public List<JButton> getButtonArray() {
		return ButtonArray;
	}

	public List<JSlider> getSliderArray() {
		return SliderArray;
	}

	
	public ProjectGUIload() {
		//Sets up frame
		frame = new JFrame();
		//Sets title of window
		frame.setTitle("Tie Fighter Flocking Simulation");
		frame.setSize(WINDOW_X_SIZE, WINDOW_Y_SIZE);
		//Allows closing with X and makes visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//Sets up toolbar and Icon
		toolBar = initialiseToolBar();
		Image icon = Toolkit.getDefaultToolkit().getImage("Tie-Fighter-Icon.png");  
		frame.setIconImage(icon);
		frame.setResizable(false);
		frame.validate();
	}

	/**
	 * @return returns toolbar, for adding elements to bottom of page
	 */
	private JPanel initialiseToolBar() {
		JPanel toolBar = new JPanel();
		frame.add(toolBar, BorderLayout.SOUTH);
		frame.validate();
		return toolBar;
	}

	/**
	 * @param buttonName, string with text to appear on button
	 * @return returns an index of the button for identification
	 */
	public int createButton(String buttonName) {
		JButton addButton = new JButton(buttonName);
		toolBar.add(addButton);
		ButtonArray.add(addButton);
		frame.validate();
		return ButtonArray.size()-1;
	}

	/**
	 * @param size, defines the range of values in the slider, from 0
	 * @param spacing, returns the number of spaces between major ticks
	 * @param sliderName, string which adds a label before the slider
	 * @return returns an index of the slider for identification
	 */
	public int createSlider(int size, int spacing, String sliderName) {
		// Slider Label:
		JLabel Slidername = new JLabel(sliderName);
		toolBar.add(Slidername);
		// Create Slider:
		JSlider createSlider = new JSlider(0, size, size/10);
		createSlider.setMinorTickSpacing(spacing);
		createSlider.setMajorTickSpacing(spacing*2);
		createSlider.setPaintTicks(true);
		toolBar.add(createSlider);
		SliderArray.add(createSlider);
		frame.validate();
		return SliderArray.size()-1;

	}

	/**
	 * @return returns the canvas to the main function
	 */
	public Canvas setCanvas() {
		Canvas canvas = new Canvas();
		frame.add(canvas, BorderLayout.CENTER);
		canvas.setBackground(Color.LIGHT_GRAY);
		frame.validate();
		return canvas;
	}
	
	public void popUp(String popUpText) {
		//creates a popup with the string inputted
		JOptionPane.showMessageDialog(frame, popUpText);
	}

}