import java.util.*;

public class RandomPlayer implements Player{
	public QuixoBoard tabla;
	public ArrayList<Move> lepesek; 			//lehetseges lepesek letarolasara
	public int szinem; 							//A jatekos sajat mintaja
	public int eSzin;							//Az ellenfel mintaja
	public long osszIdo;
	
	public void beallitTablat(QuixoBoard qt) {
		tabla=(QuixoBoard)qt.clone();
	}

	public void adatok(int sorrend, long ido) {
		lepesek=new ArrayList<Move>(56); 		//16*3-4 lehetseges lepes lehet maximum (3*5*4)
		osszIdo=ido;
		if(sorrend==1){ 						//A jatekosnak melyik a mintaja, es az ellenfele melyik
			szinem=QuixoBoard.X;
			eSzin=QuixoBoard.O;
		}else if(sorrend==2){szinem=QuixoBoard.O;
				eSzin=QuixoBoard.X;
		}
	}

	//Az AI kovetkezo lepese. A szabalyos lepeseket osszegyujti, osszekeveri oket, es valaszt kozuluk egyet, amit meglep
	public Move kovLepes(Move elozoLepes, long ido) {
	  /*
		lepesek.clear(); 		//torli az eddig lementett lepeseket
		
		for(int i=0; i<5; i++){ 		//A lehetseges lepeseken vegigmegy, es lepesek-hez hozzaadja a szabalyos lepeseket
			//ha az elso sorbol valasztok
			if(tabla.szabalyos(0, i, szinem, 0, 4)){lepesek.add(new Move(0, i, 0, 4));}
			if(tabla.szabalyos(0, i, szinem, 0, 0)){lepesek.add(new Move(0, i, 0, 0));}
			if(tabla.szabalyos(0, i, szinem, 4, i)){lepesek.add(new Move(0, i, 4, i));}
			//ha az utolso sorbol valasztok
			if(tabla.szabalyos(4, i, szinem, 4, 0)){lepesek.add(new Move(4, i, 4, 0));}
			if(tabla.szabalyos(4, i, szinem, 4, 4)){lepesek.add(new Move(4, i, 4, 4));}
			if(tabla.szabalyos(4, i, szinem, 0, i)){lepesek.add(new Move(4, i, 0, i));}
				
			//ha az elso oszlopbol valasztok
			if(tabla.szabalyos(i, 0, szinem, 4, 0)){lepesek.add(new Move(i, 0, 4, 0));}
			if(tabla.szabalyos(i, 0, szinem, 0, 0)){lepesek.add(new Move(i, 0, 0, 0));}
			if(tabla.szabalyos(i, 0, szinem, i, 4)){lepesek.add(new Move(i, 0, i, 4));}
			//ha az utolso oszlopbol valasztok
			if(tabla.szabalyos(i, 4, szinem, 0, 4)){lepesek.add(new Move(i, 4, 0, 4));}
			if(tabla.szabalyos(i, 4, szinem, 4, 4)){lepesek.add(new Move(i, 4, 4, 4));}
			if(tabla.szabalyos(i, 4, szinem, i, 0)){lepesek.add(new Move(i, 4, i, 0));}
		}
		Collections.shuffle(lepesek); 		//Lepesek osszekeverese
		Move m=lepesek.remove(0);
		return m;
		*/
	  return null;
	}

	@Override
	public int getSzin() {
		return szinem;
	}
	
	public String toString(){
		return "RandomPlayer";
	}

}
