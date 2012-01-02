import java.util.*;

public class Tree extends SimplePlayer{
		public Pair pair;
		public TreeStructure tree;
		public int depth;
		public ArrayList<Node> newRoot=new ArrayList<Node>(80);
		public ArrayList<Node> newChildren=new ArrayList<Node>(80);
		public Node maxNode=null;
		
		public void setDepth(int d){
			depth=d;
		}
		
		public Move nextMove(Move prevStep, long oTime) {
			pair=new Pair(color, table);
			tree = new TreeStructure(pair, depth, null, getColor());
			//a depth melysegig kifejtett gyerekeket nezi at
		/*	for(int i=0; i<tree.newRoots.size(); i++){
				newRoot.add(tree.newRoots.remove(i));
			}
		*/	
			for(int i=0; i<tree.root.children.size(); i++){
				newRoot.add(tree.root.children.remove(i));
			}
			step=undo(find());
			tree=null;
			newRoot.clear();
			return step;
		}
		
		public Move undo(Node n){
			while(n.index!=1){
				System.out.println(n);
				n=n.parent;
			}
			return n.getStep();
		}
		
		public Node find(){
			Node node;
			int max=264*(-3);
			for(int i=0; i<newRoot.size(); i++){
				node=newRoot.remove(i);
				if(node.isLeaf()){
					//System.out.println("Level");
					if(node.data.model!=color){
						System.out.println("Sajat levelet");
						i=newRoot.size();
						maxNode=node;
						maxNode.value=264*3;
						return maxNode;
					}
				}
				if(node.index==depth && max<node.value){
					max=node.value;
					maxNode=node;
					System.out.println("uj max");
				}
				if(node.index!=depth){
					for(int j=0; j<node.children.size(); j++){
						newChildren.add(node.children.remove(j));
					}
				}
			}
			if(newRoot.remove(0).index!=depth){
				newRoot.clear();
				for(int i=0; i<newChildren.size(); i++){
						newRoot.add(newChildren.remove(i));
				}
				newChildren.clear();
				find();
			}
			return maxNode;
		}
}