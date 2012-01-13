import java.util.ArrayList;

public class TreeStructure extends Node{
	public Node root;
	public int[][] fields=new int[25][7];
	public QuixoBoard newTable=new QuixoBoard();
	public QuixoBoard rootTable=new QuixoBoard();
	public QuixoBoard table=new QuixoBoard();
	public int my=3;
	public int your=-3;
	public int nobody=1;
	public int value;
	public int depth;
	public int d=0;
	public int me;
	public Node child;
	public Node now;
	public int kutya=0;
	public ArrayList<Node> roots=new ArrayList<Node>();
	public ArrayList<Node> newRoots=new ArrayList<Node>();
	public Move[] steps;
	public Minmax minmaxTree;
	public Node maxNode;
	
	public TreeStructure(int color, QuixoBoard t, int d, Move s){
		super(color, null, 0, s);
		steps=new Move[d];
		rootTable=(QuixoBoard) t.clone();
		table=(QuixoBoard) rootTable.clone();
		roots.clear();
		newRoots.clear();
		this.depth=d;
		me=color;
		root=new Node(color, null, 0, s);
		setIndex(0);
		makeTree(root, color);
		cyrcle();
		minmaxTree=new Minmax(root, depth);
		maxNode=minmaxTree.start(root, depth);
	}
	
