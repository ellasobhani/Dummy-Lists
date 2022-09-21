/**
 * Implementation of lists, using doubly linked elements, and dummy nodes.
 * Starter class for "9.10 Laboratory: Lists with Dummy Nodes"
 * Please modify this code by following the directions in  on page 216 of
 * Java Structures sqrt(7) edition by Duane Bailey.
 */

import structure5.*;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E> {

	// Use these variables inherited from DoublyLinkedList
	// Do not uncomment this!  Just use the variables as if they were uncommented
	/**
	* Number of elements within the list.
	*	protected int count;
	*/

	/**
	* Reference to head of the list.
	*
	protected DoublyLinkedNode<E> head;
	*/

	/**
	* Reference to tail of the list.
	*
	protected DoublyLinkedNode<E> tail;
	*/


	/**
	* Constructs an empty list.
	*
	* @post constructs an empty list
	*
	*/
	public LinkedList() {
		// create null-value head and tail nodes
		head = new DoublyLinkedNode<E>(null);
		tail = new DoublyLinkedNode<E>(null);

		// link head and tail nodes
		head.setNext(tail);
		tail.setPrevious(head);

		count = 0;
	}

	/**
	* Determine the number of elements in the list.
	*
	* @post returns the number of elements in list
	*
	* @return The number of elements found in the list.
	*/
	public int size() {
		return count;
	}

	/**
	* Determine if the list is empty.
	*
	* @post returns true iff the list has no elements.
	*
	* @return True iff list has no values.
	*/
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	* Remove all values from list.
	*
	* @post removes all the elements from the list
	*/
	public void clear() {
		// create new null-value head and tail nodes
		head = new DoublyLinkedNode<E>(null);
		tail = new DoublyLinkedNode<E>(null);

		// link head and tail nodes
		head.setNext(tail);
		tail.setPrevious(head);

		count = 0;
	}

	/**
	* A private routine to add an element after a node.
	* @param value the value to be added
	* @param previous the node the come before the one holding value
	* @pre previous is non null
	* @post list contains a node following previous that contains value
	*/
	protected void insertAfter(E value, DoublyLinkedNode<E> previous) {
		// satisfy above pre condition
		Assert.pre(previous != null, "previous is null.");

		// create new node
		DoublyLinkedNode<E> newNode = new DoublyLinkedNode<E>(value);

		// insert 'newNode' and link to next, previous nodes
		newNode.setNext(previous.next());
		newNode.setPrevious(previous);

		// update links of surrounding nodes
		previous.setNext(newNode);
		newNode.next().setPrevious(newNode);

		// update count
		count++;

	}

	/**
	* A private routine to remove a node.
	* @param node the node to be removed
	* @pre node is non null
	* @post node node is removed from the list
	* @post returns the value of the node removed
	* @return the value of the node removed
	*/
	protected E remove(DoublyLinkedNode<E> node) {
		// satisfy above pre condition
		Assert.pre(node.value() != null, "node is null");

		E value = node.value();

		// update links of surrounding nodes to exclude 'node'
		node.previous().setNext(node.next());
		node.next().setPrevious(node.previous());

		// update count
		count--;

		// return removed value
		return value;
	}


	/**
	* Add a value to the head of the list.
	*
	* @param value The value to be added.
	* @pre value is not null
	* @post adds element to head of list
	*
	*/
	public void addFirst(E value) {
		// satisfy above pre condition
		Assert.pre(value != null, "value is null");

		// insert value directly after head
		insertAfter(value, head);
	}

	/**
	* Add a value to the tail of the list.
	*
	* @param value The value to be added.
	* @pre value is not null
	* @post adds new value to tail of list
	*
	*/
	public void addLast(E value) {
		// satisfy above pre condition
		Assert.pre(value != null, "value is null");

		// insert value directly before tail
		insertAfter(value, tail.previous());

	}

	/**
	* Remove a value from the head of the list.
	* Value is returned.
	*
	* @pre list is not empty
	* @post removes first value from list
	*
	* @post Returns the value removed from the list.
	* @return The value removed from the list.
	*/
	public E removeFirst() {
		// satisfy above pre condition
		Assert.pre(!isEmpty(), "List is empty.");

		// remove first node, return value
		return remove(head.next());
	}

	/**
	* Remove a value from the tail of the list.
	*
	* @pre list is not empty
	* @post removes value from tail of list
	* @post Returns the value removed from the list.
	*
	* @return The value removed from the list.
	*/
	public E removeLast() {
		// satisfy above pre condition
		Assert.pre(!isEmpty(), "List is empty.");

		// remove last node, return value
		return remove(tail.previous());
	}

	/**
	* Get a copy of the first value found in the list.
	*
	* @pre list is not empty
	* @post returns first value in list.
	*
	* @return A reference to first value in list.
	*/
	public E getFirst() {
		// satisfy above pre condition
		Assert.pre(!isEmpty(), "List is empty.");

		// return first value
		return head.next().value();
	}

	/**
	* Get a copy of the last value found in the list.
	*
	* @pre list is not empty
	* @post returns last value in list.
	*
	* @return A reference to the last value in the list.
	*/
	public E getLast() {
		// satisfy above pre condition
		Assert.pre(!isEmpty(), "List is empty.");

		// return final value
		return tail.previous().value();
	}


	/**
	* Get the node at a given index i.
	*
	* @pre 0 <= i <= size()
	* @post returns the DoublyLinkedNode object at some index i
	*
	* @return the DoublyLinkedNode object at some index i (returns
	* null if i invalid)
	*/
	private DoublyLinkedNode<E> getNode(int i) {
		// satisfy above pre condition
		// if i out of range, returns null
		if (!(0 <= i) && (i < size())) {
			return null;
		}

		// get first node in list
		DoublyLinkedNode<E> current = head.next();

		// find ith index in list
		while (i > 0) {
			current = current.next();
			i--;
		}

		// return ith node
		return current;
	}
	//$ Nice job separating this out into its own method!

	/**
	* Insert the value at location.
	*
	* New value gets index i, old value at index i shifts to index i + 1
	*
	* @pre 0 <= i <= size()
	* @post adds the value o to the ith entry of the list
	* @param i the index of this new value
	* @param o the the value to be stored
	*/
	public void add(int i, E o) {
		// satisfy above pre condition
		Assert.pre((0 <= i) && (i <= size()), "Index out of range.");

		// get node at index i
		DoublyLinkedNode<E> current = getNode(i);

		// insert new node at ith index
		insertAfter(o, current.previous());
	}

	/**
	* Remove and return the value at location i.
	*
	* @pre 0 <= i < size()
	* @post removes and returns the object found at that location.
	*
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E remove(int i) {
		// if i out of range, returns null
		if (!(0 <= i) && (i < size())) {
			return null;
		}

		// get node at index i
		DoublyLinkedNode<E> current = getNode(i);

		// remove ith node, return value
		return remove(current);
	}


	/**
	* Get the value at location i.
	*
	* @pre 0 <= i < size()
	* @post returns the object found at that location.
	*
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E get(int i) {
		// // satisfy above pre condition
		// if i out of range, returns null
		if (!(0 <= i) && (i < size())) {
			return null;
		}

		// get node at index i
		DoublyLinkedNode<E> current = getNode(i);

		// return value at index i
		return current.value();
	}

	/**
	* Set the value stored at location i to object o, returning the old value.
	*
	* @pre 0 <= i < size()
	* @post sets the ith entry of the list to value o, returns the old value.
	* @param i the location of the entry to be changed.
	* @param o the new value
	* @return the former value of the ith entry of the list.
	*/
	public E set(int i, E o) {
		// satisfy above pre condition
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");

		// get node at index i
		DoublyLinkedNode<E> currentNode = getNode(i);

		// store existing value
		E oldValue = currentNode.value();

		// update value
		currentNode.setValue(o);

		// return former value
		return oldValue;
	}

	/**
	* Determine the first location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	*
	* @param value The value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int indexOf(E value) {
		// satisfy above pre condition
		Assert.pre(value != null, "Value is null.");

		// start with first node
		DoublyLinkedNode<E> current = head.next();

		// check each node
		for (int i = 0; i < size(); i++) {
			// if value found, return index 'i'
			if (current.value().equals(value)) {
				return i;
			}
			// move to next node
			current = current.next();
		}
		// if value not found, return -1
		return -1;
	}

	/**
	* Determine the last location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	*
	* @param value the value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int lastIndexOf(E value) {
		// satisfy above pre condition
		Assert.pre(value != null, "Value is null.");

		// start with last node
		DoublyLinkedNode<E> current = tail.previous();

		// check each node, moving backwards through list
		for (int i = size() - 1; i >= 0; i--) {
			// if value found, return index 'i'
			if (current.value().equals(value)) {
				return i;
			}
			// move to previous node
			current = current.previous();
		}
		// if value not found, return -1
		return -1;
	}

	/**
	* Check to see if a value is within the list.
	*
	* @pre value not null
	* @post returns true iff value is in the list
	*
	* @param value A value to be found in the list.
	* @return True if value is in list.
	*/
	public boolean contains(E value) {
		// satisfy above pre condition
		Assert.pre(value != null, "Value is null.");

		// if element not in list, return false
		if (indexOf(value) == -1) {
			return false;
		}

		// else, return true
		return true;
	}

	/**
	* Remove a value from the list.  At most one value is removed.
	* Any duplicates remain.  Because comparison is done with "equals,"
	* the actual value removed is returned for inspection.
	*
	* @pre value is not null.  List can be empty.
	* @post first element matching value is removed from list
	*
	* @param value The value to be removed.
	* @return The value actually removed.
	*/
	public E remove(E value) {
		// satisfy above pre condition
		Assert.pre(value != null, "Value is null.");

		// get index of 'value'
		int index = indexOf(value);

		// remove node at 'index', return value
		return remove(index);
	}

	/**
	* Construct an iterator to traverse the list.
	*
	* @post returns iterator that allows the traversal of list.
	*
	* @return An iterator that traverses the list from head to tail.
	*/
	public Iterator<E> iterator() {
		/**
		 * comment
		 */

		return new DoublyLinkedListIterator<E>(head, tail);
		// return new DoublyLinkedListIterator<E>(head);
	}

	/**
	* Construct a string representation of the list.
	*
	* @post returns a string representing list
	*
	* @return A string representing the elements of the list.
	*/
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("<LinkedList (" + count + "):");

		Iterator<E> li = iterator();
		while (li.hasNext()) {
			s.append(" " + li.next());
		}
		s.append(">");

		return s.toString();
	}
}
