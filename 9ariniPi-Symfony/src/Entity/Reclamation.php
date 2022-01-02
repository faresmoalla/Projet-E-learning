<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Boolean;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Reclamation
 *
 * @ORM\Table(name="reclamation", indexes={@ORM\Index(name="FOREIGN 1", columns={"utilisateurID"})})
 * @ORM\Entity(repositoryClass="App\Repository\ReclamationRepository")
 */
class Reclamation
{
    /**
     * @var int
     *
     * @ORM\Column(name="reclamationID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $reclamationid;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieReclamation", type="string", length=9, nullable=false)
     */
    private $categoriereclamation;

    /**
     * @var string
     *
     * @ORM\Column(name="messageReclamation", type="string", length=255, nullable=false)
     */
    private $messagereclamation;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateReclamation", type="datetime", nullable=false)
     */
    private $datereclamation;

    /**
     * @var string
     *
     * @ORM\Column(name="reponseReclamation", type="string", length=255, nullable=true)
     */
    private $reponsereclamation;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateReponse", type="datetime", nullable=true)
     */
    private $datereponse;

    /**
     * @var string
     *
     * @ORM\Column(name="etatReclamation", type="string", length=8, nullable=false)
     */
    private $etatreclamation;

    /**
     * @var bool
     *
     * @ORM\Column(name="etatReponse", type="boolean", nullable=true)
     */
    private $etatreponse;

    /**
     * @var \Utilisateur
     *
     * @ORM\ManyToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="utilisateurID", referencedColumnName="id")
     * })
     */
    private $utilisateurid;

    public function getReclamationid(): ?int
    {
        return $this->reclamationid;
    }

    public function getMessagereclamation(): ?string
    {
        return $this->messagereclamation;
    }

    public function setMessagereclamation(string $messagereclamation): self
    {
        $this->messagereclamation = $messagereclamation;

        return $this;
    }

    public function getReponsereclamation(): ?string
    {
        return $this->reponsereclamation;
    }

    public function setReponsereclamation(?string $reponsereclamation): self
    {
        $this->reponsereclamation = $reponsereclamation;

        return $this;
    }

    public function getCategoriereclamation(): ?string
    {
        return $this->categoriereclamation;
    }

    public function setCategoriereclamation(string $categoriereclamation): self
    {
        $this->categoriereclamation = $categoriereclamation;

        return $this;
    }

    public function getDatereclamation(): ?\DateTimeInterface
    {
        return $this->datereclamation;
    }

    public function setDatereclamation(\DateTimeInterface $datereclamation): self
    {
        $this->datereclamation = $datereclamation;

        return $this;
    }

    public function getDatereponse(): ?\DateTimeInterface
    {
        return $this->datereponse;
    }

    public function setDatereponse(\DateTimeInterface $datereponse): self
    {
        $this->datereponse = $datereponse;

        return $this;
    }

    public function getEtatreclamation(): ?string
    {
        return $this->etatreclamation;
    }

    public function setEtatreclamation(string $etatreclamation): self
    {
        $this->etatreclamation = $etatreclamation;

        return $this;
    }

    public function getEtatreponse(): ?bool
    {
        return $this->etatreponse;
    }

    public function setEtatreponse(bool $etatreponse): self
    {
        $this->etatreponse = $etatreponse;

        return $this;
    }

    public function getUtilisateurid(): ?Utilisateur
    {
        return $this->utilisateurid;
    }

    public function setUtilisateurid(Utilisateur $utilisateurid): self
    {
        $this->utilisateurid = $utilisateurid;

        return $this;
    }


}
