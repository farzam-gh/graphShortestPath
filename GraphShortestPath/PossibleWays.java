import java.util.*;
public class PossibleWays{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int[] note=new int[n+1];
note[0]=n;
for(int i=1;i<=n;i++){
	note[i]=sc.nextInt();
}//end of for i
Graph g=new Graph(n);
for(int i=0;i<n-1;i++){
	g.addEdge(sc.nextInt(),sc.nextInt());
}//end of for i
int[] p=new int[n+1];
if(note[1]!=0)
	p[0]=1;
else
	p[0]=n;
int temp=0;
for(int i=2;i<=n;i++){
	if (note[i]!=0){
		temp=find(g,note[i]);
		p[temp]=1;
	}else{
		p[i]=g.vertices.get(i-1).adj.size();
	}//end of else
}//end of for i
int sum=1;
for(int i=0;i<=n;i++){
	if(p[i]>0)
		sum*=p[i];
	//System.out.println(p[i]);
}//end of for i
	System.out.println(sum);
	}//end of main
	public static int find(Graph g,int no){
	for(Node node:g.vertices)
		if(node.adj.contains(no))
			return node.data;
	return 0;
}//end of find
}//end of class
class Node{
	int data;
	HashSet<Integer> adj=new HashSet<>();
public Node(int data){
	this.data=data;
}//end of constructor
public void addEdge(int d){
	adj.add(d);
}//end  of addEdge		
}//end of Node class
class Graph{
	ArrayList<Node>vertices=new ArrayList<>();
	Graph(int v){
	for(int i=1;i<=v;i++){
		vertices.add(new Node(i));
	}//end of for i
	}//end of constructor
	public void addEdge(int s,int d){
		vertices.get(s).addEdge(d);
	}//end of addEdge
}//end of Graph class