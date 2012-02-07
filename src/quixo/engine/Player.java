package quixo.engine;

import quixo.players.minimax.Node;

public abstract class Player{
	/**@table jatekos tablaja*/
	public QuixoBoard table;
	/**@step jatekos kovetkezo lepese*/
	public Move step;
	/**@color a jatekos mintaja*/
	public int color; 
	/**@opponentColor az ellenfel mintaja*/
	public int opponentColor; 
	/**@maxTime a jatekos ideje*/
	public long maxTime;
	/**@depth jatekfa eseten hany lepest nezzen meg elore*/
	public int depth;
	/**@root jatekfa eseten ez a gyoker*/
	public Node root;
	
	/**Beallitja a jatekos szinet az ellenfel szinet es a jatekos idejet
	 * @param sequence a jatekban hanyadik jatekos a jatekos
	 * @param time a jatekos ideje*/
	public void datas(int sequence, long time) {
		table=new QuixoBoard();
		maxTime=time;
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
	public Move nextMove(Move prevStep){return null;}
	
	public void setDepth(int d) {
		depth=d;
	}
	
	public void setHeuristic(int h){}

	public QuixoBoard getTable() {
		return table;
	}
	
	public int getColor() {
		return color;
	}
	
	public Move getStep() {
		return step;
	}

	public void setStep(Move step) {
		this.step = step;
	}

	public int getOpponentColor() {
		return opponentColor;
	}

	public void setOpponentColor(int opponentColor) {
		this.opponentColor = opponentColor;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
}
