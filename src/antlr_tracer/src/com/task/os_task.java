package com.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class os_task extends task {
    protected Map<Integer, String> tids = null;

    public Map<Integer, String> getTids() {
        return this.tids;
    }

    public void setTids(Map<Integer, String> tids) {
        this.tids = tids;
    }
}
