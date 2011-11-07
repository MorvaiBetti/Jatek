public class DefendPlayer extends RandomPlayer{
	public QuixoBoard table;
	public Move step; 			//lehetseges steps letarolasara
	public int color; 						//A jatekos sajat mintaja
	public int opponentColor; 				//Az ellenfel mintaja
	public long maxTime;
	public int[][] line;
	
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
		line=new int[4][2];
		empty();
		step=null;
		if(winner(color)){
			winnerStep();
		}
		if(step!=null){
			System.out.println("winner");
			return step;
		}
		
		if(winner(opponentColor) && step==null){
			defendStep();
		}
		
		if(step!=null){
			System.out.println("mifene "+step);
			return step;
		}

		System.out.println("Lepes: "+super.nextMove(prevStep, oTime));
		return super.nextMove(prevStep, oTime);
	}
	
	public void empty(){
		for(int k=0; k<4; k++){
			line[k][0]=0;
			line[k][1]=0;
		}
	}
	
	public boolean winner(int model){
		int db=0;
		//sorokon megy vegig
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(i,j)==model){
					line[db][0]=i;
					line[db][1]=j;
					db++;
					if(db==4){
						return true;
					}
				}
			}
			empty();
			db=0; 
		}
		//oszlopokon megy vegig
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(j,i)==model){
					line[db][0]=j;
					line[db][1]=i;
					db++;
					if(db==4){
						return true;
					}	 
				}
			}
			empty();
			db=0;
		}
		//foatlo
		for(int i=0; i<5; i++){
			if(table.getField(i,i)==model){
				line[db][0]=i;
				line[db][1]=i;
				db++;
				if(db==4){
					return true;
				}
			}
		}
		empty();
		db=0;
		//mellekatlo
		for(int i=0; i<5; i++){
			if(table.getField(i, 4-i)==model){
				line[db][0]=i;
				line[db][1]=4-i;
				db++;
				if(db==4){
					return true;
				}
			}
		}
		return false;
	}
	
	public void defendStep(){
		//sor
		if(line[0][0]==line[1][0]){
			for(int i=0; i<4; i++){
				for(int j=0; j<5; j++){
					for(int k=0; k<5; k++){
						if(table.legal(j, line[i][1], color, k, line[i][1]) && ((j>=line[i][0] && line[i][0]>=k) || (k>=line[i][0] && line[i][0]>=j))){
							step=new Move(j, line[i][1], k, line[i][1]);
							return;
						}
					}
				}
			}
		}
		//oszlop
		if(line[0][1]==line[1][1]){
			for(int i=0; i<4; i++){
				for(int j=0; j<5; j++){
					for(int k=0; k<5; k++){
						if(table.legal(line[i][0], j, color, line[i][0], k) && ((j>=line[i][1] && line[i][1]>=k) || (k>=line[i][1] && line[i][1]>=j))){
							step=new Move(line[i][0], j, line[i][0], k);
							return;
						}
					}
				}
			}
		}
		//foatlo
		if((line[0][0]==line[0][1] && line[1][0]==line[1][1]) ||(line[0][0]+line[0][1]==4 && line[1][0]+line[1][1]==4)){
			for(int i=0; i<5; i++){
				if(table.legal(i, 0, color, i, 4)){
					step=new Move(i, 0, i, 4);
					return;
				}
				if(table.legal(i, 4, color, i, 0)){
					step=new Move(i, 4, i, 0);
					return;
				}
				if(table.legal(0, i, color, 4, i)){
					step=new Move(0, i, 4, i);
					return;
				}
				if(table.legal(4, i, color, 0, i)){
					step=new Move(4, i, 0, i);
					return;
				}
			}
		}
		return;
	}
	
	public void winnerStep(){
		//sor
		if(line[0][0]==line[1][0]){
			for(int i=1; i<4; i++){
				if(line[i][1]!=line[i-1][1]+1){
					for(int j=0; j<5; j++){
						if(table.legal(0, line[i-1][1]+1, color, line[i][1], line[i-1][1]+1)){
							step=new Move(0, line[i-1][1]+1, line[i][1], line[i-1][1]+1);
							return;
						}
						if(table.legal(4, line[i-1][1]+1, color, line[i][1], line[i-1][1]+1)){
							step=new Move(4, line[i-1][1]+1, line[i][1], line[i-1][1]+1);
							return;
						}
						for(int k=0; k<5; k++){
							if(table.legal(k, line[i-1][1]+1, color, j, line[i-1][1]+1)){
								step=new Move(k, line[i-1][1]+1, j, line[i-1][1]+1);
								return;
							}
						}
					}
				}
			}
		}
		//oszlop
		if(line[0][1]==line[1][1]){
			for(int i=1; i<4; i++){
				if(line[i][0]!=line[i-1][0]+1){
					for(int j=0; j<5; j++){
						if(table.legal(line[i-1][0]+1, 0, color, line[i-1][0]+1, line[i][1])){
							step=new Move(line[i-1][0]+1, 0, line[i-1][0]+1, line[i][1]);
							return;
						}
						if(table.legal(line[i-1][0]+1, 4, color, line[i-1][0]+1, line[i][1])){
							step=new Move(line[i-1][0]+1, 4, line[i-1][0]+1, line[i][1]);
							return;
						}
						for(int k=0; k<5; k++){
							if(table.legal(line[i-1][0]+1, k, color, line[i-1][0]+1, j)){
								step=new Move(line[i-1][0]+1, k, line[i-1][0]+1, j);
								return;
							}
						}
					}
					
				}
			}
		}
		//atlo
		/*if(line[0][0]==line[0][1]||line[1][0]==line[1][1]){
			for(int i=0; i<4; i++){
				if(line[i][0]!=line[i+1][0]+1 || line[i][0]!=line[i+1][0]-1){}
					if(table.legal(line[i][0], 0, color, line[i][0], ))
			}
		}*/
		
		return;
	}
}
