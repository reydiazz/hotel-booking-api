package com.hotel.booking.api;

import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.person.web.request.CreateCustomerRequest;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	void contextLoads() {
		CreatePersonRequest person1 = new CreatePersonRequest(
				"Anghelo",
				"Reynoso",
				"+51 987 654 321",
				null
		);

		CreateCustomerRequest request1 = new CreateCustomerRequest(
				person1,
				DocumentTypeCustomer.DNI,
				"74224765"
		);

		customerService.create(request1);


		CreatePersonRequest person2 = new CreatePersonRequest(
				"Camila",
				"Torres",
				"+51 923 114 567",
				null
		);

		CreateCustomerRequest request2 = new CreateCustomerRequest(
				person2,
				DocumentTypeCustomer.DNI,
				"73145628"
		);

		customerService.create(request2);


		CreatePersonRequest person3 = new CreatePersonRequest(
				"Diego",
				"Ramirez",
				"+51 934 556 781",
				null
		);

		CreateCustomerRequest request3 = new CreateCustomerRequest(
				person3,
				DocumentTypeCustomer.DNI,
				"75896321"
		);

		customerService.create(request3);


		CreatePersonRequest person4 = new CreatePersonRequest(
				"Lucia",
				"Fernandez",
				"+51 945 667 892",
				null
		);

		CreateCustomerRequest request4 = new CreateCustomerRequest(
				person4,
				DocumentTypeCustomer.DNI,
				"71478569"
		);

		customerService.create(request4);


		CreatePersonRequest person5 = new CreatePersonRequest(
				"Andres",
				"Mendoza",
				"+51 956 778 903",
				null
		);

		CreateCustomerRequest request5 = new CreateCustomerRequest(
				person5,
				DocumentTypeCustomer.DNI,
				"78963214"
		);

		customerService.create(request5);


		CreatePersonRequest person6 = new CreatePersonRequest(
				"Valeria",
				"Castro",
				"+51 967 889 014",
				null
		);

		CreateCustomerRequest request6 = new CreateCustomerRequest(
				person6,
				DocumentTypeCustomer.DNI,
				"74589632"
		);

		customerService.create(request6);


		CreatePersonRequest person7 = new CreatePersonRequest(
				"Joaquin",
				"Rojas",
				"+51 978 990 125",
				null
		);

		CreateCustomerRequest request7 = new CreateCustomerRequest(
				person7,
				DocumentTypeCustomer.DNI,
				"76985214"
		);

		customerService.create(request7);


		CreatePersonRequest person8 = new CreatePersonRequest(
				"Mariana",
				"Silva",
				"+51 989 101 236",
				null
		);

		CreateCustomerRequest request8 = new CreateCustomerRequest(
				person8,
				DocumentTypeCustomer.DNI,
				"75214789"
		);

		customerService.create(request8);


		CreatePersonRequest person9 = new CreatePersonRequest(
				"Sebastian",
				"Vargas",
				"+51 990 212 347",
				null
		);

		CreateCustomerRequest request9 = new CreateCustomerRequest(
				person9,
				DocumentTypeCustomer.DNI,
				"79632541"
		);

		customerService.create(request9);


		CreatePersonRequest person10 = new CreatePersonRequest(
				"Renata",
				"Paredes",
				"+51 901 323 458",
				null
		);

		CreateCustomerRequest request10 = new CreateCustomerRequest(
				person10,
				DocumentTypeCustomer.DNI,
				"77896521"
		);

		customerService.create(request10);

	}

}
