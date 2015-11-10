package com.nelsoft.droiddit.reddit.model;

import com.google.gson.JsonElement;
import com.nelsoft.droiddit.reddit.RedditType;

public class RedditObjectWrapper {
  RedditType kind;
  JsonElement data;

  public RedditType getKind() {
    return kind;
  }

    public JsonElement getData() {
        return data;
    }
}