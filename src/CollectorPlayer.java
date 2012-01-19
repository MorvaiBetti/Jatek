public class CollectorPlayer extends RandomPlayer{
	public int[][] line;
	public int db;
	
	public CollectorPlayer() {
		super();
		line=new int[25][2];
	}

	public Move nextMove() {
		int rand;
		
		for(int k=0; k<4; k++){
			line[k][0]=0;
			line[k][1]=0;
		}
		
		if(existEmptyField()){
			rand=(int) (Math.random()*(db-1));
			//ugyanabban a sorban marad
			if(table.legal(line[rand][0], line[rand][1], color, line[rand][0], 0)){
				step=new Move(line[rand][0], line[rand][1], line[rand][0], 0);
				return step;
			}
			if(table.legal(line[rand][0], line[rand][1], color, line[rand][0], 4)){	
				step=new Move(line[rand][0], line[rand][1], line[rand][0], 4);
				return step;
			}
			//ugyanabban az oszlopban marad
			if(table.legal(line[rand][0], line[rand][1], color, 0, line[rand][1])){
				step=new Move(line[rand][0], line[rand][1], 0, line[rand][1]);
				return step;
			}
			if(table.legal(line[rand][0], line[rand][1], color, 4, line[rand][1])){	
				step=new Move(line[rand][0], line[rand][1], 4, line[rand][1]);
				return step;
			}
		}
		return super.nextMove();
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
		System.out.println("db: "+db);
		if(db>0){
			return true;
		}
		return false;
	}

}
