
/*==============================================================*/
/* Table: ANTWOORD_OP_VRAAG                                     */
/*==============================================================*/
create table ANTWOORD_OP_VRAAG
(
  VRAAGID              varchar(32)                        not null,
  VRAAGVERSIE          varchar(10)                    not null,
  STUDENTNUMMER        varchar(6)                        not null,
  TENTAMENCODE         varchar(255)                   not null,
  TENTAMENVERSIE       varchar(10)                    not null,
  ANTWOORD             long varchar                   not null,
  BEHAALDEPUNTEN       float                          null,
  NAKIJKCOMMENT        varchar(1024)                  null,
  constraint PK_ANTWOORD_OP_VRAAG primary key (VRAAGID, VRAAGVERSIE, STUDENTNUMMER, TENTAMENCODE, TENTAMENVERSIE),
  foreign key(STUDENTNUMMER, TENTAMENCODE, TENTAMENVERSIE)
  references RESULTAAT (STUDENTNUMMER, TENTAMENCODE, TENTAMENVERSIE)
    on update cascade
    on delete restrict,
  foreign key (VRAAGID, VRAAGVERSIE)
  references VRAAG (VRAAGID, VRAAGVERSIE)
    on update cascade
    on delete restrict,
  foreign key (STUDENTNUMMER)
  references STUDENT (STUDENTNUMMER)
    on update cascade
    on delete restrict
);

/*==============================================================*/
/* Index: ANTWOORD_OP_VRAAG_VAN_RESULTAAT_FK                    */
/*==============================================================*/
create index ANTWOORD_OP_VRAAG_VAN_RESULTAAT_FK on ANTWOORD_OP_VRAAG (
  STUDENTNUMMER ASC,
  TENTAMENCODE ASC,
  TENTAMENVERSIE ASC
);

/*==============================================================*/
/* Index: ANTWOORD_OP_VRAAG_VAN_VRAAG_FK                        */
/*==============================================================*/
create index ANTWOORD_OP_VRAAG_VAN_VRAAG_FK on ANTWOORD_OP_VRAAG (
  VRAAGID ASC,
  VRAAGVERSIE ASC
);

/*==============================================================*/
/* Table: NAKIJKMODEL                                           */
/*==============================================================*/
create table NAKIJKMODEL
(
  NAKIJKMODELVERSIE    varchar(10)                    not null,
  VRAAGID              varchar(32)                        not null,
  VRAAGVERSIE          varchar(10)                    not null,
  CORRECTEANTWOORDEN   varchar(1024)                  null,
  NAKIJKINSTRUCTIES    varchar(1024)                  null,
  constraint PK_NAKIJKMODEL primary key (NAKIJKMODELVERSIE, VRAAGID, VRAAGVERSIE),
  foreign key (VRAAGID, VRAAGVERSIE)
  references VRAAG (VRAAGID, VRAAGVERSIE)
    on update cascade
    on delete restrict
);

/*==============================================================*/
/* Index: NAKIJKMODEL_PK                                        */
/*==============================================================*/
create unique index NAKIJKMODEL_PK on NAKIJKMODEL (
  NAKIJKMODELVERSIE ASC,
  VRAAGID ASC,
  VRAAGVERSIE ASC
);

/*==============================================================*/
/* Index: NAKIJKMODEL_VAN_VRAAG2_FK                             */
/*==============================================================*/
create index NAKIJKMODEL_VAN_VRAAG2_FK on NAKIJKMODEL (
  VRAAGID ASC,
  VRAAGVERSIE ASC
);

/*==============================================================*/
/* Table: RESULTAAT                                             */
/*==============================================================*/
create table RESULTAAT
(
  STUDENTNUMMER        varchar(6)                        not null,
  TENTAMENCODE         varchar(255)                   not null,
  TENTAMENVERSIE       varchar(10)                    not null,
  CIJFER               float(3)                       not null,
  BEOORDELENDEDOCENT   varchar(255)                   not null,
  HASH                 varchar(255)                   not null,
  constraint PK_RESULTAAT primary key (STUDENTNUMMER, TENTAMENCODE, TENTAMENVERSIE),
  foreign key (STUDENTNUMMER)
  references STUDENT (STUDENTNUMMER)
    on update cascade
    on delete restrict,
  foreign key (TENTAMENCODE, TENTAMENVERSIE)
  references TENTAMEN (TENTAMENCODE, TENTAMENVERSIE)
    on update cascade
    on delete restrict
);

