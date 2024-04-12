package org.example.simplelist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("SimpleArrayList Test")
public class SimpleArrayListTests {

    static class ElementClass{
        public int param1;
        public String param2;

        public ElementClass(int param1, String param2) {
            this.param1 = param1;
            this.param2 = param2;
        }
    }

    @Test
    @DisplayName("Create List with negative capacity")
    public void whenConstruct_givenNegativeCapacity_thenThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimpleArrayList<Integer>(-5));
    }

    @Test
    @DisplayName("Add 1000 elements in List of Integer")
    public void whenAdd_given1000ElementsOfInteger_thenSize1000() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        for (int i = 0; i < 1000; ++i) {
            arrayList.add(i);
        }

        assertEquals(1000, arrayList.size());
    }

    @Test
    @DisplayName("Add 1000 elements in List of String")
    public void whenAdd_given1000ElementsOfString_thenSize1000() {
        SimpleArrayList<String> arrayList = new SimpleArrayList<>();

        for (int i = 0; i < 1000; ++i) {
            arrayList.add(String.valueOf(i));
        }

        assertEquals(1000, arrayList.size());
    }

    @Test
    @DisplayName("Add 1000 elements in List of custom class")
    public void whenAdd_given1000ElementsOfClass_thenSize1000() {
        SimpleArrayList<ElementClass> arrayList = new SimpleArrayList<>();

        for (int i = 0; i < 1000; ++i) {
            arrayList.add(new ElementClass(i, String.valueOf(i)));
        }

        assertEquals(1000, arrayList.size());
    }


    @Test
    @DisplayName("Add elements and get by indexes")
    public void whenAdd_thenGet() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(100);
        arrayList.add(200);
        arrayList.add(300);

        assertEquals(100, arrayList.get(0));
        assertEquals(200, arrayList.get(1));
        assertEquals(300, arrayList.get(2));
    }


    @Test
    @DisplayName("Add element by index and get by indexes")
    public void whenAddByIndex_thenGet() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        arrayList.add(1, 444);

        assertEquals(4, arrayList.size());

        assertEquals(1, arrayList.get(0));
        assertEquals(444, arrayList.get(1));
        assertEquals(2, arrayList.get(2));
        assertEquals(3, arrayList.get(3));
    }


    @Test
    @DisplayName("Get element by incorrect index")
    public void whenGet_givenIncorrectIndex_thenThrow() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(1);
        arrayList.add(2);

        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(10));

    }

    @Test
    @DisplayName("Remove element in middle position")
    public void whenRemove_Given5Elements_thenSize4() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(100);
        arrayList.add(200);
        arrayList.add(300);
        arrayList.add(400);
        arrayList.add(500);

        arrayList.remove(2);

        assertEquals(4, arrayList.size());

        assertEquals(100, arrayList.get(0));
        assertEquals(200, arrayList.get(1));
        assertEquals(400, arrayList.get(2));
        assertEquals(500, arrayList.get(3));
    }

    @Test
    @DisplayName("Sorting array of 5 Integer")
    public void whenSort_given5Integer_thenSorted() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(4);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.add(3);
        arrayList.add(1);

        arrayList.sort(Integer::compare);

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
        assertEquals(4, arrayList.get(3));
        assertEquals(5, arrayList.get(4));
    }

    @Test
    @DisplayName("Sort array of 1000 Long")
    public void whenSort_given1000Long_thenSorted() {
        SimpleArrayList<Long> arrayList = new SimpleArrayList<>();

        for (int i = 999; i >=0; --i) {
            arrayList.add(i * 1000L);
        }

        arrayList.sort(Long::compareTo);

        for (int i = 0; i <= 999; ++i) {
            assertEquals(i * 1000L, arrayList.get(i));
        }
    }

    @Test
    @DisplayName("Sort array with null elements")
    public void whenSort_givenNullElement_thenSorted() {
        SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(null);
        arrayList.add(3);
        arrayList.add(null);

        arrayList.sort(Integer::compareTo);

        assertNull(arrayList.get(0));
        assertNull(arrayList.get(1));
        assertEquals(1, arrayList.get(2));
        assertEquals(2, arrayList.get(3));
        assertEquals(3, arrayList.get(4));
    }


}
