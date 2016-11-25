package illgirni.ds.ptde.pc.saveviewer.savefile.savedata.datatype;

/**
 * The game's covenants.
 * 
 * @author illgirni
 *
 */
public enum Covenant {
    
    NONE {
        @Override
        public int calculateRank(long offerings) {
            return 0;
        }
    },
    
    WAY_OF_WHITE {
        @Override
        public int calculateRank(long offerings) {
            return 0;
        }
    },
    
    PRINESS_S_GUARD {
        @Override
        public int calculateRank(long offerings) {
            return 0;
        }
    },
    
    WARRIOR_OF_SUNLIGHT,
    
    DARKWRAITH,
    
    PATH_OF_THE_DRAGON,
    
    GRAVELORD_SERVANT,
    
    FOREST_HUNTER,
    
    BLADE_OF_THE_DARKMOON,
    
    CHAOS_SERVANT;
    
    /**
     * Calculates the current rank in the covenant.
     * 
     * @param offerings The number of offerings provided to the covenant leader.
     * @return The rank in the covenant.
     */
    public int calculateRank(long offerings)  {
        if (offerings < 10) {
            return 0;
        } else if (offerings < 30) {
            return 1;
        } else if (offerings < 80) {
            return 2;
        } else {
            return 3;
        }
    }
}
