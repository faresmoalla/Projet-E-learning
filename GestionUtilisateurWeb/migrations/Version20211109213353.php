<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211109213353 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE note (id INT AUTO_INCREMENT NOT NULL, notevaleur INT DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE note_utilisateur (note_id INT NOT NULL, utilisateur_id INT NOT NULL, INDEX IDX_3BA5F43626ED0855 (note_id), INDEX IDX_3BA5F436FB88E14F (utilisateur_id), PRIMARY KEY(note_id, utilisateur_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE utilisateur (utilisateurID INT AUTO_INCREMENT NOT NULL, utilisateurPDP TEXT NOT NULL, utilisateurPrenom VARCHAR(20) DEFAULT NULL, utilisateurNom VARCHAR(20) NOT NULL, utilisateurGenre VARCHAR(100) NOT NULL, utilisateurDDN DATE NOT NULL, utilisateurAdresse VARCHAR(60) NOT NULL, utilisateurPays VARCHAR(100) NOT NULL, utilisateurphone INT NOT NULL, utilisateurFonction VARCHAR(50) DEFAULT NULL, utilisateurOrganisme VARCHAR(50) DEFAULT NULL, utilisateurSavoirEtre TEXT DEFAULT NULL, utilisateurAdresseEmail VARCHAR(60) NOT NULL, utilisateurMDP VARCHAR(5000) NOT NULL, utilisateurRole VARCHAR(50) NOT NULL, nomEntreprise VARCHAR(50) DEFAULT NULL, EntrepreneurSiteWeb VARCHAR(60) DEFAULT NULL, EntrepreneurUsage TEXT DEFAULT NULL, UNIQUE INDEX utilisateurAdresseEmail (utilisateurAdresseEmail), PRIMARY KEY(utilisateurID)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE note_utilisateur ADD CONSTRAINT FK_3BA5F43626ED0855 FOREIGN KEY (note_id) REFERENCES note (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE note_utilisateur ADD CONSTRAINT FK_3BA5F436FB88E14F FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE note_utilisateur DROP FOREIGN KEY FK_3BA5F43626ED0855');
        $this->addSql('ALTER TABLE note_utilisateur DROP FOREIGN KEY FK_3BA5F436FB88E14F');
        $this->addSql('DROP TABLE note');
        $this->addSql('DROP TABLE note_utilisateur');
        $this->addSql('DROP TABLE utilisateur');
    }
}
