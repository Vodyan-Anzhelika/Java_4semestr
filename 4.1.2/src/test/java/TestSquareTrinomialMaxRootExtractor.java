import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquareTrinomialMaxRootExtractor {
    private static final double EPS = 1E-6;
    
    
    @Test
    void testSquareTrinomialMaxRootExtractor() throws SquareTrinomialMaxRootExtractorException {
        SquareTrinomial trinomial1 = new SquareTrinomial(1, 2, -24);
        SquareTrinomial trinomial2 = new SquareTrinomial(1./9, -2./3, 1);
        SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(trinomial1);
        SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(trinomial2);
        
        assertAll(
                () -> assertEquals(trinomial1, extractor1.getSquareTrinomial()),
                () -> assertEquals(4, extractor1.getMaxRealRoot(), EPS),
                () -> assertEquals(trinomial2, extractor2.getSquareTrinomial()),
                () -> assertEquals(3, extractor2.getMaxRealRoot(), EPS)
        );
    }
    
    
    @Test
    void testSquareTrinomialMaxRootExtractorExceptions() {
        try {
            SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(null);
            fail();
        } catch (SquareTrinomialMaxRootExtractorException e) {
            assertEquals(SquareTrinomialMaxRootExtractorErrorCode.NULL_TRINOMIAL, e.getErrorCode());
        }
    
        try {
            SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 1, 1));
            double maxRealRoot = extractor2.getMaxRealRoot();
            fail();
        } catch (SquareTrinomialMaxRootExtractorException e) {
            assertEquals(SquareTrinomialMaxRootExtractorErrorCode.NO_REAL_ROOTS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testSquareTrinomialMaxRootExtractorEquals() throws SquareTrinomialMaxRootExtractorException {
        SquareTrinomialMaxRootExtractor extractor1 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, -8));
        SquareTrinomialMaxRootExtractor extractor2 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, -8));
        SquareTrinomialMaxRootExtractor extractor3 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(2, 2, -8));
        SquareTrinomialMaxRootExtractor extractor4 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 3, -8));
        SquareTrinomialMaxRootExtractor extractor5 = new SquareTrinomialMaxRootExtractor(new SquareTrinomial(1, 2, -2));
    
        assertAll(
                () -> assertEquals(extractor1, extractor2),
                () -> assertNotEquals(extractor1, extractor3),
                () -> assertNotEquals(extractor1, extractor4),
                () -> assertNotEquals(extractor1, extractor5),
                () -> assertNotEquals(extractor1, "")
        );
    }
}
