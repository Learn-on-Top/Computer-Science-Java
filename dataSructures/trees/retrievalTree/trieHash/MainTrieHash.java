package trees.retrievalTree.trieHash;

public class MainTrieHash {

    public static void main(String[] args) throws Exception {
        String[] array = { "Ator", "Atriz", "Cientista", "Médico", "Chef", "Dentista", "Cientista", "Boxeador", "Chef",
                "Padeiro" };

        RetrievalTree first = new RetrievalTree(), second = new RetrievalTree();
        RetrievalTree merged = new RetrievalTree(), intersected = new RetrievalTree();

        for (int index = 0; index < array.length; index++) {
            if (index < (array.length / 2))
                first.insert(array[index]);
            else
                second.insert(array[index]);
        }

        System.out.println("Árvore 1:");
        first.print();
        System.out.println();

        System.out.println("Árvore 2:");
        second.print();
        System.out.println();

        intersected.root = intersected.intersect(first.root, second.root);

        System.out.println("Árvore de Interseção");
        intersected.print();
        System.out.println();

        merged.root = merged.merge(first.root, second.root);
        System.out.println("Árvore Mista:");
        merged.print();
        System.out.println();

        System.out.println(
                "Cientista está presente em intersected? " + (intersected.search("Cientista") ? "Sim" : "Não"));
        System.out.println(
                "Boxeador está presente em intersected? " + (intersected.search("Boxeador") ? "Sim" : "Não"));

    }
}
