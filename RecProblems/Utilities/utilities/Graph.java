package utilities;

import java.util.ArrayList;

@SuppressWarnings("null")

public class Graph {
	public abstract class ConicSection{
		public abstract double eccentricity();
		public abstract ArrayList<Coord> foci();
		public abstract String getForm();
		public ArrayList<Double> sqrtA(double aSq, double bSq, double h, double k){
			ArrayList<Double> retList = new ArrayList<>();
			retList.add(Math.sqrt(aSq));
			retList.add(Math.sqrt(bSq));
			retList.add(h);
			retList.add(k);
			return retList;
		}
	}
	public class Coord{
		public double X, Y;
		public Coord(){
			initialize(0, 0);
		}
		public Coord(int x, int y){
			initialize(x, y);
		}
		public Coord(double x, double y){
			initialize(x, y);
		}
		private void initialize(double x, double y){
			X = x;
			Y = y;
		}
		public Coord clone(){
			return new Coord(X, Y);
		}
		public double distance(Coord c){
			return Math.sqrt(Math.pow(X - c.X, 2) + Math.pow(Y - c.Y, 2));
		}
		public void moveHoriz(double d){
			X += d;
		}
		public void moveVert(double d){
			Y += d;
		}
		public String toString(){
			return "(" + X + ", " + Y + ")";
		}
		public boolean equals(Coord c){
			return Y == c.Y && X == c.X;
		}
	}
	public enum Orientation{
		HORIZONTAL,
		VERTICAL;
	}
	
	
	
	
	
	
		
	public class Ellipse extends ConicSection{
		public Coord center;
		public ArrayList<Coord> foci, vertices, coverticies;
		public String getForm(){ return "(x-h)^2/a^2+(y-k)^2/b^2=1";}
		public double A, B, H, K;
		public Orientation orientation;
		/**
		 * a must be the first denominator, under x; b must be the second denominator, under y.
		 */
		public Ellipse(double a, double b, double h, double k){
			A = a;
			B = b;
			H = h;
			K = k;
			center = new Coord(H, K);
			orientation = A > B ? Orientation.HORIZONTAL : Orientation.VERTICAL;
			foci = foci();
			vertices = vertices();
		}
		/**
		 * args must follow the form a, b, h, k.
		 * a must be the first denominator, under x; b must be the second denominator, under y.
		 */
		public Ellipse(ArrayList<Double> args){
			if(args.size() < 4) throw new IndexOutOfBoundsException("args must have a size of at least 4");
			A = args.get(0);
			B = args.get(1);
			H = args.get(2);
			K = args.get(3);
			
			center = new Coord(H, K);
			orientation = A > B ? Orientation.HORIZONTAL : Orientation.VERTICAL;
			foci = foci();
		}
		@Override
		public double eccentricity() {
			return ( Math.sqrt(Math.pow(A,2) - Math.pow(B, 2)) ) / A;
		}
		@Override
		public ArrayList<Coord> foci() {
			double c = Math.sqrt(Math.abs(Math.pow(A, 2) - Math.pow(B, 2)));
			
			ArrayList<Coord> retCoords = new ArrayList<Coord>();
			retCoords.add(center.clone());
			retCoords.add(center.clone());
			
			if(orientation == Orientation.HORIZONTAL){
				retCoords.get(0).moveHoriz(c);
				retCoords.get(1).moveHoriz(-1 * c);
			} else if(orientation == Orientation.VERTICAL){
				retCoords.get(0).moveVert(c);
				retCoords.get(1).moveVert(-1 * c);
			}
			
			return retCoords;
		}
		/**
		 * Elements 0 & 1 are vertices, 2 & 3 are co-vertices.
		 */
		public ArrayList<Coord> vertices(){
			ArrayList<Coord> retList = new ArrayList<>();
			for(int i = 0; i < 4; i++)
				retList.add(center.clone());
		
			retList.get(0).moveHoriz(A);
			retList.get(1).moveHoriz(-1*A);
			retList.get(2).moveVert(B);
			retList.get(3).moveVert(-1*B);
			
			return retList;
		}
		public String toString(){
			return "((x - " + H + ")^2) / (" + A + " ^2) + "
				 + "((y - " + K + ")^2) / (" + B + " ^2) = 1";
		}
		
		
	}
	
	
	
	
	
	
	
	
	public class Parabola extends ConicSection{
		public String getForm(){ return "";}
		@Override
		public double eccentricity() {
			return (Double) null;
		}

		@Override
		public ArrayList<Coord> foci() {
			return null;
		}
		
	}
	public class Hyperbola extends ConicSection{
		public String getForm(){ return "";}
		@Override
		public double eccentricity() {
			return (Double) null;
		}

		@Override
		public ArrayList<Coord> foci() {
			return null;
		}
		
	}
	public class Circle extends ConicSection{
		public Coord center;
		public String getForm(){ return "";}
		@Override
		public double eccentricity() {
			return 0;
		}

		@Override
		public ArrayList<Coord> foci() {
			ArrayList<Coord> retList = new ArrayList<>();
			retList.add(center);
			return retList;
		}
		
	}
}
