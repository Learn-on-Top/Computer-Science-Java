/**
 * Represents a node in a binary tree
 * Each node stores an element and references to its left and right child nodes
 * 
 * @author raickmiranda
 * @summary This class is part of a binary search tree implementation
 * @version 1.0
 */

public class BinarySearchTree {
    private Node root;

    /**
     * Constructs an empty BinarySearchTree
     */
    BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts the specified element into the BinarySearchTree
     * 
     * @param in the element to be inserted
     * @throws Exception if the specified element is already present in the tree
     */
    public void insert(int in) throws Exception {
        root = insert(in, root);
    }

    /**
     * Recursive helper method to insert the specified element into the
     * BinarySearchTree
     * 
     * @param in          the element to be inserted
     * @param currentNode node being considered
     * @return the modified {@code currentNode} structure
     * @throws Exception if the specified element is already present in the tree
     */
    private Node insert(int in, Node currentNode) throws Exception {
        if (currentNode == null)
            currentNode = new Node(in);
        else if (in < currentNode.element)
            currentNode.left = insert(in, currentNode.left);
        else if (in > currentNode.element)
            currentNode.right = insert(in, currentNode.right);
        else
            throw new Exception("Error! The specified element is already present in the tree.");

        return currentNode;
    }

    /**
     * Inserts a new node with the specified value using the parent pointer approach
     * 
     * @param in the element to be inserted
     * @throws Exception if the specified element is already present in the tree
     */
    void insertByParent(int in) throws Exception {
        if (root == null)
            root = new Node(in);
        else if (in < root.element)
            insertByParent(in, root.left, root);
        else if (in > root.element)
            insertByParent(in, root.right, root);
        else
            throw new Exception("Error! The specified element is already present in the tree.");
    }

    /**
     * 
     * @param in          the element to be inserted
     * @param currentNode node being considered
     * @param parent      parent node of {@code currentNode}
     * @throws Exception if the specified element is already present in the tree
     */
    void insertByParent(int in, Node currentNode, Node parent) throws Exception {
        if (currentNode == null) {
            if (in < parent.element)
                parent.left = new Node(in);
            else
                parent.right = new Node(in);
        } else if (in < currentNode.element)
            insertByParent(in, currentNode.left, currentNode);
        else if (in > currentNode.element)
            insertByParent(in, currentNode.right, currentNode);
        else
            throw new Exception("Error! The specified element is already present in the tree.");
    }
}
