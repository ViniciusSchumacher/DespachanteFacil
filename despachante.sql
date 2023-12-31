PGDMP         5                {            despachante    15.3    15.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16793    despachante    DATABASE     �   CREATE DATABASE despachante WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE despachante;
                postgres    false            �            1259    25003    cliente    TABLE     5  CREATE TABLE public.cliente (
    id integer NOT NULL,
    nome character varying(255),
    cpf character varying(11),
    endereco_id integer,
    sexo character varying(50),
    contato1 character varying(15),
    contato2 character varying(15),
    observacoes text,
    filepath character varying(255)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    25002    cliente_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public          postgres    false    219                       0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public          postgres    false    218            �            1259    24994    endereco    TABLE     �   CREATE TABLE public.endereco (
    id integer NOT NULL,
    logradouro character varying(255),
    numero integer,
    bairro character varying(255),
    cep character varying(9),
    cidade character varying(255),
    uf character varying(2)
);
    DROP TABLE public.endereco;
       public         heap    postgres    false            �            1259    24993    endereco_id_seq    SEQUENCE     �   CREATE SEQUENCE public.endereco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.endereco_id_seq;
       public          postgres    false    217                       0    0    endereco_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.id;
          public          postgres    false    216            �            1259    24985    veiculo    TABLE     O  CREATE TABLE public.veiculo (
    id integer NOT NULL,
    placa character varying(7),
    renavam character varying(11),
    chassi character varying(17),
    marcamodelo character varying(255),
    anofab integer,
    anomod integer,
    categoria character varying(50),
    carroceria character varying(50),
    observacoes text
);
    DROP TABLE public.veiculo;
       public         heap    postgres    false            �            1259    24984    veiculo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.veiculo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.veiculo_id_seq;
       public          postgres    false    215                       0    0    veiculo_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.veiculo_id_seq OWNED BY public.veiculo.id;
          public          postgres    false    214            q           2604    25006 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            p           2604    24997    endereco id    DEFAULT     j   ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);
 :   ALTER TABLE public.endereco ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            o           2604    24988 
   veiculo id    DEFAULT     h   ALTER TABLE ONLY public.veiculo ALTER COLUMN id SET DEFAULT nextval('public.veiculo_id_seq'::regclass);
 9   ALTER TABLE public.veiculo ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215                      0    25003    cliente 
   TABLE DATA           n   COPY public.cliente (id, nome, cpf, endereco_id, sexo, contato1, contato2, observacoes, filepath) FROM stdin;
    public          postgres    false    219          
          0    24994    endereco 
   TABLE DATA           S   COPY public.endereco (id, logradouro, numero, bairro, cep, cidade, uf) FROM stdin;
    public          postgres    false    217                    0    24985    veiculo 
   TABLE DATA           ~   COPY public.veiculo (id, placa, renavam, chassi, marcamodelo, anofab, anomod, categoria, carroceria, observacoes) FROM stdin;
    public          postgres    false    215   �                   0    0    cliente_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cliente_id_seq', 8, true);
          public          postgres    false    218                       0    0    endereco_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.endereco_id_seq', 8, true);
          public          postgres    false    216                       0    0    veiculo_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.veiculo_id_seq', 8, true);
          public          postgres    false    214            w           2606    25010    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    219            u           2606    25001    endereco endereco_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.endereco DROP CONSTRAINT endereco_pkey;
       public            postgres    false    217            s           2606    24990    veiculo veiculo_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT veiculo_pkey;
       public            postgres    false    215            x           2606    25011     cliente cliente_endereco_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_endereco_id_fkey FOREIGN KEY (endereco_id) REFERENCES public.endereco(id);
 J   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_endereco_id_fkey;
       public          postgres    false    3189    217    219               h  x���Mn�0���S�2�/�]����"��� ��X��D��6]�=A�E�EO��$Hkw�"�6�(q���7�0����q�@��d9]��]\�T�I&�d�Y������W���|�ٶ������P���g8�,�Y��`Y!Er�p���ƯG�Bg�ރR2�NA�|�8�xܜ�4�4A�ԩ�������1��]��dkjgv�w�$d�]e*K�
re��f+C"�7�m~r���٫U��}@BB*�f��(���ܯmC[a9Kʻ~�\�Ǡ����#K�ڼ!K�ژ�ζ@'\qIӉ��4�U�2��	��,c�Ֆ�~�6�ԣ�KХ0����sc�B��C�����D0���t�r=~Z�#��Xy��ss���+�ڑ)&�aV�q�9�)�S�)�J#%3P����ǑR"(a�P��i�����V�ח�d���17�F�Y����O1�>0Oid�Ah�5,g��W3R��ڴᅁʰ�nmY���ۀ�B�O�3�R�@�C�S,{	����S�m0��Y;7���!�j��
��T25Ѵ��}�Mڂ�C=�`|_���t�>�a���W����;��g��ݷ���lw?��Q��h4���C      
   �   x�u��N�0@��W�4�Ѕ=��Z��'/�h�.�-��[�n4�4=��י����0X���ܦi�S�M0�$[^j��d���!;S�zi�R�'�M��`��<.J��h�Lqh#U��bǪ��8bO�Z�zm���Nyãa�V�+��������i���L�ӆ�F��l#�}���S�E�r^ȋ�fmz
Wz��,�}��k�X�*\r�w��V�8�z�ݩY�q�$���z�           x�E�=j�0�zt
� x��ʉ,[Zֲ�%��N0�Ҧ�AR� �X��o�a>� z��O�)�,�
*��fDзmN����	x�y�������a�ˡV:8":\��PἋ�}�l۴-�7�c�@g���!ɼ����m}�����F�
�OS���"+�,4 B�M�c�]�D;9���4B缓F������ޫ��@����y|8V�DYU�<t��w��kMIg(�3�� �3�F�Wu�X��c�����R�     