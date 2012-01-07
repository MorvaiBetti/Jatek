public class Tree extends SimplePlayer{
		public Pair pair;
		public TreeStructure tree;
		public int depth;
		public Node maxNode=null;
		
		public void setDepth(int d){
			depth=d;
		}
		
		public Move nextMove(Move prevStep, long oTime) {
			pair=new Pair(color, table);
			tree = new TreeStructure(pair, depth, null, getColor());
			step=tree.maxNode.step;
			return step;
		}
}