	public void makeTree(Node node, int model){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				nextStep(i,j, model);
			}
		}
		return;
	}

	public void cyrcle(){
		d=1;
		while(d<depth){
			roots.clear();
			for(Node n:newRoots){
				roots.add(n);
			}
			newRoots.clear();
			if(roots.isEmpty()){
				System.out.println("Empty");
				break;
			}
			for(Node n: roots){
			
				if(!root.isLeaf()){
					int i=0;
					root=n;
					int m=0;
					table=(QuixoBoard) rootTable.clone();
					while(n.index!=0){
						steps[i]=n.step;
						i++;
						n=n.parent;
					}
					for(int j=i-1; j>=0; j--){
						Move stp=steps[j];
						int mdl;
						if(m%2==0){
							mdl=me;
						}else {mdl=(me+1)%2;}
						table.makeStep(stp.x, stp.y, mdl, stp.nx, stp.ny);	
						m++;
					}
				//	table=(QuixoBoard) rootTable.clone();
					makeTree(root, root.getModel());
				}
			}
			d++;
		}
	
		while(root.getIndex()!=0){
			root=root.parent;
		}
		roots.clear();
	}
	
	public void calculate(int model){
		emptyFields();
		int k=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				fields[k][0]=i;
				fields[k][1]=j;
				for(int l=0; l<5; l++){
					if(l!=i){
						//oszlop
						if(newTable.getField(l,j)==model){
							fields[k][2]+=my;
						}
						if(newTable.getField(l,j)==QuixoBoard.empty){
							fields[k][2]+=nobody;
						}
						if(newTable.getField(l,j)==(model+1)%2){
							fields[k][2]+=your;
						}
					}
					if(l!=j){
						//sor
						if(newTable.getField(i,l)==model){
							fields[k][3]+=my;
						}
						if(newTable.getField(i,l)==QuixoBoard.empty){
							fields[k][3]+=nobody;
						}
						if(newTable.getField(i,l)==(model+1)%2){
							fields[k][3]+=your;
						}
					}
					if(i==j){
						//foatlo
						if(l!=i){
							if(newTable.getField(l,l)==model){
								fields[k][4]+=my;
							}
							if(newTable.getField(l,l)==QuixoBoard.empty){
								fields[k][4]+=nobody;
							}
							if(newTable.getField(l,l)==(model+1)%2){
								fields[k][4]+=your;
							}
						}
					}
					if(i==4-j){
						//mellekatlo
						if(l!=i){
							if(newTable.getField(l,4-l)==model){
								fields[k][5]+=my;
							}
							if(newTable.getField(l,4-l)==QuixoBoard.empty){
								fields[k][5]+=nobody;
							}
							if(newTable.getField(l,4-l)==(model+1)%2){
								fields[k][5]+=your;
							}
						}
					}
				}
				k++;
			}
		}
	}
		
	public void emptyFields(){
		for(int i=0; i<25; i++){
			for(int j=2; j<6; j++){
				fields[i][j]=0;
			}
		}
	}
	
	public int sum(int model){
		calculate(model);
		int value=0;
		for(int i=0; i<25; i++){
			for(int j=2; j<6; j++){
				value=value+fields[i][j];
			}
		}
		if(model!=me){
			value=0-value;
		}
		return value;
	}
	
	//a=sor b=oszlop
	public void nextStep(int a, int b, int model){
		newTable=(QuixoBoard) table.clone();
		//ha vmelyik csucsra akarok tenni
		if((b==0 || a==4) && (a==0 || b==4)){
			for(int i=0; i<5; i++){
				//elobb az ureseket
				if(table.getField(Math.abs(i-a), b)==QuixoBoard.empty && table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(Math.abs(i-a), b, a, b);
					child=new Node((model+1)%2, root, value, step);
					addChild(child, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
				if(table.getField(a, Math.abs(i-b))==QuixoBoard.empty && table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(a, Math.abs(i-b), a, b);
					child=new Node((model+1)%2, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			//majd a sajatokat
			for(int i=0; i<5; i++){
				if(table.getField(Math.abs(i-a), b)==model && table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(Math.abs(i-a), b, a, b);
					child=new Node((model+1)%2, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
				if(table.getField(a, Math.abs(i-b))==model && table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(a, Math.abs(i-b), a, b);
					child=new Node((model+1)%2, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
		}
		
		//fuggolegesen van szelen
		if((b==0 || b==4) && a!=0 && a!=4){
			//elobb forditani probalok
			if(a+1<5 && table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==QuixoBoard.empty && table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(i, b, 4, b);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(a-1 >-1 && table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==QuixoBoard.empty && table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(i, b, 0, b);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			//ha nem tudok forditani
			if(a+1<5 && table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(table.getField(i, b)==model && table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(i, b, 4, b);
						child=new Node((model+1)%2, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
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
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(i, b, 0, b);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(table.getField(a, 4-b)!=(model+1)%2 && table.legal(a, 4-b, model, a, b)){
				newTable.makeStep(a, 4-b, model, a, b);
				if((root.index+1)==depth){
					value=sum(model);
				}else {value=0;}
				step=new Move(a, 4-b, a, b);
				child=new Node((model+1)%2, root, value, step);
				addChild(child, root, value, step);
				if(child.isLeaf()){
					child.value=sum(model);
				}
				newRoots.add(child);
				newTable=(QuixoBoard) table.clone();
			}
		}
			
		//vizszintesen van szelen
		if((a==0 || a==4) && b!=0 && b!=4){
			//forditani probalok
			if(b+1<5 && table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(table.getField(a, i)==QuixoBoard.empty && table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(a, i, a, 4);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(b-1>-1 && table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(table.getField(a, i)==QuixoBoard.empty && table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(a, i, a, 0);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			//ha nem tudok forditani
			if(b+1<5 && table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(table.getField(a, i)==model && table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(a, i, a, 4);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(b-1>-1 && table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(table.getField(a, i)==model && table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						if((root.index+1)==depth){
							value=sum(model);
						}else {value=0;}
						step=new Move(a, i, a, 0);
						child=new Node((model+1)%2, root, value, step);
						addChild(child, root, value, step);
						if(child.isLeaf()){
							child.value=sum(model);
						}
						newRoots.add(child);
						newTable=(QuixoBoard) table.clone();
					}
				}
			}
			if(table.getField(4-a, b)!=(model+1)%2 && table.legal(4-a, b, model, a, b)){
				newTable.makeStep(4-a, b, model, a, b);
				if((root.index+1)==depth){
					value=sum(model);
				}else {value=0;}
				step=new Move(4-a, b, a, b);
				child=new Node((model+1)%2, root, value, step);
				if(child.isLeaf()){
					child.value=sum(model);
				}
				addChild(child, root, value, step);
				newRoots.add(child);
				newTable=(QuixoBoard) table.clone();	
			}
		}
			
		//ha nincs szelen
		if(a!=0 && a!=4 && b!=0 && b!=4){
			//oszlopban kovetkezot tolom a jo helyre
			if(a+1<5 && table.getField(a+1, b)==model){
				if((table.getField(0, b)==model || table.getField(0, b)==QuixoBoard.empty) && table.legal(0, b, model, 4, b)){
					newTable.makeStep(0, b, model, 4, b);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(0, b, 4, b);
					Node child=new Node((model+1)%2, root, value, step);
					addChild(child, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			//oszlopban elozot tolom a jo helyre
			if(a-1>-1 && table.getField(a-1, b)==model){
				if((table.getField(4, b)==model || table.getField(4, b)==QuixoBoard.empty) && table.legal(4, b, model, 0, b)){
					newTable.makeStep(4, b, model, 0, b);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(4, b, 0, b);
					child=new Node((model+1)%2, root, value, step);
					addChild(child, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			//sorban kovetkezot tolom a jo helyre
			if(b+1<5 && table.getField(a, b+1)==model){
				if((table.getField(a, 0)==model || table.getField(a, 0)==QuixoBoard.empty) && table.legal(a, 0, model, a, 4)){
					newTable.makeStep(a, 0, model, a, 4);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(a, 0, a, 4);
					child=new Node((model+1)%2, root, value, step);
					addChild(child, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
			//sorban elozot tolom a jo helyre
			if(b-1>-1 && table.getField(a, b-1)==model){
				if((table.getField(a, 4)==model || table.getField(a, 4)==QuixoBoard.empty) && table.legal(a, 4, model, a, 0)){
					newTable.makeStep(a, 4, model, a, 0);
					if((root.index+1)==depth){
						value=sum(model);
					}else {value=0;}
					step=new Move(a, 4, a, 0);
					child=new Node((model+1)%2, root, value, step);
					addChild(child, root, value, step);
					if(child.isLeaf()){
						child.value=sum(model);
					}
					newRoots.add(child);
					newTable=(QuixoBoard) table.clone();
				}
			}
		}
		return;
	}

}