package trees.redBlackTree;

/**
 * The class represents a node in a Red-Black tree, which is a self-balancing
 * binary search tree. Each node stores an element and maintains references to
 * its left and right child nodes. The color of the node is used for balancing
 * operations in the Red-Black tree.
 * 
 * @author raickmiranda
 * @summary Represents a node in a Red-Black tree.
 * @version 1.0
 */

public class NodeRB {

    protected boolean color;
    protected int element;
    protected NodeRB left, right;

    /**
     * Creates a new node with no element assigned.
     */
    NodeRB() {
        this(-1);
    }

    /**
     * Creates a new node with the specified element.
     * 
     * @param element to be stored
     */
    NodeRB(int element) {
        this(element, false, null, null);
    }

    /**
     * Creates a new node with the specified element and color.
     * 
     * @param element to be stored
     * @param color   of the node
     */
    NodeRB(int element, boolean color) {
        this(element, color, null, null);
    }

    /**
     * Creates a new node with the specified element, color, left child, and right
     * child.
     * 
     * @param element to be stored
     * @param color   of the node
     * @param left    child of the node
     * @param right   child of the node
     */
    NodeRB(int element, boolean color, NodeRB left, NodeRB right) {
        this.color = color;
        this.element = element;
        this.left = left;
        this.right = right;
    }

}