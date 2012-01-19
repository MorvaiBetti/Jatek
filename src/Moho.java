public class Moho extends SimplePlayer{
	public TreeStructure tree;
	
	public Move nextMove() {
		tree = new TreeStructure(getColor(), table, 1);
		return tree.maxNode.step;
	}
}