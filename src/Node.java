import java.util.*;

public class Node {
	public Pair data;
	public int value;
	public Node parent;
	public ArrayList<Node> children=new ArrayList<Node>(80);
	public boolean leaf;
	public int index;
	
	public Node(Pair d, Node p, int v){
		data=d;
		parent=p;
		value=v;
	}

	public void addChild(Pair child, Node parent, int v){
		if(child.getTable().win(parent.data.getModel())){
			parent.setLeaf(true);
			System.out.println("Level vagyok");
			return;
		}
		parent.setLeaf(false);
		Node newChild=new Node(child, parent, v);
		children.add(newChild);
		newChild.setIndex(parent.getIndex()+1);
		System.out.println("ertekem: "+v);
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

}
