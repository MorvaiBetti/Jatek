package quixo.players.minimax;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.players.SimplePlayer;

public class Moho extends SimplePlayer{
	/**@tree n melysegu fa minimax algoritmussal es alfa-beta vagassal*/
	public TreeStructure tree;
	/**@heuristic melyik heuristikat hasznalja kiertekelesre*/
	public int heuristic;
	
	public void setHeuristic(int h){
		heuristic=h;
		System.out.println("moho "+h);
	}
	
	public Move nextMove(Move prevStep) {
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		
		Pair p=new Pair(getColor(), (QuixoBoard)table.clone());
		tree = new TreeStructure(p, 1, heuristic);
		table.makeStep(tree.maxNode.step, color);
		return tree.maxNode.step;
	}
}