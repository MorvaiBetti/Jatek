import java.util.*;

public class RandomPlayer implements Player{
	public QuixoBoard table;
	public ArrayList<Move> steps; //lehetseges steps letarolasara
	public int color; //A jatekos sajat mintaja
	public int opponentColor; //Az ellenfel mintaja
	public long maxTime;
	
	public void setTable(QuixoBoard qt) {
		table=qt;
	}
	
	public void datas(int sequence, long time) {
		steps=new ArrayList<Move>(56); //16*3-4 lehetseges lepes lehet maximum (3*5*4)
		maxTime=time;
		if(sequence==QuixoBoard.X){ //A jatekosnak melyik a mintaja, es az ellenfele melyik
			color=QuixoBoard.X;
			opponentColor=QuixoBoard.O;
		}else if(sequence==QuixoBoard.O){color=QuixoBoard.O;
		opponentColor=QuixoBoard.X;
		}
	}
	
	//Az AI kovetkezo lepese. A legal stepset osszegyujti, osszekeveri oket, es valaszt kozuluk egyet, amit meglep
	public Move nextMove(Move prevStep, long time) {

		table.toString();
		steps.clear(); //torli az eddig lementett stepset
		for(int i=0; i<5; i++){ //A lehetseges stepsen vegigmegy, es steps-hez hozzaadja a legal stepset
			//ha az elso sorbol valasztok
			if(table.legal(0, i, color, 0, 4)){steps.add(new Move(0, i, 0, 4));}
			if(table.legal(0, i, color, 0, 0)){steps.add(new Move(0, i, 0, 0));}
			if(table.legal(0, i, color, 4, i)){steps.add(new Move(0, i, 4, i));}
			//ha az utolso sorbol valasztok
			if(table.legal(4, i, color, 4, 0)){steps.add(new Move(4, i, 4, 0));}
			if(table.legal(4, i, color, 4, 4)){steps.add(new Move(4, i, 4, 4));}
			if(table.legal(4, i, color, 0, i)){steps.add(new Move(4, i, 0, i));}
			//ha az elso oszlopbol valasztok
			if(table.legal(i, 0, color, 4, 0)){steps.add(new Move(i, 0, 4, 0));}
			if(table.legal(i, 0, color, 0, 0)){steps.add(new Move(i, 0, 0, 0));}
			if(table.legal(i, 0, color, i, 4)){steps.add(new Move(i, 0, i, 4));}
			//ha az utolso oszlopbol valasztok
			if(table.legal(i, 4, color, 0, 4)){steps.add(new Move(i, 4, 0, 4));}
			if(table.legal(i, 4, color, 4, 4)){steps.add(new Move(i, 4, 4, 4));}
			if(table.legal(i, 4, color, i, 0)){steps.add(new Move(i, 4, i, 0));}
		}
		Collections.shuffle(steps); //steps mix
		Move m=steps.remove(0);
		return m;
	}
	
	@Override
	public int getColor() {
		return color;
	}
	
	public String toString(){
		return "RandomPlayer";
	}
	
}