package RechercheMotif;

import java.util.List;

public class Naif extends AlgoRecherche{
	private char[] sequence;
	private char[] motif;
	private List<Integer> occurences;
	
	public Naif(char[] sequence, char[] motif) {
		super(sequence, motif);
	}
	
	public void setMotif(char[] motif){
		this.motif = motif;
	}
	
	
	public void calcul(){
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

	
	public void preCalcul() {		
	}
}
