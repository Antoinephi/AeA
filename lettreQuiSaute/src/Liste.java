
/**
 * Liste chainée d'entiers
 *
 */
public class Liste {
	//Entier courant
	private int element;
	//Element suivant de la liste
	private Liste next;
	
	/**
	 * Constructeur :
	 * @param element : entier courant
	 * @param next : element suivant
	 */
	public Liste(int element, Liste next){
		this.element = element;
		this.next = next;
	}
	
	/**
	 * Getter de la valeur courante de la liste chainee
	 * @return element
	 */
	public int getElement(){
		return this.element;
	}
	
	/**
	 * Retourne l'élément suivant de la liste chainee
	 * @return
	 */
	public Liste getNextElement(){
		return this.next;
	}
	
	
	
}
