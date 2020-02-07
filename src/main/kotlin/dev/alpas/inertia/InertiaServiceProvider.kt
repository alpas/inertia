package dev.alpas.inertia

import dev.alpas.Application
import dev.alpas.ServiceProvider
import dev.alpas.view.addCustomTag

class InertiaServiceProvider : ServiceProvider {
    override fun boot(app: Application) {
        app.apply {
            addCustomTag("inertia") {
                """<div id="app" data-page="{{ page | json_encode }}"></div>"""
            }
            append(InertiaMiddleware::class)
        }
    }
}
