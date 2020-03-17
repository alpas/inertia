package dev.alpas.inertia

import dev.alpas.Application
import dev.alpas.ServiceProvider
import dev.alpas.bindIfMissing
import dev.alpas.view.addCustomTag

class InertiaServiceProvider : ServiceProvider {
    override fun register(app: Application) {
        app.apply {
            bindIfMissing { InertiaConfig() }
            addCustomTag("inertia") {
                """<div id="app" data-page="{{ page | json_encode }}"></div>"""
            }
            append(InertiaMiddleware::class)
        }
    }
}
