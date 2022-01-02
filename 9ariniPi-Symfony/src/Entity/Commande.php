<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande", indexes={@ORM\Index(name="FOREIGN2", columns={"coursID"}), @ORM\Index(name="FOREIGN1", columns={"panierID"})})
 * @ORM\Entity(repositoryClass="App\Repository\CommandeRepository")
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="commandeID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $commandeid;

    /**
     * @var \Panier
     *
     * @ORM\ManyToOne(targetEntity="Panier")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="panierID", referencedColumnName="panierID")
     * })
     */
    private $panierid;

    /**
     * @var \Cours
     *
     * @ORM\ManyToOne(targetEntity="Cours")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="coursID", referencedColumnName="id")
     * })
     */
    private $coursid;

    public function getCommandeid(): ?int
    {
        return $this->commandeid;
    }

    public function getPanierid(): ?Panier
    {
        return $this->panierid;
    }

    public function setPanierid(?Panier $panierid): self
    {
        $this->panierid = $panierid;

        return $this;
    }

    public function getCoursid(): ?Cours
    {
        return $this->coursid;
    }

    public function setCoursid(?Cours $coursid): self
    {
        $this->coursid = $coursid;

        return $this;
    }


}
