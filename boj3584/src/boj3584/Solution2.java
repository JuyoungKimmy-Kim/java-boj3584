package boj3584;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {

	static List<Integer>[] adj;
	static int[] parent, level;
	static boolean[] rootCheck;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		int T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N=Integer.parseInt(br.readLine());
			
			parent=new int[N+1];
			level=new int[N+1];
			adj=new ArrayList[N+1];
			
			for (int j=1; j<N+1; j++)
				adj[j]=new ArrayList<>();
			
			boolean[] rootCheck=new boolean[N+1];
			Arrays.fill(rootCheck, true);
			
			for (int i=0; i<N-1; i++) {
				
				st=new StringTokenizer(br.readLine(), " ");
				int A=Integer.parseInt(st.nextToken());
				int B=Integer.parseInt(st.nextToken());
				
				adj[A].add(B);
				adj[B].add(A);
				rootCheck[B]=false;
			}
			
			int rootIdx=0;
			for (int k=1; k<N-1; k++) { 
				if (rootCheck[k]) {
					rootIdx=k;
					break;
				}
			}
			
			//setTree(rootIdx, 0);
			setTree(rootIdx, 1,0);
			
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			System.out.println(LCA(a,b));
		}
		

	}
	
	
	/*
	static void setTree(int node, int pnode) {
		parent[node]=pnode;
		level[node]=level[pnode]+1;
		
		for (int child : adj[node]) {
			if (child != pnode) {
				setTree(child, node);
			}
		}
	}
	*/
	
	static void setTree (int cur, int h, int pa) {
		level[cur]=h;
		parent[cur]=pa;
		
		for (int nxt: adj[cur]) {
			if (nxt !=pa) {
				setTree (nxt, h+1, cur);
			}
		}
	}
	
	static int LCA (int a, int b) {
		if (level[a]<level[b]) {
			int tmp=a;
			a=b;
			b=tmp;
		}
		
		while (level[a]!=level[b]) 
			a=parent[a];
		
		while (a!=b) {
			a=parent[a];
			b=parent[b];
		}
		
		return a;
	}
}
