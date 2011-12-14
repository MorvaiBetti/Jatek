public class Moho extends Tree{
	
	public Move nextMove(Move prevStep, long oTime) {
		Pair p=new Pair(color, table);
		tree = new TreeStructure(p, 3, null, getColor());
	//	max();
		return max();
	}
	
	public Move max(){
		Node n, c = null;
		n=tree.root;
	
		maxValue=-1000;
		for(int i=0; i<n.children.size(); i++){
			c=n.children.remove(i);
			if(c.getValue()>maxValue){
				step=c.getStep();
				maxValue=c.getValue();
			}
		}
		step=undo(c);
		System.out.println("max: "+maxValue+" lepes: "+step);
		return step;
	}
	
	public Move undo(Node n){
		while(n.getIndex()!=1){
			n=n.parent;
		}
		return n.getStep();
	}

}
public class Moho extends Tree{
	
	public Move nextMove(Move prevStep, long oTime) {
		Pair p=new Pair(color, table);
		tree = new TreeStructure(p, 3, null, getColor());
	//	max();
		return max();
	}
	
	public Move max(){
		Node n, c = null;
		n=tree.root;
	
		maxValue=-1000;
		for(int i=0; i<n.children.size(); i++){
			c=n.children.remove(i);
			if(c.getValue()>maxValue){
				step=c.getStep();
				maxValue=c.getValue();
			}
		}
		step=undo(c);
		System.out.println("max: "+maxValue+" lepes: "+step);
		return step;
	}
	
	public Move undo(Node n){
		while(n.getIndex()!=1){
			n=n.parent;
		}
		return n.getStep();
	}

}
