CREATE TABLE SINGER (
                        ID INT NOT NULL AUTO_INCREMENT
    , VERSION INT NOT NULL DEFAULT 0
    , FIRST_NAME VARCHAR(60) NOT NULL
    , LAST_NAME VARCHAR(40) NOT NULL
    , BIRTH_DATE DATE
    , UNIQUE (FIRST_NAME, LAST_NAME)
    , PRIMARY KEY (ID)
);

CREATE TABLE ALBUM (
                       ID INT NOT NULL AUTO_INCREMENT
    , VERSION INT NOT NULL DEFAULT 0
    , SINGER_ID INT NOT NULL
    , TITLE VARCHAR(100) NOT NULL
    , RELEASE_DATE DATE
    , UNIQUE (SINGER_ID, TITLE)
    , PRIMARY KEY (ID)
    , CONSTRAINT FK_ALBUM FOREIGN KEY (SINGER_ID)
        REFERENCES SINGER (ID)
);

CREATE TABLE INSTRUMENT (
                            INSTRUMENT_ID VARCHAR(20) NOT NULL
    , PRIMARY KEY (INSTRUMENT_ID)
);

CREATE TABLE SINGER_INSTRUMENT (
                                   SINGER_ID INT NOT NULL
    , INSTRUMENT_ID VARCHAR(20) NOT NULL
    , PRIMARY KEY (SINGER_ID, INSTRUMENT_ID)
    , CONSTRAINT FK_SINGER_INSTRUMENT_1 FOREIGN KEY (SINGER_ID)
        REFERENCES SINGER (ID) ON DELETE CASCADE
    , CONSTRAINT FK_SINGER_INSTRUMENT_2 FOREIGN KEY (INSTRUMENT_ID)
        REFERENCES INSTRUMENT (INSTRUMENT_ID)
);