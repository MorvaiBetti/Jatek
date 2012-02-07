package quixo.engine;

import java.util.ArrayList;

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
	
	/**a table egy adott mezojenek erteket adja vissza
	 * @param x koordinata
	 * @param y koordinata*/
	public int getField(int x, int y){
		return table[x][y];
	}
	
	/**Beallitja a tabla adott mezojet egy adott mintara
	 * @param x tabla x koordinataja
	 * @param y tabla y koordinataja
	 * @param model milyen mintaju legyen a mezo*/
	public void setField(int x, int y, int model){
		table[x][y]=model;
	}
	
	/**Az adott lepes helyes-e?
	 * @param x kivetel x koordinataja
	 * @param y kivetel y koordinataja
	 * @param model milyen mintaval van a lepes
	 * @param nx visszatetel x koordinataja
	 * @param ny visszatetel y koordinataja*/
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
	
	/**Az adott jatekos nyert-e.
	 * @param model megnyerte-e a jatekot*/
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
	
	/**A jatektablan vegrehajtja a lepest
	 * @param m lepes
	 * @param model lepes mintaja*/
	public void makeStep(Move m, int model){
		makeStep(m.x, m.y, model, m.nx, m.ny);
	}
	
	/**A jatektablan vegrehajtja a lepest
	 * @param x kivetel x koordinataja
	 * @param y kivetel y koordinataja
	 * @param model milyen mintaval van a lepes
	 * @param nx visszatetel x koordinataja
	 * @param ny visszatetel y koordinataja*/
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
	
	/**Melyik mintabol hany darab van a tablan*/
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
	
	/**Adott minta lehetseges lepeseinek listajat adja vissza.
	 * @param color a minta, aminek a lepeseit vizsgalom*/
	public ArrayList<Move> nextSteps(int color){
		ArrayList<Move> steps=new ArrayList<Move>();
		Move step;
		int j=0;
		for(int i=0; i<5; i++){ 					/**A lehetseges stepsen vegigmegy, es steps-hez hozzaadja a legal stepset*/
			/**ha az elso sorbol valasztok*/
			if(getField(0, i)==color || getField(0, i)==empty){
				if(legal(0, i, color, 0, 4)){
					step=new Move(0, i, 0, 4);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(0, i, color, 0, 0)){
					step=new Move(0, i, 0, 0);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(0, i, color, 4, i)){
					step=new Move(0, i, 4, i);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
			}
			/**ha az utolso sorbol valasztok*/
			if(getField(4, i)==color || getField(4, i)==empty){
				if(legal(4, i, color, 4, 0)){
					step=new Move(4, i, 4, 0);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(4, i, color, 4, 4)){
					step=new Move(4, i, 4, 4);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(4, i, color, 0, i)){
					step=new Move(4, i, 0, i);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
			}
			
			/**ha az elso oszlopbol valasztok*/
			if(getField(i, 0)==color || getField(i, 0)==empty){
				if(legal(i, 0, color, 4, 0)){
					step=new Move(i, 0, 4, 0);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(i, 0, color, 0, 0)){
					step=new Move(i, 0, 0, 0);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(i, 0, color, i, 4)){
					step=new Move(i, 0, i, 4);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
			}
			/**ha az utolso oszlopbol valasztok*/
			if(getField(i, 4)==color || getField(i, 4)==empty){
				if(legal(i, 4, color, 0, 4)){
					step=new Move(i, 4, 0, 4);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(i, 4, color, 4, 4)){
					step=new Move(i, 4, 4, 4);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
				if(legal(i, 4, color, i, 0)){
					step=new Move(i, 4, i, 0);
					if(!exist(step, steps)){
						steps.add(step);
						j++;
					}
				}
			}
		}
		return steps;
	}
	
	/**Az adott lepes letezik-e mar az adott listaban?
	 * @param m a lepes, amit keresek
	 * @param steps a lista, amiben keresek*/
	public boolean exist(Move m, ArrayList<Move> steps){
		for(Move move: steps){
			if(steps.indexOf(m)!=steps.indexOf(move) && move.as(m)){
				return true;
			}
		}
		return false;
	}
	
	/**Ket tabla egyenlo-e
	 * @param t a tabla, amit osszehasonlitok az eredetivel*/
	public boolean as(QuixoBoard t){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(getField(i, j)!=t.getField(i, j)){
					return false;
				}
			}
		}
		return true;
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
	
	/**tesztelesre*/
	/*public static void main(String[] args) {
		QuixoBoard t=new QuixoBoard();
		/*t.setField(0, 0, 0);
		t.setField(0, 1, 0);
		t.setField(0, 2, 0);
		t.setField(0, 3, 0);
		t.setField(0, 4, 0);
		t.setField(1, 0, 0);
		t.setField(2, 0, 0);
		t.setField(3, 0, 0);
		t.setField(4, 0, 0);
		t.setField(1, 4, 0);
		t.setField(2, 4, 0);
		t.setField(3, 4, 0);
		t.setField(4, 4, 0);
		t.setField(4, 3, 0);
		t.setField(4, 2, 0);
		t.setField(4, 1, 0);
		System.out.println(t);
		t.nextSteps(0);
	}*/
}