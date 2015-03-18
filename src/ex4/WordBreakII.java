package ex4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
	public List<String> wordBreak(String s, Set<String> dict) {
		// if(s.length()<1){
		// return null;
		// }
		int stringLength = s.length();
		String currentWord = "";
		int[] spaceLocation = new int[stringLength];
		for (int i = 0; i < stringLength ; i++) {
			spaceLocation[i] = 0;
		}
		int currentWordInitial = 0;
		int currentIndex = 1;// currentIndex>currentWordInitial
		List<String> completeList = new ArrayList<String>();
		while (true) {
			if (currentWordInitial > stringLength - 1) {
				break;
			}
			currentWord = s.substring(currentWordInitial, currentIndex);

			if (currentIndex < stringLength) {//句子未结束
				if(dict.contains(currentWord)){
					spaceLocation[currentIndex - 1] = 1;
					currentWordInitial = currentIndex;
				}
				currentIndex++;
				continue;
			} else {// 如果句子已经结束
				int previousInternal = -1;
				if(dict.contains(currentWord)){
					String completeString = "";
				
					for (int i = 0; i < stringLength; i++) {
						completeString += s.charAt(i);
						if (i < stringLength - 1 && spaceLocation[i] == 1) {
							completeString += " ";
							if (i < currentWordInitial - 1) {
								previousInternal = i;
							} else {
								spaceLocation[i] = 0;
							}
						}
					}
					completeList.add(completeString);// 将完成的句子添加空格后加入列表
				}else{
					if(currentWordInitial-1>=0){
						spaceLocation[currentWordInitial-1]=0;
					}
					for(int i=stringLength-1;i>=0;i--){
						if(spaceLocation[i]==1){
							previousInternal=i;
							break;
						}
					}
				}
				if (currentWordInitial == 0) {// 整个句子已被遍历完毕，无需回溯
					break;
				}
				currentIndex = currentWordInitial + 1;
				currentWordInitial = previousInternal+1;// 回溯

			}
		}
		return completeList;
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
