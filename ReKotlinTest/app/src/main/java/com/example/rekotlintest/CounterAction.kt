package com.example.rekotlintest

import org.rekotlin.Action

class CounterAction {
    class Increase(val unit: Unit = Unit): Action
    class Decrease(val unit: Unit = Unit): Action
}