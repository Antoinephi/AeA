package RechercheMotif;

public class KMP {

	char[] sequence;
	public int[] next;
	char[] motif;
	
	
	public KMP(char[] sequence, char[] motif){
		this.sequence = sequence;
		this.motif = motif;
	}
	
	public void calculNext(){
		this.next = new int[this.motif.length];
		this.next[0] = -1;
		this.next[1] = motif[0] == motif[1] ? -1 : 0; 
		int j;
		int estTrouve = 0;
		for (int i = 2; i < this.next.length; i++) {
		j = i - 2;	
			while((j > 0) && (estTrouve == 0)){
					int estUnBord = 1;
					for(int k = 0; k <= j; k++){
						if(this.motif[k] != this.motif[i-k]){
							estUnBord = 0;
							break;
						}
					}
					if(estUnBord == 1){
						if(this.motif[j+1] == this.motif[i]){
							estTrouve = 1;
						}
					}
					j--;
			}
			this.next[i] = j+1;
		}
	}
	
	public static void main(String[] args) {
		char[] motif = {'T','A','C','T','A','G','A'};
		KMP recherche = new KMP(null,motif);
		recherche.calculNext();
		
		for(int i=0;i<recherche.next.length;i++){
			System.out.println(recherche.next[i]+" ");
		}
		
		
		
	}
}
