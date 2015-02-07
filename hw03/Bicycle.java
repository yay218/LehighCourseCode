////////////////////////////////////////////
//Name: Yang Yi
//Date: 02/06/15
//Professor: Brian Chen 
//Class: CSE 002
import java.util.Scanner;
public class Bicycle {
    public static void main (String[] args) { // main method required for every Java program
    	Scanner myScanner; 
        myScanner = new Scanner(System.in);
        System.out.print("Enter the number of seconds: "); //Let user enter the number of seconds
        int secsTrip = myScanner.nextInt(); //Enter the number of seconds
        System.out.print("Enter the number of counts: "); //Let user enter the number of counts
        int countsTrip = myScanner.nextInt(); //Enter the number of counts
        double wheelDiameter=27.0, //the diameter of the wheel is 27.0
        	    PI=3.14159,
        	    feetPerMile=5280, //there are 5280 feet per mile
        	    inchesPerFoot=12, //there are 12 inches per foot
        	    secondsPerMinute=60; //there are 60 seconds per minute
        double distanceTrip,minutes,averageMiles,minutesFinal,distanceTripFinal,averageMilesFinal;
        minutes=secsTrip/secondsPerMinute; //calculate the number of minutes
        minutesFinal=(int)(minutes*100); 
	    distanceTrip=countsTrip*wheelDiameter*PI/inchesPerFoot/feetPerMile; //calculate the distance of the trip
	    distanceTripFinal=(int)(distanceTrip*100);
	    averageMiles=distanceTrip/(minutes/60); //calculate the average miles per hour
	    averageMilesFinal=(int)(averageMiles*100); 
	    System.out.println("The distance was "+(distanceTripFinal/100)+" miles and took "+(minutesFinal/100)+" minutes.");
	    System.out.println("The average mph was "+(averageMilesFinal/100)); //print the average speed per hour
    }//end of main method 
}//end of class