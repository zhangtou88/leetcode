package ex4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
	public List<String> wordBreak(String s, Set<String> dict) {
		HashMap<Integer,ArrayList<Integer>> mark=new HashMap<Integer,ArrayList<Integer>>();
		for(int i=0;i<s.length();i++){
			mark.put(i,new ArrayList<Integer>());
		}
		for(int stop=s.length();stop>=0;stop--){
			if(stop<s.length()&&mark.get(stop).isEmpty())continue;
			for(int start=stop-1;start>=0;start--){
				if(dict.contains(s.substring(start, stop)))
					mark.get(start).add(stop);
			}
		}
		ArrayList<String> result=new ArrayList<String>();
		collect(mark,0,s,"",result);
		return result;
	}
	public void collect(HashMap<Integer,ArrayList<Integer>> mark,int ind,String s,String path,ArrayList<String> result){
		for(int stop:mark.get(ind)){
			String sub=s.substring(ind, stop);
			String newPath=path+(ind==0?sub:" "+sub);
			if(stop==s.length())
				result.add(newPath);
			else
				collect(mark,stop,s,newPath,result);
		}
	}
	public static void main(String[] args){
		String[] dic={"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		String sentence="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		ArrayList<String> cdic=new ArrayList<String>();
		for(int i=0;i<dic.length;i++){
			cdic.add(dic[i]);
		}
		List<String> result=new WordBreakII().wordBreak(sentence,new HashSet<String>(cdic) );
		for(String s:result){
			System.out.println(s);
		}
	}
}
