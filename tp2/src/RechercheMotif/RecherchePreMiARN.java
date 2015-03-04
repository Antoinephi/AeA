package RechercheMotif;

public class RecherchePreMiARN {

	char[] sequence;
	
	
	public RecherchePreMiARN(char[] sequence){
		this.sequence = sequence;
	}
	
	
	public void calcul(){
		for(int i = this.sequence.length - (this.sequence.length/2+1), j = this.sequence.length/2; i >= 0 && j <= this.sequence.length; i--, j++){
			
			System.out.println(this.sequence[i] + ":" + i + " " + this.sequence[j] + ":"+j);
			if(this.sequence[j] == this.sequence[i])
				System.out.println("paire");
			
		}		
	}
	/**
	 * Verifie si les 2 nucléotides sont des complémentaires ou non via un parcours de tableau à 2 dimensions
	 * chaque dimension contenant un des complémentaires à l'indice i
	 * @param a représente le premier nucléotide
	 * @param b représente le second nucléotide
	 * @return true si a & b se retrouvent au même indice i de leurs tableaux respectifs, false sinon
	 */
	public boolean estUnAppariement(char a, char b){
		char[][] appariements = {{'A', 'C', 'G', 'U', 'G', 'U'}, {'U', 'G', 'U', 'A', 'C', 'G'}};
		for(int i = 0; i < appariements[0].length; i++){
			if(appariements[0][i] == a && appariements[1][i] == b)
				return true;
			}
			return false;
		}
	
	public void algoNeedlemanAndWunsch(){
		int[][] table;
		System.out.println(this.sequence.length/2+1);
		table = new int[(this.sequence.length/2)+1][(this.sequence.length/2)+1];
		int[][] tableI = new int[(this.sequence.length/2+1)][(this.sequence.length/2)+1];
		int[][] tableJ = new int[(this.sequence.length/2+1)][(this.sequence.length/2)+1];
		int sub, del, ins;
		table[0][0] = 0;
		/* Initialisation du tableau */
		for(int i=1;i<table.length;i++){
			table[i][0] = -2*i;
			table[0][i] = -2*i;
		}
		for(int i=1;i<table.length;i++){
			for(int j=1;j<table.length;j++){
				//sub = table[i-1][j-1] + (this.estUnAppariement(this.sequence[i], this.sequence[this.sequence.length-j]) ? 2 : -1);
				sub = table[i-1][j-1] + (this.estUnAppariement(this.sequence[table.length-i-1], this.sequence[table.length+j-2]) ? 2 : -1);
				del = table[i-1][j] -2;
				ins = table[i][j-1] -2;
				
				if(sub > del && sub > ins){
					table[i][j] = sub;
					tableI[i][j] = i-1;
					tableJ[i][j] = j-1;
				}
				else if(del > sub && del > ins){
					table[i][j] = del;
					tableI[i][j] = i-1;
					tableJ[i][j] = j;
				}
				else {
					table[i][j] = ins;
					tableI[i][j] = i;
					tableJ[i][j] = j-1;

				}
			}
		}
		for(int i=0;i<table.length;i++){
			for(int j=0;j<table.length;j++){
				System.out.print(table[i][j] + " | ");
			}
			System.out.println();
		
		}
		System.out.println();
		for(int i=0;i<table.length;i++){
			for(int j=0;j<table.length;j++){
				System.out.print("i:"+tableI[i][j]+" j:"+tableJ[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println();
		for(int i=0;i<table.length;i++){
			for(int j=0;j<table.length;j++){
				System.out.print("i:"+i+" j:"+j + " | ");
			}
			System.out.println();
		}
	}
	

	
	public static void main(String[] args) {
		char[] sequence = {'A','U','A','U','C','A'};
		/*char[] sequence1 = {'C','U','A','C','A','G','U','A','G','C','C','G','A','U','A'};
		char[] sequence2 = {'A','U','A','G','C','C','G','U','A','C','U','G','U','A','G'};
		char[] sequence3 = {'U','A','A'};*/
		RecherchePreMiARN algo = new RecherchePreMiARN(sequence);
		algo.algoNeedlemanAndWunsch();
		
	}
	
}
