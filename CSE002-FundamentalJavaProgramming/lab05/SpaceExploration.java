public class SpaceExploration {

	public static void main(String[] args) {
		int year =(int)(Math.random()*11) + 2000;
		String x0="2000: First spacecraft orbits an asteroid";
		String x1="2001: First spacecraft lands on asteroid";
		String x2="2002: N/A";
		String x3="2003: Largest infrared telescope released";
		String x4="2004: N/A";
		String x5="2005: Spacecraft collies with comet";
		String x6="2006: Spacecraft returns with collections from a comet";
		String x7="2007: N/A";
		String x8="2008: Kepler launched to study deep space";
		String x9="2009: N/A";
		String x10="2010: SpaceX sucessfully sends spacecraft to orbit and back";
		System.out.println("Here is a timeline of space exploration events from "+year+" to 2000: ");
		switch(year){
		case 2000:
			System.out.println(x0);
			break;
		case 2001:
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2002:
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2003:
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2004:
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2005:
			System.out.println(x5);
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2006:
			System.out.println(x6);
			System.out.println(x5);
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2007:
			System.out.println(x7);
			System.out.println(x6);
			System.out.println(x5);
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2008:
			System.out.println(x8);
			System.out.println(x7);
			System.out.println(x6);
			System.out.println(x5);
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2009:
			System.out.println(x9);
			System.out.println(x8);
			System.out.println(x7);
			System.out.println(x6);
			System.out.println(x5);
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		case 2010:
			System.out.println(x10);
			System.out.println(x9);
			System.out.println(x8);
			System.out.println(x7);
			System.out.println(x6);
			System.out.println(x5);
			System.out.println(x4);
			System.out.println(x3);
			System.out.println(x2);
			System.out.println(x1);
			System.out.println(x0);
			break;
		}
	}
}
