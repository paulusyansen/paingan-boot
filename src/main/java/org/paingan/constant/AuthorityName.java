package org.paingan.constant;

public enum AuthorityName {
	USER("ROLE_USER","USER"), 
	ADMIN("ROLE_ADMIN","ADMIN");
	
	private final String key;
	private final String text;

    /**
     * 
     * @param text
     */
	AuthorityName(final String key, final String text) {
		this.key = key;
        this.text = text;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

	public String getKey() {
		return key;
	}
	
	public String getText() {
		return text;
	}
}
