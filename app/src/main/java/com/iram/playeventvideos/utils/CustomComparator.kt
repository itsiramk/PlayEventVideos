package com.iram.playeventvideos.utils

import com.iram.playeventvideos.model.EventSchedule
import java.util.Comparator

class CustomComparator : Comparator<EventSchedule> {
    override fun compare(o1: EventSchedule, o2: EventSchedule): Int {
        return o1.date.compareTo(o2.date)
    }
}