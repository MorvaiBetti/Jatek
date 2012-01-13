public class Tree extends SimplePlayer{
		public TreeStructure tree;
		public int depth;
		public Node maxNode=null;
		
		public void setDepth(int d){
			depth=d;
		}
		
		public Move nextMove(Move prevStep, long oTime) {
			tree = new TreeStructure(getColor(), table, depth, null);
			step=tree.maxNode.step;
			return step;
		}
}