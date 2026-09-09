package test;

import src.Tree;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeTest {
    Tree tree;
    int height;
    int size;

    @BeforeEach
    public void setUp() {
        /**
         * Tree structure:
         *
         *------------- •
         * ----------/-----\
         * ---------•-------•
         * -------/---\------\
         * ------•-----•------•
         * -------\---/-\----/
         * ------- 5 777 3 21
         */
        tree = new Tree();
        tree = Tree.set(tree, 1, 5);
        tree = Tree.set(tree, 2, 777);
        tree = Tree.set(tree, 3, 3);
        tree = Tree.set(tree, 6, 21);

    }

    @Test
    void checkHeight() {
        int result = tree.getHeight();
        Assertions.assertEquals(3, result);
    }

    @Test
    void getReturnsCorrectValueForIndexWithMaxInt() {
        Tree newTree = Tree.set(tree, Integer.MAX_VALUE, 5);
        int result = Tree.get(newTree, Integer.MAX_VALUE);
    
        Assertions.assertEquals(5, result);
    }

    @Test
    void getThrowsIllegalArgumentExceptionForNegativeIndex() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Tree.get(tree,-1));
        Assertions.assertEquals("Index out of bounds", exception.getMessage());
    }

    @Test
    void setThrowsIllegalArgumentExceptionForNegativeIndex() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Tree.set(tree,-1, 99999));
        Assertions.assertEquals("Index out of bounds", exception.getMessage());
    }

    @Test
    void setThrowsIllegalArgumentExceptionForNonPositiveValue() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Tree.set(tree,7, -1));
        Assertions.assertEquals("Value must be positive integer", exception.getMessage());
    }

    @Test
    void maxinintervalReturnsCorrectValueForSameLeftAndRightIndex() {
        int result = Tree.maxininterval(tree, 2, 2);
        Assertions.assertEquals(777, result);
    }

    @Test
    void maxinintervalReturnsCorrectValueForLeftIndexOutOfBounds() {
        int result = Tree.maxininterval(tree, 0, 3);
        Assertions.assertEquals(777, result);
    }

    @Test
    void maxinintervalReturnsCorrectValueForRightIndexOutOfBounds() {
        int result = Tree.maxininterval(tree, 3, 16);
        Assertions.assertEquals(21, result);
    }

    @Test
    void maxinintervalReturnsZeroForBothIndexOutOfPositiveBounds() {
        int result = Tree.maxininterval(tree, 7, 16);
        Assertions.assertEquals(0, result);
    }

    @Test 
    void maxinintervalReturnsZeroForBothIndexOutOfNegativeBounds() {
        int result = Tree.maxininterval(tree, -100, -1);
        Assertions.assertEquals(0, result);
    }

        @Test
    void maxinintervalReturnsMaxOfTreeIfLeftOutOfNegativeAndRightOutOfPositiveIndexBounds() {
        int result = Tree.maxininterval(tree, -100, 100);
        Assertions.assertEquals(777, result);

    }
}
