public class Moho extends SimplePlayer{
	public Pair pair;
	public TreeStructure tree;
	public int depth=1;
	
	public Move nextMove(Move prevStep, long oTime) {
		pair=new Pair(color, table);
		tree = new TreeStructure(pair, table, depth, null, getColor());
		return tree.maxNode.step;
	}
	
	/*public Move max(){
		Node d=null;
		int maxValue=264*(-3);
		for(Node c : tree.newRoots){
			if(c.getValue()>maxValue){
				step=c.getStep();
				maxValue=c.getValue();
			}
			if(c.isLeaf()){
				step=undo(c);
				return step;
			}
			d=c;
		}
		step=undo(d);
		tree=null;
		return step;
	}
	
	public Move undo(Node n){
		while(n.index!=1){
			n=n.parent;
		}
		return n.getStep();
	}*/
}