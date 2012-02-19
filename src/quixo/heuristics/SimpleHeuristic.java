package quixo.heuristics;

import quixo.engine.QuixoBoard;
import quixo.players.minimax.Node;

public abstract class SimpleHeuristic implements Heuristics {
	/**@node adott csomopont, aminek a tablajat kiertekelem*/
	protected Node node;
	/**@table a node-hoz tartozo tabla, aminek az erteket kiszamolom*/
	protected QuixoBoard table;
	/**@color az utolso lepes mintaja, ebbol a szemszogbol szamolom a tabla erteket*/
	protected int color;
	/**@value az adott tabla erteke*/
	protected int value;
	/**@mine az aktualis minta darabszama*/
	protected int mine; 	
	/**@yours az ellenfel minta darabszama*/
	protected int yours; 
	/**@free az ures minta darabszama*/
	protected int free; 	
	/**@me sajat babu erteke*/
	protected int me; 	
	/**@you ellenfel babu erteke*/
	protected int you;
	/**@nobody ires babuk erteke*/
	protected int nobody;
	
	/**Inicializalas
	 * @param node amibol megkapom 
	 * @param h melyik heurisztika alapjan szamolja az erteket*/
	public void init(int me, int you, int nobody){
		this.me=me;
		this.you=you;
		this.nobody=nobody;
		value=0;
		empty();
	}
	
	/**A szamlalokat kinullazza*/
	public void empty(){
			mine=0;
			yours=0;
			free=0;
	}
	
	/**Az adott szin szempontjabol kiszamolja, melyik szinbol mennyi van a tablan.*/
	public int calculation(Node node){
		this.node=node;
		table=(QuixoBoard) node.getTable().clone();
		color=(node.getModel()+1)%2;
		if(table.win(color)){
			value=Integer.MAX_VALUE;
			return value;
		}
		if(table.win((color+1)%2)){
			value=Integer.MIN_VALUE;
			return value;
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
	public int sum(){
		int result;
		if(mine<yours){
			free=-free;
		}
		if(yours>0){
			if(mine>0){
				result=(int) (Math.pow(me, mine)-Math.pow(you, yours)+free*nobody);
			} else {	result=(int) (-Math.pow(you, yours)+free*nobody);}
		} else {if(mine>0){
					result=(int) (Math.pow(me, mine)+free*nobody);
				}else {result=free;}
		}
		return result;
	}
	
	public int getValue(){
		return value;
	}
}
