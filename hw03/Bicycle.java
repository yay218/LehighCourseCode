import java.util.Scanner;
public class Bicycle {
    public static void main (String[] args) {
    	Scanner myScanner;
        myScanner = new Scanner(System.in);
        System.out.print("Enter the number of seconds: ");
        int secsTrip = myScanner.nextInt();
        System.out.print("Enter the number of counts: ");
        int countsTrip = myScanner.nextInt();
        double wheelDiameter=27.0,
        	    PI=3.14159,
        	    feetPerMile=5280,
        	    inchesPerFoot=12,
        	    secondsPerMinute=60;
        double distanceTrip,minutes,averageMiles,minutesFinal,distanceTripFinal,averageMilesFinal;
        minutes=secsTrip/secondsPerMinute;
        minutesFinal=(int)(minutes*100);
	    distanceTrip=countsTrip*wheelDiameter*PI/inchesPerFoot/feetPerMile;
	    distanceTripFinal=(int)(distanceTrip*100);
	    averageMiles=distanceTrip/(minutes/60);
	    averageMilesFinal=(int)(averageMiles*100);
	    System.out.println("The distance was "+(distanceTripFinal/100)+" miles and took "+(minutesFinal/100)+" minutes.");
	    System.out.println("The average mph was "+(averageMilesFinal/100));
    }
}