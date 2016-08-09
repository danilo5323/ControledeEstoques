--
-- PostgreSQL database cluster dump
--

-- Started on 2016-04-04 21:46:17

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE controlador;
ALTER ROLE controlador WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION NOBYPASSRLS PASSWORD 'md53496a749862f0234f775a2690b75d034';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md5244af1e2823d5eaeeffc42c5096d8260';






-- Completed on 2016-04-04 21:46:17

--
-- PostgreSQL database cluster dump complete
--

