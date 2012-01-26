public class Tree extends SimplePlayer{
		public TreeStructure tree=null;
		/**@depth milyen melysegig vizsgalja a jatekfat*/
		public int depth;
		/**@maxNode kovetkezo lepes*/
		public Node maxNode=null;
	//	public Minimax minmaxTree;
	//	public int[] delete;
		
		public void setDepth(int d){
			depth=d;
		}
		
		public Move nextMove(Move prevStep) {
		//	if(tree==null){
				tree = new TreeStructure(getColor(), table, depth);
				maxNode=tree.maxNode;
				tree.mainNode=tree.maxNode;
				return maxNode.step;
		/*	}
			for(Node node: tree.mainNode.children){
				if(node.step.equals(prevStep)){
					tree.mainNode=node;
					System.out.println("Megvan!"+tree.mainNode);
					break;
				}
			}
			System.out.println("MERET "+tree.newRoots.size());
			delete=new int[tree.newRoots.size()];
			int i=0;
			for(Node n : tree.newRoots){
				int ind=tree.newRoots.indexOf(n);
				while(n.index!=tree.mainNode.index){
					n=n.parent;
				}
				if(n!=tree.mainNode){
					delete[i]=ind;
					i++;
				}
			}
			for(int j=0; j<i; j++){
				tree.newRoots.add(delete[j], null);
			}
			
			tree.depth=3;
			System.out.println("inditom a ciklust");
			tree.cyrcle(table);
			
			System.out.println("inditom a minmaxot");
			minmaxTree=new Minmax(tree.mainNode, tree.mainNode.index);
			System.out.println("inditom a minmaxot!!!");
			maxNode=minmaxTree.start(tree.mainNode, tree.depth);
			System.out.println(maxNode);
			return step;*/
		}
}