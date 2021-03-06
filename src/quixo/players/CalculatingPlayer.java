package quixo.players;

import quixo.engine.QuixoPlayer;
import quixo.engine.QuixoBoard;

public abstract class CalculatingPlayer extends QuixoPlayer{
	/**@fields tabla minden mezojenek kulon-kulon az erteke*/
	protected int[][] fields=new int[25][7];
	/**@my sajat babu erteke*/
	protected int my=3;
	/**@your ellenfel babujanak erteke*/
	protected int your=-3;
	/**@nobody ures mezo erteke*/
	protected int nobody=1;
	
	/**Kiszamolja, melyik babubol hany darab van az adott mezo soraban, oszlopaban atlokban. A babuk erteket hozzaadja a mezo ertekehez. 
	 * sorSzam, oszlopSzam, oszlopVonal, sorVonal, foatloVonal, mellekatloVonal, mezoErtek*/
	protected void calculat(){
		empty();
		int k=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				fields[k][0]=i;
				fields[k][1]=j;
				for(int l=0; l<5; l++){
					if(l!=i){
						/**oszlop*/
						if(table.getField(l,j)==color){
							fields[k][2]+=my;
						}
						if(table.getField(l,j)==QuixoBoard.empty){
							fields[k][2]+=nobody;
						}
						if(table.getField(l,j)==opponentColor){
							fields[k][2]+=your;
						}
					}
					if(l!=j){
						/**sor*/
						if(table.getField(i,l)==color){
							fields[k][3]+=my;
						}
						if(table.getField(i,l)==QuixoBoard.empty){
							fields[k][3]+=nobody;
						}
						if(table.getField(i,l)==opponentColor){
							fields[k][3]+=your;
						}
					}
					if(i==j){
						/**foatlo*/
						if(l!=i){
							if(table.getField(l,l)==color){
								fields[k][4]+=my;
							}
							if(table.getField(l,l)==QuixoBoard.empty){
								fields[k][4]+=nobody;
							}
							if(table.getField(l,l)==opponentColor){
								fields[k][4]+=your;
							}
						}
					}
					if(i==4-j){
						/**mellekatlo*/
						if(l!=i){
							if(table.getField(l,4-l)==color){
								fields[k][5]+=my;
							}
							if(table.getField(l,4-l)==QuixoBoard.empty){
								fields[k][5]+=nobody;
							}
							if(table.getField(l,4-l)==opponentColor){
								fields[k][5]+=your;
							}
						}
					}
				}
				k++;
			}
		}
		
	}
	
	/**kinullazza a fields tombot*/
	protected void empty(){
		for(int i=0; i<25; i++){
			for(int j=2; j<7; j++){
				fields[i][j]=0;
			}
		}
	}
	
}
