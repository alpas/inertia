package dev.alpas.inertia

import dev.alpas.http.HttpCall
import dev.alpas.http.HttpCallHook

abstract class InertiaCallHookBase : HttpCallHook {
    override fun register(call: HttpCall) {
        if (call.isInertia) {
            call.combineJsonBodyWithParams()
        }
    }
}
