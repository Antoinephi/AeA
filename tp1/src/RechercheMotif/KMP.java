package RechercheMotif;

import java.util.ArrayList;
import java.util.List;

public class KMP extends AlgoRecherche{
	
	private int next[];
	private int nbRes;
	
	public KMP(char[] sequence, char[] motif) {
		super(sequence, motif);
		this.nbRes = 0;
	}

	public void setMotif(char[] motif){
		this.motif = motif;
	}
	
	public void preCalcul() {
		this.next = new int[this.motif.length + 1];
		this.next[0] = -1;
		if(this.motif.length > 1)
			this.next[1] = motif[0] == motif[1] ? -1 : 0;
		else
			return;
		int j;
		int estTrouve = 0;
		int estUnBord = 0;
		for (int i = 2; i < this.next.length; i++) {
			estTrouve = 0;
			j = i - 1;
			while ((j >= 0) && (estTrouve == 0)) {
				estUnBord = 1;
				for (int k = 0; k <= j; k++) {
					if (this.motif[k] != this.motif[i - j + k - 1]) {
						estUnBord = 0;
						if (i == 6) {
						}
					}
				}
				if (estUnBord == 1 && i < next.length - 1) {
					if (this.motif[j + 1] != this.motif[i]) {
						estTrouve = 1;
					}
				} else if (estUnBord == 1 && i == next.length - 1) {
					if (j != i - 1)
						estTrouve = 1;
				}
				j--;
			}
			if (j == -1 && i < next.length - 1) {

				if (this.motif[0] == this.motif[i]) {
					j = -3;
				} else {
					j = -2;
				}
			} else {
				if (j == -1 && i == next.length - 1) {
					j = -2;
				}
			}
			this.next[i] = j + 2;
		}
	}

	public void calcul() {
		int indice = 0;
		this.preCalcul();
		for (int i = 0; i < this.sequence.length; i++) {
			/* Cas où la lettre suivante correspond par rapport au motif */
			if (this.sequence[i] == this.motif[indice]) {
//				System.out.println("indice : " + indice + " lettre : "
//						+ this.sequence[i]);
				if (indice == this.motif.length - 1) {
					this.nbRes++;
//					System.out.println(this.nbRes + "e occurence : "
//							+ (i - indice + 1) + "->" + i);
					/**Position de l'occurence**/
					this.occurences.add(i-indice+1);
				}
				indice = (indice + 1) % (this.motif.length);
			} else {
				/* Cas où la lettre ne correspond pas au motif */
				i -= this.next[indice] + 1;
				indice = 0;
			}
		}
	}

	/*
	 * public void test() { this.next = new int[this.motif.length + 1];
	 * this.next[0] = -1; this.next[1] = (motif[0] == motif[1]) ? -1 : 0;
	 * boolean ok = false; int tailleBord = 0; int bordMax = 0; String u = "";
	 * 
	 * for(int j = 2; j <this.motif.length+1; j++){
	 * System.out.println("------------ Indice " + j + " : lettre = " +
	 * motif[j%motif.length]); bordMax = 0; tailleBord = 0; u = ""; for (int i =
	 * (j / 2)-1; i >= 0; i--) { System.out.println("motif[" + i + "] == motif["
	 * + (j - (j/2) + i) + "] => " + motif[i] + " == " + motif[j - (j/2) + i]);
	 * 
	 * if (motif[i] == motif[j - (j/2) + i]){ tailleBord++; bordMax =
	 * (tailleBord >= bordMax) ? tailleBord : bordMax; u += motif[i]; } else {
	 * tailleBord = 0; break; } } for(int i = 0; i <u.length(); i++){
	 * if(motif[i] != u.charAt(i)) break; else bordMax = (motif[i] ==
	 * u.charAt(0)) ? -1 : 0; }
	 * 
	 * next[j] = bordMax; System.out.println(bordMax); for(int i = 0; i <
	 * bordMax; i++){ System.out.print(this.motif[i] + " "); } } }
	 */

	/*public static void main(String[] args) {
		char[] motif = { 'T', 'A', 'C', 'T', 'A', 'G','A'};
		char[] sequence = { 'T', 'A', 'C', 'T', 'A', 'G', 'A', 'T', 'A', 'C',
				'T', 'A', 'C', 'T', 'A', 'C', 'T' };
		KMP recherche = new KMP(sequence, motif);

		recherche.calculNext();
		recherche.algo();
		for (int i = 0; i < recherche.next.length; i++) {
			System.out.print(recherche.next[i] + " ");
		}

	}*/
}
