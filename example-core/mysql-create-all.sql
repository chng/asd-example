create table affiliation (
  aff_id                        bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_affiliation primary key (aff_id)
);

create table affiliation_charge (
  emp_id                        bigint,
  aff_id                        bigint,
  date                          datetime(6),
  amount                        double
);

create table employee (
  emp_id                        bigint auto_increment not null,
  bank_account                  varchar(255),
  name                          varchar(255),
  addr                          varchar(255),
  affiliation_name              varchar(255),
  pay_by_json                   varchar(255),
  pay_classification_json       varchar(255),
  pay_schedule_json             varchar(255),
  gmt_create                    datetime(6) not null,
  gmt_modify                    datetime(6) not null,
  constraint pk_employee primary key (emp_id)
);

create table sales_receipt (
  emp_id                        bigint,
  amount                        double,
  date                          datetime(6)
);

create table time_card (
  start_time                    datetime(6),
  end_time                      datetime(6),
  emp_id                        bigint
);

