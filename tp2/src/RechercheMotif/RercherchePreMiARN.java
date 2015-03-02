package RechercheMotif;

public class RercherchePreMiARN {

	char[] sequence;
	
	
	public RercherchePreMiARN(char[] sequence){
		this.sequence = sequence;
	}
	
	
	public void calcul(){
		for(int i = this.sequence.length - (this.sequence.length/2+1), j = this.sequence.length/2; i >= 0 && j <= this.sequence.length; i--, j++){
			
			System.out.println(this.sequence[i] + ":" + i + " " + this.sequence[j] + ":"+j);
			if(this.sequence[j] == this.sequence[i])
				System.out.println("paire");
			
		}		
	}
	
	public void algoNeedlemanAndWunsch(){
		int[][] table = new int[this.sequence.length/2][this.sequence.length/2];
		table[0][0] = 0;
		for(int i=1;i<table.length;i++){
			table[i][0] = -2*i;
			table[0][i] = -2*i;
		}
		for(int j=1;j<table.length;j++){
			for(int k=1;k<table.length;k++){
			//int sub = table[j-1][k-1] + ???;
			}
		}
	}
	
	
	public static void main(String[] args) {
		char[] sequence = {'A', 'G', 'C', 'U', 'T', 'R', 'L'};
		RercherchePreMiARN algo = new RercherchePreMiARN(sequence);
		algo.calcul();
		
	}
	
}
