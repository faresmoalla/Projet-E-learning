<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Formation
 *
 * @ORM\Table(name="formation", indexes={@ORM\Index(name="FK_UserId", columns={"utilisateurID"})})
 * @ORM\Entity
 */
class Formation
{
    /**
     * @var int
     *
     * @ORM\Column(name="formationID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $formationid;

    /**
     * @var int
     *
     * @ORM\Column(name="utilisateurID", type="integer", nullable=false)
     */
    private $utilisateurid;

    /**
     * @var string
     *
     * @ORM\Column(name="nomFormation", type="string", length=100, nullable=false)
     */
    private $nomformation;

    public function getFormationid(): ?int
    {
        return $this->formationid;
    }

    public function getUtilisateurid(): ?int
    {
        return $this->utilisateurid;
    }

    public function setUtilisateurid(int $utilisateurid): self
    {
        $this->utilisateurid = $utilisateurid;

        return $this;
    }

    public function getNomformation(): ?string
    {
        return $this->nomformation;
    }

    public function setNomformation(string $nomformation): self
    {
        $this->nomformation = $nomformation;

        return $this;
    }


}
