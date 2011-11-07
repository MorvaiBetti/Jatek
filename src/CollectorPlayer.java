public class CollectorPlayer extends RandomPlayer{
	public QuixoBoard table;
	public int color; 						//A jatekos sajat mintaja
	public int opponentColor; 				//Az ellenfel mintaja
	public long maxTime;
	public int[][] line;
	public Move step=null;
	public int db;

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

	public Move nextMove(Move prevStep, long oTime) {
		line=new int[25][2];
		int rand;
		
		for(int k=0; k<4; k++){
			line[k][0]=0;
			line[k][1]=0;
		}
		
		if(existEmptyField()){
			rand=(int) (Math.random()*db);
			if(table.legal(line[rand][0], line[rand][1], color, line[rand][0], Math.abs(4-line[rand][1]))){
				return step=new Move(line[rand][0], line[rand][1], line[rand][0], Math.abs(4-line[rand][1]));
			}
		}
		return super.nextMove(prevStep, oTime);
	}
	
	//adott jatekosnak van-e meg szabalyos lepese
	public boolean existEmptyField(){
		db=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(i,j)==QuixoBoard.empty && (i==0 || i==4 || j==0 || j==4)){
					line[db][0]=i;
					line[db][1]=j;
					db++;
				}
			}
		}
		if(db>0){
			return true;
		}
		return false;
	}

}
