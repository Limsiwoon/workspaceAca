package j01_basic;

import java.util.PriorityQueue;

public class Ex02_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(30);
		pq.add(80);
		pq.add(20);

		for (Integer o : pq) {
			System.out.println(o);
		}
		System.out.println("원소 삭제");
		while (!pq.isEmpty()) {
			System.out.println(pq.remove());
		}

	}

}
