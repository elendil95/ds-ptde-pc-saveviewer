package illgirni.ds.ptde.pc.saveviewer.savemanager.workspace;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;

/**
 * Representation of an exported character / save slot. This is basically a named container
 * for the file name of the exported slot.
 * 
 * @author illgirni
 *
 */
@XmlType(name = "exportedSlotType", propOrder = {"file", "name", "description"})
@XmlAccessorType(XmlAccessType.NONE)
public class ExportedSlot implements Comparable<ExportedSlot> {
    
    /**
     * The custom name for the slot.
     */
    @XmlElement(name = "name", required = true)
    private String name;
    
    /**
     * Optional description (unused).
     */
    @XmlElement(name = "description")
    private String description;
    
    /**
     * Name of the file containing the exported slot.
     */
    @XmlElement(name = "file", required = true)
    private String file;
    
    /**
     * The slot data loaded from the file.
     */
    @XmlTransient
    private SaveSlot slot;
    
    /**
     * The custom name for the slot.
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
     * Optional description (unused).
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @see #getDescription()
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Name of the file containing the exported slot.
     */
    public String getFile() {
        return file;
    }
    
    /**
     * @see #getFile()
     */
    public void setFile(String file) {
        this.file = file;
    }
    
    /**
     * The slot data loaded from the file.
     * 
     * @see #getFile()
     */
    public SaveSlot getSlot() {
        return slot;
    }
    
    /**
     * @see #getSlot()
     */
    public void setSlot(SaveSlot slot) {
        this.slot = slot;
    }
    
    /**
     * Compares to the other slot by name.
     */
    @Override
    public int compareTo(ExportedSlot otherSlot) {
        if (this.name == null && otherSlot.name == null) {
            return 0;
        }
        
        if (this.name == null && otherSlot.name != null) {
            return -1;
        }
        
        if (this.name != null && otherSlot.name == null) {
            return 1;
        }
        
        return this.name.compareTo(otherSlot.name);
    }
    
}
