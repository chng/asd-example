alter table affiliation_employee drop foreign key fk_affiliation_employee_affiliation;
drop index ix_affiliation_employee_affiliation on affiliation_employee;

alter table affiliation_employee drop foreign key fk_affiliation_employee_employee;
drop index ix_affiliation_employee_employee on affiliation_employee;

alter table employee_affiliation drop foreign key fk_employee_affiliation_employee;
drop index ix_employee_affiliation_employee on employee_affiliation;

alter table employee_affiliation drop foreign key fk_employee_affiliation_affiliation;
drop index ix_employee_affiliation_affiliation on employee_affiliation;

drop table if exists affiliation;

drop table if exists affiliation_employee;

drop table if exists affiliation_charge;

drop table if exists employee;

drop table if exists employee_affiliation;

drop table if exists sales_receipt;

drop table if exists time_card;

