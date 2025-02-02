package illgirni.ds.ptde.pc.saveviewer.savemanager.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.SaveFile;

/**
 * Container for the information about the save file to load in the save viewer and exported
 * characters. Corresponds to an XML file containing all the information - hence the XML
 * annotations.
 * 
 * @author illgirni
 *
 */
@JacksonXmlRootElement(localName = "saveWorkspace")
@JsonPropertyOrder({"saveFilePath", "exportedSlots"})
// @XmlAccessorType(XmlAccessType.NONE)
public class SaveWorkspace {

  /**
   * The path to the save file to load.
   */
  @JacksonXmlProperty(localName = "saveFilePath")
  private String saveFilePath;

  /**
   * The "root" for the groups (virtual "folders") in which the exported characters are organized.
   */
  @JacksonXmlProperty(localName = "exportedSlots")
  private ExportedSlots exportedSlots = new ExportedSlots();

  /**
   * The save file loaded from the {@link #saveFilePath}.
   */
  @JsonIgnore
  private SaveFile saveFile;

  /**
   * The path to the save file to load.
   */
  public String getSaveFilePath() {
    return saveFilePath;
  }

  /**
   * @see #getSaveFilePath()
   */
  public void setSaveFilePath(String saveFile) {
    this.saveFilePath = saveFile;
  }

  /**
   * The "root" for the groups (virtual "folders") in which the exported characters are organized.
   */
  public ExportedSlots getExportedSlots() {
    return exportedSlots;
  }

  /**
   * @see #getExportedSlots()
   */
  public void setExportedSlots(ExportedSlots exportedSlots) {
    this.exportedSlots = exportedSlots;
  }

  /**
   * The save file loaded from the {@link #getSaveFilePath()}.
   */
  public SaveFile getSaveFile() {
    return saveFile;
  }

  /**
   * @see #getSaveFile()
   */
  public void setSaveFile(SaveFile saveFile) {
    this.saveFile = saveFile;
  }
}
