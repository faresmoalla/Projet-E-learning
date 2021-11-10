<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Chapitre
 *
 * @ORM\Entity(repositoryClass="App\Repository\ChapitreRepository")
 */
class Chapitre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;


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

    /**
     * @var \Chapitre
     * @ORM\ManyToOne(targetEntity=Cours::class, inversedBy="Chapitre")
     * @ORM\JoinColumn(nullable=false)({
     *  @ORM\JoinColumn(name="cours_id", referencedColumnName="id")
     * })
     */
    private $cours;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getCours(): ?Cours
    {
        return $this->cours;
    }

    public function setCours(?Cours $cours): self
    {
        $this->cours = $cours;

        return $this;
    }


}
