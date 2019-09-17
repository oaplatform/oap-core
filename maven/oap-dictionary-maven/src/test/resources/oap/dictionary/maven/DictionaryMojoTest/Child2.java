package test;

import oap.dictionary.Dictionary;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Arrays.asList;

public enum Child2 implements Dictionary {
  sid111(101, true),
  sid112(102, true),
  sid121(111, true);

  private final int externalId;
  private final boolean enabled;



  Child2( int externalId, boolean enabled ) {
    this.externalId = externalId;
    this.enabled = enabled;
  }

  public static Child2 valueOf( int externalId ) {
    switch( externalId ) {
      case 65: return id1;
      case 122: return id2;
      default: throw new java.lang.IllegalArgumentException( "Unknown id " + externalId );
    }
  }

  @Override
  public int getOrDefault( String id, int defaultValue ) {
    return defaultValue;
  }

  @Override
  public Integer get( String id ) {
    return null;
  }

  @Override
  public String getOrDefault( int externlId, String defaultValue ) {
    return defaultValue;
  }

  @Override
  public boolean containsValueWithId( String id ) {
    return false;
  }

  @Override
  public List<String> ids() {
    return emptyList();
  }

  @Override
  public int[] externalIds() {
    return new int[0];
  }

  @Override
  public Map<String, Object> getProperties() {
    return emptyMap();
  }

  @Override
  public Optional<? extends Dictionary> getValueOpt( String name ) {
    return Optional.empty();
  }

  @Override
  public Dictionary getValue( String name ) {
    return null;
  }

  @Override
  public Dictionary getValue( int externalId ) {
    return null;
  }

  @Override
  public List<? extends Dictionary> getValues() {
    return emptyList();
  }

  @Override
  public String getId() {
    return name();
  }

  @Override
  public Optional<Object> getProperty( String name ) {
    return Optional.empty();
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public int getExternalId() {
    return externalId;
  }
  @Override
  public Child2 cloneDictionary() {
    return this;
  }
  public int externalId() {
    return externalId;
  }

  @Override
  public boolean containsProperty( String name ) {
    return false;
  }
}
