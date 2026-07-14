ALTER TABLE reservation_rooms
    ADD COLUMN check_in DATETIME NULL,
    ADD COLUMN check_out DATETIME NULL;

ALTER TABLE reservation_rooms
    ADD CONSTRAINT chk_reservation_room_dates
        CHECK (
            check_in IS NULL
                OR check_out IS NULL
                OR check_out > check_in
            );

ALTER TABLE reservations
DROP COLUMN check_in,
    DROP COLUMN check_out;