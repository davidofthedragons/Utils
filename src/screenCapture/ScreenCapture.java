package screenCapture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ScreenCapture extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	
	Robot robot;
	BufferedImage screen;
	JFileChooser chooser = new JFileChooser();
	ScreenPanel screenPanel = new ScreenPanel();
	JPanel buttonPanel = new JPanel();
	JButton saveButton;
	
	public ScreenCapture() {
		super("Screen Capture");
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.err.println("Fatal Error: cannot initialize Robot.");
			System.exit(1);
		}
		
		createGUI();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void createGUI() {
		super.setLayout(new BorderLayout());
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);
		buttonPanel.setLayout(layout);
		JButton capture = new JButton("Capture");
		capture.setActionCommand("Capture");
		capture.addActionListener(this);
		buttonPanel.add(capture);
		saveButton = new JButton("Save Image");
		saveButton.setActionCommand("Save");
		saveButton.addActionListener(this);
		buttonPanel.add(saveButton);
		
		super.add(buttonPanel, BorderLayout.NORTH);
		super.add(screenPanel, BorderLayout.CENTER);
		
		
	}
	
	
	public static void main(String[] args) {
		new ScreenCapture();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Capture")) {
			final Rectangle bounds = new Rectangle(screenPanel.getLocationOnScreen().x, screenPanel.getLocationOnScreen().y, screenPanel.getWidth(), screenPanel.getHeight());
			this.setVisible(false);
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						System.err.println("I don't even know why there's an error here...");
					}
					screen = robot.createScreenCapture(bounds);
					setVisible(true);
					screenPanel.repaint();
				}
			});
			thread.start();
			
		}
		else if(e.getActionCommand().equals("Save")) {
			int retVal = chooser.showSaveDialog(this);
			if(retVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				try {
					ImageIO.write(screen, "png", file);
				} catch (IOException e1) {
					System.err.println("Error writing to file");
				}
			}
		}
	}

	
	class ScreenPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			g.drawImage(screen, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
	

}
