public class DefendCalculatPlayer extends CalculatPlayer{
	public int min;
	public int id;

	public Move nextMove(Move prevStep, long time){
		calculat();
		step=null;
		for(int i=0; i<25; i++){
			for(int j=2; j<6; j++){
				//ha mar csak egy lepes hianyzik hogy nyerjek
				if((0-12)>=fields[i][j]){
					System.out.println("ertekem!! "+fields[i][j]);
					//sorban van negy
					if(j==3){
						System.out.println("sor eleje");
						for(int k=0; k<5; k++){
							if(table.getField(fields[i][0], k)==opponentColor){
								System.out.println("ide kell lepni 1 "+(4-fields[i][0])+" "+k+" "+fields[i][0]+" "+fields[i][1]);
								if(table.getField(4-fields[i][0], k)!=opponentColor && table.legal(4-fields[i][0], k, color, fields[i][0], k)){
									step=new Move(4-fields[i][0], k, fields[i][0], k);
									System.out.println("mi lesz "+step);
									return step;
								}
							}
						}
						System.out.println("sor vege "+step);
					}
					//oszlopban van negy
					if(j==2 ){
						System.out.println("oszlop eleje");
						for(int k=0; k<5; k++){
							if(table.getField(k, fields[i][1])==opponentColor){
								System.out.println("ide kell lepni 3 "+k+" "+(4-fields[i][1])+" "+fields[i][0]+" "+fields[i][1]);
								
								if(table.getField(k, 4-fields[i][1])!=opponentColor && table.legal(k, 4-fields[i][1], color, k, fields[i][1])){
									step=new Move(k, 4-fields[i][1], k, fields[i][1]);
									System.out.println("mi lesz "+step);
									return step;
								}
							}
						}
						System.out.println("oszlop vege "+step);
					}
				}
				fields[i][6]+=fields[i][j];
			}
			System.out.println(i+". ertek: "+fields[i][6]+ " k: "+fields[i][0]+" "+fields[i][1]);
		}
		System.out.println("nini");
		if(step==null){
			System.out.println("nincs gaz");
			find();
			newStep();
		}
		return step;
	}
	
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
	
