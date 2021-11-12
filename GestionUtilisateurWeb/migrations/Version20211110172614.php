<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211110172614 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE note DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE note CHANGE noteValeur notevaleur INT DEFAULT NULL, CHANGE noteid id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE note ADD PRIMARY KEY (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE note MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE note DROP FOREIGN KEY FK_CFBDFA14FB88E14F');
        $this->addSql('ALTER TABLE note DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE note CHANGE notevaleur noteValeur INT NOT NULL, CHANGE id noteID INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE note ADD PRIMARY KEY (noteID)');
    }
}
