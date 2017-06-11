import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class PiPanel extends JPanel{
	
	private int X, Y;
	private int n, r;
	private Dots dots = Dots.randN(0);
	
	public PiPanel(){
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		X = getWidth();
		Y = getHeight();
		int w = 7;
		
		for(int i = 0; i < getDots().getArray().length; i++){
			if(!getDots().getArray()[i].inQuadrant()) g.setColor(Color.ORANGE);
			else g.setColor(Color.BLUE);
			double xloc = getDots().getArray()[i].getX();
			double yloc = getDots().getArray()[i].getY();
			g.fillOval((int)(X*xloc), (int)(Y*yloc), w, w);
		}
		
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawOval(-X, -Y, X*2, Y*2);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Calibri", Font.PLAIN, 20));
		String data = "";
		
		if(getDots().getArray().length != 0){
			data = "For "+getN()+" points: ";
			g2d.drawString("The approximate value for pi is: "+getDots().piApprox(), 5, 45);
		}
		
		if(getR() != 0){
			data = "For "+getN()+" points and "+getR()+" iterations: ";
			g2d.drawString("The avg. value of pi is: "+Dots.piAvg(getDots(), getR()), 5, 65);
			g2d.drawString("The standard deviation is: "+Dots.stDev(getDots(), getR()), 5, 85);
		}
		g2d.drawString(data, 5, 25);
	}
	
	public int getN(){
		return n;
	}
	public void setN(int n){
		this.n = n;
	}
	public int getR(){
		return r;
	}
	public void setR(int r){
		this.r = r;
	}
	public Dots getDots(){
		return dots;
	}
	public void newDots(int n){
		dots = Dots.randN(n);
	}
	
	
}



public class PiFrame extends JFrame{

	private int X = 650, Y = 600;
	PiPanel pipan;
	JPanel control;
	JLabel nPoints, nItr;
	JTextField npText, nrText;
	
	public PiFrame(){
		
		nPoints = new JLabel("Enter the no. of points: ");
		npText = new JTextField(10);
		nItr = new JLabel("Enter the no. of iterations: ");
		nrText = new JTextField(10);
		
		pipan = new PiPanel();
		control  = new JPanel(); 
		control.add(nPoints);
		control.add(npText);
		control.add(nItr);
		control.add(nrText);
		
		npText.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				npText.setText("");
			}
		});

		nrText.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				nrText.setText("");
			}
		});
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		
		
		setLayout(new BorderLayout());
		getContentPane().add(pipan, BorderLayout.CENTER);
		getContentPane().add(control, BorderLayout.SOUTH);
		pack();
		
		setTitle("Pi approximation");
		setBackground(Color.LIGHT_GRAY);
		setSize(X, Y);
		setLocation(200, 0);
		setVisible(true);
		
		renumerate();
		reiterate();
	}
	
	public void renumerate(){
		npText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int n = Integer.parseInt(npText.getText());
				if(n > 0){
					pipan.newDots(n);
					pipan.setN(n);
				}
				repaint();
			}
		});
	}
	
	public void reiterate(){
		nrText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int r = Integer.parseInt(nrText.getText());
				if(r > 0) pipan.setR(r);
				repaint();
			}
		});
	}
	
	
	
}
