package trees.binarySearchTree;

/**
 * Represents a node in a binary tree. Each node stores an element and
 * references to its left and right child nodes.
 * 
 * @author raickmiranda
 * @summary Represents a node in a binary tree.
 * @version 1.0
 */

public class NodeBS {

    protected int element;
    protected NodeBS left, right;

    /**
     * Stores an element and references to the left and right child nodes.
     * 
     * @param element to be stored
     */
    NodeBS(int element) {
        this(element, null, null);
    }

    /**
     * Stores an element and references to the left and right child nodes.
     * 
     * @param element to be stored
     * @param left    child node reference
     * @param right   child node reference
     */
    NodeBS(int element, NodeBS left, NodeBS right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

}