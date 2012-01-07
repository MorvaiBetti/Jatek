public class Moho extends SimplePlayer{
	public Pair pair;
	public TreeStructure tree;
	public int depth=1;
	
	public Move nextMove(Move prevStep, long oTime) {
		pair=new Pair(color, table);
		tree = new TreeStructure(pair, depth, null, getColor());
		return tree.maxNode.step;
	}
}