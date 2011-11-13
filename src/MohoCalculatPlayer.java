
public class MohoCalculatPlayer extends CalculatPlayer{
	public int max;
	public int id;

	public Move nextMove(Move prevStep, long time){
		calculat();
		for(int i=0; i<25; i++){
			for(int j=3; j<6; j++){
				fields[i][2]+=fields[i][j];
				
			}
		//	System.out.println(fields[i][2]);
		}
		find();
		while(true){
			//bal felso sarok
			//fuggolegesen van szelen
			if(fields[id][1]==0 || fields[id][1]==4){
				if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
					for(int i=0; i<fields[id][0]; i++){
						if(table.legal(0, fields[id][1], color, 4, fields[id][1])){
							step=new Move(0, fields[id][1], 4, fields[id][1]);
							System.out.println("Lepesem: "+step);
							return step;
						}
					}
				}
				if(fields[id][0]-1 >-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
					for(int i=0; i<fields[id][0]; i++){
						if(table.legal(4, fields[id][1], color, 0, fields[id][1])){
							step=new Move(4, fields[id][1], 0, fields[id][1]);
							System.out.println("Lepesem: "+step);
							return step;
						}
					}
				}
			}
			//vizszintesen van szelen
			if(fields[id][0]==0 || fields[id][0]==4){
				if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
					for(int i=fields[id][0]+1;  i<5; i++){
						if(table.legal(fields[id][0], 0, color, fields[id][0], 4)){
							step=new Move(fields[id][0], 0, fields[id][0], 4);
							System.out.println("Lepesem: "+step);
							return step;
						}
					}
				}
				if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
					for(int i=0;  i<fields[id][0]; i++){
							if(table.legal(fields[id][0], 4, color, fields[id][0], 0)){
								step=new Move(fields[id][0], 4, fields[id][0], 0);
								System.out.println("Lepesem: "+step);
								return step;
							}
					}
				}
			}
			
			//oszlopban kovetkezot tolom a jo helyre
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				if(table.legal(0, fields[id][1], color, 4, fields[id][1])){
					step=new Move(0, fields[id][1], 4, fields[id][1]);
					System.out.println("Lepesem: "+step);
					return step;
				}
			}
			//oszlopban elozot tolom a jo helyre
			if(fields[id][0]-1>-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				if(table.legal(4, fields[id][1], color, 0, fields[id][1])){
					step=new Move(4, fields[id][1], 0, fields[id][1]);
					System.out.println("Lepesem: "+step);
					return step;
				}
			}
			//sorban kovetkezot tolom a jo helyre
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				if(table.legal(fields[id][0], 0, color, fields[id][0], 4)){
					step=new Move(fields[id][0], 0, fields[id][0], 4);
					System.out.println("Lepesem: "+step);
					return step;
				}
			}
			//sorban elozot tolom a jo helyre
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				if(table.legal(fields[id][0], 4, color, fields[id][0], 0)){
					step=new Move(fields[id][0], 4, fields[id][0], 0);
					System.out.println("Lepesem: "+step);
					return step;
				}
			}
			if(table.getField(fields[id][0], 4-fields[id][1])==QuixoBoard.empty && table.legal(fields[id][0], 4-fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(fields[id][0], 4-fields[id][1], fields[id][0], fields[id][1]);
				System.out.println("Lepesem: "+step);
				return step;
			}
			if(table.getField(4-fields[id][0], fields[id][1])==QuixoBoard.empty && table.legal(4-fields[id][0], fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(4-fields[id][0], fields[id][1], fields[id][0], fields[id][1]);
				System.out.println("Lepesem: "+step);
				return step;
			}
			
			if(table.legal(fields[id][0], 4-fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(fields[id][0], 4-fields[id][1], fields[id][0], fields[id][1]);
				System.out.println("Lepesem: "+step);
				return step;
			}
			if(table.legal(4-fields[id][0], fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(4-fields[id][0], fields[id][1], fields[id][0], fields[id][1]);
				System.out.println("Lepesem: "+step);
				return step;
			}
			

			fields[id][2]=0;
			find();
		}	
	}
	
	public int find(){
		max=0;
		for(int i=0; i<25; i++){
			if(max<fields[i][2]){
				max=fields[i][2];
				id=i;
			}
		}
		if(table.getField(fields[id][0], fields[id][1])==color){
			fields[id][2]=0;
			return find();
		}
		return id;
	}
}
