package hcsmain;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class JTextFieldLimit
 */

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {

    private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

        if (str == null) {
            return;
        }
        if ((this.getLength() + str.length()) <= this.limit) {
            super.insertString(offs, str, a);
        }

    }
}
