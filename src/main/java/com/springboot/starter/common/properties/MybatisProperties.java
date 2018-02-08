package com.springboot.starter.common.properties;

/**
 * @author baker
 */
public class MybatisProperties {

    private String mapperLocations;

    private boolean enabled;

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
