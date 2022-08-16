package stack;

public interface Stack<E> {
	public int size();

	public boolean isEmpty();

	public boolean isFull();

	public boolean push(E anElement);

	public E pop();

	public E peek();

	public void clear();
}
