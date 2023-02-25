package illgirni.ds.ptde.pc.saveviewer.savemanager.workspace;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Container for the "root" groups (virtual "folders") in which the exported characters are
 * organized.
 * 
 * @author illgirni
 *
 */
@JacksonXmlRootElement(localName = "exportedSlotType")
@JsonPropertyOrder({"slotGroups"})
// @XmlAccessorType(XmlAccessType.NONE)
public class ExportedSlots {

  /**
   * The root groups.
   */
  @JacksonXmlProperty(localName = "slotGroups")
  private List<ExportedSlotGroup> slotGroups = new ArrayList<>();

  /**
   * The root groups.
   */
  public List<ExportedSlotGroup> getSlotGroups() {
    return slotGroups;
  }

  /**
   * @see #getSlotGroups()
   */
  public void setSlotGroups(List<ExportedSlotGroup> slotGroups) {
    this.slotGroups = slotGroups;
  }

}
