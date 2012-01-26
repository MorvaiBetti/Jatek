public class Minimax{
	/**@min minimalis ertek*/
	public int min;
	/**@max maximalis ertek*/
	public int max;
	/**@maxNode kovetkezo lepes*/
	public Node maxNode;
	/**@value minimax algoritmus eredmenye*/
	public int value;
	
	/*public Minimax(Node root){
		value=Evaluate(root, -900000, 900000);
		for(Node n:  root.children){
			if(n.value==value){
				maxNode=n;
			}
		}
	}
	
	public int Evaluate(Node node, int alpha, int beta){
	   if (node.isLeaf() || node.isEnd()){
	       return node.value;
	   } 
	   if((node.index%2)==0){
	       for(Node child : node.children){
	           beta = Math.min (beta, Evaluate (child, alpha, beta));
	           if(beta <= alpha){
	               return alpha;
	   			}
	       }
	       return beta;
	   }
	   if((node.index%2)==1){
	       for(Node child: node.children){
	           alpha = Math.max (alpha, Evaluate (child, alpha, beta));
	           if (beta <= alpha)
	               return beta;
	       }
	       return alpha;
	   }
	   return 0;
	}*/
	
	
	
	
	
	
	
	
	public Minimax(Node root){
		root.value=maxValue(root, -900000, 900000);
		System.out.println("Gyoker ertekeeeeee "+root.value);
		for(Node n:  root.children){
			if(n.value==root.value){
				maxNode=n;
			}
		}
	}
	
	public int maxValue(Node node, int alfa, int beta){
		if(node.isLeaf() || node.isEnd()){
			return node.value;
		}
		max=-900000000;
		for(Node child : node.children){
			max=Math.max(max, minValue(child, alfa, beta));
			if(max>=beta){
				node=null;
				return max;
			}
			alfa=Math.max(max, alfa);
		}
		node.value=max;
		return max;
	}
	
	public int minValue(Node node, int alfa, int beta){
		if(node.isLeaf() || node.isEnd()){
			return node.value;
		}
		min=900000000;
		for(Node child: node.children){
			min=Math.min(min, maxValue(child, alfa, beta));
			if(alfa>=min){
				node=null;
				return min;
			}
			beta=Math.min(min, beta);
		}
		node.value=min;
		return min;
	}
	
	
	
	
	/*public Node lastNode;
	public Node maxNode;
	public int index;

	public Minmax(Node root, int index){
		this.index=index;
	}
	
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
		int min=1000000;
		for(Node child: node.children){
			if(child.value<min){
				min=child.value;
			}
			if(alfa>=min){
				return;
			}
			beta=Math.min(min, beta);
		}
		node.value=min;
	}
	
	public void maxValue(Node node, int alfa, int beta){
		int max=-1000000;
		for(Node child: node.children){
			if(child.value>max){
				maxNode=child;
				max=child.value;
			}
		//	System.out.println("child "+child);
			if(max>=beta){
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
		if(root.index==index){
			maxValue(root, alfa, beta);
			return;
		}
		if(root.index>=1){
			upMinmax(root.parent, alfa, beta);
		}
	}*/
}