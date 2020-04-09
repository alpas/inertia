package dev.alpas.inertia

import dev.alpas.auth.BaseUser
import dev.alpas.http.HttpCall

open class InertiaBagBase {
    open operator fun invoke(call: HttpCall): Map<String, Any?> {
        val auth = userAsInertiaPayload(call)
        val errors = call.session.errors()
        val flash = call.session.userFlashBag()
        val csrfToken = call.session.csrfToken()

        return mapOf("errors" to errors, "flash" to flash, "auth" to auth, "csrf_token" to csrfToken)
    }

    protected open fun userAsInertiaPayload(call: HttpCall): Map<String, Any?> {
        return if (call.isAuthenticated) {
            val user: BaseUser<*> = call.caller()
            return mapOf(
                "user" to mapOf(
                    "id" to user.id,
                    "name" to user.name,
                    "email" to user.email
                )
            )
        } else {
            emptyMap()
        }
    }
}
