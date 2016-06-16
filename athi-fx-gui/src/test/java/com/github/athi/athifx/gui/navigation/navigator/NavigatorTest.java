package com.github.athi.athifx.gui.navigation.navigator;

import com.github.athi.athifx.gui.AthiFXTestCase;
import com.github.athi.athifx.gui.mock_data.TestItems;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Athi
 */
public class NavigatorTest extends AthiFXTestCase {

    @Inject
    private Navigator navigator;

    @Rule
    private ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNavigateToNonExistingView() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(Navigator.VIEW_DOES_NOT_EXIST_MESSAGE);

        navigator.navigateTo(TestItems.ITEM_FIVE);
    }

}
