package illgirni.ds.ptde.pc.saveviewer.savemanager.workspace;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * A named container. Can contain other elements of this type (as "sub-groups") and 
 * exported characters (non-empty save slots).
 * 
 * @author illgirni
 *
 */
@JacksonXmlRootElement(localName = "exportedSlotGroupType")
@JsonPropertyOrder({"name", "slotGroups", "slots"})
// @XmlAccessorType(XmlAccessType.NONE)
public class ExportedSlotGroup implements Comparable<ExportedSlotGroup> {
    
    /**
     * The group name.
     */

    @JacksonXmlProperty(localName = "name")
    private String name;
    
    /**
     * The sub-groups.
     */

    @JacksonXmlProperty(localName = "slotGroup")
    private List<ExportedSlotGroup> slotGroups = new ArrayList<>();
    
    /**
     * The exported characters / save slots in this group.
     */

    @JacksonXmlProperty(localName = "slot")
    private List<ExportedSlot> slots = new ArrayList<>();
    
    /**
     * The group name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * @see #getName()
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The sub-groups.
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
    
    /**
     * The exported characters / save slots in this group.
     */
    public List<ExportedSlot> getSlots() {
        return slots;
    }
    
    /**
     * @see #getSlots()
     */
    public void setSlots(List<ExportedSlot> slots) {
        this.slots = slots;
    }
    
    /**
     * Compares to the other group by group name.
     */
    @Override
    public int compareTo(ExportedSlotGroup otherGroup) {
        if (this.name == null && otherGroup.name == null) {
            return 0;
        }
        
        if (this.name == null && otherGroup.name != null) {
            return -1;
        }
        
        if (this.name != null && otherGroup.name == null) {
            return 1;
        }
        
        return this.name.compareTo(otherGroup.name);
    }
}
