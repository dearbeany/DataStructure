package queue;

public class ArrayQueueTest {
	public static ArrayQueue<String> queue = new ArrayQueue<>();

	public static void main(String[] args) {

		queue.enQueue("A");
		queue.enQueue("B");
		queue.enQueue("C");
		queue.printList(); // A B C

//		System.out.println(queue.size()); // 3, 현재 들어간 원소 개수 
//		System.out.println(queue.capacity); // 50, 사용자가 지정하지 않아 자동 초기화된 최대 용량  

//		System.out.println(queue.front()); // A
//		System.out.println(queue.rear()); // C

		System.out.println(queue.deQueue()); // A 삭제
		queue.printList(); // B C

		queue.clear();
		queue.printList(); // empty

	}

	// 출력해야할 인덱스는 front+1 부터 rear까지이다.
	// printList() 와 동일한 결과를 출력한다.
	public static void showAllElements() {
		System.out.print("<Front> ");
		for (int i = queue.frontIdx + 1; i < queue.rearIdx + 1; i++) {
			System.out.print(queue.elementAt(i) + " ");
		}
		System.out.print("<Rear>\n");
	}

}
