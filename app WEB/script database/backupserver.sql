--
-- PostgreSQL database cluster dump
--

-- Started on 2016-04-04 21:47:10

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE "controlador";
ALTER ROLE "controlador" WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION NOBYPASSRLS PASSWORD 'md53496a749862f0234f775a2690b75d034';
CREATE ROLE "postgres";
ALTER ROLE "postgres" WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md5244af1e2823d5eaeeffc42c5096d8260';






--
-- Database creation
--

CREATE DATABASE "GerenciadordeEstoques" WITH TEMPLATE = template0 OWNER = "postgres";
CREATE DATABASE "sgce" WITH TEMPLATE = template0 OWNER = "postgres";
REVOKE ALL ON DATABASE "template1" FROM PUBLIC;
REVOKE ALL ON DATABASE "template1" FROM "postgres";
GRANT ALL ON DATABASE "template1" TO "postgres";
GRANT CONNECT ON DATABASE "template1" TO PUBLIC;


\connect "GerenciadordeEstoques"

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-04-04 21:47:10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA "public"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA "public" IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "plpgsql" WITH SCHEMA "pg_catalog";


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION "plpgsql"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "plpgsql" IS 'PL/pgSQL procedural language';


SET search_path = "public", pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 188 (class 1259 OID 16493)
-- Name: administrador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "administrador" (
    "id_administrador" bigint NOT NULL,
    "usuario" character varying(10),
    "senha" character varying(20),
    "email" character varying(30)
);


ALTER TABLE "administrador" OWNER TO "postgres";

--
-- TOC entry 181 (class 1259 OID 16460)
-- Name: cli_nota_fiscal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "cli_nota_fiscal" (
    "id_nf_cliente" bigint NOT NULL,
    "id_cliente" bigint NOT NULL,
    "codigo_produto" bigint,
    "data_emissao" "date",
    "valor_total" numeric(7,2),
    "id_produto" bigint
);


ALTER TABLE "cli_nota_fiscal" OWNER TO "postgres";

--
-- TOC entry 182 (class 1259 OID 16465)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "cliente" (
    "cli_telefone" integer,
    "cli_cpf" numeric(11,0),
    "cli_ie" numeric(12,0),
    "cli_cnpj" numeric(14,0),
    "id_endereco" integer,
    "cli_nome_princ" character varying(30),
    "cli_nome_sec" character varying(30),
    "id_cliente" integer NOT NULL
);


ALTER TABLE "cliente" OWNER TO "postgres";

--
-- TOC entry 190 (class 1259 OID 16520)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "cliente_id_cliente_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "cliente_id_cliente_seq" OWNER TO "postgres";

--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 190
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "cliente_id_cliente_seq" OWNED BY "cliente"."id_cliente";


--
-- TOC entry 183 (class 1259 OID 16470)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "endereco" (
    "id_endereco" bigint NOT NULL,
    "cep" integer,
    "logradouro" character varying(55),
    "numero" character varying(10),
    "bairro" character varying(30),
    "cidade" character varying(30),
    "estado" character varying(30),
    "país" character varying(15)
);


ALTER TABLE "endereco" OWNER TO "postgres";

--
-- TOC entry 187 (class 1259 OID 16490)
-- Name: estoque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "estoque" (
    "prd_preco_venda" numeric(10,2),
    "prd_preco_compra" numeric(10,2),
    "prd_data_compra" "date",
    "prd_data_validade" "date",
    "prd_medida" numeric(14,3),
    "prd_data_venda" "date",
    "prd_cod_barras" character varying(30)
);


ALTER TABLE "estoque" OWNER TO "postgres";

--
-- TOC entry 185 (class 1259 OID 16480)
-- Name: for_nota_fiscal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "for_nota_fiscal" (
    "id_nf_fornecedor" bigint NOT NULL,
    "id_fornecedor" bigint NOT NULL,
    "id_produto" bigint NOT NULL,
    "codigo_lote" bigint,
    "data_emissao" "date"
);


ALTER TABLE "for_nota_fiscal" OWNER TO "postgres";

--
-- TOC entry 184 (class 1259 OID 16475)
-- Name: fornecedor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "fornecedor" (
    "id_fornecedor" bigint NOT NULL,
    "forn_nome_empresa" character varying(30),
    "forn_nome_fantasia" character varying(55),
    "forn_cnpj" numeric(14,0),
    "forn_ie" character varying(30),
    "forn_email" character varying(30),
    "forn_tel" character varying(16),
    "id_endereco" bigint
);


ALTER TABLE "fornecedor" OWNER TO "postgres";

