BEGIN;

DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS buildings;

CREATE TABLE IF NOT EXISTS buildings (
    id      SERIAL      NOT NULL,
    address VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS rooms (
    id             SERIAL  NOT NULL,
    building_id    INT     NOT NULL,
    floor          INTEGER NOT NULL,
    max_allocation INTEGER NOT NULL,
    multimedia     BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT fk_building
          FOREIGN KEY(building_id)
    	  REFERENCES buildings(id)
    	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservations (
    id             SERIAL    NOT NULL,
    room_id        INT       NOT NULL,
    start_datetime TIMESTAMP NOT NULL,
    end_datetime   TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_room
          FOREIGN KEY(room_id)
    	  REFERENCES rooms(id)
    	  ON DELETE CASCADE
);

COMMIT;