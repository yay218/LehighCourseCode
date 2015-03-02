public class SmileGenerator {

	public static void main(String[] args) {
		
		System.out.print(":) :) :) :) :)");
		System.out.println();
		
		int n1=1;
		while (n1<=5){
			System.out.print(":) ");
			n1++;
		}
		System.out.println();
		
		int n2=1;
		do{
			System.out.print(":) ");
			n2++;
		}while (n2<=5);
		System.out.println();
		
		double x = Math.random()*100+1;
		double x1=100-x;
		while (x1<=100){
			System.out.print(":) ");
			x1++;
		}
		System.out.println();
		
		int y = (int)(Math.random()*100+1);
		int y1=100-y;
		int count1=0;
		while (y1<100){
			System.out.print(":) ");
			y1++;
			count1++;
			while(count1%30==0){
				System.out.println();
				break;
			}
		}
		System.out.println();
		
		String n = "";
		int count=1;
		while (count<=4){
			n=n+":) ";
			count++;
			System.out.println(n);
		}
		
	}

}
