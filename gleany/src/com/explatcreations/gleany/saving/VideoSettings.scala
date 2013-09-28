package com.explatcreations.gleany.saving

import com.explatcreations.gleany.graphics.display.DisplayType
import com.explatcreations.gleany.data.Point2i


trait VideoSettings {
    def getDisplayType:DisplayType
    def getWindowSize:Point2i
}
