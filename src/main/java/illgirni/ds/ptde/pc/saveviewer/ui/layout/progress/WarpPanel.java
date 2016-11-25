package illgirni.ds.ptde.pc.saveviewer.ui.layout.progress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.WarpState;
import illgirni.ds.ptde.pc.saveviewer.ui.i18n.Messages;
import illgirni.ds.ptde.pc.saveviewer.ui.layout.AbstractDetailsPanel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * A details panel which shows the warp points. For layout reasons they are split in four groups, 
 * which are aligned as a grid with two columns. Each list entry consists of label-value-pairs.
 * The order of the warp points corresponds to the discovery order in a "usual playthrough".
 * <p/>
 * Also shows, if warping is unlocked at all. 
 * 
 * @author illgirni
 *
 */
public class WarpPanel extends AbstractDetailsPanel {
    
    /**
     * The text for each warp point showing if it is unlocked.
     */
    private Map<Bonfire, Text> warpPoints;
    
    /**
     * The text showing if warping is unlocked.
     */
    private Text warpUnlocked;
    
    /** {@inheritDoc} */
    @Override
    protected String getTitle() {
        return "Warp Points";
    }
    
    /**
     * Adds the four warp point blocks and the warping unlocked text to the container.
     */
    @Override
    protected void fillContentGrid(GridPane contentContainer) {
        warpPoints = new HashMap<>();
        
        int row = 0;
        
        warpUnlocked = new Text();
        warpUnlocked.setStyle("-fx-font-weight: bold");
        GridPane.setColumnSpan(warpUnlocked, 4);
        GridPane.setHalignment(warpUnlocked, HPos.CENTER);
        GridPane.setHgrow(warpUnlocked, Priority.NEVER);
        contentContainer.add(warpUnlocked, 0, row++);
        
        contentContainer.add(new Text(""), 0, row++);
        
        int nextRow = createBlock1(contentContainer, 0, row);
        int nextRow2 = createBlock2(contentContainer, 2, row);
        
        nextRow = Integer.max(nextRow, nextRow2);
        contentContainer.add(new Text(""), 0, nextRow++);
        
        createBlock3(contentContainer, 0, nextRow);
        createBlock4(contentContainer, 2, nextRow);
        
    }
    
    /**
     * Adds the warp points block 1 to the container.
     * 
     * @param warpPointsContainer The container
     * @param column The column for the cells.
     * @param row The row of the first cell.
     * @return The index of the next free row in the container.
     */
    private int createBlock1(final GridPane warpPointsContainer, final int column, int row) {
        List<Bonfire> warpPointsBlock = Arrays.asList(Bonfire.FIRELINK_SHRINE,
                                                      Bonfire.SUNLIGHT_ALTAR,
                                                      Bonfire.UNDEAD_PARISH,
                                                      Bonfire.DEPTHS,
                                                      Bonfire.DAUGHTER_OF_CHAOS);
        
        createBlock(warpPointsContainer, column, row, warpPointsBlock);
        
        return row + warpPointsBlock.size();

    }
    
    /**
     * Adds the warp points block 2 to the container.
     * 
     * @param warpPointsContainer The container
     * @param column The column for the cells.
     * @param row The row of the first cell.
     * @return The index of the next free row in the container.
     */
    private int createBlock2(GridPane warpPointsContainer, int column, int row) {
        List<Bonfire> warpPointsBlock = Arrays.asList(Bonfire.STONE_DRAGON,
                                                      Bonfire.ANOR_LONDO,
                                                      Bonfire.CHAMBER_OF_THE_PRINCESS,
                                                      Bonfire.DARKMOON_TOMB,
                                                      Bonfire.PAINTED_WORLD);
        
        createBlock(warpPointsContainer, column, row, warpPointsBlock);
        
        return row + warpPointsBlock.size();
    }
    
    /**
     * Adds the warp points block 3 to the container.
     * 
     * @param warpPointsContainer The container
     * @param column The column for the cells.
     * @param row The row of the first cell.
     * @return The index of the next free row in the container.
     */
    private int createBlock3(GridPane warpPointsContainer, int column, int row) {
        List<Bonfire> warpPointsBlock = Arrays.asList(Bonfire.DUKES_ARCHIVE,
                                                      Bonfire.CRYSTAL_CAVES,
                                                      Bonfire.TOMB_OF_GIANTS_1,
                                                      Bonfire.GRAVELORD_ALTAR,
                                                      Bonfire.ABYSS);
        
        createBlock(warpPointsContainer, column, row, warpPointsBlock);
        
        return row + warpPointsBlock.size();
    }
    
    /**
     * Adds the warp points block 4 to the container.
     * 
     * @param warpPointsContainer The container
     * @param column The column for the cells.
     * @param row The row of the first cell.
     * @return The index of the next free row in the container.
     */
    private int createBlock4(GridPane warpPointsContainer, int column, int row) {
        List<Bonfire> warpPointsBlock = Arrays.asList(Bonfire.SANCTUARY_GARDEN,
                                                      Bonfire.OOLACILE_SANCTUARY,
                                                      Bonfire.OOLACILE_TOWNSHIP,
                                                      Bonfire.OOLACILE_DUNGEON,
                                                      Bonfire.CHASM_OF_THE_ABYSS);
        
        createBlock(warpPointsContainer, column, row, warpPointsBlock);
        
        return row + warpPointsBlock.size();
    }
    
    /**
     * Adds the warp points block to the container.
     * 
     * @param warpPointsContainer The container
     * @param column The column for the cells.
     * @param row The row of the first cell.
     * @param warpPointsBlock The warp points of the block.
     */
    private void createBlock(GridPane warpPointsContainer, int column, int row, List<Bonfire> warpPointsBlock) {
        final int labelColumn = column;
        final int valueColumn = labelColumn + 1;
        
        for (int warpPointIndex = 0; warpPointIndex < warpPointsBlock.size(); warpPointIndex++) {
            final Bonfire warpPoint = warpPointsBlock.get(warpPointIndex);
            final VPos position;
            
            if (warpPointIndex == 0) {
                position = VPos.TOP;
            } else if (warpPointIndex == warpPointsBlock.size() - 1) {
                position = VPos.BOTTOM;
            } else {
                position = VPos.CENTER;
            }
            
            addLabelGridCell(warpPointsContainer, getWarpPointName(warpPoint), position, labelColumn, row);
            warpPoints.put(warpPoint, addValueCell(warpPointsContainer, "", position, valueColumn, row++));
            
        }
        
    }
    
    /**
     * The displayed name for a warp point.
     */
    private String getWarpPointName(final Bonfire warpPoint) {
        return Messages.getMessage(warpPoint);
    }
    
    /**
     * Updates the panel title to show number of unlocked warp points.
     */
    public void setWarpPointsProgress(final int unlocked) {
        setTitle("Warp Points (" + unlocked + " / " + WarpState.ALL_WARP_POINTS.size() + ")");
    }
    
    /**
     * Sets the text for the warp point, so that it shows if the warp point is unlocked.
     * 
     * @param warpPoint The warp point.
     * @param unlocked If the warp point is unlocked.
     */
    public void setWarpPoint(final Bonfire warpPoint, final boolean unlocked) {
        warpPoints.get(warpPoint).setText(unlocked ? "unlocked" : "sealed");
    }
    
    /**
     * Sets the text showing if warping is unlocked.
     * 
     * @param unlocked If warping is unlocked.
     */
    public void setWarpUnlocked(final boolean unlocked) {
        if (unlocked) {
            warpUnlocked.setText("Can Warp: Yes");
        } else {
            warpUnlocked.setText("Can Warp: No");
        }
    }
    
}