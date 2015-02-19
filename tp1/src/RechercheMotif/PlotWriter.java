package RechercheMotif;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class PlotWriter {
	
	private PrintWriter p;
	
	
	public PlotWriter(String file) throws FileNotFoundException, UnsupportedEncodingException{
		this.p = new PrintWriter(file, "UTF-8");
		
	}
	
	public void writePlot(int length, List<Integer> occurences){
		int total = 0;
		for(int i = 0; i < length; i++){
			for(int j = 0; j<occurences.size(); j++){
				if((i*j)%100 == 0) {
					p.println(i + " " + total/100);
					System.out.println(i + " " + total/100);
					total = 0;
				} else {
					total += occurences.get(j);
				}
			}
		}
		
		p.close();
		
	}

}
