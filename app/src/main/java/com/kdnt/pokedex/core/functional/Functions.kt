package com.kdnt.pokedex.core.functional

typealias Supplier<T> = () -> T

interface Consumer<T> {
    fun accept(t: T)
}