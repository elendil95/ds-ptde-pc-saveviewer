package illgirni.ds.ptde.pc.saveviewer.savemanager.workspace;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Container for the "root" groups (virtual "folders") in which the exported characters 
 * are organized.
 * 
 * @author illgirni
 *
 */
@XmlType(name = "exportedSlotsType", propOrder = {"slotGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class ExportedSlots {
    
    /**
     * The root groups.
     */
    @XmlElement(name = "slotGroup")
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
