package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestReadFile {

	public static void main(String[] args) {

		String filePathName = "C:/tba/NaturalONE/Workspaces/mru/Achive/Archive/resources/mySQLStatement.txt";
		try {
			FileReader fileReader = new FileReader(filePathName);
			BufferedReader bufferReader = new BufferedReader(fileReader);

//			String line = bufferReader.readLine();
//			while (line != null){
//				System.out.println(line);	
//				line = bufferReader.readLine();
//			}
//			
			String line ="";
			while ((line = bufferReader.readLine()) != null){
				System.out.println(line);	
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
