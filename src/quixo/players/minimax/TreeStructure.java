package quixo.players.minimax;

import java.util.ArrayList;

import quixo.engine.Move;
import quixo.engine.QuixoBoard;
import quixo.heuristics.Calculater;
import quixo.heuristics.Heuristic;
import quixo.heuristics.Winner;

public class TreeStructure extends Node{
	/**@parentsOfNumber az aktualis faban hany apa van osszesen*/
	public int parentsOfNumber;
	/**@childrenOfNumber az aktualis faban hany fiu van osszesen*/
	public int childrenOfNumber;
	/**@root az aktualis gyoker, mindig ennek a fiait keresem*/
	public Node root;
	/**@mainRoot az aktualis fa gyokere*/
	public Node mainRoot;
	/**@maxNode maximalis lepest tartalmazo csomopont*/
	public Node maxNode;
	/**@newTable a root tablaja*/
	public QuixoBoard newTable;
	/**@depth a fa melysege*/
	public int depth;
	/**@heuristic melyik heurisztikat hasznalja*/
	public int heuristic;
	/**@actualDepth aktualis melyseg*/
	public int actualDepth;
	/**@myColor a jatekos szine*/
	public int myColor;
	/**@step legalis lepes az aktualis tablan*/
	public Move step;
	/**@roots aktualis apak*/
	public ArrayList<Node> roots=new ArrayList<Node>(80);
	/**@newRoots uj, lehetseges apak*/
	public ArrayList<Node> newRoots=new ArrayList<Node>(80);

	public TreeStructure(Pair rootData, int d, int h){
		super(rootData, null, null);
		childrenOfNumber=0;
		parentsOfNumber=0;
		depth=d;
	//	heuristic=h;
		root=new Node(rootData, null, null);
		mainRoot=root;
		newRoots.clear();
		newRoots.add(root);
		setIndex(0);
		cyrcle();
	//	System.out.println("szulok "+parentsOfNumber+" gyerekek "+childrenOfNumber+" atlago elagazasi tenyezo "+childrenOfNumber/parentsOfNumber);
		Minimax minmaxTree=new Minimax(mainRoot);
		maxNode=minmaxTree.maxNode;
	/*	System.out.println("gyoker ertek: "+mainRoot.getValue());
		System.out.println("max erteke "+maxNode.getValue());
	*/	
	}
	
	/**Ciklussal felepiti a jatekfat depth melysegig*/
	public void cyrcle(){
		while(actualDepth<depth){
			roots.clear();
			for(Node node: newRoots){
				roots.add(node);
			}
			newRoots.clear();
			for(Node node: roots){
				nextStep(node, node.data.getModel());
				if(node.children.size()!=0){
					parentsOfNumber++;
					childrenOfNumber=childrenOfNumber+node.children.size();
				}
			//	System.out.println("fiai szama "+node.children.size());
			}
			actualDepth++;
		}
	}
	
	/**Kiszamolja az aktualis csomopont erteket a csomoponthoz tartozo tabla alapjan
	 * @param model az adott minta
	 * @param node az adott csucspont*/
	public int sum(int model, Node node){
	//	Heuristic result = null;
	//	if(heuristic==1){
			Calculater result=new Calculater(model, node.data.getTable());
	//	}
	//	if(heuristic==2){
	//		Winner result= new Winner(node, model);
	//		result=(Heuristic) Class.forName(Winner(node, model)).newInstance();
	//	}
		return result.setValue();
	}

