package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.characterstats;

import java.util.List;

import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Covenant;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Ending;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Gender;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Gift;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Location;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.Physique;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype.StartingClass;

/**
 * The stats of the character in a save slot.
 * 
 * @author illgirni
 *
 */
public class CharacterStatistics {
    
    /**
     * Current location.
     */
    private Location location;
    
    /**
     * Play time.
     */
    private String playTime;
    
    /**
     * Character name.
     */
    private String name;
    
    /**
     * Gender.
     */
    private Gender gender;
    
    /**
     * Starting class.
     */
    private StartingClass startingClass;
    
    /**
     * Starting gift.
     */
    private Gift gift;
    
    /**
     * Physique.
     */
    private Physique physique;
    
    /**
     * Current covenant.
     */
    private Covenant covenant;
    
    /**
     * Current number of held souls.
     */
    private long souls;
    
    /**
     * Total number of earned souls.
     */
    private long soulsTotal;
    
    /**
     * Level.
     */
    private long level;
    
    /**
     * Current HP (maybe reduced by damage taken or curse).
     */
    private long hpCurrent;
    
    /**
     * The maximum HP (including buffs/de-buffs).
     */
    private long hpMaxModified;
    
    /**
     * The maximum HP (excluding buffs/de-buffs).
     */
    private long hpMaxUnmodified;
    
    /**
     * Current stamina (maybe reduced by last action(s)).
     */
    private long staminaCurrent;
    
    /**
     * The maximum stamina (including buffs/de-buffs).
     */
    private long staminaMaxModified;
    
    /**
     * The maximum stamina (excluding buffs/de-buffs).
     */
    private long staminaMaxUnmodified;
    
    /**
     * Vitality.
     */
    private long vitality;
    
    /**
     * Attunement.
     */
    private long attunement;
    
    /**
     * Endurance.
     */
    private long endurance;
    
    /**
     * Strength.
     */
    private long strength;
    
    /**
     * Dexterity.
     */
    private long dexterity;
    
    /**
     * Resistance.
     */
    private long resistance;
    
    /**
     * Intelligence.
     */
    private long intelligence;
    
    /**
     * Faith.
     */
    private long faith;
    
    /**
     * Currently held soft humanity.
     */
    private long humanity;
    
    /**
     * Number of participations in multi-player.
     */
    private long multiplayerCount;
    
    /**
     * Number of bosses defeated as white phantom.
     */
    private long coopVictories;
    
    /**
     * Progress of all "levelable" covenants.
     */
    private List<CovenantLevel> covenantLevels;
    
    /**
     * Current number of indictments.
     */
    private long indictments;
    
    /**
     * If currently cursed.
     */
    private boolean cursed;
    
    /**
     * Last chosen ending.
     */
    private Ending lastEnding;
    
    /**
     * Number of deaths that the character suffered.
     */
    private long deaths;
    
    /**
     * NG counter.
     */
    private long clearCount;
    
    /**
     * NG counter.
     */
    public long getClearCount() {
        return clearCount;
    }
    
    /**
     * @see #getClearCount()
     */
    public void setClearCount(long clearCount) {
        this.clearCount = clearCount;
    }
    
    /**
     * Number of deaths that the character suffered.
     */
    public long getDeaths() {
        return deaths;
    }
    
