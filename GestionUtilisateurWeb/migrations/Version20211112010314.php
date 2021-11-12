<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211112010314 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(180) NOT NULL, roles JSON NOT NULL, password VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE note DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE note CHANGE noteValeur notevaleur INT DEFAULT NULL, CHANGE noteid id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE note ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE utilisateur CHANGE utilisateurDDN utilisateurDDN DATE DEFAULT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE user');
        $this->addSql('ALTER TABLE note MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE note DROP FOREIGN KEY FK_CFBDFA14FB88E14F');
        $this->addSql('ALTER TABLE note DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE note CHANGE notevaleur noteValeur INT NOT NULL, CHANGE id noteID INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE note ADD PRIMARY KEY (noteID)');
        $this->addSql('ALTER TABLE utilisateur CHANGE utilisateurDDN utilisateurDDN DATE NOT NULL');
    }
}
