package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FastaParser {
	
	private String fileName;
	private String fileContent;
	private char[] sequence;
	
	public FastaParser(String fileName){

		this.fileName = fileName;
	
	}
	
	public char[] readFile(){
		fileContent = "";
		try {
			BufferedReader buff = new BufferedReader(new FileReader(this.fileName));
			String line = buff.readLine(); //File description
			
			while((line = buff.readLine()) != null){
				fileContent += line;
			}
			sequence = fileContent.toCharArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.sequence;
	}
}
