import java.util.*;

public class CheatRandomPlayer extends RandomPlayer{
	public ArrayList<Move> csalok;
	public double epsz=5;
	
	public int getSzin(){
		return szinem;
	}

	public void beallitTablat(QuixoBoard qt) {
		tabla=qt;
	}
	
	public Move kovLepes(Move elozoLepes, long ido){
	  // wait for a moment
    System.out.println(getSzin() + " alszik!");
    for (long qqq = 0; qqq < Integer.MAX_VALUE; qqq ++) { 
    }
    //
    /*
		int db=0;
		Move m=null;
		csalok=new ArrayList<Move>(75);
		lepesek.clear();
		csalok.clear();
		for(int i=0; i<5; i++){
			
			//ha az elso sorbol valasztok
			if(tabla.szabalyos(0, i, szinem, 0, 4)){lepesek.add(new Move(0, i, 0, 4)); db++;}else {csalok.add(new Move(0, i, 0, 4));}
			if(tabla.szabalyos(0, i, szinem, 0, 0)){lepesek.add(new Move(0, i, 0, 0)); db++;}else {csalok.add(new Move(0, i, 0, 0));}
			if(tabla.szabalyos(0, i, szinem, 4, i)){lepesek.add(new Move(0, i, 4, i)); db++;}else {csalok.add(new Move(0, i, 4, i));}
			//ha az utolso sorbol valasztok
			if(tabla.szabalyos(4, i, szinem, 4, 0)){lepesek.add(new Move(4, i, 4, 0)); db++;}else {csalok.add(new Move(4, i, 4, 0));}
			if(tabla.szabalyos(4, i, szinem, 4, 4)){lepesek.add(new Move(4, i, 4, 4)); db++;}else {csalok.add(new Move(4, i, 4, 4));}
			if(tabla.szabalyos(4, i, szinem, 0, i)){lepesek.add(new Move(4, i, 0, i)); db++;}else {csalok.add(new Move(4, i, 0, i));}
				
			//ha az elso oszlopbol valasztok
			if(tabla.szabalyos(i, 0, szinem, 4, 0)){lepesek.add(new Move(i, 0, 4, 0)); db++;}else {csalok.add(new Move(i, 0, 4, 0));}
			if(tabla.szabalyos(i, 0, szinem, 0, 0)){lepesek.add(new Move(i, 0, 0, 0)); db++;}else {csalok.add(new Move(i, 0, 0, 0));}
			if(tabla.szabalyos(i, 0, szinem, i, 4)){lepesek.add(new Move(i, 0, i, 4)); db++;}else {csalok.add(new Move(i, 0, i, 4));}
			//ha az utolso oszlopbol valasztok
			if(tabla.szabalyos(i, 4, szinem, 0, 4)){lepesek.add(new Move(i, 4, 0, 4)); db++;}else {csalok.add(new Move(i, 4, 0, 4));}
			if(tabla.szabalyos(i, 4, szinem, 4, 4)){lepesek.add(new Move(i, 4, 4, 4)); db++;}else {csalok.add(new Move(i, 4, 4, 4));}
			if(tabla.szabalyos(i, 4, szinem, i, 0)){lepesek.add(new Move(i, 4, i, 0)); db++;}else {csalok.add(new Move(i, 4, i, 0));}
		}
		
		Collections.shuffle(csalok);
		Collections.shuffle(lepesek);
		m=lepesek.remove(0);
		*/
		/*if((db%2)==0){
			m=csalok.remove(0);
			System.out.println(db+" Csalok: "+m);
			return m;
		}*/
		//return m;
    return null;
	}
	
	public String toString(){
		return "CheatRandomPlayer";
	}
}
