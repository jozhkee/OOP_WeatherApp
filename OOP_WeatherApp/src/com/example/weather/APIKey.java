package com.example.weather;

/**
 * The APIKey class represents an API key used for accessing weather data.
 */
public class APIKey {
    private String key;

    public APIKey(String key) {
        this.key = key;
    }

    /**
     * Returns the API key string.
     *
     * @return The API key string.
     */

    public String getKey() {
        return key;
    }
}