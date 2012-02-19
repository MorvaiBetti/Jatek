package quixo.heuristics;


import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.players.minimax.Node;

/**@Winner A 3-as szamu heurisztika.
 * Azt vizsgalja, hogy az adott tablan valamelyik jatekosnak van-e 4 egy vonalban es tud-e a kovetkezo lepesben nyerni.*/
public class Winner extends SimpleHeuristic{

	public void init(int me, int you, int nobody){
		super.init(me, you, nobody);
	}
	
	/**Kiszamolja, hogy egy voanlban melyik babubol mennyi van es a koordinatajukat letarolja*/
	public int calculation(Node node){
		this.node=node;
		table=(QuixoBoard) node.getTable().clone();
		color=(node.getModel()+1)%2;
		if(findStep((color+1)%2)){
			return Integer.MIN_VALUE;
		}
		if(findStep(color)){
			return Integer.MAX_VALUE;
		}
		if(table.win((color+1)%2)){
			return Integer.MIN_VALUE;
		}
		if(table.win(color)){
			return Integer.MAX_VALUE;
		}
		value=super.calculation(node);
		return value;
	}
	
	/**Ellenorzi, hogy egy lepesbol nyerhet-e az adott minta.
	 * @param model a celhelyre ezt a mintat szeretnem tenni*/
	public boolean findStep(int model){
		ArrayList<Move> steps=new ArrayList<Move>();
		steps=table.nextSteps(model);
		for(Move move: steps){
			table.makeStep(move, model);
			if(table.win(model)){
				table.undoStep(move);
				return true;
			}
			table.undoStep(move);
		}
		return false;
	}
	
/*	public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		t.setField(0, 0, 0);
		t.setField(0, 1, 0);
		t.setField(0, 2, 0);
	//	t.setField(0, 3, 0);
	//	t.setField(0, 4, 0);
	/*	t.setField(1, 0, 1);
		t.setField(2, 0, 0);
		t.setField(3, 0, 1);
		t.setField(4, 0, 0);
		t.setField(1, 4, 1);
		t.setField(2, 4, 0);
		t.setField(3, 4, 1);
		t.setField(4, 4, 0);
		t.setField(4, 3, 1);
		t.setField(4, 2, 0);
		t.setField(4, 1, 1);
		System.out.println(t);
		
		Node rootx=new Node(t, null, null);
		rootx.setModel(1);
		Winner testx=new Winner(rootx);
		testx.value=0;
		testx.calculation();
		System.out.println("x: "+testx.value);
		
		Node rooto=new Node(t, null, null);
		rooto.setModel(0);
		Winner testo=new Winner(rooto);
		testo.value=0;
		testo.calculation();
		System.out.println(" o: "+testo.value);
	}*/
}
