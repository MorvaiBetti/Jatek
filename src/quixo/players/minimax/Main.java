package quixo.players.minimax;

import quixo.engine.Move;
import quixo.players.minimax.Node;

public class Main {
	public static Node root;
//	public static String s="";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node newRoot;
		root=new Node(0, null, 0, null);
		root.ind=1;
		
		Node elso=new Node(1, root, 0, null);
		elso.ind=2;
		root.children.add(elso);
		Node masodik=new Node(1, root, 0, null);
		masodik.ind=3;
		root.children.add(masodik);
		Node harmadik=new Node(1, root, 0, null);
		harmadik.ind=4;
		root.children.add(harmadik);
		
		//elso fiu
		Node child=new Node(0, elso, 5, null);
		child.ind=5;
		child.leaf=true;
		elso.children.add(child);
		child=new Node(0, elso, 0, null);
		child.ind=6;
		elso.children.add(child);
			newRoot=child;
			child=new Node(1, newRoot, 6, null);
			child.leaf=true;
			child.ind=13;
			newRoot.children.add(child);
			child=new Node(1, newRoot, 8, null);
			child.leaf=true;
			child.ind=14;
			newRoot.children.add(child);
			
		//harmadik fiu	
		child=new Node(0, harmadik, 3, null);
		child.leaf=true;
		child.ind=10;
		harmadik.children.add(child);
		child=new Node(0, harmadik, 0, null);
		child.ind=11;
		harmadik.children.add(child);
			newRoot=child;
			child=new Node(1, newRoot, 7, null);
			child.leaf=true;
			child.ind=18;
			newRoot.children.add(child);
			child=new Node(1, newRoot, -9, null);
			child.leaf=true;
			child.ind=19;
			newRoot.children.add(child);
		child=new Node(0, harmadik, 0, null);
		child.ind=12;
		harmadik.children.add(child);
			newRoot=child;
			child=new Node(1, newRoot, -1, null);
			child.leaf=true;
			child.ind=20;
			newRoot.children.add(child);
			
		//masodik fiu	
		child=new Node(0, masodik, 0, null);
		child.ind=7;
		masodik.children.add(child);
			newRoot=child;
			child=new Node(1, newRoot, 6, null);
			child.leaf=true;
			child.ind=15;
			newRoot.children.add(child);
		child=new Node(0, masodik, -2, null);
		child.leaf=true;
		child.ind=8;
		masodik.children.add(child);
		child=new Node(0, masodik, 0, null);
		child.ind=9;
		masodik.children.add(child);
		Node m=child;
			newRoot=child;
			child=new Node(1, newRoot, 0, null);
			child.ind=16;
			newRoot.children.add(child);
				newRoot=child;
				child=new Node(0, newRoot, -8, null);
				child.leaf=true;
				child.ind=21;
				newRoot.children.add(child);
				child=new Node(0, newRoot, 3, null);
				child.leaf=true;
				child.ind=22;
				newRoot.children.add(child);
		child=new Node(1, m, 5, null);
		child.leaf=true;
		child.ind=17;
		m.children.add(child);
		
		System.out.println(root);
		
		Minimax minimax=new Minimax(root);
		
		System.out.println(root);
	}

	
}
