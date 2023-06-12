package trees.retrievalTree.trieHash;

public class RetrievalTree {

    protected NodeTrie root;

    public RetrievalTree() {
        root = new NodeTrie();
    }

    public boolean search(String key) throws Exception {
        return search(key, root, 0);
    }

    private boolean search(String key, NodeTrie currentNode, int i) throws Exception {
        boolean answer;

        if (currentNode.next[key.charAt(i)] == null)
            answer = false;
        else if (i == key.length() - 1)
            answer = (currentNode.next[key.charAt(i)].leaf == true);
        else if (i < key.length() - 1)
            answer = search(key, currentNode.next[key.charAt(i)], i + 1);
        else
            throw new Exception("Er");

        return answer;
    }

    public void insert(String in) throws Exception {
        insert(in, root, 0);
    }

    private void insert(String key, NodeTrie currentNode, int i) throws Exception {
        if (currentNode.next[key.charAt(i)] == null) {
            currentNode.next[key.charAt(i)] = new NodeTrie(key.charAt(i));

            if (i == key.length() - 1)
                currentNode.next[key.charAt(i)].leaf = true;
            else
                insert(key, currentNode.next[key.charAt(i)], i + 1);
        } else if (currentNode.next[key.charAt(i)].leaf == false && i < key.length() - 1)
            insert(key, currentNode.next[key.charAt(i)], i + 1);
        else
            throw new Exception("Error when inserting!");
    }

    public void print() {
        print("", root);
    }

    private void print(String out, NodeTrie currentNode) {
        if (currentNode.leaf == true)
            System.out.println(out + currentNode.element);
        else
            for (int index = 0; index < currentNode.next.length; index++)
                if (currentNode.next[index] != null)
                    print(out + currentNode.element, currentNode.next[index]);
    }

    public NodeTrie intersect(NodeTrie first, NodeTrie second) {
        if (first == null || second == null)
            return null;

        NodeTrie answer = new NodeTrie(first.element);
        answer.leaf = first.leaf && second.leaf;

        for (int index = 0; index < first.next.length; index++)
            if (first.next[index] != null && second.next[index] != null)
                answer.next[index] = intersect(first.next[index], second.next[index]);

        return answer;
    }

    public NodeTrie merge(NodeTrie first, NodeTrie second) {
        if (second == null)
            return first;

        if (first == null)
            first = new NodeTrie(second.element);

        first.leaf = first.leaf || second.leaf;

        for (int index = 0; index < second.next.length; index++) {
            if (second.next[index] != null)
                if (first.next[index] == null) {
                    if (!containsKey(second.next[index].element, first)) {
                        first.next[index] = new NodeTrie(second.next[index].element);
                        merge(first.next[index], second.next[index]);
                    }
                } else
                    merge(first.next[index], second.next[index]);
        }

        return first;
    }

    private boolean containsKey(char in, NodeTrie currentNode) {
        if (currentNode.element == in && currentNode.leaf)
            return true;

        for (int index = 0; index < currentNode.next.length; index++)
            if (currentNode.next[index] != null)
                if (containsKey(in, currentNode.next[index]))
                    return true;

        return false;
    }

    public boolean containsKey(String key, NodeTrie root) {
        NodeTrie currentNode = root;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            if (!containsKey(c, currentNode))
                return false;
            else {
                for (int index = 0; index < currentNode.next.length; index++)
                    if (currentNode.next[index] != null && currentNode.next[index].element == c) {
                        currentNode = currentNode.next[index];
                        break;
                    }
            }
        }

        return currentNode.leaf;
    }

}
