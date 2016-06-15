package com.github.athi.athifx.gui.font_awesome;

import com.github.athi.athifx.gui.AthiFXTestCase;
import javafx.scene.control.Label;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Athi
 */
public class FontAwesomeTest extends AthiFXTestCase {

    @Test
    public void unicodeTest() {
        String expectedYoutubeSquareUnicode = "\uF166";
        assertEquals(expectedYoutubeSquareUnicode, FontAwesome.YOUTUBE_SQUARE.getUnicode());
    }

    @Test
    public void fontSizeStyleTest() {
        String expected = "-fx-font-size: 16px;";
        assertEquals(expected, FontAwesome.FONT_SIZE_STYLE(16));
    }

    @Test
    public void labelIconTest() {
        Label label = FontAwesome.labelIcon(FontAwesome.ADJUST, 16);
        assertEquals(label.getText(), FontAwesome.ADJUST.getUnicode());
        assertTrue(label.getStyleClass().contains(FontAwesome.STYLE_CLASS_ICONS));
        assertTrue(label.getStyle().contains(FontAwesome.FONT_SIZE_STYLE(16)));
    }
}
