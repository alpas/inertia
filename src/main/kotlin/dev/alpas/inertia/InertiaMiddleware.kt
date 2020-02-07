package dev.alpas.inertia

import dev.alpas.Handler
import dev.alpas.Middleware
import dev.alpas.http.HttpCall
import dev.alpas.http.Method
import dev.alpas.http.Redirect
import dev.alpas.http.RedirectFilter
import dev.alpas.isOneOf

class InertiaMiddleware : Middleware<HttpCall>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(call: HttpCall, forward: Handler<HttpCall>) {
        call.redirector.pushFilter(object : RedirectFilter() {
            override fun invoke(redirect: Redirect, forward: Handler<Redirect>) {
                val newRedirect = if (call.method.isOneOf(Method.DELETE, Method.PUT, Method.PATCH)) {
                    redirect.copy(status = 303)
                } else {
                    redirect
                }
                forward(newRedirect)
            }
        })

        forward(call)
    }
}
