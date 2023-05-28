/**
 * Represents a binary search tree data structure. It stores a collection of
 * elements in a hierarchical structure, where each element has a unique key
 * value and follows the property that the left child node has a smaller value
 * and the right child node has a larger value than its parent node.
 * 
 * @author raickmiranda
 * @summary This class is part of a binary search tree implementation.
 * @version 1.0
 */

public class BinarySearchTree {
    private Node root;

    /**
     * Constructs an empty BinarySearchTree.
     */
    BinarySearchTree() {
        root = null;
    }

    /**
     * Searches for a specific key in the {@code BinarySearchTree}.
     * 
     * @param key element to be searched for
     * @return true if the key is found in the tree, false otherwise
     */
    public boolean search(int key) {
        return search(key, root);
    }

    /**
     * Searches for a specific key in the {@code BinarySearchTree} starting from the
     * given node.
     * 
     * @param key         element to be searched for
     * @param currentNode node being examined
     * @return true if the key is found in the subtree rooted at the
     *         {@code currentNode}, false otherwise
     */
    private boolean search(int key, Node currentNode) {
        boolean answer;

        if (currentNode == null)
            answer = false;
        else if (key == currentNode.element)
            answer = true;
        else if (key < currentNode.element)
            answer = search(key, currentNode.left);
        else
            answer = search(key, currentNode.right);

        return answer;
    }

    /**
     * Calculates and returns the height of the {@code BinarySearchTree}. The heigth
     * is defined as the maximum number of edges from the roof to a leaf node.
     * 
     * @param currentNode node being processed
     * @param height      of the {@code currentNode}
     * @return height of the {@code BinarySearchTree}
     */
    public int getHeight(Node currentNode, int height) {
        if (currentNode == null)
            height--;
        else {
            int heightLeft = getHeight(currentNode.left, height + 1);
            int heightRight = getHeight(currentNode.right, height + 1);
            height = (heightLeft > heightRight) ? heightLeft : heightRight;
        }
        return height;
    }

    /**
     * Inserts the specified element into the {@code BinarySearchTree}.
     * 
     * @param in the element to be inserted
     * @throws Exception if the specified element is already present in the tree
     */
    public void insert(int in) throws Exception {
        root = insert(in, root);
    }

