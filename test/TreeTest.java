package test;
import src.Tree;
import org.junit.jupiter.api.BeforeEach;
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
         *               •
         *             /   \
         *            •     •
         *           /  \     \
         *          •    •     •
         *         / \  / \   /
         *        8  5 777 3  21  
         */
        tree = new Tree();
        tree = Tree.set(tree, 0, 8);
        tree = Tree.set(tree, 1, 5);
        tree = Tree.set(tree, 2, 777);
        tree = Tree.set(tree, 3, 3);
        tree = Tree.set(tree, 6, 21);
        
    }

    @Test
    void checkHeight() {
        int result = tree.getHeight();
        Assertions.assertEquals(3, result, "The result should be 3");
    }
}

