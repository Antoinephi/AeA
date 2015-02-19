package RechercheMotif;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Conversion.ConvertSequence;

public abstract class AlgoRecherche implements Algorithme {

	protected char[] sequence;
	protected char[] motif;
	protected List<Integer> occurences;
	
	
	public AlgoRecherche(char[] sequence, char[] motif){
		this.sequence = sequence;
		this.motif = motif;
		occurences = new ArrayList<Integer>();
	}
	
	public void setMotif(char[] motif){
		this.motif = motif;
	}
	
	public void resetListOccurences(){
		this.occurences = new ArrayList<Integer>();
	}
	
	public List<Integer> getListOccurences(){
		return this.occurences;
	}
	
	public void calculTous(int n){
		try {
			//PrintWriter p = new PrintWriter("file.txt", "UTF-8");
			for(int i=0;i<this.sequence.length-this.motif.length-1;i++){
				this.resetListOccurences();
				char[] motif_n = new char[n];
				for(int j=0;j<n;j++){
					motif_n[j] = this.sequence[i+j];
				}
				ConvertSequence seq = new ConvertSequence(motif_n);
				this.setMotif(motif_n);
				this.calcul();
				PlotWriter p = new PlotWriter("file_test.txt");
				p.writePlot(this.sequence.length-this.motif.length-1, occurences);
				/*for(int k = 0;k<this.occurences.size();k++){
					System.out.println(i + " " + this.occurences.get(k));
					p.println(i + " " + this.occurences.get(k));
				}*/
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

			}
			//p.close();

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void preCalcul();
	
	public abstract void calcul();
	
}
