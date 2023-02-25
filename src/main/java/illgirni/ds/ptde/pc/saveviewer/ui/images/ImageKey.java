package illgirni.ds.ptde.pc.saveviewer.ui.images;

/**
 * The keys to retrieve images from the image registry.
 * 
 * @author ilgirni
 *
 */
public enum ImageKey {

  APPLICATION("application-icon.png"),

  RELOAD("reload.png"),

  MALE("male.png"),

  FEMALE("female.png"),

  FILE("file.png"),

  ADD_FILE("add_file.png"),

  EDIT_FILE("edit_file.png"),

  EXPORT_FILE("export_file.png"),

  IMPORT_FILE("import_file.png"),

  MOVE_FILE("move_file.png"),

  REMOVE_FILE("remove_file.png"),

  FOLDER("folder.png"),

  ADD_FOLDER("add_folder.png"),

  EDIT_FOLDER("edit_folder.png"),

  MOVE_FOLDER("move_folder.png"),

  REMOVE_FOLDER("remove_folder.png");

  /**
   * The file name of the image for the key.
   */
  private final String fileName;

  /**
   * @param fileName The file name of the image for the key.
   */
  private ImageKey(final String fileName) {
    this.fileName = fileName;
  }

  /**
   * The file name of the image for the key.
   */
  protected String getFileName() {
    return fileName;
  }

}
