package quixo.players;

import quixo.engine.QuixoBoard;
import quixo.engine.Move;

public class MohoCalculatPlayer extends CalculatPlayer{
	public int max;
	public int id;

	public Move nextMove(Move prevStep){
		if(prevStep!=null){
			table.makeStep(prevStep, (color+1)%2);
		}
		calculat();
		for(int i=0; i<25; i++){
			for(int j=2; j<6; j++){
				/**ha mar csak egy lepes hianyzik hogy nyerjek*/
				if(fields[i][j]>=(4*my)){
					id=i;
					/**sorban van negy*/
					if(j==3 && table.legal(4-fields[id][0], fields[id][1], color, fields[id][0], fields[id][1])){
						step=new Move(4-fields[id][0], fields[id][1], fields[id][0], fields[id][1]);
						table.makeStep(step, color);
						return step;
					}else if(table.getField(fields[id][0], fields[id][1])==QuixoBoard.empty && table.legal(fields[id][0], fields[id][1], color, fields[id][0], 4-fields[id][1])){
						step=new Move(fields[id][0], fields[id][1], fields[id][0], 4-fields[id][1]);
						table.makeStep(step, color);
						return step;
					}
					/**oszlopban van negy*/
					if(j==2 && table.legal(fields[id][0], 4-fields[id][1], color, fields[id][0], fields[id][1])){
						step=new Move(fields[id][0], 4-fields[id][1], fields[id][0], fields[id][1]);
						table.makeStep(step, color);
						return step;
					}else if(table.getField(fields[id][0], fields[id][1])==QuixoBoard.empty && table.legal(fields[id][0], fields[id][1], color, 4-fields[id][0], fields[id][1])){
						step=new Move(fields[id][0], fields[id][1], 4-fields[id][0], fields[id][1]);
						table.makeStep(step, color);
						return step;
					}
				}
				fields[i][6]+=fields[i][j];
			}
		}
		find();
		newStep();
		table.makeStep(step, color);
		return step;
	}
	
	public int find(){
		max=0;
		for(int i=0; i<25; i++){
			if(max<fields[i][6]){
				max=fields[i][6];
				id=i;
			}
		}
		return id;
	}
	
	public void newStep(){
		/**ha ott mar a sajat figuram van*/
		if(table.getField(fields[id][0], fields[id][1])==color){
			fields[id][6]=0;
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
		fields[id][6]=0;
		find();
		newStep();
		return;
	}
}