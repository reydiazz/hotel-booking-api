package com.hotel.booking.api;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.repository.UserRepository;
import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.hotel.service.RoomService;
import com.hotel.booking.api.domain.reservation.model.enums.PaymentMethod;
import com.hotel.booking.api.domain.reservation.service.PaymentService;
import com.hotel.booking.api.domain.reservation.service.ReservationService;
import com.hotel.booking.api.domain.reservation.web.request.CreatePaymentRequest;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRoomRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;


@SpringBootTest
class ApplicationTests {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private PaymentService paymentService;

	@MockitoBean
	private AuthService authService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {

		User user = userRepository
				.findByUsername("U23226030")
				.orElseThrow();

		Mockito.when(authService.getAuthenticatedUser())
				.thenReturn(user);

		CreateReservationRoomRequest room = new CreateReservationRoomRequest(
				"ROM260520170912UMCVF",
				new BigDecimal("120"),
				5
		);

		CreateReservationRequest request = new CreateReservationRequest(
				"CUS260519160210RAUGN",
				room
		);
		reservationService.create(request);
	}

	@Test
	void changeStatusRoom(){
		reservationService.cancel("RSV260523174021MT89A");
	}

	@Test
	void pay(){
		User user = userRepository
				.findByUsername("U23226030")
				.orElseThrow();

		Mockito.when(authService.getAuthenticatedUser())
				.thenReturn(user);
		CreatePaymentRequest  request = new CreatePaymentRequest("RSV260523211905461NZ", PaymentMethod.CASH,new BigDecimal("200"));
		paymentService.create(request);
	}

	@Test
	void reservation(){
		roomService.updateLastCleaned("ROM260520170912UMCVF");
	}

}
