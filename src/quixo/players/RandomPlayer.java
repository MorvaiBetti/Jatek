package quixo.players;

import java.util.*;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;

public class RandomPlayer extends SimplePlayer{
	public ArrayList<Move> steps=new ArrayList<Move>();  	/**@steps szabalyos lepesek letarolasara*/

	
	public RandomPlayer(){
		step=null;
	}
	
	/**Az AI kovetkezo lepese. A legal stepset osszegyujti, osszekeveri oket, es valaszt kozuluk egyet, amit meglep*/
	public Move nextMove(Move prevStep) {
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		steps.clear(); 								/**torli az eddig lementett stepset*/
		for(int i=0; i<5; i++){ 					/**A lehetseges stepsen vegigmegy, es steps-hez hozzaadja a legal stepset*/
			/**ha az elso sorbol valasztok*/
			if(table.getField(0, i)==getColor() || table.getField(0, i)==QuixoBoard.empty){
				if(table.legal(0, i, getColor(), 0, 4)){steps.add(new Move(0, i, 0, 4));}
				if(table.legal(0, i, getColor(), 0, 0)){steps.add(new Move(0, i, 0, 0));}
				if(table.legal(0, i, getColor(), 4, i)){steps.add(new Move(0, i, 4, i));}
			}
			/**ha az utolso sorbol valasztok*/
			if(table.getField(4, i)==getColor() || table.getField(4, i)==QuixoBoard.empty){
				if( table.legal(4, i, getColor(), 4, 0)){steps.add(new Move(4, i, 4, 0));}
				if(table.legal(4, i, getColor(), 4, 4)){steps.add(new Move(4, i, 4, 4));}
				if(table.legal(4, i, getColor(), 0, i)){steps.add(new Move(4, i, 0, i));}
			}
			
			/**ha az elso oszlopbol valasztok*/
			if(table.getField(i, 0)==getColor() || table.getField(i, 0)==QuixoBoard.empty){
				if(table.legal(i, 0, getColor(), 4, 0)){steps.add(new Move(i, 0, 4, 0));}
				if(table.legal(i, 0, getColor(), 0, 0)){steps.add(new Move(i, 0, 0, 0));}
				if(table.legal(i, 0, getColor(), i, 4)){steps.add(new Move(i, 0, i, 4));}
			}
			/**ha az utolso oszlopbol valasztok*/
			if(table.getField(i, 4)==getColor() || table.getField(i, 4)==QuixoBoard.empty){
				if(table.legal(i, 4, getColor(), 0, 4)){steps.add(new Move(i, 4, 0, 4));}
				if(table.legal(i, 4, getColor(), 4, 4)){steps.add(new Move(i, 4, 4, 4));}
				if(table.legal(i, 4, getColor(), i, 0)){steps.add(new Move(i, 4, i, 0));}
			}
		}
		
		Collections.shuffle(steps); /**steps mix*/
		Move m=steps.remove(0);
		table.makeStep(m, color);
		return m;
	}	
}