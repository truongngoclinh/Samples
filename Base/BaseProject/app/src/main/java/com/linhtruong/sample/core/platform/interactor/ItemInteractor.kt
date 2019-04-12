package com.linhtruong.sample.core.platform.interactor


/**
 * @author linhtruong
 */
interface ItemInteractor<T> {
    fun onItemClick(data: T)
}