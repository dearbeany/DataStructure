package queue;

/*
 * 핵심 아이디어
 * 1. 원형큐의 경우 공백/포화를 구분하기 위해 front가 있는 자리는 사용하지 않고 항상 빈자리로 둔다.
 * 2. 이전(-1)과 달리 초기 공백상태(0)를 front = rear = 0으로 설정한다. 
 * 3. front와 rear의 위치가 배열의 마지막 인덱스인 n-1을 가리킨 후, 
 * 	  그 다음은 논리적 순환을 이뤄 배열의 처음 인덱스인 0으로 이동해야한다. (mod 이용) 
 */
public class CircularQueue<E> implements Queue<E> {

	private static final int DEAFAULT_CAPACITY = 50;

	/*
	 * 원형큐의 경우, 저장 가능한 원소의 최대 개수(capacity)는 최대길이(maxLength) 보다 하나 작다. 예를 들어
	 * maxLength가 8일 때, 원소는 7개까지 넣을 수 있으며 원소가 7개 삽입된 경우 Full로 본다.
	 */

	public int maxLength; // 원형큐에 담을 수 있는 최대 용량(크기) capacity + 1
	private int frontIdx;
	private int rearIdx;
	private E[] elements;

	@SuppressWarnings("unchecked")
	public CircularQueue() {
		this.maxLength = DEAFAULT_CAPACITY;
		this.elements = (E[]) new Object[this.maxLength];
		this.frontIdx = 0;
		this.rearIdx = 0;
	}

	@SuppressWarnings("unchecked")
	public CircularQueue(int givenCapacity) {
		this.maxLength = givenCapacity + 1;
		this.elements = (E[]) new Object[this.maxLength];
		this.frontIdx = 0;
		this.rearIdx = 0;
	}

	// 현재 큐가 가진 원소의 개수를 알려준다
	@Override
	public int size() {
		if (this.rearIdx >= this.frontIdx) {
			return this.rearIdx - this.frontIdx;
		} else {
			return ((this.rearIdx + this.maxLength) - this.frontIdx);
		}
//		return this.rearIdx - this.frontIdx;

	}

	@Override
	public boolean isEmpty() {
		// 둘이 같으면 size가 0이므로 true
		return this.rearIdx == this.frontIdx;
	}

	// 원형큐에서는 front가 있는 자리를 사용하지 않고 비워둔다
	// 그렇지 않을 경우, 포화상태 또한 front == rear 값이 같아지므로
	// 공백과 포화상태를 구분할 수 없기 때문이다
	@Override
	public boolean isFull() {
		// 모두 채우면 큐가 empty인지 full인지 구분이 불가능하기 때문에
		// 1개가 덜 찬 상태를 full로 보도록 한다
		// 나머지 연산 사용, maxLength 보다 작은 값을 maxLength로 나누면 자기 자신이 나온다
		return this.frontIdx == (this.rearIdx + 1) % maxLength;
	}

	@Override
	public E front() {
		// 처음에 frontIdx를 0으로 초기화 했으나,
		// 환형큐는 맨 처음 front 자리를 항상 비워두므로
		// 인덱스를 하나 더해야 배열의 가장 첫 번째임
		return this.elements[++this.frontIdx];
	}

	@Override
	public E rear() {
		return this.elements[this.rearIdx];
	}

	@Override
	public boolean enQueue(E anElement) {
		if (isFull()) {
			// 큐가 가득 찰 경우 넣을 수 없음
			return false;
		}
		this.rearIdx = (this.rearIdx + 1) % maxLength; // 배열의 마지막 위치 n-1의 다음 위치는 0
		this.elements[this.rearIdx] = anElement;
		return true;

		/*
		 * this.rearIdx++; if(this.rearIdx == this.maxLength){ this.rearIdx = 0; }
		 * 
		 */

	}

	@Override
	public E deQueue() {
		if (isEmpty()) {
			// 큐가 isEmpty 면 삭제할 원소가 없음
			return null;
		} else {
			this.frontIdx = (this.frontIdx + 1) % maxLength;
			return this.elements[this.frontIdx];
		}
	}

	@Override
	public void clear() {
		this.frontIdx = 0;
		this.rearIdx = 0;
		for (int i = 0; i < size(); i++) {
			this.elements[i] = null; // 있어도 되고 없어도 됨
		}

	}

	// front원소의 위치가 리스트의 맨 앞 위치, 즉 0번째 위치이다
	// (this.frontIdx + 1) % maxLength 위치가 order가 0인 위치다
	@Override
	public E elementAt(int order) {
		order = (this.frontIdx + 1 + order) % this.maxLength;
		return this.elements[order];
	}

}
