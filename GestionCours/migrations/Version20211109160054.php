<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211109160054 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE chapitre ADD cours_id INT NOT NULL, ADD nomCours VARCHAR(100) NOT NULL');
        $this->addSql('CREATE INDEX IDX_8C62B0257ECF78B0 ON chapitre (cours_id)');
        $this->addSql('CREATE INDEX FK_coursnOM ON chapitre (nomCours)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE chapitre DROP FOREIGN KEY FK_8C62B0257ECF78B0');
        $this->addSql('DROP INDEX IDX_8C62B0257ECF78B0 ON chapitre');
        $this->addSql('DROP INDEX FK_coursnOM ON chapitre');
        $this->addSql('ALTER TABLE chapitre DROP cours_id, DROP nomCours');
        $this->addSql('ALTER TABLE cours DROP FOREIGN KEY FK_FDCA8C9CBCF5E72D');
    }
}
