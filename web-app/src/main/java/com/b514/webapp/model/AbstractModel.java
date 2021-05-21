package com.b514.webapp.model;

import com.b514.webapp.exception.NoCustomConvert;

import java.util.HashMap;

public abstract class AbstractModel {
    public HashMap<String, String> toHashMap() throws NoCustomConvert {
        throw new NoCustomConvert();
    }
}