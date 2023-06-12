package trees.retrievalTree.trieBinaryTree;

public class NodeTrie {

    protected char element;
    protected NodeBTree root;
    protected boolean leaf;

    public NodeTrie() {
        this(' ');
    }

    public NodeTrie(char in) {
        this.element = in;
        root = null;
        leaf = false;
    }

    public NodeTrie insert(char in) throws Exception {
        root = insert(in, root);
        return search(in);
    }

    private NodeBTree insert(char in, NodeBTree currentNode) throws Exception {
        if (currentNode == null)
            currentNode = new NodeBTree(in);
        else if (in < currentNode.element)
            currentNode.left = insert(in, currentNode.left);
        else if (in > currentNode.element)
            currentNode.right = insert(in, currentNode.right);
        else
            throw new Exception("Error when inserting!");

        return currentNode;
    }

    public NodeTrie search(char key) {
        return search(key, root);
    }

    private NodeTrie search(int key, NodeBTree currentNode) {
        NodeTrie answer;

        if (currentNode == null)
            answer = null;
        else if (key == currentNode.element)
            answer = currentNode.node;
        else if (key < currentNode.element)
            answer = search(key, currentNode.left);
        else
            answer = search(key, currentNode.right);

        return answer;
    }

    public void setLeaf(char in) {
        setLeaf(in, root);
    }

    private void setLeaf(char in, NodeBTree currentNode) {
        if (currentNode == null)
            return;
        else if (in == currentNode.element)
            currentNode.node.leaf = true;
        else if (in < currentNode.element)
            setLeaf(in, currentNode.left);
        else
            setLeaf(in, currentNode.right);
    }

    public int getTotalNodes() {
        return getTotalNodes(root);
    }

    private int getTotalNodes(NodeBTree currentNode) {
        int answer = 0;

        if (currentNode != null)
            answer = 1 + getTotalNodes(currentNode.left) + getTotalNodes(currentNode.right);

        return answer;
    }

    public NodeTrie[] getSon() {
        int totalNodes = getTotalNodes();
        NodeTrie[] array = new NodeTrie[totalNodes];

        getSon(array, 0, root);

        return array;
    }

    private int getSon(NodeTrie[] array, int pos, NodeBTree currentNode) {
        if (currentNode != null) {
            array[pos++] = currentNode.node;
            pos = getSon(array, pos, currentNode.left);
            pos = getSon(array, pos, currentNode.right);
        }

        return pos;
    }

}