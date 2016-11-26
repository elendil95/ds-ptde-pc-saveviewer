package illgirni.ds.ptde.pc.saveviewer.savefile.savedata;

/**
 * One of the save slots in the save file. Can be empty (i.e. has no character information).
 * 
 * @author illgirni
 *
 */
public class SaveSlot {
    
    /**
     * The actual bytes from the save file, which represent this save slot.
     */
    private ByteBlock data;
    
    /**
     * The actual bytes from the save file, which contain the data displayed 
     * on the loading screen.
     */
    private ByteBlock loadScreenData;
    
    /**
     * The slot index.
     */
    private int index;
    
    /**
     * The slot name.
     */
    private String name;
    
    /**
     * The slot checksum.
     */
    private byte[] checkSum;
    
    /**
     * The length of the slot's content 8character information) in bytes.
     */
    private int contentLength;
    
    /**
     * The actual slot content.
     */
    private SaveSlotContent content;
    
    /**
     * If this slot contains illegal data.
     */
    private boolean erroneous = false;
    
    /**
     * We treat the slot as empty when it has no content or only empty content.
     * 
     * @see SaveSlotContent#isEmpty()
     */
    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }
    
    /**
     * The slot index.
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * @see #getIndex()
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * The slot name.
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
     * The slot checksum.
     */
    public byte[] getCheckSum() {
        return checkSum;
    }
    
    /**
     * @see #getCheckSum()
     */
    public void setCheckSum(byte[] checkSum) {
        this.checkSum = checkSum;
    }
    
    /**
     * The length of the slot's content 8character information) in bytes.
     */
    public int getContentLength() {
        return contentLength;
    }
    
    /**
     * @see #getContentLength()
     */
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
    
    /**
     * The actual slot content.
     */
    public SaveSlotContent getContent() {
        return content;
    }
    
    /**
     * @see #getContent()
     */
    public void setContent(SaveSlotContent content) {
        this.content = content;
    }
    
    /**
     * The actual bytes from the save file, which represent this save slot.
     */
    public ByteBlock getData() {
        return data;
    }
    
    /**
     * @see #getData()
     */
    public void setData(ByteBlock slotData) {
        this.data = slotData;
    }
    
    /**
     * The actual bytes from the save file, which contain the data displayed 
     * on the loading screen.
     */
    public ByteBlock getLoadScreenData() {
        return loadScreenData;
    }
    
    /**
     * @see #getLoadScreenData()
     */
    public void setLoadScreenData(ByteBlock loadScreenData) {
        this.loadScreenData = loadScreenData;
    }
    
    /**
     * If this slot contains illegal data.
     */
    public boolean isErroneous() {
        return erroneous;
    }
    
    /**
     * @see #isErroneous()
     */
    public void setErroneous(boolean erroneous) {
        this.erroneous = erroneous;
    }
}
