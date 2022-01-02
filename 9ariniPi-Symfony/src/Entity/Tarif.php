<?php

namespace App\Entity;

use App\Repository\TarifRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TarifRepository::class)
 */
class Tarif
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prixGenreTous;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prixGenreHomme;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prixGenreFamme;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prixAges;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prixDuree;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $nbPubParCible;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrixGenreTous(): ?int
    {
        return $this->prixGenreTous;
    }

    public function setPrixGenreTous(?int $prixGenreTous): self
    {
        $this->prixGenreTous = $prixGenreTous;

        return $this;
    }

    public function getPrixGenreHomme(): ?int
    {
        return $this->prixGenreHomme;
    }

    public function setPrixGenreHomme(?int $prixGenreHomme): self
    {
        $this->prixGenreHomme = $prixGenreHomme;

        return $this;
    }

    public function getPrixGenreFamme(): ?int
    {
        return $this->prixGenreFamme;
    }

    public function setPrixGenreFamme(?int $prixGenreFamme): self
    {
        $this->prixGenreFamme = $prixGenreFamme;

        return $this;
    }

    public function getPrixAges(): ?int
    {
        return $this->prixAges;
    }

    public function setPrixAges(?int $prixAges): self
    {
        $this->prixAges = $prixAges;

        return $this;
    }

    public function getPrixDuree(): ?int
    {
        return $this->prixDuree;
    }

    public function setPrixDuree(?int $prixDuree): self
    {
        $this->prixDuree = $prixDuree;

        return $this;
    }

    public function getNbPubParCible(): ?int
    {
        return $this->nbPubParCible;
    }

    public function setNbPubParCible(?int $nbPubParCible): self
    {
        $this->nbPubParCible = $nbPubParCible;

        return $this;
    }
}
