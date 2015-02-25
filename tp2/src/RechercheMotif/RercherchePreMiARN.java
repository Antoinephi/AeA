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
	
	
	public static void main(String[] args) {
		char[] sequence = {'A', 'G', 'C', 'U', 'T', 'R', 'L'};
		RercherchePreMiARN algo = new RercherchePreMiARN(sequence);
		algo.calcul();
		
	}
	
}
