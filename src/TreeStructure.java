import java.util.ArrayList;

public class TreeStructure extends Node{
	public Node root;
	public int[][] fields=new int[25][7];
	public QuixoBoard newTable;
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
	public ArrayList<Node> roots=new ArrayList<Node>(80);
	public ArrayList<Node> newRoots=new ArrayList<Node>(80);
	
	public TreeStructure(Pair rootData, int d, Move s, int model){
		super(rootData, null, 0, s);
		this.depth=d;
		me=model;
		System.out.println("melyseg= "+depth);
		root=new Node(rootData, null, 0, s);
		setIndex(0);
		makeTree(root, rootData.getModel());
		cyrcle();
	}
	
	public void makeTree(Node node, int model){
	//	System.out.println("Kifejtve! szint: "+node.getIndex()+ " menet: "+depth);
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				nextStep(i,j, model);
			}
		}
	}
	
	public void step(ArrayList<Node> nodes){
		//System.out.println("bent vok ************************************************");
		for(int i=0; i<nodes.size(); i++){
			root=nodes.remove(i);
			makeTree(root, root.data.getModel());
		}
	}
	public void cyrcle(){
		d=newRoots.remove(newRoots.size()-1).getIndex();
		while(d<depth){
			d=newRoots.remove(newRoots.size()-1).getIndex();
			System.out.println("Melyseg: "+d);
			roots.clear();
			for(int i=0; i<newRoots.size(); i++){
				roots.add(newRoots.remove(i));
			}
			newRoots.clear();
			for(int i=0; i<roots.size(); i++){
				root=roots.remove(i);
				makeTree(root, root.data.getModel());
			}
			//step(roots);
			d++;
		}
	}
	
	public void calculate(int model){
		emptyFields();
		int k=0;

	//	System.out.println("Tabla CALC.: "+newTable);
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
	//	System.out.println("model: "+model+" me "+me);
		int value=0;
		for(int i=0; i<25; i++){
			for(int j=2; j<6; j++){
				value=value+fields[i][j];
			}
		}
		if(model!=me){
		//	System.out.println("ellen");
			value=0-value;
		}
//		System.out.println("ertek: "+value);
		return value;
	}
	
	//a=sor b=oszlop
	public void nextStep(int a, int b, int model){
	//	System.out.println("a: "+a+" b: "+b);
		
		newTable=(QuixoBoard) data.getTable().clone();
		Pair newData; 
		//ha vmelyik csucsra akarok tenni
		if((b==0 || a==4) && (a==0 || b==4)){
			for(int i=0; i<5; i++){
				//elobb az ďż˝reseket
				if(root.data.table.getField(Math.abs(i-a), b)==QuixoBoard.empty && root.data.table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(Math.abs(i-a), b, a, b);
					child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
				if(root.data.table.getField(a, Math.abs(i-b))==QuixoBoard.empty && root.data.table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(a, Math.abs(i-b), a, b);
					child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
			}
			//majd a sajatokat
			for(int i=0; i<5; i++){
				if(root.data.table.getField(Math.abs(i-a), b)==model && root.data.table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(Math.abs(i-a), b, a, b);
					child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
				if(root.data.table.getField(a, Math.abs(i-b))==model && root.data.table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(a, Math.abs(i-b), a, b);
					child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
			}
		}
		
		//fuggolegesen van szelen
		if((b==0 || b==4) && a!=0 && a!=4){
			//elobb forditani probalok
			if(a+1<5 && root.data.table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(root.data.table.getField(i, b)==QuixoBoard.empty && root.data.table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(i, b, 4, b);
						child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			if(a-1 >-1 && root.data.table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(root.data.table.getField(i, b)==QuixoBoard.empty && root.data.table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(i, b, 0, b);
						child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			//ha nem tudok forditani
			if(a+1<5 && root.data.table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(root.data.table.getField(i, b)==model && root.data.table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(i, b, 4, b);
						child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			if(a-1 >-1 && root.data.table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(root.data.table.getField(i, b)==model && root.data.table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(i, b, 0, b);
						Node child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			if(root.data.table.getField(a, 4-b)!=(model+1)%2 && root.data.table.legal(a, 4-b, model, a, b)){
				newTable.makeStep(a, 4-b, model, a, b);
				newData=new Pair((model+1)%2, newTable);
				value=sum(model);
				step=new Move(a, 4-b, a, b);
				Node child=new Node(newData, root, value, step);
				addChild(child, root, value, step);
				newRoots.add(child);
				newTable=(QuixoBoard) root.data.table.clone();
			}
		}
			
		//vizszintesen van szelen
		if((a==0 || a==4) && b!=0 && b!=4){
			//forditani probalok
			if(b+1<5 && root.data.table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(root.data.table.getField(a, i)==QuixoBoard.empty && root.data.table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(a, i, a, 4);
						Node child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			if(b-1>-1 && root.data.table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(root.data.table.getField(a, i)==QuixoBoard.empty && root.data.table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(a, i, a, 0);
						Node child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			//ha nem tudok forditani
			if(b+1<5 && root.data.table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(root.data.table.getField(a, i)==model && root.data.table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(a, i, a, 4);
						Node child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			if(b-1>-1 && root.data.table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(root.data.table.getField(a, i)==model && root.data.table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						newData=new Pair((model+1)%2, newTable);
						value=sum(model);
						step=new Move(a, i, a, 0);
						Node child=new Node(newData, root, value, step);
						addChild(child, root, value, step);
						newRoots.add(child);
						newTable=(QuixoBoard) root.data.table.clone();
					}
				}
			}
			if(root.data.table.getField(4-a, b)!=(model+1)%2 && root.data.table.legal(4-a, b, model, a, b)){
				newTable.makeStep(4-a, b, model, a, b);
				newData=new Pair((model+1)%2, newTable);
				value=sum(model);
				step=new Move(4-a, b, a, b);
				Node child=new Node(newData, root, value, step);
				addChild(child, root, value, step);
				newRoots.add(child);
				newTable=(QuixoBoard) root.data.table.clone();	
			}
		}
			
		//ha nincs szelen
		if(a!=0 && a!=4 && b!=0 && b!=4){
			//oszlopban kovetkezot tolom a jo helyre
			if(a+1<5 && root.data.table.getField(a+1, b)==model){
				if((root.data.table.getField(0, b)==model || root.data.table.getField(0, b)==QuixoBoard.empty) && root.data.table.legal(0, b, model, 4, b)){
					newTable.makeStep(0, b, model, 4, b);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(0, b, 4, b);
					Node child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
			}
			//oszlopban elozot tolom a jo helyre
			if(a-1>-1 && root.data.table.getField(a-1, b)==model){
				if((root.data.table.getField(4, b)==model || root.data.table.getField(4, b)==QuixoBoard.empty) && root.data.table.legal(4, b, model, 0, b)){
					newTable.makeStep(4, b, model, 0, b);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(4, b, 0, b);
					Node child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
			}
			//sorban kovetkezot tolom a jo helyre
			if(b+1<5 && root.data.table.getField(a, b+1)==model){
				if((root.data.table.getField(a, 0)==model || root.data.table.getField(a, 0)==QuixoBoard.empty) && root.data.table.legal(a, 0, model, a, 4)){
					newTable.makeStep(a, 0, model, a, 4);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(a, 0, a, 4);
					Node child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
			}
			//sorban elozot tolom a jo helyre
			if(b-1>-1 && root.data.table.getField(a, b-1)==model){
				if((root.data.table.getField(a, 4)==model || root.data.table.getField(a, 4)==QuixoBoard.empty) && root.data.table.legal(a, 4, model, a, 0)){
					newTable.makeStep(a, 4, model, a, 0);
					newData=new Pair((model+1)%2, newTable);
					value=sum(model);
					step=new Move(a, 4, a, 0);
					Node child=new Node(newData, root, value, step);
					addChild(child, root, value, step);
					newRoots.add(child);
					newTable=(QuixoBoard) root.data.table.clone();
				}
			}
		}
		return;
	}
}