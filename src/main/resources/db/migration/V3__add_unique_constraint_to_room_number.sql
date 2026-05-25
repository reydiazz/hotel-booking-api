ALTER TABLE rooms
    ADD CONSTRAINT uk_room_number UNIQUE (number);