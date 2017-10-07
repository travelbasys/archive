package test;

import java.io.IOException;

public class TestString {

	public static void main(String[] args) {

	try{
		System.out.println("start testing \n");
		String test= null;
		test.length();
		
	} catch (Exception e) {
		System.err.println("Exception: " + e);
	}
	
		/* 
		String text= "test {*filename*} test";
		text = text.replaceAll("\\{\\*filename\\*\\}", "url");
		
		System.out.println(text);
		
		try {
			
			
			String firma = "1234";
			int firmaInt = Integer.parseInt(firma);
			firma = String.valueOf(firmaInt);
			System.out.println("firma: " + firma);
			
			String firma1 = "1234";
			int firma1Int = Integer.parseInt(firma1);
			firma1 = String.valueOf(firma1Int);
			System.out.println("firma1: " + firma1);
			
			
			System.out.println("Sume: " + (firmaInt + firma1Int));
			
			
			
	
			Experimente.staticMethod2();
			Experimente.staticMethod3();
			
			Experimente experiment = new Experimente();
			experiment.staticMethod4();
			
			String temp = "test.pdf.";
			
			if(temp.contains(".pdf")){
				System.out.println("temp contains .pdf");
			}
			
			
			if(temp.endsWith(".pdf")){
				System.out.println("temp endsWith .pdf");
			}
			
			System.out.println("length: " + temp.length());
			
			System.out.println("substring(5, 8): " + temp.substring(5, 8));
			
			System.out.println("substring (5): " + temp.substring(5));
			
			System.out.println("charAt(5): " + temp.charAt(5));
			
			System.out.println("substring(0, 8): " + temp.substring(0, 8));
			
			System.out.println("substring(0, 8): " + temp.substring(0, temp.length()-1));
			
			temp += "...";
			System.out.println("temp.isEmpty: " + temp.isEmpty());
			
			
			System.out.println("before temp: " + temp);
			
			temp = temp.replaceAll("\\.", "");
			System.out.println("after temp: " + temp);
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		*/
	}

}
