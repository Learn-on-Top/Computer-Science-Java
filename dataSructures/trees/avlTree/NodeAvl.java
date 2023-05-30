package trees.avlTree;

/**
 * The Node class represents a node in an AVL tree, which is a self-balancing
 * binary search tree. Each node stores an element and maintains references to
 * its left and right child nodes. The node also keeps track of its height in
 * the AVL tree for balancing purposes.
 * 
 * @author raickmiranda
 * @summary Represents a node in an AVL tree.
 * @version 1.0
 */

public class NodeAvl {

    protected int element, level;
    protected NodeAvl left, right;

    /**
     * Creates a node with the given element and default level 1.
     * 
     * @param element to be stored
     */
    NodeAvl(int element) {
        this(element, null, null, 1);
    }

    /**
     * Creates a node with the given element, left and right children, and level.
     * 
     * @param element to be stored
     * @param left    child node
     * @param right   child node
     * @param level   number of levels below the node
     */
    NodeAvl(int element, NodeAvl left, NodeAvl right, int level) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.level = level;
    }

    /**
     * Calculates the number of levels below the current node and updates the level
     * variable. The level is determined by adding 1 to the maximum level between
     * the left and right child nodes.
     */
    protected void setLevel() {
        this.level = 1 + Math.max(getLevel(left), getLevel(right));
    }

    /**
     * Returns the number of levels below the given node.
     * 
     * @param node being considered
     * @return the number of levels below the given node
     */
    public static int getLevel(NodeAvl node) {
        return (node == null) ? 0 : node.level;
    }

}