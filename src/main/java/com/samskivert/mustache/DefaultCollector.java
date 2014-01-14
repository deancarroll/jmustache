//
// $Id$

package com.samskivert.mustache;

import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

import java.util.*;

/**
 * A collector used when running in GWT.
 */
public class DefaultCollector extends BasicCollector {
  protected static final Mustache.VariableFetcher JSON_OBJECT_FETCHER = new Mustache.VariableFetcher() {
    public Object get(Object ctx, String name) throws Exception {
      JsonObject obj = ((JsonObject) ctx);

      if (obj.hasKey(name)) {
        return obj.get(name);
      } else {
        return Template.NO_FETCHER_FOUND;
      }
    }
  };
  protected static final Mustache.VariableFetcher JSON_ARRAY_FETCHER = new Mustache.VariableFetcher() {
    public Object get(Object ctx, String name) throws Exception {
      try {
        return ((JsonArray) ctx).get(Integer.parseInt(name));
      } catch (NumberFormatException nfe) {
        return Template.NO_FETCHER_FOUND;
      }
    }
  };

  @Override
  public Iterator<?> toIterator(final Object value) {
    Iterator<?> iter = super.toIterator(value);
    if (iter != null) return iter;

    if (value.getClass().isArray()) {
      return Arrays.asList((Object[]) value).iterator();
    } else if (value instanceof JsonArray) {
      List<JsonValue> ret = new ArrayList();
      for (int i = 0; i < ((JsonArray) value).length(); i = i + 1) {
        ret.add(((JsonArray) value).get(i));
      }

      return ret.iterator();
    }
    return null;
  }

  @Override
  public <K, V> Map<K, V> createFetcherCache() {

    return new HashMap<K, V>();
  }

  public Mustache.VariableFetcher createFetcher(Object ctx, String name) {

    if (ctx instanceof JsonObject) {
      return JSON_OBJECT_FETCHER;
    } else if (ctx instanceof JsonArray) {
      return JSON_ARRAY_FETCHER;
    }

    return super.createFetcher(ctx, name);
  }


  // TODO: override createFetcher and do some magic for JavaScript/JSON objects
}
