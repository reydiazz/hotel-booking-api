package com.hotel.booking.api;

import com.hotel.booking.api.domain.hotel.service.HotelService;
import com.hotel.booking.api.domain.hotel.web.request.CreateHotelRequest;
import com.hotel.booking.api.domain.person.model.enums.EmployeePosition;
import com.hotel.booking.api.domain.person.service.EmployeeService;
import com.hotel.booking.api.domain.person.service.PersonService;
import com.hotel.booking.api.domain.person.web.request.CreateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.request.UpdatePersonRequest;
import com.hotel.booking.api.domain.room.service.RoomService;
import com.hotel.booking.api.domain.room.web.response.RoomResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private EmployeeService service;
	@Autowired
	private HotelService hotelService;

	@Test
	void contextLoads() {
		CreatePersonRequest personRequest = new CreatePersonRequest("Fabrizio","Leon","+51 912002887",null);
		CreateEmployeeRequest request = new CreateEmployeeRequest("HOT260518103004ZDMOX",personRequest, EmployeePosition.RECEPTIONIST,new BigDecimal("10.50"));
		service.create(request);


	}

}
