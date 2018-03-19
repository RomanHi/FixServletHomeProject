--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: fix_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE fix_user (
    id bigint NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(200) NOT NULL,
    role character varying(20) DEFAULT 'USER'::character varying
);


ALTER TABLE fix_user OWNER TO postgres;

--
-- Name: table_name_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE table_name_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE table_name_id_seq OWNER TO postgres;

--
-- Name: table_name_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE table_name_id_seq OWNED BY fix_user.id;


--
-- Name: fix_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fix_user ALTER COLUMN id SET DEFAULT nextval('table_name_id_seq'::regclass);


--
-- Data for Name: fix_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fix_user (id, username, password, role) FROM stdin;
11	123	$2a$12$WMIMGYx6.AbIoEP/PM0UbuVNLAzy.2RJtdGUEjMf54CCwaxN356Iy	USER
13	1234	$2a$12$gdTmJNcywMvQRAQIqq10gOcyzOlRpDDBfp8KRkm4zDRoXNw7U79nW	USER
6	petya	$2a$12$8n4jXodiPf/C/6nfEWO7GObQ/7O/L/oVqmtXXf1vke.VPXr/X8K2u	ADMIN
14	Alisher	$2a$12$h6Y9SgNPJ.ya.LMwItD35.qWPJVaxVhF9vKI7a6CJBF37WGGYAXTK	USER
19	34	$2a$12$2wUiN5k2DlOo3ZglvTO4T.tyLj1gfNg/r6QKgu9U3ZTSvTmkG/Cf.	USER
17	admin	$2a$12$cv/8p1IzxzEOIGQzzIam4.4jB1GQ0TYh7CoaIELS5UGwCyS.IJWye	ADMIN
20	vova	$2a$12$gxba/nAHPjbep1x6J7wZTuVz0DQzJTuh/HF1QmS8WwEnKFkB/ysqO	USER
\.


--
-- Name: table_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('table_name_id_seq', 20, true);


--
-- Name: fix_user table_name_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fix_user
    ADD CONSTRAINT table_name_pkey PRIMARY KEY (id);


--
-- Name: table_name_username_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX table_name_username_uindex ON fix_user USING btree (username);


--
-- PostgreSQL database dump complete
--

