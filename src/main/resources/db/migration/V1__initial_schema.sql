CREATE TABLE hotels
(
    code    VARCHAR(20) PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(150) NOT NULL,
    city    VARCHAR(50)  NOT NULL,
    country VARCHAR(50)  NOT NULL,
    phone   VARCHAR(20)
);

CREATE TABLE room_types
(
    code        VARCHAR(20) PRIMARY KEY,
    hotel_code  VARCHAR(20)    NOT NULL,
    name        VARCHAR(50)    NOT NULL,
    capacity    INT            NOT NULL,
    base_price  DECIMAL(10, 2) NOT NULL,
    description VARCHAR(150),

    FOREIGN KEY (hotel_code) REFERENCES hotels (code)
);

CREATE TABLE rooms
(
    code           VARCHAR(20) PRIMARY KEY,
    hotel_code     VARCHAR(20) NOT NULL,
    room_type_code VARCHAR(20) NOT NULL,
    number         INT         NOT NULL,
    floor          INT         NOT NULL,

    status         ENUM(
        'AVAILABLE',
        'OCCUPIED',
        'DIRTY',
        'OUT_OF_SERVICE'
    ) NOT NULL DEFAULT 'AVAILABLE',

    last_cleaned   DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (hotel_code) REFERENCES hotels (code),
    FOREIGN KEY (room_type_code) REFERENCES room_types (code)
);

CREATE TABLE persons
(
    code       VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    phone      VARCHAR(20),
    birth_date DATE
);

CREATE TABLE employees
(
    code        VARCHAR(20) PRIMARY KEY,
    hotel_code  VARCHAR(20)    NOT NULL,
    person_code VARCHAR(20)    NOT NULL,

    position    ENUM(
        'MANAGER',
        'RECEPTIONIST',
        'HOUSEKEEPER'
    ) NOT NULL,

    salary      DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (hotel_code) REFERENCES hotels (code),
    FOREIGN KEY (person_code) REFERENCES persons (code)
);

CREATE TABLE customers
(
    code            VARCHAR(20) PRIMARY KEY,
    person_code     VARCHAR(20) NOT NULL,

    document_type   ENUM(
        'DNI',
        'PASSPORT',
        'FOREIGN_ID',
        'TAX_ID'
    ) NOT NULL,

    document_number VARCHAR(20) NOT NULL,

    FOREIGN KEY (person_code) REFERENCES persons (code)
);

CREATE TABLE app_users
(
    code          VARCHAR(20) PRIMARY KEY,

    username      VARCHAR(50) UNIQUE NOT NULL,
    password      VARCHAR(250)       NOT NULL,

    role          ENUM(
        'ADMIN',
        'RECEPTION'
    ) NOT NULL,

    active        BOOLEAN            NOT NULL DEFAULT TRUE,

    employee_code VARCHAR(20),

    FOREIGN KEY (employee_code) REFERENCES employees (code)
);

CREATE TABLE reservations
(
    code          VARCHAR(20) PRIMARY KEY,

    customer_code VARCHAR(20) NOT NULL,
    user_code     VARCHAR(20) NOT NULL,

    created_at    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    status        ENUM(
        'PENDING',
        'ACTIVE',
        'DONE',
        'CANCELLED'
    ) NOT NULL DEFAULT 'PENDING',

    check_in      DATETIME    NOT NULL,
    check_out     DATETIME    NOT NULL,

    FOREIGN KEY (customer_code) REFERENCES customers (code),
    FOREIGN KEY (user_code) REFERENCES app_users (code),

    CHECK (check_out > check_in)
);

CREATE TABLE reservation_rooms
(
    code             VARCHAR(20) PRIMARY KEY,

    reservation_code VARCHAR(20)    NOT NULL,
    room_code        VARCHAR(20)    NOT NULL,

    price_per_night  DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (reservation_code) REFERENCES reservations (code),
    FOREIGN KEY (room_code) REFERENCES rooms (code),

    UNIQUE (reservation_code, room_code)
);

CREATE TABLE payments
(
    code             VARCHAR(20) PRIMARY KEY,

    user_code        VARCHAR(20)    NOT NULL,
    reservation_code VARCHAR(20)    NOT NULL,

    payment_method   ENUM(
        'CASH',
        'CARD',
        'TRANSFER',
        'DIGITAL_WALLET'
    ) NOT NULL,

    status           ENUM(
        'PENDING',
        'PAID',
        'CANCELLED',
        'PARTIAL',
        'REFUNDED'
    ) NOT NULL DEFAULT 'PENDING',

    amount           DECIMAL(10, 2) NOT NULL,
    payment_date     DATETIME,

    FOREIGN KEY (reservation_code) REFERENCES reservations (code),
    FOREIGN KEY (user_code) REFERENCES app_users (code)
);