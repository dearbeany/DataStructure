package queue;

public class CicularQueueTest {

	public static CircularQueue<String> circularQueue = new CircularQueue<>(4);

	public static void main(String[] args) {
		circularQueue.enQueue("1");
		circularQueue.enQueue("2");
		circularQueue.enQueue("3");
		showAllElements(); // 1 2 3

		circularQueue.enQueue("4");
		showAllElements(); // 1 2 3 4

		circularQueue.enQueue("5"); // full 이므로 더이상 삽입 불가
		circularQueue.deQueue(); // 큐는 FIFO 이므로 1 삭제
		circularQueue.deQueue(); // 큐는 FIFO 이므로 2 삭제
		showAllElements(); // 3 4

		circularQueue.enQueue("5");
		showAllElements(); // 3 4 5

		System.out.println(circularQueue.front()); // 3
		System.out.println(circularQueue.rear()); // 5

		circularQueue.clear();
		showAllElements();

	}

	public static void showAllElements() {
		System.out.print("<Front> ");
		for (int i = 0; i < circularQueue.size(); i++) {
			System.out.print(circularQueue.elementAt(i) + " ");
		}
		System.out.print("<Rear>\n");
	}
}
