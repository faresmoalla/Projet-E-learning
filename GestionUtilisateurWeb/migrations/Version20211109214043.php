<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211109214043 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE note_utilisateur (note_id INT NOT NULL, utilisateur_id INT NOT NULL, INDEX IDX_3BA5F43626ED0855 (note_id), INDEX IDX_3BA5F436FB88E14F (utilisateur_id), PRIMARY KEY(note_id, utilisateur_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE note_utilisateur ADD CONSTRAINT FK_3BA5F43626ED0855 FOREIGN KEY (note_id) REFERENCES note (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE note_utilisateur ADD CONSTRAINT FK_3BA5F436FB88E14F FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE note DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE note CHANGE noteValeur notevaleur INT DEFAULT NULL, CHANGE noteid id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE note ADD PRIMARY KEY (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE note_utilisateur');
        $this->addSql('ALTER TABLE note MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE note DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE note CHANGE notevaleur noteValeur INT NOT NULL, CHANGE id noteID INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE note ADD PRIMARY KEY (noteID)');
    }
}
