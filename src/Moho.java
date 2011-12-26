public class Moho extends SimplePlayer{
	public Pair pair;
	public TreeStructure tree;
	
	public Move nextMove(Move prevStep, long oTime) {
		pair=new Pair(color, table);
		tree = new TreeStructure(pair, 3, null, getColor());
	//	max();
		return max();
	}
	
	public Move max(){
		Node c = null;
		/*for(int i=0; i<TreeStructure.newRoots.size(); i++){
			n.add(TreeStructure.newRoots.remove(i));
		}*/
	
		int maxValue=-1000;
		for(int i=0; i<tree.newRoots.size(); i++){
			c=tree.newRoots.remove(i);
			if(c.getValue()>maxValue){
				step=c.getStep();
				maxValue=c.getValue();
			}
			if(c.isLeaf()){
				step=undo(c);
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
