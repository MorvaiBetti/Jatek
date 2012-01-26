package quixo.players.minimax;

import quixo.engine.Move;
import quixo.players.SimplePlayer;

public class Moho extends SimplePlayer{
	public TreeStructure tree;
	
	public Move nextMove(Move prevStep) {
		tree = new TreeStructure(getColor(), table, 1);
		System.out.println(tree.root);
		return tree.maxNode.step;
	}
}