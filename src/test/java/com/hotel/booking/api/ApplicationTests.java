package com.hotel.booking.api;

import com.hotel.booking.api.domain.room.service.RoomService;
import com.hotel.booking.api.domain.room.web.response.RoomResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private RoomService service;

	@Test
	void contextLoads() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<RoomResponse> result = service.findAll(pageable);

		System.out.println(result.getContent());
	}

}
