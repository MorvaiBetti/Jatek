import java.util.*;

public class Tree extends SimplePlayer{
		public Pair pair;
		public TreeStructure tree;
		public int depth;
		public ArrayList<Node> newRoot=new ArrayList<Node>(80);
		public ArrayList<Node> newChildren=new ArrayList<Node>(80);
		
		public void setDepth(int d){
			depth=d;
		}
		
		public Move nextMove(Move prevStep, long oTime) {
			pair=new Pair(color, table);
		//	System.out.println("tabla "+pair.table);
			tree = new TreeStructure(pair, depth, null, getColor());
			//a depth melysegig kifejtett gyerekeket nezi at
			for(int i=0; i<tree.newRoots.size(); i++){
				newRoot.add(tree.newRoots.remove(i));
			}
			
		/*	for(int i=0; i<tree.root.children.size(); i++){
				newRoot.add(tree.root.children.remove(i));
			}*/
			
			step=undo(find());
			tree=null;
			newRoot.clear();
			return step;
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
					System.out.println("levelet talaltam!");
					step=undo(c);
					return step;
				}
			}
			step=undo(c);
		//	System.out.println("max: "+maxValue+" lepes: "+step);
			return step;
		}
		
		public Move undo(Node n){
		//	System.out.println("hatha: \n"+n);
		/*	System.out.println("index: \n"+n.parent);
		*/	
			while(n.parent.data.table!=table){	//java.lang.NullPointerException
			//	System.out.println("index: \n"+n.index);
				n=n.parent;
			}
	//		System.out.println("apa: \n"+n.parent);
	//		System.out.println("fia: \n"+n);
			return n.getStep();
		//	return null;
		}
		
		public Node find(){
			Node node, maxNode=null;
			int max=264*(-3);
	//		System.out.println("root "+newRoot.remove(0));
			//max=newRoot.remove(0).value;
			for(int i=0; i<newRoot.size(); i++){
				node=newRoot.remove(i);
			//	max=node.value;
				/*if(node.isLeaf() && tree.root.data.model==color){
					return node;
				}*/
		//		System.out.println("aktualis "+node.index+"\tMelysegg: "+depth);
			//	System.out.println("max "+max+" value "+node.value);
				if(node.index==depth && max<node.value){
			//		System.out.println("bent vok");
					max=node.value;
					maxNode=node;
				}/*
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
			}*/
	
			}
			return maxNode;
		}
}