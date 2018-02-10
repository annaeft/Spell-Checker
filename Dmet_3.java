/**
     * Encode the value using DoubleMetaphone.  It will only work if
     * <code>obj</code> is a <code>String</code> (like <code>Metaphone</code>).
     *
     * @param obj Object to encode (should be of type String)
     * @return An encoded Object (will be of type String)
     * @throws EncoderException encode parameter is not of type String
     */
    @Override
    public Object encode(final Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
        }
        return doubleMetaphone((String) obj);
    }

    /**
     * Encode the value using DoubleMetaphone.
     *
     * @param value String to encode
     * @return An encoded String
     */
    @Override
    public String encode(final String value) {
        return doubleMetaphone(value);
    }