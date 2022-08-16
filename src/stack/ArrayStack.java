package stack;

public class ArrayStack<E> implements Stack<E> {

	// 사용자가 지정하지 않을 경우에 사용될 초기의 스택 크기
	private static final int DEAFAULT_CAPACITY = 50;

	public int capacity; // 현재 스택의 최대 용량
	private int top; // 현재 스택의 top원소(즉, 마지막으로 삽입합 원소)의 배열에서의 위치
	private E[] elements; // 스택의 원소들이 저장되는 배열

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		this.capacity = DEAFAULT_CAPACITY;
		this.top = -1;
		this.elements = (E[]) new Object[this.capacity];
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int givenCapacity) {
		this.capacity = givenCapacity;
		this.top = -1;
		this.elements = (E[]) new Object[this.capacity];
	}

	// 현재 스택의 사이즈를 반환한다(원소의 개수가 몇 개 들어갔는지?)
	// top == size - 1로 맨 끝원소를 가리킨다
	@Override
	public int size() {
		return this.top + 1;
	}

	// 스택이 비어있는지 확인한다
	@Override
	public boolean isEmpty() {
//		return size() == 0;
		return top == -1;
	}

	// 스택이 꽉 차있는지 확인한다
	@Override
	public boolean isFull() {
		return top == this.capacity - 1;
	}

	// 스택에 원소를 넣는다
	@Override
	public boolean push(E anElement) {
		// 스택이 차있는지 확인한다
		// 스택이 isFull하면 삽입할 수 없음
		if (isFull()) {
			this.resize();
		}
		this.top++;
		this.elements[top] = anElement;
		return true;

	}

	// 스택에서 마지막 원소를 삭제하고 반환한다
	@Override
	public E pop() {
		// 스택이 비어있는지 확인한다
		// 스택이 isEmpty라면 삭제할 수 없음
		if (isEmpty()) {
			return null;
		} else {
			E topElement = this.elements[top];
			this.top--;
			return topElement;

		}
	}

	// 스택에 마지막 원소를 반환한다
	@Override
	public E peek() {
		// 스택이 비어있는지 확인한다
		// 스택이 isEmpty라면 반환할 마지막 원소는 없다
		if (isEmpty()) {
			return null;
		} else {
			E topElement = this.elements[top];
			return topElement;
		}
	}

	// 배열로 만든 스택의 원소들을 모두 비워준다
	@Override
	public void clear() {
		// top이 -1을 가리킬 때까지
		// 스택은 모두 null로 채운다
		// top이 -1이 될 때 루프 탈출
		while (this.top != -1) {
			this.elements[top] = null;
			this.top--;
		}
	}

	// push할 때, 스택이 isFull할 경우를 고려하여
	// 스택의 사이즈를 2배로 늘려준다
	@SuppressWarnings("unchecked")
	private void resize() {
		this.capacity *= 2;
		E[] oldElements = this.elements; // 기존배열은 복사해서 소유권만 옮겨놈

		this.elements = (E[]) new Object[capacity]; // 2배 늘어난 배열

		// 기존 배열에 있는 원소 값들을 새로운 배열로 복사해준다
		for (int i = 0; i < this.size(); i++) {
			this.elements[i] = oldElements[i];
		}

//		this.elements = Arrays.copyOf(this.elements, this.capacity);
	}

	// order번째 원소를 반환한다
	public E elementAt(int order) {
		for (int i = 0; i < this.size(); i++) {
			if (i == order) {
				return this.elements[i];
			}
		}
		return null;
	}

}
