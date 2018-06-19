package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;

import model.Direction;
import model.Floor;
import model.Position;

public class View extends JFrame {
	private final Floor floor = new Floor();
	private final JPanel floorPanel = new JPanel();
	private final JLabel status = new JLabel();
	private final Timer timer = new Timer(5, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			status.setText("Time " + floor.getTime() + " Ants " + floor.getAntCount());
			floor.tick();
			Graphics gr = floorPanel.getGraphics();
			floor.forEachSmell((p, s) -> drawSmell(gr, p, s));
		}
	});

	private void drawSmell(Graphics gr, Position position, int smell) {
		int red = Math.max(0, 255 - smell & 255);
		int green = Math.max(0, 255 - (smell >> 8) & 255);
		int blue = Math.max(0, 255 - (smell >> 16) & 255);
		Color color = new Color(red, green, blue);
		gr.setColor(color);
		gr.drawString("*", 250 + (int) position.getNorth(), 250 + (int) position.getEast());
	}

	public View(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		floor.addNest(Position.ORIGIN.move(Direction.E, 80));
		floor.addNest(Position.ORIGIN.move(Direction.SW, 80));
		floor.addNest(Position.ORIGIN.move(Direction.NW, 80));
		setLayout(new BorderLayout());
		JToolBar tools = new JToolBar();
		tools.add(new AbstractAction("Close") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(tools, BorderLayout.NORTH);
		floorPanel.setPreferredSize(new Dimension(500, 500));
		add(floorPanel, BorderLayout.CENTER);
		status.setPreferredSize(new Dimension(500, 20));
		status.setBorder(BorderFactory.createEtchedBorder());
		add(status, BorderLayout.SOUTH);
		timer.start();
	}

	public static void main(String[] args) {
		View view = new View("Ants");
		view.pack();
		view.setVisible(true);
	}

}
