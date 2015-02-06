import java.util.Scanner;
public class Root {
    public static void main (String[] args) {
    	Scanner myScanner;
        myScanner = new Scanner(System.in);
        System.out.print("Enter a double, and I print its cube root: ");
        Double x = myScanner.nextDouble();
        Double guess = x/3;
        Double guess1,guess2,guess3,guess4,guess5;
        guess1 = (2*guess*guess*guess+x)/(3*guess*guess);
        guess2 = (2*guess1*guess1*guess1+x)/(3*guess1*guess1);
        guess3 = (2*guess2*guess2*guess2+x)/(3*guess2*guess2);
        guess4 = (2*guess3*guess3*guess3+x)/(3*guess3*guess3);
        guess5 = (2*guess4*guess4*guess4+x)/(3*guess4*guess4);
	    System.out.println("The cube root is "+guess5+": "+guess5+"*"+guess5+"*"+guess5+"="+(guess5*guess5*guess5));
    }
}