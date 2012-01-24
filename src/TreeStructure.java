import java.util.ArrayList;

public class TreeStructure extends Node{
	public Node root;
	/**@newTable aktualis gyerek tablaja*/
	public QuixoBoard newTable=new QuixoBoard(); 
	/**@rootTable eredeti tablaja*/
	public QuixoBoard rootTable=new QuixoBoard(); 	
	/**@table aktualis tabla*/
	public QuixoBoard table=new QuixoBoard();
	public int depth;
 	/** @actualDepth a ciklus miatt kell a melyseg szamolasahoz*/
	public int actualDepth=0;
	/**@me az aktualis jatekos mintaja*/
	public int me; 		
	public Node child;
 	/**@roots az uj szuok*/
	public ArrayList<Node> roots=new ArrayList<Node>();
	/**@newRoots az uj gyerekek, amik az uj szulok lesznek*/
	public ArrayList<Node> newRoots=new ArrayList<Node>(); 	
	public Move[] steps;
	public Minimax minmaxTree;
	public Node maxNode;
	/**@mainNode eredeti kezdopont*/
	public Node mainNode; 	
	
	public TreeStructure(int color, QuixoBoard t, int d){
		super(color, null, 0, null, null);
		steps=new Move[d];
		rootTable=(QuixoBoard) t.clone();
		table=(QuixoBoard) rootTable.clone();
		roots.clear();
		newRoots.clear();
		this.depth=d;
		me=color;
		root=new Node(color, null, 0, null, null);
		setIndex(0);
		mainNode=root;
		makeTree(root, color);
		cyrcle(rootTable);
//		System.out.println("root "+root+" actualdepth "+actualDepth);
//		System.out.println("mainNode "+mainNode);		
//		minmaxTree=new Minmax(mainNode, mainNode.index);
//		maxNode=minmaxTree.start(mainNode, actualDepth);
		
		minmaxTree=new Minimax(mainNode);
		maxNode=minmaxTree.maxNode;
		
		
		System.out.println("maxNode: "+maxNode);
	}
	