--
-- TOC entry 186 (class 1259 OID 16485)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "produto" (
    "prd_nome_padrao" character varying(30),
    "prd_nome_qualificador" character varying(30),
    "prd_unidade_medida" character varying(5),
    "id_produto" integer NOT NULL,
    "prd_cod_barras" character varying(30)
);


ALTER TABLE "produto" OWNER TO "postgres";

--
-- TOC entry 189 (class 1259 OID 16504)
-- Name: produto_id_produto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "produto_id_produto_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "produto_id_produto_seq" OWNER TO "postgres";

--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 189
-- Name: produto_id_produto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "produto_id_produto_seq" OWNED BY "produto"."id_produto";


--
-- TOC entry 2012 (class 2604 OID 16522)
-- Name: id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "cliente" ALTER COLUMN "id_cliente" SET DEFAULT "nextval"('"cliente_id_cliente_seq"'::"regclass");


--
-- TOC entry 2013 (class 2604 OID 16506)
-- Name: id_produto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "produto" ALTER COLUMN "id_produto" SET DEFAULT "nextval"('"produto_id_produto_seq"'::"regclass");


--
-- TOC entry 2147 (class 0 OID 16493)
-- Dependencies: 188
-- Data for Name: administrador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "administrador" ("id_administrador", "usuario", "senha", "email") FROM stdin;
\.


--
-- TOC entry 2140 (class 0 OID 16460)
-- Dependencies: 181
-- Data for Name: cli_nota_fiscal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "cli_nota_fiscal" ("id_nf_cliente", "id_cliente", "codigo_produto", "data_emissao", "valor_total", "id_produto") FROM stdin;
\.


--
-- TOC entry 2141 (class 0 OID 16465)
-- Dependencies: 182
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "cliente" ("cli_telefone", "cli_cpf", "cli_ie", "cli_cnpj", "id_endereco", "cli_nome_princ", "cli_nome_sec", "id_cliente") FROM stdin;
1155264006	32278940848	32	32	0	Danilo	Brandao	1
1155264006	32278940848	32	32	0	Danilo	Brandao	6
1155264006	32278940848	32	32	0	Danilo	Brandao	7
1155264006	32278940848	32	32	0	Danilo	Brandao	8
1155264006	32278940848	32	32	0	Danilo	Brandao	9
1155264006	32278940848	32	32	0	Danilo	Brandao	10
1155264006	32278940848	32	32	0	Danilo	Brandao	11
1155264006	32278940848	32	32	0	Danilo	Brandao	12
1155264006	32278940848	32	32	0	Danilo	Brandao	13
1155264006	32278940848	32	32	0	Danilo	Brandao	14
1155264006	32278940848	32	32	0	Danilo	Brandao	15
\.


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 190
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"cliente_id_cliente_seq"', 15, true);


--
-- TOC entry 2142 (class 0 OID 16470)
-- Dependencies: 183
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "endereco" ("id_endereco", "cep", "logradouro", "numero", "bairro", "cidade", "estado", "país") FROM stdin;
\.


--
-- TOC entry 2146 (class 0 OID 16490)
-- Dependencies: 187
-- Data for Name: estoque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "estoque" ("prd_preco_venda", "prd_preco_compra", "prd_data_compra", "prd_data_validade", "prd_medida", "prd_data_venda", "prd_cod_barras") FROM stdin;
\.


--
-- TOC entry 2144 (class 0 OID 16480)
-- Dependencies: 185
-- Data for Name: for_nota_fiscal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "for_nota_fiscal" ("id_nf_fornecedor", "id_fornecedor", "id_produto", "codigo_lote", "data_emissao") FROM stdin;
\.


--
-- TOC entry 2143 (class 0 OID 16475)
-- Dependencies: 184
-- Data for Name: fornecedor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "fornecedor" ("id_fornecedor", "forn_nome_empresa", "forn_nome_fantasia", "forn_cnpj", "forn_ie", "forn_email", "forn_tel", "id_endereco") FROM stdin;
\.


