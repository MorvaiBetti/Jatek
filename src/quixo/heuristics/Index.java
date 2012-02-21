package quixo.heuristics;

import quixo.engine.QuixoBoard;
import quixo.players.minimax.Node;
/**@Index A 4-as szamu heurisztika.
 * Ha nyert valamelyik jatekos, a tabla erteke maximalis/minimalis szam osztva a csomopont indexevel.*/
public class Index extends SimpleHeuristic{

	public void init(Node node, int me, int you, int nobody) {
		super.init( me, you, nobody);
	}
	
	public double calculation(Node node){
		this.node=node;
		table=(QuixoBoard) node.getTable().clone();
		color=(node.getModel()+1)%2;
		if(table.win(color)){
			if(node.getIndex()!=0){
				value=Integer.MAX_VALUE/node.getIndex();
			}else {value=Integer.MAX_VALUE;}
			return value;
		}
		if(table.win((color+1)%2)){
			if(node.getIndex()!=0){
				value=Integer.MIN_VALUE/node.getIndex();
			}else {value=Integer.MIN_VALUE;}
			return value;
		}
		return super.calculation(node);
	}

/*	public static void main(String[] args){
		QuixoBoard t=new QuixoBoard();
		t.setField(0, 0, 0);
		t.setField(0, 1, 0);
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
		Node rootx=new Node(t, null, null);
		rootx.setIndex(2);
		rootx.setModel(1);
		Index testx=new Index(rootx);
		testx.value=0;
		testx.calculation();
		System.out.println("x: "+testx.value);
		
		Node rooto=new Node(t, null, null);
		rooto.setIndex(2);
		rooto.setModel(0);
		Index testo=new Index(rooto);
		testo.value=0;
		testo.calculation();
		System.out.println(" o: "+testo.value);
	}*/
}
