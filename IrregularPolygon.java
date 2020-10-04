package irregularpolygon;

import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import gpdraw.*; // for gpdraw
import java.awt.Color;

public class IrregularPolygon {
    
    public ArrayList<Point2D.Double> myPolygon; //creates  arraylist myPolygon

    // constructor
    public IrregularPolygon() {
        myPolygon = new ArrayList<Point2D.Double>();
    }

    //methods
    public void add(double x, double y) {  //method that adds a new point in the form of x,y to the myPolygon array.  This point is then recognized by 2ddouble
        Point2D.Double newPoint = new Point2D.Double(x, y);
        myPolygon.add(newPoint);
        //this method allows a 2dpoint to be "added" to a polygon    
    }
    
    public void draw() {
        DrawingTool pencil = new DrawingTool(new SketchPad(1000, 1000));
        pencil.up();//pencil moves to first point in polygon without leaving mark
        Point2D.Double point0 = myPolygon.get(0); //point0 in 2d is the polygons first point
        pencil.move(point0.x, point0.y);//pencil moves to the first point the .x and .y means the x and y coordinates of the point
        pencil.setColor(Color.blue);//to make polygon cool
        pencil.setWidth(10);
        pencil.down();//pencil begins drawing starting from the first point

        for (int i = 0; i < myPolygon.size(); i++) { //runs the amount of times as the amount of points in the polygon
            Point2D.Double point1 = myPolygon.get(i);//each new point is the index  i of the array
            pencil.move(point1.x, point1.y);//pencil draws connecting the points as the loop runs

        }
        pencil.move(point0.x, point0.y);//pencil connects the last point with the first point.  This is after the loop, because after the loop has run the pencil is not the last point i of the polygon

    }
    
    public double area() { //the equation given is split.  The addition side is called sumPos, and the sub side is called sumNeg
        double area = 0.0;
        double sumPos = 0.0;//sumPos is the positive side of the equation
        double sumNeg = 0.0;//sumNeg is the negative side of the given equation
        //after sumPos and sumNeg is found sumNeg is subtracted from sumPos
        for (int i = 0; i < myPolygon.size(); i++) {//repeats loop for every point in the arraylist
            Point2D.Double point = myPolygon.get(i); //gets a point in the index i of array my polygon
            if (i == myPolygon.size() - 1) { //if i is the last point, then the next point to compare it to is in index 0
                sumPos = sumPos + point.x * myPolygon.get(0).y; //last multiplication is x(n-1) times y(0) on addtion side
                sumNeg = sumNeg + point.y * myPolygon.get(0).x;//on subtaction side
            } else {//if i is not the last point, then then next point is i+1 for y for sumPos, and x for sumNeg
                sumPos = sumPos + point.x * myPolygon.get(i + 1).y;  //in the equation, the positive side is x(i) multiplied with y(i+1)
                sumNeg = sumNeg + point.y * myPolygon.get(i + 1).x; //sumNeg in the equation multiplies y(i) with x(i+1)

            }//since the equation has a part where the terms are added, and then a part where they are subtrated, you find find both parts and then subract them

        }
        area = Math.abs((sumPos - sumNeg) / 2.0); //finds the area by taking the absolute value of the sumPos-sumNeg devided by 2
        return area; //method returns double area
    }
    
    public double perimeter() {
        double perimeter = 0.0; //creates variable perimeter

        for (int i = 0; i < myPolygon.size(); i++) { //loop runs for every point in the arralist Polygon
            Point2D.Double point1 = myPolygon.get(i); //loop runs many times and calculates distance between 2 points.  first point is i
            Point2D.Double point2;
            
            if (i == myPolygon.size() - 1) {
                point2 = myPolygon.get(0); //if i is the last point, then the next point is the first point                       

            } else {
                point2 = myPolygon.get(i + 1); //else the second point is the next point after i                
            }
            double distance = Math.sqrt((point2.x - point1.x) * (point2.x - point1.x) + (point2.y - point1.y) * (point2.y - point1.y)); //finds the square of the distance between the points.  
            perimeter = perimeter + distance; //loop runs as many times as there is point. The distance gets added onto the perimeter 
        }
        return perimeter;
    }
//all methods are outside main

    public static void main(String[] args) {//main creates a polygon (coolPolygon) and then runs the methods of draw, area and perimeter

        IrregularPolygon coolPolygon = new IrregularPolygon(); //creates a new polygon
        coolPolygon.add(20, 10); //is able to add new points to the 2d double due to the add method
        coolPolygon.add(70, 20);
        coolPolygon.add(50, 50);
        coolPolygon.add(0, 40);
        coolPolygon.add(100, 100);
        
        coolPolygon.draw();//calls the method draws

        System.out.printf("The perimeter is %.1f\n", +coolPolygon.perimeter()); //prints out the perimeter and formats it by rounding to the 10th decimal place. the /n is so that the next item printe to go onto the next line
        System.out.printf("The area is %.1f\n", coolPolygon.area());//prints out the area in the same way the perimeter is printed

        coolPolygon = new IrregularPolygon(); //creates a new polygon
        coolPolygon.add(-150, -75);
        coolPolygon.add(-165, 70);
        coolPolygon.add(-75, -30);
        coolPolygon.add(0, 70);
        coolPolygon.add(75, -30);
        coolPolygon.add(165, 70);
        coolPolygon.add(150, -75);
        
        coolPolygon.draw();
        
        System.out.printf("The perimeter is %.1f\n", coolPolygon.perimeter());
        System.out.printf("The area is %.1f\n", coolPolygon.area());
    }
}
