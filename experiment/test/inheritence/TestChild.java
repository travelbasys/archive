package test.inheritence;

public class TestChild extends TestParent {

	public String testMethodChild (){
		return "TestChild";
	}
	
	public String testMethod (){
		return "TestChild.testMethod";
	}
	
	public static void main(String[] args) {
		TestChild testChild = new TestChild();
		System.out.println(testChild.testMethodParent());
		System.out.println(testChild.testMethodChild());
		System.out.println(testChild.testMethod());
		System.out.println(testChild.testMethodAbstract());
		
		TestParent testParent = new TestChild();
		System.out.println(testParent.testMethodParent());
		System.out.println(testChild.testMethod());
		System.out.println(testChild.testMethodAbstract());
	}

	@Override
	public String testMethodAbstract() {
		return "testMethodAbstract";
	}
}
