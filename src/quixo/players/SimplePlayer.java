package quixo.players;

import quixo.engine.Player;
import quixo.engine.QuixoBoard;
import quixo.engine.Move;

public abstract class SimplePlayer implements Player{
	public QuixoBoard table;
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
}