    /**
     * Helper method to insert (normally) the specified element into
     * {@code BinarySearchTree}.
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
     * Inserts a new node with the specified value using the parent pointer
     * approach.
     * 
     * @param in the element to be inserted
     * @throws Exception if the specified element is already present in the tree
     */
    public void insertByParent(int in) throws Exception {
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
     * Helper method to insert (by parent) the specified element into
     * {@code BinarySearchTree}.
     * 
     * @param in          the element to be inserted
     * @param currentNode node being considered
     * @param parent      parent node of {@code currentNode}
     * @throws Exception if the specified element is already present in the tree
     */
    private void insertByParent(int in, Node currentNode, Node parent) throws Exception {
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

    /**
     * Removes the specified element from the {@code BinarySearchTree}.
     * 
     * @param toBeRemoved the element to be removed
     * @throws Exception if the binary tree is empty
     */
    public void remove(int toBeRemoved) throws Exception {
        root = remove(toBeRemoved, root);
    }

    /**
     * Method to remove the specified element from the {@code BinarySearchTree}.
     * 
     * @param toBeRemoved the element to be removed
     * @param currentNode node being checked
     * @return updated node after removal
     * @throws Exception if the binary tree is empty
     */
    private Node remove(int toBeRemoved, Node currentNode) throws Exception {
        if (currentNode == null)
            throw new NullPointerException("Error! The binary search tree is empty.");
        else if (toBeRemoved < currentNode.element)
            currentNode.left = remove(toBeRemoved, currentNode.left);
        else if (toBeRemoved > currentNode.element)
            currentNode.right = remove(toBeRemoved, currentNode.right);
        else if (currentNode.right == null)
            currentNode = currentNode.left;
        else if (currentNode.left == null)
            currentNode = currentNode.right;
        else
            currentNode.left = findMaxLeft(currentNode, currentNode.left);

        return currentNode;
    }

    /**
     * Removes the specified element from the {@code BinarySearchTree} using the
     * parent node approach. The removal process maintains the data structure
     * properties.
     * 
     * @param toBeRemoved element to be removed
     * @throws Exception if the binary tree is empty
     */
    public void removeByParent(int toBeRemoved) throws Exception {
        if (root == null)
            throw new NullPointerException("Error! The binary search tree is empty.");
        else if (toBeRemoved < root.element)
            removeByParent(toBeRemoved, root.left, root);
        else if (toBeRemoved > root.element)
            removeByParent(toBeRemoved, root.right, root);
        else if (root.right == null)
            root = root.left;
        else if (root.left == null)
            root = root.right;
        else
            root.left = findMaxLeft(root, root.left);
    }

    /**
     * Helper method to remove the specified element from the
     * {@code BinarySearchTree}. The removal process maintains the data structure
     * properties.
     * 
     * @param toBeRemoved the element to be removed
     * @param currentNode node being considered
     * @param parent      parent of the current node
     * @throws Exception if the binary tree is empty
     */
    private void removeByParent(int toBeRemoved, Node currentNode, Node parent) throws Exception {
        if (currentNode == null)
            throw new NullPointerException("Error! The binary search tree is empty.");
        else if (toBeRemoved < currentNode.element)
            removeByParent(toBeRemoved, currentNode.left, currentNode);
        else if (toBeRemoved > currentNode.element)
            removeByParent(toBeRemoved, currentNode.right, currentNode);
        else if (currentNode.right == null) {
            if (toBeRemoved < parent.element)
                parent.left = currentNode.left;
            else
                parent.right = currentNode.left;
        } else if (currentNode.left == null) {
            if (toBeRemoved < parent.element)
                parent.left = currentNode.right;
            else
                parent.right = currentNode.right;
        } else
            currentNode.left = findMaxLeft(currentNode, currentNode.left);
    }

    /**
     * Finds the maximum element on the left subtree of the current node during
     * removal.
     * 
     * @param currentNode     node being checked
     * @param currentNodeLeft left child of the {@code currentNode}
     * @return the updated left child after finfing and replacing the maximum
     *         element
     */
    private Node findMaxLeft(Node currentNode, Node currentNodeLeft) {
        if (currentNodeLeft.right == null) {
            currentNode.element = currentNodeLeft.element;
            currentNodeLeft = currentNodeLeft.left;
        } else
            currentNodeLeft.right = findMaxLeft(currentNode, currentNodeLeft.right);

        return currentNodeLeft;
    }

    /**
     * Performs a preorder traversal of the {@code BinarySearchTree}, visting the
     * {@code currentNode}, followed by its left subtree and then its right subtree.
     * 
     * @param currentNode node being visited
     */
    public void preOrderTraversal(Node currentNode) {
        if (currentNode != null) {
            System.out.println(currentNode.element + " ");
            preOrderTraversal(currentNode.left);
            preOrderTraversal(currentNode.right);
        }
    }

    /**
     * Performs a inorder traversal of the {@code BinarySearchTree}, visiting the
     * left subtree, followed by the {@code currentNode} and then its right subtree.
     * 
     * @param currentNode node being visited
     */
    public void inOrderTraversal(Node currentNode) {
        if (currentNode != null) {
            inOrderTraversal(currentNode.left);
            System.out.println(currentNode.element + " ");
            inOrderTraversal(currentNode.right);
        }
    }

    /**
     * Performs a postorder traversal of the {@code BinarySearchTree}, visiting the
     * left subtree, followed by the right subtree and then the {@code currentNode}.
     * 
     * @param currentNode node being visited
     */
    public void postOrderTraversal(Node currentNode) {
        if (currentNode != null) {
            postOrderTraversal(currentNode.left);
            postOrderTraversal(currentNode.right);
            System.out.println(currentNode.element + " ");
        }
    }
}