	public void newStep(){
		System.out.println("min koord.: " +fields[id][0]+" "+fields[id][1]+" index: "+id);
		
		//ha ott mar a sajat figuram van
		if(table.getField(fields[id][0], fields[id][1])==color){
			fields[id][6]=100;
			again();
			return;
		}
		
		//ha vmelyik csucsra akarok tenni
		if((fields[id][1]==0 || fields[id][1]==4) && (fields[id][0]==0 || fields[id][0]==4)){
			for(int i=0; i<5; i++){
				//elobb az üreseket
				if(table.getField(Math.abs(i-fields[id][0]), fields[id][1])==QuixoBoard.empty && table.legal(Math.abs(i-fields[id][0]), fields[id][1], color, fields[id][0], fields[id][1])){
					step=new Move(Math.abs(i-fields[id][0]), fields[id][1], fields[id][0], fields[id][1]);
					System.out.println("Lepesem: "+step);
					return;
				}
				if(table.getField(fields[id][0], Math.abs(i-fields[id][1]))==QuixoBoard.empty && table.legal(fields[id][0], Math.abs(i-fields[id][1]), color, fields[id][0], fields[id][1])){
					step=new Move(fields[id][0], Math.abs(i-fields[id][1]), fields[id][0], fields[id][1]);
					System.out.println("Lepesem: "+step);
					return;
				}
			}
			for(int i=0; i<5; i++){
				//majd a sajatokat
				if(table.getField(Math.abs(i-fields[id][0]), fields[id][1])==color && table.legal(Math.abs(i-fields[id][0]), fields[id][1], color, fields[id][0], fields[id][1])){
					step=new Move(Math.abs(i-fields[id][0]), fields[id][1], fields[id][0], fields[id][1]);
					System.out.println("Lepesem: "+step);
					return;
				}
				if(table.getField(fields[id][0], Math.abs(i-fields[id][1]))==color && table.legal(fields[id][0], Math.abs(i-fields[id][1]), color, fields[id][0], fields[id][1])){
					step=new Move(fields[id][0], Math.abs(i-fields[id][1]), fields[id][0], fields[id][1]);
					System.out.println("Lepesem: "+step);
					return;
				}
			}
		}
		
		//fuggolegesen van szelen
		if(fields[id][1]==0 || fields[id][1]==4){
			//elobb forditani probalok
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==QuixoBoard.empty && table.legal(i, fields[id][1], color, 4, fields[id][1])){
						step=new Move(i, fields[id][1], 4, fields[id][1]);
						System.out.println("Lepesem: "+step);
						return;
					}
				}
			}
			if(fields[id][0]-1 >-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==QuixoBoard.empty && table.legal(i, fields[id][1], color, 0, fields[id][1])){
						step=new Move(i, fields[id][1], 0, fields[id][1]);
						System.out.println("Lepesem: "+step);
						return;
					}
				}
			}
			//ha nem tudok forditani
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==color && table.legal(i, fields[id][1], color, 4, fields[id][1])){
						step=new Move(i, fields[id][1], 4, fields[id][1]);
						System.out.println("Lepesem: "+step);
						return;
					}
				}
			}
			if(fields[id][0]-1 >-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				for(int i=0; i<fields[id][0]; i++){
					if(table.getField(i, fields[id][1])==color && table.legal(i, fields[id][1], color, 0, fields[id][1])){
						step=new Move(i, fields[id][1], 0, fields[id][1]);
						System.out.println("Lepesem: "+step);
						return;
					}
				}
			}
			if(table.getField(4-fields[id][0], fields[id][1])!=opponentColor && table.legal(4-fields[id][0], fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(4-fields[id][0], fields[id][1], fields[id][0], fields[id][1]);
				return;
			}
		}
		
		//vizszintesen van szelen
		if(fields[id][0]==0 || fields[id][0]==4){
			//forditani probalok
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				for(int i=fields[id][0]+1;  i<5; i++){
					if(table.getField(fields[id][0], i)==QuixoBoard.empty && table.legal(fields[id][0], i, color, fields[id][0], 4)){
						step=new Move(fields[id][0], i, fields[id][0], 4);
						System.out.println("Lepesem: "+step);
						return;
					}
				}
			}
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				for(int i=0;  i<fields[id][0]; i++){
						if(table.getField(fields[id][0], i)==QuixoBoard.empty && table.legal(fields[id][0], i, color, fields[id][0], 0)){
							step=new Move(fields[id][0], i, fields[id][0], 0);
							System.out.println("Lepesem: "+step);
							return;
						}
				}
			}
			//ha nem tudok forditani
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				for(int i=fields[id][0]+1;  i<5; i++){
					if(table.getField(fields[id][0], i)==color && table.legal(fields[id][0], i, color, fields[id][0], 4)){
						step=new Move(fields[id][0], i, fields[id][0], 4);
						System.out.println("Lepesem: "+step);
						return;
					}
				}
			}
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				for(int i=0;  i<fields[id][0]; i++){
						if(table.getField(fields[id][0], i)==color && table.legal(fields[id][0], i, color, fields[id][0], 0)){
							step=new Move(fields[id][0], i, fields[id][0], 0);
							System.out.println("Lepesem: "+step);
							return;
						}
				}
			}
			if(table.getField(fields[id][0], 4-fields[id][1])!=opponentColor && table.legal(fields[id][0], 4-fields[id][1], color, fields[id][0], fields[id][1])){
				step=new Move(fields[id][0], 4-fields[id][1], fields[id][0], fields[id][1]);
				return;
			}
		}
		
		//ha nincs szelen
		if(fields[id][0]!=0 && fields[id][0]!=4 && fields[id][1]!=0 && fields[id][1]!=4){
			//oszlopban kovetkezot tolom a jo helyre
			if(fields[id][0]+1<5 && table.getField(fields[id][0]+1, fields[id][1])==color){
				if((table.getField(0, fields[id][1])==color || table.getField(0, fields[id][1])==QuixoBoard.empty) && table.legal(0, fields[id][1], color, 4, fields[id][1])){
					step=new Move(0, fields[id][1], 4, fields[id][1]);
					System.out.println("Lepesem: "+step);
					return;
				}
			}
			//oszlopban elozot tolom a jo helyre
			if(fields[id][0]-1>-1 && table.getField(fields[id][0]-1, fields[id][1])==color){
				if((table.getField(4, fields[id][1])==color || table.getField(4, fields[id][1])==QuixoBoard.empty) && table.legal(4, fields[id][1], color, 0, fields[id][1])){
					step=new Move(4, fields[id][1], 0, fields[id][1]);
					System.out.println("Lepesem: "+step);
					return;
				}
			}
			//sorban kovetkezot tolom a jo helyre
			if(fields[id][1]+1<5 && table.getField(fields[id][0], fields[id][1]+1)==color){
				if((table.getField(fields[id][0], 0)==color || table.getField(fields[id][0], 0)==QuixoBoard.empty) && table.legal(fields[id][0], 0, color, fields[id][0], 4)){
					step=new Move(fields[id][0], 0, fields[id][0], 4);
					System.out.println("Lepesem: "+step);
					return;
				}
			}
			//sorban elozot tolom a jo helyre
			if(fields[id][1]-1>-1 && table.getField(fields[id][0], fields[id][1]-1)==color){
				if((table.getField(fields[id][0], 4)==color || table.getField(fields[id][0], 4)==QuixoBoard.empty) && table.legal(fields[id][0], 4, color, fields[id][0], 0)){
					step=new Move(fields[id][0], 4, fields[id][0], 0);
					System.out.println("Lepesem: "+step);
					return;
				}
			}
		}
		System.out.println("nincs talalat");
		again();
		return;
	}
	
	public void again(){
		fields[id][6]=100;
		find();
		newStep();
		return;
	}
}