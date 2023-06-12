package trees.retrievalTree.trieBinaryTree;

public class NodeBTree {

    protected char element;
    protected NodeBTree left, right;
    protected NodeTrie node;

    public NodeBTree() {
        this.element = 0;
        this.left = this.right = null;
        this.node = null;
    }

    public NodeBTree(char in) {
        this.element = in;
        this.left = this.right = null;
        this.node = new NodeTrie(in);
    }

}