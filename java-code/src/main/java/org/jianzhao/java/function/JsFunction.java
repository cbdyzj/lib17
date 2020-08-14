package org.jianzhao.java.function;

import org.jianzhao.jojo.JsonObject;

import java.util.function.Function;

@FunctionalInterface
public interface JsFunction extends Function<JsonObject, JsonObject> {
}
