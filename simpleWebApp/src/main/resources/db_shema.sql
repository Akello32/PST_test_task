create table JOB_TITLE
(
    ID   INT auto_increment,
    NAME VARCHAR not null,
    constraint JOB_TITLE_PK
        primary key (ID)
);

create table DEPARTMENT
(
    ID   INT auto_increment,
    NAME VARCHAR not null,
    constraint DEPARTMENT_PK
        primary key (ID)
);

create table EMPLOYEE
(
    ID            INT auto_increment,
    FIRSTNAME     VARCHAR not null,
    LASTNAME      VARCHAR not null,
    DATE_OF_BIRTH DATE    not null,
    GENDER        VARCHAR not null,
    DEPARTMENT_ID INT     not null,
    constraint EMPLOYEE_PK
        primary key (ID),
    constraint DEPARTMENT_ID
        foreign key (DEPARTMENT_ID) references DEPARTMENT (ID)
            on update cascade on delete set null
);

create table TECHNICALLY_DEPARTMENTS
(
    ID            INT auto_increment,
    DEPARTMENT_ID INT not null,
    constraint TECHNICALLY_DEPARTMENTS_PK
        primary key (ID),
    constraint TECHNICALLY_DEPARTMENTS_DEPARTMENT_ID_FK
        foreign key (DEPARTMENT_ID) references DEPARTMENT (ID)
            on update cascade on delete cascade
);

create table "EmployeesPosition"
(
    JOB_TITLE_ID INT not null,
    EMPLOYEE_ID  INT not null,
    constraint EMPLOYEE_ID
        foreign key (EMPLOYEE_ID) references EMPLOYEE (ID)
            on update cascade on delete cascade,
    constraint JOB_TITLE_ID
        foreign key (JOB_TITLE_ID) references JOB_TITLE (ID)
            on update cascade on delete cascade
);

