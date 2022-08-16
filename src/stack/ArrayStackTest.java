package stack;

public class ArrayStackTest {
	public static ArrayStack<String> stack = new ArrayStack<>();
	public static ArrayStack<String> resizeStack = new ArrayStack<>(4);

	public static void main(String[] args) {

		// E
		// D
		// C
		// B
		// A
		// 스택은 Last In First Out 구조이기에
		// 가장 먼저 삽입하면 가장 맨 밑에 쌓임
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		showAllElements(stack);

		// 가장 나중에 삽입된 E가 pop된다
		String poppedElement = stack.pop();
		System.out.println("Pop한 원소: " + poppedElement);
		showAllElements(stack);

		// 가장 나중에 삽입된 E가 peek된다
		String peekElement = stack.peek();
		System.out.println("Peek한 원소: " + peekElement);

		stack.clear(); // stack을 비운다
		showAllElements(stack);

		System.out.println("---------------------------");
		resizeStack.push("a");
		resizeStack.push("b");
		resizeStack.push("c");
		System.out.println(resizeStack.size()); // 3, 현재 스택에 담긴 원소의 개수
		resizeStack.push("d");
		System.out.println(resizeStack.capacity); // 4, 처음 스택 생성 시 사용자가 지정한 스택의 크기
		resizeStack.push("e"); // 오버플로우, resize 발생
		System.out.println(resizeStack.size()); // 5, 현재 스택에 담긴 원소의 개수
		System.out.println(resizeStack.capacity); // 8, 처음 크기의 2배로 resize
		showAllElements(resizeStack);

	}

	// Top부터 Bottom까지 스택의 모습을 출력
	// 스택에 쌓인 모습 그대로 출력하기 위해 Top부터 위->아래 방향으로 출력한다(LIFO)
	public static void showAllElements(ArrayStack<String> stack) {
		System.out.println("<Top>");
		for (int i = stack.size() - 1; i >= 0; i--) {
			System.out.println(stack.elementAt(i));
		}
		System.out.println("<Bottom>");
	}

}
