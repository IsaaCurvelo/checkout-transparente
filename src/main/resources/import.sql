insert into cliente (id, nome, cpf) values (1, 'Antonaldinho Pereira', '07656654345'), (2, 'Alejo da Silva', '11298754634'), (3, 'Dona Menina', '76509812841');

insert into endereco (cliente_id, cep, logradouro, numero, complemento, bairro, cidade, estado) values (1, '65053-21', 'Rua dos Afogados', 31, null, 'Centro', 'São Luís', 'Maranhão'), (1, '65053-21', 'Rua dos Lamentos', 1412, null, 'João Paulo', 'São Luís', 'Maranhão'), (2, '65053-21', 'Avenida 13 ', 13, 'proximo à farmácia', 'Bequimão', 'São Luís', 'Maranhão'), (3, '65053-21', 'Avenida principal', 5315, null, 'Vicente Fialho', 'São Luís', 'Maranhão'), (3, '65053-21', 'Rua Projetada', 10, 'Condomínio não-me-rele-o-dedo', 'Penísula', 'São Luís', 'Maranhão');

insert into cartao_de_credito (id, cliente_id, nome, bandeira, numero_cartao, nome_titular_cartao, codigo_verificacao, expiracao) values (1, 1, 'cartão de mamãe', 'visa', '0000111122223333', 'Mãe de Antonaldinho', '122', '02/2028'), (2, 1, 'cartão de papai', 'master card', '9999111122223333', 'Pai de Antonaldinho', '666', '08/2022'), (3, 2, 'mel cartaum', 'american express', '0000111122224444', 'Alejo Silva', '063', '07/2011'), (4, 3, 'visa', 'visa', '1111111122223333', 'Dona Menina', '733', '10/2021');

insert into transportadora (id, nome, taxa_frete) values (1, 'Ajax Transportadora', 15.51), (2, 'Pesadaum Cometa', 20.76), (3, 'Pé de Pano expresso', 40.13);

insert into produto (id, descricao , preco) values (1, 'Sabão Líquido Limpatuto', 3.12), (2, 'Feijão Feijoei', 6.79), (3, 'Alcool em Gel Paloma', 18.00), (4, 'Arroz Soltin', 17.02), (5, 'Creme Dental VacaPortão', 10.21), (6, 'Desodorante Leite de Violetas', 12.66), (7, 'Talco Sapo não lava os pés', 8.90);
