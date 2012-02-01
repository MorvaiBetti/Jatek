package quixo.players;

import quixo.engine.Player;
import quixo.engine.QuixoBoard;
import quixo.engine.Move;

public abstract class SimplePlayer implements Player{
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
	
	public void setTable(QuixoBoard qt) {
		table=(QuixoBoard) qt.clone();
	}
	
	public int getColor() {
		return color;
	}
	
	/**Beallitja a jatekos szinet az ellenfel szinet es a jatekos idejet
	 * @param sequence a jatekban hanyadik jatekos a jatekos
	 * @param time a jatekos ideje*/
	public void datas(int sequence, long time) {
		maxTime=time;
		/**A jatekosnak melyik a mintaja, es az ellenfele melyik*/
		if(sequence==QuixoBoard.X){ 				
			color=QuixoBoard.X;
			opponentColor=QuixoBoard.O;
		}else if(sequence==QuixoBoard.O){color=QuixoBoard.O;
		opponentColor=QuixoBoard.X;
		}
	}
	
	public void setDepth(int d) {}
	
	public void setHeuristic(int h){}
}
