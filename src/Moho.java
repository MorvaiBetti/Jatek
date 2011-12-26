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
		int maxValue=-1000;
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
		System.out.println("max: "+maxValue+" lepes: "+step);
		return step;
	}
	
	public Move undo(Node n){
		while(n.getIndex()!=1){
	//		System.out.println("lepegetek: \n"+n);
			n=n.parent;
		}
	//	System.out.println("lepegetek: \n"+n);
		return n.getStep();
	}
}