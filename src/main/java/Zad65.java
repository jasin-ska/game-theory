import java.util.Collection;
import java.util.List;

public class Zad65 {

  public static void main(String[] args) {

    Node root = new Node(null, 0, 0, 0, 0);

    List<Node> firstLectureStates = root.calculateChildNodes(4, 4, false);

    List<Node> secondLectureStates = firstLectureStates.stream()
            .map(n -> n.calculateChildNodes(3, 3, false))
            .flatMap(Collection::stream)
            .toList();

    List<Node> thirdLectureStates = secondLectureStates.stream()
            .map(n -> n.calculateChildNodes(5, 6, true))
            .flatMap(Collection::stream)
            .toList();

    for (Node n : secondLectureStates) {
      n.calculateMne();
    }
    for (Node n : firstLectureStates) {
      n.calculateMne();
    }
    root.calculateMne();

    TreePrinter.printTree(root);
  }
}
