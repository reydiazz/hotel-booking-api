ALTER TABLE room_types
    ADD CONSTRAINT uk_room_types_name UNIQUE (name);