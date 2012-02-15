package quixo.heuristics;

import quixo.engine.QuixoBoard;
import quixo.players.minimax.Node;
/**@PrevTable A 2-es szamu heuristika. 
 * Az aktualis csomopont tablajat az apa tablajaval hasonlitja ossze.
 * Ez alapjan szamolja ki a sorok, oszlopok es atlok erteket, majd ezeket osszeadja*/
public class PrevTable extends Calculater{
	/**@parentMine Szulo tablan a sajat babuim*/
	public int parentMine;
	/**@parentYours Szulo tablan az ellenfel babui*/
	public int parentYours;
	/**@parentFree Szulo tablan az ures babuk*/
	public int parentFree;
	/**@parentTable Szulo tablaja*/
	public QuixoBoard parentTable;
	
	/**Konstruktor*/
	public PrevTable(Node node, int me, int you, int nobody){
		super(node, me, you, nobody);		
		parentTable=(QuixoBoard) node.parent.getTable().clone();
		value=0;
		empty();
	}
	
	/**A tomboket es a szamlalokat kinullazza*/
	public void empty(){
		super.empty();
		parentMine=0;
		parentYours=0;
		parentFree=0;
	}
	
	/**Kiszamolja, hogy egy voanlban melyik babubol mennyi van es a koordinatajukat letarolja*/
	public int calculation(){
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
			compareField();
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
			compareField();
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
		compareField();
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
		compareField();
		empty();
		return value;
	}
	
	/**Az apa tablajahoz kepset milyen az aktualis tabla*/
	public void compareField(){
		System.out.println(mine+" "+yours+" "+free);
		if(parentYours<mine){
			value=(int) (value+Math.pow(me,mine));
		}
		if(parentMine<yours){
			value=(int) (value-Math.pow(you,yours));
		}
		/**Ha az ellenfel mintainak aranya romlott*/
		if(parentYours>yours){
			value=(int) (value+you*yours);
		}
		/**Ha az ellenfel mintainak aranya javult*/
		if(parentYours<yours){
			value=(int) (value-you*yours);
		}
		/**Ha a sajat mintaim aranya romlott*/
		if(parentMine>mine){
			value=(int) (value-me*mine);
		}
		/**Ha a sajat mintaim aranya javult*/
		if(parentMine<mine){
			value=(int) (value+me*mine);
		}
	}
	
/*	public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		parentTable=(QuixoBoard) t.clone();
		System.out.println(parentTable);
		t.setField(0, 0, 0);
	/*	t.setField(0, 1, 0);
		t.setField(0, 2, 0);
		t.setField(0, 3, 0);
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
		PrevTable testx=new PrevTable(rootx);
		testx.value=0;
		testx.calculation();
		System.out.println("x: "+testx.value);
		
		father.setModel(1);
		Node rooto=new Node(t, father, null);
		rooto.setModel(0);
		PrevTable testo=new PrevTable(rooto);
		testo.value=0;
		testo.calculation();
		System.out.println(" o: "+testo.value);
	}*/
}