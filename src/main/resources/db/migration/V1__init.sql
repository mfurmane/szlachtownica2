CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE provinces (
    id       serial PRIMARY KEY,
    name     text,
    geom     geometry(Polygon, 4326) NOT NULL,
    location geometry(Point, 4326)
);

CREATE TABLE sub_provinces (
    id          serial PRIMARY KEY,
    province_id integer NOT NULL REFERENCES provinces (id) ON DELETE CASCADE ON UPDATE CASCADE,
    geom        geometry(Polygon, 4326) NOT NULL
);

CREATE TABLE region (
    id                serial PRIMARY KEY,
    sub_province_id   integer NOT NULL REFERENCES sub_provinces (id) ON DELETE CASCADE ON UPDATE CASCADE,
    humidity          text,
    climate           text,
    terrain_shape     text,
    soil_type         text,
    type              text,
    enchant           text,
    fertility         double precision,
    efficiency        double precision,
    planting_easiness double precision,
    farming_easiness  double precision,
    health            double precision,
    wind_of_change    double precision,
    expansion         double precision,
    attitude          double precision,
    stability         double precision,
    wood_richness     integer,
    development_level integer,
    enchantment_level integer,
    coast             boolean,
    geom              geometry(Polygon, 4326) NOT NULL
);
