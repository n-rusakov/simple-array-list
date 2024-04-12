package org.example.simplelist;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


/**
 * Simple dynamic array implementation of org.example.simplelist.AbstractSimpleList interface.
 *
 * <p>Each instance have a size - current elements count, and capacity -
 * max size, when adding not call new memory allocation.
 * If size == capacity when adding, then allocating new list with
 * new capacity = capacity * 2, and current data copied to new memory.
 * Default value of capacity is 8.
 *
 * <p>In this implementation realized sorting by quicksort algorithm.
 * <p>Null elements allowed.
 * @param <T> type of stored elements
 */
public class SimpleArrayList<T> implements AbstractSimpleList<T> {

    private static final String OUT_OF_BOUNDS_MESSAGE =
            "Index: {0}, size: {1}";

    private final static int DEFAULT_CAPACITY = 8;

    private int size;

    private Object[] data;

    private final Random random = new Random(Instant.now().toEpochMilli());

    /**
     * Construct new list with default capacity = 8.
     */
    SimpleArrayList() {
        size = 0;
        data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Construct new list with specified capacity.
     *
     * @param capacity initial capacity of list
     * @throws IllegalArgumentException if capacity < 0
     */
    SimpleArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        size = 0;
        data = new Object[capacity];
    }

    /**
     * Add new element at end of list.
     *
     * @param t element to be added
     */
    @Override
    public void add(T t) {
        if (size == data.length) {
            grow();
        }
        data[size] = t;
        size++;
    }

    /**
     * Insert new element at specified position by index.
     *
     * @param index index in witch position element to be inserted
     * @param t     element to insert
     * @throws IndexOutOfBoundsException if index out of bounds
     *                                   (less than 0 or great than size). If index == size, then
     *                                   element added to the end of list
     */
    @Override
    public void add(int index, T t) {
        checkIndexAdd(index);

        if (size == data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = t;
        size++;
    }

    /**
     * Return element at specified position.
     *
     * @param index index of element to return
     * @return element at specified position
     * @throws IndexOutOfBoundsException if index out of bounds
     *                                   (less than 0 or great or equal than size)
     */
    @Override
    public T get(int index) {
        checkIndexGetDelete(index);

        return (T) data[index];
    }

    /**
     * Remove element at specified position.
     *
     * @param index index of element to remove
     * @throws IndexOutOfBoundsException if index out of bounds
     *                                   (less than 0 or great or equal than size)
     */
    @Override
    public void remove(int index) {
        checkIndexGetDelete(index);

        if (index != size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[size - 1] = null;
        size--;
    }

    /**
     * Remove all elements from the list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; ++i)
            data[i] = null;

        size = 0;
    }


    /**
     * Sort elements in list, using specified comparator.
     * <p>Used quicksort algorithm whit random select of pivot element.
     * <p>Comparator equal null not allowed.
     * <p>For null elements used nullFirst policy.
     *
     * @param comparator used to compare elements by sorting
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException();
        }

        var nullFirstComparator = Comparator.nullsFirst(comparator);
        quicksort((T[]) data, 0, size - 1, nullFirstComparator);
    }

    /**
     * Utility recursive method, then sort interval in specified array.
     * <p> Used quicksort algorithm with random selection of pivot element.
     *
     * @param data       array to be sorted
     * @param left       left bound of interval to sort, inclusive
     * @param right      right bound of interval to sort, inclusive
     * @param comparator comparator, used to order elements
     * @param <E>        type of elements in array
     */
    private <E> void quicksort(E[] data, int left, int right, Comparator<? super E> comparator) {
        if (left >= right) {
            return;
        }

        // Select pivot element and shift them to the right position
        int pivotIndex = left + random.nextInt(right - left + 1);
        E pivotObject = data[pivotIndex];
        swap(data, pivotIndex, right);

        // Partition
        int i = left - 1;
        for (int j = left; j <= right - 1; ++j) {
            if (comparator.compare(data[j], pivotObject) <= 0) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, right);

        // Recursive calls
        quicksort(data, left, i, comparator);
        quicksort(data, i + 2, right, comparator);
    }

    /**
     * Utility method, swap two elements in array.
     * <p> Bounds not checked!!
     *
     * @param data source array
     * @param idx1 index of first element
     * @param idx2 index of second element
     */
    private void swap(Object[] data, int idx1, int idx2) {
        Object tmp = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = tmp;
    }

    /**
     * Check that index not out of bounds when add element.
     *
     * @param index index to be checked
     */
    private void checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    MessageFormat.format(OUT_OF_BOUNDS_MESSAGE, index, size));
        }
    }

    /**
     * Check that index not out of bounds when get ot delete element.
     *
     * @param index index to be checked
     */
    private void checkIndexGetDelete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    MessageFormat.format(OUT_OF_BOUNDS_MESSAGE, index, size));
        }
    }

    /**
     * Reallocate memory with capacity = capacity * 2
     * or DEFAULT_CAPACITY if current capacity is zero,
     * and copy data to new memory
     */
    private void grow() {
        int oldCapacity = data.length;

        int newCapacity = oldCapacity == 0
                ? DEFAULT_CAPACITY
                : oldCapacity * 2;

        data = Arrays.copyOf(data, newCapacity);
    }

    /**
     * Returns current size of list
     *
     * @return size
     */
    public int size() {
        return size;
    }

}
