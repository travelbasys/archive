package test.interface1;

public class TestClasses {

	public static void main(String[] args) {

		TestInterface testInterface = new TestImpl1();

		String returnValue = testInterface.TestMethod();

		System.out.println(returnValue);
		System.out.println(testInterface instanceof TestImpl1);

		testInterface = new TestImpl2();

		returnValue = testInterface.TestMethod();

		System.out.println(returnValue);
		System.out.println(testInterface instanceof TestImpl2);

	}

}
