package de.htwberlin.prog2.datamodel;

import static org.junit.Assert.*;

/**
 * Created by laura on 04.02.17.
 */
public class BalancedTreeTest {

    private BalancedTree tree;
    private BalancedTree smallTree;
    private BalancedTreeNode expectedNode;
    private BalancedTreeNode actualNode;
    private BalancedTreeNode testNode;
    private BalancedTreeNode testNode2;
    private boolean actualBool;


    @org.junit.Before
    public void setUp() throws Exception {

        tree = new BalancedTree();
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

        smallTree = new BalancedTree();
        smallTree.insert("a");
        smallTree.insert("b");
        smallTree.insert("c");
    }

    @org.junit.After
    public void tearDown() throws Exception {

        tree.clear();
        smallTree.clear();
    }

    @org.junit.Test
    public void insert() throws Exception {
        smallTree.insert("d");

        assertTrue(smallTree.search("d"));
    }

    @org.junit.Test
    public void searchTrue() throws Exception {

        boolean actualBool = tree.search("xx");

        assertTrue(actualBool);
    }

    @org.junit.Test
    public void searchFalse() throws Exception {

        boolean actualBool = tree.search("xyz");

        assertFalse(actualBool);
    }

    @org.junit.Test
    public void searchNodeIsInTree() throws Exception {

        expectedNode = new BalancedTreeNode("yyy");
        actualNode = tree.getNode("yyy");

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

    @org.junit.Test
    public void searchNodeIsNotInTree() throws Exception {

        expectedNode = null;
        actualNode = tree.getNode("not");

        assertEquals(expectedNode, actualNode);
    }

    @org.junit.Test
    public void removeTheRootNode() throws Exception {

        //before removal of root
        expectedNode = new BalancedTreeNode("b");
        actualNode = smallTree.getRoot();

        assertEquals(expectedNode.getData(), actualNode.getData());

        //after removal of root
        expectedNode = new BalancedTreeNode("a");
        testNode = new BalancedTreeNode("b");
        actualNode = smallTree.remove(testNode);
        actualNode = smallTree.getRoot();

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

    @org.junit.Test
    public void isNodeALeaf() throws Exception {
        //if node == leaf
        testNode = tree.getNode("ab");
        actualBool = tree.getIfNodeIsLeaf(testNode);

        assertTrue(actualBool);
    }

    @org.junit.Test
    public void isNodeNotALeaf() throws Exception {
        //if node != leaf
        testNode = tree.getNode("ibb");
        actualBool = tree.getIfNodeIsLeaf(testNode);

        assertFalse(actualBool);
    }

    @org.junit.Test
    public void removeALeafNode() throws Exception {
        //before removal of leaf, node is in the tree
        expectedNode = new BalancedTreeNode("ab");
        actualNode = tree.getNode("ab");

        assertEquals(expectedNode.getData(), actualNode.getData());

        //after removal of leaf, node is not in the tree
        testNode = new BalancedTreeNode("ab");
        tree.remove(testNode);
        actualBool = tree.search("ab");

        assertFalse(actualBool);

        //after removal of leaf, parentNode(=="a") of "ab" is now a leaf
        testNode = new BalancedTreeNode("a");

        assertTrue(tree.getIfNodeIsLeaf(testNode));
    }

    @org.junit.Test
    public void getParentNodeIfParentIsRoot() throws Exception {
        expectedNode = new BalancedTreeNode("uio");
        testNode = new BalancedTreeNode("uuu");

        assertEquals(expectedNode.getData(), tree.getParentNode(testNode).getData());
    }

    @org.junit.Test
    public void getParentNode() throws Exception {
        expectedNode = new BalancedTreeNode("ibb");
        testNode = new BalancedTreeNode("r");

        assertEquals(expectedNode.getData(), tree.getParentNode(testNode).getData());
    }

    @org.junit.Test
    public void testGetNodeToReplaceRemovedEasy() throws Exception {
        expectedNode = new BalancedTreeNode("vvv");
        testNode = new BalancedTreeNode("xx");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

    @org.junit.Test
    public void testGetNodeToReplaceRemoved() throws Exception {
        expectedNode = new BalancedTreeNode("vvv");
        testNode = new BalancedTreeNode("uuu");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

    @org.junit.Test
    public void testGetNodeToReplaceRemovedTwoChildren() throws Exception {
        expectedNode = new BalancedTreeNode("ab");
        testNode = new BalancedTreeNode("ibb");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());

    }

    @org.junit.Test
    public void testGetNodeToReplaceRemovedTwoChildren2() throws Exception {
        expectedNode = new BalancedTreeNode("vvv");
        testNode = new BalancedTreeNode("yyy");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

        @org.junit.Test
    public void removeAMiddleNodeWithTwoChildren() throws Exception {
        expectedNode = new BalancedTreeNode("vvv");
        testNode = new BalancedTreeNode("yyy");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.remove(testNode2);

     //   assertEquals(expectedNode.getData(), actualNode.getData());

    }

    @org.junit.Test
    public void clear() throws Exception {
        int expectedDepth = 0;
        int actualDepth = tree.clear().getDepthOfTree();

        assertEquals(expectedDepth, actualDepth);
    }
}