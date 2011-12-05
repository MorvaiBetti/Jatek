public class TreeStructure extends Node{
	public Node root;
	public int[][] fields=new int[25][7];
	public QuixoBoard newTable;
	public int my=3;
	public int your=-3;
	public int nobody=1;
	public int id;
	public int value;
	
	public TreeStructure(Pair rootData, int depth){
		super(rootData, null, 0);
		setIndex(id);
		makeTree(depth, rootData.getModel());
	}
	
	public void makeTree(int depth, int model){
		for(id=0; id<=depth; id++){
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					nextStep(i,j, model);
				}
			}
		}
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
						if(data.table.getField(l,j)==model){
							fields[k][2]+=my;
						}
						if(data.table.getField(l,j)==QuixoBoard.empty){
							fields[k][2]+=nobody;
						}
						if(data.table.getField(l,j)==(model+1)%2){
							fields[k][2]+=your;
						}
					}
					if(l!=j){
						//sor
						if(data.table.getField(i,l)==model){
							fields[k][3]+=my;
						}
						if(data.table.getField(i,l)==QuixoBoard.empty){
							fields[k][3]+=nobody;
						}
						if(data.table.getField(i,l)==(model+1)%2){
							fields[k][3]+=your;
						}
					}
					if(i==j){
						//foatlo
						if(l!=i){
							if(data.table.getField(l,l)==model){
								fields[k][4]+=my;
							}
							if(data.table.getField(l,l)==QuixoBoard.empty){
								fields[k][4]+=nobody;
							}
							if(data.table.getField(l,l)==(model+1)%2){
								fields[k][4]+=your;
							}
						}
					}
					if(i==4-j){
						//mellekatlo
						if(l!=i){
							if(data.table.getField(l,4-l)==model){
								fields[k][5]+=my;
							}
							if(data.table.getField(l,4-l)==QuixoBoard.empty){
								fields[k][5]+=nobody;
							}
							if(data.table.getField(l,4-l)==(model+1)%2){
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
		return value;
	}
	
	//a=sor b=oszlop
	public void nextStep(int a, int b, int model){
		System.out.println("a: "+a+" b: "+b);
		newTable=(QuixoBoard) data.getTable().clone();
		Pair newData; 
		//ha vmelyik csucsra akarok tenni
		if((b==0 || a==4) && (a==0 || b==4)){
			for(int i=0; i<5; i++){
				//elobb az �reseket
				if(data.table.getField(Math.abs(i-a), b)==QuixoBoard.empty && data.table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
				if(data.table.getField(a, Math.abs(i-b))==QuixoBoard.empty && data.table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
			}
			//majd a sajatokat
			for(int i=0; i<5; i++){
				if(data.table.getField(Math.abs(i-a), b)==model && data.table.legal(Math.abs(i-a), b, model, a, b)){
					newTable.makeStep(Math.abs(i-a), b, model, a, b);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
				if(data.table.getField(a, Math.abs(i-b))==model && data.table.legal(a, Math.abs(i-b), model, a, b)){
					newTable.makeStep(a, Math.abs(i-b), model, a, b);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
			}
		}
		
		//fuggolegesen van szelen
		if((b==0 || b==4) && a!=0 && a!=4){
			//elobb forditani probalok
			if(a+1<5 && data.table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(data.table.getField(i, b)==QuixoBoard.empty && data.table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			if(a-1 >-1 && data.table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(data.table.getField(i, b)==QuixoBoard.empty && data.table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			//ha nem tudok forditani
			if(a+1<5 && data.table.getField(a+1, b)==model){
				for(int i=0; i<a; i++){
					if(data.table.getField(i, b)==model && data.table.legal(i, b, model, 4, b)){
						newTable.makeStep(i, b, model, 4, b);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			if(a-1 >-1 && data.table.getField(a-1, b)==model){
				for(int i=0; i<a; i++){
					if(data.table.getField(i, b)==model && data.table.legal(i, b, model, 0, b)){
						newTable.makeStep(i, b, model, 0, b);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			if(data.table.getField(a, 4-b)!=(model+1)%2 && data.table.legal(a, 4-b, model, a, b)){
				newTable.makeStep(a, 4-b, model, a, b);
				newData=new Pair(model, newTable);
				value=sum(data.getModel());
				addChild(newData, this, value);
				newTable=(QuixoBoard) data.table.clone();
			}
		}
			
		//vizszintesen van szelen
		if((a==0 || a==4) && b!=0 && b!=4){
			//forditani probalok
			if(b+1<5 && data.table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(data.table.getField(a, i)==QuixoBoard.empty && data.table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			if(b-1>-1 && data.table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(data.table.getField(a, i)==QuixoBoard.empty && data.table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			//ha nem tudok forditani
			if(b+1<5 && data.table.getField(a, b+1)==model){
				for(int i=a+1;  i<5; i++){
					if(data.table.getField(a, i)==model && data.table.legal(a, i, model, a, 4)){
						newTable.makeStep(a, i, model, a, 4);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			if(b-1>-1 && data.table.getField(a, b-1)==model){
				for(int i=0;  i<a; i++){
					if(data.table.getField(a, i)==model && data.table.legal(a, i, model, a, 0)){
						newTable.makeStep(a, i, model, a, 0);
						newData=new Pair(model, newTable);
						value=sum(data.getModel());
						addChild(newData, this, value);
						newTable=(QuixoBoard) data.table.clone();
					}
				}
			}
			if(data.table.getField(4-a, b)!=(model+1)%2 && data.table.legal(4-a, b, model, a, b)){
				newTable.makeStep(4-a, b, model, a, b);
				newData=new Pair(model, newTable);
				value=sum(data.getModel());
				addChild(newData, this, value);
				newTable=(QuixoBoard) data.table.clone();	
			}
		}
			
		//ha nincs szelen
		if(a!=0 && a!=4 && b!=0 && b!=4){
			//oszlopban kovetkezot tolom a jo helyre
			if(a+1<5 && data.table.getField(a+1, b)==model){
				if((data.table.getField(0, b)==model || data.table.getField(0, b)==QuixoBoard.empty) && data.table.legal(0, b, model, 4, b)){
					newTable.makeStep(0, b, model, 4, b);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
			}
			//oszlopban elozot tolom a jo helyre
			if(a-1>-1 && data.table.getField(a-1, b)==model){
				if((data.table.getField(4, b)==model || data.table.getField(4, b)==QuixoBoard.empty) && data.table.legal(4, b, model, 0, b)){
					newTable.makeStep(4, b, model, 0, b);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
			}
			//sorban kovetkezot tolom a jo helyre
			if(b+1<5 && data.table.getField(a, b+1)==model){
				if((data.table.getField(a, 0)==model || data.table.getField(a, 0)==QuixoBoard.empty) && data.table.legal(a, 0, model, a, 4)){
					newTable.makeStep(a, 0, model, a, 4);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
			}
			//sorban elozot tolom a jo helyre
			if(b-1>-1 && data.table.getField(a, b-1)==model){
				if((data.table.getField(a, 4)==model || data.table.getField(a, 4)==QuixoBoard.empty) && data.table.legal(a, 4, model, a, 0)){
					newTable.makeStep(a, 4, model, a, 0);
					newData=new Pair(model, newTable);
					value=sum(data.getModel());
					addChild(newData, this, value);
					newTable=(QuixoBoard) data.table.clone();
				}
			}
		}
		return;
	}
}
