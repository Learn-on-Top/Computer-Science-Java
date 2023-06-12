package trees.binarySearchTree;

public class MainBST {
    public static void main(String[] args) throws Exception {
        BinarySearchTree test = new BinarySearchTree();

        test.insert(15);
        test.insert(7);
        test.insert(13);
        test.insert(1);
        test.insert(6);
        test.insert(21);
        test.insert(22);
        test.insert(30);
        test.insert(0);
        test.insert(18);
        test.insert(17);
        test.insert(2);

        test.preOrderTraversal();
        System.out.println("\n");
        test.inOrderTraversal();
        System.out.println("\n");
        test.postOrderTraversal();
        System.out.println("\n");

        test.remove(21);
        test.remove(6);
        test.remove(0);
        test.remove(1);
        test.remove(15);

    }
}
