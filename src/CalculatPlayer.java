public abstract class CalculatPlayer extends SimplePlayer{
	public int[][] fields=new int[25][7];
	public int my=3;
	public int your=-3;
	public int nobody=1;
	
	/**sorSzam, oszlopSzam, oszlopVonal, sorVonal, foatloVonal, mellekatloVonal, mezoErtek*/
	public void calculat(){
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
	
	public void empty(){
		for(int i=0; i<25; i++){
			for(int j=2; j<7; j++){
				fields[i][j]=0;
			}
		}
	}
	
}
