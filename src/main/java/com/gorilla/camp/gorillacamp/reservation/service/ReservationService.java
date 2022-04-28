package com.gorilla.camp.gorillacamp.reservation.service;

import com.gorilla.camp.gorillacamp.common.util.ResponseMessage;
import com.gorilla.camp.gorillacamp.reservation.vo.PensionRoomVo;
import com.gorilla.camp.gorillacamp.reservation.vo.ReservationVo;

import java.util.List;
import java.util.Map;

public interface ReservationService {

    // 예약 가능한 방 확인
    public List<PensionRoomVo> getReservationList(ReservationVo vo);

    // 예약금액 계산
    public Map<String, Integer> doCount(ReservationVo vo);

    // 예약하기
    public ResponseMessage doReservation(ReservationVo vo);
}
