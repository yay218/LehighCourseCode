import java.util.Scanner;
public class FourDigits {
    public static void main (String[] args) {
    	Scanner myScanner;
        myScanner = new Scanner(System.in);
        System.out.print("Enter a double and I display the four digits to the right of the decimal point: ");
        double number = myScanner.nextDouble();
        double number1;
        int number2,digit1,digit2,digit3,digit4;
        number1 = number*10000;
        number2 = (int)number1;
	    digit4=number2%10;
	    digit3=(number2%100)/10;
	    digit2=(number2%1000)/100;
	    digit1=(number2%10000)/1000;
	    System.out.println("The four digits are "+digit1+""+digit2+""+digit3+""+digit4);
    }
}