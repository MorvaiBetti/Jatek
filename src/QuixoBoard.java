public class QuixoBoard implements Cloneable {
	/*(sor; oszlop)*/

	public static final int empty =0;
	public static final int X=1;
	public static final int O=2;
	public int[][] tabla;
	
	//konstruktor
	public QuixoBoard(){
		tabla=new int[5][5];
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				tabla[i][j]=0;
			}
		}
	}
	
	//Tabla masolasa
	public Object clone(){
		QuixoBoard board=new QuixoBoard();
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				board.tabla[i][j]=this.tabla[i][j];
			}
		}
		return board;
	}
	
	//a tabla egy adott mezojenek erteket adja vissza
	public int getMezo(int x, int y){
		return tabla[x][y];
	}
	
	//Az adott lepes helyes-e?
	public boolean szabalyos(int x, int y, int minta, int xn, int yn){
	//	System.out.println("Szabalyos?? "+minta);
		//csak sajat szinut vagy semlegeset lehet 
		if(tabla[x][y]!=minta && tabla[x][y]!=0){
			return false;
		}
		
		//ahonnan elvettem, oda nem rakhatom vissza
		if(x==xn && y==yn){
			return false;
		}
	/*	//nem letezo minta
		if(minta!=1 && minta!=2){
			return false;
		}*/
		
		//ha nem letezo mezorol akarunk kivenni/visszatenni
		if(x<0 || y<0 || x>4 || y>4 || xn<0 || yn<0 || xn>4 || yn>4){
			return false;
		}
		
		//csak a palya szelerol lehet venni
		if(x!=0 && x!=4 && y!=0 && y!=4){
			return false;
		}
		
		//csak a palya szelere lehet visszatenni
		if(xn!=0 && xn!=4 && yn!=0 && yn!=4){
			return false;
		}

		//melyik kivetel eseten hova lehet tenni
		if(x==0 && ((xn==x && yn==0) ||(xn==x && yn==4) || (xn==4 && y==yn) )){
			return true;
		}else if(x==4 &&((x==xn && yn==0) || (xn==x && yn==4) || (xn==0 && yn==y))){
			return true;
		}else if(y==0 && ((yn==y && xn==0) ||(yn==y && xn==4) || (yn==4 && x==xn) )){
			return true;
		}else if(y==4 &&((y==yn && xn==0) || (yn==y && xn==4) || (yn==0 && xn==x))){
			return true;
		}
		return false;
	}
	
	//nyertes-e
	public boolean nyert(int minta){
		int db=0;
		//sorokon megy vegig
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(getMezo(i,j)==minta){
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
		//oszlopokon megy vegig
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(getMezo(j,i)==minta){
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
		//foatlo es mellekatlo
		int db2=0;
		for(int i=0; i<5; i++){
			if(getMezo(i,i)==minta){
				db++;
				if(db==5){
					return true;
				}
			}
			if(getMezo(i, 4-i)==minta){
				db2++;
				if(db2==5){
					return true;
				}
			}
		}
		return false;
	}
	
	public void lepestVegrehajt(int x, int y, int minta, int xn, int yn){
		/*if(!szabalyos(x,y,minta,xn,yn)){
			System.out.println("TÖKI "+minta);
			return;
		}*/
		//ha az eredeti sorba rakom vissza
		if(x==xn){
			//jobbrol tolok
			if(y<yn){
				for(int i=0; i+y<4; i++){
					tabla[x][y+i]=tabla[x][y+i+1];
				}
				tabla[xn][yn]=minta;
				return;
			}
			//balrol tolok
			if(y>yn){
				for(int i=0; y-i>0; i++){
					tabla[x][y-i]=tabla[x][y-i-1];
				}
				tabla[xn][yn]=minta;
				return;
			}
		}
		
		//ha az eredeti oszlopba rakom vissza
		if(y==yn){
			//alulrol tolok
			if(x<xn){
				for(int i=0; i+x<4; i++){
					tabla[x+i][y]=tabla[x+i+1][y];
				}
				tabla[xn][yn]=minta;
				return;
			}
			//felulrol tolok
			if(x>xn){
				for(int i=0; x-i>0; i++){
					tabla[x-i][y]=tabla[x-i-1][y];
				}
				tabla[xn][yn]=minta;
				return;
			}
		}
	}
	
	//adott jatekosnak van-e meg szabalyos lepese
	public boolean vanSzabalyosLepes(int minta){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(getMezo(i,j)==minta ||getMezo(i,j)==empty){
					return true;
				}
			}
		}
		return false;
	}
	
	//tabla kiiratasa
	public void kiir(){
		String s="     0  1  2  3  4\n\n 0  ";
		for(int i=0; i<5; i++){
			for(int k=0; k<5; k++){
				if(getMezo(i,k)==empty){
					s=s+" - ";
				}
				if(getMezo(i,k)==X){
					s=s+" X ";
				}
				if(getMezo(i,k)==O){
					s=s+" O ";
				}
			}
			if(i==4){
				break;
			}
			s=s+"\n " + (i+1)+"  ";
		}
		System.out.print(s+"\n ");
	}
	
	//Tesztelesre
	/*public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		t.print();
		System.out.println();
		t.makeMove(0,0,2,0,2);
		t.print();
		System.out.println();
		t.makeMove(0,0,1,0,4);
		t.print();
	}*/
	/*public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		t.print();
		BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		boolean megy=true;
		int[] m;
		m=new int[4];
		int i;
		String be;
		StringTokenizer st;
		while(megy){
			if(!t.legalMoveExist(1)){
				megy=false; 
				break;
			}
			i=0;
			System.out.println("X lép: ");
			try {
				be = reader.readLine();
				st = new StringTokenizer(be);
			    while (st.hasMoreTokens()) {
			         m[i]=Integer.parseInt(st.nextToken());
			         i++;
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    t.makeMove(m[0], m[1], 1, m[2], m[3]);
			    t.print();
			    
		    //masodik jatekos
			if(!legalMoveExist(2)){
				megy=false; 
				break;
			}
		    i=0;
			System.out.println("O lép: ");
			try {
				be = reader.readLine();
				st = new StringTokenizer(be);
			    while (st.hasMoreTokens()) {
			         m[i]=Integer.parseInt(st.nextToken());
			         i++;
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    t.makeMove(m[0], m[1], 2, m[2], m[3]);
		    t.print();
		}
	}*/
}
