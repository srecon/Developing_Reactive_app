package com.blu.junodb.javaexamples;

public interface DAO {
    public void addEnrty(String key, String value);
    public String getEntryByKey(String key);
}
