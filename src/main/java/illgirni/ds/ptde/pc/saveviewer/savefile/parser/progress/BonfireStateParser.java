package illgirni.ds.ptde.pc.saveviewer.savefile.parser.progress;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.UnknownIndicatorException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Bonfire;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.BonfireStrength;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.progress.BonfireState;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.progress.BonfireStateDefinition;

/**
 * Parses the kindle state of a bonfire from a byte block.
 * 
 * @author illgirni
 *
 */
@Bean
public class BonfireStateParser extends AbstractSaveElementParser {
    
    /**
     * The definition of a bonfire kindle state.
     */
    @Inject
    private BonfireStateDefinition bonfireStateDefinition;
    
    /**
     * Parser for the actual bonfire kindle "strength"/state
     */
    @Inject
    private BonfireStrengthParser bonfireStrengthParser;
    
    /**
     * Parses the kindle state of a single bonfire from the byte block.
     * 
     * @param bonfireStateBlock The byte block with a single bonfire kindle state. 
     * @return The kindle state of the bonfire.
     * 
     * @throws ParserException
     */
    public BonfireState parse(final ByteBlock bonfireStateBlock) throws ParserException {
        final BonfireState bonfireState = new BonfireState();
        
        final long bonfireId = blockSectionParser.parse(bonfireStateDefinition.getBonfireDefinition(), bonfireStateBlock);
        final Bonfire bonfire = getBonfireForId(bonfireId);
        final BonfireStrength bonfireStrength = bonfireStrengthParser.parse(blockSectionParser.parse(bonfireStateDefinition.getBonfireStrengthDefinition(), 
                                                                            bonfireStateBlock));
        
        bonfireState.setBonfire(bonfire);
        bonfireState.setStrength(bonfireStrength);
        
        return bonfireState;
    }
    
    /**
     * Maps the bonfire id to the internal Bonfire value.
     * 
     * @param bonfireId The bonfire id.
     * @return The internal bonfire value.
     * 
     * @throws UnknownIndicatorException
     */
    public Bonfire getBonfireForId(final long bonfireId) throws UnknownIndicatorException {
        //isn't this a beauty? this is what you get for following principles...
        if (bonfireId == 1601950) {
            return Bonfire.ABYSS;
        } else if (bonfireId == 1511960) {
            return Bonfire.ANOR_LONDO;
        } else if (bonfireId == 1511961) {
            return Bonfire.ANOR_LONDO_KEEP;
        } else if (bonfireId == 1321961) {
            return Bonfire.ASH_LAKE;
        } else if (bonfireId == 1411950) {
            return Bonfire.BED_OF_CHAOS;
        } else if (bonfireId == 1401961) {
            return Bonfire.BLIGHTTOWN_SWAMP;
        } else if (bonfireId == 1401962) {
            return Bonfire.BLIGHTTOWN_SHANTY;
        } else if (bonfireId == 1301960) {
            return Bonfire.CATACOMBS_ENTRANCE;
        } else if (bonfireId == 1301961) {
            return Bonfire.CATACOMBS;
        } else if (bonfireId == 1511950) {
            return Bonfire.CHAMBER_OF_THE_PRINCESS;
        } else if (bonfireId == 1211950) {
            return Bonfire.CHASM_OF_THE_ABYSS;
        } else if (bonfireId == 1701950) {
            return Bonfire.CRYSTAL_CAVES;
        } else if (bonfireId == 1511962) {
            return Bonfire.DARKMOON_TOMB;
        } else if (bonfireId == 1601961) {
            return Bonfire.DARKROOT_BASIN;
        } else if (bonfireId == 1201961) {
            return Bonfire.DARKROOT_GARDEN;
        } else if (bonfireId == 1401960) {
            return Bonfire.DAUGHTER_OF_CHAOS;
        } else if (bonfireId == 1411961) {
            return Bonfire.DEMON_RUINS_1;
        } else if (bonfireId == 1411962) {
            return Bonfire.DEMON_RUINS_2;
        } else if (bonfireId == 1411963) {
            return Bonfire.DEMON_RUINS_3;
        } else if (bonfireId == 1001960) {
            return Bonfire.DEPTHS;
        } else if (bonfireId == 1701962) {
            return Bonfire.DUKES_ARCHIVE_ENTRANCE;
        } else if (bonfireId == 1701960) {
            return Bonfire.DUKES_ARCHIVE;
        } else if (bonfireId == 1701961) {
            return Bonfire.DUKES_ARCHIVE_PRISON;
        } else if (bonfireId == 1801960) {
            return Bonfire.FIRELINK_ALTAR;
        } else if (bonfireId == 1021960) {
            return Bonfire.FIRELINK_SHRINE;
        } else if (bonfireId == 1321962) {
            return Bonfire.GREAT_HOLLOW;
        } else if (bonfireId == 1411960) {
            return Bonfire.LOST_IZALITH;
        } else if (bonfireId == 1411964) {
            return Bonfire.LOST_IZALITH_ENTRANCE;
        } else if (bonfireId == 1311950) {
            return Bonfire.GRAVELORD_ALTAR;
        } else if (bonfireId == 1211964) {
            return Bonfire.OOLACILE_DUNGEON;
        } else if (bonfireId == 1211961) {
            return Bonfire.OOLACILE_SANCTUARY;
        } else if (bonfireId == 1211962) {
            return Bonfire.OOLACILE_TOWNSHIP;
        } else if (bonfireId == 1101960) {
            return Bonfire.PAINTED_WORLD;
        } else if (bonfireId == 1211963) {
            return Bonfire.SANCTUARY_GARDEN;
        } else if (bonfireId == 1501961) {
            return Bonfire.SENS_FORTRESS;
        } else if (bonfireId == 1321960) {
            return Bonfire.STONE_DRAGON;
        } else if (bonfireId == 1011961) {
            return Bonfire.SUNLIGHT_ALTAR;
        } else if (bonfireId == 1311961) {
            return Bonfire.TOMB_OF_GIANTS_1;
        } else if (bonfireId == 1311960) {
            return Bonfire.TOMB_OF_GIANTS_2;
        } else if (bonfireId == 1811961) {
            return Bonfire.UNDEAD_ASYLUM_CHAMBER;
        } else if (bonfireId == 1811960) {
            return Bonfire.UNDEAD_ASYLUM_COURTYARD;
        } else if (bonfireId == 1011962) {
            return Bonfire.UNDEAD_BURG;
        } else if (bonfireId == 1011964) {
            return Bonfire.UNDEAD_PARISH;
        } else {
            return null;
        }
    }
}
