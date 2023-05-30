package trees.redBlackTree;

/**
 * The class represents a Red-Black tree, which is a self-balancing binary
 * search tree. Each node stores an element and maintains references to its left
 * and right child nodes. The color of the node is used for balancing operations
 * in the Red-Black tree.
 * 
 * @author raickmiranda
 * @summary Represents a Red-Black tree data structure.
 * @version 1.0
 */

public class RedBlackTree {

    private NodeRB root;

    /**
     * Constructs an empty {@code RedBlackTree}.
     */
    RedBlackTree() {
        root = null;
    }

    /**
     * Searches for the specified key in the {@code RedBlackTree}.
     * 
     * @param key to be searched
     * @return true if the key is found in the tree, false otherwise
     */
    public boolean search(int key) {
        return search(key, root);
    }

    /**
     * Helper method to search for the specified key in the {@code RedBlackTree}.
     * 
     * @param key         to be searched
     * @param currentNode node being considered
     * @return true if the key is found in the tree, false otherwise
     */
    private boolean search(int key, NodeRB currentNode) {
        boolean answer;

        if (currentNode == null)
            answer = false;
        else if (key == currentNode.element)
            answer = false;
        else if (key < currentNode.element)
            answer = search(key, currentNode.left);
        else
            answer = search(key, currentNode.right);

        return answer;
    }

    /**
     * Inserts a new element into the {@code RedBlackTree}
     * 
     * @param in element to be inserted
     * @throws Exception if the specified element is already present in the tree
     */
    public void insert(int in) throws Exception {
        if (root == null)
            root = new NodeRB(in);
        else if (root.left == null && root.right == null) {
            if (in < root.element)
                root.left = new NodeRB(in);
            else
                root.right = new NodeRB(in);
        } else if (root.left == null) {
            if (in < root.element)
                root.left = new NodeRB(in);
            else if (in < root.right.element) {
                root.left = new NodeRB(root.element);
                root.element = in;
            } else {
                root.left = new NodeRB(root.element);
                root.element = root.right.element;
                root.right.element = in;
            }
            root.left.color = root.right.color = false;
        } else if (root.right == null) {
            if (in > root.element)
                root.right = new NodeRB(in);
            else if (in > root.left.element) {
                root.right = new NodeRB(root.element);
                root.element = in;
            } else {
                root.right = new NodeRB(root.element);
                root.element = root.left.element;
                root.left.element = in;
            }
            root.left.color = root.right.color = false;
        } else
            insert(in, null, null, null, root);

        root.color = false;
    }

    /**
     * Inserts a new element into the {@code RedBlackTree} and performs the
     * necessary balancing operations.
     * 
     * @param in               element to be inserted
     * @param greatGrandParent node of the {@code currentNode}
     * @param grandParent      node of the {@code currentNode}
     * @param parent           node of the {@code currentNode}
     * @param currentNode      node being examined
     * @throws Exception if the specified element is already present in the tree
     */
    private void insert(int in, NodeRB greatGrandParent, NodeRB grandParent, NodeRB parent, NodeRB currentNode)
            throws Exception {
        if (currentNode == null) {
            if (in < parent.element)
                currentNode = parent.left = new NodeRB(in, true);
            else
                currentNode = parent.right = new NodeRB(in, true);

            if (parent.color == true)
                rebalance(greatGrandParent, grandParent, parent, currentNode);
        } else {
            if (currentNode.left != null && currentNode.right != null && currentNode.left.color == true
                    && currentNode.right.color == true) {
                currentNode.color = true;
                currentNode.left.color = currentNode.right.color = false;

                if (currentNode == root)
                    currentNode.color = false;
                else if (parent.color == true)
                    rebalance(greatGrandParent, grandParent, parent, currentNode);
            }

            if (in < currentNode.element)
                insert(in, grandParent, parent, currentNode, currentNode.left);
            else if (in > currentNode.element)
                insert(in, grandParent, parent, currentNode, currentNode.right);
            else
                throw new Exception("Error! The specified element is already present in the tree.");
        }
    }

