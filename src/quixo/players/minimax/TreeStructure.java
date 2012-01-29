package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.heuristics.Calculater;
import quixo.heuristics.Winner;

public class TreeStructure extends Node{
	public Node root;
	public Node mainRoot;
	public Node maxNode;
	public int[][] fields=new int[25][7];
	public QuixoBoard newTable;
	public int depth;
	public int actualDepth;
	public int myColor;
	public Move step;
	public ArrayList<Node> roots=new ArrayList<Node>(80);
	public ArrayList<Node> newRoots=new ArrayList<Node>(80);

	public TreeStructure(Pair rootData, int d, Move s, int model){
		super(rootData, null, s);
		depth=d;
		root=new Node(rootData, null, s);
		mainRoot=root;
		newRoots.clear();
		newRoots.add(root);
		setIndex(0);
		cyrcle();
		Minimax minmaxTree=new Minimax(mainRoot);
		maxNode=minmaxTree.maxNode;
	/*	System.out.println("gyoker ertek: "+mainRoot.getValue());
		System.out.println("max erteke "+maxNode.getValue());
	*/	
	}
	
	public void cyrcle(){
		while(actualDepth<depth){
			roots.clear();
			for(Node node: newRoots){
				roots.add(node);
			}
			newRoots.clear();
			for(Node node: roots){
				nextStep(node, node.data.getModel());
			}
			actualDepth++;
		}
	}
	
	/**Kiszamolja az aktualis csomopont erteket a csomoponthoz tartozo tabla alapjan*/
	public int sum(int model, QuixoBoard table){
		//Calculater result=new Calculater(model, table);
		Winner result=new Winner(model, table);
		return result.value;
	}


	public void nextStep(Node root, int model){
		newTable=(QuixoBoard) data.getTable().clone();
		
		for(int i=0; i<5; i++){ 					/**A lehetseges stepsen vegigmegy, es steps-hez hozzaadja a legal stepset*/
			/**ha az elso sorbol valasztok*/
			if(newTable.getField(0, i)==model || newTable.getField(0, i)==QuixoBoard.empty){
				if(newTable.legal(0, i, model, 0, 4)){
					step=new Move(0, i, 0, 4);
					newChild(model, step, root);
				}
				if(newTable.legal(0, i, model, 0, 0)){
					step=new Move(0, i, 0, 0);
					newChild(model, step, root);
					step=null;
				}
				if(newTable.legal(0, i, model, 4, i)){
					step=new Move(0, i, 4, i);
					newChild(model, step, root);
				}
			}
			/**ha az utolso sorbol valasztok*/
			if(newTable.getField(4, i)==model || newTable.getField(4, i)==QuixoBoard.empty){
				if( newTable.legal(4, i, model, 4, 0)){
					step=new Move(4, i, 4, 0);
					newChild(model, step, root);
				}
				if(newTable.legal(4, i, model, 4, 4)){
					step=new Move(4, i, 4, 4);
					newChild(model, step, root);
				}
				if(newTable.legal(4, i, model, 0, i)){
					step=new Move(4, i, 0, i);
					newChild(model, step, root);
				}
			}
			
			/**ha az elso oszlopbol valasztok*/
			if(newTable.getField(i, 0)==model || newTable.getField(i, 0)==QuixoBoard.empty){
				if(newTable.legal(i, 0, model, 4, 0)){
					step=new Move(i, 0, 4, 0);
					newChild(model, step, root);
				}
				if(newTable.legal(i, 0, model, 0, 0)){
					step=new Move(i, 0, 0, 0);
					newChild(model, step, root);
				}
				if(newTable.legal(i, 0, model, i, 4)){
					step=new Move(i, 0, i, 4);
					newChild(model, step, root);
				}
			}
			/**ha az utolso oszlopbol valasztok*/
			if(newTable.getField(i, 4)==model || newTable.getField(i, 4)==QuixoBoard.empty){
				if(newTable.legal(i, 4, model, 0, 4)){
					step=new Move(i, 4, 0, 4);
					newChild(model, step, root);
				}
				if(newTable.legal(i, 4, model, 4, 4)){
					step=new Move(i, 4, 4, 4);
					newChild(model, step, root);
				}
				if(newTable.legal(i, 4, model, i, 0)){
					step=new Move(i, 4, i, 0);
					newChild(model, step, root);
				}
			}
		}
		return;
	}
	
	/**Az aktualis csomopont uj fiat letrehozza es hozzaadja az apa gyerekeinek listajahoz*/
	public void newChild(int model, Move step, Node parent){
		newTable.makeStep(step, model);
		Pair newData=new Pair((model+1)%2, newTable);
		Node child=new Node(newData, parent, step);
		for(Node node: parent.children){
			if(node==child){
				System.out.println("if kilepett");
				return;
			}
		}
		parent.children.add(child);
		newRoots.add(child);
		if(child.index==depth){
			child.setLeaf(true);
		}
		if(child.isLeaf()){
			child.setValue(sum(model, child.data.getTable()));
		}
	//	System.out.println("lepes "+step+" level-e: "+child.isLeaf());
		newTable=(QuixoBoard) parent.data.table.clone();
		return;
	}
}