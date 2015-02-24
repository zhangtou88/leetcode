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
			if (result.equals("0")) // ����һ�ξ���0���������Ժ���0���������
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

	public int findOptimum(ArrayList<Integer> num, int currentDigitNum) { // �ݹ麯���������ѡֵ�Լ���ǰλ������ʼΪ1��
		if (num.size() == 1) {
			return num.get(0);
		}
		if (currentDigitNum > 1) {// ������һλ������
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
				for (int i = 0; i < num.size(); i++) { // �������ֵ���Ϸ�ʽ������¼
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
		for (int i = 0; i < num.size(); i++) {// �ҵ�ָ��λ������ֵ
			ceiling = ((num.get(i) + "").charAt(currentDigitNum - 1) > ceiling) ? (num
					.get(i) + "").charAt(currentDigitNum - 1) : ceiling;
		}
		ArrayList<Integer> newNum = new ArrayList<Integer>();
		for (int i = 0; i < num.size(); i++) {// ɾ��ָ��λ�ñ�ceilingС��ֵ
			if ((num.get(i) + "").charAt(currentDigitNum - 1) == ceiling)
				newNum.add(num.get(i));
		}
		currentDigitNum++;
		return findOptimum(newNum, currentDigitNum);
	}

	
}