	/**A root csomopontbol minden lehetseges lepest megvizsgal es ha szabalyos, akkor meghivja a newChild()-ot.
	 * @param root aktualis gyoker, az o fiait keresem
	 * @param model aktualis minta, amivel lepni kell.*/
	public void nextStep(Node root, int model){
		newTable=(QuixoBoard) root.data.getTable().clone();
		for(int a=0; a<5; a++){
			for(int b=0; b<5; b++){
				/**ha vmelyik csucsra akarok tenni*/
				if((b==0 || a==4) && (a==0 || b==4)){
					for(int i=0; i<5; i++){
						/**elobb az ureseket*/
						if(newTable.getField(Math.abs(i-a), b)==QuixoBoard.empty && newTable.legal(Math.abs(i-a), b, model, a, b)){
							step=new Move(Math.abs(i-a), b, a, b);
							newChild((model+1)%2, step, root);
						}
						if(newTable.getField(a, Math.abs(i-b))==QuixoBoard.empty && newTable.legal(a, Math.abs(i-b), model, a, b)){
							step=new Move(a, Math.abs(i-b), a, b);
							newChild((model+1)%2, step, root);
						}
					}
					/**majd a sajatokat*/
					for(int i=0; i<5; i++){
						if(newTable.getField(Math.abs(i-a), b)==model && newTable.legal(Math.abs(i-a), b, model, a, b)){
							step=new Move(Math.abs(i-a), b, a, b);
							newChild((model+1)%2, step, root);
						}
						if(newTable.getField(a, Math.abs(i-b))==model && newTable.legal(a, Math.abs(i-b), model, a, b)){
							step=new Move(a, Math.abs(i-b), a, b);
							newChild((model+1)%2, step, root);
						}
					}
				}
		
				/**fuggolegesen van szelen*/
				if((b==0 || b==4) && a!=0 && a!=4){
					/**elobb forditani probalok*/
					if(a+1<5 && newTable.getField(a+1, b)==model){
						for(int i=0; i<a; i++){
							if(newTable.getField(i, b)==QuixoBoard.empty && newTable.legal(i, b, model, 4, b)){
								step=new Move(i, b, 4, b);
								newChild((model+1)%2, step, root);
							}
						}
					}
					if(a-1 >-1 && newTable.getField(a-1, b)==model){
						for(int i=0; i<a; i++){
							if(newTable.getField(i, b)==QuixoBoard.empty && newTable.legal(i, b, model, 0, b)){
								step=new Move(i, b, 0, b);
								newChild((model+1)%2, step, root);
							}
						}
					}
					/**ha nem tudok forditani*/
					if(a+1<5 && newTable.getField(a+1, b)==model){
						for(int i=0; i<a; i++){
							if(newTable.getField(i, b)==model && newTable.legal(i, b, model, 4, b)){
								step=new Move(i, b, 4, b);
								newChild((model+1)%2, step, root);
							}
						}
					}
					if(a-1 >-1 && newTable.getField(a-1, b)==model){
						for(int i=0; i<a; i++){
							if(newTable.getField(i, b)==model && newTable.legal(i, b, model, 0, b)){
								step=new Move(i, b, 0, b);
								newChild((model+1)%2, step, root);
							}
						}
					}
					if(newTable.getField(a, 4-b)!=(model+1)%2 && newTable.legal(a, 4-b, model, a, b)){
						step=new Move(a, 4-b, a, b);
						newChild((model+1)%2, step, root);
					}
				}
		
				/**vizszintesen van szelen*/
				if((a==0 || a==4) && b!=0 && b!=4){
					/**forditani probalok*/
					if(b+1<5 && newTable.getField(a, b+1)==model){
						for(int i=a+1;  i<5; i++){
							if(newTable.getField(a, i)==QuixoBoard.empty && newTable.legal(a, i, model, a, 4)){
								step=new Move(a, i, a, 4);
								newChild((model+1)%2, step, root);
							}
						}
					}
					if(b-1>-1 && newTable.getField(a, b-1)==model){
						for(int i=0;  i<a; i++){
							if(newTable.getField(a, i)==QuixoBoard.empty && newTable.legal(a, i, model, a, 0)){
								step=new Move(a, i, a, 0);
								newChild((model+1)%2, step, root);
							}
						}
					}
					/**ha nem tudok forditani*/
					if(b+1<5 && newTable.getField(a, b+1)==model){
						for(int i=a+1;  i<5; i++){
							if(newTable.getField(a, i)==model && newTable.legal(a, i, model, a, 4)){
								step=new Move(a, i, a, 4);
								newChild((model+1)%2, step, root);
							}
						}
					}
					if(b-1>-1 && newTable.getField(a, b-1)==model){
						for(int i=0;  i<a; i++){
							if(newTable.getField(a, i)==model && newTable.legal(a, i, model, a, 0)){
								step=new Move(a, i, a, 0);
								newChild((model+1)%2, step, root);
							}
						}
					}
					if(newTable.getField(4-a, b)!=(model+1)%2 && newTable.legal(4-a, b, model, a, b)){
						step=new Move(4-a, b, a, b);
						newChild((model+1)%2, step, root);
					}
				}
		
				/**ha nincs szelen*/
				if(a!=0 && a!=4 && b!=0 && b!=4){
					/**oszlopban kovetkezot tolom a jo helyre*/
					if(a+1<5 && newTable.getField(a+1, b)==model){
						if((newTable.getField(0, b)==model || newTable.getField(0, b)==QuixoBoard.empty) && newTable.legal(0, b, model, 4, b)){
							step=new Move(0, b, 4, b);
							newChild((model+1)%2, step, root);
						}
					}
					/**oszlopban elozot tolom a jo helyre*/
					if(a-1>-1 && newTable.getField(a-1, b)==model){
						if((newTable.getField(4, b)==model || newTable.getField(4, b)==QuixoBoard.empty) && newTable.legal(4, b, model, 0, b)){
							step=new Move(4, b, 0, b);
							newChild((model+1)%2, step, root);
						}
					}
					/**sorban kovetkezot tolom a jo helyre*/
					if(b+1<5 && newTable.getField(a, b+1)==model){
						if((newTable.getField(a, 0)==model || newTable.getField(a, 0)==QuixoBoard.empty) && newTable.legal(a, 0, model, a, 4)){
							step=new Move(a, 0, a, 4);
							newChild((model+1)%2, step, root);
						}
					}
					/**sorban elozot tolom a jo helyre*/
					if(b-1>-1 && newTable.getField(a, b-1)==model){
						if((newTable.getField(a, 4)==model || newTable.getField(a, 4)==QuixoBoard.empty) && newTable.legal(a, 4, model, a, 0)){
							step=new Move(a, 4, a, 0);
							newChild((model+1)%2, step, root);
						}
					}
				}
			}
		}
		return;
	}
	
	/**Az aktualis csomopont uj fiat letrehozza es hozzaadja az apa gyerekeinek listajahoz
	 * @param model 
	 * @param step
	 * @param parent*/
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
		if(parent.children.contains(child)){
			child=null;
			System.out.println("nyertem!");
			newTable=(QuixoBoard) parent.data.table.clone();
			return;
		}
		parent.children.add(child);
		newRoots.add(child);
		if(child.index==depth){
			child.setLeaf(true);
		}
		if(child.isLeaf()){
			//child.setValue(sum(model, child.data.getTable()));
			child.setValue(sum(model, child));
		}
	//	System.out.println("lepes "+step+" level-e: "+child.isLeaf());
		newTable=(QuixoBoard) parent.data.table.clone();
		return;
	}
}