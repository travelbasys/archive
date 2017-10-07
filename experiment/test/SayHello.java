package test;

public class SayHello {

	private String peterName;

	public static void main(String[] args) {

//		SayHello sey = new SayHello();
//		sey.doSayHello("Peter");
//		sey.doSayHello("Angelo");

		SayHello seyPeter = new SayHello("Peter");
		seyPeter.doSayHello();
	}

	public SayHello() {
	}

	public SayHello(String string) {
		peterName = string;
	}

	public SayHello(String string, String string2) {

	}

	public void doSayHello(String Name) {
		System.out.println("Say hello." + Name);
	}

	
	public void doSayHello() {
		System.out.println("Say hello." + peterName);
	}
	
}
