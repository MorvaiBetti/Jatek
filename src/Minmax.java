public class Minmax{
	public Node lastNode;
	public Node maxNode;

	public Minmax(Node root, int depth){}
	
	public Node start(Node root, int depth){
		if(depth>2){
			downMinmax(root, depth);
			upMinmax(lastNode.parent.parent, 0, 0);
		} if(depth==2){
			downMinmax(root, depth);
			maxValue(root, 0, 0);
		}else {maxValue(root, 0, 0);}
		return maxNode;
	}
	
	public void minValue(Node node, int alfa, int beta){
		int min=1000;
		for(Node child: node.children){
			if(child.value<min){
				min=child.value;
			}
			if(alfa>=min){
				//System.out.println("min vagas");
				return;
			}
			beta=Math.min(min, beta);
		}
		node.value=min;
	}
	
	public void maxValue(Node node, int alfa, int beta){
		int max=-10000;
		for(Node child: node.children){
			if(child.value>max){
				maxNode=child;
				max=child.value;
			}
			if(max>=beta){
				//System.out.println("max vagas");
				return;
			}
			alfa=Math.max(max, alfa);
		}
		node.value=max;
	}
	
	public Node downMinmax(Node root, int depth){
		for(Node node : root.children){
			if(node.index==(depth-1) && !node.isLeaf()){
				if((node.index%2)==0){
					maxValue(node, 0, 0);
				}
				if((node.index%2)==1){
					minValue(node, 0, 0);
				}
				lastNode=node;
			}
			if(node.index<(depth-1)){
				downMinmax(node, depth);
			}
		}
		return lastNode;
	}
	
	public void upMinmax(Node root, int alfa, int beta){
		for(Node child :root.children){
			if(!child.isLeaf()){
				if((child.index%2)==0){
					maxValue(child, alfa, beta);
				}
				if((child.index%2)==1){
					minValue(child, alfa, beta);
				}
			}
		}
		if(root.index==0){
			maxValue(root, alfa, beta);
			return;
		}
		if(root.index>=1){
			upMinmax(root.parent, alfa, beta);
		}
	}
}