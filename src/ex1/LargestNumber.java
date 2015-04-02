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

	public int findOptimum(ArrayList<Integer> num) { // �����ѡֵ,ϣ����ȡ��ֵ���ֵ����������֣�e.g��978��9680֮��223��2211֮�󣬣�
		int currentDigitNum=1;
		while (true) {
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
			num = newNum;
		}
	}

}
