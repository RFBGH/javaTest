package com.rfb.demo.rxjavatest.kotlin.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FormatDelegate : ReadWriteProperty<Any?, String>{

    private var formattedString = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return formattedString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {

        if(thisRef is Person){
            thisRef.updateCount++
        }

        formattedString = "$value by delegate"
    }
}