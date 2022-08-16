package ProjectMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.ProjectGUIload;
import drawing.Canvas;
import geometry.CartesianCoordinate;
import shape.Circle;
import tools.Utils;
import turtle.Boid;
import turtle.TieFighter;

public class ProjectMain {

	private List<Boid> boids;
	private List<Circle> circlesArray;
	private Canvas canvas;
	private int detectionRadius;
	private double cohesionCoef;
	private double seperationCoef;
	private double alignmentCoef;

	private static final int INVERSE_REFRESH_RATE = 14;

	public ProjectMain() {
		// ----------------Initialise GUI----------------\\
		ProjectGUIload GUI = new ProjectGUIload();
		canvas = GUI.setCanvas();

		// sets background image and places on canvas
		JLabel Background = new JLabel(new ImageIcon("Background.png"));
		canvas.setLayout(null);
		canvas.add(Background);
		Background.setBounds(GUI.ORIGIN_XY, GUI.ORIGIN_XY, GUI.WINDOW_X_SIZE, GUI.WINDOW_Y_SIZE);
		canvas.validate();

		// draws the deathstar boundries
		drawMap();

		// list of GUI elements to be added to the toolbar
		int AddButton = GUI.createButton("Add Boid");
		int DelButton = GUI.createButton("Delete Boid");
		int SpeedSlider = GUI.createSlider(1000, 100, "Speed:");
		int VisualRangeSlider = GUI.createSlider(200, 10, "Visual Range:");
		int AlignmentSlider = GUI.createSlider(100, 10, "Alignment:");
		int CohesionSlider = GUI.createSlider(100, 10, "Cohesion:");
		int SeparationSlider = GUI.createSlider(100, 10, "Separation:");

		// Syncs array to keep prevent desync errors
		boids = Collections.synchronizedList(new ArrayList<>());

		// ----------------Listens to buttons and sliders----------------\\
		// Buttons
		GUI.getButtonArray().get(AddButton).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boids.add(
						new TieFighter(canvas, new CartesianCoordinate(GUI.WINDOW_X_SIZE / 4, GUI.WINDOW_Y_SIZE / 2)));
				Boid boid = boids.get(boids.size() - 1);
				boid.setSpeed(GUI.getSliderArray().get(SpeedSlider).getValue());
				boid.turn(getRandomNumber(0, 360));
			}
		});

		GUI.getButtonArray().get(DelButton).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (boids.size() != 0) {
					boids.get(boids.size() - 1).undraw();
					boids.remove(boids.get(boids.size() - 1));

				} else {
					GUI.popUp("No more boids available to delete!");
				}
			}
		});
		// Sliders
		GUI.getSliderArray().get(SpeedSlider).addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				for (Boid boid : boids) {
					boid.setSpeed(GUI.getSliderArray().get(SpeedSlider).getValue());
				}
			}
		});

		GUI.getSliderArray().get(VisualRangeSlider).addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				detectionRadius = GUI.getSliderArray().get(VisualRangeSlider).getValue();
			}
		});

		GUI.getSliderArray().get(CohesionSlider).addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cohesionCoef = (double) GUI.getSliderArray().get(CohesionSlider).getValue() / 1000;
			}
		});
		GUI.getSliderArray().get(SeparationSlider).addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				seperationCoef = (double) GUI.getSliderArray().get(SeparationSlider).getValue() / 1000;
			}
		});
		GUI.getSliderArray().get(AlignmentSlider).addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				alignmentCoef = (double) GUI.getSliderArray().get(AlignmentSlider).getValue() / 1000;
			}
		});

	}

	public static void main(String[] args) {
		ProjectMain projectMain = new ProjectMain();
		projectMain.runGame();
	}

	// --------------------Game Function--------------------\\
	private void runGame() {

		int deltaTime = INVERSE_REFRESH_RATE;
		boolean continueRunning = true;
		// --------------------Update Loop--------------------\\
		while (continueRunning) {
			Utils.pause(deltaTime);
			synchronized (boids) {
				// undraws the boids in the array
				for (Boid boid : boids) {
					boid.undraw();
				}
			}

			synchronized (boids) {
				for (Boid boid : boids) {
					// Updates the boids positioning mathematically
					// checks for wraping at borders and collisions
					boid.wrap(canvas.getWidth(), canvas.getHeight());
					boid.checkColision(circlesArray);
					double cohesion = boid.cohesion(boids, detectionRadius, cohesionCoef);
					double seperation = boid.seperation(boids, detectionRadius, seperationCoef);
					double alignment = boid.alignment(boids, detectionRadius, alignmentCoef);
					boid.turn(cohesion + seperation + alignment);
					boid.update(deltaTime);
				}

				synchronized (boids) {
					for (Boid boid : boids) {
						// draws each boid at their new position
						boid.draw();
					}

				}

			}
		}

	}

	private void drawMap() {
		// sets up zones for obstacle collision
		circlesArray = new ArrayList<>();
		Circle circle1 = new Circle(canvas, new CartesianCoordinate(350, 200), 75);
		circlesArray.add(circle1);
		Circle circle2 = new Circle(canvas, new CartesianCoordinate(150, 150), 40);
		circlesArray.add(circle2);
		Circle circle3 = new Circle(canvas, new CartesianCoordinate(600, 300), 80);
		circlesArray.add(circle3);
	}

	/**
	 * @param min, minimum value to be generated
	 * @param max, maximum value to be generated
	 * @return returns a random integer
	 */
	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}