package queue;

import java.util.Arrays;

/*
 * 선형큐의 문제점 <잘못된 포화상태 인식>
 * 1. 원소의 위치가 점진적으로 왼쪽에서 오른쪽으로 이동한다.
 * 2. 즉, enQueue 와 deQueue를 거치며 배열의 앞공간은 비어있음에도 불구하고,
 * 	  rear = n-1인 상태(포화상태)로 인식하여 더 이상의 삽입이 불가능하다.
 * 3. 따라서, 선형큐에 존재하는 원소만을 출력하려고 한다면 frontIdx+1부터 rearIdx까지로 범위를 지정해주어야 한다 
 *  
 */
public class ArrayQueue<E> implements Queue<E> {

	private static final int DEAFAULT_CAPACITY = 50;

	public int capacity; // 큐에 담을 수 있는 최대 용량 즉, 현재 큐의 크기
	public int frontIdx; // 마지막으로 삭제된 원소의 인덱스
	public int rearIdx; // 마지막으로 저장된 원소의 인덱스
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
			this.rearIdx++;
			this.elements[rearIdx] = anElement;
			return true;
		}

	}

	@Override
	public E deQueue() {
		// 스택이 비워져있는지 확인한다
		// 스택이 isEmpty하면 삭제할 원소가 없다
		/*
		 * E frontElement = null;
		 * 
		 * if (!isEmpty()) { frontElement = this.elements[++frontIdx];
		 * this.elements[this.frontIdx] = null; // 증가된 frontIdx에 있는 원소를 삭제 } return
		 * frontElement;
		 */

		if (isEmpty()) {
			return null;
		} else {
			this.frontIdx++;
			E frontElement = this.elements[frontIdx];
			return frontElement;
		}

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
		for (int i = this.frontIdx; i < this.rearIdx + 1; i++) {
			if (i == order) {
				return this.elements[i];
			}
		}
		return null;
	}

	// 출력해야할 인덱스는 front+1 부터 rear까지이다
	// Arrays.copyOf(원본배열, 복사길이)
	// -> 0번째부터 두번째 파라미터인 복사길이만큼 복사한다. 즉, 시작인덱스가 0으로 고정되어있다.
	// -> 선형큐의 경우 0부터 복사한다면 deQueue 했을 때 null값이 출력될 수 있다.
	// 이를 방지 하기 위하여 시작인덱스를 지정해줄 수 있는 메소드를 사용한다.

	// Arrays.copyOfRange(원본 배열, 시작인덱스, 마지막인덱스+1)

	public void printList() {
		System.out.print("<Front> ");
//		System.out.println(Arrays.toString(Arrays.copyOf(elements, size())));
		System.out.print(Arrays.toString(Arrays.copyOfRange(elements, frontIdx + 1, rearIdx + 1)));
		System.out.print(" <Rear>\n");
	}

}
