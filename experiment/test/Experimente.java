package test;

public class Experimente {

	public static void main(String[] args) {
		// staticMethod();
		//
		// Experimente experimente = new Experimente();
		// experimente.normalMethod();

		String temp = "wittek";

		if (temp.equals("witt")) {
			System.out.println("witt");
		} else if (temp.equals("wittek")) {
			System.out.println("wittek");
		} else {
			System.out.println("either witt nor wittek");
		}
		
		if (temp.equals("witt")) {
			System.out.println("witt");
		} else {
			if (temp.equals("wittek")) {
				System.out.println("wittek");
			} else {
				System.out.println("either witt nor wittek");
			}
		}
	
		
		switch (temp) {
		case "witt":
		case "wittek":
			System.out.println("wittek");
			break;
		default:
			System.out.println("either witt nor wittek");
			break;
		}
		
		if (temp.equals("wittek") || temp.equals("witt")) {
			System.out.println("wittek");
		} else {
			System.out.println("either witt nor wittek");
		}
		
		while (temp.equals("wittek")) {
			System.out.println("wittek");
			temp="";
		}
		

		temp="wittek";
		for (int i = 0; i == temp.length(); i++) {
			System.out.println(temp.charAt(i));
//			System.out.println(temp.indexOf(String.valueOf(i)));
		}
		
		System.out.println("********************");
		
		String[] arrayOfStrings = new String[]{"witt", "witte","wittek"};
		
		for (String string : arrayOfStrings) {
			System.out.println(string);
		}
	}

	void normalMethod() {
		System.out.println("normalMethod");
	}

	// Nur intern
	private static void staticMethod() {
		System.out.println("normalMethod");
	}

	// Für Inheritence erreichbar
	protected static void staticMethod1() {
		System.out.println("normalMethod");
	}

	// Im Package erreichbar
	static void staticMethod2() {
		System.out.println("normalMethod");
	}

	// Im Projekt erreichbar
	public static void staticMethod3() {
		System.out.println("normalMethod");
	}

	// Im Projekt erreichbar /NON static
	public void staticMethod4() {
		System.out.println("normalMethod");
	}
}
