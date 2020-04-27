package com.estebakos.letscook.base

interface NavigationProvider<in A> {

    fun navigateTo(originView: A, destinationView: A, params: Any? = null)
}
