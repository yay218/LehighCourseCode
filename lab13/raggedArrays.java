
public class raggedArrays{
	public static void main(String[] args){
		int[][] array=new int[5][];
		for(int i=0;i<array.length;i++){
			array[i]=new int[i*3+5];
			for(int j=0;j<array[i].length;j++){
				array[i][j]=(int)(Math.random()*40);
			}
		}
		System.out.println("The array before sorting");
		for (int i=0;i<array.length;i++){
			System.out.print("row "+(i+1)+" : ");
			for(int j=0;j<array[i].length;j++){
				System.out.printf("%3d",array[i][j]);
			}
			System.out.println();
		}
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				int min = array[i][j];
				int index=j;
				for(int k=j+1;k<array[i].length;k++){
					if(array[i][k]<min){
						index=k;
						min=array[i][k];
					}
				}
				int temp=array[i][j];
				array[i][j]=array[i][index];
				array[i][index]=temp;
			}
		}
		System.out.println("The array after sorting");
		for (int i=0;i<array.length;i++){
			System.out.print("row "+(i+1)+" : ");
			for(int j=0;j<array[i].length;j++){
				System.out.printf("%3d",array[i][j]);
			}
			System.out.println();
		}
		int [][] newArray= new int[5][17];
		for(int i=0;i<5;i++){
			System.arraycopy(array[i],0,newArray[i],0,array[i].length);
		}
		System.out.println("The array after sorting and copying");
		for (int i=0;i<newArray.length;i++){
			System.out.print("row "+(i+1)+" : ");
			for(int j=0;j<newArray[i].length;j++){
				System.out.printf("%3d",newArray[i][j]);
			}
			System.out.println();
		}
	}
}