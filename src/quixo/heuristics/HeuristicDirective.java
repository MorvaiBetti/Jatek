package quixo.heuristics;

import quixo.players.minimax.Node;

public class HeuristicDirective {
	/**@node adott csomopont, aminek a tablajat kiertekelem*/
	public Node node;
	/**@me sajat babu erteke*/
	public int me; 	
	/**@you ellenfel babu erteke*/
	public int you;
	public int nobody;
	/**@heuristic melyik heurisztikat akarom hasznalni*/
	public int heuristic;
	
	/**@HeuristicDirective A megadott heurisztikat hozza letre es a konstruktoranak atadja az adott csomopontot.
	 * @param node amibol megkapom 
	 * @param h melyik heurisztika alapjan szamolja az erteket*/
	public HeuristicDirective(Node n, int h, int me, int you, int nobody){
		node=n;
		heuristic=h;
		this.me=me;
		this.you=you;
		this.nobody=nobody;
	}
	/**Kiszamolja az adott tabla erteket*/
	public double calculation(){
		switch (heuristic){
			case 1:	Calculater calculater=new Calculater(node, me, you, nobody);
				//	System.out.println("calculater");
					return calculater.calculation();
			case 2: PrevTable prevTable=new PrevTable(node, me, you, nobody);
				//	System.out.println("prevStep");
					return prevTable.calculation();
			case 3: Winner winner=new Winner(node, me, you, nobody);
				//	System.out.println("winner");
					return winner.calculation();
			case 4: Index index=new Index(node, me, you, nobody);
				//	System.out.println("index");
					return index.calculation();
		}
		return 0;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
/*	public static void main(String[] args){
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
		
		/*Node rootx=new Node(t, null, null);
		rootx.setModel(0);
		Heuristic testx=new Heuristic(rootx, 1);
		testx.calculation();
		System.out.println("x: "+testx.getValue());
		
		Node rooto=new Node(t, null, null);
		rooto.setModel(1);
		Calculater testo=new Calculater(rooto);
		testo.calculation();
		System.out.println(" o: "+testo.getValue());
	}*/
}
