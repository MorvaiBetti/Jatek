import java.util.*;

public class Node {
	public Pair data;
	public int value;
	public Node parent;
	public ArrayList<Node> children=new ArrayList<Node>(80);
	public boolean leaf;
	public int index;
	public Move step;
	
	public Node(Pair d, Node p, int v, Move s){
		data=d;
		parent=p;
		value=v;
		step=s;
	}

	public void addChild(Node child, Node parent, int v, Move s){
		/*if(child.getTable().win(parent.data.getModel())){
			parent.setLeaf(true);
			
			System.out.println("Level vagyok");
			return;
		}*/
		parent.setLeaf(false);
		parent.children.add(child);
		child.setIndex(parent.getIndex()+1);
	//	System.out.println("ertekem: "+v);
	}
	
	public Move getStep() {
		return step;
	}
	public void setStep(Move step) {
		this.step = step;
	}
	public Pair getData() {
		return data;
	}
	public void setData(Pair data) {
		this.data = data;
	}
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(Node child) {
		this.children.add(child);
	}
	
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Node [" +
			//	"value=" + value + 
			//	", parent=" + parent + 
			//	", children="+ children + 
				", data="+ data+
				", index=" + index + "]";
	}

}
