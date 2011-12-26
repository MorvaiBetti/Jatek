public class Moho extends SimplePlayer{
	public Pair pair;
	public TreeStructure tree;
	
	public Move nextMove(Move prevStep, long oTime) {
<<<<<<< HEAD
		pair=new Pair(color, table);
		tree = new TreeStructure(pair, 3, null, getColor());
=======
		Pair p=new Pair(color, table);
		tree = new TreeStructure(p, 3, null, getColor());
>>>>>>> origin/master
	//	max();
		return max();
	}
	
	public Move max(){
<<<<<<< HEAD
		Node c = null;
		/*for(int i=0; i<TreeStructure.newRoots.size(); i++){
			n.add(TreeStructure.newRoots.remove(i));
		}*/
	
		int maxValue=-1000;
		for(int i=0; i<tree.newRoots.size(); i++){
			c=tree.newRoots.remove(i);
=======
		Node n, c = null;
		n=tree.root;
	
		maxValue=-1000;
		for(int i=0; i<n.children.size(); i++){
			c=n.children.remove(i);
>>>>>>> origin/master
			if(c.getValue()>maxValue){
				step=c.getStep();
				maxValue=c.getValue();
			}
<<<<<<< HEAD
			if(c.isLeaf()){
				step=undo(c);
=======
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
>>>>>>> origin/master
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
