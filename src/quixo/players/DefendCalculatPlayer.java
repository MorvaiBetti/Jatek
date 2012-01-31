package quixo.players;

import quixo.engine.QuixoBoard;
import quixo.engine.Move;

public class DefendCalculatPlayer extends CalculatPlayer{
	public int min;
	public int id;

	public Move nextMove(Move prevStep){	
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		calculat();
		step=null;
		/**Ha egy lepessel nyerhetek*/
		for(int i=0; i<25; i++){
			for(int j=2; j<6; j++){
				/**ha mar csak egy lepes hianyzik hogy nyerjek*/
				if((4*your)>=fields[i][j]){
					/**sorban van negy*/
					if(j==3){
						for(int k=0; k<5; k++){
							if(table.getField(fields[i][0], k)==opponentColor){
								if(table.getField(4-fields[i][0], k)!=opponentColor && table.legal(4-fields[i][0], k, color, fields[i][0], k)){
									step=new Move(4-fields[i][0], k, fields[i][0], k);
									table.makeStep(step, color);
									return step;
								}
							}
						}
					}
					/**oszlopban van negy*/
					if(j==2 ){
						for(int k=0; k<5; k++){
							if(table.getField(k, fields[i][1])==opponentColor){
								if(table.getField(k, 4-fields[i][1])!=opponentColor && table.legal(k, 4-fields[i][1], color, k, fields[i][1])){
									step=new Move(k, 4-fields[i][1], k, fields[i][1]);
									table.makeStep(step, color);
									return step;
								}
							}
						}
					}
				}
				fields[i][6]+=fields[i][j];
			}
		}
	//	if(step==null){
			find();
			newStep();
		//}
		table.makeStep(step, color);
		return step;
	}
	
	/**megkeresi a legveszelyesebb koordinatat*/
	public int find(){
		min=100;
		for(int i=0; i<25; i++){
			if(min>fields[i][6]){
				min=fields[i][6];
				id=i;
			}
		}
		return id;
	}
	
