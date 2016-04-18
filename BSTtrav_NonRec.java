import java.util.ArrayList;
import java.util.Stack;

class Node
{
	int data;
	Node left;
	Node right;
	
	Node()
	{
		left = null;
		right = null;
	}
}

public class BSTtrav_NonRec 
{
	static Node root, temp;

	//----------------- INSERT -------------------
	private static void insert(int i)
	{
		Node temp = new Node();
		temp.data = i;	
		if(root == null)
		{
			root = new Node();
			root = temp;
			return;
		}
		else
		{
			search(root,temp);
		}
	}
	
	private static void search(Node root2, Node temp) 
	{
		if( (root2.left != null) && (root2.data > temp.data) )
			search(root2.left,temp);
		else if( (root2.left == null) && (root2.data > temp.data) )
			root2.left = temp;
		else if( (root2.right != null) && (root2.data < temp.data) )
			search(root2.right,temp);
		else if( (root2.right == null) && (root2.data < temp.data) )
			root2.right=temp;
	}
	//--------------------------------------------------
	
//----------------------Display----------------------------
//	System.out.println(root2.data);
//	if(root2.left != null)
//		display_Inorder(root2.left);
//	if(root2.right != null)
//		display_Inorder(root2.right);

	private static void display_Preorder_1S(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		Stack<Node> s1 = new Stack<Node>();
		Node temp = new Node();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		temp = root;
		s1.push(temp);
		
		while(!s1.isEmpty())
		{
			temp = s1.pop();
			ans.add(temp.data);
			
			if(temp.right!=null)
				s1.push(temp.right);
			if(temp.left!=null)
				s1.push(temp.left);
		}
		System.out.println("Preorder_1S :"+ans);
		return;
	}
	//--------------------------------------------------
	
	
	//----------------------Display----------------------------

	//--------------------------------------------------
	private static void display_Inorder_1S(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		Stack<Node> s1 = new Stack<Node>();
		Node temp = new Node();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		temp = root;
		s1.push(temp);

		while(!s1.isEmpty())
		{
			while(temp.left!=null)
			{
				s1.push(temp.left);
				temp = temp.left;
			}
			temp = s1.pop();
			ans.add(temp.data);
			if(temp.right!=null)
			{
				s1.push(temp.right);
				temp = temp.right;
			}			
		}
		
		System.out.println("Inorder_1S :"+ans);
		
	}
	//-------------------------------------------------
	
	
	//----------------------Display----------------------------
	
	private static void display_Postorder_1S(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		Stack<Node> s1 = new Stack<Node>();
		Node temp = new Node();	
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int count = 0;
		temp = root;
		do
		{
			while(temp!=null)
			{
				if(temp.right!=null)
					s1.push(temp.right);
				s1.push(temp);
				temp = temp.left;
			}

			temp = s1.pop();

			if(temp.right != null && !s1.isEmpty() && temp.right == s1.peek())
			{
				Node temp1 = s1.pop();
				s1.push(temp);
				temp = temp1;
			}
			else
			{
				ans.add(temp.data);
				temp = null;
			}
		}while(!s1.isEmpty());
		
		System.out.println("Postorder_1S :"+ans);
		return;
	}
//--------------------------------------------------
	private static void display_Postorder_2S(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		Node temp = new Node();

		temp = root;
		s1.push(temp);
		
		while(!s1.isEmpty())
		{
			temp = (s1.pop());
			s2.push(temp);
			if(temp.left!=null)
				s1.push(temp.left);
			if(temp.right!=null)
				s1.push(temp.right);
		}
		System.out.print("Postorder_1S :");
		while(!s2.isEmpty())
		{
			System.out.print(s2.pop().data+" ");
		}
		System.out.println();
		return;
	}
//--------------------------------------------------	


	static Node pRoot = new Node();
	static int k = -1;
	public static void main(String[] args) 
	{		
		insert(40);
		insert(20);
		insert(60);
		insert(10);
		insert(30);
		insert(50);
		insert(70);
		
		//display_Preorder_1S(root);
		display_Inorder_1S(root);
		//display_Postorder_1S(root);
		//display_Postorder_2S(root);
		
		getMedian(root);
		System.out.println("Number of Nodes: "+ count);
		count = count/2;
		median(root);

	}

	private static void median(Node root2) {
		if(root2 == null)
			return;

		if(root2.left != null) {
			median(root2.left);
			count--;
		}
		
		if(count==0) {
			System.out.println(root2.data);
			return;
		}

		if(root2.right != null) {
			median(root2.right);
			count--;
		}
	}


	static int count = 0;
	private static void getMedian(Node root2) 
	{
		if(root2 == null)
			return;

		if(root2.left != null)
			getMedian(root2.left);

		count++;

		if(root2.right != null)
			getMedian(root2.right);
	}

	

}

