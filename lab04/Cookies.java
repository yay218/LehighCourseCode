import java.util.Scanner;
	public class Cookies{
	public static void main(String[ ] args) { 
	int need;
	Scanner myScanner = new Scanner(System.in);
	System.out.print("Enter the number of People: ");
	int x;
	if(myScanner.hasNextInt()){
	    x=myScanner.nextInt();
		if(x>0){
			System.out.print("Enter the number of cookies you are planning to buy: ");
		}
		else{
			System.out.print("You did not enter an int > 0");
		}
	}
	else {
		System.out.println("You did not enter an int");
		return;
	}
	int y=myScanner.nextInt();
	System.out.print("How many do you want each person to get? ");
	int z=myScanner.nextInt();
	if(x*z<y&&y%x==0){
		System.out.println("You have enough cookies for each person and the amount will divide evenly.");
	}
	else if(x*z<y&&y%x!=0){
		System.out.println("You have enough, but they will not divide evenly.");
	}
	else if(x*z>y){
		need = x*z-y;
		System.out.println("You will not have enough cookies. You need to buy at least "+need+" more.");
	}
  }   //end of main method
}  //end of class 

