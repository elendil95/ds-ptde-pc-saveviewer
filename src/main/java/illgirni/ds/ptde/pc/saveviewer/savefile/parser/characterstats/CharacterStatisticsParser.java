package illgirni.ds.ptde.pc.saveviewer.savefile.parser.characterstats;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.AbstractSaveElementParser;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.ParserException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats.CharacterStatistics;
import illgirni.ds.ptde.pc.saveviewer.savefile.savestructure.characterstats.CharacterStatisticsDefinition;

/**
 * Parses the character stats from a byte block representing the content of a save slot.
 * Simply goes over all the value definitions in the {@link CharacterStatisticsDefinition}, 
 * reads them, and writes them to a {@link CharacterStatistics} or calls a "sub-parser" for 
 * the parsed value and writes the result to the stats Object. 
 * 
 * @author illgirni
 *
 */
@Bean
public class CharacterStatisticsParser extends AbstractSaveElementParser {
    
    /**
     * The stats definition.
     */
    @Inject
    private CharacterStatisticsDefinition statsDefinition;
    
    /**
     * Location parser.
     */
    @Inject
    private LocationParser locationParser;
    
    /**
     * Play time parser.
     */
    @Inject
    private PlayTimeParser playTimeParser;
    
    /**
     * Gender parser.
     */
    @Inject
    private GenderParser genderParser;
    
    /**
     * Starting class sparser.
     */
    @Inject
    private StartingClassParser startingClassParser;
    
    /**
     * Physique parser.
     */
    @Inject
    private PhysiqueParser physiqueParser;
    
    /**
     * Starting gift parser.
     */
    @Inject
    private GiftParser giftParser;
    
    /**
     * Covenant levels parser.
     */
    @Inject
    private CovenantLevelsParser covenantLevelsParser;
    
    /**
     * Current covenant parser.
     */
    @Inject
    private CovenantParser covenantParser;
    
    /**
     * Curse state parser.
     */
    @Inject
    private CursedParser cursedParser;
    
    /**
     * Last ending parser.
     */
    @Inject
    private EndingParser endingParser;
    
    /**
     * Parses the character statistics from the byte block representing a save slot's content.
     */
    public CharacterStatistics parse(final ByteBlock slotContentData) throws ParserException {
        final ByteBlock characterStatsBlock = blockSectionParser.parse(statsDefinition.getCharacterStatisticsDefinition(), slotContentData);
        final ByteBlock clearCounterBlock = blockSectionParser.parse(statsDefinition.getClearCounterDefinition(), slotContentData);
        final ByteBlock deathCountBlock = blockSectionParser.parse(statsDefinition.getDeathCountDefinition(), slotContentData);
        
        return parse(characterStatsBlock, clearCounterBlock, deathCountBlock);
    }
    
