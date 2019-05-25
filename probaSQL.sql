BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "dodjelaBus" (
	"driverId" INTEGER,
	"busId" INTEGER,
	"dodjelaBusId" INTEGER,
	PRIMARY KEY ("dodjelaBusId"),
	FOREIGN KEY ("driverId") REFERENCES "Vozac" ("driver_id"),
	FOREIGN KEY ("busId") REFERENCES "Bus" ("bus_id")
)
CREATE TABLE IF NOT EXISTS "Bus" (
	"bus_id" INTEGER,
	"proizvodjac" TEXT,
	"serija" TEXT,
	"broj_sjedista" INTEGER,
	"broj_vozaca" INTEGER,
	PRIMARY KEY ("bus_id")
);

CREATE TABLE IF NOT EXISTS "Vozac" (
	"vozac_id" INTEGER,
	"ime" TEXT,
	"prezime" TEXT,
	"jmb" TEXT UNIQUE,
	"datum_rodjenja" DATE,
	"datum_zaposlenja" DATE,
	PRIMARY KEY ("vozac_id")
);
CREATE UNIQUE INDEX "VozacJMBindex" ON "Vozac" (
	"jmb"
);
