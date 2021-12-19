DROP TABLE human_gene IF EXISTS;
DROP TABLE mouse_gene IF EXISTS;
DROP TABLE mouse_gene_synonym IF EXISTS;
DROP TABLE mouse_gene_synonym_relation IF exists;
DROP TABLE ortholog IF EXISTS;


create table human_gene
(
    id                  NUMERIC,
    hgnc_acc_id         VARCHAR(300),
    name                VARCHAR(300),
    symbol              VARCHAR(300),
    ensembl_gene_acc_id VARCHAR(300),
    entrez_gene_acc_id  NUMERIC
);

create table mouse_gene
(
    id                  NUMERIC,
    ensembl_chromosome  VARCHAR(300),
    ensembl_gene_acc_id VARCHAR(300),
    ensembl_start       NUMERIC,
    ensembl_stop        NUMERIC,
    ensembl_strand      VARCHAR(300),
    entrez_gene_acc_id  NUMERIC,
    genome_build        VARCHAR(300),
    mgi_gene_acc_id     VARCHAR(300),
    name                VARCHAR(300),
    mgi_cm              VARCHAR(300),
    mgi_chromosome      VARCHAR(300),
    mgi_start           NUMERIC,
    mgi_stop            NUMERIC,
    mgi_strand          VARCHAR(300),
    ncbi_chromosome     NUMERIC,
    ncbi_start          NUMERIC,
    ncbi_stop           VARCHAR(300),
    ncbi_strand         VARCHAR(300),
    symbol              VARCHAR(300),
    type                VARCHAR(300),
    subtype             VARCHAR(300)
);

create table mouse_gene_synonym
(
    id              NUMERIC,
    mgi_gene_acc_id VARCHAR(300),
    synonym         VARCHAR(300)
);

create table mouse_gene_synonym_relation
(
    mouse_gene_id         VARCHAR(300),
    mouse_gene_synonym_id VARCHAR(300)
);

create table ortholog
(
    id                    NUMERIC,
    support               VARCHAR(300),
    support_raw           VARCHAR(300),
    support_count         NUMERIC,
    category              VARCHAR(300),
    human_gene_id         NUMERIC,
    mouse_gene_id         NUMERIC,
    is_max_human_to_mouse VARCHAR(300),
    is_max_mouse_to_human VARCHAR(300)
);