    /**
     * @see #getDeaths()
     */
    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }
    
    /**
     * Last chosen ending.
     */
    public Ending getLastEnding() {
        return lastEnding;
    }
    
    /**
     * @see #getLastEnding()
     */
    public void setLastEnding(Ending lastEnding) {
        this.lastEnding = lastEnding;
    }
    
    /**
     * If currently cursed.
     */
    public boolean isCursed() {
        return cursed;
    }
    
    /**
     * @see #isCursed()
     */
    public void setCursed(boolean cursed) {
        this.cursed = cursed;
    }
    
    /**
     * Current number of indictments.
     */
    public long getIndictments() {
        return indictments;
    }
    
    /**
     * @see #getIndictments()
     */
    public void setIndictments(long indictments) {
        this.indictments = indictments;
    }
    
    /**
     * Progress of all "levelable" covenants.
     */
    public List<CovenantLevel> getCovenantLevels() {
        return covenantLevels;
    }
    
    /**
     * @see #getCovenantLevels()
     */
    public void setCovenantLevels(List<CovenantLevel> covenantLevels) {
        this.covenantLevels = covenantLevels;
    }
    
    /**
     * Number of participations in multi-player.
     */
    public long getMultiplayerCount() {
        return multiplayerCount;
    }
    
    /**
     * @see #getMultiplayerCount()
     */
    public void setMultiplayerCount(long multiplayerCount) {
        this.multiplayerCount = multiplayerCount;
    }
    
    /**
     * Number of bosses defeated as white phantom.
     */
    public long getCoopVictories() {
        return coopVictories;
    }
    
    /**
     * @see #getCoopVictories()
     */
    public void setCoopVictories(long coopVictories) {
        this.coopVictories = coopVictories;
    }
    
    /**
     * Current location.
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * @see #getLocation()
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    /**
     * Play time.
     */
    public String getPlayTime() {
        return playTime;
    }
    
    /**
     * @see #getPlayTime()
     */
    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }
    
    /**
     * Character name.
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
     * Gender.
     */
    public Gender getGender() {
        return gender;
    }
    
    /**
     * @see #getGender()
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    /**
     * Starting class.
     */
    public StartingClass getStartingClass() {
        return startingClass;
    }
    
    /**
     * @see #getStartingClass()
     */
    public void setStartingClass(StartingClass startingClass) {
        this.startingClass = startingClass;
    }
    
    /**
     * Starting gift.
     */
    public Gift getGift() {
        return gift;
    }
    
    /**
     * @see #getGift()
     */
    public void setGift(Gift startingGift) {
        this.gift = startingGift;
    }
    
    /**
     * Physique.
     */
    public Physique getPhysique() {
        return physique;
    }
    
    /**
     * @see #getPhysique()
     */
    public void setPhysique(Physique physique) {
        this.physique = physique;
    }
    
    /**
     * Current covenant.
     */
    public Covenant getCovenant() {
        return covenant;
    }
    
    /**
     * @see #getCovenant()
     */
    public void setCovenant(Covenant covenant) {
        this.covenant = covenant;
    }
    
    /**
     * Current number of held souls.
     */
    public long getSouls() {
        return souls;
    }
    
    /**
     * @see #getSouls()
     */
    public void setSouls(long souls) {
        this.souls = souls;
    }
    
    /**
     * Total number of earned souls.
     */
    public long getSoulsTotal() {
        return soulsTotal;
    }
    
    /**
     * @see #getSoulsTotal()
     */
    public void setSoulsTotal(long soulsTotal) {
        this.soulsTotal = soulsTotal;
    }
    
    /**
     * Level.
     */
    public long getLevel() {
        return level;
    }
    
    /**
     * @see #getLevel()
     */
    public void setLevel(long level) {
        this.level = level;
    }
    
    /**
     * Vitality.
     */
    public long getVitality() {
        return vitality;
    }
    
    /**
     * @see #getVitality()
     */
    public void setVitality(long vitality) {
        this.vitality = vitality;
    }
    
    /**
     * Attunement.
     */
    public long getAttunement() {
        return attunement;
    }
    
    /**
     * @see #getAttunement()
     */
    public void setAttunement(long attunement) {
        this.attunement = attunement;
    }
    
    /**
     * Endurance.
     */
    public long getEndurance() {
        return endurance;
    }
    
    /**
     * @see #getEndurance()
     */
    public void setEndurance(long endurance) {
        this.endurance = endurance;
    }
    
    /**
     * Strength.
     */
    public long getStrength() {
        return strength;
    }
    
    /**
     * @see #getStrength()
     */
    public void setStrength(long strength) {
        this.strength = strength;
    }
    
    /**
     * Dexterity.
     */
    public long getDexterity() {
        return dexterity;
    }
    
    /**
     * @see #getDexterity()
     */
    public void setDexterity(long dexterity) {
        this.dexterity = dexterity;
    }
    
    /**
     * Resistance.
     */
    public long getResistance() {
        return resistance;
    }
    
    /**
     * @see #getResistance()
     */
    public void setResistance(long resistance) {
        this.resistance = resistance;
    }
    
    /**
     * Intelligence.
     */
    public long getIntelligence() {
        return intelligence;
    }
    
    /**
     * @see #getIntelligence()
     */
    public void setIntelligence(long intelligence) {
        this.intelligence = intelligence;
    }
    
    /**
     * Faith.
     */
    public long getFaith() {
        return faith;
    }
    
    /**
     * @see #getFaith()
     */
    public void setFaith(long faith) {
        this.faith = faith;
    }
    
    /**
     * Currently held soft humanity.
     */
    public long getHumanity() {
        return humanity;
    }
    
    /**
     * @see #getHumanity()
     */
    public void setHumanity(long humanity) {
        this.humanity = humanity;
    }
    
    /**
     * Current HP (maybe reduced by damage taken or curse).
     */
    public long getHpCurrent() {
        return hpCurrent;
    }
    
    /**
     * @see #getHpCurrent()
     */
    public void setHpCurrent(long hpCurrent) {
        this.hpCurrent = hpCurrent;
    }
    
    /**
     * The maximum HP (including buffs/de-buffs).
     */
    public long getHpMaxModified() {
        return hpMaxModified;
    }
    
    /**
     * @see #getHpMaxModified()
     */
    public void setHpMaxModified(long hpMaxModified) {
        this.hpMaxModified = hpMaxModified;
    }
    
    /**
     * The maximum HP (excluding buffs/de-buffs).
     */
    public long getHpMaxUnmodified() {
        return hpMaxUnmodified;
    }
    
    /**
     * @see #getHpMaxUnmodified()
     */
    public void setHpMaxUnmodified(long hpMax) {
        this.hpMaxUnmodified = hpMax;
    }
    
    /**
     * Current stamina (maybe reduced by last action(s)).
     */
    public long getStaminaCurrent() {
        return staminaCurrent;
    }
    
    /**
     * @see #getStaminaCurrent()
     */
    public void setStaminaCurrent(long staminaCurrent) {
        this.staminaCurrent = staminaCurrent;
    }
    
    /**
     * The maximum stamina (including buffs/de-buffs).
     */
    public long getStaminaMaxModified() {
        return staminaMaxModified;
    }
    
    /**
     * @see #getStaminaMaxModified()
     */
    public void setStaminaMaxModified(long staminaMaxModified) {
        this.staminaMaxModified = staminaMaxModified;
    }
    
    /**
     * The maximum stamina (excluding buffs/de-buffs).
     */
    public long getStaminaMaxUnmodified() {
        return staminaMaxUnmodified;
    }
    
    /**
     * @see #getStaminaMaxUnmodified()
     */
    public void setStaminaMaxUnmodified(long staminaMax) {
        this.staminaMaxUnmodified = staminaMax;
    }

}
