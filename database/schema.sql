-- Create the database
create database bgg;

-- Use the created database
use bgg;

-- Create the user table
create table user (
  user_id varchar(8) not null,
  username varchar(255) not null,
  name varchar(255),
  primary key (user_id),
  unique (username)
);

create table task (
 task_id   int not null auto_increment,
    user_id   varchar(8) not null,
    description  text not null,
    priority  int not null ,
    due_date  date not null,
 constraint  task_pk primary key (task_id),
    constraint  task_user_fk foreign key(user_id) references user(user_id),
    constraint  chk_priority check(priority between 1 and 3) 
);

insert into task (user_id, description, priority, due_date) values ('66223e28', 'jogging', 2, '2023-5-7');

select * from task;