package quixo.players;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.Player;

public class CheatRandomPlayer extends Player{
	/**@cheat szabalytalan lepesek letarolasara*/
	private ArrayList<Move> cheat=new ArrayList<Move>(); 
	/**@steps szabalyos lepesek letarolasara*/
	private ArrayList<Move> steps=new ArrayList<Move>();
	
	private CheatRandomPlayer() {
	}
	
	public String toString(){
		return "CheatRandomPlayer";
	}
	
	protected Move nextMove(Move prevStep){
		int db=0, j=0;
		int rand;
		Move m=null;
		steps.clear();
		cheat.clear();
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		for(int i=0; i<5; i++){
			/**ha az elso sorbol valasztok*/
			if(table.legal(0, i, color, 0, 4)){steps.add(new Move(0, i, 0, 4)); db++;}else {cheat.add(new Move(0, i, 0, 4)); j++;}
			if(table.legal(0, i, color, 0, 0)){steps.add(new Move(0, i, 0, 0)); db++;}else {cheat.add(new Move(0, i, 0, 0)); j++;}
			if(table.legal(0, i, color, 4, i)){steps.add(new Move(0, i, 4, i)); db++;}else {cheat.add(new Move(0, i, 4, i)); j++;}
			/**ha az utolso sorbol valasztok*/
			if(table.legal(4, i, color, 4, 0)){steps.add(new Move(4, i, 4, 0)); db++;}else {cheat.add(new Move(4, i, 4, 0)); j++;}
			if(table.legal(4, i, color, 4, 4)){steps.add(new Move(4, i, 4, 4)); db++;}else {cheat.add(new Move(4, i, 4, 4)); j++;}
			if(table.legal(4, i, color, 0, i)){steps.add(new Move(4, i, 0, i)); db++;}else {cheat.add(new Move(4, i, 0, i)); j++;}
			/**ha az elso oszlopbol valasztok*/
			if(table.legal(i, 0, color, 4, 0)){steps.add(new Move(i, 0, 4, 0)); db++;}else {cheat.add(new Move(i, 0, 4, 0)); j++;}
			if(table.legal(i, 0, color, 0, 0)){steps.add(new Move(i, 0, 0, 0)); db++;}else {cheat.add(new Move(i, 0, 0, 0)); j++;}
			if(table.legal(i, 0, color, i, 4)){steps.add(new Move(i, 0, i, 4)); db++;}else {cheat.add(new Move(i, 0, i, 4)); j++;}
			/**ha az utolso oszlopbol valasztok*/
			if(table.legal(i, 4, color, 0, 4)){steps.add(new Move(i, 4, 0, 4)); db++;}else {cheat.add(new Move(i, 4, 0, 4)); j++;}
			if(table.legal(i, 4, color, 4, 4)){steps.add(new Move(i, 4, 4, 4)); db++;}else {cheat.add(new Move(i, 4, 4, 4)); j++;}
			if(table.legal(i, 4, color, i, 0)){steps.add(new Move(i, 4, i, 0)); db++;}else {cheat.add(new Move(i, 4, i, 0)); j++;}
		}
		if((db%2)==1){
			rand=(int) (Math.random()*(j-1));
			m=cheat.remove(rand);
		}else {rand=(int) (Math.random()*(db-1));
				m=steps.remove(rand);
			}
		table.makeStep(m, color);
	    return m;
	}
}