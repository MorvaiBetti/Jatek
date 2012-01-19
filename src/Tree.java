public class Tree extends SimplePlayer{
		public TreeStructure tree;
		public int depth;
		public Node maxNode=null;
		
		public void setDepth(int d){
			depth=d;
		}
		
		public Move nextMove() {
			tree = new TreeStructure(getColor(), table, depth);
			step=tree.maxNode.step;
			return step;
		}
}