public class Moho extends SimplePlayer{
	public TreeStructure tree;
	
	public Move nextMove(Move prevStep) {
		tree = new TreeStructure(getColor(), table, 1);
	//	System.out.println("eredmeny "+tree.maxNode);
		return tree.maxNode.step;
	}
}