    /**
     * Rebalances the {@code RedBlackTree}, ensuring that the tree maintains its
     * properties.
     * 
     * @param greatGrandParent node of the {@code currentNode}
     * @param grandParent      node of the {@code currentNode}
     * @param parent           node of the {@code currentNode}
     * @param currentNode      node being rebalanced
     */
    private void rebalance(NodeRB greatGrandParent, NodeRB grandParent, NodeRB parent, NodeRB currentNode) {
        if (parent.color == true) {
            if (parent.element > grandParent.element) {
                if (currentNode.element > parent.element)
                    grandParent = leftRotation(grandParent);
                else
                    grandParent = rightLeftRotation(grandParent);
            } else {
                if (currentNode.element < parent.element)
                    greatGrandParent = rightRotation(grandParent);
                else
                    grandParent = leftRightRotation(grandParent);
            }

            if (greatGrandParent == null)
                root = grandParent;
            else if (grandParent.element < greatGrandParent.element)
                greatGrandParent.left = grandParent;
            else
                greatGrandParent.right = grandParent;

            grandParent.color = false;
            grandParent.left.color = grandParent.right.color = true;
        }
    }

    /**
     * Performs a left rotation on the specified node in the {@code RedBlackTree}.
     * 
     * @param node to be rotated
     * @return the new root of the rotated subtree
     */
    private NodeRB leftRotation(NodeRB node) {
        NodeRB nodeRight = node.right;
        NodeRB nodeRightLeft = nodeRight.left;

        nodeRight.left = node;
        node.right = nodeRightLeft;

        return nodeRight;
    }

    /**
     * Performs a right rotation on the specified node in the {@code RedBlackTree}.
     * 
     * @param node to be rotated
     * @return the new root of the rotated subtree
     */
    private NodeRB rightRotation(NodeRB node) {
        NodeRB nodeLeft = node.left;
        NodeRB nodeLeftRight = nodeLeft.right;

        nodeLeft.right = node;
        node.left = nodeLeftRight;

        return nodeLeft;
    }

    /**
     * Performs a left-right rotation on the specified node in the
     * {@code RedBlackTree}.
     * 
     * @param node to be rotated
     * @return the new root of the rotated subtree
     */
    private NodeRB leftRightRotation(NodeRB node) {
        node.right = rightRotation(node.right);

        return leftRotation(node);
    }

    /**
     * Performs a right-left rotation on the specified node in the
     * {@code RedBlackTree}.
     * 
     * @param node to be rotated
     * @return the new root of the rotated subtree
     */
    private NodeRB rightLeftRotation(NodeRB node) {
        node.left = leftRotation(node.left);

        return rightRotation(node);
    }

    /**
     * Performs a pre-order traversal of the {@code RedBlackTree}, visiting each
     * node in a specific order.
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * Helper method for performing a pre-order traversal of the
     * {@code RedBlackTree}.
     * 
     * @param currentNode node being visited
     * @implNote (r) represents a red node and (b) represents a black node
     */
    private void preOrderTraversal(NodeRB currentNode) {
        if (currentNode != null) {
            System.out.println(currentNode.element + (currentNode.color ? " -> (r) " : " -> (b) "));
            preOrderTraversal(currentNode.left);
            preOrderTraversal(currentNode.right);
        }
    }

    /**
     * Performs an in-order traversal of the {@code RedBlackTree}, visiting each
     * node in ascending order of their keys.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Helper method for performing an in-order traversal of the
     * {@code RedBlackTree}.
     * 
     * @param currentNode node being visited
     * @implNote (r) represents a red node and (b) represents a black node
     */
    private void inOrderTraversal(NodeRB currentNode) {
        if (currentNode != null) {
            inOrderTraversal(currentNode.left);
            System.out.println(currentNode.element + (currentNode.color ? " -> (r) " : " -> (b) "));
            inOrderTraversal(currentNode.right);
        }
    }

    /**
     * Performs a post-order traversal of the {@code RedBlackTree}, visiting each
     * node in a specific order.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * Helper method for performing a post-order traversal of the
     * {@code RedBlackTree}.
     * 
     * @param currentNode node being visited
     * @implNote (r) represents a red node and (b) represents a black node
     */
    private void postOrderTraversal(NodeRB currentNode) {
        if (currentNode != null) {
            postOrderTraversal(currentNode.left);
            postOrderTraversal(currentNode.right);
            System.out.println(currentNode.element + (currentNode.color ? " -> (r) " : " -> (b) "));
        }
    }

}