package dev.alpas.inertia

import dev.alpas.Handler
import dev.alpas.Middleware
import dev.alpas.http.HttpCall
import dev.alpas.http.Method
import dev.alpas.http.RedirectFilter
import dev.alpas.http.RedirectResponse

class InertiaMiddleware : Middleware<HttpCall>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(call: HttpCall, forward: Handler<HttpCall>) {
        call.redirect().pushFilter(object : RedirectFilter() {
            override fun invoke(redirectResponse: RedirectResponse, forward: Handler<RedirectResponse>) {
                if (redirectResponse.statusCode == 302 && call.method in listOf(Method.DELETE, Method.PUT, Method.PATCH)) {
                    redirectResponse.statusCode = 303
                }
                forward(redirectResponse)
            }
        })

        forward(call)
    }
}
