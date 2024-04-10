create table method_log
(
    id               serial   primary key,
    method_name      varchar  not null,
    executed         timestamp not null,
    execution_nano   int4 not null,
    execution_millis int4,
    asynchronous     boolean default false
);