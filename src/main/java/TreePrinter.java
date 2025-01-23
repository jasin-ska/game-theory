import java.util.List;

public class TreePrinter {

  private static void printTree(Node node, String prefix, boolean isLast) {
    // Wypisz bieżący węzeł
    System.out.print(prefix);
    System.out.print(isLast ? "└── " : "├── ");
    System.out.println(node.toString());

    // Dla wszystkich dzieci wywołaj rekurencję
    List<Node> children = node.getChildNodes();
    for (int i = 0; i < children.size(); i++) {
      boolean lastChild = (i == children.size() - 1);
      printTree(children.get(i), prefix + (isLast ? "    " : "│   "), lastChild);
    }
  }

  public static void printTree(Node root) {
    printTree(root, "", true);
  }
}