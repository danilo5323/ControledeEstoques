-- Database: "Gerenciador"

-- DROP TABLE public.cli_nota_fiscal;


CREATE TABLE public.cli_nota_fiscal
(

  id_nf_cliente bigint not null primary key, --primary key
  id_cliente bigint NOT NULL, -- Foreign key
  codigo_produto bigint,
  data_emissao date
)
WITH (
  OIDS=FALSE
);

--drop table public.cliente
CREATE TABLE public.cliente
(
  id_cliente bigint NOT NULL primary key, -- Primary key
  cli_telefone integer,
  cli_cpf DECIMAL(11,0),
  cli_ie decimal (12,0),
  cli_cnpj decimal (14,0),
  id_endereco integer
)
WITH (
  OIDS=FALSE
);



CREATE TABLE public.endereco
(
  id_endereco bigint NOT NULL primary key, -- Primary key
  cep integer,
  logradouro varchar(55),
  numero varchar(10),
  bairro varchar(30),
  cidade varchar(30),
  estado varchar(30),
  país varchar(15)
)
WITH (
  OIDS=FALSE
);




CREATE TABLE public.fornecedor
(
  id_fornecedor     bigint NOT NULL primary key, -- Primary key
  forn_nome_empresa varchar(30),
  forn_nome_fantasia varchar(55),
  forn_cnpj         decimal(14,0),
  forn_ie           varchar(30),
  forn_email        varchar(30),
  forn_tel          varchar(16),
  id_endereco       bigint -- Foreign key
)
WITH (
  OIDS=FALSE
);


CREATE TABLE public.for_nota_fiscal
(
  id_nf_fornecedor     bigint NOT NULL primary key, -- Primary key
  id_fornecedor        bigint not null, --foreign key
  id_produto       bigint not null,
  codigo_lote          bigint,
  data_emissao         date
)
WITH (
  OIDS=FALSE
);


CREATE TABLE public.produto
(
  id_produto             bigint NOT NULL primary key, -- Primary key
  prd_nome_padrao        varchar(30), --foreign key
  prd_nome_qualificador  varchar(30),
  prd_unidade_medida     varchar(5),
  prd_medida             decimal(12,4),
  prd_data_validade      date,
  prd_preco_compra 	 decimal(10,2),
  prd_preco_venda 	 decimal(10,2)
)
WITH (
  OIDS=FALSE
);



CREATE TABLE public.estoque
(
  id_produto             bigint NOT NULL, -- foreign key
  prd_data_entrada	 date,
  prd_data_saida	 date
)
WITH (
  OIDS=FALSE
);



CREATE TABLE public.administrador
(
  id_administrador    bigint NOT NULL primary key, -- foreign key
  usuario varchar(10),
  senha	 varchar(20),
  email  varchar(30)
)
WITH (
  OIDS=FALSE
);
