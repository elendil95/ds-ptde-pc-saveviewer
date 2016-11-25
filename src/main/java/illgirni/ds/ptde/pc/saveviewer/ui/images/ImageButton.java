package illgirni.ds.ptde.pc.saveviewer.ui.images;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * A button represented as an icon. Loads the icon image from the {@link ImageRegistry}.
 * 
 * @author illgirni
 *
 */
public class ImageButton extends Button {
    
    /**
     * The normal style ("unclicked") style of the button.
     */
    private static final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
    
    /**
     * The clicked style of the button. Makes it, so that the icon moves like a pressed button.
     */
    private static final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";
    
    /**
     * The size of the icon.
     */
    private static final int ICON_SIZE = 26;
    
    /**
     * @param image The key of the image in the {@link ImageRegistry}.
     */
    public ImageButton(ImageKey image) {
        this(image, ICON_SIZE);
    }
    
    /**
     * @param image The key of the image in the {@link ImageRegistry}.
     * @param iconSize The icon size.
     */
    public ImageButton(final ImageKey image, final int iconSize) {
        final ImageView icon = new ImageView(ImageRegistry.getImage(image));
        
        icon.setFitHeight(iconSize);
        icon.setFitWidth(iconSize);
        setMaxWidth(iconSize);
        setMinWidth(iconSize);
        setMaxHeight(iconSize);
        setMinHeight(iconSize);
        
        setGraphic(icon);
        setStyle(STYLE_NORMAL);
        setCursor(Cursor.HAND);
        
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(STYLE_PRESSED);
            }            
        });
        
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               setStyle(STYLE_NORMAL);
            }
        });
        
    }
    
}