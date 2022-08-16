package queue;

public class ArrayQueueTest {
	public static ArrayQueue<String> queue = new ArrayQueue<>();

	public static void main(String[] args) {

		queue.enQueue("A");
		queue.enQueue("B");
		queue.enQueue("C");

//		System.out.println(queue.size()); // 3, 현재 들어간 원소 개수 
//		System.out.println(queue.capacity); // 50, 사용자가 지정하지 않아 자동 초기화된 최대 용량  

		showAllElements(); // A B C
		System.out.println(queue.front()); // A
		System.out.println(queue.rear()); // C
//		System.out.println(queue.deQueue()); // A 삭제 
		showAllElements(); // B C 출력 되어야 함 ....

		queue.clear();
//		showAllElements();
	}

	public static void showAllElements() {
		System.out.print("<Front> ");
		for (int i = 0; i < queue.size(); i++) {
			System.out.print(queue.elementAt(i) + " ");
		}
		System.out.print("<Rear>\n");
	}
}
