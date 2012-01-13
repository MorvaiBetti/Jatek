public class Moho extends SimplePlayer{
	public TreeStructure tree;
	public int depth=1;
	
	public Move nextMove(Move prevStep, long oTime) {
		tree = new TreeStructure(getColor(), table, depth, null);
		return tree.maxNode.step;
	}
}