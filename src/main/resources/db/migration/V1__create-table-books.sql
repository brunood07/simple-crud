create table books (
    isbn bigint not null,
    title varchar(100) not null,
    edition int not null,
    author varchar(100) not null,
    category varchar(100) not null,
    model varchar(100) not null,

    primary key(isbn)
);