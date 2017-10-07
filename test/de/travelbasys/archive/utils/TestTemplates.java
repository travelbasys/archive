package de.travelbasys.archive.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;


public class TestTemplates {

	public static void main(String[] args) {
		String text;
		try {
			text = Templates
					.ReadFile( "ShowFile.html");
			System.out.println("text: " + text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// "<html><body> <object width='800'
		// height='800'data='{*filename*}'></object></body></html>";
		
	}

}
