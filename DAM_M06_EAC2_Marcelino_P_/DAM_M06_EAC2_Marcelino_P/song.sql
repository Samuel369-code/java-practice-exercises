CREATE TYPE credits_type AS
(
	composer character varying,
	lyricist character varying,
	producer character varying
);

ALTER TYPE credits_type
    OWNER TO ioc;

CREATE TYPE duration_type AS
(
	minutes integer,
	seconds integer
);

ALTER TYPE duration_type
    OWNER TO ioc;


CREATE TYPE stats_type AS
(
	plays integer,
	rating numeric(2,1),
	downloads integer,
	likes integer
);

ALTER TYPE stats_type
    OWNER TO ioc;

CREATE TABLE song
(
    isrc character varying(15) NOT NULL,
    title character varying NOT NULL,
    duration duration_type NOT NULL,
    credits credits_type NOT NULL,
    stats stats_type NOT NULL,
    popularity numeric(2,1),
    explicit boolean NOT NULL,
    tags character varying[],
    CONSTRAINT song_pkey PRIMARY KEY (isrc)
);

ALTER TABLE song
    OWNER to ioc;
