ALTER TABLE reservation_rooms
    ADD nights INT NOT NULL CHECK (nights > 0);