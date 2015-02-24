package ex2;

public class MajorityElement {
	/*
	 * version with recursion
	 */
//	public int majorityElement(int[] num) {
//		
//		quicksort(num, 0, num.length - 1);
//		int Major = num[0];
//		int present = num[0];
//		int presentExistTime = 1;
//		int baseline = (int) Math.floor(num.length / 2);
//		for (int i = 1; i < num.length; i++) {
//			if (num[i] == present) {
//				presentExistTime++;
//			} else if (presentExistTime <= baseline) {
//				present = num[i];
//				presentExistTime = 1;
//			}
//			if (presentExistTime > baseline) {
//				Major = present;
//				break;
//			}
//		}
//		return Major;
//	}
//
//	public void quicksort(int[] num, int left, int right) {
//		if (right <= left)
//			return;
//		int mid = divideArray(num, left, right);
//		quicksort(num, left, mid - 1);
//		quicksort(num, mid + 1, right);
//	}
//
//	public int divideArray(int[] num, int left, int right) {
//		int sign = num[left];
//		int signLocation = left;
//		for (int i = left + 1; i < right+1 ; i++) {
//			if (num[i] < sign) {
//				signLocation++;
//				if (i > signLocation) {
//					int temp = num[i];
//					num[i] = num[signLocation];
//					num[signLocation] = temp;
//				}
//			}
//		}
//		num[left] = num[signLocation];
//		num[signLocation] = sign;
//		return signLocation;
//	}
	/*
	 * version with moore voting algorithm
	 */
	public int majorityElement(int[] num) {
		int majorityindex=0;
		int count=1;
		for(int i=1;i<num.length;i++){
			count=num[i]==num[majorityindex] ? (count+1):(count-1);
			if(count==0){
				majorityindex=i;
				count=1;
			}
		}
		return num[majorityindex];
	}

	public static void main(String[] args) {
//		int[] test=
//		System.out.println(new MajorityElement().majorityElement(test));
	}
}
