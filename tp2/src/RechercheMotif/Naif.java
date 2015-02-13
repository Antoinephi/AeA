package RechercheMotif;

import java.util.ArrayList;
import java.util.List;

public class Naif {
	private char[] sequence;
	private int[] next;
	private char[] motif;
	private int nbRes;
	private List<Integer> occurences;
	
	public Naif(char[] sequence, char[] motif) {
		this.sequence = sequence;
		this.motif = motif;
		this.nbRes = 0;
		occurences = new ArrayList<Integer>();
	}
	
	public void setMotif(char[] motif){
		this.motif = motif;
	}
	
	public void algoNaif(){
		boolean trouve = true;
		for(int i=0;i<this.sequence.length;i++){
			trouve = true;
			for(int j=0;j<this.motif.length;j++){
				if(this.sequence[i+j] != this.motif[j]){
					trouve = false;
				}
			}
			if(trouve == true){
				this.occurences.add(i);
			}
		}
	}
}
