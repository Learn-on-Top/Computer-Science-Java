package trees.avlTree;

/**
 * The AvlTree class represents an AVL tree, which is a self-balancing binary 
 * search tree. It maintains a reference to the root node of the AVL tree.
 * 
 * @author raickmiranda
 * @summary Represents an AVL tree data structure.
 * @version 1.0
 */

import trees.EmptyTreeException;

public class AvlTree {

    private NodeAvl root;

    /**
     * Constructs an empty AVL tree.
     */
    AvlTree() {
        root = null;
    }

    /**
     * Searches for a specified key in the {@code AvlTree}.
     * 
     * @param key to be searched
     * @return true if the key is found in the tree, false otherwise
     */
    public boolean search(int key) {
        return search(key, root);
    }

    /**
     * Recursively searches for a specified key in the {@code AvlTree}.
     * 
     * @param key         to be searched
     * @param currentNode node being considered
     * @return true if the key is found in the AVL tree, false otherwise.
     */
    private boolean search(int key, NodeAvl currentNode) {
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
     * Inserts a specified element into the {@code AvlTree}.
     * 
     * @param in element to be inserted
     * @throws Exception if the specified element is already present in the tree
     */
    public void insert(int in) throws Exception {
        root = insert(in, root);
    }

    /**
     * Recursively inserts a specified element into the {@code AvlTree}.
     * 
     * @param in          element to be inserted
     * @param currentNode node being examined
     * @return the updated node after the insertion
     * @throws Exception if the specified element is already present in the tree
     */
    private NodeAvl insert(int in, NodeAvl currentNode) throws Exception {
        if (currentNode == null)
            currentNode = new NodeAvl(in);
        else if (in < currentNode.element)
            currentNode.left = insert(in, currentNode.left);
        else if (in > currentNode.element)
            currentNode.right = insert(in, currentNode.right);
        else
            throw new Exception("Error! The specified element is already present in the tree.");

        return rebalance(currentNode);
    }

    /**
     * Removes the specified element from the {@code AvlTree}.
     * 
     * @param toBeRemoved element to be removed
     * @throws Exception if an error occurs during the removal process
     */
    public void remove(int toBeRemoved) throws Exception {
        root = remove(toBeRemoved, root);
    }

    /**
     * Helper method to remove the specified element from the {@code AvlTree}.
     * 
     * @param out         element to be removed
     * @param currentNode node being checked
     * @return updated node after removing the element
     * @throws Exception if an error occurs during the removal process
     */
    private NodeAvl remove(int out, NodeAvl currentNode) throws Exception {
        if (currentNode == null)
            throw new EmptyTreeException("Error! The AVL tree is empty.");
        else if (out < currentNode.element)
            currentNode.left = remove(out, currentNode.left);
        else if (out > currentNode.element)
            currentNode.right = remove(out, currentNode.right);
        else if (currentNode.right == null)
            currentNode = currentNode.left;
        else if (currentNode.left == null)
            currentNode = currentNode.right;
        else
            currentNode.left = findMaxLeft(currentNode, currentNode.left);

        return rebalance(currentNode);
    }

    /**
     * Finds the maximum element in the left subtree of a given node in an
     * {@code AvlTree}. This method is used during the removal operation to find the
     * predecessor of the node being removed.
     * 
     * @param currentNode from which to find the maximum element in the left subtree
     * @param currentMax  node found in the left subtree
     * @return the maximum node found in the left subtree
     */
    private NodeAvl findMaxLeft(NodeAvl currentNode, NodeAvl currentMax) {
        if (currentMax.right == null) {
            currentNode.element = currentMax.element;
            currentMax = currentMax.left;
        } else
            currentMax.right = findMaxLeft(currentNode, currentMax.right);

        return currentMax;
    }

    /**
     * Rebalances the {@code AvlTree} by performing rotations if necessary.
     * 
     * @param node to rebalance
     * @return the balanced node
     * @throws Exception if an error occurs during rebalancing
     */
    private NodeAvl rebalance(NodeAvl node) throws Exception {
        if (node != null) {
            int balanceFactor = NodeAvl.getLevel(node.right) - NodeAvl.getLevel(node.left);

            if (Math.abs(balanceFactor) <= 1)
                node.setLevel();
            else if (balanceFactor == 2) {
                int balanceFactorRightChild = NodeAvl.getLevel(node.right.right) - NodeAvl.getLevel(node.right.left);

                if (balanceFactorRightChild == -1)
                    node.right = rightRotation(node.right);

                node = leftRotation(node);
            } else if (balanceFactor == -2) {
                int balanceFactorLeftChild = NodeAvl.getLevel(node.left.right) - NodeAvl.getLevel(node.left.left);

                if (balanceFactorLeftChild == 1)
                    node.left = leftRotation(node.left);

                node = rightRotation(node);
            } else
                throw new Exception(
                        "Error in Node(" + node.element + ") with invalid balance factor (" + balanceFactor + ")!");
        }

        return node;
    }

    /**
     * Performs a left rotation on the specified node in the {@code RedBlackTree}.
     * 
     * @param node to be rotated
     * @return the new root of the rotated subtree
     */
    private NodeAvl leftRotation(NodeAvl node) {
        NodeAvl nodeRight = node.right;
        NodeAvl nodeRightLeft = nodeRight.left;

        nodeRight.left = node;
        node.right = nodeRightLeft;

        node.setLevel();
        nodeRight.setLevel();

        return nodeRight;
    }

    /**
     * Performs a right rotation on the specified node in the {@code RedBlackTree}.
     * 
     * @param node to be rotated
     * @return the new root of the rotated subtree
     */
    private NodeAvl rightRotation(NodeAvl node) {
        NodeAvl nodeLeft = node.left;
        NodeAvl nodeLeftRight = nodeLeft.right;

        nodeLeft.right = node;
        node.left = nodeLeftRight;

        node.setLevel();
        nodeLeft.setLevel();

        return nodeLeft;
    }

    /**
     * Performs a pre-order traversal of the {@code AvlTree}, visiting each
     * node in a specific order.
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * Helper method for performing a pre-order traversal of the
     * {@code AvlTree}.
     * 
     * @param currentNode node being visited
     */
    private void preOrderTraversal(NodeAvl currentNode) {
        if (currentNode != null) {
            System.out.println(currentNode.element + " ");
            preOrderTraversal(currentNode.left);
            preOrderTraversal(currentNode.right);
        }
    }

    /**
     * Performs an in-order traversal of the {@code AvlTree}, visiting each
     * node in ascending order of their keys.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Helper method for performing an in-order traversal of the
     * {@code AvlTree}.
     * 
     * @param currentNode node being visited
     */
    private void inOrderTraversal(NodeAvl currentNode) {
        if (currentNode != null) {
            inOrderTraversal(currentNode.left);
            System.out.println(currentNode.element + " ");
            inOrderTraversal(currentNode.right);
        }
    }

    /**
     * Performs a post-order traversal of the {@code AvlTree}, visiting each
     * node in a specific order.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * Helper method for performing a post-order traversal of the
     * {@code AvlTree}.
     * 
     * @param currentNode node being visited
     */
    private void postOrderTraversal(NodeAvl currentNode) {
        if (currentNode != null) {
            postOrderTraversal(currentNode.left);
            postOrderTraversal(currentNode.right);
            System.out.println(currentNode.element + " ");
        }
    }

}
