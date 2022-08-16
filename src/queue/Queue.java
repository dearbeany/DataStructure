package queue;

public interface Queue<E> {
	public int size();

	public boolean isEmpty();

	public boolean isFull();

	public E front();

	public E rear();

	public boolean enQueue(E anElement);

	public E deQueue();

	public void clear();

	public E elementAt(int order);
}