--
-- TOC entry 2145 (class 0 OID 16485)
-- Dependencies: 186
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "produto" ("prd_nome_padrao", "prd_nome_qualificador", "prd_unidade_medida", "id_produto", "prd_cod_barras") FROM stdin;
Sabão	em pó	Kg	173	1
Farinha	de trigo	Kg	175	31
Sabão	em pó	Kg	177	1
Farinha	de trigo	Kg	179	31
Sabão	em pó	Kg	181	1
Farinha	de trigo	Kg	183	31
Sabão	em pó	Kg	185	1
Sabão	em pó	Kg	129	1
Farinha	de trigo	Kg	131	31
Sabão	em pó	Kg	133	1
Farinha	de trigo	Kg	135	31
Sabão	em pó	Kg	137	1
Farinha	de trigo	Kg	139	31
Sabão	em pó	Kg	141	1
Farinha	de trigo	Kg	143	31
Sabão	em pó	Kg	145	1
Farinha	de trigo	Kg	147	31
Sabão	em pó	Kg	149	1
Farinha	de trigo	Kg	151	31
Sabão	em pó	Kg	153	1
Farinha	de trigo	Kg	155	31
Sabão	em pó	Kg	157	1
Farinha	de trigo	Kg	159	31
Sabão	em pó	Kg	161	1
Farinha	de trigo	Kg	163	31
Sabão	em pó	Kg	165	1
Farinha	de trigo	Kg	167	31
Sabão	em pó	Kg	169	1
Farinha	de trigo	Kg	171	31
Farinha	de trigo	Kg	187	31
Sabão	em pó	Kg	189	1
Farinha	de trigo	Kg	191	31
Sabão	em pó	Kg	193	1
Farinha	de trigo	Kg	195	31
Sabão	em pó	Kg	197	1
Farinha	de trigo	Kg	199	31
Sabão	em pó	Kg	201	1
Farinha	de trigo	Kg	203	31
Sabão	em pó	Kg	205	1
Farinha	de trigo	Kg	207	31
Sabão	em pó	Kg	209	1
Farinha	de trigo	Kg	211	31
Sabão	em pó	Kg	225	1
Farinha	de trigo	Kg	227	31
Sabão	em pó	Kg	229	1
Farinha	de trigo	Kg	231	31
Sabão	em pó	Kg	213	1
Farinha	de trigo	Kg	215	31
Sabão	em pó	Kg	233	1
Farinha	de trigo	Kg	235	31
Sabão	em pó	Kg	237	1
Farinha	de trigo	Kg	239	31
Sabão	em pó	Kg	241	1
Sabão	em pó	Kg	217	1
Farinha	de trigo	Kg	219	31
Farinha	de trigo	Kg	243	31
Sabão	em pó	Kg	245	1
Farinha	de trigo	Kg	247	31
Sabão	em pó	Kg	249	1
Farinha	de trigo	Kg	251	31
Sabão	em pó	Kg	253	1
Farinha	de trigo	Kg	255	31
Sabão	em pó	Kg	257	1
Farinha	de trigo	Kg	259	31
Sabão	em pó	Kg	221	1
Farinha	de trigo	Kg	223	31
Sabão	em pó	Kg	261	1
Farinha	de trigo	Kg	263	31
Sabão	em pó	Kg	265	1
Farinha	de trigo	Kg	267	31
Sabão	em pó	Kg	269	1
Farinha	de trigo	Kg	271	31
Sabão	em pó	Kg	273	1
Farinha	de trigo	Kg	275	31
Sabão	em pó	Kg	277	1
Farinha	de trigo	Kg	279	31
Sabão	em pó	Kg	281	1
Farinha	de trigo	Kg	283	31
Sabão	em pó	Kg	285	1
Farinha	de trigo	Kg	287	31
Farinha	de mandioca	Kg	176	32
Farinha	de mandioca	Kg	200	32
Farinha	de mandioca	Kg	212	32
Farinha	de mandioca	Kg	228	32
Farinha	de mandioca	Kg	132	32
Farinha	de mandioca	Kg	136	32
Farinha	de mandioca	Kg	140	32
Farinha	de mandioca	Kg	144	32
Farinha	de mandioca	Kg	148	32
Farinha	de mandioca	Kg	232	32
Farinha	de mandioca	Kg	152	32
Farinha	de mandioca	Kg	156	32
Farinha	de mandioca	Kg	180	32
Farinha	de mandioca	Kg	160	32
Farinha	de mandioca	Kg	216	32
Farinha	de mandioca	Kg	164	32
Farinha	de mandioca	Kg	204	32
Farinha	de mandioca	Kg	184	32
Farinha	de mandioca	Kg	236	32
Farinha	de mandioca	Kg	168	32
Farinha	de mandioca	Kg	240	32
Farinha	de mandioca	Kg	220	32
Farinha	de mandioca	Kg	244	32
Farinha	de mandioca	Kg	248	32
Farinha	de mandioca	Kg	252	32
Farinha	de mandioca	Kg	256	32
Farinha	de mandioca	Kg	172	32
Farinha	de mandioca	Kg	260	32
Farinha	de mandioca	Kg	264	32
Farinha	de mandioca	Kg	208	32
Farinha	de mandioca	Kg	268	32
Farinha	de mandioca	Kg	224	32
Farinha	de mandioca	Kg	188	32
Farinha	de mandioca	Kg	272	32
Farinha	de mandioca	Kg	276	32
Farinha	de mandioca	Kg	192	32
Farinha	de mandioca	Kg	280	32
Farinha	de mandioca	Kg	284	32
Farinha	de mandioca	Kg	288	32
Detergente	em po32	litro	282	2
Detergente	em po32	litro	286	2
Farinha	de mandioca	Kg	196	32
\.


