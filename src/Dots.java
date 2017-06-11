import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Dots{

	private Point[] points;
	private int r;
	private double piApp, piAvg;

	public Dots(Point[] points){

		this.points = points;
		this.r = 1;
	}


	public Point[] getArray(){
		return points;
	}
	public void setArray(Point[] points){
		this.points = points;
	}
	public int getR(){
		return r;
	}
	public void setR(int r){
		this.r = r;
	}

	public double nC(){

		double runningtotal = 0;

		for(int i = 0; i < getArray().length; i++){
			if(getArray()[i].inQuadrant()) runningtotal++;
		}
		return runningtotal;
	}


	public double piApprox(){
		return (nC()/(double)getArray().length)*4.0;
	}



	public void printN(){
		System.out.println("For "+getArray().length+" points, the value of pi is: "+piApprox());
	}



	public static double piAvg(Dots d, int r){

		//Dots d = new Dots(points);

		int n = d.getArray().length;
		double runningtotal = 0;

		for(int i = 0; i < r; i++){
			double pie = d.piApprox();
			runningtotal += pie;
			d = randN(n);
		}

		return runningtotal/r;
	}

	public static double stDev(Dots d, int r){

		double piAvg = piAvg(d, r);
		double fraction = 1.0/(r - 1.0);
		double runningtotal = 0;

		for(int i = 0; i < r; i++){
			
			double pie = d.piApprox();
			double brackets = (pie - piAvg)*(pie - piAvg);
			runningtotal += brackets;
		}


		return Math.sqrt(fraction*runningtotal);
	}
	
	
	public static Dots askRandN(){
		int n = askN();	
		return new Dots(Point.randPointArray(n));
	}



	public static Dots randN(int n){
		return new Dots(Point.randPointArray(n));
	}


	public static void printAndSave()throws IOException{
		
		Dots d = askRandN();
		double piapp = d.piApprox();
		
		System.out.println("The approx value of pi for "+d.getArray().length+" points is: "+piapp);
		
		int r = askR();
		double avgpi = piAvg(d, r);
		double stdev = stDev(d, r);
		
		System.out.println("For "+r+" iterations, the average value for pi is: "+avgpi+" (st dev "+stdev+")");
		
		String filename = askFilename();
		
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(filename)));
		out.write("The approx value of pi for "+d.getArray().length+" points is: "+piapp+"\n");
		out.write("For "+r+" iterations, the average value for pi is: "+avgpi+" (st dev "+stdev+")\n");
		out.close();
		System.out.println("Results saved to "+filename);
	}
	
	
	public static int askN(){
		Scanner keyboard = new Scanner(System.in);
		int n = 0;

		while(true){
			try{
				System.out.println("Enter the number of points: ");
				n = keyboard.nextInt();
				if(n >= 10 && n <= 1000) break;
				System.out.println("Value must be between 10 & 1000");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}
		return n;
	}



	public static int askR(){
		Scanner keyboard = new Scanner(System.in);
		int r = 0;

		while(true){
			try{
				System.out.println("Enter the number of iterations: ");
				r = keyboard.nextInt();
				if(r > 0) break;
				System.out.println("Value must be at least 1.");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}
		return r;
	}


	public static String askFilename(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of the file to save these results to.");
		return keyboard.next().trim()+".txt";
	}


}
