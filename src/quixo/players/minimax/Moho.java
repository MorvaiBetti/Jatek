package quixo.players.minimax;

import quixo.engine.Move;
/**@Moho egy melysegig vizsgalja az adott jatekfat. 
 * A megadott heirisztika alapjan szamol erteket.*/
public class Moho extends Minimax{
	/**@forbear Minimax tipusu jatekos, rajta keresztul vizsalom a jatekfat egy melyseggel.*/
	public Minimax forbear;
	
	public Moho(){
		super();
		forbear=new Minimax();
		forbear.datas(color, maxTime, rand);
		forbear.setDepth(1);
	}
	
	public Move nextMove(Move prevStep) {
		forbear.setHeuristic(heuristic, me, you, nobody);
		if(prevStep!=null){
			table.makeStep(prevStep, getOpponentColor());
		}
		step=forbear.nextMove(prevStep);
		table.makeStep(step, color);
		return step;
	}
}