	/**megnezem, hogy lephetek a fields[id][]-edik koordinatar*/
	public void newStep(){
		/**ha ott mar a sajat figuram van*/
		if(table.getField(fields[id][0], fields[id][1])==color){
			fields[id][6]=1000;
			find();
			newStep();
			return;
		}
		
		/**ha vmelyik csucsra akarok tenni*/
		if((fields[id][1]==0 || fields[id][1]==4) && (fields[id][0]==0 || fields[id][0]==4)){
			for(int i=0; i<5; i++){
				/**elobb az �reseket*/
				if(table.getField(Math.abs(i-fields[id][0]), fields[id][1])==QuixoBoard.empty && table.legal(Math.abs(i-fields[id][0]), fields[id][1], color, fields[id][0], fields[id][1])){
					step=new Move(Math.abs(i-fields[id][0]), fields[id][1], fields[id][0], fields[id][1]);
					return;
				}
				if(table.getField(fields[id][0], Math.abs(i-fields[id][1]))==QuixoBoard.empty && table.legal(fields[id][0], Math.abs(i-fields[id][1]), color, fields[id][0], fields[id][1])){
					step=new Move(fields[id][0], Math.abs(i-fields[id][1]), fields[id][0], fields[id][1]);
					return;
				}
			}
			for(int i=0; i<5; i++){
				/**majd a sajatokat*/
				if(table.getField(Math.abs(i-fields[id][0]), fields[id][1])==color && table.legal(Math.abs(i-fields[id][0]), fields[id][1], color, fields[id][0], fields[id][1])){
					step=new Move(Math.abs(i-fields[id][0]), fields[id][1], fields[id][0], fields[id][1]);
					return;
				}
				if(table.getField(fields[id][0], Math.abs(i-fields[id][1]))==color && table.legal(fields[id][0], Math.abs(i-fields[id][1]), color, fields[id][0], fields[id][1])){
					step=new Move(fields[id][0], Math.abs(i-fields[id][1]), fields[id][0], fields[id][1]);
					return;
				}
			}
		}
		
		/**fuggolegesen van szelen*/
		if(fields[id][1]==0 || fields[id][1]==4){
			/**elobb forditani probalok*/
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==QuixoBoard.empty && table.legal(i, fields[id][1], color, 4, fields[id][1])){
						step=new Move(i, fields[id][1], 4, fields[id][1]);
						return;
					}
				}
			}
			if(fields[id][0]-1 >-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==QuixoBoard.empty && table.legal(i, fields[id][1], color, 0, fields[id][1])){
						step=new Move(i, fields[id][1], 0, fields[id][1]);
						return;
					}
				}
			}
			/**ha nem tudok forditani*/
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==color && table.legal(i, fields[id][1], color, 4, fields[id][1])){
						step=new Move(i, fields[id][1], 4, fields[id][1]);
						return;
					}
				}
			}
			if(fields[id][0]-1 >-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==color && table.legal(i, fields[id][1], color, 0, fields[id][1])){
						step=new Move(i, fields[id][1], 0, fields[id][1]);
						return;
					}
				}
			}
			if(table.getField(fields[id][0], 4-fields[id][1])!=opponentColor && table.legal(fields[id][0], 4-fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(fields[id][0], 4-fields[id][1], fields[id][0], fields[id][1]);
				return;
			}
		}
		
		/**vizszintesen van szelen*/
		if(fields[id][0]==0 || fields[id][0]==4){
			/**forditani probalok*/
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				for(int i=fields[id][0]+1;  i<5; i++){
					if(table.getField(fields[id][0], i)==QuixoBoard.empty && table.legal(fields[id][0], i, color, fields[id][0], 4)){
						step=new Move(fields[id][0], i, fields[id][0], 4);
						return;
					}
				}
			}
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				for(int i=0;  i<fields[id][0]; i++){
						if(table.getField(fields[id][0], i)==QuixoBoard.empty && table.legal(fields[id][0], i, color, fields[id][0], 0)){
							step=new Move(fields[id][0], i, fields[id][0], 0);
							return;
						}
				}
			}
			/**ha nem tudok forditani*/
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				for(int i=fields[id][0]+1;  i<5; i++){
					if(table.getField(fields[id][0], i)==color && table.legal(fields[id][0], i, color, fields[id][0], 4)){
						step=new Move(fields[id][0], i, fields[id][0], 4);
						return;
					}
				}
			}
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				for(int i=0;  i<fields[id][0]; i++){
						if(table.getField(fields[id][0], i)==color && table.legal(fields[id][0], i, color, fields[id][0], 0)){
							step=new Move(fields[id][0], i, fields[id][0], 0);
							return;
						}
				}
			}
			if(table.getField(4-fields[id][0], fields[id][1])!=opponentColor && table.legal(4-fields[id][0], fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(4-fields[id][0], fields[id][1], fields[id][0], fields[id][1]);
				return;
			}
		}
		
		/**ha nincs szelen*/
		if(fields[id][0]!=0 && fields[id][0]!=4 && fields[id][1]!=0 && fields[id][1]!=4){
			/**oszlopban kovetkezot tolom a jo helyre*/
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				if((table.getField(0, fields[id][1])==color || table.getField(0, fields[id][1])==QuixoBoard.empty) && table.legal(0, fields[id][1], color, 4, fields[id][1])){
					step=new Move(0, fields[id][1], 4, fields[id][1]);
					return;
				}
			}
			/**oszlopban elozot tolom a jo helyre*/
			if(fields[id][0]-1>-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				if((table.getField(4, fields[id][1])==color || table.getField(4, fields[id][1])==QuixoBoard.empty) && table.legal(4, fields[id][1], color, 0, fields[id][1])){
					step=new Move(4, fields[id][1], 0, fields[id][1]);
					return;
				}
			}
			/**sorban kovetkezot tolom a jo helyre*/
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				if((table.getField(fields[id][0], 0)==color || table.getField(fields[id][0], 0)==QuixoBoard.empty) && table.legal(fields[id][0], 0, color, fields[id][0], 4)){
					step=new Move(fields[id][0], 0, fields[id][0], 4);
					return;
				}
			}
			/**sorban elozot tolom a jo helyre*/
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				if((table.getField(fields[id][0], 4)==color || table.getField(fields[id][0], 4)==QuixoBoard.empty) && table.legal(fields[id][0], 4, color, fields[id][0], 0)){
					step=new Move(fields[id][0], 4, fields[id][0], 0);
					return;
				}
			}
		}
		fields[id][6]=100;
		find();
		newStep();
		return;
	}
}