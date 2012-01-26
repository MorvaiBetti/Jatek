package quixo.players.minimax;


public class Minimax{
	/**@min minimalis ertek*/
//	public int min;
	/**@max maximalis ertek*/
//	public int max;
	/**@maxNode kovetkezo lepes*/
	public Node maxNode;
	/**@value minimax algoritmus eredmenye*/
	public int value;
	
	
	public Minimax(Node root){
		if(root.model%2==1){
			root.value=minValue(root, -900000, 900000);
		}else 	{root.value=maxValue(root, -900000, 900000);}
		System.out.println("Kolkok syama "+root.children.size()+" erteke "+root.value);
		for(Node n:  root.children){
			if(n.value==root.value){
				maxNode=n;
			}
		}
	//	System.out.println("maxom "+maxNode);
	}
	
	public int maxValue(Node node, int alfa, int beta){
		if(node.isLeaf() || node.isEnd() || node.children.isEmpty()){
			return node.value;
		}
		int max=-Integer.MAX_VALUE;
		for(Node child : node.children){
				max=Math.max(max, minValue(child, alfa, beta));
				if(max>=beta){
					node.value=max;	
					return max;
				}
				alfa=Math.max(max, alfa);
		}
		node.value=max;
		return max;
	}
	
	public int minValue(Node node, int alfa, int beta){
		if(node.isLeaf() || node.isEnd() || node.children.isEmpty()){
			return node.value;
		}
		int min=Integer.MAX_VALUE;
		for(Node child: node.children){
				min=Math.min(min, maxValue(child, alfa, beta));
				if(alfa>=min){
			 		node.value=min;
					return min;
				}
				beta=Math.min(min, beta);
		}
		node.value=min;
		return min;
	}
}