/*==============================================================*/
/* Index: RESULTAAT_PK                                          */
/*==============================================================*/
create unique index RESULTAAT_PK on RESULTAAT (
  STUDENTNUMMER ASC,
  TENTAMENCODE ASC,
  TENTAMENVERSIE ASC
);

/*==============================================================*/
/* Index: RESULTAAT_VAN_TENTAMEN_PER_STUDENT_FK                 */
/*==============================================================*/
create index RESULTAAT_VAN_TENTAMEN_PER_STUDENT_FK on RESULTAAT (
  TENTAMENCODE ASC,
  TENTAMENVERSIE ASC
);

/*==============================================================*/
/* Table: STUDENT                                               */
/*==============================================================*/
create table STUDENT
(
  STUDENTNUMMER        varchar(6)                            not null,
  KLAS                 varchar(100)                   null,
  constraint PK_STUDENT primary key (STUDENTNUMMER)
);

/*==============================================================*/
/* Table: TENTAMEN                                              */
/*==============================================================*/
create table TENTAMEN
(
  TENTAMENCODE         varchar(255)                   not null,
  TENTAMENVERSIE       varchar(10)                    not null,
  TENTAMENNAAM         long varchar                   not null,
  TENTAMENTIJDSDUUR    time                           not null,
  TENTAMENKLAARZETDATUM date                           null,
  TENTAMENVERSIEDATUM  timestamp                      not null,
  TENTAMENVERSIEOMSCHRIJVING varchar(1024)                  null,
  constraint PK_TENTAMEN primary key (TENTAMENCODE, TENTAMENVERSIE)
);

/*==============================================================*/
/* Index: TENTAMEN_PK                                           */
/*==============================================================*/
create unique index TENTAMEN_PK on TENTAMEN (
  TENTAMENCODE ASC,
  TENTAMENVERSIE ASC
);

/*==============================================================*/
/* Table: TENTAMEN_MOMENT                                       */
/*==============================================================*/
create table TENTAMEN_MOMENT
(
  TENTAMENMOMENTID     varchar(32)                        not null,
  TENTAMENCODE         varchar(255)                   not null,
  TENTAMENVERSIENUMMER varchar(10)                    not null,
  TENTAMENMOMENTDATUM  date                           not null,
  TENTAMENMOMENTBEGINTIJD time                           not null,
  TENTAMENMOMENTEINDTIJD time                           not null,
  TENTAMENMOMENTLOKALEN varchar(10)                    not null,
  TENTAMENMOMENTSLEUTEL varchar(100)                   not null,
  TENTAMENMOMENTTOEGESTANEHULPMIDDELEN varchar(512)                   not null,
  TENTAMENMOMENTBIJZONDERHEDEN varchar(512)                   null,
  constraint PK_TENTAMEN_MOMENT primary key (TENTAMENMOMENTID),
  foreign key (TENTAMENCODE, TENTAMENVERSIENUMMER)
  references TENTAMEN (TENTAMENCODE, TENTAMENVERSIE)
    on update cascade
    on delete restrict

);

/*==============================================================*/
/* Index: TENTAMEN_MOMENT_PK                                    */
/*==============================================================*/
create unique index TENTAMEN_MOMENT_PK on TENTAMEN_MOMENT (
  TENTAMENMOMENTID ASC
);

/*==============================================================*/
/* Index: TENTAMEN_MOMENT_VAN_TENTAMEN_FK                       */
/*==============================================================*/
create index TENTAMEN_MOMENT_VAN_TENTAMEN_FK on TENTAMEN_MOMENT (
  TENTAMENCODE ASC,
  TENTAMENVERSIENUMMER ASC
);

/*==============================================================*/
/* Table: THEMA                                                 */
/*==============================================================*/
create table THEMA
(
  THEMANAAM            long varchar                   not null,
  constraint PK_THEMA primary key (THEMANAAM)
);

