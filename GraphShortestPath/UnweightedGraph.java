import java.io.*;
import java.util.*;

public class UnweightedGraph {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); 
        char temp;
        int start=0;
		int dest=0;
        int n=sc.nextInt();
        Graph g=new Graph(n*n);
        for(int i=0;i<n*n;i++){
            temp=sc.next().charAt(0);
            g.vertices.get(i).data=temp;
            if(temp=='m')
                start=i;
            if(temp=='p')
                dest=i;
        }//end of for i
        for(int i=0;i<n*n;i++){
           
            if(i>=n)
            g.addEdge(i,i-n);
            
            if(i%n>0)
            g.addEdge(i,i-1);
            if(i%n<n-1)
            g.addEdge(i,i+1);
            
            if(i+n<n*n)
            g.addEdge(i,i+n);
           
        }//end of for i
        dijkstra(g,n,start,dest); 
		/*for(int i=0;i<n*n;i++){
			System.out.println(g.vertices.get(i).id+" : "+g.vertices.get(i).pl+" , "+g.vertices.get(i).pre.id+" ,neighbors "+g.vertices.get(i).adj);
		}//end of for i*/
        Node current=g.vertices.get(dest);
		ArrayList<Integer>answer=new ArrayList<>();
        while(current!=g.vertices.get(start)){
			answer.add(current.id);
            current=current.pre;
        }//end of while		
		int s=start;		
		for(int i=answer.size()-1;i>=0;i--){
			if(answer.get(i)-s==-n)				
				System.out.println("UP");
			else if(answer.get(i)-s==-1)				
				System.out.println("LEFT");
			else if(answer.get(i)-s==1)				
				System.out.println("RIGHT");				
			else if(answer.get(i)-s==n)					
				System.out.println("DOWN");
			
			s=answer.get(i);
		}//end of for i
    }//end of main
    public static void dijkstra(Graph g,int n,int start,int dest){
        int min=0;
        Node current;
        ArrayList<Integer>unsettled=new ArrayList<>();
        for(int i=0;i<n*n;i++)
            unsettled.add(i);
        g.vertices.get(start).pl=0;  
		g.vertices.get(start).pre=g.vertices.get(start);	
        while(unsettled.size()>0){
            min=findMin(g,unsettled);
            current=g.vertices.get(min);
            unsettled.remove(unsettled.indexOf(min));
            for(Integer x:current.adj){
                if(g.vertices.get(x).pl>current.pl+1){
                    g.vertices.get(x).pl=current.pl+1;
                    g.vertices.get(x).pre=current;
                }//end if                    
            }//end of ex-for            
        }//end of while        
    }//end of dijkstra
    public static int findMin(Graph g,ArrayList<Integer> unsettled){
        int min=Integer.MAX_VALUE;
        int index=0;
        for(Integer x:unsettled){
            if(g.vertices.get(x).pl<min){
                min=g.vertices.get(x).pl;
                index=x;
            }//end dof for            
        }//end of ex-for
		return index;
    }//end of findMin
}//end of Solution
///////////////////////////////////////////////
class Node{
    int id;
    char data;
    int pl=Integer.MAX_VALUE-1;
    Node pre;
    HashSet<Integer> adj=new HashSet<>();
    Node(int id){
        this.id=id;        
    }//end of constructor
    public void addEdge(int d){
        adj.add(d);
    }//end of addEdge
}//end of Node
///////////////////////////////////////////////
class Graph{
    ArrayList<Node> vertices=new ArrayList<>();
    Graph(int v){
        for(int i=0;i<v;i++){
            vertices.add(new Node(i));
        }//end of for i
    }//end of constructor
    public void addEdge(int s,int d){
        vertices.get(s).addEdge(d);
    }//e of addEdge    
}//end of Graph class
