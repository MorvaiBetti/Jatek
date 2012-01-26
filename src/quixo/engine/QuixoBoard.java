package quixo.engine;
public class QuixoBoard implements Cloneable {
	/**(sor; oszlop)
	 * @empty ures mezo*/
	public static final int empty=2;
	/**@X a mezon X egy babuja van*/
	public static final int X=0;
	/**@O a mezon O egy babuja van*/
	public static final int O=1;
	/**@table jatektabla*/
	public int[][] table;
	
	/**konstruktor*/
	public QuixoBoard(){
		table=new int[5][5];
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				table[i][j]=empty;
			}
		}
	}
	
	/**table clone*/
	public Object clone(){
		QuixoBoard board=new QuixoBoard();
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				board.table[i][j]=this.table[i][j];
			}
		}
		return board;
	}
	
	/**a table egy adott mezojenek erteket adja vissza*/
	public int getField(int x, int y){
		return table[x][y];
	}
	
	public void setField(int x, int y, int model){
		table[x][y]=model;
	}
	
	/**Az adott lepes helyes-e?*/
	public boolean legal(int x, int y, int model, int nx, int ny){
		/**csak sajat szinut vagy semlegeset lehet */
		if(table[x][y]!=model && table[x][y]!=empty){
			return false;
		}
		
		/**ahonnan elvettem, oda nem rakhatom vissza*/
		if(x==nx && y==ny){
			return false;
		}
		
		/**ha nem letezo mezorol akarunk kivenni/visszatenni*/
		if(x<0 || y<0 || x>4 || y>4 || nx<0 || ny<0 || nx>4 || ny>4){
			return false;
		}
		
		/**csak a palya szelerol lehet venni*/
		if(x!=0 && x!=4 && y!=0 && y!=4){
			return false;
		}
		
		/**csak a palya szelere lehet visszatenni*/
		if(nx!=0 && nx!=4 && ny!=0 && ny!=4){
			return false;
		}
		
		/**melyik kivetel eseten hova lehet tenni*/
		if(x==0 && ((nx==x && ny==0) ||(nx==x && ny==4) || (nx==4 && y==ny) )){
			return true;
		}else if(x==4 &&((x==nx && ny==0) || (nx==x && ny==4) || (nx==0 && ny==y))){
			return true;
		}else if(y==0 && ((ny==y && nx==0) ||(ny==y && nx==4) || (ny==4 && x==nx) )){
			return true;
		}else if(y==4 &&((y==ny && nx==0) || (ny==y && nx==4) || (ny==0 && nx==x))){
			return true;
		}
		return false;
	}
	
	/**@param model megnyerte-e a jatekot*/
	public boolean win(int model){
		int db=0;
		/**sorokon megy vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(getField(i,j)==model){
					db++;
					if(db==5){
						return true;
					}
				}else {
					db=0; 
					break;
				}
			}
		}
		/**oszlopokon megy vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(getField(j,i)==model){
					db++;
					if(db==5){
						return true;
					}
				}else {
					db=0; 
					break;
				}
			}
		}
		/**foatlo es mellekatlo*/
		int db2=0;
		for(int i=0; i<5; i++){
			if(getField(i,i)==model){
				db++;
				if(db==5){
					return true;
				}
			}
			if(getField(i, 4-i)==model){
				db2++;
				if(db2==5){
					return true;
				}
			}
		}
		return false;
	}
	
	/**A jatektablan vegrehajtja a lepest*/
	public void makeStep(int x, int y, int model, int nx, int ny){
		/**ha az eredeti sorba rakom vissza*/
		if(x==nx){
			/**jobbrol tolok*/
			if(y<ny){
				for(int i=0; i+y<4; i++){
					table[x][y+i]=table[x][y+i+1];
				}
				table[nx][ny]=model;
				return;
			}
			/**balrol tolok*/
			if(y>ny){
				for(int i=0; y-i>0; i++){
					table[x][y-i]=table[x][y-i-1];
				}
				table[nx][ny]=model;
				return;
			}
		}
		
		/**ha az eredeti oszlopba rakom vissza*/
		if(y==ny){
			/**alulrol tolok*/
			if(x<nx){
				for(int i=0; i+x<4; i++){
					table[x+i][y]=table[x+i+1][y];
				}
				table[nx][ny]=model;
				return;
			}
			/**felulrol tolok*/
			if(x>nx){
				for(int i=0; x-i>0; i++){
					table[x-i][y]=table[x-i-1][y];
				}
				table[nx][ny]=model;
				return;
			}
		}
	}
	
	/**melyik mintabol hany darab van a tablan*/
	public void piece(){
		int x=0, o=0, e=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table[i][j]==X){
					x++;
				}
				if(table[i][j]==O){
					o++;
				}
				if(table[i][j]==empty){
					e++;
				}
			}
		}
		System.out.println("X="+x+" O="+o+" Empty="+e);
	}
	
	public String toString(){
		String s="     0  1  2  3  4\n\n 0  ";
		for(int i=0; i<5; i++){
			for(int k=0; k<5; k++){
				if(getField(i,k)==empty){
					s=s+" - ";
				}
				if(getField(i,k)==X){
					s=s+" X ";
				}
				if(getField(i,k)==O){
					s=s+" O ";
				}
			}
			if(i==4){
				break;
			}
			s=s+"\n " + (i+1)+"  ";
		}
		return s;
	}
}