package com.farzam.java;
import java.io.*;
import java.util.*;

public class WeightedGraph {
    public static Set<Node> visited=new HashSet<Node>();
    public static ArrayList<Node> unsettled=new ArrayList<Node>();
    static int ans=0;
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the nomber of Nodes: ");
        Graph g=new Graph(sc.nextInt());
        System.out.println("Enter the nomber of Edges: ");
        int m=sc.nextInt();
        System.out.println("Enter the Edges (start dest weight): ");
        for(int i=0;i<m;i++){
            g.addEdge(g.vertices.get(sc.nextInt()),g.vertices.get(sc.nextInt()),sc.nextInt());
        }//end of for i
        System.out.println("Enter the start Node: ");
        Node s=g.vertices.get(sc.nextInt());
        dijkstra(g,s);
    }//end of main
    public static void dijkstra(Graph g,Node s){
        for(Node x:g.vertices)            
            unsettled.add(x);
        if(!Graph.similar.isEmpty())
            for(Node x:Graph.similar.keySet()){
                x.edges.addAll(Graph.similar.get(x).edges);
                unsettled.remove(Graph.similar.get(x));                                
            }//end of ex-for      
            Graph.similar.clear();  
        Node tempNode;
        Edge tempEdge;
        int tempInt=0;  
        int ans=0;
        visited.add(s);
        unsettled.remove(s);
        TreeSet<Edge> q=new TreeSet<Edge>(new Comparator<Edge>(){
           public int compare(Edge e1,Edge e2){
               return e1.w-e2.w;
           }
           });
        tempNode=s;
        while(unsettled.size()>0){
            q.addAll(tempNode.edges);
            tempEdge=q.first();
            //System.out.println(visited);
           // System.out.println(tempEdge);            
            while(visited.contains(tempEdge.des) && q.size()>0){
                 q.remove(tempEdge);
                if(q.size()>0)
                 tempEdge=q.first();    
            }//end of while
            if(q.size()==0){
            	System.out.println("Minimum path length for Maximu possible visited Nodes is: "+ans);
                System.out.println(ans);
                return;
            }//end if            
            tempNode=tempEdge.des;
            ans+=tempEdge.w;
            visited.add(tempNode);
            unsettled.remove(tempNode); 
            q.remove(tempEdge);
        }//end of while  
        System.out.println("Minimum path length for Maximu possible visited Nodes is: "+ans);
    }//end of dijkstra
}//end of Solution

class Graph{
    ArrayList<Node> vertices=new ArrayList<Node>();  
    public static HashMap<Node,Node> similar=new HashMap<Node,Node>();
    Node temp;
    Graph(int n){
        vertices.add(new Node(0));
        for(int i=1;i<=n;i++){
            temp=new Node(i);
            vertices.add(temp);          
        }//end of for i
    }//end of constructor
    void addEdge(Node s,Node d,int w){
        vertices.get(s.id).addEdge(d,w);
        vertices.get(d.id).addEdge(s,w);
        if(w==0 && !similar.containsKey(s)&& !similar.containsKey(d))
            similar.put(s,d);
    }//end of addEdge
}//end of Graph

class Node{
    int id;
    int pl=Integer.MAX_VALUE-1;
    Node pre=null;
    TreeSet<Edge> edges=new TreeSet<Edge>(new Comparator<Edge>(){
        public int compare(Edge e1,Edge e2){
           return e1.w-e2.w;
        }//end of compare
    });
    Node(int id){
        this.id=id;        
    }//end of constructor
    void addEdge(Node d,Integer w){
        Edge temp=new Edge(d,w);
       if(!edges.add(temp)){
           if(edges.ceiling(temp).w>w){
               edges.remove(edges.ceiling(temp));
               edges.add(temp);
           }//end if
       }//end if           
    }//end of addEdge  
    public String toString(){
        return ""+this.id;
    }//end of toString
}//end of Node

class Edge{
    Node des;
    int w;
    Edge(Node d,int w){
        this.des=d;
        this.w=w;
    }//end of constructor
    public String toString(){
        return des.id+":"+w;
    }//end of toString
}//end of Edge

