package quixo.players.minimax;

import quixo.engine.Move;
/**@Moho egy melysegig vizsgalja az adott jatekfat. 
 * A megadott heirisztika alapjan szamol erteket.*/
public class Greedy extends Minimax{
	
	public Greedy(){
		super();
		this.setDepth(1);
	}
	
	protected Move nextMove(Move prevStep) {
	//	setHeuristic(heuristic, me, you, nobody);
		/*if(prevStep!=null){
			table.makeStep(prevStep, getOpponentColor());
		}*/
		step=super.nextMove(prevStep);
		return step;
	}
}