package io.github.mattthomson.depijp.impatient;

import com.google.common.collect.Ordering;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Token implements Comparable<Token>, Serializable {
    private static final Ordering<Token> ORDERING =
            Ordering.natural().onResultOf(Token::getDocId).compound(
                    Ordering.natural().onResultOf(Token::getToken)
            );

    private final String docId;
    private final String token;

    public Token(String docId, String token) {
        this.docId = docId;
        this.token = token;
    }

    public String getDocId() {
        return docId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public final boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public final String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int compareTo(Token other) {
        return ORDERING.compare(this, other);
    }
}
