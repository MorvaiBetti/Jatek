public class CalculatPlayer extends RandomPlayer{
//	public QuixoBoard table;
//	public int color; 						//A jatekos sajat mintaja
	//public int opponentColor; 				//Az ellenfel mintaja
	public long maxTime;
	public int[][] fields;
	
	public CalculatPlayer(){
		fields=new int[25][6];
	}
	
	public Move nextMove(Move prevStep, long time){
		calculat();
		for(int i=0; i<25; i++){
			for(int j=0; j<6; j++){
				System.out.print(" "+fields[i][j]+" ");
			}
			System.out.println();
		}
		return super.nextMove(prevStep, time);
	}
	
	//sorSzam, oszlopSzam, oszlopVonal, sorVonal, foatloVonal, mellekatloVonal
	public void calculat(){
		int k=0;
		System.out.println("color "+color+" eszin "+opponentColor);
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				fields[k][0]=i;
				fields[k][1]=j;
				for(int l=0; l<5; l++){
					if(l!=i){
						//oszlop
						if(table.getField(l,j)==color){
							fields[k][2]=+2;
						//	System.out.println("oszlop color "+l+" "+j);
						}
						if(table.getField(l,j)==QuixoBoard.empty){
							fields[k][2]++;
						//	System.out.println("oszlop empty "+l+" "+j);
						//	System.out.println("oszlop empty");
						}
						if(table.getField(l,j)==opponentColor){
							fields[k][2]--;
						//	System.out.println("oszlop opponentColor "+l+" "+j);
						//	System.out.println("oszlop oponentColor");
						}
					}
					if(l!=j){
						//sor
						if(table.getField(i,l)==color){
							fields[k][3]=+2;
						//	System.out.println("sor color "+i+" "+l);
						//	System.out.println("sor color");
						}
						if(table.getField(i,l)==QuixoBoard.empty){
							fields[k][3]++;
						//	System.out.println("sor empty "+i+" "+l);
						//	System.out.println("sor empty");
						}
						if(table.getField(i,l)==opponentColor){
							fields[k][3]--;
						//	System.out.println("sor opponentColor "+i+" "+l);
						//	System.out.println("sor opponentColor");
						}
					}
					/*if(l!=i && l!=j){
						//foatlo
						if(table.getField(l,l)==color){
							fields[k][4]=+2;
						//	System.out.println("fo color "+l+" "+l);
						//	System.out.println("fo color");
						}
						if(table.getField(l,l)==QuixoBoard.empty){
							fields[k][4]++;
						//	System.out.println("fo empty "+l+" "+l);
						//	System.out.println("fo empty");
						}
						if(table.getField(l,l)==opponentColor){
							fields[k][4]--;
						//	System.out.println("fo opponentColor "+l+" "+l);
						//	System.out.println("fo opponentColor");
						}
					}
					if(i!=l && j!=4-l){
						//mellekatlo
						if(table.getField(l,4-l)==color){
							fields[k][5]=+2;
						//	System.out.println("mellek color "+l+" "+(4-l));
						//	System.out.println("mellek color");
						}
						if(table.getField(l,4-l)==QuixoBoard.empty){
							fields[k][5]++;
						//	System.out.println("mellek empty "+l+" "+(4-l));
						//	System.out.println("mellek empty");
						}
						if(table.getField(l,4-l)==opponentColor){
							fields[k][5]--;
						//	System.out.println("mellek opponentColor "+l+" "+(4-l));
						//	System.out.println("mellek opponentColor");
						}
					}*/
				}
				k++;
			}
		}
	}
	
}