/*==============================================================*/
/* Index: ONDERWERP_PK                                          */
/*==============================================================*/
create unique index ONDERWERP_PK on THEMA (
  THEMANAAM ASC
);

/*==============================================================*/
/* Table: VRAAG                                                 */
/*==============================================================*/
create table VRAAG
(
  VRAAGID              varchar(32)                        not null,
  VRAAGVERSIE          varchar(10)                    not null,
  THEMANAAM            long varchar                   not null,
  NAKIJKMODELVERSIE    varchar(10)                    null,
  VRAAGSTELLING        long varchar                   not null,
  VRAAGPLUGIN          long varchar                   not null,
  VRAAGOEFENVRAAG      int                       not null,
  VRAAGGEMAAKTDOOR     varchar(255)                   not null,
  VRAAGVERSIEDATUM     timestamp                     not null,
  VRAAGVERSIEOMSCHRIJVING varchar(1024)                  null,
  constraint PK_VRAAG primary key (VRAAGID, VRAAGVERSIE),
  foreign key (NAKIJKMODELVERSIE, VRAAGID, VRAAGVERSIE)
  references NAKIJKMODEL (NAKIJKMODELVERSIE, VRAAGID, VRAAGVERSIE)
    on update cascade
    on delete restrict,
  foreign key (THEMANAAM)
  references THEMA (THEMANAAM)
    on update cascade
    on delete restrict

);

/*==============================================================*/
/* Index: VRAAG_PK                                              */
/*==============================================================*/
create unique index VRAAG_PK on VRAAG (
  VRAAGID ASC,
  VRAAGVERSIE ASC
);

/*==============================================================*/
/* Index: ONDERWERP_VAN_VRAAG_FK                                */
/*==============================================================*/
create index ONDERWERP_VAN_VRAAG_FK on VRAAG (
  THEMANAAM ASC
);

/*==============================================================*/
/* Index: NAKIJKMODEL_VAN_VRAAG_FK                              */
/*==============================================================*/
create index NAKIJKMODEL_VAN_VRAAG_FK on VRAAG (
  NAKIJKMODELVERSIE ASC,
  VRAAGID ASC,
  VRAAGVERSIE ASC
);

/*==============================================================*/
/* Table: VRAAG_VAN_TENTAMEN                                    */
/*==============================================================*/
create table VRAAG_VAN_TENTAMEN
(
  TENTAMENCODE         varchar(255)                   not null,
  TENTAMENVERSIE       varchar(10)                    not null,
  VRAAGID              varchar(32)                        not null,
  VRAAGVERSIE          varchar(10)                    not null,
  AANTALPUNTEN         float                          null,
  constraint PK_VRAAG_VAN_TENTAMEN primary key (TENTAMENCODE, TENTAMENVERSIE, VRAAGID, VRAAGVERSIE),
  foreign key (TENTAMENCODE, TENTAMENVERSIE)
  references TENTAMEN (TENTAMENCODE, TENTAMENVERSIE)
    on update cascade
    on delete restrict,
  foreign key(VRAAGID, VRAAGVERSIE)
  references VRAAG (VRAAGID, VRAAGVERSIE)
    on update cascade
    on delete restrict
);

/*==============================================================*/
/* Index: VRAAG_VAN_TENTAMEN_PK                                 */
/*==============================================================*/
create unique index VRAAG_VAN_TENTAMEN_PK on VRAAG_VAN_TENTAMEN (
  TENTAMENCODE ASC,
  TENTAMENVERSIE ASC,
  VRAAGID ASC,
  VRAAGVERSIE ASC
);

/*==============================================================*/
/* Index: VRAAG_VAN_TENTAMEN_FK                                 */
/*==============================================================*/
create index VRAAG_VAN_TENTAMEN_FK on VRAAG_VAN_TENTAMEN (
  TENTAMENCODE ASC,
  TENTAMENVERSIE ASC
);

/*==============================================================*/
/* Index: VRAAG_VAN_TENTAMEN2_FK                                */
/*==============================================================*/
create index VRAAG_VAN_TENTAMEN2_FK on VRAAG_VAN_TENTAMEN (
  VRAAGID ASC,
  VRAAGVERSIE ASC
);
