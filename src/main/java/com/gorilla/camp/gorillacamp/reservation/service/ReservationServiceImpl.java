package com.gorilla.camp.gorillacamp.reservation.service;

import com.gorilla.camp.gorillacamp.common.util.ResponseMessage;
import com.gorilla.camp.gorillacamp.reservation.mapper.ReservationMapper;
import com.gorilla.camp.gorillacamp.reservation.vo.PensionRoomVo;
import com.gorilla.camp.gorillacamp.reservation.vo.ReservationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationMapper reservationMapper;

    // 예약 가능한 방 확인
    public List<PensionRoomVo> getReservationList(ReservationVo vo) {
        List<ReservationVo> reservationList = reservationMapper.getReservationList(vo); // 예약 목록
        List<PensionRoomVo> roomList = reservationMapper.getRoomList(); // 방 목록


        // 예약목록에 포함되지 않은 방 목록f
        try {
            for (int i = 0; i < roomList.size(); i++) {
                String roomNum1 = roomList.get(i).getRoomNum(); // 예약 방
                for (int z = 0; z < reservationList.size(); z++) {
                    String roomNum2 = reservationList.get(z).getRoomNum(); // 방
                    if (roomNum2.equals(roomNum1)) {
                        roomList.remove(i);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return roomList;
    }

    // 예약금액 계산
    public Map<String, Integer> doCount(ReservationVo vo) {
        Map<String, Integer> countMap = new HashMap<>();
        // 날짜 계산 후 기본 요금 세팅
        String startDate = vo.getStartDate();
        int year = Integer.parseInt(startDate.substring(0, 4));
        int month = Integer.parseInt(startDate.substring(5, 7));
        int day = Integer.parseInt(startDate.substring(8, 10));

        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String strDay = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        if ("금".equals(strDay) || "토".equals(strDay)) {
            countMap.put("baseRate", 60000);
        } else {
            countMap.put("baseRate", 40000);
        }

        // 인원 계산 후 추가 요금 세팅
        int personnel = Integer.parseInt(vo.getReservationPersonnel());
        if (personnel > 2) {
            int difference = 4 - personnel;
            if (difference == 1) {
                countMap.put("extraCharge", 10000);
            } else {
                countMap.put("extraCharge", 20000);
            }
        } else {
            countMap.put("extraCharge", 0);
        }

        countMap.put("total", countMap.get("baseRate") + countMap.get("extraCharge"));
        return countMap;
    }

    // 예약하기
    public ResponseMessage doReservation(ReservationVo vo){
        return reservationMapper.doReservation(vo);
    }
}
