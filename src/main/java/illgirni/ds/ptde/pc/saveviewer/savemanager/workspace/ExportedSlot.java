package illgirni.ds.ptde.pc.saveviewer.savemanager.workspace;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveSlot;

/**
 * Representation of an exported character / save slot. This is basically a named container
 * for the file name of the exported slot.
 * 
 * @author illgirni
 *
 */

@JacksonXmlRootElement(localName = "exportedSlotType")
@JsonPropertyOrder({"file", "loadScreen", "name"})
// @XmlAccessorType(XmlAccessType.NONE)
public class ExportedSlot implements Comparable<ExportedSlot> {
    
    /**
     * The custom name for the slot.
     */
    @JacksonXmlProperty(localName = "name")
    private String name;
    
    /**
     * Optional description (unused).
     */
    @JacksonXmlProperty(localName = "description")
    private String description;
    
    /**
     * Name of the file containing the exported slot.
     */

    @JacksonXmlProperty(localName = "file")
    private String file;
    
    /**
     * Name of the file containing the data shown on the character loading screen.
     */

    @JacksonXmlProperty(localName = "loadscreenfile")
    private String loadScreenFile;
    
    /**
     * The slot data loaded from the file.
     */

    @JacksonXmlProperty(localName = "slot")
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
     * Name of the file containing the data shown on the character loading screen.
     */
    public String getLoadScreenFile() {
        return loadScreenFile;
    }
    
    /**
     * @see #getLoadScreenFile()
     */
    public void setLoadScreenFile(String loadScreenFile) {
        this.loadScreenFile = loadScreenFile;
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
