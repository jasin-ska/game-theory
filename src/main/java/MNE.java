import lombok.Value;

import java.util.Optional;

@Value
public class MNE {
  double dawidGoProbability;
  double ewaGoProbability;

  double dawidPayoff;
  double ewaPayoff;

  public static MNE create(Node d1e1, Node d1e0, Node d0e1, Node d0e0) {
    Optional<MNE> dominated = checkForDominated(d1e1, d1e0, d0e1, d0e0);
    if (dominated.isPresent()) return dominated.get();

    double dawidGoProbability = (d0e1.getEwaMNEPayoff() - d0e0.getEwaMNEPayoff())
            / (d1e0.getEwaMNEPayoff() - d1e1.getEwaMNEPayoff() + d0e1.getEwaMNEPayoff() - d0e0.getEwaMNEPayoff());
    assert dawidGoProbability <= 1;
    assert dawidGoProbability >= 0;

    double ewaGoProbability = (d1e0.getDawidMNEPayoff() - d0e0.getDawidMNEPayoff())
            / (d0e1.getDawidMNEPayoff() - d1e1.getDawidMNEPayoff() + d1e0.getDawidMNEPayoff() - d0e0.getDawidMNEPayoff());
    assert ewaGoProbability <= 1;
    assert ewaGoProbability >= 0;

    // payoff

    double dawidPayoff = dawidGoProbability * ewaGoProbability * d1e1.getDawidMNEPayoff()
            + dawidGoProbability * (1 - ewaGoProbability) * d1e0.getDawidMNEPayoff()
            + (1 - dawidGoProbability) * ewaGoProbability * d0e1.getDawidMNEPayoff()
            + (1 - dawidGoProbability) * (1 - ewaGoProbability) * d0e0.getDawidMNEPayoff();

    double ewaPayoff = dawidGoProbability * ewaGoProbability * d1e1.getEwaMNEPayoff()
            + dawidGoProbability * (1 - ewaGoProbability) * d1e0.getEwaMNEPayoff()
            + (1 - dawidGoProbability) * ewaGoProbability * d0e1.getEwaMNEPayoff()
            + (1 - dawidGoProbability) * (1 - ewaGoProbability) * d0e0.getEwaMNEPayoff();

    return new MNE(
            dawidGoProbability,
            ewaGoProbability,
            dawidPayoff,
            ewaPayoff);
  }

  private static Optional<MNE> checkForDominated(Node d1e1, Node d1e0, Node d0e1, Node d0e0) {
    // dawid go dominates
    if (d1e1.getDawidMNEPayoff() > d0e1.getDawidMNEPayoff() && d1e0.getDawidMNEPayoff() > d0e0.getDawidMNEPayoff()) {
      if (d1e1.getEwaMNEPayoff() > d1e0.getEwaMNEPayoff())
        return Optional.of(new MNE(
                1.0, 1.0, d1e1.getDawidMNEPayoff(), d1e1.getEwaMNEPayoff()
        ));
      else
        return Optional.of(new MNE(
                1.0, 0.0, d1e0.getDawidMNEPayoff(), d1e0.getEwaMNEPayoff()
        ));
    }
    // dawid skip dominates
    if (d0e1.getDawidMNEPayoff() > d1e1.getDawidMNEPayoff() && d0e0.getDawidMNEPayoff() > d1e0.getDawidMNEPayoff()) {
      if (d0e1.getEwaMNEPayoff() > d0e0.getEwaMNEPayoff())
        return Optional.of(new MNE(
                1.0, 1.0, d0e1.getDawidMNEPayoff(), d0e1.getEwaMNEPayoff()
        ));
      else
        return Optional.of(new MNE(
                1.0, 0.0, d0e0.getDawidMNEPayoff(), d0e0.getEwaMNEPayoff()
        ));
    }
    // ewa go dominates
    if (d1e1.getEwaMNEPayoff() > d1e0.getEwaMNEPayoff() && d0e1.getEwaMNEPayoff() > d0e0.getEwaMNEPayoff()) {
      if (d1e1.getDawidMNEPayoff() > d0e1.getDawidMNEPayoff())
        return Optional.of(new MNE(
                1.0, 1.0, d1e1.getDawidMNEPayoff(), d1e1.getEwaMNEPayoff()
        ));
      else
        return Optional.of(new MNE(
                0.0, 1.0, d0e1.getDawidMNEPayoff(), d0e1.getEwaMNEPayoff()
        ));
    }
    // ewa skip dominates
    if (d1e0.getEwaMNEPayoff() > d1e1.getEwaMNEPayoff() && d0e0.getEwaMNEPayoff() > d0e1.getEwaMNEPayoff()) {
      if (d1e0.getDawidMNEPayoff() > d0e0.getDawidMNEPayoff())
        return Optional.of(new MNE(
                1.0, 0.0, d1e0.getDawidMNEPayoff(), d1e0.getEwaMNEPayoff()
        ));
      else
        return Optional.of(new MNE(
                0.0, 0.0, d0e0.getDawidMNEPayoff(), d0e0.getEwaMNEPayoff()
        ));
    }

    return Optional.empty();
  }

}
