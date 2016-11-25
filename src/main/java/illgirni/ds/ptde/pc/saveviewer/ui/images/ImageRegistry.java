package illgirni.ds.ptde.pc.saveviewer.ui.images;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.scene.image.Image;

/**
 * Registry/cache of images. Allows static access to images without knowing the file name
 * and using {@link ImageKey} instead. Loads each image only once (upon first request) and 
 * then retrieves them from a cache on subsequent requests.
 * 
 * @author illgirni
 *
 */
public class ImageRegistry {
    
    /**
     * The image cache.
     */
    private static final Map<ImageKey, Image> IMAGES = new ConcurrentHashMap<>();
    
    private ImageRegistry() {
    }
    
    /**
     * Gets the image for the key.
     * 
     * @param key The key.
     * @return The image.
     */
    public static Image getImage(final ImageKey key) {
        if (!IMAGES.containsKey(key)) {
            IMAGES.put(key, new Image(ImageRegistry.class.getResourceAsStream(key.getFileName())));
        }
        
        return IMAGES.get(key);
    }
    
}
