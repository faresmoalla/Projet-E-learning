<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Chapitre
 *
 * @ORM\Table(name="chapitre", indexes={@ORM\Index(name="FK_coursnOM", columns={"nomCours"})})
 * @ORM\Entity(repositoryClass="App\Repository\ChapitreRepository")
 */
class Chapitre
{
    /**
     * @var int
     *
     * @ORM\Column(name="chapitreID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $chapitreid;

    /**
     * @var string
     *
     * @ORM\Column(name="nomCours", type="string", length=100, nullable=false)
     */
    private $nomcours;

    /**
     * @var string
     *
     * @ORM\Column(name="chapitreNom", type="string", length=100, nullable=false)
     */
    private $chapitrenom;

    /**
     * @var string
     *
     * @ORM\Column(name="videoChapitre", type="text", length=65535, nullable=false)
     */
    private $videochapitre;

    public function getChapitreid(): ?int
    {
        return $this->chapitreid;
    }

    public function getNomcours(): ?string
    {
        return $this->nomcours;
    }

    public function setNomcours(string $nomcours): self
    {
        $this->nomcours = $nomcours;

        return $this;
    }

    public function getChapitrenom(): ?string
    {
        return $this->chapitrenom;
    }

    public function setChapitrenom(string $chapitrenom): self
    {
        $this->chapitrenom = $chapitrenom;

        return $this;
    }

    public function getVideochapitre(): ?string
    {
        return $this->videochapitre;
    }

    public function setVideochapitre(string $videochapitre): self
    {
        $this->videochapitre = $videochapitre;

        return $this;
    }


}
