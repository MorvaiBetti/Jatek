package quixo.heuristics;

import quixo.engine.QuixoBoard;
import quixo.players.minimax.Node;
/**@PrevTable A 2-es szamu heuristika. 
 * Az aktualis csomopont tablajat az apa tablajaval hasonlitja ossze.
 * Ez alapjan szamolja ki a sorok, oszlopok es atlok erteket, majd ezeket osszeadja*/
public class PrevTable extends SimpleHeuristic implements Heuristics{
	/**@parentMine Szulo tablan a sajat babuim*/
	private int parentMine;
	/**@parentYours Szulo tablan az ellenfel babui*/
	private int parentYours;
	/**@parentFree Szulo tablan az ures babuk*/
	private int parentFree;
	/**@parentTable Szulo tablaja*/
	private QuixoBoard parentTable;
	
	public void init(int me, int you, int nobody){		
		super.init(me, you, nobody);
	}
	
	/**A tomboket es a szamlalokat kinullazza*/
	public void empty(){
		super.empty();
		parentMine=0;
		parentYours=0;
		parentFree=0;
	}
	
	/**Kiszamolja, hogy egy voanlban melyik babubol mennyi van es a koordinatajukat letarolja*/
	public double calculation(Node node){
		this.node=node;
		table=(QuixoBoard) node.getTable().clone();
		color=(node.getModel()+1)%2;
		parentTable=(QuixoBoard) node.parent.getTable().clone();
		empty();
		/**Soronkent megy vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(i, j)==color){
					mine++;
				}else {if(table.getField(i, j)==(color+1)%2){
						yours++;
					}else {free++;}
				}
				if(parentTable.getField(i, j)==color){
					parentMine++;
				}else {if(parentTable.getField(i, j)==(color+1)%2){
						parentYours++;
					}else {parentFree++;}
				}
			}
			if(yours==5){
				value=Integer.MIN_VALUE;
				return value;
			}
			if(mine==5){
				value=Integer.MAX_VALUE;
				return value;
			}
			value=value+sum();
			empty();
		}
		
		/**oszlopononkent megyek vegig*/
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table.getField(j, i)==color){
					mine++;
				}else {if(table.getField(j, i)==(color+1)%2){
						yours++;
					}else {free++;}
				}
				if(parentTable.getField(i, j)==color){
					parentMine++;
				}else 	{if(parentTable.getField(i, j)==(color+1)%2){
							parentYours++;
						}else {parentFree++;}
				}
			}
			if(yours==5){
				value=Integer.MIN_VALUE;
				return value;
			}
			if(mine==5){
				value=Integer.MAX_VALUE;
				return value;
			}
			value=value+sum();
			empty();
		}
		
		/**Foatlon megyek vegig*/
		for(int i=0; i<5; i++){
			if(table.getField(i,i)==color){
				mine++;
			}else {if(table.getField(i, i)==(color+1)%2){
						yours++;
				}else {free++;}
			}
			if(parentTable.getField(i, i)==color){
					parentMine++;
			}else {if(parentTable.getField(i, i)==(color+1)%2){
						parentYours++;
				}else {parentFree++;}
			}
		}
		value=value+sum();
		empty();
		
		/**Mellekatlo atvizsgalasa*/
		for(int i=0; i<5; i++){
			if(table.getField(i,4-i)==color){
				mine++;
			}else {if(table.getField(i, 4-i)==(color+1)%2){
						yours++;
				}else {free++;}
			}
			if(parentTable.getField(i, 4-i)==color){
					parentMine++;
			}else {if(parentTable.getField(i, 4-i)==(color+1)%2){
						parentYours++;
				}else {parentFree++;}
			}
		}
		value=value+sum();
		empty();
		return value;
	}
	
	/**Az apa tablajahoz kepset milyen az aktualis tabla*/
	public double sum(){
		double result=0;
		mine=mine-parentMine;
		yours=yours-parentYours;
		free=free-parentFree;
		
		result=mine*me-yours*you+free*nobody;		
		return result;
	}
	
	public double getValue(){
		return value;
	}
	
/*	public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		parentTable=(QuixoBoard) t.clone();
		System.out.println(parentTable);
		t.setField(0, 0, 0);
		t.setField(0, 1, 0);
		t.setField(0, 2, 0);
	/*	t.setField(0, 3, 0);
		t.setField(0, 4, 0);
	/*	t.setField(1, 0, 1);
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
		Node father=new Node(parentTable, null, null);
		father.setModel(0);
		Node rootx=new Node(t, father, null);
		rootx.setModel(1);
		PrevTable testx=new PrevTable();
		testx.init(1, 2, 0);
		testx.value=0;
		testx.calculation(rootx);
		System.out.println("x: "+testx.value);
		
		father.setModel(1);
		Node rooto=new Node(t, father, null);
		rooto.setModel(0);
		PrevTable testo=new PrevTable();
		testo.init(1, 2, 0);
		testo.value=0;
		testo.calculation(rooto);
		System.out.println(" o: "+testo.value);
	}*/
}