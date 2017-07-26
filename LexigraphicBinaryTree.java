package assignment5;  
 import java.util.Stack;  
 public class LexigraphicBinaryTree {  
   int []left;  
   int []right;  
   boolean TrueForLeft = false;  
   int strahlerN;  
   int []pruning;  
   int []strahlerNumberCount = new int[6];  
   int[] pruningNumberCount = new int [6];  
   //initializes arrays to right most chain of n nodes.  
   public void initializeLexigraphicBinaryTree(int n)  
   {  
    left = new int[n+1];  
    right = new int[n+1];  
    for (int count = 1; count<n;count++)  
    {  
      right[count]=count+1;  
    }  
     pruning = new int[n+1];  
   }  
   //generates every binary tree of n nodes in lexigraphical order  
   public void generateAllTrees(int nodes)  
   {  
     initializeLexigraphicBinaryTree(nodes);  
     binaryCode();System.out.print(” “);  
     while (!treeComplete())  
     {  
      makeNextBestLexigographicalTree();  
       count++;  
     }  
    System.out.println(count);  
   }  
   //find next best lexigographical tree  
   public void makeNextBestLexigographicalTree()  
   {  
     int n = findHighestLeafNode(); //finds the highest numbered leaf node in tree and sets to n  
     boolean originalLeftChild = false;  
     if (left[n-1]==n) originalLeftChild = true; //if n-1 node is originally a parent of n  
     int k=reversePreorderMoveByOnePosition(n,originalLeftChild); //moves the n’th node to the next best reverse preorder position  
     if (originalLeftChild)  
     {  
       makeAllNodesLessThanNBestPreorderPosition(n,k,TrueForLeft); //makes all LEAF nodes less than n the best possible preorder position  
     }  
     binaryCode();System.out.print(” “);  
     System.out.println();  
   }  
   public void makeNextBestLexigographicalTree1()  
   {  
     int n = findHighestLeafNode(); //finds the highest numbered leaf node in tree and sets to n  
     boolean originalLeftChild = false;  
     if (left[n-1]==n) originalLeftChild = true; //if n-1 node is originally a parent of n  
     int k=reversePreorderMoveByOnePosition(n,originalLeftChild); //moves the n’th node to the next best reverse preorder position  
     if (originalLeftChild)  
     {  
       makeAllNodesLessThanNBestPreorderPosition(n,k,TrueForLeft); //makes all LEAF nodes less than n the best possible preorder position  
     }  
   }  
   //takes highest leaf node N, and moves it to next best reverse preorder position taking into account the fact that parents are always less than children  
  public int reversePreorderMoveByOnePosition(int N, boolean originalLeftChild)  
  {  
    int m=0;  
    Stack stack = new Stack(); boolean foundN=false;  
     int q;int ltptr = 0;  
     int node = 1;  
     while ((node!=0)||!stack.empty())  
     {  
       if (node!=0)  
       {  
          if (foundN&&right[node]==0)  
         {  
           m = node;  
           TrueForLeft = false;  
           if (!originalLeftChild)right[node]=N;  
           else right[node]=-1;  
           return m;  
         }  
          //if the highest Leaf Node N is found.  
          if (node== N)  
         {foundN=true;  
         node=0;  
         if (!originalLeftChild)breakParent(N);  
         continue;  
         }  
         if (right[node]!=0)stack.push(node);  
         if (right[node]!=0) node = right[node];  
         else  
         {node=left[node]; }  
       }  
       else{  
         do{  
           q = (Integer)stack.peek();  
            if (!stack.empty())  
            {  
              if (left[q]==0&&foundN)  
              {  
                TrueForLeft=true;  
                if (!originalLeftChild)left[q]=N;  
                else left[q]=-1;  
                m=q;  
                return m;  
              }  
              ltptr = left[q]; stack.pop();  
            }  
           else ltptr = 0;  
         }while (!stack.empty()&&q==ltptr);  
         node = ltptr;  
       }  
     }  
    return m;  
  }  
  // If highest leaf node N was originally the left child of its n-1 node before reverse preorder moving by one position,  
  // than after it reverse preorder moving by one position, you take all leaf nodes less than N and push it onto a stack.  
  // you pop the top of the stack and move it onto the reverse preorder position and than pop the remaining stack on the  
  // best possible reverse preorder position.  
   public void makeAllNodesLessThanNBestPreorderPosition(int n, int k, boolean TrueForLeft)  
   {  
     int j = n; Stack stack = new Stack();  
     while (left[j]==0&&right[j]==0)  
     {  
       stack.push(j);  
       int ko =findParent(j);  
       breakParent(j);  
       j = ko;  
     }  
     if (TrueForLeft&&!stack.empty())  
     {  
       left[k]=(Integer)stack.peek();stack.pop();  
     }  
     else if (!TrueForLeft&&!stack.empty())  
     {  
       right[k]=(Integer)stack.peek();stack.pop();  
     }  
    int node = 1;  
    while (right[node]!=0)  
    {  
      node = right[node];  
    }  
    while (!stack.empty())  
    {  
      right[node]=(Integer)stack.peek(); stack.pop();  
      node = right[node];  
    }  
   }  
  public void breakParent(int n)  
  {  
    for (int i = 1; i<=left.length;i++)  
    {  
      if (left[i]==n)  
      {left[i]=0;break;}  
      if (right[i]==n)  
      {  
        right[i]=0;break;  
      }  
    }  
  }  
  //checks to see if current tree is the final left chain train of N nodes.  
  public boolean treeComplete()  
  {  
    boolean treeComplete = true;  
    int n = left.length-1;  
    for (int i = 1; i<n;i++)  
    {  
      if (left[i]==i+1) treeComplete = true;  
      else  
      {  
        treeComplete = false; break;  
      }  
    }  
    return treeComplete;  
  }  
  public int findParent(int j)  
  {  
    int i =1;  
    for (;i<left.length;i++)  
    {  
      if (left[i]==j||right[i]==j)  
      {  
        return i;  
      }  
    }  
     i = 1;  
    return i;  
  }  
  public int findHighestLeafNode()  
  {  
    int j = left.length-1;  
    for (;2<j;j–)  
    {  
      if (left[j]==0&&right[j]==0) break;  
    }  
    return j;  
  }  
  public void binaryCode ()  
   {  
     Stack stack = new Stack();  
     int q;int rtptr = 0;  
     int node = 1;  
     while ((node!=0)||!stack.empty())  
     {  
       if (node!=0)  
       {  
         System.out.print(“1”);  
         if (left[node]!=0)stack.push(node);  
         if (left[node]!=0) node = left[node];  
         else  
         {node=right[node];  
         System.out.print(“0”);  
         }  
       }  
       else{System.out.print(“0”);  
         do{  
            q = (Integer)stack.peek();  
            if (!stack.empty())  
            {rtptr = right[q]; stack.pop();}  
           else rtptr = 0;  
         }while (!stack.empty()&&q==rtptr);  
         node = rtptr;  
       }  
     }  
   }  
  //prints left and right arrays  
   public void printArray()  
   {  
   System.out.print(” left array:[“);  
   for (int count = 1; count<left.length;count++)  
   {  
     if (count==left.length-1)System.out.print(left[count]);  
     if (count!=left.length-1)  
     System.out.print(left[count]+”,”);  
   }  
   System.out.print(“]”);  
   System.out.print(” right array:[“);  
   for (int count = 1; count<left.length;count++)  
   {  
     if (count==right.length-1)System.out.print(right[count]);  
     if (count!=right.length-1)  
     System.out.print(right[count]+”,”);  
   }  
   System.out.print(“]”);  
   }  
   public void preorder ()  
   {  
     Stack stack = new Stack();  
     int q;int rtptr = 0;  
     int node = 1;  
     while ((node!=0)||!stack.empty())  
     {  
       if (node!=0)  
       {  
         System.out.print(node);System.out.print(“,”);  
         if (left[node]!=0)stack.push(node);  
         if (left[node]!=0) node = left[node];  
         else  
         {node=right[node];  
         }  
       }  
       else{  
         do{  
            q = (Integer)stack.peek();  
            if (!stack.empty())  
            {  
              rtptr = right[q];  
              stack.pop();  
            }  
           else rtptr = 0;  
         }while (!stack.empty()&&q==rtptr);  
         node = rtptr;  
       }  
     }  
   }  
 }  