public class Minmax{
	public Node lastNode;
	public Node maxNode;

	public Minmax(Node root, int depth){}
	
	public Node start(Node root, int depth){
		if(depth>2){
			downMinmax(root, depth);
			upMinmax(lastNode.parent.parent, depth);
		} if(depth==2){
			downMinmax(root, depth);
			maxValue(root);
		}else {maxValue(root);}
		return maxNode;
	}
	
	public void minValue(Node node){
		int min=1000;
		for(Node child: node.children){
			if(child.value<min){
				min=child.value;
			}
		}
		node.value=min;
	}
	
	public void maxValue(Node node){
		int max=-10000;
		for(Node child: node.children){
			if(child.value>max){
				maxNode=child;
				max=child.value;
			}
		}
		node.value=max;
	}
	
	public Node downMinmax(Node root, int depth){
		for(Node node : root.children){
			if(node.index==(depth-1) && !node.isLeaf()){
				if((node.index%2)==0){
					maxValue(node);
				}
				if((node.index%2)==1){
					minValue(node);
				}
				lastNode=node;
			}
			if(node.index<(depth-1)){
				downMinmax(node, depth);
			}
		}
		return lastNode;
	}
	
	public void upMinmax(Node root, int depth){
		for(Node child :root.children){
			if(!child.isLeaf()){
				if((child.index%2)==0){
					maxValue(child);
				}
				if((child.index%2)==1){
					minValue(child);
				}
			}
		}
		if(root.index==0){
			maxValue(root);
			return;
		}
		if(root.index>=1){
			upMinmax(root.parent, depth);
		}
	}
}