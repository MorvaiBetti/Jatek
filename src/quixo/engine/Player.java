package quixo.engine;

import java.util.Random;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.heuristics.Heuristics;
import quixo.players.minimax.Node;

public abstract class Player{
	/**@table jatekos tablaja*/
	protected QuixoBoard table;
	/**@step jatekos kovetkezo lepese*/
	protected Move step;
	/**@color a jatekos mintaja*/
	protected int color; 
	/**@opponentColor az ellenfel mintaja*/
	protected int opponentColor; 
	/**@maxTime a jatekos ideje*/
	protected long maxTime;
	/**@depth jatekfa eseten hany lepest nezzen meg elore*/
	protected int depth;
	/**@root jatekfa eseten ez a gyoker*/
	protected Node root;
	/**@rand random erteke*/
	protected Random rand;
	/**@heuristic az adott jatekos melyik heurisztika alapjan szamolja a tabla ertekeket*/
	protected Heuristics heuristic;
	
	/**Beallitja a jatekos szinet az ellenfel szinet es a jatekos idejet
	 * @param sequence a jatekban hanyadik jatekos a jatekos
	 * @param time a jatekos ideje
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException */
	protected void datas(int sequence, long time, long random, String h, int me, int you, int nobody) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		table=new QuixoBoard();
		maxTime=time;
		rand=new Random(random);
		if(!h.equals("null")){
			String nameH="quixo.heuristics.";
			nameH=nameH+h;
			heuristic= (Heuristics) Class.forName(nameH).newInstance();
			heuristic.init(me, you, nobody);
		}
		/**A jatekosnak melyik a mintaja, es az ellenfele melyik*/
		if(sequence==QuixoBoard.X){ 				
			color=QuixoBoard.X;
			opponentColor=QuixoBoard.O;
		}else if(sequence==QuixoBoard.O){color=QuixoBoard.O;
		opponentColor=QuixoBoard.X;
		}
	}
	
	/**A kovetkezo lepest szamolja ki.
	 * @param prevStep az ellenfel utolso lepese*/
	protected Move nextMove(Move prevStep){return null;}
	
	protected void setDepth(int d) {
		depth=d;
	}

	protected QuixoBoard getTable() {
		return table;
	}
	
	protected int getColor() {
		return color;
	}
	
	protected Move getStep() {
		return step;
	}

	protected void setStep(Move step) {
		this.step = step;
	}

	protected int getOpponentColor() {
		return opponentColor;
	}

	protected void setOpponentColor(int opponentColor) {
		this.opponentColor = opponentColor;
	}

	protected void setColor(int color) {
		this.color = color;
	}
	
}
