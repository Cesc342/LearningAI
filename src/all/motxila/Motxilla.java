package all.motxila;

/*
 Basicament, una prova de IA Genetica, que he vist en aquest video: https://youtu.be/uQj5UNhCPuo
*/

public class Motxilla implements Comparable<Motxilla>{
	private int gens; //		Integer.toBinaryString(i);
	private int length;
	
	public Motxilla(int numObjectes) {
		gens = 0b0;
        for(int i = 0; i < numObjectes; i++) {
			gens = (Math.random() < 0.5) ? gens & ~(0x1<<i) : gens | 0x1<<i; // Fica Bits Aleatoris
		}
        
        length = Integer.toBinaryString(gens).length();
	}
	
	
	public int getGens() { return gens; }
	public int getLength() { return length; }
	
	public void setNousGens(int x) { 
		gens = x; 
		length = Integer.toBinaryString(gens).length();
	}
	
	public int resultat() {
		int pes = 0, like = 0;
		
		//System.out.println("/////////////");
		for(int i = 0; i < length ; i++) {
			if((gens & 0b1 << i) == 0) continue; 
			pes += i * i * i;
			like += i * i * i; 
			//System.out.println(pes + "|" + "like " + like);
		}
		//System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\");
		if(pes > 500 || pes < 400) return 0;
		return like;
	}
	
	public int reproduir(Motxilla parella) {
		int randomCut = (int) Math.floor( Math.random() * length );
		
		int nousGens = gens;
		int gensParella = parella.getGens();
		
		gensParella = (gensParella >> randomCut) << randomCut; // 110111 >> 3 ... 100 << 3 ... 110000 (netejar els bits)
		nousGens &= ~(0b1 << randomCut); // (Netejan els de a)
	    nousGens |= gensParella; // 000101 |= 110000 -> 110101   Ajuntant els dos bytes (fent la nova genetica)
		
		if(Math.random() < SimuladorMotxilla.MOTACIO) {
			int numMotacions = (int) (SimuladorMotxilla.MOTACIO * length);
			for(int i = 0; i < numMotacions; i++) {
				int bit =  0b1 << ( (int) (Math.random() * length) );
				nousGens ^= bit; // Canvia el bit gracies a les propietats del XOR
			}

		}
		
		return nousGens;
	}


	@Override
	public int compareTo(Motxilla m) {
		return m.resultat() - this.resultat();
	}
	
}
