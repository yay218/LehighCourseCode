import java.util.Random;
import java.util.Scanner;
public class StoryGeneration{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true){
			String story="The "+adjective()+subject()+verb()+"the "+adjective()+object()+".";
			System.out.println(story);
			System.out.print("Would you like another sentence?(y/n) ");
			String x=in.nextLine();
			if(x.equals("y")){
				continue;
			}
			else if(x.equals("n")){
				break;
			}
		}
    }
	public static String adjective(){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10);
		switch(randomInt){
		case 0:
			return "pretty ";
		case 1:
			return "quick ";
		case 2:
			return "slow ";
		case 3:
			return "lazy ";
		case 4:
			return "ugly ";
		case 5:
			return "handsome ";
		case 6:
			return "tall ";
		case 7:
			return "short ";
		case 8:
			return "fat ";
		case 9:
			return "thin ";
		}
		return "";
	}
	public static String subject(){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10);
		switch(randomInt){
		case 0:
			return "wife ";
		case 1:
			return "husband ";
		case 2:
			return "cat ";
		case 3:
			return "girl ";
		case 4:
			return "boy ";
		case 5:
			return "professor ";
		case 6:
			return "student ";
		case 7:
			return "friend ";
		case 8:
			return "pig ";
		case 9:
			return "horse ";
		}
		return "";
	}
	public static String verb(){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10);
		switch(randomInt){
		case 0:
			return "passed ";
		case 1:
			return "walked ";
		case 2:
			return "ran ";
		case 3:
			return "hit ";
		case 4:
			return "bit ";
		case 5:
			return "kicked ";
		case 6:
			return "loved ";
		case 7:
			return "kissed ";
		case 8:
			return "touched ";
		case 9:
			return "pushed ";
		}
		return "";
	}
	public static String object(){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10);
		switch(randomInt){
		case 0:
			return "duck";
		case 1:
			return "beauty";
		case 2:
			return "mother";
		case 3:
			return "father";
		case 4:
			return "sister";
		case 5:
			return "brother";
		case 6:
			return "grandmother";
		case 7:
			return "grandfather";
		case 8:
			return "dog";
		case 9:
			return "fox";
		}
		return "";
	}
}