package com.gorilla.camp.gorillacamp.reservation.vo;

import lombok.Data;

@Data
public class ReservationVo {
    private String roomNum; // 예약 방 번호
    private String roomName; // 예약 방 이름
    private String reservationName; // 예약자 명
    private String reservationPhoneNum; // 예약자 전화번호
    private String reservationPersonnel; // 예약 인원
    private String reservationType; // 예약 방 타입
    private String startDate; // 입실일
    private String endDate; // 퇴실일
    private String reservationDate; // 예약시점
    private String cancelDate; // 취소시점
    private String status; // 예약상태(y->예약, n->예약취소)


}
