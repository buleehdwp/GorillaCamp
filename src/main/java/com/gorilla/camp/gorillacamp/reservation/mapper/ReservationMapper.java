package com.gorilla.camp.gorillacamp.reservation.mapper;

import com.gorilla.camp.gorillacamp.common.util.ResponseMessage;
import com.gorilla.camp.gorillacamp.reservation.vo.PensionRoomVo;
import com.gorilla.camp.gorillacamp.reservation.vo.ReservationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약 가능한 방 확인
    public List<ReservationVo> getReservationList(ReservationVo vo);
    public List<PensionRoomVo> getRoomList();
    public int doReservation(ReservationVo vo);
}
