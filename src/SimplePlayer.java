public abstract class SimplePlayer implements Player{
	public QuixoBoard table;
	public Move step;
	public int color; 						//A jatekos sajat mintaja
	public int opponentColor; 				//Az ellenfel mintaja
	public long maxTime;
	
	public void setTable(QuixoBoard qt) {
		table=qt;
	}
	
	public int getColor() {
		return color;
	}
	
	public void datas(int sequence, long time) {
		maxTime=time;
		if(sequence==QuixoBoard.X){ 				//A jatekosnak melyik a mintaja, es az ellenfele melyik
			color=QuixoBoard.X;
			opponentColor=QuixoBoard.O;
		}else if(sequence==QuixoBoard.O){color=QuixoBoard.O;
		opponentColor=QuixoBoard.X;
		}
	}
	
	public void setDepth(int d) {}
}
