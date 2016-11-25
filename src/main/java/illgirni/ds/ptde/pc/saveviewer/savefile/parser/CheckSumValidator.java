package illgirni.ds.ptde.pc.saveviewer.savefile.parser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.savefile.parser.exception.CheckSumConflictException;
import illgirni.ds.ptde.pc.saveviewer.savefile.savedata.ByteBlock;

/**
 * Allows to validate a block of bytes against an MD5 checksum.  
 * 
 * @author illgirni
 *
 */
@Bean
public class CheckSumValidator {
    
    /**
     * Validates the ByteBlock against the MD5 checksum. Basically calculates the checksum for the byte block
     * and then compares that against the expected checksum.
     * 
     * @param expectedCheckSum The expected MD5 checksum.
     * @param checkSummedBlock The byte data to validate against the checksum.
     * 
     * @throws CheckSumConflictException When the checksums don't match.
     */
    public void validateCheckSum(final byte[] expectedCheckSum, final ByteBlock checkSummedBlock) throws CheckSumConflictException {
        try {
            final byte[] checkSum = MessageDigest.getInstance("MD5").digest(checkSummedBlock.getBlockData());
            
            if (!Arrays.equals(checkSum, expectedCheckSum)) {
                throw new CheckSumConflictException();
            }
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot calculate check sums.", e);
        }
    }
    
}
