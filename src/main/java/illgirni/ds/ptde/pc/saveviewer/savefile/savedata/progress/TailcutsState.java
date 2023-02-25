package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress;

import java.util.ArrayList;
import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.TailOwner;

/**
 * A "collection" of all the available tail cuts.
 * 
 * @author illgirni
 *
 */
public class TailcutsState {

  /**
   * The actual tail cut information collection.
   */
  private final List<TailcutState> tailcutStates = new ArrayList<>();

  /**
   * Adds a tail cute information to this "collection".
   */
  public void addTailcutState(final TailcutState tailcutState) {
    if (tailcutState != null) {
      tailcutStates.add(tailcutState);
    }
  }

  /**
   * Determines for the tail owner if the tail has been cut.
   * 
   * @param tailOwner The tail owner.
   * @return {@code true} when tail has been cut.
   */
  public boolean isCut(final TailOwner tailOwner) {
    for (final TailcutState tailcutState : tailcutStates) {
      if (tailcutState.getTailOwner() == tailOwner) {
        return tailcutState.isCut();
      }
    }

    return false;
  }

}