--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 189
-- Name: produto_id_produto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"produto_id_produto_seq"', 288, true);


--
-- TOC entry 2025 (class 2606 OID 16497)
-- Name: administrador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "administrador"
    ADD CONSTRAINT "administrador_pkey" PRIMARY KEY ("id_administrador");


--
-- TOC entry 2015 (class 2606 OID 16464)
-- Name: cli_nota_fiscal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "cli_nota_fiscal"
    ADD CONSTRAINT "cli_nota_fiscal_pkey" PRIMARY KEY ("id_nf_cliente");


--
-- TOC entry 2017 (class 2606 OID 16474)
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "endereco"
    ADD CONSTRAINT "endereco_pkey" PRIMARY KEY ("id_endereco");


--
-- TOC entry 2021 (class 2606 OID 16484)
-- Name: for_nota_fiscal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "for_nota_fiscal"
    ADD CONSTRAINT "for_nota_fiscal_pkey" PRIMARY KEY ("id_nf_fornecedor");


--
-- TOC entry 2019 (class 2606 OID 16479)
-- Name: fornecedor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "fornecedor"
    ADD CONSTRAINT "fornecedor_pkey" PRIMARY KEY ("id_fornecedor");


--
-- TOC entry 2023 (class 2606 OID 16508)
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "produto"
    ADD CONSTRAINT "produto_pkey" PRIMARY KEY ("id_produto");


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA "public" FROM PUBLIC;
REVOKE ALL ON SCHEMA "public" FROM "postgres";
GRANT ALL ON SCHEMA "public" TO "postgres";
GRANT ALL ON SCHEMA "public" TO PUBLIC;


-- Completed on 2016-04-04 21:47:11

--
-- PostgreSQL database dump complete
--

\connect "postgres"

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-04-04 21:47:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2095 (class 1262 OID 12373)
-- Dependencies: 2094
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "postgres" IS 'default administrative connection database';


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA "public"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA "public" IS 'standard public schema';


--
-- TOC entry 2 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "plpgsql" WITH SCHEMA "pg_catalog";


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "plpgsql"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "plpgsql" IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "adminpack" WITH SCHEMA "pg_catalog";


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION "adminpack"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "adminpack" IS 'administrative functions for PostgreSQL';


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA "public" FROM PUBLIC;
REVOKE ALL ON SCHEMA "public" FROM "postgres";
GRANT ALL ON SCHEMA "public" TO "postgres";
GRANT ALL ON SCHEMA "public" TO PUBLIC;


-- Completed on 2016-04-04 21:47:11

--
-- PostgreSQL database dump complete
--

\connect "sgce"

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-04-04 21:47:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA "public"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA "public" IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "plpgsql" WITH SCHEMA "pg_catalog";


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION "plpgsql"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "plpgsql" IS 'PL/pgSQL procedural language';


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA "public" FROM PUBLIC;
REVOKE ALL ON SCHEMA "public" FROM "postgres";
GRANT ALL ON SCHEMA "public" TO "postgres";
GRANT ALL ON SCHEMA "public" TO PUBLIC;


-- Completed on 2016-04-04 21:47:12

--
-- PostgreSQL database dump complete
--

\connect "template1"

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-04-04 21:47:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2094 (class 1262 OID 1)
-- Dependencies: 2093
-- Name: template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "template1" IS 'default template for new databases';


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA "public"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA "public" IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "plpgsql" WITH SCHEMA "pg_catalog";


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION "plpgsql"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "plpgsql" IS 'PL/pgSQL procedural language';


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA "public" FROM PUBLIC;
REVOKE ALL ON SCHEMA "public" FROM "postgres";
GRANT ALL ON SCHEMA "public" TO "postgres";
GRANT ALL ON SCHEMA "public" TO PUBLIC;


-- Completed on 2016-04-04 21:47:12

--
-- PostgreSQL database dump complete
--

-- Completed on 2016-04-04 21:47:12

--
-- PostgreSQL database cluster dump complete
--

