package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.engine.QuixoPlayer;

public class Minimax extends QuixoPlayer{
	/**@nextMoves adott tabla es minta eseten a szabalyos lepesek listaja*/
	protected ArrayList<Move> nextMoves=new ArrayList<Move>();
	/**@alfa hasznossag legalabb alfa*/
	protected static double alfa;
	/**@beta hasznossag legfeljebb beta*/
	protected static double beta;
	/**@child aktualis csomopont uj fia*/
	protected Node child;
	/**@opponent az ellenfel lepeset keresem-e*/
	protected boolean opponent;
	/**@prevTable elozo lepesem utani tabla*/
	protected QuixoBoard prevTable=new QuixoBoard();
	
	public Minimax(){}
	
	/**Kovetkezo lepest szamitja ki
	 * @param prevStep elozo lepes*/
	protected Move nextMove(Move prevStep){
		if(depth==1){
			if(prevStep!=null){
				table.makeStep(prevStep, getOpponentColor());
			}
			root=new Node(table, null, null);
			root.setModel(getColor());
			maxValue(root, Double.MIN_VALUE, Double.MAX_VALUE);
			root=find(root);
			table.makeStep(root.getStep(), getColor());
			prevTable=(QuixoBoard) table.clone();
			return root.getStep();
		}
		if(prevStep==null){
			root=new Node(table, null, null);
			root.setModel(getColor());
			maxValue(root, Double.MIN_VALUE, Double.MAX_VALUE);
			root=find(root);
			table.makeStep(root.getStep(), getColor());
			prevTable=(QuixoBoard) table.clone();
			return root.getStep();
		}
		table.makeStep(prevStep, getOpponentColor());
		if(root==null){
			root=new Node(table, null, null);
			root.setModel(getColor());
			maxValue(root, Double.MIN_VALUE, Double.MAX_VALUE);
			root=find(root);
			table.makeStep(root.getStep(), getColor());
			prevTable=(QuixoBoard) table.clone();
			return root.getStep();
		}
		/**Azert kell, mert az ellenfel valaszthatott olyan lepest, amit en korabban az alfa-beta miatt levagtam.*/
		Node possible=null;
		for(Node child:root.children){	
			if(child.getTable().equals(table)){
				possible=child;
				break;
			}
		}
		if(possible==null){
			QuixoBoard t=(QuixoBoard) root.getTable().clone();
			t.makeStep(prevStep, opponentColor);
			root=new Node(t, null, null);
			root.setModel(getColor());
		}else {
			root=possible;
			root.setLeaf(false);
			root.setParent(null);
			root.setStep(null);
			root.setModel(getColor());
		}
		maxValue(root, Double.MIN_VALUE, Double.MAX_VALUE);
		root=find(root);
		table.makeStep(root.getStep(), getColor());
		prevTable=(QuixoBoard) table.clone();
		return root.getStep();
	}
	
	/**Megkeresi, hogy a gyoker erteke melyik fianak ertekevel egyezik meg, az a fiu tartalmazza a kovetkezo lepest
	 * @param node a gyoker, akinek a fiai kozott keresek*/
	private Node find(Node node){
		Node found=null;
		for(Node child: node.children){
			if(node.getValue()==child.getValue()){
				if(child.table.equals(prevTable)){
					continue;
				}
				if(found==null){
					found=child;
				}else if(rand.nextBoolean()){
					found=child;
					}
			}
		}
		return found;
	}
	
