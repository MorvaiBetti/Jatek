package quixo.players.minimax;

import quixo.engine.Move;

public class Moho extends Minimax{
	public Minimax forbear;
	public Move step;
	/**@heuristic melyik heuristikat hasznalja kiertekelesre*/
/*	public int heuristic;
	
	public void setHeuristic(int h){
		heuristic=h;
		System.out.println("moho "+h);
	}*/
	
	public Moho(){
		super();
		forbear=new Minimax();
		forbear.setDepth(1);
	}
	
	public Move nextMove(Move prevStep) {
		if(prevStep!=null){
			table.makeStep(prevStep, getOpponentColor());
		}
		forbear.root=new Node(table, null, null);
		forbear.table=table;
		forbear.setColor(getColor());
	
		forbear.maxValue(forbear.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		forbear.root=forbear.find(forbear.root);
		step=forbear.root.getStep();
		table=forbear.root.getTable();
		return step;
	}
}