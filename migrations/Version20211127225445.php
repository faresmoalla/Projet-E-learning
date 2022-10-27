<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211127225445 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE calendar CHANGE start start DATETIME NOT NULL, CHANGE end end DATETIME NOT NULL');
        $this->addSql('ALTER TABLE chapitre ADD image VARCHAR(255) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE calendar CHANGE start start DATE NOT NULL, CHANGE end end DATE NOT NULL');
        $this->addSql('ALTER TABLE chapitre DROP FOREIGN KEY FK_8C62B0257ECF78B0');
        $this->addSql('ALTER TABLE chapitre DROP image');
        $this->addSql('ALTER TABLE cours DROP FOREIGN KEY FK_FDCA8C9CBCF5E72D');
    }
}
