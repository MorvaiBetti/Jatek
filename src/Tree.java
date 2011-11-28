public abstract class Tree extends SimplePlayer{
	public int id;
	public Move[] steps;
	public int[] values;
	public int my=3;
	public int your=-3;
	public int nobody=1;
	public int[][] fields=new int[25][7];
	public QuixoBoard myTable;
	
	public void setTable(QuixoBoard qt) {
		table=qt;
	}
	
	public int getColor() {
		return color;
	}
	
	public void datas(int sequence, long time) {
		steps=new Move[25*5];
		values=new int[25*5];
		super.datas(sequence, time);
		return;
	}
	
	
	//a=sor b=oszlop
		public void nextStep(int a, int b){
			myTable=(QuixoBoard) table.clone();
			//ha vmelyik csucsra akarok tenni
			if((b==0 || a==4) && (a==0 || b==4)){
				for(int i=0; i<5; i++){
					//elobb az �reseket
					if(table.getField(Math.abs(i-a), b)==QuixoBoard.empty && table.legal(Math.abs(i-a), b, color, a, b)){	
						myTable.makeStep(Math.abs(i-a), b, color, a, b);
						values[id]=sum();
						steps[id]=new Move(Math.abs(i-a), b, a, b);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
					if(table.getField(a, Math.abs(i-b))==QuixoBoard.empty && table.legal(a, Math.abs(i-b), color, a, b)){
						myTable.makeStep(a, Math.abs(i-b), color, a, b);
						values[id]=sum();
						steps[id]=new Move(a, Math.abs(i-b), a, b);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
				}
				//majd a sajatokat
				for(int i=0; i<5; i++){
					if(table.getField(Math.abs(i-a), b)==color && table.legal(Math.abs(i-a), b, color, a, b)){
						myTable.makeStep(Math.abs(i-a), b, color, a, b);
						values[id]=sum();
						steps[id]=new Move(Math.abs(i-a), b, a, b);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
					if(table.getField(a, Math.abs(i-b))==color && table.legal(a, Math.abs(i-b), color, a, b)){
						myTable.makeStep(a, Math.abs(i-b), color, a, b);
						values[id]=sum();
						steps[id]=new Move(a, Math.abs(i-b), a, b);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
				}
			}
			
			//fuggolegesen van szelen
			if((b==0 || b==4) && a!=0 && a!=4){
				//elobb forditani probalok
				if(a+1<5 && table.getField(a+1, b)==color){
					for(int i=0; i<a; i++){
						if(table.getField(i, b)==QuixoBoard.empty && table.legal(i, b, color, 4, b)){
							myTable.makeStep(i, b, color, 4, b);
							values[id]=sum();
							steps[id]=new Move(i, b, 4, b);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				if(a-1 >-1 && table.getField(a-1, b)==color){
					for(int i=0; i<a; i++){
						if(table.getField(i, b)==QuixoBoard.empty && table.legal(i, b, color, 0, b)){
							myTable.makeStep(i, b, color, 0, b);
							values[id]=sum();
							steps[id]=new Move(i, b, 0, b);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				//ha nem tudok forditani
				if(a+1<5 && table.getField(a+1, b)==color){
					for(int i=0; i<a; i++){
						if(table.getField(i, b)==color && table.legal(i, b, color, 4, b)){
							myTable.makeStep(i, b, color, 4, b);
							values[id]=sum();
							steps[id]=new Move(i, b, 4, b);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				if(a-1 >-1 && table.getField(a-1, b)==color){
					for(int i=0; i<a; i++){
						if(table.getField(i, b)==color && table.legal(i, b, color, 0, b)){
							myTable.makeStep(i, b, color, 0, b);
							values[id]=sum();
							steps[id]=new Move(i, b, 0, b);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				if(table.getField(a, 4-b)!=opponentColor && table.legal(a, 4-b, color, a, b)){
					myTable.makeStep(a, 4-b, color, a, b);
					values[id]=sum();
					steps[id]=new Move(a, 4-b, a, b);
					id++;
					myTable=(QuixoBoard) table.clone();
				}
			}
			
			//vizszintesen van szelen
			if((a==0 || a==4) && b!=0 && b!=4){
				//forditani probalok
				if(b+1<5 && table.getField(a, b+1)==color){
					for(int i=a+1;  i<5; i++){
						if(table.getField(a, i)==QuixoBoard.empty && table.legal(a, i, color, a, 4)){
							myTable.makeStep(a, i, color, a, 4);
							values[id]=sum();
							steps[id]=new Move(a, i, a, 4);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				if(b-1>-1 && table.getField(a, b-1)==color){
					for(int i=0;  i<a; i++){
						if(table.getField(a, i)==QuixoBoard.empty && table.legal(a, i, color, a, 0)){
							myTable.makeStep(a, i, color, a, 0);
							values[id]=sum();
							steps[id]=new Move(a, i, a, 0);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				//ha nem tudok forditani
				if(b+1<5 && table.getField(a, b+1)==color){
					for(int i=a+1;  i<5; i++){
						if(table.getField(a, i)==color && table.legal(a, i, color, a, 4)){
							myTable.makeStep(a, i, color, a, 4);
							values[id]=sum();
							steps[id]=new Move(a, i, a, 4);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				if(b-1>-1 && table.getField(a, b-1)==color){
					for(int i=0;  i<a; i++){
						if(table.getField(a, i)==color && table.legal(a, i, color, a, 0)){
							myTable.makeStep(a, i, color, a, 0);
							values[id]=sum();
							steps[id]=new Move(a, i, a, 0);
							id++;
							myTable=(QuixoBoard) table.clone();
						}
					}
				}
				if(table.getField(4-a, b)!=opponentColor && table.legal(4-a, b, color, a, b)){
					myTable.makeStep(4-a, b, color, a, b);
					values[id]=sum();
					steps[id]=new Move(4-a, b, a, b);
					id++;
					myTable=(QuixoBoard) table.clone();
					
				}
			}
			
			//ha nincs szelen
			if(a!=0 && a!=4 && b!=0 && b!=4){
				//oszlopban kovetkezot tolom a jo helyre
				if(a+1<5 && table.getField(a+1, b)==color){
					if((table.getField(0, b)==color || table.getField(0, b)==QuixoBoard.empty) && table.legal(0, b, color, 4, b)){
						myTable.makeStep(0, b, color, 4, b);
						values[id]=sum();
						steps[id]=new Move(0, b, 4, b);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
				}
				//oszlopban elozot tolom a jo helyre
				if(a-1>-1 && table.getField(a-1, b)==color){
					if((table.getField(4, b)==color || table.getField(4, b)==QuixoBoard.empty) && table.legal(4, b, color, 0, b)){
						myTable.makeStep(4, b, color, 0, b);
						values[id]=sum();
						steps[id]=new Move(4, b, 0, b);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
				}
				//sorban kovetkezot tolom a jo helyre
				if(b+1<5 && table.getField(a, b+1)==color){
					if((table.getField(a, 0)==color || table.getField(a, 0)==QuixoBoard.empty) && table.legal(a, 0, color, a, 4)){
						myTable.makeStep(a, 0, color, a, 4);
						values[id]=sum();
						steps[id]=new Move(a, 0, a, 4);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
				}
				//sorban elozot tolom a jo helyre
				if(b-1>-1 && table.getField(a, b-1)==color){
					if((table.getField(a, 4)==color || table.getField(a, 4)==QuixoBoard.empty) && table.legal(a, 4, color, a, 0)){
						myTable.makeStep(a, 4, color, a, 0);
						values[id]=sum();
						steps[id]=new Move(a, 4, a, 0);
						id++;
						myTable=(QuixoBoard) table.clone();
					}
				}
			}
		}

		public void calculate(){
			emptyFields();
			int k=0;
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					fields[k][0]=i;
					fields[k][1]=j;
					for(int l=0; l<5; l++){
						if(l!=i){
							//oszlop
							if(myTable.getField(l,j)==color){
								fields[k][2]+=my;
							}
							if(myTable.getField(l,j)==QuixoBoard.empty){
								fields[k][2]+=nobody;
							}
							if(myTable.getField(l,j)==opponentColor){
								fields[k][2]+=your;
							}
						}
						if(l!=j){
							//sor
							if(myTable.getField(i,l)==color){
								fields[k][3]+=my;
							}
							if(myTable.getField(i,l)==QuixoBoard.empty){
								fields[k][3]+=nobody;
							}
							if(myTable.getField(i,l)==opponentColor){
								fields[k][3]+=your;
							}
						}
						if(i==j){
							//foatlo
							if(l!=i){
								if(myTable.getField(l,l)==color){
									fields[k][4]+=my;
								}
								if(myTable.getField(l,l)==QuixoBoard.empty){
									fields[k][4]+=nobody;
								}
								if(myTable.getField(l,l)==opponentColor){
									fields[k][4]+=your;
								}
							}
						}
						if(i==4-j){
							//mellekatlo
							if(l!=i){
								if(myTable.getField(l,4-l)==color){
									fields[k][5]+=my;
								}
								if(myTable.getField(l,4-l)==QuixoBoard.empty){
									fields[k][5]+=nobody;
								}
								if(myTable.getField(l,4-l)==opponentColor){
									fields[k][5]+=your;
								}
							}
						}
					}
					k++;
				}
			}
		}
		
		public void emptyFields(){
			for(int i=0; i<25; i++){
				for(int j=2; j<6; j++){
					fields[i][j]=0;
				}
			}
		}
		
		public void emptyAll(){
			for(int i=0; i<25*5; i++){
				values[i]=0;
				steps[i]=null;
			}
		}
		
		public int sum(){
			calculate();
			int value=0;
			for(int i=0; i<25; i++){
				for(int j=2; j<6; j++){
					value=value+fields[i][j];
				}
			}
			return value;
		}
		
}
