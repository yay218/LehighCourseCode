public class Cyclometer {
    //main method required for every Java program
    public static void main (String[] args) {
    int secsTrip1=480; //create variables that will store the number of seconds for trip1
    int secsTrip2=3220; //create variables that will store the number of seconds for trip2
    int countsTrip1=1561; //create variables that will store the number of countsfor trip1
    int countsTrip2=9037; //create variables that will store the number of countsfor trip1
    double wheelDiameter=27.0,
    PI=3.14159,
    feetPerMile=5280,
    inchesPerFoot=12,
    secondsPerMinute=60;
    double distanceTrip1, distanceTrip2,totalDistance;
    System.out.println("Trip 1 took "+(secsTrip1/secondsPerMinute)+"minutes and had "+countsTrip1+" counts.");
    System.out.println("Trip 2 took "+(secsTrip2/secondsPerMinute)+"minutes and had "+countsTrip2+" counts.");
    distanceTrip1=countsTrip1*wheelDiameter*PI;
    distanceTrip1/=inchesPerFoot*feetPerMile;
    distanceTrip2=countsTrip2*wheelDiameter*PI/inchesPerFoot/feetPerMile;
    totalDistance=distanceTrip1+distanceTrip2;
    System.out.println("Trip 1 was "+distanceTrip1+" miles");
    System.out.println("Trip 2 was "+distanceTrip2+" miles");
    System.out.println("The total distance was "+totalDistance+" miles");
    } //end of main method
} //end of class