package Conversion;

public class ConvertSequence {
	
	char[] seqInitiale;
	char[] seqReverse;
	char[] seqComp;
	char[] seqReverseComp;
	
	public ConvertSequence(char[] seq){
		this.seqInitiale = seq;
		this.seqReverse = reverse(seqInitiale);
		this.seqComp = comp();
		this.seqReverseComp = reverse(seqComp);
	}
	
	public char[] getReverse(){
		return this.seqReverse;
	}
	
	public char[] getComp(){
		return this.seqComp;
	}
	
	public char[] getReverseComp(){
		return this.seqReverseComp;
	}
	
	public char[] reverse(char[] seqInitiale){
		char[] reverse = new char[seqInitiale.length];
		for (int i = 0; i < seqInitiale.length; i++) {
			reverse[seqInitiale.length-i-1] = seqInitiale[i];
		}
		
		return reverse;
	}
	
	public char[] comp(){
		char c;
		char[] comp = new char[seqInitiale.length];
		for (int i = 0; i < seqInitiale.length; i++) {
			c = seqInitiale[i];
		
			switch(c){			
			case 'A':
				comp[i] = 'T';
				break;
			case 'T':
				comp[i] = 'A';
				break;
			case 'C':
				comp[i] = 'G';
				break;
			case 'G':
				comp[i] = 'C';
				break;
			default:
				break;
			}
		
	}
		
		return comp;
	}
	
	public String toString(){
		String str = "";
		for(int i=0; i<seqInitiale.length; i++){
			str += seqInitiale[i];

		}
		str += "\n";

		for(int i=0; i<seqInitiale.length; i++){
			str += seqReverse[i];

		}
		str += "\n";

		for(int i=0; i<seqInitiale.length; i++){
			str += seqComp[i];
		}
		str += "\n";

		for(int i=0; i<seqInitiale.length; i++){
			str += seqReverseComp[i];
		}
		str += "\n";
		return str;
	}
	
	public static void main(String[] args) {
		char[] seq = {'A','T','G','C','T','A', 'T','A'};
		ConvertSequence conv = new ConvertSequence(seq);
		System.out.println(conv);
	}
	
}
