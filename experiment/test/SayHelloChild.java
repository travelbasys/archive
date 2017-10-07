package test;

public class SayHelloChild  extends SayHello{

	public static void main(String[] args) {

		SayHelloChild sey = new SayHelloChild();
		sey.doSayHello("Peter");
		sey.doSayHelloBrother("Angelo");
	}

	public SayHelloChild() {
	}
	
	protected void doSayHelloBrother(String Name) {
		System.out.println("Say hello." + Name);
	}
	
}
