package queue;

/*
 * 선형큐의 문제점 <잘못된 포화상태 인식>
 * 1. 원소의 위치가 점진적으로 왼쪽에서 오른쪽으로 이동한다.
 * 2. 즉, enQueue 와 deQueue를 거치며 배열의 앞공간은 비어있음에도 불구하고,
 * 	  rear = n-1인 상태(포화상태)로 인식하여 더 이상의 삽입이 불가능하다.
 *  
 */
public class ArrayQueue<E> implements Queue<E> {

	private static final int DEAFAULT_CAPACITY = 50;

	public int capacity; // 큐에 담을 수 있는 최대 용량 즉, 현재 큐의 크기
	private int frontIdx; // 마지막으로 삭제된 원소의 인덱스
	private int rearIdx; // 마지막으로 저장된 원소의 인덱스
	private E[] elements; // 스택을 구현하는 배열

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		this.capacity = DEAFAULT_CAPACITY;
		this.elements = (E[]) new Object[capacity];
		this.frontIdx = -1;
		this.rearIdx = -1;
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(int givenCapacity) {
		this.capacity = givenCapacity;
		this.elements = (E[]) new Object[capacity];
		this.frontIdx = -1;
		this.rearIdx = -1;
	}

	// 현재 큐에 담겨있는 원소들의 개수, 큐의 사이즈
	@Override
	public int size() {
		return this.rearIdx - this.frontIdx;
	}

	@Override
	public boolean isEmpty() {
		if (this.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
//		return (this.rearIdx == this.size() - 1); // 이걸로 실행하면 안 됨 size랑 capacity는 다름! 
		return (this.rearIdx == this.capacity - 1); // 최대 용량까지 꽉 채워져있으면
	}

	@Override
	public E front() {
		// 실제 맨 앞 원소가 있는 위치는 frontIdx+1
		// 처음 비어있을 때 frontIdx 는 -1이기 때문이다
		return this.elements[++frontIdx];
	}

	@Override
	public E rear() {
		return this.elements[rearIdx];
	}

	@Override
	public boolean enQueue(E anElement) {
		// 스택이 꽉 차있는지 확인한다
		// 스택이 isFull 하면 원소를 삽입할 수 없다
		if (isFull()) {
			return false;
		} else {
//			this.rearIdx++;
			this.elements[++rearIdx] = anElement;
			return true;
		}

	}

	@Override
	public E deQueue() {
		// 스택이 비워져있는지 확인한다
		// 스택이 isEmpty하면 삭제할 원소가 없다
		E frontElement = null;

		if (!isEmpty()) {
			this.frontIdx++;
			frontElement = this.elements[this.frontIdx];
			this.elements[this.frontIdx] = null; // 증가된 frontIdx에 있는 원소를 삭제
		}
		return frontElement;
	}

	@Override
	public void clear() {
		this.frontIdx = -1;
		this.rearIdx = -1;
		for (int i = 0; i < this.size(); i++) {
			this.elements[i] = null; // 해도 되고 안 해도 됨 어차피 덮어쓸 거니까
		}

	}

	// 수정필요
	@Override
	public E elementAt(int order) {
		for (int i = 0; i < this.size(); i++) {
			if (i == order) {
				return this.elements[i];
			}
		}
		return null;
	}

}
