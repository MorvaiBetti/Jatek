package quixo.heuristics;

import quixo.engine.QuixoBoard;
import quixo.players.minimax.Node;

public class Calculater{
	/**@node adott csomopont, aminek a tablajat kiertekelem*/
	public Node node;
	/**@table a node-hoz tartozo tabla, aminek az erteket kiszamolom*/
	public QuixoBoard table;
	/**@color az utolso lepes mintaja, ebbol a szemszogbol szamolom a tabla erteket*/
	public int color;
	/**@value az adott tabla erteke*/
	public int value;
	/**@mine az aktualis minta darabszama*/
	public static int mine; 	
	/**@yours az ellenfel minta darabszama*/
	public static int yours; 
	/**@free az ures minta darabszama*/
	public static int free; 	
	/**@me sajat babu erteke*/
	public static int me=2; 	
	/**@you ellenfel babu erteke*/
	public static int you=6; 	
	/**@nobody ures mezo erteke*/
	public int nobody=0;
	public int win=7;
	
	/**Konstruktor
	 * @param node amibol megkapom 
	 * @param h melyik heurisztika alapjan szamolja az erteket*/
	public Calculater(Node node){
		this.node=node;
		table=(QuixoBoard) node.getTable().clone();
		color=(node.getModel()+1)%2;
		value=0;
	}
	
	/**A szamlalokat kinullazza*/
	public static void empty(){
			mine=0;
			yours=0;
			free=0;
	}
	
	/**Az adott szin szempontjabol kiszamolja, melyik szinbol mennyi van a tablan.*/
	public double calculation(){
		if(table.win(color)){
			return Integer.MAX_VALUE;
		}
		if(table.win((color+1)%2)){
			return Integer.MIN_VALUE;
		}
		/**sorokon megy vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(i, j)==color){
					mine++;
				}
				if(table.getField(i, j)==(color+1)%2){
					yours++;
				}
				if(table.getField(i, j)==QuixoBoard.empty){
					free++;
				}
			}
			value=value+sum();
			empty();
		}
		/**oszlopokon megy vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(j, i)==color){
					mine++;
				}
				if(table.getField(j, i)==(color+1)%2){
					yours++;
				}
				if(table.getField(j, i)==QuixoBoard.empty){
					free++;
				}
			}
			value=value+sum();
			empty();
		}
		
		/**foatlon megy vegig*/
		for(int i=0; i<5; i++){
			if(table.getField(i, i)==color){
				mine++;
			}
			if(table.getField(i, i)==(color+1)%2){
				yours++;
			}
			if(table.getField(i, i)==QuixoBoard.empty){
				free++;
			}
		}
		value=value+sum();
		empty();
		
		/**mellekatlon megy vegig*/
		for(int i=0; i<5; i++){
			if(table.getField(i, 4-i)==color){
				mine++;
			}
			if(table.getField(i, 4-i)==(color+1)%2){
				yours++;
			}
			if(table.getField(i, 4-i)==QuixoBoard.empty){
				free++;
			}
		}
		value=value+sum();
		empty();
		return value;
	}

	/**osszegzi a tabla tartalmat*/
	public static int sum(){
		int result;
		if(mine<yours){
			free=-free;
		}
		if(yours>0){
			if(mine>0){
				result=(int) (Math.pow(me, mine)-Math.pow(you, yours)+free);
			} else {	result=(int) (-Math.pow(you, yours)+free);}
		} else {if(mine>0){
					result=(int) (Math.pow(me, mine)+free);
				}else {result=free;}
		}
		return result;
	}
	
		/**Tesztelesre*/
/*		public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		t.setField(0, 0, 0);
	/*	t.setField(0, 1, 1);
		t.setField(0, 2, 0);
		t.setField(0, 3, 1);
		t.setField(0, 4, 0);
		t.setField(1, 0, 1);
		t.setField(2, 0, 0);
		t.setField(3, 0, 1);
		t.setField(4, 0, 0);
		t.setField(1, 4, 1);
		t.setField(2, 4, 0);
		t.setField(3, 4, 1);
		t.setField(4, 4, 0);
		t.setField(4, 3, 1);
		t.setField(4, 2, 0);
		t.setField(4, 1, 1);
		System.out.println(t);
		
		Node rootx=new Node(t, null, null);
		rootx.setModel(0);
		Calculater testx=new Calculater(rootx);
		testx.calculation();
		System.out.println("x: "+testx.getValue());
		
		Node rooto=new Node(t, null, null);
		rooto.setModel(1);
		Calculater testo=new Calculater(rooto);
		testo.calculation();
		System.out.println(" o: "+testo.getValue());
	}*/
}
