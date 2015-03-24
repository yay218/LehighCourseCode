import java.util.Scanner;
public class HW8{
	public static void main(String[] args) {
        char option;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to MG's adventure world. Now your journey begins. Good luck!");
        System.out.println("Please hit 'C' or 'c' to continue, anything else to quit-");
        String input = getInput(scan, "Cc");
        System.out.println("You are in a dead valley.");
        System.out.println("Please hit 'C' or 'c' to continue, anything else to quit-");
        input = getInput(scan, "Cc");
        System.out.println("You walked and walked and walked and you saw a cave!");
        cave();
        System.out.println("Please hit 'C' or 'c' to continue, anything else to quit-");
        input = getInput(scan, "Cc");
        System.out.println("You entered a cave!");
        System.out.println("Please hit 'C' or 'c' to continue, anything else to quit-");
        input = getInput(scan, "Cc");
        System.out.println("Unfortunately, you ran into a GIANT!");
        giant();
        System.out.println("Enter 'A' or 'a' to Attack, 'E' or 'e' to Escape, ANYTHING else is to YIELD");
        input = getInput(scan, "AaEe", 10);
        System.out.println("Congratulations! You SURVIVED! Get your REWARD!");
        System.out.println("There are three 3 tressure box in front of you! Enter the box number you want to open!");
        box();
        input = getInput(scan);
        System.out.println("Hero! Have a good day!");
    }
	public static void giant() {
        System.out.println("                                 ---------                    ");
        System.out.println("                                |  /    \\|                   ");
        System.out.println("                      ZZZZZH    |    O    |    HZZZZZ             ");
        System.out.println("                           H     -----------   H              ");
        System.out.println("                      ZZZZZHHHHHHHHHHHHHHHHHHHHHZZZZZ                   ");
        System.out.println("                           H    HHHHHHHHHHH    H                      ");
        System.out.println("                      ZZZZZH    HHHHHHHHHHH    HZZZZZ               ");
        System.out.println("                                HHHHHHHHHHH                   ");
        System.out.println("                                HHH     HHH                   ");
        System.out.println("                               HHH       HHH                   ");
    }

    public static void cave() {
        System.out.println("                              *****   * * * * * * * *        ");
        System.out.println("                         ***         ***                      ");
        System.out.println("                      ***               ***                   ");
        System.out.println("                 |    ***               ***                   ");
        System.out.println("                 |    ***               ***                   ");
        System.out.println("             O __|__  ***               ***                   ");
        System.out.println("           ******l    ***               ***                   ");
        System.out.println("            * *       ***               ***                   ");
        System.out.println("           *   *      ********************* * * * * * *       ");
    }
    
    public static void box(){
       System.out.println("                      *********************     *********************    *********************             ");
        System.out.println("                     ***               ***     ***               ***    ***               ***              ");
        System.out.println("                     ***       1       ***     ***       2       ***    ***       3       ***              ");
        System.out.println("                     ***               ***     ***               ***    ***               ***              ");
        System.out.println("                     *********************     *********************    *********************               "); 
    }
    public static String getInput(Scanner scan, String string){
    	String input = scan.nextLine();
    	if (input.equals("C")||input.equals("c")){
    		return input;
    	}
    	else{
    		System.out.println("Yea right LOSER!");
    		return "";
    	}
    }
    public static String getInput(Scanner scan, String string, int trial){
    	String input = scan.next();
    	while (input.equals("A")||input.equals("a")||input.equals("E")||input.equals("e")){
	    	if (input.equals("A")||input.equals("a")){
	    		int random1 = (int) (Math.random()*2);
	    		if (random1 == 0){
	    			System.out.println("Critical Hit!");
	    			trial--;
	    			if(trial == 0){
	    				return "";
	    			}
	    		}
	    		else{
	    			System.out.println("Gosh! How can you miss it!");
	    		}
	    		System.out.println("Enter 'A' or 'a' to Attack, 'E' or 'e' to Escape, ANYTHING else is to YIELD");
	    		input = scan.next();
	    	}
	    	else{
	    		int random2 = (int) (Math.random()*10+1);
	    		if (random2 == 10){
	    			System.out.println("You escaped successfully.");
	    			return "";
	    		}
	    		else{
	    			System.out.println("Enter 'A' or 'a' to Attack, 'E' or 'e' to Escape, ANYTHING else is to YIELD");
		    		input = scan.next();
	    		}
	    	}
    	}
    	if (!input.equals("A")||!input.equals("a")||!input.equals("E")||!input.equals("e")){
    		System.out.println("Executed by the GIANT! Game Over!");
    		System.exit(0);
    		return "";
    	}
    	return "";
    }
    public static String getInput(Scanner scan){
    	int input = scan.nextInt();
    	switch (input){
    	case 1:
    		System.out.println("You get 1 dollar.");
    		return "";
    	case 2:
    		System.out.println("You get 10 dollars.");
    		return "";
    	case 3:
    		System.out.println("You get 100 dollars.");
    		return "";
    	default:
    		System.out.println("A Wrong Number! You get nothing! Better restart the game LOL.");
    		System.exit(0);
    		return "";
    	}
    }
}