package RechercheMotif;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Conversion.ConvertSequence;

public class Naif extends AlgoRecherche{
	
	public Naif(char[] sequence, char[] motif) {
		super(sequence, motif);
		this.occurences = new ArrayList<Integer>();
	}
	
	
	public void calcul(){
		//System.out.println("la");
		boolean trouve = true;
		for(int i=0;i<this.sequence.length-this.motif.length+1;i++){
			//System.out.println(this.sequence[i]);
			trouve = true;
			for(int j=0;j<this.motif.length;j++){
				//System.out.println(this.motif[j]);
				if(this.sequence[i+j] != this.motif[j]){
					//System.out.println("ici");
					trouve = false;
				}
			}
			if(trouve == true){
				this.occurences.add(i);
			}
		}
	}
	
	public void calculTous(int n){
		try {
			PrintWriter p = new PrintWriter("file.txt", "UTF-8");
			for(int i=0;i<this.sequence.length;i++){
				char[] motif_n = new char[n];
				for(int j=0;j<n;j++){
					motif_n[j] = this.sequence[i];
				}
				ConvertSequence seq = new ConvertSequence(motif_n);
				this.setMotif(motif_n);
				this.calcul();
				for(int k = 0;k<this.occurences.size();k++){
					p.println(i + " " + this.occurences.get(i));
				}
			/*	this.setMotif(seq.getReverse());
				this.calcul();
				for(int k = 0;k<this.occurences.size();k++){
					p.println(i + " " + this.occurences.get(i));
				}
				this.setMotif(seq.getComp());
				this.calcul();
				for(int k = 0;k<this.occurences.size();k++){
					p.println(i + " " + this.occurences.get(i));
				}*/
			p.close();

			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	public void preCalcul() {		
	}
}
