package trees.retrievalTree.trieHash;

public class NodeTrie {

    protected char element;
    protected final int length = 255;
    protected NodeTrie[] next;
    protected boolean leaf;

    public NodeTrie() {
        this(' ');
    }

    public NodeTrie(char in) {
        this.element = in;
        next = new NodeTrie[length];
        leaf = false;

        for (int index = 0; index < length; index++)
            next[index] = null;
    }

    protected static int hash(char in) {
        return (int) in;
    }

}
