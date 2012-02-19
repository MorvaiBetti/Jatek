package quixo.players;

import java.util.ArrayList;
import java.util.Collections;

import quixo.engine.Move;
import quixo.engine.Player;

public class RandomPlayer extends Player{
	/**@steps szabalyos lepesek letarolasara*/
	protected ArrayList<Move> steps=new ArrayList<Move>();  	
	
	public RandomPlayer(){
		step=null;
	}
	
	/**Az AI kovetkezo lepese. A legal stepset osszegyujti, osszekeveri oket, es valaszt kozuluk egyet, amit meglep
	 * @param prevStep az ellenfel utolso lepese*/
	protected Move nextMove(Move prevStep) {
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		steps.clear();
		steps=table.nextSteps(getColor());
	
		Collections.shuffle(steps, rand); /**steps mix*/
		Move m=steps.remove(0);
		table.makeStep(m, color);
		return m;
	}	
}