import java.util.*;

public class CheatRandomPlayer extends RandomPlayer{
	public ArrayList<Move> cheat;
	public double epsz=5;
	
	public CheatRandomPlayer() {
		super();
		epsz=5;
		cheat=new ArrayList<Move>(75);
	}
	
	public void setTable(QuixoBoard qt) {
		table=qt;
	}
	
	public int getColor(){
		return color;
	}
	
	public String toString(){
		return "CheatRandomPlayer";
	}
	
	public Move nextMove(Move prevStep, long time){
		// wait for a moment
	 /*   System.out.println(getColor() + " alszik!");
	    for (long qqq = 0; qqq < Integer.MAX_VALUE; qqq ++) {
	    }*/

		int db=0;
		Move m=null;
		
		steps.clear();
		cheat.clear();
		for(int i=0; i<5; i++){
			//ha az elso sorbol valasztok
			if(table.legal(0, i, color, 0, 4)){steps.add(new Move(0, i, 0, 4)); db++;}else {cheat.add(new Move(0, i, 0, 4));}
			if(table.legal(0, i, color, 0, 0)){steps.add(new Move(0, i, 0, 0)); db++;}else {cheat.add(new Move(0, i, 0, 0));}
			if(table.legal(0, i, color, 4, i)){steps.add(new Move(0, i, 4, i)); db++;}else {cheat.add(new Move(0, i, 4, i));}
			//ha az utolso sorbol valasztok
			if(table.legal(4, i, color, 4, 0)){steps.add(new Move(4, i, 4, 0)); db++;}else {cheat.add(new Move(4, i, 4, 0));}
			if(table.legal(4, i, color, 4, 4)){steps.add(new Move(4, i, 4, 4)); db++;}else {cheat.add(new Move(4, i, 4, 4));}
			if(table.legal(4, i, color, 0, i)){steps.add(new Move(4, i, 0, i)); db++;}else {cheat.add(new Move(4, i, 0, i));}
			//ha az elso oszlopbol valasztok
			if(table.legal(i, 0, color, 4, 0)){steps.add(new Move(i, 0, 4, 0)); db++;}else {cheat.add(new Move(i, 0, 4, 0));}
			if(table.legal(i, 0, color, 0, 0)){steps.add(new Move(i, 0, 0, 0)); db++;}else {cheat.add(new Move(i, 0, 0, 0));}
			if(table.legal(i, 0, color, i, 4)){steps.add(new Move(i, 0, i, 4)); db++;}else {cheat.add(new Move(i, 0, i, 4));}
			//ha az utolso oszlopbol valasztok
			if(table.legal(i, 4, color, 0, 4)){steps.add(new Move(i, 4, 0, 4)); db++;}else {cheat.add(new Move(i, 4, 0, 4));}
			if(table.legal(i, 4, color, 4, 4)){steps.add(new Move(i, 4, 4, 4)); db++;}else {cheat.add(new Move(i, 4, 4, 4));}
			if(table.legal(i, 4, color, i, 0)){steps.add(new Move(i, 4, i, 0)); db++;}else {cheat.add(new Move(i, 4, i, 0));}
		}
		Collections.shuffle(cheat);
		Collections.shuffle(steps);
		m=steps.remove(0);
		/*if((db%2)==0){
			m=cheat.remove(0);
			System.out.println(db+" cheat: "+m);
			return m;
		}*/
	    return m;
	}
}