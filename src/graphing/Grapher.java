package graphing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parser.MathParser;
import parser.MathParser.MathSyntaxException;

public class Grapher extends JPanel {
	
	private DataSet data;
	private String function;
	private int sx = 800, sy = 800;
	private double xmin=-10.0, xmax=10.0, ymin=-10.0, ymax=10.0;
	private double delta = .1;
	
	
	public Grapher(DataSet data) {
		this.data=data;
	}
	public Grapher(String function) {
		this.function = function;
	}
	
	public void setFunction(String f) {function = f; this.repaint();}
	public String getFunction() {return function;}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, sy - (int) map(ymin, ymax, 0, sy, 0), sx, sy - (int) map(ymin, ymax, 0, sy, 0));
		g.drawLine((int) map(xmin, xmax, 0, sx, 0), 0, (int) map(xmin, xmax, 0, sx, 0), sy);
		double j = 0.0;
		Point prevPoint = null;
		for(double i=xmin; i<xmax; i+=delta) {
			try {
				j = Double.parseDouble(MathParser.parse(function.replace("x", Double.toString(i))));
			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException waz here");
				//e.printStackTrace();
				prevPoint = null;
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("StringIndexOutOfBoundsException waz here");
				prevPoint = null;
			} catch (MathSyntaxException e) {
				System.out.println("MathSyntaxException waz here");
				prevPoint = null;
			}
			
			Point p = new Point((int) Math.round(map(xmin, xmax, 0, sx, i)), sy - (int) Math.round(map(ymin, ymax, 0, sy, j)));
			
			if(prevPoint != null)
				g.drawLine(prevPoint.x, prevPoint.y, p.x, p.y);
			else g.drawLine(p.x, p.y, p.x, p.y);
			prevPoint = new Point(p.x, p.y);
			//System.out.println(j);
		}
	}
	
	public double map(double imin, double imax, double omin, double omax, double x) {
		return (x - imin) * (omax - omin) / (imax - imin) + omin;
	}
	
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Graphing Test");
		frame.setSize(825, 875);
		frame.setLayout(new BorderLayout());
		final Grapher grapher = new Grapher("x");
		frame.add(grapher, BorderLayout.CENTER);
		JPanel controlPanel = new JPanel();
		controlPanel.add(new JLabel("x Min:"));
		final JTextField xminBox = new JTextField(4);
		xminBox.setText(Double.toString(grapher.xmin));
		controlPanel.add(xminBox);
		controlPanel.add(new JLabel("x Max:"));
		final JTextField xmaxBox = new JTextField(4);
		xmaxBox.setText(Double.toString(grapher.xmax));
		controlPanel.add(xmaxBox);
		controlPanel.add(new JLabel("y Min:"));
		final JTextField yminBox = new JTextField(4);
		yminBox.setText(Double.toString(grapher.ymin));
		controlPanel.add(yminBox);
		controlPanel.add(new JLabel("y Max:"));
		final JTextField ymaxBox = new JTextField(4);
		ymaxBox.setText(Double.toString(grapher.ymax));
		controlPanel.add(ymaxBox);
		controlPanel.add(new JLabel("   "));
		controlPanel.add(new JLabel("y ="));
		final JTextField fBox = new JTextField(6);
		fBox.setText(grapher.getFunction());
		controlPanel.add(fBox);
		JButton gButton = new JButton("Graph!");
		gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grapher.xmin = Double.parseDouble(xminBox.getText());
				grapher.xmax = Double.parseDouble(xmaxBox.getText());
				grapher.ymin = Double.parseDouble(yminBox.getText());
				grapher.ymax = Double.parseDouble(ymaxBox.getText());
				grapher.setFunction(fBox.getText());
			}
		});
		controlPanel.add(gButton);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
