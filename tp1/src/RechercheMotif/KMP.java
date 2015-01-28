package RechercheMotif;

public class KMP {

	char[] sequence;
	public int[] next;
	char[] motif;

	public KMP(char[] sequence, char[] motif) {
		this.sequence = sequence;
		this.motif = motif;
	}

	public void calculNext() {
		this.next = new int[this.motif.length+1];
		this.next[0] = -1;
		this.next[1] = motif[0] == motif[1] ? -1 : 0;
		int j;
		int estTrouve = 0;
		int estUnBord = 0;
		for (int i = 2; i < this.next.length; i++) {
			estTrouve = 0;
			j = i - 1;
			while ((j >= 0) && (estTrouve == 0)) {
				estUnBord = 1;
				for (int k = 0; k <= j; k++) {
					if (this.motif[k] != this.motif[i-j+k-1]) {
						estUnBord = 0;
						if(i==6){
						}
					}
				}
				if (estUnBord == 1 && i <next.length-1) {
					if (this.motif[j + 1] != this.motif[i]) {
						estTrouve = 1;
					}
				} else 
					if(estUnBord == 1 && i == next.length-1){
						if(j!=i-1)
							estTrouve = 1;
					}
				j--;
			}
			if (j == -1 && i < next.length-1) {

				if (this.motif[0] == this.motif[i]) {
					j = -3;
				} else {
					j = -2;
				}
			} else 
				{
					if(j == -1 && i == next.length-1){
						j = -2;
					}
				}
			this.next[i] = j + 2;
		}
	}
	
	public void algo(){
		int nbRes = 0;
		int indice = 0;
		for (int i = 0; i < this.sequence.length; i++) {
			/* Cas où la lettre suivante correspond par rapport au motif */
			if (this.sequence[i] == this.motif[indice]) {
				System.out.println("indice : " +  indice + " lettre : " + this.sequence[i]);
				if(indice == this.motif.length-1){
					nbRes++;
					System.out.println(nbRes + "e occurence : " + (i - indice+1) + "->" + i);
				}
				indice = (indice+1) % (this.motif.length);
			} else {
				/* Cas où la lettre ne correspond pas au motif */
				i -= this.next[indice] + 1;
				indice = 0;
			}
		}
		System.out.println(nbRes);
	}

	public static void main(String[] args) {
		char[] motif = {'T','A','C','T'};
		char[] sequence = {'T','A','C','T','A','G','A','T','A','C','T','A','C','T','A','C','T'};
		KMP recherche = new KMP(sequence,motif);
		recherche.calculNext();
		recherche.algo();
		for(int i=0;i<recherche.next.length;i++){
			System.out.print(recherche.next[i]+" ");
		}
		
		
		
	}
}
