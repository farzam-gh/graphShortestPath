import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t>0){
            int n=sc.nextInt();
            int m=sc.nextInt();
            Graph g=new Graph(n);
            for(int i=0;i<m;i++){
                g.addEdge(sc.nextInt(),sc.nextInt());
            }//end of for i
            int s=sc.nextInt();
            bfs(g,s);            
            for(Node x:g.vertices){
                if(x.id!=0){
                if(x.pl==Integer.MAX_VALUE-1)
                    System.out.print("-1 ");
                else if(x.pl!=0)
                    System.out.print(x.pl+" ");
                }//end if
            }//end of ex-for                
            System.out.println("");
            t--;
        }//edn of while
       
    }//end of main
    public static void bfs(Graph g,int s){
        Node sn=g.vertices.get(s);
        LinkedList<Node> q=new LinkedList<>();
        //TreeSet<Node> q=new TreeSet<Node>(Comparator.comparing(x->x.pl));
        sn.pl=0;
        sn.adj.forEach(
            x->{
            x.pl=6;
            q.add(x);
        });
        q.remove(g.vertices.get(s));
        while(q.size()>0){
        Node min=findMin(q);
        min.adj.forEach(x->{            
            if(x.pl>min.pl+6){
                x.pl=min.pl+6; 
            q.add(x);
            }//end if
               });
        q.remove(min);
        }//end of while
    }//end of bfs
    public static Node findMin(LinkedList<Node> q){        
            Collections.sort(q,new Comparator<Node>(){
                public int compare(Node n1,Node n2){
                    return n1.pl-n2.pl;
                }//end of compare
            });         
        return q.get(0);
    }//end of findMin
    }//end of solution

    class Graph{
       ArrayList<Node> vertices=new ArrayList<>();
        public Graph(int n){
            vertices.add(new Node(0));
            for(int i=1;i<=n;i++)
                vertices.add(new Node(i));
        }//end of constructor
       public void addEdge(int s,int d){
           vertices.get(s).addEdge(vertices.get(d)); 
           vertices.get(d).addEdge(vertices.get(s));
       } //edn of addEdge        
    }//end of Graph
    
    class Node{
        int id; 
        int pl=Integer.MAX_VALUE-1;
        Node pre=null;
        Set<Node> adj=new  HashSet<Node>();
        public Node(int id){
            this.id=id;
        }//end of constructro
        public void addEdge(Node d){
            adj.add(d);
        }//end o addEdge        
    }//end of node
