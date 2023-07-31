package Data_Structure.List;


/**
 * 
 * �ڹ� List Interface�Դϴ�. <br>
 * List�� ArrayList, SinglyLinkedList, DoublyLinked�� ���� ���� �����˴ϴ�.
 * 
 * @author st_lab
 * @param <E> the type of elements in this list
 *
 * @version 1.0
 * 
 */
 
public interface List<E> {
 
	/**
	 * ����Ʈ�� ��Ҹ� �߰��մϴ�.
	 * 
	 * @param value ����Ʈ�� �߰��� ���
	 * @return ����Ʈ���� �ߺ��� ������� ���� ��쿡 ����Ʈ�� �̹� �ߺ��Ǵ� 
	 *         ��Ұ� ���� ��� {@code false}�� ��ȯ�ϰ�, �ߺ��Ǵ� ���Ұ�
	 *         ������� {@code true}�� ��ȯ
	 */
	boolean add(E value);
 
	/**
	 * ����Ʈ�� ��Ҹ� Ư�� ��ġ�� �߰��մϴ�. 
	 * Ư�� ��ġ �� ������ ��ҵ��� �� ĭ�� �ڷ� �и��ϴ�.
	 * 
	 * @param index ����Ʈ�� ��Ҹ� �߰��� Ư�� ��ġ ����
	 * @param value ����Ʈ�� �߰��� ���
	 */
	void add(int index, E value);
 
	/**
	 * ����Ʈ�� index ��ġ�� �ִ� ��Ҹ� �����մϴ�.
	 * 
	 * @param index ����Ʈ���� ���� �� ��ġ ����
	 * @return ������ ��Ҹ� ��ȯ
	 */
	E remove(int index);
 
	/**
	 * ����Ʈ���� Ư�� ��Ҹ� �����մϴ�. ������ ��Ұ� 
	 * ���� ���� ��� ���� ó�� �߰��� ��Ҹ� �����˴ϴ�.
	 * 
	 * @param value ����Ʈ���� ������ ���
	 * @return ����Ʈ�� ������ ��Ұ� ���ų� ������ ������ 
	 *         ��� {@code false}�� ��ȯ�ϰ� ������ ������ ��� {@code true}�� ��ȯ 
	 */
	boolean remove(Object value);
 
	/**
	 * ����Ʈ�� �ִ� Ư�� ��ġ�� ��Ҹ� ��ȯ�մϴ�.
	 * 
	 * @param index ����Ʈ�� ������ ��ġ ���� 
	 * @return ����Ʈ�� index ��ġ�� �ִ� ��� ��ȯ 
	 */
	E get(int index);
 
	/**
	 * ����Ʈ���� Ư�� ��ġ�� �ִ� ��Ҹ� �� ��ҷ� ��ü�մϴ�.
	 * 
	 * @param index ����Ʈ�� ������ ��ġ ���� 
	 * @param value ���� ��ü�� ��� ���� 
	 */
	void set(int index, E value);
 
	/**
	 * ����Ʈ�� Ư�� ��Ұ� ����Ʈ�� �ִ��� ���θ� Ȯ���մϴ�.
	 * 
	 * @param value ����Ʈ���� ã�� Ư�� ��� ���� 
	 * @return ����Ʈ�� Ư�� ��Ұ� ������ ��� {@code true}, �������� ���� ��� {@code false}�� ��ȯ  
	 */
	boolean contains(Object value);
 
	/**
	 * ����Ʈ�� Ư�� ��Ұ� �� ��° ��ġ�� �ִ����� ��ȯ�մϴ�.
	 * 
	 * @param value ����Ʈ���� ��ġ�� ã�� ��� ����  
	 * @return ����Ʈ���� ó������ ��ҿ� ��ġ�ϴ� ��ġ�� ��ȯ.
	 *         ���� ��ġ�ϴ� ��Ұ� ������� -1 �� ��ȯ 
	 */
	int indexOf(Object value);
 
	/**
	 * ����Ʈ�� �ִ� ����� ������ ��ȯ�մϴ�.
	 * 
	 * @return ����Ʈ�� �ִ� ��� ������ ��ȯ  
	 */
	int size();
 
	/**
	 * ����Ʈ�� ��Ұ� ����ִ����� ��ȯ�մϴ�.
	 * @return ����Ʈ�� ��Ұ� ������� {@code true}, ��Ұ� ������� {@code false}�� ��ȯ 
	 */
	boolean isEmpty();
 
	/**
	 * ����Ʈ�� �ִ� ��Ҹ� ��� �����մϴ�.
	 */
	public void clear();
 
}