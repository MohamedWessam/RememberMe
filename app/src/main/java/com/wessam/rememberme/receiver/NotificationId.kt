package com.wessam.rememberme.receiver

import java.util.concurrent.atomic.AtomicInteger

private val atomicInteger = AtomicInteger()

class NotificationId {

    companion object {

        @JvmStatic
        fun getId(): Int {
            return atomicInteger.incrementAndGet()
        }

    }

}