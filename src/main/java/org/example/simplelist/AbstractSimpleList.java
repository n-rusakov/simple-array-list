package org.example.simplelist;

import java.util.Comparator;

/**
 * Simple collection with basic operations add, get, remove, clear, sort
 *
 * @param <T> type of elements
 */
public interface AbstractSimpleList<T> {

    /**
     * Add element to the end of list
     *
     * @param t element to added
     */
    void add(T t);

    /**
     * Insert element in specified position.
     * <p> All elements with index greater or equal that specified shift to right
     *
     * @param index index in witch position element to be inserted
     * @param t element to insert
     */
    void add(int index, T t);

    /**
     * Return element at specified position
     *
     * @param index index of element to return
     * @return element at specified position
     */
    T get(int index);

    /**
     * Remove element at specified position
     * <p>All element with index greater that specified shift to left
     *
     * @param index index of element to remove
     */
    void remove(int index);

    /**
     * Remove all elements from the list
     */
    void clear();

    /**
     * Return elements count in list
     *
     * @return elements count
     */
    int size();

    /**
     * Sort all elements, using specified comparator
     * @param comparator used to compare elements by sorting
     */
    void sort(Comparator<? super T> comparator);

}
