import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Node {
  private static final int missingPageCost = 3;
  private static final int pageCopyCost = 1;

  private final Node parent;

  @NonNull
  private int dawidPoints;
  @NonNull
  private int ewaPoints;

  private final int dawidAbsence;
  private final int ewaAbsence;

  private MNE mne;

  @Getter
  private List<Node> childNodes = new ArrayList<>();

  public List<Node> calculateChildNodes(int dawidPages, int ewaPages, boolean end) {


    Node d0e0 = new Node(
            this,
            dawidPoints - missingPageCost * dawidPages,
            ewaPoints - missingPageCost * ewaPages,
            dawidAbsence + 1,
            ewaAbsence + 1
    );
    Node d1e0 = new Node(
            this,
            dawidPoints + pageCopyCost * dawidPages,
            ewaPoints - pageCopyCost * dawidPages,
            dawidAbsence,
            ewaAbsence + 1
    );
    Node d0e1 = new Node(
            this,
            dawidPoints - pageCopyCost * ewaPages,
            ewaPoints + pageCopyCost * ewaPages,
            dawidAbsence + 1,
            ewaAbsence
    );
    Node d1e1 = new Node(
            this,
            dawidPoints,
            ewaPoints,
            dawidAbsence,
            ewaAbsence
    );
    childNodes = List.of(
            d1e1,
            d1e0,
            d0e1,
            d0e0
    );

    if (end) {
      for (Node n : childNodes) n.finishGame();
    }
    return childNodes;
  }

  public void calculateMne() {
    mne = MNE.create(childNodes.get(0), childNodes.get(1), childNodes.get(2), childNodes.get(3));
  }

  public double getDawidMNEPayoff() {
    if (mne == null) return dawidPoints;
    return mne.getDawidPayoff();
  }

  public double getEwaMNEPayoff() {
    if (mne == null) return ewaPoints;
    return mne.getEwaPayoff();
  }

  protected void finishGame() {
    if (dawidAbsence > ewaAbsence) {
      dawidPoints += 10;
      ewaPoints -= 10;
    }
    if (ewaAbsence > dawidAbsence) {
      ewaPoints += 10;
      dawidPoints -= 10;
    }
  }

  @Override
  public String toString() {
    if (mne != null)
      return "[ D(" + dawidAbsence + ", " + dawidPoints + "), E(" + ewaAbsence + ", " + ewaPoints
              + ") ] MNE(d: " + String.format("%.2f", mne.getDawidGoProbability()) + ", e: " + String.format("%.2f", mne.getEwaGoProbability()) + ")";
    else
      return "[ D(" + dawidAbsence + ", " + dawidPoints + "), E(" + ewaAbsence + ", " + ewaPoints
              + ") ]";
  }
}
