package com.gorilla.camp.gorillacamp.reservation.controller;

import com.gorilla.camp.gorillacamp.common.util.ResponseMessage;
import com.gorilla.camp.gorillacamp.reservation.service.ReservationService;
import com.gorilla.camp.gorillacamp.reservation.vo.PensionRoomVo;
import com.gorilla.camp.gorillacamp.reservation.vo.ReservationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name : 실시간 예약
 * @desc : 실시간 예약 처리
 */
@RequestMapping("/api/v1")
@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    // 예약 가능한 방 확인
    @PostMapping("/v/searchReservation")
    public List<PensionRoomVo> getReservationList(ReservationVo vo) {
        return reservationService.getReservationList(vo);
    }

    // 예약금액 계산
    @PostMapping("/v/doCount")
    public Map<String, Integer> doCount(ReservationVo vo) {
        return reservationService.doCount(vo);
    }

    // 예약하기
    // TODO return 공통 만들기
    @PostMapping("/v/doReservation")
    public ResponseMessage doReservation(ReservationVo vo) throws Exception {
        return ResponseMessage.responseMap(reservationService.doReservation(vo));
        /*Map<String, Object> returnMap = new HashMap<>();
        try {
            reservationService.doReservation(vo);
            returnMap.put("code", "0000");
            returnMap.put("msg", "예약되었습니다.");
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("code", "1111");
            returnMap.put("msg", "예약에 실패했습니다.");
            return returnMap;
        }*/
    }
}

