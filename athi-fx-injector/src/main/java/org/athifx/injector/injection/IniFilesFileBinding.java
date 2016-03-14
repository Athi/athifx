package org.athifx.injector.injection;

import com.google.inject.Binder;
import com.google.inject.name.Names;
import org.athifx.injector.configuration.InjectorConfiguration;
import org.athifx.injector.log.Log;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * Created by Athi
 */
class IniFilesFileBinding implements ConfigurationFileBinding {

    private static final Log LOGGER = Log.getLogger(IniFilesFileBinding.class);

    @Override
    public void bind(Binder binder, InjectorConfiguration injectorConfiguration) {
        List<URL> iniURLs = injectorConfiguration.getIniURLs();
        iniURLs.forEach(url -> bindIni(binder, url).ifPresent(ini -> bindIniValues(binder, ini)));
    }

    private Optional<Ini> bindIni(Binder binder, URL iniURL) {
        try {
            String name = new File(iniURL.getFile()).getName();
            Ini iniInstance = new Ini(iniURL);
            binder.bind(Ini.class).annotatedWith(Names.named(name)).toInstance(iniInstance);
            return Optional.of(iniInstance);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    private void bindIniValues(Binder binder, Ini ini) {
        for (String sectionName : ini.keySet()) {
            Profile.Section section = ini.get(sectionName);
            for (String optionKey : section.keySet()) {
                binder.bindConstant().annotatedWith(Names.named("ini:" + optionKey)).to(section.get(optionKey));
            }
        }
    }
}
