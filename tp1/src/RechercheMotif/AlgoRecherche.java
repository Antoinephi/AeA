package RechercheMotif;

import java.util.ArrayList;
import java.util.List;

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
	
	public abstract void preCalcul();
	
	public abstract void calcul();
	
}
