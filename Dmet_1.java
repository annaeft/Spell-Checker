import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;

/**
 * Encodes a string into a double metaphone value. This Implementation is based on the algorithm by Lawrence
 * Philips
 */
public class DoubleMetaphone implements StringEncoder {

    /**
     * "Vowels" to test for
     */
    private static final String VOWELS = "AEIOUY";

    /**
     * Prefixes when present which are not pronounced
     */
    private static final String[] SILENT_START =
        { "GN", "KN", "PN", "WR", "PS" };
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE =
        { "L", "R", "N", "M", "B", "H", "F", "V", "W", " " };
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER =
        { "ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER" };
    private static final String[] L_T_K_S_N_M_B_Z =
        { "L", "T", "K", "S", "N", "M", "B", "Z" };

    /**
     * Maximum length of an encoding, default is 4
     */
    private int maxCodeLen = 4;

    /**
     * Creates an instance of this DoubleMetaphone encoder
     */
    public DoubleMetaphone() {
        super();
    }

    /**
     * Encode a value with Double Metaphone.
     *
     * @param value String to encode
     * @return an encoded string
     */