    /**
     * Parses the actual character stats from the given blocks.
     */
    private CharacterStatistics parse(final ByteBlock characterStatsBlock, final ByteBlock clearCounterBlock, final ByteBlock deathCountBlock) 
            throws ParserException {
        final CharacterStatistics stats = new CharacterStatistics();
        
        //special value stats
        final ByteBlock locationBlock = blockSectionParser.parse(statsDefinition.getLocationDefinition(), characterStatsBlock);
        stats.setLocation(locationParser.parse(locationBlock));
        
        final ByteBlock playTimeBlock = blockSectionParser.parse(statsDefinition.getPlayTimeDefinition(), characterStatsBlock);
        stats.setPlayTime(playTimeParser.parse(playTimeBlock));
        
        final ByteBlock genderBlock = blockSectionParser.parse(statsDefinition.getGenderDefinition(), characterStatsBlock);
        stats.setGender(genderParser.parse(genderBlock));
        
        final ByteBlock startingClassBlock = blockSectionParser.parse(statsDefinition.getStartingClassDefinition(), characterStatsBlock);
        stats.setStartingClass(startingClassParser.parse(startingClassBlock));
        
        final ByteBlock physiqueBlock = blockSectionParser.parse(statsDefinition.getPhysiqueDefinition(), characterStatsBlock);
        stats.setPhysique(physiqueParser.parse(physiqueBlock));
        
        final ByteBlock giftBlock = blockSectionParser.parse(statsDefinition.getGiftDefinition(), characterStatsBlock);
        stats.setGift(giftParser.parse(giftBlock));
        
        final ByteBlock covenantLevelsBlock = blockSectionParser.parse(statsDefinition.getCovenantLevelsDefinition(), characterStatsBlock);
        stats.setCovenantLevels(covenantLevelsParser.parse(covenantLevelsBlock));
        
        final ByteBlock covenantBlock = blockSectionParser.parse(statsDefinition.getCovenantDefinition(), characterStatsBlock);
        stats.setCovenant(covenantParser.parse(covenantBlock));
        
        final ByteBlock cursedBlock = blockSectionParser.parse(statsDefinition.getCursedDefinition(), characterStatsBlock);
        stats.setCursed(cursedParser.parse(cursedBlock));
        
        final ByteBlock endingBlock = blockSectionParser.parse(statsDefinition.getLastEndingDefinition(), clearCounterBlock);
        stats.setLastEnding(endingParser.parse(endingBlock));
        
        //simple stats
        stats.setAttunement(blockSectionParser.parse(statsDefinition.getAttunementDefinition(), characterStatsBlock));
        stats.setDexterity(blockSectionParser.parse(statsDefinition.getDexterityDefinition(), characterStatsBlock));
        stats.setEndurance(blockSectionParser.parse(statsDefinition.getEnduranceDefinition(), characterStatsBlock));
        stats.setFaith(blockSectionParser.parse(statsDefinition.getFaithDefinition(), characterStatsBlock));
        stats.setHpCurrent(blockSectionParser.parse(statsDefinition.getHpCurrentDefinition(), characterStatsBlock));
        
        stats.setHpMaxModified(blockSectionParser.parse(statsDefinition.getHpTotalModifiedDefinition(), characterStatsBlock));
        stats.setHpMaxUnmodified(blockSectionParser.parse(statsDefinition.getHpTotalUnmodifiedDefinition(), characterStatsBlock));
        stats.setHumanity(blockSectionParser.parse(statsDefinition.getHumanityDefinition(), characterStatsBlock));
        stats.setIntelligence(blockSectionParser.parse(statsDefinition.getIntelligenceDefinition(), characterStatsBlock));
        
        stats.setLevel(blockSectionParser.parse(statsDefinition.getLevelDefinition(), characterStatsBlock));
        stats.setName(blockSectionParser.parse(statsDefinition.getNameDefinition(), characterStatsBlock));
        stats.setResistance(blockSectionParser.parse(statsDefinition.getResistanceDefinition(), characterStatsBlock));
        stats.setSouls(blockSectionParser.parse(statsDefinition.getSoulsDefinition(), characterStatsBlock));
        stats.setSoulsTotal(blockSectionParser.parse(statsDefinition.getSoulsTotalDefinition(), characterStatsBlock));
        
        stats.setStaminaCurrent(blockSectionParser.parse(statsDefinition.getStaminaCurrentDefinition(), characterStatsBlock));
        stats.setStaminaMaxModified(blockSectionParser.parse(statsDefinition.getStaminaTotalModifiedDefinition(), characterStatsBlock));
        stats.setStaminaMaxUnmodified(blockSectionParser.parse(statsDefinition.getStaminaTotalUnmodifiedDefinition(), characterStatsBlock));
        stats.setStrength(blockSectionParser.parse(statsDefinition.getStrengthDefinition(), characterStatsBlock));
        stats.setVitality(blockSectionParser.parse(statsDefinition.getVitalityDefinition(), characterStatsBlock));
        
        stats.setMultiplayerCount(blockSectionParser.parse(statsDefinition.getMultiplayerCountDefinition(), characterStatsBlock));
        stats.setCoopVictories(blockSectionParser.parse(statsDefinition.getCoopVictoriesDefinition(), characterStatsBlock));
        stats.setIndictments(blockSectionParser.parse(statsDefinition.getIndictmentsDefinition(), characterStatsBlock));
        
        stats.setDeaths(blockSectionParser.parse(statsDefinition.getDeathsDefinition(), deathCountBlock));
        stats.setClearCount(blockSectionParser.parse(statsDefinition.getClearCountDefinition(), clearCounterBlock));
        
        return stats;
    }
    
}
