public class Moho extends SimplePlayer{
	public Pair pair;
	public TreeStructure tree;
	
	public Move nextMove(Move prevStep, long oTime) {
		pair=new Pair(color, table);
		tree = new TreeStructure(pair, 1, null, getColor());
		return max();
	}
	
	public Move max(){
		Node c = null;
		int maxValue=264*(-3);
		for(int i=0; i<tree.newRoots.size(); i++){
			c=tree.newRoots.remove(i);
			if(c.getValue()>maxValue){
				step=c.getStep();
				maxValue=c.getValue();
			}
			if(c.isLeaf()){
				step=undo(c);
				return step;
			}
		}
		step=undo(c);
		tree=null;
		return step;
	}
	
	public Move undo(Node n){
		while(n.index!=1){
			System.out.println(n);
			n=n.parent;
		}
		return n.getStep();
	}
}