package quixo.players.minimax;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.players.SimplePlayer;

public class Moho extends SimplePlayer{
	public TreeStructure tree;
	
	public Move nextMove(Move prevStep) {
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		Pair p=new Pair(getColor(), (QuixoBoard)table.clone());
		tree = new TreeStructure(p, 1, null, getColor());
	//	tree = new TreeStructure(getColor(), table, 1);
	//	System.out.println(tree.root);
		table.makeStep(tree.maxNode.step, color);
		return tree.maxNode.step;
	}
}