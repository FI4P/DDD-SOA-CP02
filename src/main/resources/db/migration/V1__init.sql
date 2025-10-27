
CREATE TABLE rooms (
                       id UUID PRIMARY KEY,
                       number INT NOT NULL UNIQUE,
                       type VARCHAR(20) NOT NULL,
                       capacity INT NOT NULL,
                       price_per_night DECIMAL(10,2) NOT NULL,
                       status VARCHAR(20) NOT NULL
);

CREATE TABLE reservations (
                              id UUID PRIMARY KEY,
                              room_id UUID NOT NULL,
                              guest_name VARCHAR(120) NOT NULL,
                              checkin_expected DATE NOT NULL,
                              checkout_expected DATE NOT NULL,
                              status VARCHAR(20) NOT NULL,
                              total_amount DECIMAL(10,2),
                              CONSTRAINT fk_reservation_room FOREIGN KEY (room_id) REFERENCES rooms(id)
);