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
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "user" (
    id bigint NOT NULL,
    username character varying(20),
    password character varying(100),
    role character varying(20),
    status character varying(20)
);


ALTER TABLE "user" OWNER TO postgres;

--
-- Name: user_file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_file (
    file_id bigint NOT NULL,
    user_id bigint,
    create_date date DEFAULT CURRENT_DATE,
    type character varying(10) DEFAULT 'txt'::character varying,
    file_name character varying(30) NOT NULL,
    file text,
    create_data bytea
);


ALTER TABLE user_file OWNER TO postgres;

--
-- Name: user_file_file_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_file_file_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_file_file_id_seq OWNER TO postgres;

--
-- Name: user_file_file_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_file_file_id_seq OWNED BY user_file.file_id;


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- Name: user_file file_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_file ALTER COLUMN file_id SET DEFAULT nextval('user_file_file_id_seq'::regclass);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "user" (id, username, password, role, status) FROM stdin;
5	roman	$2a$12$YSiYwovxNRDTlz/sEr.MTuijllz5XJAu3kKbj70uMng4inWPIX8Z.	USER	ACTIVE
7	test	$2a$12$b1SUX9LSjJkm19ZeR.b9N.3moOeK4UnCSCLH91/VnSq5yuCnhsm22	USER	ACTIVE
9	pp	$2a$12$rAAkq6S5hY4G.Z3hA4ZLlOpnpRwPUyaBEeW39aZ.U9rd009Im6p9W	USER	ACTIVE
1	admin	$2a$12$cv/8p1IzxzEOIGQzzIam4.4jB1GQ0TYh7CoaIELS5UGwCyS.IJWye	ADMIN	ACTIVE
11	123	$2a$12$ndZIZDpqSrs9wlgC5ZhGu./x63YFIWpAee823kpIOLu/xgU/64Fua	USER	ACTIVE
\.


--
-- Data for Name: user_file; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_file (file_id, user_id, create_date, type, file_name, file, create_data) FROM stdin;
2	1	2018-04-19	json	test	{"a" : 1}	\N
3	1	2018-04-20	xml	root	<data><name>username</name></data>	\N
6	5	2018-04-20	json	root	{"value" : 1.5, "best" : spring the best}	\N
5	1	2018-04-21	json	filename	{"id":1}	\N
8	7	2018-04-21	xml	xml	<USER><age>22</age><sex>M</sex></USER>	\N
9	7	2018-04-21	txt	name	<USER><age>23</age><sex>W</sex></USER>	\N
17	9	2018-04-29	json	big json	{"15" : 573369.97, "last" : 573369.97, "buy" : 573369.97, "sell" : 573369.97, "symbol" : "RUB"}	\N
15	9	2018-04-29	json	big	{"15" : 573369.97, "last" : 573369.97, "buy" : 573369.97, "sell" : 573369.97, "symbol" : "RUB"}	\N
\.


--
-- Name: user_file_file_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_file_file_id_seq', 17, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 12, true);


--
-- Name: user_file user_file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_file
    ADD CONSTRAINT user_file_pkey PRIMARY KEY (file_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_username_key UNIQUE (username);


--
-- Name: user_file user_file_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_file
    ADD CONSTRAINT user_file_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- PostgreSQL database dump complete
--

