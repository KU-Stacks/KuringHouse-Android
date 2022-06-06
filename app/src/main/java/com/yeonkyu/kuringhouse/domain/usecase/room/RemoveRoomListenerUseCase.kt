package com.yeonkyu.kuringhouse.domain.usecase.room

import com.yeonkyu.kuringhouse.domain.repository.RoomListenerRepository
import javax.inject.Inject

class RemoveRoomListenerUseCase @Inject constructor(
    private val repository: RoomListenerRepository
) {
    fun execute(tag: String, roomId: String) {
        repository.removeListener(tag, roomId)
    }
}