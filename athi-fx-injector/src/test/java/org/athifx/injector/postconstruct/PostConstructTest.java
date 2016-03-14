package org.athifx.injector.postconstruct;

import com.google.inject.Inject;
import org.athifx.injector.AthiFXTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class PostConstructTest extends AthiFXTestCase {

    @Inject
    private WithPublicPostConstruct withPublicPostConstruct;

    @Inject
    private WithPrivatePostConstruct withPrivatePostConstruct;

    @Test
    public void publicPostConstructTest() {
        assertEquals(false, WithPublicPostConstruct.PUBLIC_INIT_VALUE_BEFORE_CALL.equals(WithPublicPostConstruct.PUBLIC_INIT_VALUE_AFTER_CALL));

        assertEquals(false, withPublicPostConstruct.getValue().equals(WithPublicPostConstruct.PUBLIC_INIT_VALUE_BEFORE_CALL));
        assertEquals(true, withPublicPostConstruct.getValue().equals(WithPublicPostConstruct.PUBLIC_INIT_VALUE_AFTER_CALL));
    }

    @Test
    public void privatePostConstructTest() {
        assertEquals(false, WithPrivatePostConstruct.PRIVATE_INIT_VALUE_BEFORE_CALL.equals(WithPrivatePostConstruct.PRIVATE_INIT_VALUE_AFTER_CALL));

        assertEquals(false, withPrivatePostConstruct.getValue().equals(WithPrivatePostConstruct.PRIVATE_INIT_VALUE_BEFORE_CALL));
        assertEquals(true, withPrivatePostConstruct.getValue().equals(WithPrivatePostConstruct.PRIVATE_INIT_VALUE_AFTER_CALL));
    }

}
