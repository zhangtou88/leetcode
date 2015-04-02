package ex1;

import java.util.ArrayList;

public class LargestNumber {
	public static void main(String[] args) {
		int[] num = { 957, 8775,
				4822, 396, 8995, 8597, 2304 };
		System.out.println(new LargestNumber().largestNumber(num));
	}

	public String largestNumber(int[] num) {
		String result = "";
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < num.length; i++) {
			nums.add(num[i]);
		}
		int optimum;
		for (int i = 0; i < num.length; i++) {
			optimum = findOptimum(nums);
			result += optimum;
			// result+=",";
			if (result.equals("0")) // 若第一次就是0，很明显以后都是0，无需继续
				break;
			for (int j = 0; j < nums.size(); j++) {
				if (nums.get(j) == optimum) {
					nums.remove(j);
					break;
				}
			}
		}
		return result;
	}

	public int findOptimum(ArrayList<Integer> num) { // 传入待选值,希望获取数值中字典序最靠后的数字（e.g。978在9680之后，223在2211之后，）
		int currentDigitNum=1;
		while (true) {
			if (num.size() == 1) {
				return num.get(0);
			}
			if (currentDigitNum > 1) {// 处理少一位的数字
				int lackNum = -1;
				for (int i = 0; i < num.size(); i++) {
					if ((num.get(i) + "").length() == currentDigitNum - 1) {
						lackNum = num.get(i);
						break;
					}
				}
				if (lackNum != -1) {
					ArrayList<Integer> del = new ArrayList<Integer>();
					del.add(lackNum);
					num.removeAll(del);
					boolean consistLarger = false;
					for (int i = 0; i < num.size(); i++) { // 两个数字的组合方式，见记录
						int comb1 = Integer.parseInt(lackNum + "" + num.get(i));
						int comb2 = Integer.parseInt(num.get(i) + "" + lackNum);
						if (comb1 < comb2) {
							consistLarger = true;
							break;
						}
					}
					if (!consistLarger)
						return lackNum;
				}
			}
			char ceiling = '0';
			for (int i = 0; i < num.size(); i++) {// 找到指定位置最大的值
				ceiling = ((num.get(i) + "").charAt(currentDigitNum - 1) > ceiling) ? (num
						.get(i) + "").charAt(currentDigitNum - 1) : ceiling;
			}
			ArrayList<Integer> newNum = new ArrayList<Integer>();
			for (int i = 0; i < num.size(); i++) {// 删除指定位置比ceiling小的值
				if ((num.get(i) + "").charAt(currentDigitNum - 1) == ceiling)
					newNum.add(num.get(i));
			}
			currentDigitNum++;
			num = newNum;
		}
	}

}
