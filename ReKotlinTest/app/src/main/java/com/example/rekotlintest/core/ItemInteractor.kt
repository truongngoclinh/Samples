package com.example.rekotlintest.core

interface ItemInteractor<T> {
    fun onItemClick(data: T)
}