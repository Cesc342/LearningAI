package all.motxila;

import java.util.Arrays;

import all.Util;

public class SimuladorMotxilla {
	
	public static final int CAPACITAT = 100; //grams
	public static final double SUPERVIVENCIA = 0.3; // -> eleccoiNatural()
	public static final double MOTACIO = 0.3;
	
	Motxilla[] motxilles;
	int numMotxilles;
	int numObjectes;
	final int max_supervivents;
		
	public SimuladorMotxilla(int numMotxilles, int numObjectes) {
		this.numMotxilles = numMotxilles;
		this.numObjectes = numObjectes;
		this.max_supervivents = (int) Math.floor(SUPERVIVENCIA * numMotxilles);
		
		motxilles = new Motxilla[numMotxilles];
		for(int i = 0; i < numMotxilles; i++) {
			motxilles[i] = new Motxilla(numObjectes);
		}
		
	}
	
	public void entrenar(int generacions) {
		Arrays.sort(motxilles); //Sorts (per si algun cas)
		
		for(int i = 0; i < generacions; i++) { //Entrenament			
			Motxilla[] supervivents = eleccioNatural();
			reproduccio( supervivents );
			
			Arrays.sort(motxilles); //Sorts
			
			System.out.println("	GENERACIO NUMERO " + i);
			outResultats();
		}		
	}
	
	public void _entrenar(String[] args) { this.entrenar( Integer.parseInt(args[0]) ); }
	
	
	private Motxilla[] eleccioNatural() { // Una forma de dir assessinar de forma freda ;D
		Motxilla[] supervivents = new Motxilla[max_supervivents];
		
		for(int i = 0; i < max_supervivents; i++) supervivents[i] = motxilles[i];	
		return supervivents;
	}
	
	private void reproduccio(Motxilla[] supervivents) {
		for(int i = max_supervivents; i < motxilles.length; i++) {
			Motxilla m1 = Util.RandomValueArray(supervivents);
			Motxilla m2 = Util.RandomValueArray(supervivents);
			motxilles[i].setNousGens( m1.reproduir( m2 ) );
		}
	}
	
	public Motxilla getMillorMotxilla() {
		return motxilles[0];
	}
	
	
	public void outResultats() {
		System.out.println("-----------------------------------------------------------------------");
		
		for(int i = 0; i < motxilles.length; i++) {
			Motxilla motxillaOut = motxilles[i];
			
			System.out.println(i + "->" + 
			" Gens:" + Integer.toBinaryString(motxillaOut.getGens()) + 
			" | Like: " + motxillaOut.resultat());
			
		}
		
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();
	}
	
	public void _outResultats(String[] args) { outResultats(); }
}