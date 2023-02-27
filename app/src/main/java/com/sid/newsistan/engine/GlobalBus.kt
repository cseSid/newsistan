package com.sid.newsistan.engine

import org.greenrobot.eventbus.EventBus



object GlobalBus {
    private var sBus: EventBus? = null
    val sbus: EventBus?
        get() {
            if (GlobalBus.sBus == null) GlobalBus.sBus = EventBus.getDefault()
            return GlobalBus.sBus
        }
}
