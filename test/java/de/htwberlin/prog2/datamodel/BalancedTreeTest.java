package de.htwberlin.prog2.datamodel;

import static org.junit.Assert.*;

/**
 * Created by laura on 04.02.17.
 */
public class BalancedTreeTest {

    private BalancedTree<String> tree;
    private BalancedTree<String> emptyTree;
    private BalancedTreeNode<String> expectedNode;
    private BalancedTreeNode<String> actualNode;


    @org.junit.Before
    public void setUp() throws Exception {

        tree = new BalancedTree<>();
        tree.insert("a");
        tree.insert("yyy");
        tree.insert("uio");
        tree.insert("r");
        tree.insert("xx");
        tree.insert("ibb");
        tree.insert("zzz");
        tree.insert("ab");
        tree.insert("uuu");
        tree.insert("vvv");

        emptyTree = new BalancedTree<>();
    }

    @org.junit.After
    public void tearDown() throws Exception {

        tree.clear();
    }

    @org.junit.Test
    public void insert() throws Exception {

    }

    @org.junit.Test
    public void searchTrue() throws Exception {

        boolean expectedBool = true;
        boolean actualBool = tree.search("xx");

        assertEquals(expectedBool, actualBool);
    }

    @org.junit.Test
    public void searchFalse() throws Exception {

        boolean expectedBool = false;
        boolean actualBool = tree.search("xyz");

        assertEquals(expectedBool, actualBool);
    }

    @org.junit.Test
    public void searchNodeIsInTree() throws Exception {

        expectedNode = new BalancedTreeNode<>("yyy");
        actualNode = tree.searchNode("yyy");

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

    @org.junit.Test
    public void searchNodeIsNotInTree() throws Exception {

        expectedNode = null;
        actualNode = tree.searchNode("not");

        assertEquals(expectedNode, actualNode);
    }

    @org.junit.Test
    public void remove() throws Exception {

    }

    @org.junit.Test
    public void clear() throws Exception {
        int expectedDepth = 0;
        int actualDepth = tree.clear().getDepthOfTree();

        //BalancedTree<String> expectedTree = emptyTree;
        //BalancedTree<String> actualTree = tree.clear();

        assertEquals(expectedDepth, actualDepth);
    }
}