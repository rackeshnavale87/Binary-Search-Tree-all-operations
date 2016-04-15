package pkg;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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

// Binary tree common ancestor *******************************NOT BST
static boolean v1 = false, v2 = false;
 Node findLCA(int n1, int n2) {
     v1 = false;
     v2 = false;
     Node lca = findLCAUtil(root, n1, n2);
     if (v1 && v2)
        return lca;
     else
     	return null;
 }
 Node findLCAUtil(Node node, int n1, int n2) {
	 if(node == null)
		 return null;
	 if(node.data == n1 && node.data == n2) {
		 v1=true;
		 v2 = true;
		 return node;
	 }
	 if(node.data == n1) {
		 v1 = true;
		 return node;
	 } 
	 if(node.data==n2) {
		 v2 = true;
		 return node;
	 }
	 Node left = findLCAUtil(node.left, n1,n2);
	 Node right = findLCAUtil(node.right,n1,n2);
	 
	 if(left!=null && right !=null)
		 return node;
	 return left!=null ? left : right;
 }
 // Finds lca of n1 and n2 under the subtree rooted with 'node'

// Binary tree common ancestor *******************************

public class BST_All_Operations_Functions 
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
	private static void display_Inorder(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		if(root2.left != null)
			display_Inorder(root2.left);
		System.out.print(root2.data +" ");
		if(root2.right != null)
			display_Inorder(root2.right);
	}
	//--------------------------------------------------
	
	//----------------------Display----------------------------
	private static void display_Postorder(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		if(root2.left != null)
			display_Inorder(root2.left);
		if(root2.right != null)
			display_Inorder(root2.right);
		System.out.println(root2.data);
	}
	//--------------------------------------------------
	
	//----------------------Display----------------------------
	private static void display_Preorder(Node root2) 
	{
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		System.out.println(root2.data);
		if(root2.left != null)
			display_Inorder(root2.left);
		if(root2.right != null)
			display_Inorder(root2.right);
	}
	//--------------------------------------------------
	
	//---------------------- Delete ----------------------------

	private static void delete(Node root2, int val) 
	{
		if(root2 == null)
		{	
			System.out.println("Element NOT found in BST");
			return;
		}		
		if(root2.data > val)
		{
			delete(root2.left,val);
		}
		else if(root2.data < val)
		{
			delete(root2.right,val);
		}
		else if(root2.data == val)
		{
			if(root2.left == null && root2.right == null)
				if(root2 == root)
				{
					root= null;
					return;
				}
				else
				{
					remove(root2.data,root);
				}
			
			else if(root2.left == null)
			{
				temp = root2.right;
				while(temp.left != null)
					temp = temp.left;
				root2.data = temp.data;
				remove(temp.data,root2);
			}
			else if(root2.right == null)
			{
				temp = root2.left;
				while(temp.right != null)
					temp = temp.right;
				root2.data = temp.data;
				remove(temp.data,root2);
			}
			else
			{
				temp = root2.left;
				while(temp.right != null)
					temp = temp.right;
				root2.data = temp.data;
				remove(temp.data,root2);
			}
		}
		else
		{		}
	}
	//--------------------------------------------------
	
	private static void remove(int data, Node root2) 
	{
		myparent(data, root2);
		if(pRoot.left!= null && pRoot.left.data == data)
			pRoot.left = null;
		else if(pRoot.right!= null && pRoot.right.data == data)
			pRoot.right = null;		
	}

	private static Node myparent(int data, Node R) 
	{
		if( (R.left==null) && (R.right==null) )
			return null;
		
		if( (R.left!=null) || (R.right!=null) )
		{
			if(R.left.data == data || R.right.data == data)
			pRoot = R;
			return pRoot;
		}
		else if (R.data <= data)
			myparent(data, R.right);
		else if (R.data > data)
			myparent(data, R.left);
		else
			pRoot = null;
		return pRoot;
	}
	
	//--------------------------------------------------

	//----------------------Common ancestor----------------------------

	private static void coommonancestor(Node root2, int i, int j) 
	{
		if(i == j && j == root2.data)
			System.out.println("Both nodes are same");
		
		if(root2.data < i && root2.data < j)
			coommonancestor(root2.right,i,j);
			
		if(root2.data > i && root2.data > j)
			coommonancestor(root2.left,i,j);
			
		if(root2.data >= i && root2.data <= j)
			System.out.println("The ancestor of "+i+" and "+j+ " is :" + root2.data);		
	}
	
	//--------------------------------------------------

	private static int height(Node root2) 
	{
		if(root2 == null)
			return 0;
		else
			return 1+ Math.max(height(root2.left),height(root2.right) );
	}

	private static int diameter(Node root2) 
	{
		 if (root2 == null)
		     return 0;	
		 
		  int lheight = height(root2.left);
		  int rheight = height(root2.right);
		 
		  /* get the diameter of left and right sub-trees */
		  int ldiameter = diameter(root2.left);
		  int rdiameter = diameter(root2.right);
		 
		  /* Return max of following three
		   1) Diameter of left subtree
		   2) Diameter of right subtree
		   3) Height of left subtree + height of right subtree + 1 */
		  return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));
	}

	//--------------------------------------------------

	
	private static void before_after(Node root2, int i) 
	{
		if(root2 == null)
		{
			System.out.println("Element is NOT present in the BST");
			return;
		}
		if(root2.data== i)
		{
			if(root2.left!= null)
			{
				temp =  root2.left;
				while(temp.right != null)
					temp = temp.right;
				System.out.println("The predecessor of "+i+" in the BST is "+temp.data);
			}
			else
			{
					myparent(root2.data, root);
					while(pRoot.data >= i && pRoot != null && pRoot.data != root.data)
					{
						myparent(pRoot.data,root);
					}
					if(pRoot != null && pRoot.data != root.data)
						System.out.println("The predecessor of "+i+" in the BST is "+pRoot.data);					
					else
						System.out.println("There is NO predecessor of "+i+" in the BST.");
			}
			
			if(root2.right!= null)
			{
				temp =  root2.right;
				while(temp.left != null)
					temp = temp.left;
				System.out.println("The successor of "+i+" in the BST is "+temp.data);
			}
			else
			{
					myparent(root2.data, root);
					while(pRoot.data <= i && pRoot != null && pRoot.data != root.data)
					{
						myparent(pRoot.data,root);
					}
					if(pRoot != null && pRoot.data != root.data)
						System.out.println("The successor of "+i+" in the BST is "+pRoot.data);					
					else
						System.out.println("There is NO successor of "+i+" in the BST.");
			}
		}
		
		else if(root2.data < i)
			before_after(root2.right,i);
		
		else if(root2.data > i)
			before_after(root2.left,i);
	}
	
	
	
	//--------------------------------------------------

	
	
	

	
	//--------------------------------------------------
	//--------------------------------------------------
	
	static Node pRoot = new Node();
	static int k = -1;
	public static void main(String[] args) 
	{		
		insert(30);
//		insert(5);
//		insert(35);
//		insert(2);
//		insert(32);
//		insert(81);
//		insert(27);
//		insert(8);
//		insert(1);
//		insert(20);
//		insert(41);
//		insert(31);
//		insert(122);
		
		/*Node n1 = new Node();
		n1.data = 20;
		root.left = n1;
		
		Node n2 = new Node();
		n2.data = 40;
		root.right = n2;
		
		Node n3 = new Node();
		n3.data = 5;
		root.left.left = n3;
		
		Node n4 = new Node();
		n4.data = 21;
		root.left.right = n4;*/
		
		
		//display_Preorder(root);
		//display_Postorder(root);
		//display_Inorder(root);
		//System.out.println("\nRoot is :" + root.data);
		System.out.println("\n--------------------------------------");

		/*delete(root,5);
		display_Inorder(root);
		System.out.println();
		System.out.println("Root is :" + root.data);
		
		coommonancestor(root,2,5);
		
		int ht = height(root);		System.out.println("Height of the tree is : "+ht);
		
		int d = diameter(root);		System.out.println("Diameter of the tree is : "+d);*/

		//before_after(root,12);
		/*boolean check = checkBST(root);
		if(check)
			System.out.println("Tree is BST");
		else
			System.out.println("Tree is NOT BST");
		
		k = 10;
		kthelement(root);
		*/
		
		//int k1 = 12, k2 = 41;
		//All_inrange(root,k1,k2);
		
		
		//boolean check = checkBalancedBST(root);
//		if(check)
//			System.out.println("Tree is Balanced");
//		else
//			System.out.println("Tree is NOT Balanced");

		findLevellist(temp);
		
		Node x = root;
		int ans = inorderSucc(x);
		System.out.println("Successor of "+x.data+" is : "+ans);
	}

	private static boolean checkBalancedBST(Node root2) 
	{
		if(root2==null)
			return true;
		else if( (maxDepth(root2)-minDepth(root2)<=1))
		{
			System.out.println("MIN depth : "+minDepth(root2));
			System.out.println("MAX depth : "+maxDepth(root2));
			return true;
		}
		else
			return false;
	}

	private static int minDepth(Node root2) {
		if(root2 == null)
			return 0;
		else
		{
			int dp = Math.min(maxDepth(root2.left), maxDepth(root2.right));
			return 1+ (dp);
		}
	}

	private static int maxDepth(Node root2) {
		if(root2 == null)
			return 0;
		else
		{
			int dp = Math.max(maxDepth(root2.left), maxDepth(root2.right));
			return 1+ (dp);
		}
	}

	private static void All_inrange(Node root2, int k1, int k2) 
	{
		if(root2 == null)
			return;
		
		if(root2.data > k1)
			All_inrange(root2.left,k1,k2);
		
		if(k1 <= root2.data && root2.data <= k2)
			System.out.println(root2.data+" ");
		
		if(root2.data < k2)
			All_inrange(root2.right,k1,k2);	
	}

	
	public static int inorderSucc(Node root2) 
	{
		if(root2.right!=null)
		{
			root2 = root2.right;		
			while(root2.left!=null)
				root2 = root2.left;
			//System.out.println("Successor : "+ root2.data);
			return root2.data;
		}
		
		Node parent = new Node();
		
		if(root2.right == null)
		{
			parent = myparent(root2.data, root);
			while(parent !=null && parent.left != root2)
			{
				root2 = parent;
				parent = myparent(parent.data, root);
			}
		}
		if(parent!=null)
			root2 = parent;
		else
		{
			System.out.println("No parent");
			return -1;
		}
		
		//System.out.println("Successor : "+ root2.data);
		return root2.data;
	}	
		
	private static ArrayList<LinkedList<Node>> findLevellist(Node root2)
	{
	
		ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> list = new LinkedList<Node>(); list.add(root);
		int level = 0;
		result.add(level, list);
		
		while (true) 
		{
			list = new LinkedList<Node>();
			for (int i = 0; i < result.get(level).size(); i++) 
			{
				Node n = (Node) result.get(level).get(i); if (n != null) 
					if (n != null) 
					{
						if(n.left != null)
							list.add(n.left);
						if(n.right!=null) 
							list.add(n.right);
					}
			}
			if (list.size() > 0) 
			{
				result.add(level+1,list);
			} 
			else
			{ 
				break;
			}
			level++; 
		}
		
		Iterator<LinkedList<Node>> Itr = result.iterator();
		while(Itr.hasNext())
		{
			LinkedList<Node> L = Itr.next();
			Iterator<Node> Itr2 = L.iterator();
			while(Itr2.hasNext())
			{
				System.out.print(Itr2.next().data+" ");
			}
			System.out.println();
		}
		
		return result; 
	}
	
	
	
	
	
	
	
	private static void kthelement(Node root2) 
	{
		
		if(root2 == null)
		{
			System.out.println("BST is empty");
			return;
		}
		if(root2.left != null && k>=0)
			kthelement(root2.left);
		k--;
		if(k==0)
		{
			System.out.print("\nKth element is :"+root2.data +" & k is "+ k);
			return;
		}
		if(root2.right != null && k>=0)
			kthelement(root2.right);		
	}

	private static boolean checkBST(Node root2) 
	{
		if(root2 == null)
		{
			return true;
		}		
		if((root2.left != null && root2.data < root2.left.data) || (root2.right!= null &&root2.data > root2.right.data))
			return false;
		
		if( (!checkBST(root2.left)||!checkBST(root2.right)) )
			return false;
		
		return true;
	}

}

