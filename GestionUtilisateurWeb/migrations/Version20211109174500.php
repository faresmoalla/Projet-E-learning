<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211109174500 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE utilisateur ADD note INT DEFAULT NULL, CHANGE utilisateurPDP utilisateurPDP TEXT NOT NULL, CHANGE utilisateurPrenom utilisateurPrenom VARCHAR(20) DEFAULT NULL, CHANGE utilisateurFonction utilisateurFonction VARCHAR(50) DEFAULT NULL, CHANGE utilisateurOrganisme utilisateurOrganisme VARCHAR(50) DEFAULT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE utilisateur DROP note, CHANGE utilisateurPDP utilisateurPDP TEXT CHARACTER SET utf8 DEFAULT NULL COLLATE `utf8_bin`, CHANGE utilisateurPrenom utilisateurPrenom VARCHAR(20) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, CHANGE utilisateurFonction utilisateurFonction VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, CHANGE utilisateurOrganisme utilisateurOrganisme VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`');
    }
}