	/**Vegigmegy minden mezon*/
	public void makeTree(Node node, int model){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				nextStep(i,j, model);
			}
		}
		return;
	}

	public void cyrcle(QuixoBoard t){
		rootTable=t;
		actualDepth=1;
		while(actualDepth<depth){
			roots.clear();
			for(Node n:newRoots){
				roots.add(n);
			}
			newRoots.clear();
			if(roots.isEmpty()){
				break;
			}
			for(Node n: roots){
				if(!root.isLeaf() && n!=null){
					int i=0;
					root=n;
					int m=0;
					/**adott csucshoz vezeto lepesek*/
					table=(QuixoBoard) rootTable.clone();
					while(n.index!=mainNode.index){
						steps[i]=n.step;
						i++;
						n=n.parent;
					}
					/**az aktualis tablan eljutok az aktualis csucshoz*/
					for(int j=i-1; j>=0; j--){
						Move stp=steps[j];
						int mdl;
						if(m%2==0){
							mdl=me;
						}else {mdl=(me+1)%2;}
						table.makeStep(stp.x, stp.y, mdl, stp.nx, stp.ny);	
						m++;
					}
					makeTree(root, root.getModel());
				}
			}
			actualDepth++;
		}
		/**visszalepegetek a gyokerhez*/
		while(root.getIndex()!=mainNode.index){
			root=root.parent;
		}
		roots.clear();
		return;
	}
	
		
	/**szummazza a tabla erteketaz utoljara lepett jatekos szemszogebol nezve. ha az ellenfelem lepett, akkor az ertek negativ lesz*/
	public int heuristic(int m, QuixoBoard t){
		Calculater c=new Calculater(m, t);
		value=c.calculation();
		if(model!=me){
		//	value=-value;
			value=(-2)*value;
		}
	//	System.out.println("value "+value);
		return value;
	}
	
	/**minden lehetseges lepest megvizsga, hogy szabalyos-e. Ha igen, akkor az egy uj csomopont.
	@param a =sor 
	@param b =oszlop */
	public void nextStep(int a, int b, int model){
		newTable=(QuixoBoard) table.clone();
		/**ha vmelyik csucsra akarok tenni*/
		if((b==0 || a==4) && (a==0 || b==4)){
			for(int i=0; i<5; i++){
				/**elobb az ureseket*/
				if(table.getField(Math.abs(i-a), b)==QuixoBoard.empty && table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					step=new Move(Math.abs(i-a), b, a, b);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
				if(table.getField(a, Math.abs(i-b))==QuixoBoard.empty && table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					step=new Move(a, Math.abs(i-b), a, b);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			/**majd a sajatokat*/
			for(int i=0; i<5; i++){
				if(table.getField(Math.abs(i-a), b)==model && table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					step=new Move(Math.abs(i-a), b, a, b);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
				if(table.getField(a, Math.abs(i-b))==model && table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					step=new Move(a, Math.abs(i-b), a, b);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
		}
		
		/**fuggolegesen van szelen*/
		if((b==0 || b==4) && a!=0 && a!=4){
			/**elobb forditani probalok*/
			if(a+1<5 && table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==QuixoBoard.empty && table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						step=new Move(i, b, 4, b);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(a-1 >-1 && table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==QuixoBoard.empty && table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						step=new Move(i, b, 0, b);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			/**ha nem tudok forditani*/
			if(a+1<5 && table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==model && table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						step=new Move(i, b, 4, b);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(a-1 >-1 && table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==model && table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						step=new Move(i, b, 0, b);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(table.getField(a, 4-b)!=(model+1)%2 && table.legal(a, 4-b, model, a, b)){
				newTable.makeStep(a, 4-b, model, a, b);
				step=new Move(a, 4-b, a, b);
				child=new Node((model+1)%2, root, value, step, root.parent);
				if((root.index+1)==depth){
					child.end=true;
					value=heuristic(model, newTable);
				}else {value=0;}
				if(newTable.win(model) || newTable.win((model+1)%2)){
					child.leaf=true;
					child.value=heuristic(model, newTable);
				}
				addChild(child, root, value, step);
				newRoots.add(child);
				newTable=(QuixoBoard) table.clone();
			}
		}
			
		/**vizszintesen van szelen*/
		if((a==0 || a==4) && b!=0 && b!=4){
			/**forditani probalok*/
			if(b+1<5 && table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(table.getField(a, i)==QuixoBoard.empty && table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						step=new Move(a, i, a, 4);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(b-1>-1 && table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(table.getField(a, i)==QuixoBoard.empty && table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						step=new Move(a, i, a, 0);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			/**ha nem tudok forditani*/
			if(b+1<5 && table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(table.getField(a, i)==model && table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						step=new Move(a, i, a, 4);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(b-1>-1 && table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(table.getField(a, i)==model && table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						step=new Move(a, i, a, 0);
						child=new Node((model+1)%2, root, value, step, root.parent);
						if((root.index+1)==depth){
							child.end=true;
							value=heuristic(model, newTable);
						}else {value=0;}
						if(newTable.win(model) || newTable.win((model+1)%2)){
							child.leaf=true;
							child.value=heuristic(model, newTable);
						}
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(table.getField(4-a, b)!=(model+1)%2 && table.legal(4-a, b, model, a, b)){
				newTable.makeStep(4-a, b, model, a, b);
				step=new Move(4-a, b, a, b);
				child=new Node((model+1)%2, root, value, step, root.parent);
				if((root.index+1)==depth){
					child.end=true;
					value=heuristic(model, newTable);
				}else {value=0;}
				if(newTable.win(model) || newTable.win((model+1)%2)){
					child.leaf=true;
					child.value=heuristic(model, newTable);
				}
				addChild(child, root, value, step);
				newRoots.add(child);
				newTable=(QuixoBoard) table.clone();	
			}
		}
			
		/**ha nincs szelen*/
		if(a!=0 && a!=4 && b!=0 && b!=4){
			/**oszlopban kovetkezot tolom a jo helyre*/
			if(a+1<5 && table.getField(a+1, b)==model){
				if((table.getField(0, b)==model || table.getField(0, b)==QuixoBoard.empty) && table.legal(0, b, model, 4, b)){
					newTable.makeStep(0, b, model, 4, b);
					step=new Move(0, b, 4, b);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			/**oszlopban elozot tolom a jo helyre*/
			if(a-1>-1 && table.getField(a-1, b)==model){
				if((table.getField(4, b)==model || table.getField(4, b)==QuixoBoard.empty) && table.legal(4, b, model, 0, b)){
					newTable.makeStep(4, b, model, 0, b);
					step=new Move(4, b, 0, b);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			/**sorban kovetkezot tolom a jo helyre*/
			if(b+1<5 && table.getField(a, b+1)==model){
				if((table.getField(a, 0)==model || table.getField(a, 0)==QuixoBoard.empty) && table.legal(a, 0, model, a, 4)){
					newTable.makeStep(a, 0, model, a, 4);
					step=new Move(a, 0, a, 4);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			/**sorban elozot tolom a jo helyre*/
			if(b-1>-1 && table.getField(a, b-1)==model){
				if((table.getField(a, 4)==model || table.getField(a, 4)==QuixoBoard.empty) && table.legal(a, 4, model, a, 0)){
					newTable.makeStep(a, 4, model, a, 0);
					step=new Move(a, 4, a, 0);
					child=new Node((model+1)%2, root, value, step, root.parent);
					if((root.index+1)==depth){
						child.end=true;
						value=heuristic(model, newTable);
					}else {value=0;}
					if(newTable.win(model) || newTable.win((model+1)%2)){
						child.leaf=true;
						child.value=heuristic(model, newTable);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
		}
		return;
	}

}