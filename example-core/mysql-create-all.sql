create table affiliation (
  aff_id                        bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_affiliation primary key (aff_id)
);

create table affiliation_employee (
  affiliation_aff_id            bigint not null,
  employee_emp_id               bigint not null,
  constraint pk_affiliation_employee primary key (affiliation_aff_id,employee_emp_id)
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
  pay_by_json                   varbinary(2048),
  pay_classification_json       varbinary(2048),
  gmt_create                    datetime(6) not null,
  gmt_modify                    datetime(6) not null,
  constraint pk_employee primary key (emp_id)
);

create table employee_affiliation (
  employee_emp_id               bigint not null,
  affiliation_aff_id            bigint not null,
  constraint pk_employee_affiliation primary key (employee_emp_id,affiliation_aff_id)
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

alter table affiliation_employee add constraint fk_affiliation_employee_affiliation foreign key (affiliation_aff_id) references affiliation (aff_id) on delete restrict on update restrict;
create index ix_affiliation_employee_affiliation on affiliation_employee (affiliation_aff_id);

alter table affiliation_employee add constraint fk_affiliation_employee_employee foreign key (employee_emp_id) references employee (emp_id) on delete restrict on update restrict;
create index ix_affiliation_employee_employee on affiliation_employee (employee_emp_id);

alter table employee_affiliation add constraint fk_employee_affiliation_employee foreign key (employee_emp_id) references employee (emp_id) on delete restrict on update restrict;
create index ix_employee_affiliation_employee on employee_affiliation (employee_emp_id);

alter table employee_affiliation add constraint fk_employee_affiliation_affiliation foreign key (affiliation_aff_id) references affiliation (aff_id) on delete restrict on update restrict;
create index ix_employee_affiliation_affiliation on employee_affiliation (affiliation_aff_id);

