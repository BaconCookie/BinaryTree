package de.htwberlin.prog2.model;

import static org.junit.Assert.*;

/**
 * Created by laura on 04.02.17.
 */
public class BinaryTreeTest {

    private BinaryTree tree;
    private BinaryTree smallTree;
    private BinaryTreeNode expectedNode;
    private BinaryTreeNode actualNode;
    private BinaryTreeNode testNode;
    private BinaryTreeNode testNode2;
    private boolean actualBool;


    @org.junit.Before
    public void setUp() throws Exception {

        tree = new BinaryTree();
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

        smallTree = new BinaryTree();
        smallTree.insert("a");
        smallTree.insert("b");
        smallTree.insert("c");
    }

    @org.junit.After
    public void tearDown() throws Exception {

        tree.clearTree();
        smallTree.clearTree();
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

        boolean actualBool = tree.search("xyz"); //no node xyz in this tree

        assertFalse(actualBool);
    }

    @org.junit.Test
    public void searchNodeIsInTree() throws Exception {

        expectedNode = new BinaryTreeNode("yyy");
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
        expectedNode = new BinaryTreeNode("a");
        actualNode = smallTree.getRoot();

        assertEquals(expectedNode.getData(), actualNode.getData());

        //after removal of root
        expectedNode = new BinaryTreeNode("b");
        testNode = new BinaryTreeNode("a");
        smallTree.remove(testNode);
        actualNode = smallTree.getRoot();

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

    @org.junit.Test
    public void isNodeALeaf() throws Exception {
        //if node == leaf
        testNode = smallTree.getNode("c");
        actualBool = smallTree.getIfNodeIsLeaf(testNode);

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
        expectedNode = new BinaryTreeNode("c");
        actualNode = smallTree.getNode("c");

        assertEquals(expectedNode.getData(), actualNode.getData());

        //after removal of leaf, node is not in the tree
        testNode = new BinaryTreeNode("c");
        tree.remove(testNode);
        actualBool = smallTree.search("c");

        assertFalse(actualBool);

        //after removal of leaf, parentNode(=="b") of "c" is now a leaf
        testNode = new BinaryTreeNode("b");

        assertTrue(tree.getIfNodeIsLeaf(testNode));
    }

    @org.junit.Test
    public void getParentNodeIfParentIsRoot() throws Exception {
        expectedNode = new BinaryTreeNode("a");
        testNode = new BinaryTreeNode("b");

        assertEquals(expectedNode.getData(), smallTree.getParentNode(testNode).getData());
    }

    @org.junit.Test
    public void getParentNode() throws Exception {
        expectedNode = new BinaryTreeNode("b");
        testNode = new BinaryTreeNode("c");

        assertEquals(expectedNode.getData(), smallTree.getParentNode(testNode).getData());
    }

    @org.junit.Test
    public void testGetNodeToReplaceRemovedEasy() throws Exception {
        expectedNode = new BinaryTreeNode("c");
        testNode = new BinaryTreeNode("b");
        testNode2 = smallTree.getNode(testNode.getData());
        actualNode = smallTree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());
    }
/*
    @org.junit.Test
    public void testGetNodeToReplaceRemoved() throws Exception {
        expectedNode = new BinaryTreeNode("vvv");
        testNode = new BinaryTreeNode("uuu");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());

    }

    @org.junit.Test
    public void testGetNodeToReplaceRemovedTwoChildren() throws Exception {
        expectedNode = new BinaryTreeNode("ab");
        testNode = new BinaryTreeNode("ibb");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());

    }

    @org.junit.Test
    public void testGetNodeToReplaceRemovedTwoChildren2() throws Exception {
        expectedNode = new BinaryTreeNode("vvv");
        testNode = new BinaryTreeNode("yyy");
        testNode2 = tree.getNode(testNode.getData());
        actualNode = tree.getNodeToReplaceRemoved(testNode2);

        assertEquals(expectedNode.getData(), actualNode.getData());
    }

        @org.junit.Test
    public void removeAMiddleNodeWithTwoChildren() throws Exception {
        expectedNode = new BinaryTreeNode("vvv");
        testNode = new BinaryTreeNode("yyy");
        testNode2 = tree.getNode(testNode.getData());


     //   assertEquals(expectedNode.getData(), actualNode.getData());

    }
    */

    @org.junit.Test
    public void clearTree() throws Exception {
        int expectedDepth = 0;
        int actualDepth = tree.clearTree().getDepthOfTree();

        assertEquals(expectedDepth, actualDepth);
    }

}