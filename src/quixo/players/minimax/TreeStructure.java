package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;

public class TreeStructure extends Node{
	public Node root;
	public Node mainRoot;
	public Node maxNode;
	public int[][] fields=new int[25][7];
	public QuixoBoard newTable;
	public int my=2;
	public int your=3;
	public int nobody=1;
	public int value;
	public int depth;
	public int d=0;
	public int me;
	public int kutya=0;
	public Node now;
	public Move step;
	public ArrayList<Node> roots=new ArrayList<Node>(80);
	public ArrayList<Node> newRoots=new ArrayList<Node>(80);

	public TreeStructure(Pair rootData, int d, Move s, int model){
		super(rootData, null, s);
		depth=d;
		me=model;
		System.out.println("melyseg= "+depth);
		root=new Node(rootData, null, s);
		mainRoot=root;
		setIndex(0);
		nextStep(root, root.data.getModel());
	//	System.out.println(root.children.size()+" fia van");
		//makeTree(root, rootData.getModel());
		cyrcle();
	//	System.out.println(mainRoot);
		Minimax minmaxTree=new Minimax(mainRoot);
		maxNode=minmaxTree.maxNode;
		System.out.println("gyoker ertek: "+mainRoot.getValue());
		System.out.println("max erteke "+maxNode.getValue());
		
	}

	/*public void makeTree(Node node, int model){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				nextStep(i,j, model);
			}
		}
	}*/

	public void step(ArrayList<Node> nodes){
		for(int i=0; i<nodes.size(); i++){
			root=nodes.remove(i);
		//	makeTree(root, root.data.getModel());
		}
	}
	public void cyrcle(){
		d=1;
		while(d<depth){
			roots.clear();
			for(Node node: newRoots){
				roots.add(node);
			}
			newRoots.clear();
			for(Node node: roots){
				nextStep(node, node.data.getModel());
			//	System.out.println("fiuk szama "+node.children.size());
			}
			d++;
		}
	}

	public int sum(int model){/*
		/**oszlopokon megy vegig*/
		int mine = 0;
		int yours = 0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(newTable.getField(i,j)==model){
					mine++;
				}
				if(newTable.getField(i,j)==(model+1)%2){
					yours++;
				}
			/*	if(newTable.getField(i, j)==QuixoBoard.empty){
					free++;
				}*/
			}	
		}
		value=(mine*my)-(yours*your);
		return value;
	}

	//a=sor b=oszlop
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
		kutya++;
		parent.children.add(child);
		newRoots.add(child);
		if(child.index==depth){
			child.setLeaf(true);
		}
		if(child.isLeaf()){
			child.setValue(sum(model));
		}
		newTable=(QuixoBoard) parent.data.table.clone();
	//	System.out.println(kutya+". "+step+" level-e "+child.isLeaf()+" index "+child.getIndex()+" ertek "+child.getValue());
		return;
	}
}