package dev.alpas.inertia

abstract class InertiaBagBase {
    abstract operator fun invoke(): Map<String, Map<String, Any?>>
}
