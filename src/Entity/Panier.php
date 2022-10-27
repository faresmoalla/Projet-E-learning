<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Panier
 *
 * @ORM\Table(name="panier", indexes={@ORM\Index(name="FOREIGN1", columns={"utilisateurID"})})
 * @ORM\Entity(repositoryClass="App\Repository\PanierRepository")
 */
class Panier
{
    /**
     * @var int
     *
     * @ORM\Column(name="panierID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $panierid;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datePanier", type="date", nullable=false)
     */
    private $datepanier;

    /**
     * @var string
     *
     * @ORM\Column(name="etatPanier", type="string", length=8, nullable=false)
     */
    private $etatpanier;

    /**
     * @var \Utilisateur
     *
     * @ORM\ManyToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="utilisateurID", referencedColumnName="id")
     * })
     */
    private $utilisateurid;

    public function getPanierid(): ?int
    {
        return $this->panierid;
    }

    public function getDatepanier(): ?\DateTimeInterface
    {
        return $this->datepanier;
    }

    public function setDatepanier(\DateTimeInterface $datepanier): self
    {
        $this->datepanier = $datepanier;

        return $this;
    }

    public function getEtatpanier(): ?string
    {
        return $this->etatpanier;
    }

    public function setEtatpanier(string $etatpanier): self
    {
        $this->etatpanier = $etatpanier;

        return $this;
    }

    public function getUtilisateurid(): ?Utilisateur
    {
        return $this->utilisateurid;
    }

    public function setUtilisateurid(?Utilisateur $utilisateurid): self
    {
        $this->utilisateurid = $utilisateurid;

        return $this;
    }


}
