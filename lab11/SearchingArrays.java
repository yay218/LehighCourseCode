import java.util.Scanner;
public class SearchingArrays{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int array1[] = new int[50];
		int array2[] = new int[50];
		for(int i = 0; i<50; i++){
			int randomInt1 = (int)(Math.random()*101);
			array1[i] =  randomInt1;
		}
		int max1 = array1[0];
		int min1 = array1[0];
		for(int i = 0; i<50; i++){
			if(array1[i]>max1){
				max1 = array1[i];
			}
			if(array1[i]<min1){
				min1 = array1[i];
			}
		}
		System.out.println("The maximum of array1 is: "+max1);
		System.out.println("The minimum of array1 is: "+min1);
		
		for(int i = 0; i<50; i++){
			int randomInt2 = (int)(Math.random()*101);
			if(i==0){
				array2[i] = randomInt2;
			}
			else{
				array2[i] = array2[i-1]+randomInt2;
			}
		}
		int max2 = array2[0];
		int min2 = array2[0];
		for(int i = 0; i<50; i++){
			if(array2[i]>max1){
				max2 = array2[i];
			}
			if(array2[i]<min2){
				min2 = array2[i];
			}
		}
		System.out.println("The maximum of array2 is: "+max2);
		System.out.println("The minimum of array2 is: "+min2);
		System.out.print("Enter an int: ");
		int integer;
		if(!in.hasNextInt()){
			System.out.println("You did not enter an int.");
			return;
		}
		else{
			integer = in.nextInt();
		}
		if(integer<0){
			System.out.println("You did not enter an int>=0.");
			return;
		}
		if(array2[25]>integer){
			for(int i = 24;i>=0;i--){
				if(array2[i]==integer){
					System.out.println(integer+" was found in array2 ["+i+"]");
					break;
				}
				else if(array2[i]<integer){
					System.out.println(integer+" was not found in the list.");
					System.out.println("The number above the key is "+array2[i+1]);
					System.out.println("The number above the key is "+array2[i]);
					break;
				}
			}
		}
		else if(array2[25]<integer){
			for(int i = 26;i<=50;i++){
				if(array2[i]==integer){
					System.out.println(integer+" was found in array2 ["+i+"]");
					break;
				}
				else if(array2[i]>integer){
					System.out.println(integer+" was not found in the list.");
					System.out.println("The number above the key is "+array2[i]);
					System.out.println("The number above the key is "+array2[i-1]);
					break;
				}
			}
		}
		else{
			System.out.println(integer+" was found in array2 [25]");
		}
	}
}