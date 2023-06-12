package trees.retrievalTree.trieBinaryTree;

public class BinaryTrie {

    protected NodeTrie root;

    public BinaryTrie() {
        root = new NodeTrie();
    }

    public boolean search(String key) throws Exception {
        return search(key, root, 0);
    }

    private boolean search(String key, NodeTrie currentNode, int i) throws Exception {
        boolean answer;
        NodeTrie son = currentNode.search(key.charAt(i));

        if (son == null)
            answer = false;
        else if (i == key.length() - 1)
            answer = (son.leaf == true);
        else if (i < key.length() - 1)
            answer = search(key, son, i + 1);
        else
            throw new Exception("Error when searching!");

        return answer;
    }

    public void insert(String in) throws Exception {
        insert(in, root, 0);
    }

    private void insert(String in, NodeTrie currentNode, int i) throws Exception {
        NodeTrie son = currentNode.search(in.charAt(i));

        if (son == null) {
            son = currentNode.insert(in.charAt(i));

            if (i == in.length() - 1)
                currentNode.setLeaf(in.charAt(i));
            else
                insert(in, son, i + 1);
        } else if (son.leaf == false && i < in.length() - 1)
            insert(in, son, i + 1);
        else
            throw new Exception("Error when inserting!");
    }

    public void print() {
        print("", root);
    }

    private void print(String out, NodeTrie currentNode) {
        if (currentNode.leaf == true)
            System.out.println(out + currentNode.element);
        else {
            NodeTrie[] son = currentNode.getSon();

            for (int index = 0; index < son.length; index++)
                print(out + currentNode.element, son[index]);
        }
    }

}