	/**Az aktualis csomopont uj fiat letrehozza es hozzaadja az apa gyerekeinek listajahoz
	 * @param step a lepes, ami az uj csomoponthoz tartozik
	 * @param parent az uj csomopont apja*/
	private Node newChild(Move step, Node parent){
		QuixoBoard newTable=(QuixoBoard) parent.table.clone();
		newTable.makeStep(step, parent.getModel());
		if(newTable.equals(prevTable)){
			return null;
		}
		Node child=new Node(newTable, parent, step);
		/**Ha az adott tablaallassal mar letezik node*/
		for(Node node: parent.children){
			if(node.getTable().equals(child.getTable())){
				if(child.getIndex()-root.getIndex()!=depth && !node.getTable().win(QuixoBoard.O) && !node.getTable().win(QuixoBoard.X)){
					node.setLeaf(false);
				}
				return node;
			}
		}

		parent.children.add(child);
		if(child.getIndex()-root.getIndex()==depth){
			child.setLeaf(true);
		}else {if(!child.getTable().win(QuixoBoard.O) && !child.getTable().win(QuixoBoard.X)){
				child.setLeaf(false);
			}
		}
		if(child.isLeaf()){
			child.setValue(sum(child));
		}
		return child;
	}
	
	/**Kiszamolja adott node erteket az adott heurisztika alapjan
	 * @param node ennek a csomopontnak az erteket szamolom ki*/
	private int sum(Node node){
		double result = 0;
		heuristic.calculation(node); 	/**Az utoljara lepett jatekos szemszogebol mennyi a tabla erteke*/
		if((node.getModel()+1)%2!=getColor()){
			result=-heuristic.getValue();
		}else {result=heuristic.getValue();}
		return (int) result;
	}
	
	/**Maximalis erteket szamolja ki az adott csucspont gyerekeinek, es azt adja at a csucspontnak
	 * @param node az a csomopont, aminek a fiainak a minimumat kell venni
	 * @param alfa hasznossag legalabb alfa
	 * @param beta hasznossag legfeljebb beta*/
	private double maxValue(Node node, double alfa, double beta){
		if(node.isLeaf()){
			return node.value;
		}
		double max=-Double.MAX_VALUE;
		nextMoves=node.table.nextSteps(node.getModel());
		for(Move move: nextMoves){
			child=newChild(move, node);
			if(child==null){
				continue;
			}
			max=Math.max(max, minValue(child, alfa, beta));
			if(!opponent){
				if(max>=beta){
					node.value=max;	
					return max;
				}
				alfa=Math.max(max, alfa);
			}
		}
		node.value=max;
		return max;
	}

	/**Minimalis erteket szamolja ki az adott csucspont gyerekeinek, es azt adja at a csucspontnak
	 * @param node az a csomopont, aminek a fiainak a minimumat kell venni
	 * @param alfa hasznossag legalabb alfa
	 * @param beta hasznossag legfeljebb beta*/
	private double minValue(Node node, double alfa, double beta){
		if(node.isLeaf()){
			return node.value;
		}
		double min=Double.MAX_VALUE;
		nextMoves=node.table.nextSteps(node.getModel());
		for(Move move: nextMoves){
			child=newChild(move, node);
			if(child==null){
				continue;
			}
			min=Math.min(min, maxValue(child, alfa, beta));
			if(alfa>=min){
				node.value=min;
				return min;
			}
			beta=Math.min(min, beta);
		}
		node.value=min;
		return min;
	}
	
	/**tesztelesre*/
/*	public static void main(String[] args){
		Minimax mini=new Minimax();
		QuixoBoard table=new QuixoBoard();
		table.setField(0, 0, 0);
		/*t.setField(0, 1, 1);
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
		System.out.println(table);
		mini.root=new Node(table, null, null);
		mini.root.setIndex(0);
		mini.root.setModel(0);
		mini.setColor(0);
		mini.setDepth(2);
		mini.maxValue(mini.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		for(Node n:mini.root.children){
			System.out.println(n.getTable()+"\t szin "+n.getModel()+" "+n.getValue());
			for(Node c: n.children){
				System.out.println(c.getTable()+"\t szin "+c.getModel()+" ertek "+c.getValue());
			}
		}
		System.out.println("root val "+mini.root);
	//	System.out.println("max "+maxNode.value);
		//System.out.println(t.nextSteps(0));
	}*/
}