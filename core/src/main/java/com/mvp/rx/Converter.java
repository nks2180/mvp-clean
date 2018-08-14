package com.mvp.rx;

import io.reactivex.functions.Function;

/**
 * Marker interface for all our converters: it will make easier to integrate them
 * in an Observable chain.
 */
public interface Converter<T, R> extends Function<T, R> {
}
