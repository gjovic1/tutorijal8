CREATE TABLE drivers {
    id INTEGER;
    name CHAR;
    surname CHAR;
    jmb CHAR;
    birthday DATE;
    hiredate DATE;
};

CREATE TABLE buses {
    id INTEGER;
    maker CHAR;
    series VARCHAR;
    seatnumber INTEGER;
};

CREATE TABLE dodjela {
    bus_id INTEGER;
    driver_id INTEGER;
}

