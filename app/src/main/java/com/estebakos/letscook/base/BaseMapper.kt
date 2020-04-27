package com.estebakos.letscook.base

interface BaseMapper<in A, out B> {
    fun map(type: A): B
}