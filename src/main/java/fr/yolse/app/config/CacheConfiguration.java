package fr.yolse.app.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(fr.yolse.app.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(fr.yolse.app.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Region.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Province.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Province.class.getName() + ".regions", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Commune.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Commune.class.getName() + ".provinces", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Profil.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.TypeEngrais.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.TypeCulture.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Langue.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Utilisateur.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Utilisateur.class.getName() + ".typeCultures", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Utilisateur.class.getName() + ".clients", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Utilisateur.class.getName() + ".langues", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Utilisateur.class.getName() + ".communes", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.UtiProfil.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.UtiProfil.class.getName() + ".utilisateurs", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.UtiProfil.class.getName() + ".profils", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Client.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.EngraisClient.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.EngraisClient.class.getName() + ".clients", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.EngraisClient.class.getName() + ".typeEngrais", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinIntrant.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinIntrant.class.getName() + ".engraisClients", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinIntrant.class.getName() + ".typeCultures", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.SuiviChamps.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.SuiviChamps.class.getName() + ".clients", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Engrais.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Culture.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Reference.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Profile.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Utilisateur.class.getName() + ".responsables", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.UtiProfile.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.UtiProfile.class.getName() + ".utilisateurs", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.UtiProfile.class.getName() + ".profiles", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Client.class.getName() + ".langues", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Client.class.getName() + ".utilisateurs", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.Client.class.getName() + ".communes", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinIntrant.class.getName() + ".clients", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinIntrant.class.getName() + ".cultureEscs", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinIntrant.class.getName() + ".cultureGars", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinEngrais.class.getName(), jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinEngrais.class.getName() + ".besoinIntrants", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.BesoinEngrais.class.getName() + ".engrais", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.SuiviChamps.class.getName() + ".utilisateurs", jcacheConfiguration);
            cm.createCache(fr.yolse.app.domain.SuiviChamps.class.getName() + ".communes", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
