package com.github.athi.athifx.gui.resources;

import java.io.File;

/**
 * Created by Athi
 */
public class AthiFXResourcesProvider {

    private static final String STYLES_RESOURCE = "styles";

    private static final String APPLICATION_ICON_NAME = "icon.jpg";
    private static final String APPLICATION_LOGO_NAME = "logo.jpg";

    public static String getIconPath() {
        return STYLES_RESOURCE + "/" + APPLICATION_ICON_NAME;
    }

    public static String getLogoPath() {
        return STYLES_RESOURCE + "/" + APPLICATION_LOGO_NAME;
    }
}
