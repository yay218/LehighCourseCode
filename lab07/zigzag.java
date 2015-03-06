import java.util.Scanner;
public class zigzag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner myScanner = new Scanner(System.in);
		int x;
		String y;
		while(true){
			System.out.print("Enter an int between 3 and 33: ");
			if (myScanner.hasNextInt()){
				x = myScanner.nextInt();
				if (x>=3&&x<=33){
					for (int i=1;i<x;i++){
						System.out.print("*");
					}
					for (int i=1;i<(x-1);i++){
						System.out.println("*");
						for (int j=1;j<=i;j++){
						System.out.print(" ");
						}
					}
					System.out.println("*");
					for (int i=1;i<=x;i++){
						System.out.print("*");
					}
				}
				else{
					System.out.println("The number is out of range [3,33].");
					break;
				}
			}
			else{
				System.out.println("The number is out of range [3,33].");
				break;
			}
			System.out.println();
			System.out.print("Enter y or Y to go again: ");
			y = myScanner.next();
			if (y.equals("y")||y.equals("Y")){
				continue;
			}
			else{
				break;
			}
		}
	}
}
