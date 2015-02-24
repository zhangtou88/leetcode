package ex1;

import java.util.ArrayList;

public class LargestNumber {
	public static void main(String[] args) {
		int[] num = { 9051, 5526, 2264, 5041, 1630, 5906, 6787, 8382, 4662,
				4532, 6804, 4710, 4542, 2116, 7219, 8701, 8308, 957, 8775,
				4822, 396, 8995, 8597, 2304, 8902, 830, 8591, 5828, 9642, 7100,
				3976, 5565, 5490, 1613, 5731, 8052, 8985, 2623, 6325, 3723,
				5224, 8274, 4787, 6310, 3393, 78, 3288, 7584, 7440, 5752, 351,
				4555, 7265, 9959, 3866, 9854, 2709, 5817, 7272, 43, 1014, 7527,
				3946, 4289, 1272, 5213, 710, 1603, 2436, 8823, 5228, 2581, 771,
				3700, 2109, 5638, 3402, 3910, 871, 5441, 6861, 9556, 1089,
				4088, 2788, 9632, 6822, 6145, 5137, 236, 683, 2869, 9525, 8161,
				8374, 2439, 6028, 7813, 6406, 7519 };
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
			optimum = findOptimum(nums, 1);
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

	public int findOptimum(ArrayList<Integer> num, int currentDigitNum) { // 递归函数，传入待选值以及当前位数（初始为1）
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
		return findOptimum(newNum, currentDigitNum);
	}

	
}
