package com.farzam.java;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class DijkstraSolution {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of Nodes: ");
            int n = in.nextInt();
            Graph g=new Graph(n);
            System.out.println("Enter the number of Edges: ");
            int m = in.nextInt();
            System.out.println("Enter the Edges (start end weight): ");
            for(int a1 = 0; a1 < m; a1++){
                int x = in.nextInt();
                int y = in.nextInt();
                int r = in.nextInt();
                g.addEdge(g.vertices.get(x-1),g.vertices.get(y-1),r);
            }//end of for a1
            System.out.println("Enter the Start Node: ");
            int s = in.nextInt();
            for(Node x:g.vertices)
                x.sort();
            dijkstra(g,n,s);
            System.out.println("start   "+"dest   "+"cost");
            for(int i=1;i<=n;i++)
                if(i!=s) {
                	String ans=g.vertices.get(i-1).pl!=Integer.MAX_VALUE-1?g.vertices.get(i-1).pl+" ":"-1 ";
                System.out.println(s+"        "+i+"         "+ans);
                }else
                	System.out.println("");
      
    }//end of main
    public static void dijkstra(Graph g,int n,int s){
        ArrayList<Node> unsettled=new ArrayList<>(g.vertices);
        Node current=g.vertices.get(s-1);        
        current.pl=0;
        current.pre=current;
        ArrayDeque<Node> ad=new ArrayDeque<>();  
         //System.out.println("current.id : current.pl  current.pre");
      while(unsettled.size()>0){
          current=unsettled.get(min(unsettled));          
          for(Node x:current.adj)
            ad.offer(x);
          unsettled.remove(current);
          for(Node x:ad){  
             //  System.out.println("Before: "+current.id+" : "+current.pl+" "+current.pre.id+" ");
              if(current.pl+x.pl<g.vertices.get(x.id-1).pl){
                g.vertices.get(x.id-1).pl=x.pl+current.pl;
                g.vertices.get(x.id-1).pre=current;
             // System.out.println("After "+current.id+" : "+current.pl+" "+current.pre.id+" ");
          }//end if
          }//end if
          ad.clear();         
      }//end of while
        
    }//end of dijkstra
    public static int min(ArrayList<Node> unsettled){
        Collections.sort(unsettled, new Comparator<Node>(){
            public int compare(Node n1,Node n2){
                return n1.pl-n2.pl;
            }//end of compare            
        });
      return 0;  
    }//end of min
}//end of Solution
class Graph{
    ArrayList<Node> vertices=new ArrayList<>();
    Graph(int v){
        for(int i=1;i<=v;i++)
            vertices.add(new Node(i));
    }//end of constructor
    public void addEdge(Node s,Node d,int c){       
        s.addEdge(d,c);
        d.addEdge(s,c);
    }//end of addEdge
    
}//end of Graph
class Node{
    int id;
    boolean temp=true;
    ArrayList<Node> adjl=new ArrayList<>();
    Set<Node> adj;
    int pl=Integer.MAX_VALUE-1;
    Node pre=null;    
    Node(int id){
        this.id=id;
    }//end of constructor
    Node(int id,int c){
        this.id=id;
        this.pl=c;
    }//end of constructor
    public void addEdge(Node d,int c){ 
        if(this.pre==null || pre.id!=d.id){
           this.pre=new Node(d.id,c);
           this.adjl.add(pre);            
        } else if(this.pre.pl>c){
                this.pre=d;
                this.pre.pl=c;
        }//end of else
    
    } //end of addEdge
    public void sort(){
        Collections.sort(adjl,new Comparator<Node>(){
            public int compare(Node  n1,Node n2){
                return n1.pl-n2.pl;
            }//end of compare            
        });//end of comparator
        adj=new HashSet<>(adjl);
    }//end of sort
    public String toString(){
        return this.id+" ";
    }
}//end  of Node
