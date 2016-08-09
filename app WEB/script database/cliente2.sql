SELECT cli_telefone, cli_cpf, cli_ie, cli_cnpj, id_endereco, cli_nome_princ, 
       cli_nome_sec, id_cliente
  FROM public.cliente;


SELECT id_cliente, cli_telefone, cli_cpf, cli_ie, cli_cnpj, id_endereco,cli_nome_princ,cli_nome_sec 
  FROM public.cliente where cli_telefone = '32'